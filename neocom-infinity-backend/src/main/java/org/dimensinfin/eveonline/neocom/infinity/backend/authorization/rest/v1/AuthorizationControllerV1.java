package org.dimensinfin.eveonline.neocom.infinity.backend.authorization.rest.v1;

import java.text.MessageFormat;
import java.util.concurrent.TimeUnit;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import org.dimensinfin.eveonline.neocom.infinity.backend.authorization.domain.AuthenticationStateResponse;
import org.dimensinfin.eveonline.neocom.infinity.backend.authorization.domain.AuthorizationTokenRequest;
import org.dimensinfin.eveonline.neocom.infinity.backend.authorization.domain.AuthorizationTokenResponse;
import org.dimensinfin.logging.LogWrapper;

import static org.dimensinfin.eveonline.neocom.infinity.infrastructure.config.GlobalAppConstants.TransportConstants.ESI_COOKIE_NAME;
import static org.dimensinfin.eveonline.neocom.infinity.infrastructure.config.GlobalAppConstants.TransportConstants.NEOCOM_COOKIE_NAME;
import static org.dimensinfin.eveonline.neocom.provider.ESIDataProvider.DEFAULT_ESI_SERVER;

/**
 * @author Adam Antinoo (adamantinoo.git@gmail.com)
 * @since 0.20.0
 */
@RestController
@Validated
@CrossOrigin()
@RequestMapping("/api/v1/neocom")
public class AuthorizationControllerV1 {
	private final AuthorizationServiceV1 authorizationServiceV1;

	// - C O N S T R U C T O R S
	@Autowired
	public AuthorizationControllerV1( @NotNull final AuthorizationServiceV1 authorizationServiceV1 ) {
		this.authorizationServiceV1 = authorizationServiceV1;
	}

	@GetMapping(path = { "/validateAuthorizationToken" },
			consumes = "application/json",
			produces = "application/json")
	public ResponseEntity<AuthorizationTokenResponse> validate( @RequestParam(value = "code") @NotNull final String code,
	                                                            @RequestParam(value = "state") @NotNull final String state,
	                                                            @RequestParam(value = "dataSource", required = false) final String dataSource,
	                                                            final HttpServletResponse response ) {
		final AuthorizationTokenRequest authorizationTokenRequest = new AuthorizationTokenRequest.Builder()
				.withCode( code )
				.withState( state )
				.withDataSource( (null != dataSource) ? dataSource : DEFAULT_ESI_SERVER )
				.build();
		final AuthorizationTokenResponse authorizationResponse = this.authorizationServiceV1
				.validateAuthorizationToken( authorizationTokenRequest );
		response.addCookie( this.generateCookie( NEOCOM_COOKIE_NAME, authorizationResponse.getJwtToken() ) );
		response.addCookie( this.generateCookie( ESI_COOKIE_NAME, authorizationResponse.getEsiToken() ) );
		return new ResponseEntity<>( authorizationResponse, HttpStatus.OK );
	}

	/**
	 * This endpoints is expected to receive a HttpOnly cookie from the frontend with the JWT token. There are some scenarios to handle the different
	 * responses.
	 *
	 * <ul>
	 *     <li><b>cookie not exists</b>- if this is a new login of from a new terminal the cookie is not set. Then the endpoint returns the 'not
	 *     found' message to start a new EVE SSO Login</li>
	 *     <li><b>cookie exists credential not found</b>-this is the same scenario. The 'not found' tells that there is no credential and should be
	 *     created with a new EVE SSO Login</li>
	 *     <li><b>cookie found and valid credential and token</b>-report to the caller that the token is still valid with the message 'valid'. In
	 *     this scenario we should update the cookie with a new expiration time. This cookie is only used on authentication validation because the
	 *     authentication data is reported on the <i>Authorization</i> header with the JWT.
	 *     </li>
	 * </ul>
	 *
	 * @return the response message depending on the scenario found.
	 */
	@GetMapping(path = { "/validateAuthenticationState" }, produces = "application/json")
	public ResponseEntity<AuthenticationStateResponse> validateAuthenticationState(
			@CookieValue(value = NEOCOM_COOKIE_NAME, defaultValue = "-INVALID-") final String neocomCookieData,
			final HttpServletResponse response ) {
		LogWrapper.info( MessageFormat.format( "Cookie value: {0}", neocomCookieData ) );
		// Validate if the cookie is empty. Is so do not go ahead and return a 'not found' immediately.
		if (neocomCookieData.toUpperCase().contains( "INVALID" ))
			return new ResponseEntity<>( new AuthenticationStateResponse.Builder()
					.withState( AuthenticationStateResponse.AuthenticationStateType.NOT_FOUND )
					.build(), HttpStatus.OK );
		else {
			final AuthenticationStateResponse validateResponse = this.authorizationServiceV1.validateAuthenticationState( neocomCookieData,
					response );
			response.addCookie( this.generateCookie( NEOCOM_COOKIE_NAME, validateResponse.getJwtToken() ) );
			response.addCookie( this.generateCookie( ESI_COOKIE_NAME, validateResponse.getEsiToken() ) );
			return new ResponseEntity<>( validateResponse, HttpStatus.OK );
		}
	}

	private Cookie generateCookie( final String name, final String payload ) {
		final Cookie cookie = new Cookie( name, payload );
		cookie.setSecure( false ); // Use HTTPS
		cookie.setHttpOnly( true ); // Only for server access
		cookie.setMaxAge( (int) TimeUnit.DAYS.toSeconds( 7 ) ); // expires in 7 days
		cookie.setPath( "/" );
		return cookie;
	}
}
