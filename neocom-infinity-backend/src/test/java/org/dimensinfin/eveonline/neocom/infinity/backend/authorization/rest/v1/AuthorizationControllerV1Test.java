package org.dimensinfin.eveonline.neocom.infinity.backend.authorization.rest.v1;

import javax.servlet.http.HttpServletResponse;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.dimensinfin.eveonline.neocom.infinity.backend.authorization.domain.AuthenticationStateResponse;
import org.dimensinfin.eveonline.neocom.infinity.backend.authorization.domain.AuthorizationTokenRequest;
import org.dimensinfin.eveonline.neocom.infinity.backend.authorization.domain.AuthorizationTokenResponse;

public class AuthorizationControllerV1Test {
	private AuthorizationServiceV1 authorizationServiceV1;

	@BeforeEach
	public void beforeEach() {
		this.authorizationServiceV1 = Mockito.mock( AuthorizationServiceV1.class );
	}

	@Test
	public void validateAllParametersSuccess() {
		// Given
		final String code = "-CODE-";
		final String state = "LU5FT0NPTS5JTkZJTklUWS1QUk9EVUNUSU9OLVZBTElEIFNUQVRFIFNUUklORy0=";
		final String dataSource = "-DATASOURCE-";
		final AuthorizationTokenRequest request = Mockito.mock( AuthorizationTokenRequest.class );
		final AuthorizationTokenResponse response = Mockito.mock( AuthorizationTokenResponse.class );
		final ResponseEntity<AuthorizationTokenResponse> responseEntity = new ResponseEntity( response, HttpStatus.OK );
		final HttpServletResponse servletResponse = Mockito.mock( HttpServletResponse.class );
		//When
		Mockito.when( this.authorizationServiceV1
				.validateAuthorizationToken( Mockito.any( AuthorizationTokenRequest.class ) ) )
				.thenReturn( response );
		// Test
		final AuthorizationControllerV1 authorizationController = new AuthorizationControllerV1( this.authorizationServiceV1 );
		final ResponseEntity<AuthorizationTokenResponse> obtainedEntity =
				authorizationController.validate( code, state, dataSource, servletResponse );
		// Asserts
		Assertions.assertEquals( HttpStatus.OK, obtainedEntity.getStatusCode() );
		Assertions.assertNotNull( obtainedEntity.getBody() );
	}

	@Test
	public void validateAuthenticationStateInvalidToken() {
		// Given
		final HttpServletResponse servletResponse = Mockito.mock( HttpServletResponse.class );
		final AuthenticationStateResponse stateResponse = Mockito.mock( AuthenticationStateResponse.class );
		//When
		Mockito.when( stateResponse.getState() ).thenReturn( AuthenticationStateResponse.AuthenticationStateType.NOT_VALID );
		Mockito.when( this.authorizationServiceV1
				.validateAuthenticationState( Mockito.anyString(), Mockito.any( HttpServletResponse.class ) ) )
				.thenReturn( stateResponse );
		// Test
		final AuthorizationControllerV1 authorizationController = new AuthorizationControllerV1( this.authorizationServiceV1 );
		final ResponseEntity<AuthenticationStateResponse> obtainedEntity = authorizationController
				.validateAuthenticationState( "-JWT CONTENT TYPE S NOT VALID-", servletResponse );
		// Asserts
		Assertions.assertNotNull( obtainedEntity );
		Assertions.assertNotNull( obtainedEntity.getBody() );
		Assertions.assertEquals( HttpStatus.OK, obtainedEntity.getStatusCode() );
		Assertions.assertEquals( AuthenticationStateResponse.AuthenticationStateType.NOT_VALID, obtainedEntity.getBody().getState() );
	}

	@Test
	public void validateAuthenticationStateNoCookie() {
		// Given
		final HttpServletResponse servletResponse = Mockito.mock( HttpServletResponse.class );
		// Test
		final AuthorizationControllerV1 authorizationController = new AuthorizationControllerV1( this.authorizationServiceV1 );
		final ResponseEntity<AuthenticationStateResponse> obtainedEntity = authorizationController
				.validateAuthenticationState( "-INVALID-", servletResponse );
		// Asserts
		Assertions.assertNotNull( obtainedEntity );
		Assertions.assertNotNull( obtainedEntity.getBody() );
		Assertions.assertEquals( HttpStatus.OK, obtainedEntity.getStatusCode() );
		Assertions.assertEquals( AuthenticationStateResponse.AuthenticationStateType.NOT_FOUND, obtainedEntity.getBody().getState() );
	}

	@Test
	public void validateAuthenticationStateValid() {
		// Given
		final HttpServletResponse servletResponse = Mockito.mock( HttpServletResponse.class );
		final AuthenticationStateResponse stateResponse = Mockito.mock( AuthenticationStateResponse.class );
		//When
		Mockito.when( stateResponse.getState() ).thenReturn( AuthenticationStateResponse.AuthenticationStateType.VALID );
		Mockito.when( this.authorizationServiceV1
				.validateAuthenticationState( Mockito.anyString(), Mockito.any( HttpServletResponse.class ) ) )
				.thenReturn( stateResponse );
		// Test
		final AuthorizationControllerV1 authorizationController = new AuthorizationControllerV1( this.authorizationServiceV1 );
		final ResponseEntity<AuthenticationStateResponse> obtainedEntity = authorizationController
				.validateAuthenticationState(
						"eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJFU0kgT0F1dGgyIEF1dGhlbnRpY2F0aW9uIiwiY29ycG9yYXRpb25JZCI6MTIzNDU2LCJhY2NvdW50TmFtZSI6IlRlc3RpbmcgQ2hhcmFjdGVyIEFjY291bnQiLCJpc3MiOiJOZW9Db20uSW5maW5pdHkuQmFja2VuZCIsInVuaXF1ZUlkIjoidHJhbnF1aWxpdHkuOTM4MTMzMTAiLCJwaWxvdElkIjo5MzgxMzMxMH0.P-o3mnrT-LzGzvKLU2KhRIpZEIECsyrtXEpmkXvNjZHLNd4pTyRNZ1lTd1h98CfRUDY6gp8jfwMuomGvTUHYKw",
						servletResponse );
		// Asserts
		Assertions.assertNotNull( obtainedEntity );
		Assertions.assertNotNull( obtainedEntity.getBody() );
		Assertions.assertEquals( HttpStatus.OK, obtainedEntity.getStatusCode() );
		Assertions.assertEquals( AuthenticationStateResponse.AuthenticationStateType.VALID, obtainedEntity.getBody().getState() );
	}

	@Test
	public void validateNotOptionalValid() {
		// Given
		final String code = "-CODE-";
		final String state = "-STATE-";
		final String dataSource = null;
		final AuthorizationTokenRequest request = Mockito.mock( AuthorizationTokenRequest.class );
		final AuthorizationTokenResponse response = Mockito.mock( AuthorizationTokenResponse.class );
		final ResponseEntity<AuthorizationTokenResponse> responseEntity = new ResponseEntity( response, HttpStatus.OK );
		final HttpServletResponse servletResponse = Mockito.mock( HttpServletResponse.class );
		//When
		Mockito.when( this.authorizationServiceV1
				.validateAuthorizationToken( Mockito.any( AuthorizationTokenRequest.class ) ) )
				.thenReturn( response );
		// Test
		final AuthorizationControllerV1 authorizationController = new AuthorizationControllerV1( this.authorizationServiceV1 );
		final ResponseEntity<AuthorizationTokenResponse> obtainedEntity =
				authorizationController.validate( code, state, dataSource, servletResponse );
		// Asserts
		Assertions.assertEquals( HttpStatus.OK, obtainedEntity.getStatusCode() );
		Assertions.assertNotNull( obtainedEntity.getBody() );
	}
}
