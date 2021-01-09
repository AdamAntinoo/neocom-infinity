package org.dimensinfin.eveonline.neocom.infinity.support.authorization.rest.v1;

import java.io.IOException;

import javax.validation.constraints.NotNull;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import org.dimensinfin.eveonline.neocom.infinity.authorization.client.v1.StoreCredentialRequest;
import org.dimensinfin.eveonline.neocom.infinity.authorization.client.v1.StoreCredentialResponse;
import org.dimensinfin.eveonline.neocom.infinity.authorization.client.v1.ValidateAuthorizationTokenRequest;
import org.dimensinfin.eveonline.neocom.infinity.authorization.client.v1.ValidateAuthorizationTokenResponse;
import org.dimensinfin.eveonline.neocom.infinity.support.core.CommonFeignClient;
import org.dimensinfin.eveonline.neocom.infinity.support.rest.NeoComApiv1;
import org.dimensinfin.eveonline.neocom.service.logger.NeoComLogger;
import org.dimensinfin.logging.LogWrapper;

import org.dimensinfin.eveonline.neocom.infinity.acceptance.support.ITargetConfiguration;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;

@Component
public class AuthorizationFeignClientV1 extends CommonFeignClient {
	public AuthorizationFeignClientV1( final @NotNull ITargetConfiguration acceptanceTargetConfig ) {
		super( acceptanceTargetConfig );
	}

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
		final String ENDPOINT_MESSAGE = "Request the validation of the ESI authentication token.";
		final Response<ValidateAuthorizationTokenResponse> response = new Retrofit.Builder()
				.baseUrl( NeoComApiv1.NEOCOM_BACKEND_APP_HOST )
				.addConverterFactory( GSON_CONVERTER_FACTORY )
				.build()
				.create( NeoComApiv1.class )
				.validateAuthorizationToken( "application/json",
						validateAuthorizationTokenRequest.getCode(),
						validateAuthorizationTokenRequest.getState(),
						validateAuthorizationTokenRequest.getDataSourceName()
				)
				.execute();
		if (response.isSuccessful()) {
			LogWrapper.info( ENDPOINT_MESSAGE );
			return new ResponseEntity<>( response.body(), HttpStatus.valueOf( response.code() ) );
		} else throw new IOException( ENDPOINT_MESSAGE + " Failed." );
	}
}
