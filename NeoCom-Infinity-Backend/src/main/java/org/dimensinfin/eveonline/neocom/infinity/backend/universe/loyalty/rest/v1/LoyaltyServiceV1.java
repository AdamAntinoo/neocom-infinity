package org.dimensinfin.eveonline.neocom.infinity.backend.universe.loyalty.rest.v1;

import java.sql.SQLException;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import javax.validation.constraints.NotNull;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import org.dimensinfin.eveonline.neocom.infinity.core.exception.NeoComRestError;
import org.dimensinfin.eveonline.neocom.loyalty.persistence.LoyaltyOfferEntity;
import org.dimensinfin.eveonline.neocom.loyalty.persistence.LoyaltyOffersRepository;
import org.dimensinfin.eveonline.neocom.loyalty.service.LoyaltyService;

/**
 * @author Adam Antinoo (adamantinoo.git@gmail.com)
 * @since 0.20.0
 */
@Service
public class LoyaltyServiceV1 {
	public static NeoComRestError errorINVENTORYSTOREREPOSITORYFAILURE( final SQLException sqle ) {
		if (null != sqle.getCause())
			return new DimensinfinError.Builder()
					.withErrorName( "INVENTORY_STORE_REPOSITORY_FAILURE" )
					.withErrorCode( APPLICATION_ERROR_CODE_PREFIX + PERSISTENCE_ERROR )
					.withHttpStatus( HttpStatus.INTERNAL_SERVER_ERROR )
					.withMessage( MessageFormat.format( "There is an SQL error on the Inventory repository. {0}. SQL cause: {1}",
							sqle.getMessage(),
							sqle.getCause().toString() ) )
					.build();
		else
			return new DimensinfinError.Builder()
					.withErrorName( "INVENTORY_STORE_REPOSITORY_FAILURE" )
					.withErrorCode( APPLICATION_ERROR_CODE_PREFIX + PERSISTENCE_ERROR )
					.withHttpStatus( HttpStatus.INTERNAL_SERVER_ERROR )
					.withMessage( MessageFormat.format( "There is an SQL error on the Inventory repository. {0}.",
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
		} catch (final SQLException throwables) {
			throw new DimensinfinRuntimeException( Printer3DErrorInfo.errorCOILNOTFOUND( updateCoilRequest.getId() ) );
		}
		if (offerList.isEmpty()) {
			offerList = this.loyaltyService.processOffers( corporationId );
		}
		return offerList
				.stream()
				.filter( offer -> offer.getLpValue() > 1000 )
				.sorted( Comparator.comparingInt( LoyaltyOfferEntity::getLpValue ) )
				//				.sorted( Comparator.reverseOrder() )
				.collect( Collectors.toList() );
	}
}