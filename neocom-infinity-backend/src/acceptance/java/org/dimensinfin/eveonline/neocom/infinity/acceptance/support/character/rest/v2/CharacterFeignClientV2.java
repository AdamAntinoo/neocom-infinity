package org.dimensinfin.eveonline.neocom.infinity.acceptance.support.character.rest.v2;

import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import org.dimensinfin.eveonline.neocom.infinity.acceptance.support.AcceptanceTargetConfig;
import org.dimensinfin.eveonline.neocom.infinity.support.core.CommonFeignClient;

@Component
public class CharacterFeignClientV2 extends CommonFeignClient {
	//	private ESIDataProviderWrapper esiDataProviderWrapper;

	// - C O N S T R U C T O R S
	@Autowired
	public CharacterFeignClientV2( final @NotNull AcceptanceTargetConfig acceptanceTargetConfig
	                               //			,
	                               //	                               final @NotNull ESIDataProviderWrapper esiDataProviderWrapper
	) {
		super( acceptanceTargetConfig );
		//		this.esiDataProviderWrapper =  new ESIDataProvider.Builder()
		//				.withConfigurationProvider( this.configurationServiceWrapper.getSingleton() )
		//				.withFileSystemAdapter( this.fileSystemAdapter )
		//				.withRetrofitFactory( this.retrofitFactoryWrapper.getSingleton() )
		//				.withLocationCatalogService( this.locationCatalogServiceWrapper.getSingleton() )
		//				.withStoreCacheManager( this.storeCacheManagerWrapper.getSingleton() )
		//				.build();
	}

	//	public ResponseEntity<List<FittingModel>> getPilotFittings( final Integer pilotId,
	//	                                                            final String authorization ) throws IOException {
	//		final String ENDPOINT_MESSAGE = "Request the Pilot Public Data.";
	//		final Response<List<FittingModel>> response = new Retrofit.Builder()
	//				.baseUrl( this.acceptanceTargetConfig.getBackendServer() )
	//				.addConverterFactory( GSON_CONVERTER_FACTORY )
	//				.build()
	//				.create( NeoComApiv2.class )
	//				.getPilotFittings(
	//						authorization,
	//						pilotId
	//				)
	//				.execute();
	//		if (response.isSuccessful()) {
	//			LogWrapper.info( ENDPOINT_MESSAGE );
	//			return new ResponseEntity<>( response.body(), HttpStatus.valueOf( response.code() ) );
	//		} else throw new IOException( ENDPOINT_MESSAGE + " Failed." );
	//	}
}
