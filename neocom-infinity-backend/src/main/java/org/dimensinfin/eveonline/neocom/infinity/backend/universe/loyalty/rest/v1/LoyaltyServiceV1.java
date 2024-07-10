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
import org.dimensinfin.eveonline.neocom.infinity.core.exception.NeoComRuntimeBackendExceptionObsolete;
import org.dimensinfin.eveonline.neocom.loyalty.domain.LoyaltyServiceConfiguration;
import org.dimensinfin.eveonline.neocom.loyalty.persistence.LoyaltyOfferEntity;
import org.dimensinfin.eveonline.neocom.loyalty.persistence.LoyaltyOffersRepository;
import org.dimensinfin.eveonline.neocom.loyalty.service.LoyaltyService;
import org.dimensinfin.logging.LogWrapper;

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
			offerList = this.loyaltyOffersRepository.searchOffers4CorporationAndHub( corporationId, this.loyaltyService.getRegionId() );
			LogWrapper.info( MessageFormat.format( "Offers for {0} at repository: {1}",
					corporationId,
					offerList.size() ) );
		} catch (final SQLException sqle) {
			throw new NeoComRuntimeBackendExceptionObsolete( errorINVENTORYSTOREREPOSITORYFAILURE( sqle ) );
		}
		if (offerList.isEmpty()) {
			LogWrapper.info( "Reprocessing new list of offers." );
			offerList = this.loyaltyService.processOffers( corporationId );
		}
		return offerList
				.stream()
				.filter( offer -> offer.getLpValue() > this.loyaltyService.getProfitLevel() )
				.sorted( ( of1, of2 ) -> Long.compare( of2.getLpValue(), of1.getLpValue() ) )
				.collect( Collectors.toList() );
	}

	/**
	 * Changes the current loyalty service configuration. On this stage only the market hub region associated to the price comparison is the only
	 * field allowed to be changed from the front end.
	 *
	 * @param serviceConfiguration the new loyalty service configuration parameters.
	 * @return the complete updated configuration.
	 */
	public LoyaltyServiceConfiguration setLoyaltyServiceConfiguration( @NotNull final LoyaltyServiceConfiguration serviceConfiguration ) {
		if (null != serviceConfiguration.getMarketRegionId())
			this.loyaltyService.setRegionId( serviceConfiguration.getMarketRegionId() );
		return serviceConfiguration;
	}
}