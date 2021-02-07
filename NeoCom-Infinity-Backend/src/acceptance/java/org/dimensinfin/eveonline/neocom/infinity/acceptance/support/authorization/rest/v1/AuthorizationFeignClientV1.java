package org.dimensinfin.eveonline.neocom.infinity.acceptance.support.authorization.rest.v1;

import java.io.IOException;
import java.util.List;
import javax.validation.constraints.NotNull;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import org.dimensinfin.eveonline.neocom.infinity.acceptance.support.AcceptanceTargetConfig;
import org.dimensinfin.eveonline.neocom.infinity.acceptance.support.ITargetConfiguration;
import org.dimensinfin.eveonline.neocom.infinity.authorization.client.v1.StoreCredentialRequest;
import org.dimensinfin.eveonline.neocom.infinity.authorization.client.v1.StoreCredentialResponse;
import org.dimensinfin.eveonline.neocom.infinity.backend.authorization.domain.AuthenticationStateResponse;
import org.dimensinfin.eveonline.neocom.infinity.backend.authorization.domain.ValidateAuthorizationTokenRequest;
import org.dimensinfin.eveonline.neocom.infinity.backend.authorization.domain.ValidateAuthorizationTokenResponse;
import org.dimensinfin.eveonline.neocom.infinity.support.core.CommonFeignClient;
import org.dimensinfin.eveonline.neocom.infinity.support.rest.NeoComApiv1;
import org.dimensinfin.eveonline.neocom.service.logger.NeoComLogger;
import org.dimensinfin.logging.LogWrapper;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;

@Component
public class AuthorizationFeignClientV1 extends CommonFeignClient {
	// - C O N S T R U C T O R S
	public AuthorizationFeignClientV1( final @NotNull ITargetConfiguration acceptanceTargetConfig ) {
		super( acceptanceTargetConfig );
	}

	public ResponseEntity<StoreCredentialResponse> storeCredential( final StoreCredentialRequest storeCredentialRequest ) throws IOException {
		final NeoComApiv1 serviceStoreCredential = new Retrofit.Builder()
				.baseUrl( new AcceptanceTargetConfig().getBackendServer() )
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

	public ResponseEntity<AuthenticationStateResponse> validateAuthenticationState( final List<String> cookies ) throws IOException {
		final String ENDPOINT_MESSAGE = "Request the Authentication current state.";
		String cookieContent = "";
		for (final String cookie : cookies)
			cookieContent += cookie + "; ";
		if (cookieContent.length() > 2) cookieContent = cookieContent.substring( 0, cookieContent.length() - 2 );
		final Response<AuthenticationStateResponse> response = new Retrofit.Builder()
				.baseUrl( this.acceptanceTargetConfig.getBackendServer() )
				.addConverterFactory( GSON_CONVERTER_FACTORY )
				.build()
				.create( NeoComApiv1.class )
				.validateAuthenticationState( cookieContent )
				.execute();
		if (response.isSuccessful()) {
			LogWrapper.info( ENDPOINT_MESSAGE );
			return new ResponseEntity<>( response.body(), HttpStatus.valueOf( response.code() ) );
		} else throw new IOException( ENDPOINT_MESSAGE + " Failed." );
	}

	public ResponseEntity<ValidateAuthorizationTokenResponse> validateAuthorizationToken(
			final ValidateAuthorizationTokenRequest validateAuthorizationTokenRequest
	) throws IOException {
		final String ENDPOINT_MESSAGE = "Request the validation of the ESI authentication token.";
		final Response<ValidateAuthorizationTokenResponse> response = new Retrofit.Builder()
				.baseUrl( new AcceptanceTargetConfig().getBackendServer() )
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
