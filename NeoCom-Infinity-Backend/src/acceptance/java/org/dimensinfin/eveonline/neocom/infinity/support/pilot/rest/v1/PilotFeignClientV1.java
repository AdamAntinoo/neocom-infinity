package org.dimensinfin.eveonline.neocom.infinity.support.pilot.rest.v1;

import java.io.IOException;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.joda.JodaModule;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import org.dimensinfin.eveonline.neocom.infinity.acceptance.support.AcceptanceTargetConfig;
import org.dimensinfin.eveonline.neocom.infinity.support.rest.NeoComApiv1;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

@Component
public class PilotFeignClientV1 {
	public ResponseEntity<PilotResponse> getPilotData( final Integer pilotIdentifier,
	                                                   final String authorizationToken ) throws IOException {
		final ObjectMapper mapper = new ObjectMapper();
		mapper.registerModule( new JodaModule() );
		final NeoComApiv1 serviceNeoComApiEndpoints = new Retrofit.Builder()
				.baseUrl( new AcceptanceTargetConfig().getBackendServer() )
				.addConverterFactory( JacksonConverterFactory.create( mapper ) )
				.build()
				.create( NeoComApiv1.class );
		final Call<PilotResponse> request = serviceNeoComApiEndpoints.getPilotData(
				"application/json",
				authorizationToken,
				pilotIdentifier
		);
		final Response<PilotResponse> response = request.execute();
		if (response.isSuccessful()) {
			final PilotResponse body = response.body();
			return new ResponseEntity<>( body, HttpStatus.OK );
		} else return new ResponseEntity( HttpStatus.valueOf( response.code() ) );
	}
}
