package org.dimensinfin.eveonline.neocom.infinity.acceptance.support.universe.rest.v1;

import java.io.IOException;
import java.util.List;
import javax.validation.constraints.NotNull;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.dimensinfin.eveonline.neocom.infinity.acceptance.support.ITargetConfiguration;
import org.dimensinfin.eveonline.neocom.infinity.acceptance.support.api.UniverseApiV1;
import org.dimensinfin.eveonline.neocom.infinity.acceptance.support.dto.ServerStatusDto;
import org.dimensinfin.eveonline.neocom.infinity.support.core.CommonFeignClient;
import org.dimensinfin.eveonline.neocom.loyalty.domain.LoyaltyServiceConfiguration;
import org.dimensinfin.eveonline.neocom.loyalty.persistence.LoyaltyOfferEntity;
import org.dimensinfin.eveonline.neocom.market.MarketData;
import org.dimensinfin.logging.LogWrapper;

import retrofit2.Response;
import retrofit2.Retrofit;

public class UniverseFeignClientV1 extends CommonFeignClient {
	// - C O N S T R U C T O R S
	public UniverseFeignClientV1( final @NotNull ITargetConfiguration acceptanceTargetConfig ) {
		super( acceptanceTargetConfig );
	}

	// - G E T T E R S   &   S E T T E R S
	public ResponseEntity<ServerStatusDto> getServerStatus() throws IOException {
		final String ENDPOINT_MESSAGE = "Request the current ESI and backend status.";
		final Response<ServerStatusDto> response = new Retrofit.Builder()
				.baseUrl( this.acceptanceTargetConfig.getBackendServer() )
				.addConverterFactory( GSON_CONVERTER_FACTORY )
				.build()
				.create( UniverseApiV1.class )
				.getServerStatus()
				.execute();
		if (response.isSuccessful()) {
			LogWrapper.info( ENDPOINT_MESSAGE );
			return new ResponseEntity<>( response.body(), HttpStatus.valueOf( response.code() ) );
		} else throw new IOException( ENDPOINT_MESSAGE + " Failed." );
	}

	public ResponseEntity<List<LoyaltyOfferEntity>> getLoyaltyRecommendedOfferForCorporation( final Integer corporationId ) throws IOException {
		final String ENDPOINT_MESSAGE = "Request the list of Loyalty Offers.";
		final Response<List<LoyaltyOfferEntity>> response = new Retrofit.Builder()
				.baseUrl( this.acceptanceTargetConfig.getBackendServer() )
				.addConverterFactory( GSON_CONVERTER_FACTORY )
				.build()
				.create( UniverseApiV1.class )
				.getLoyaltyRecommendedOfferForCorporation( corporationId )
				.execute();
		if (response.isSuccessful()) {
			LogWrapper.info( ENDPOINT_MESSAGE );
			return new ResponseEntity<>( response.body(), HttpStatus.valueOf( response.code() ) );
		} else throw new IOException( ENDPOINT_MESSAGE + " Failed." );
	}

	public ResponseEntity<MarketData> getMarketConsolidatedByRegion4ItemId( final Integer regionId, final Integer typeId ) throws IOException {
		final String ENDPOINT_MESSAGE = "Request the Consolidated Market Data for an item on region.";
		final Response<MarketData> response = new Retrofit.Builder()
				.baseUrl( this.acceptanceTargetConfig.getBackendServer() )
				.addConverterFactory( GSON_CONVERTER_FACTORY )
				.build()
				.create( UniverseApiV1.class )
				.getMarketConsolidatedByRegion4ItemId( regionId, typeId )
				.execute();
		if (response.isSuccessful()) {
			LogWrapper.info( ENDPOINT_MESSAGE );
			return new ResponseEntity<>( response.body(), HttpStatus.valueOf( response.code() ) );
		} else throw new IOException( ENDPOINT_MESSAGE + " Failed." );
	}

	public ResponseEntity<LoyaltyServiceConfiguration> setLoyaltyServiceConfiguration( final LoyaltyServiceConfiguration loyaltyServiceConfiguration ) throws IOException {
		final String ENDPOINT_MESSAGE = "Request the update on the Loyalty Service Configuration.";
		final Response<LoyaltyServiceConfiguration> response = new Retrofit.Builder()
				.baseUrl( this.acceptanceTargetConfig.getBackendServer() )
				.addConverterFactory( GSON_CONVERTER_FACTORY )
				.build()
				.create( UniverseApiV1.class )
				.setLoyaltyServiceConfiguration( loyaltyServiceConfiguration )
				.execute();
		if (response.isSuccessful()) {
			LogWrapper.info( ENDPOINT_MESSAGE );
			return new ResponseEntity<>( response.body(), HttpStatus.valueOf( response.code() ) );
		} else throw new IOException( ENDPOINT_MESSAGE + " Failed." );
	}
}
