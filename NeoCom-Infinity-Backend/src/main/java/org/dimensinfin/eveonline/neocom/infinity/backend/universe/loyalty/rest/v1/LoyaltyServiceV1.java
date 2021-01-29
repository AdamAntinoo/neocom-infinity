package org.dimensinfin.eveonline.neocom.infinity.backend.universe.loyalty.rest.v1;

import java.sql.SQLException;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;
import javax.validation.constraints.NotNull;

import org.jetbrains.annotations.NonNls;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import org.dimensinfin.eveonline.neocom.infinity.core.exception.NeoComRestError;
import org.dimensinfin.eveonline.neocom.infinity.core.exception.NeoComRuntimeBackendException;
import org.dimensinfin.eveonline.neocom.loyalty.persistence.LoyaltyOfferEntity;
import org.dimensinfin.eveonline.neocom.loyalty.persistence.LoyaltyOffersRepository;
import org.dimensinfin.eveonline.neocom.loyalty.service.LoyaltyService;

import static org.dimensinfin.eveonline.neocom.infinity.NeoComInfinityBackendApplication.APPLICATION_ERROR_CODE_PREFIX;
import static org.dimensinfin.eveonline.neocom.infinity.NeoComInfinityBackendApplication.PERSISTENCE_ERROR;

/**
 * @author Adam Antinoo (adamantinoo.git@gmail.com)
 * @since 0.20.0
 */
@Service
public class LoyaltyServiceV1 {
	@NonNls
	private static final ResourceBundle i18Bundle = ResourceBundle.getBundle( "i18/i18Properties" );
	private static final String LOYALTY_OFFERS_REPOSITORY_FAILURE = i18Bundle.getString( "error.codename.01" );

	protected static NeoComRestError errorINVENTORYSTOREREPOSITORYFAILURE( final SQLException sqle ) {
		if (null != sqle.getCause())
			return new NeoComRestError.Builder()
					.withErrorName( "LOYALTY_OFFERS_REPOSITORY_FAILURE" )
					.withErrorCode( APPLICATION_ERROR_CODE_PREFIX + PERSISTENCE_ERROR )
					.withHttpStatus( HttpStatus.INTERNAL_SERVER_ERROR )
					.withMessage( MessageFormat.format( "There is an SQL error on the LoyaltyOffers repository. {0}. SQL cause: {1}",
							sqle.getMessage(),
							sqle.getCause().toString() ) )
					.build();
		else
			return new NeoComRestError.Builder()
					.withErrorName( LOYALTY_OFFERS_REPOSITORY_FAILURE )
					.withErrorCode( APPLICATION_ERROR_CODE_PREFIX + PERSISTENCE_ERROR )
					.withHttpStatus( HttpStatus.INTERNAL_SERVER_ERROR )
					.withMessage( MessageFormat.format( "There is an SQL error on the LoyaltyOffers repository. {0}.",
							sqle.getMessage() ) )
					.build();
	}

	private final LoyaltyOffersRepository loyaltyOffersRepository;
	private final LoyaltyService loyaltyService;

	// - C O N S T R U C T O R S
	public LoyaltyServiceV1( @NotNull final LoyaltyOffersRepository loyaltyOffersRepository,
	                         @NotNull final LoyaltyService loyaltyService ) {
		this.loyaltyOffersRepository = loyaltyOffersRepository;
		this.loyaltyService = loyaltyService;
	}

	public List<LoyaltyOfferEntity> getLoyaltyRecommendedOfferForCorporation( final Integer corporationId ) {
		List<LoyaltyOfferEntity> offerList = new ArrayList<>();
		try {
			offerList = this.loyaltyOffersRepository.searchOffers4Corporation( corporationId );
		} catch (final SQLException sqle) {
			throw new NeoComRuntimeBackendException( errorINVENTORYSTOREREPOSITORYFAILURE( sqle ) );
		}
		if (offerList.isEmpty()) {
			offerList = this.loyaltyService.processOffers( corporationId );
		}
		return offerList
				.stream()
				.filter( offer -> offer.getLpValue() > this.loyaltyService.getProfitLevel() )
				.sorted( ( of1, of2 ) -> Long.compare( of2.getLpValue(), of1.getLpValue() ) )
				.collect( Collectors.toList() );
	}
}