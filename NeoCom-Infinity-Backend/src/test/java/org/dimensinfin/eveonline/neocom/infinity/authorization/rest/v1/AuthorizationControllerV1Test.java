package org.dimensinfin.eveonline.neocom.infinity.authorization.rest.v1;

import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.dimensinfin.eveonline.neocom.infinity.authorization.client.v1.ValidateAuthorizationTokenRequest;
import org.dimensinfin.eveonline.neocom.infinity.authorization.client.v1.ValidateAuthorizationTokenResponse;
import org.dimensinfin.eveonline.neocom.infinity.core.exception.NeoComSBException;

import static org.dimensinfin.eveonline.neocom.infinity.core.exception.ErrorInfo.AUTHORIZATION_TRANSLATION;

public class AuthorizationControllerV1Test {
	private AuthorizationService authorizationService;

	@BeforeEach
	public void beforeEach() {
		this.authorizationService = Mockito.mock( AuthorizationService.class );
	}

	@Test
	public void validateAllParametersSuccess() {
		// Given
		final AuthorizationControllerV1 authorizationController = new AuthorizationControllerV1( this.authorizationService );
		final String code = "-CODE-";
		final String state = "LU5FT0NPTS5JTkZJTklUWS1QUk9EVUNUSU9OLVZBTElEIFNUQVRFIFNUUklORy0=";
		final Optional<String> dataSource = Optional.of( "-DATASOURCE-" );
		ValidateAuthorizationTokenRequest request = Mockito.mock( ValidateAuthorizationTokenRequest.class );
		ValidateAuthorizationTokenResponse response = Mockito.mock( ValidateAuthorizationTokenResponse.class );
		final ResponseEntity<ValidateAuthorizationTokenResponse> responseEntity = new ResponseEntity( response, HttpStatus.OK );
		//When
		Mockito.when( this.authorizationService
				.validateAuthorizationToken( Mockito.any( ValidateAuthorizationTokenRequest.class ) ) )
				.thenReturn( response );
		// Test
		ResponseEntity<ValidateAuthorizationTokenResponse> obtainedEntity =
				authorizationController.validate( code, state, dataSource );
		// Asserts
		Assertions.assertEquals( HttpStatus.OK, obtainedEntity.getStatusCode() );
		Assertions.assertNotNull( obtainedEntity.getBody() );
	}

	@Test
	public void validateFailure() {
		// Given
		final AuthorizationControllerV1 authorizationController = new AuthorizationControllerV1( this.authorizationService );
		final String code = "-CODE-";
		final String state = "-STATE-";
		final Optional<String> dataSource = Optional.empty();
		ValidateAuthorizationTokenRequest request = Mockito.mock( ValidateAuthorizationTokenRequest.class );
		ValidateAuthorizationTokenResponse response = Mockito.mock( ValidateAuthorizationTokenResponse.class );
		final ResponseEntity<ValidateAuthorizationTokenResponse> responseEntity = new ResponseEntity( response, HttpStatus.OK );
		//When
		Mockito.when( this.authorizationService
				.validateAuthorizationToken( Mockito.any( ValidateAuthorizationTokenRequest.class ) ) )
				.thenThrow( new NeoComSBException( AUTHORIZATION_TRANSLATION ) );
		Assertions.assertThrows( NeoComSBException.class, () -> {
					ResponseEntity<ValidateAuthorizationTokenResponse> obtainedEntity =
							authorizationController.validate( code, state, dataSource );
				},
				"Expected authorizationController.validate() to throw null verification, but it didn't." );
	}

	@Test
	public void validateNotOptionalValid() {
		// Given
		final AuthorizationControllerV1 authorizationController = new AuthorizationControllerV1( this.authorizationService );
		final String code = "-CODE-";
		final String state = "-STATE-";
		final Optional<String> dataSource = Optional.empty();
		ValidateAuthorizationTokenRequest request = Mockito.mock( ValidateAuthorizationTokenRequest.class );
		ValidateAuthorizationTokenResponse response = Mockito.mock( ValidateAuthorizationTokenResponse.class );
		final ResponseEntity<ValidateAuthorizationTokenResponse> responseEntity = new ResponseEntity( response, HttpStatus.OK );
		//When
		Mockito.when( this.authorizationService
				.validateAuthorizationToken( Mockito.any( ValidateAuthorizationTokenRequest.class ) ) )
				.thenReturn( response );
		// Test
		ResponseEntity<ValidateAuthorizationTokenResponse> obtainedEntity =
				authorizationController.validate( code, state, dataSource );
		// Asserts
		Assertions.assertEquals( HttpStatus.OK, obtainedEntity.getStatusCode() );
		Assertions.assertNotNull( obtainedEntity.getBody() );
	}
}
