package acceptance.support.universe.rest.v1;

import java.io.IOException;
import javax.validation.constraints.NotNull;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import acceptance.support.ITargetConfiguration;
import org.dimensinfin.eveonline.neocom.infinity.backend.market.domain.MarketData;
import org.dimensinfin.eveonline.neocom.infinity.support.core.CommonFeignClient;
import org.dimensinfin.eveonline.neocom.infinity.support.rest.UniverseApiV1;
import org.dimensinfin.logging.LogWrapper;

import retrofit2.Response;
import retrofit2.Retrofit;

public class UniverseFeignClientV1 extends CommonFeignClient {
	// - C O N S T R U C T O R S
	public UniverseFeignClientV1( final @NotNull ITargetConfiguration acceptanceTargetConfig ) {
		super( acceptanceTargetConfig );
	}

	public ResponseEntity<MarketData> getMarketConsolidatedByRegion4ItemId( final Integer regionId, final Integer typeId ) throws IOException {
		final String ENDPOINT_MESSAGE = "Request the Consolidated Market Data for iten on region.";
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
}
