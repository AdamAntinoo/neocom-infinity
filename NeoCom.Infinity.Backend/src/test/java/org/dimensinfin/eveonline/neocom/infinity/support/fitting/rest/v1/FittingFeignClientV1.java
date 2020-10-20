package org.dimensinfin.eveonline.neocom.infinity.support.fitting.rest.v1;

import java.io.IOException;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.joda.JodaModule;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import org.dimensinfin.eveonline.neocom.infinity.support.rest.NeoComApiv1;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

@Component
public class FittingFeignClientV1 {
	public ResponseEntity<List<FittingResponse>> getPilotFittings( final Integer pilotIdentifier,
	                                                               final String authorizationToken ) throws IOException {
		ObjectMapper mapper = new ObjectMapper();
		mapper.registerModule( new JodaModule() );
		final NeoComApiv1 serviceNeoComApiEndpoints = new Retrofit.Builder()
				.baseUrl( NeoComApiv1.NEOCOM_BACKEND_APP_HOST )
				.addConverterFactory( JacksonConverterFactory.create( mapper ) )
				.build()
				.create( NeoComApiv1.class );
		final Call<List<FittingResponse>> request = serviceNeoComApiEndpoints.getPilotFittings(
				"application/json",
				authorizationToken,
				pilotIdentifier
		);
		final Response<List<FittingResponse>> response = request.execute();
		if (response.isSuccessful()) {
			final List<FittingResponse> body = response.body();
			return new ResponseEntity<List<FittingResponse>>( body, HttpStatus.OK );
		} else return new ResponseEntity( HttpStatus.valueOf( response.code() ) );
	}
}
