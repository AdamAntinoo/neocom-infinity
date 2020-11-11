package org.dimensinfin.eveonline.neocom.infinity.acceptance.support.industry.rest.v1;

import java.io.IOException;
import javax.validation.constraints.NotNull;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.dimensinfin.eveonline.neocom.infinity.acceptance.support.ITargetConfiguration;
import org.dimensinfin.eveonline.neocom.infinity.backend.industry.fitting.domain.FittingBuildConfiguration;
import org.dimensinfin.eveonline.neocom.infinity.backend.industry.fitting.domain.FittingConfigurations;
import org.dimensinfin.eveonline.neocom.infinity.support.core.CommonFeignClient;
import org.dimensinfin.eveonline.neocom.infinity.support.rest.NeoComApiv1;
import org.dimensinfin.logging.LogWrapper;

import retrofit2.Response;
import retrofit2.Retrofit;

public class IndustryFeignClientV1 extends CommonFeignClient {
	// - C O N S T R U C T O R S
	public IndustryFeignClientV1( final @NotNull ITargetConfiguration acceptanceTargetConfig ) {
		super( acceptanceTargetConfig );
	}

	public ResponseEntity<FittingBuildConfiguration> getFittingBuildConfigurationSavedConfiguration( final String authentication, final Integer fittingId ) throws IOException {
		final String ENDPOINT_MESSAGE = "Request the Build Configurations for a Fitting.";
		final Response<FittingBuildConfiguration> response = new Retrofit.Builder()
				.baseUrl( this.acceptanceTargetConfig.getBackendServer() )
				.addConverterFactory( GSON_CONVERTER_FACTORY )
				.client( okHttpClient )
				.build()
				.create( NeoComApiv1.class )
				.getFittingBuildConfigurationSavedConfiguration( authentication, fittingId )
				.execute();
		if (response.isSuccessful()) {
			LogWrapper.info( ENDPOINT_MESSAGE );
			return new ResponseEntity<>( response.body(), HttpStatus.valueOf( response.code() ) );
		} else throw new IOException( ENDPOINT_MESSAGE + " Failed." );
	}

	public ResponseEntity<FittingConfigurations> getFittingConfigurations( final String authentication, final Integer fittingId ) throws IOException {
		final String ENDPOINT_MESSAGE = "Request the Build Configurations for a Fitting.";
		final Response<FittingConfigurations> response = new Retrofit.Builder()
				.baseUrl( this.acceptanceTargetConfig.getBackendServer() )
				.addConverterFactory( GSON_CONVERTER_FACTORY )
				.client( okHttpClient )
				.build()
				.create( NeoComApiv1.class )
				.getFittingBuildConfigurationById( authentication, fittingId )
				.execute();
		if (response.isSuccessful()) {
			LogWrapper.info( ENDPOINT_MESSAGE );
			return new ResponseEntity<>( response.body(), HttpStatus.valueOf( response.code() ) );
		} else throw new IOException( ENDPOINT_MESSAGE + " Failed." );
	}
}
