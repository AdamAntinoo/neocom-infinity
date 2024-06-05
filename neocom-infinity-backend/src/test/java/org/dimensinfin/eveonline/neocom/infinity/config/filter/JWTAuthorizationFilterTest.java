package org.dimensinfin.eveonline.neocom.infinity.config.filter;

import java.io.IOException;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.security.authentication.AuthenticationManager;

import static org.dimensinfin.eveonline.neocom.infinity.config.security.SecurityConstants.AUTHORIZATION_HEADER_NAME;
import static org.dimensinfin.eveonline.neocom.infinity.config.security.SecurityConstants.SIGNATURE_HEADER_NAME;
import static org.dimensinfin.eveonline.neocom.infinity.config.security.SecurityConstants.TOKEN_PREFIX;
import static org.dimensinfin.eveonline.neocom.infinity.infrastructure.config.GlobalAppConstants.NetworkConstants.NEOCOM_COOKIE_NAME;

public class JWTAuthorizationFilterTest {

	private AuthenticationManager authManager;
	private HttpServletRequest request;
	private HttpServletResponse response;
	private FilterChain chain;

	@BeforeEach
	public void beforeEach() {
		this.authManager = Mockito.mock( AuthenticationManager.class );
		this.request = Mockito.mock( HttpServletRequest.class );
		this.response = Mockito.mock( HttpServletResponse.class );
		this.chain = Mockito.mock( FilterChain.class );
	}

	@Test
	public void constructorContract() {
		final JWTAuthorizationFilter filter = new JWTAuthorizationFilter( this.authManager );
		Assertions.assertNotNull( filter );
	}

	@Test
	public void doFilterInternal() throws IOException, ServletException {
		// Given
		final String JWT_TOKEN = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9" +
				".eyJzdWIiOiJFU0kgT0F1dGgyIEF1dGhlbnRpY2F0aW9uIiwiY29ycG9yYXRpb25JZCI6OTg2NjEwOTIsImFjY291bnROYW1lIjoiVGVzdGluZyBDaGFyYWN0ZXIgQWNjb3VudCIsImlzcyI6Ik5lb0NvbS5JbmZpbml0eS5CYWNrZW5kIiwidW5pcXVlSWQiOiJ0cmFucXVpbGl0eS45MzgxMzMxMCIsInBpbG90SWQiOjkzODEzMzEwfQ.0eq0Mk6cfK4AR-CALcq6EDdb-Cgr5VyxB0ruX0f9CeRU9oDv637wofTV_7PcxMF9Z2w9SU-ImhxAYO3QXOFCjw";
		final Cookie neoComCookie = Mockito.mock( Cookie.class );
		final Cookie[] cookies = new Cookie[1];
		cookies[0] = neoComCookie;
		// When
		Mockito.when( this.request.getCookies() ).thenReturn( cookies );
		Mockito.when( neoComCookie.getName() ).thenReturn( NEOCOM_COOKIE_NAME );
		Mockito.when( neoComCookie.getValue() ).thenReturn( JWT_TOKEN );
		Mockito.when( this.request.getHeader( SIGNATURE_HEADER_NAME ) ).thenReturn( "S0000.0020.0001" );
		Mockito.when( this.request.getHeader( AUTHORIZATION_HEADER_NAME ) ).thenReturn( TOKEN_PREFIX + JWT_TOKEN );
		// Test
		final JWTAuthorizationFilter filter = new JWTAuthorizationFilter( this.authManager );
		filter.doFilterInternal( this.request, this.response, this.chain );
		// Assertions
		Mockito.verify( this.chain, Mockito.times( 1 ) ).doFilter( this.request, this.response );
	}

	@Test
	public void doFilterInternalInvalidCookieNotFound() throws IOException, ServletException {
		// Given
		final Cookie neoComCookie = Mockito.mock( Cookie.class );
		final Cookie[] cookies = new Cookie[1];
		cookies[0] = neoComCookie;
		// When
		Mockito.when( this.request.getCookies() ).thenReturn( cookies );
		Mockito.when( neoComCookie.getName() ).thenReturn( "-OTHER-COOKIE-NAME-" );
		// Test
		final JWTAuthorizationFilter filter = new JWTAuthorizationFilter( this.authManager );
		filter.doFilterInternal( this.request, this.response, this.chain );
		// Assertions
		Mockito.verify( this.chain, Mockito.times( 1 ) ).doFilter( this.request, this.response );
	}

	@Test
	public void doFilterInternalInvalidCookieNull() throws IOException, ServletException {
		// When
		Mockito.when( this.request.getCookies() ).thenReturn( null );
		// Test
		final JWTAuthorizationFilter filter = new JWTAuthorizationFilter( this.authManager );
		filter.doFilterInternal( this.request, this.response, this.chain );
		// Assertions
		Mockito.verify( this.chain, Mockito.times( 1 ) ).doFilter( this.request, this.response );
	}

	@Test
	public void doFilterInternalSignatureNotFound() throws IOException, ServletException {
		// Given
		final String JWT_TOKEN = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9" +
				".eyJzdWIiOiJFU0kgT0F1dGgyIEF1dGhlbnRpY2F0aW9uIiwiY29ycG9yYXRpb25JZCI6OTg2NjEwOTIsImFjY291bnROYW1lIjoiVGVzdGluZyBDaGFyYWN0ZXIgQWNjb3VudCIsImlzcyI6Ik5lb0NvbS5JbmZpbml0eS5CYWNrZW5kIiwidW5pcXVlSWQiOiJ0cmFucXVpbGl0eS45MzgxMzMxMCIsInBpbG90SWQiOjkzODEzMzEwfQ.0eq0Mk6cfK4AR-CALcq6EDdb-Cgr5VyxB0ruX0f9CeRU9oDv637wofTV_7PcxMF9Z2w9SU-ImhxAYO3QXOFCjw";
		final Cookie neoComCookie = Mockito.mock( Cookie.class );
		final Cookie[] cookies = new Cookie[1];
		cookies[0] = neoComCookie;
		// When
		Mockito.when( this.request.getCookies() ).thenReturn( cookies );
		Mockito.when( neoComCookie.getName() ).thenReturn( NEOCOM_COOKIE_NAME );
		Mockito.when( neoComCookie.getValue() ).thenReturn( JWT_TOKEN );
		Mockito.when( this.request.getHeader( SIGNATURE_HEADER_NAME ) ).thenReturn( "S0000.0000.0001" );
		// Test
		final JWTAuthorizationFilter filter = new JWTAuthorizationFilter( this.authManager );
		filter.doFilterInternal( this.request, this.response, this.chain );
		// Assertions
		Mockito.verify( this.chain, Mockito.times( 1 ) ).doFilter( this.request, this.response );
	}
}
