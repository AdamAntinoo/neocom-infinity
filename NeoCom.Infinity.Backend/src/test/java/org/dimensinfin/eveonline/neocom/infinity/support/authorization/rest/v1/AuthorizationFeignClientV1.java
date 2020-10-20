package org.dimensinfin.eveonline.neocom.infinity.support.authorization.rest.v1;

import java.io.IOException;

import com.google.gson.GsonBuilder;
import org.joda.time.DateTime;
import org.joda.time.LocalDate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import org.dimensinfin.eveonline.neocom.core.support.GSONDateTimeDeserializer;
import org.dimensinfin.eveonline.neocom.core.support.GSONLocalDateDeserializer;
import org.dimensinfin.eveonline.neocom.infinity.authorization.client.v1.StoreCredentialRequest;
import org.dimensinfin.eveonline.neocom.infinity.authorization.client.v1.StoreCredentialResponse;
import org.dimensinfin.eveonline.neocom.infinity.authorization.client.v1.ValidateAuthorizationTokenRequest;
import org.dimensinfin.eveonline.neocom.infinity.authorization.client.v1.ValidateAuthorizationTokenResponse;
import org.dimensinfin.eveonline.neocom.infinity.support.core.CommonFeignClient;
import org.dimensinfin.eveonline.neocom.infinity.support.rest.NeoComApiv1;
import org.dimensinfin.eveonline.neocom.service.logger.NeoComLogger;

import retrofit2.Call;
import retrofit2.Converter;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.jackson.JacksonConverterFactory;

@Component
public class AuthorizationFeignClientV1 extends CommonFeignClient {
	public ResponseEntity<StoreCredentialResponse> storeCredential( final StoreCredentialRequest storeCredentialRequest ) throws IOException {
		final NeoComApiv1 serviceStoreCredential = new Retrofit.Builder()
				.baseUrl( NeoComApiv1.NEOCOM_BACKEND_APP_HOST )
				.addConverterFactory( GSON_CONVERTER_FACTORY )
				.build()
				.create( NeoComApiv1.class );
		final Call<StoreCredentialResponse> request = serviceStoreCredential.storeCredential(
				NeoComApiv1.NEOCOM_BACKEND_ACCEPTED_CONTENT_TYPE,
				storeCredentialRequest.getCredential().getAccountId(),
				storeCredentialRequest.getCredential()
		);
		// Getting the request response to be stored if valid.
		final Response<StoreCredentialResponse> response = request.execute();
		if (response.isSuccessful()) {
			NeoComLogger.info( "Response is 200 OK." );
			final StoreCredentialResponse storeCredentialResponse = response.body();
			return new ResponseEntity<>( storeCredentialResponse, HttpStatus.OK );
		} else return new ResponseEntity( HttpStatus.BAD_REQUEST );
	}

	public ResponseEntity<ValidateAuthorizationTokenResponse> validateAuthorizationToken(
			final ValidateAuthorizationTokenRequest validateAuthorizationTokenRequest
	) throws IOException {
		final NeoComApiv1 serviceGetAccessToken = new Retrofit.Builder()
				.baseUrl( NeoComApiv1.NEOCOM_BACKEND_APP_HOST )
				.addConverterFactory( GSON_CONVERTER_FACTORY )
				.build()
				.create( NeoComApiv1.class );
		final Call<ValidateAuthorizationTokenResponse> request = serviceGetAccessToken.validateAuthorizationToken(
				"application/json",
				validateAuthorizationTokenRequest.getCode(),
				validateAuthorizationTokenRequest.getState(),
				validateAuthorizationTokenRequest.getDataSourceName()
		);
		// Getting the request response to be stored if valid.
		final Response<ValidateAuthorizationTokenResponse> response = request.execute();
		if (response.isSuccessful()) {
			NeoComLogger.info( "Response is 200 OK." );
			final ValidateAuthorizationTokenResponse authorizationResponse = response.body();
			return new ResponseEntity<>( authorizationResponse, HttpStatus.OK );
		} else return new ResponseEntity( HttpStatus.BAD_REQUEST );
	}
}
