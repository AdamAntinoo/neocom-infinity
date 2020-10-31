package org.dimensinfin.eveonline.neocom.infinity.acceptance.support.character.rest.v2;

import java.io.IOException;
import java.util.List;
import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import org.dimensinfin.eveonline.neocom.infinity.acceptance.support.AcceptanceTargetConfig;
import org.dimensinfin.eveonline.neocom.infinity.adapter.ESIDataProviderWrapper;
import org.dimensinfin.eveonline.neocom.infinity.backend.character.fitting.domain.FittingModel;
import org.dimensinfin.eveonline.neocom.infinity.pilot.rest.representation.PilotModel;
import org.dimensinfin.eveonline.neocom.infinity.support.core.CommonFeignClient;
import org.dimensinfin.eveonline.neocom.infinity.support.rest.NeoComApiv2;
import org.dimensinfin.eveonline.neocom.provider.ESIDataProvider;
import org.dimensinfin.logging.LogWrapper;

import retrofit2.Response;
import retrofit2.Retrofit;

@Component
public class CharacterFeignClientV2 extends CommonFeignClient {
	private ESIDataProviderWrapper esiDataProviderWrapper;

	// - C O N S T R U C T O R S
	@Autowired
	public CharacterFeignClientV2( final @NotNull AcceptanceTargetConfig acceptanceTargetConfig,
	                               final @NotNull ESIDataProviderWrapper esiDataProviderWrapper ) {
		super( acceptanceTargetConfig );
		this.esiDataProviderWrapper =  new ESIDataProvider.Builder()
				.withConfigurationProvider( this.configurationServiceWrapper.getSingleton() )
				.withFileSystemAdapter( this.fileSystemAdapter )
				.withRetrofitFactory( this.retrofitFactoryWrapper.getSingleton() )
				.withLocationCatalogService( this.locationCatalogServiceWrapper.getSingleton() )
				.withStoreCacheManager( this.storeCacheManagerWrapper.getSingleton() )
				.build();
	}

	public ResponseEntity<PilotModel> getPilotData( final Integer pilotId,
	                                                final String authorization ) throws IOException {
		final String ENDPOINT_MESSAGE = "Request the Pilot Public Data.";
		final Response<PilotModel> response = new Retrofit.Builder()
				.baseUrl( this.acceptanceTargetConfig.getBackendServer() )
				.addConverterFactory( GSON_CONVERTER_FACTORY )
				.build()
				.create( NeoComApiv2.class )
				.getPilotData(
						authorization,
						pilotId
				)
				.execute();
		if (response.isSuccessful()) {
			LogWrapper.info( ENDPOINT_MESSAGE );
			return new ResponseEntity<>( response.body(), HttpStatus.valueOf( response.code() ) );
		} else throw new IOException( ENDPOINT_MESSAGE + " Failed." );
	}

	public ResponseEntity<List<FittingModel>> getPilotFittings( final Integer pilotId,
	                                                            final String authorization ) throws IOException {
		final String ENDPOINT_MESSAGE = "Request the Pilot Public Data.";
		final Response<List<FittingModel>> response = new Retrofit.Builder()
				.baseUrl( this.acceptanceTargetConfig.getBackendServer() )
				.addConverterFactory( GSON_CONVERTER_FACTORY )
				.build()
				.create( NeoComApiv2.class )
				.getPilotFittings(
						authorization,
						pilotId
				)
				.execute();
		if (response.isSuccessful()) {
			LogWrapper.info( ENDPOINT_MESSAGE );
			return new ResponseEntity<>( response.body(), HttpStatus.valueOf( response.code() ) );
		} else throw new IOException( ENDPOINT_MESSAGE + " Failed." );
	}
}
