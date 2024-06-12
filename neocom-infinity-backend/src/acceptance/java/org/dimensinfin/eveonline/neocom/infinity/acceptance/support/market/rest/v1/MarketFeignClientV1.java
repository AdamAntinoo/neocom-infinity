package org.dimensinfin.eveonline.neocom.infinity.acceptance.support.market.rest.v1;

import java.io.IOException;
import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import org.dimensinfin.eveonline.neocom.infinity.acceptance.support.ITargetConfiguration;
import org.dimensinfin.eveonline.neocom.infinity.support.core.CommonFeignClient;
import org.dimensinfin.eveonline.neocom.infinity.support.rest.NeoComApiv1;
import org.dimensinfin.eveonline.neocom.market.MarketData;
import org.dimensinfin.logging.LogWrapper;

import retrofit2.Response;
import retrofit2.Retrofit;
@Component
public class MarketFeignClientV1 extends CommonFeignClient {
	// - C O N S T R U C T O R S
	@Autowired
	public MarketFeignClientV1( final @NotNull ITargetConfiguration acceptanceTargetConfig ) {
		super( acceptanceTargetConfig );
	}

	public ResponseEntity<MarketData> getMarketConsolidated4ItemId( final String authentication, final Integer typeId ) throws IOException {
		final String ENDPOINT_MESSAGE = "Request the Market Consolidated Data for an item for authenticated Pilot.";
		final Response<MarketData> response = new Retrofit.Builder()
				.baseUrl( this.acceptanceTargetConfig.getBackendServer() )
				.addConverterFactory( GSON_CONVERTER_FACTORY )
				.client( okHttpClient )
				.build()
				.create( NeoComApiv1.class )
				.getMarketConsolidated4ItemId( authentication, typeId )
				.execute();
		if (response.isSuccessful()) {
			LogWrapper.info( ENDPOINT_MESSAGE );
			return new ResponseEntity<>( response.body(), HttpStatus.valueOf( response.code() ) );
		} else throw new IOException( ENDPOINT_MESSAGE + " Failed." );
	}
}