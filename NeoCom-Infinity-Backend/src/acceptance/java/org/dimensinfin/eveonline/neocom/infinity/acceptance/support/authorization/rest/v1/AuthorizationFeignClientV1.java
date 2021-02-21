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
import org.dimensinfin.eveonline.neocom.infinity.backend.authorization.domain.AuthorizationTokenRequest;
import org.dimensinfin.eveonline.neocom.infinity.backend.authorization.domain.AuthorizationTokenResponse;
import org.dimensinfin.eveonline.neocom.infinity.support.core.CommonFeignClient;
import org.dimensinfin.eveonline.neocom.infinity.support.rest.NeoComApiv1;
import org.dimensinfin.logging.LogWrapper;

import retrofit2.Response;
import retrofit2.Retrofit;

@Component
public class AuthorizationFeignClientV1 extends CommonFeignClient {
	// - C O N S T R U C T O R S
	public AuthorizationFeignClientV1( final @NotNull ITargetConfiguration acceptanceTargetConfig ) {
		super( acceptanceTargetConfig );
	}

	public ResponseEntity<StoreCredentialResponse> storeCredential( final StoreCredentialRequest storeCredentialRequest ) throws IOException {
		final String ENDPOINT_MESSAGE = "Store a Credential on the backend repository.";
		final Response<StoreCredentialResponse> response = new Retrofit.Builder()
				.baseUrl( this.acceptanceTargetConfig.getBackendServer() )
				.addConverterFactory( GSON_CONVERTER_FACTORY )
				.build()
				.create( NeoComApiv1.class )
				.storeCredential( storeCredentialRequest.getCredential().getAccountId(), storeCredentialRequest.getCredential() )
				.execute();
		if (response.isSuccessful()) {
			LogWrapper.info( ENDPOINT_MESSAGE );
			return new ResponseEntity<>( response.body(), HttpStatus.valueOf( response.code() ) );
		} else throw new IOException( ENDPOINT_MESSAGE + " Failed." );
	}

	public ResponseEntity<AuthenticationStateResponse> validateAuthenticationState( final List<String> cookies ) throws IOException {
		final String ENDPOINT_MESSAGE = "Request the Authentication current state.";
		final Response<AuthenticationStateResponse> response = new Retrofit.Builder()
				.baseUrl( this.acceptanceTargetConfig.getBackendServer() )
				.addConverterFactory( GSON_CONVERTER_FACTORY )
				.build()
				.create( NeoComApiv1.class )
				.validateAuthenticationState( this.prepareCookies( cookies ) )
				.execute();
		if (response.isSuccessful()) {
			LogWrapper.info( ENDPOINT_MESSAGE );
			return new ResponseEntity<>( response.body(), HttpStatus.valueOf( response.code() ) );
		} else throw new IOException( ENDPOINT_MESSAGE + " Failed." );
	}

	public ResponseEntity<AuthorizationTokenResponse> validateAuthorizationToken(
			final AuthorizationTokenRequest authorizationTokenRequest
	) throws IOException {
		final String ENDPOINT_MESSAGE = "Request the validation of the ESI authentication token.";
		final Response<AuthorizationTokenResponse> response = new Retrofit.Builder()
				.baseUrl( new AcceptanceTargetConfig().getBackendServer() )
				.addConverterFactory( GSON_CONVERTER_FACTORY )
				.build()
				.create( NeoComApiv1.class )
				.validateAuthorizationToken( "application/json",
						authorizationTokenRequest.getCode(),
						authorizationTokenRequest.getState(),
						authorizationTokenRequest.getDataSourceName()
				)
				.execute();
		if (response.isSuccessful()) {
			LogWrapper.info( ENDPOINT_MESSAGE );
			return new ResponseEntity<>( response.body(), HttpStatus.valueOf( response.code() ) );
		} else throw new IOException( ENDPOINT_MESSAGE + " Failed." );
	}

}
