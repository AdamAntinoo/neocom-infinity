package org.dimensinfin.eveonline.neocom.infinity.acceptance.support.character.rest.v1;

import java.io.IOException;
import java.text.MessageFormat;
import java.util.List;
import javax.validation.constraints.NotNull;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import org.dimensinfin.eveonline.neocom.infinity.acceptance.support.AcceptanceTargetConfig;
import org.dimensinfin.eveonline.neocom.infinity.acceptance.support.dto.PilotDto;
import org.dimensinfin.eveonline.neocom.infinity.support.core.CommonFeignClient;
import org.dimensinfin.eveonline.neocom.infinity.support.rest.NeoComApiv1;
import org.dimensinfin.logging.LogWrapper;

import retrofit2.Response;
import retrofit2.Retrofit;

@Component
public class CharacterFeignClientV1 extends CommonFeignClient {
	// - C O N S T R U C T O R S
	public CharacterFeignClientV1( final @NotNull AcceptanceTargetConfig acceptanceTargetConfig ) {
		super( acceptanceTargetConfig );
	}

	public ResponseEntity<PilotDto> getPilotData( final List<String> cookies,
	                                              final String authorization,
	                                              final Integer pilotId
	) throws IOException {
		final String ENDPOINT_MESSAGE = "Request the Pilot Public Data.";
		final Response<PilotDto> response = new Retrofit.Builder()
				.baseUrl( this.acceptanceTargetConfig.getBackendServer() )
				.addConverterFactory( GSON_CONVERTER_FACTORY )
				.build()
				.create( NeoComApiv1.class )
				.getPilotData(
						this.prepareCookies( cookies ),
						authorization,
						pilotId
				)
				.execute();
		if (response.isSuccessful()) {
			LogWrapper.info( ENDPOINT_MESSAGE );
			return new ResponseEntity<>( response.body(), HttpStatus.valueOf( response.code() ) );
		} else {
			LogWrapper.info( MessageFormat.format( "cause: {0}", response.errorBody().string() ) );
			throw new IOException( ENDPOINT_MESSAGE + " Failed." );
		}
	}
}
