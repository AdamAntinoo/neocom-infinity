package org.dimensinfin.eveonline.neocom.infinity.backend.authorization.rest.v1;

import java.util.Optional;
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

import org.dimensinfin.eveonline.neocom.infinity.authorization.client.v1.ValidateAuthorizationTokenRequest;
import org.dimensinfin.eveonline.neocom.infinity.authorization.client.v1.ValidateAuthorizationTokenResponse;
import org.dimensinfin.eveonline.neocom.infinity.backend.authorization.domain.CookieStateResponse;
import org.dimensinfin.eveonline.neocom.infinity.core.rest.NeoComController;

import static org.dimensinfin.eveonline.neocom.infinity.NeoComInfinityBackendApplication.NEOCOM_COOKIE_NAME;
import static org.dimensinfin.eveonline.neocom.provider.ESIDataProvider.DEFAULT_ESI_SERVER;

/**
 * @author Adam Antinoo (adamantinoo.git@gmail.com)
 * @since 0.20.0
 */
@RestController
@Validated
@CrossOrigin()
@RequestMapping("/api/v1/neocom")
public class AuthorizationControllerV1 extends NeoComController {
	private final AuthorizationServiceV1 authorizationServiceV1;

	// - C O N S T R U C T O R S
	@Autowired
	public AuthorizationControllerV1( @NotNull final AuthorizationServiceV1 authorizationServiceV1 ) {
		this.authorizationServiceV1 = authorizationServiceV1;
	}

	@GetMapping(path = { "/validateAuthorizationToken" },
			consumes = "application/json",
			produces = "application/json")
	public ResponseEntity<ValidateAuthorizationTokenResponse> validate( @RequestParam(value = "code") @NotNull final String code,
	                                                                    @RequestParam(value = "state") @NotNull final String state,
	                                                                    @RequestParam(value = "dataSource", required = false) final Optional<String> dataSource,
	                                                                    final HttpServletResponse response ) {
		final ValidateAuthorizationTokenRequest authorizationTokenRequest = new ValidateAuthorizationTokenRequest.Builder()
				.withCode( code )
				.withState( state )
				.withDataSource( dataSource.orElse( DEFAULT_ESI_SERVER ) )
				.build();
		final ValidateAuthorizationTokenResponse authorizationResponse = this.authorizationServiceV1
				.validateAuthorizationToken( authorizationTokenRequest );
		response.addCookie( authorizationResponse.getCookie() );
		return new ResponseEntity<>( authorizationResponse, HttpStatus.OK );
	}

	/**
	 * This endpoints is expected to receive a HttpOnly cookie from the frontend with the unique session identifier. There are some scenarios to
	 * handle the different responses.
	 *
	 * <ul>
	 *     <li><b>cookie not exists</b>- if this is a new login of from a new terminal the cookie is not set. Then the endpoint returns the 'not
	 *     found' message to start a new EVE SSO Login</li>
	 *     <li><b>cookie exists but no session</b>-this is the same scenario. The 'not found' tells that there is no session and should be
	 *     created with a new EVE SSO Login</li>
	 *     <li><b>cookie with expired session</b>-the session if found but the expiration time is on the past. The user should authenticate again
	 *     but this time we report the front end of a 'not valid' session.</li>
	 *     <li><b>cooki found and valid session</b>-report to the caller that the session is still valid with the message 'valid'. In this
	 *     scenario we should update the session with a new expiration time and a new JWT that should be reported to the called for store and use
	 *     on the next set of authenticated calls.
	 *     </li>
	 * </ul>
	 *
	 * @return the response message depending on the scenario found.
	 */
	@GetMapping(path = { "/validateSession" },
			produces = "application/json")
	public ResponseEntity<CookieStateResponse> validateAuthenticatedSession(
			@CookieValue(value = NEOCOM_COOKIE_NAME, defaultValue = "-INVALID-") final String neocomCookieData ) {
		// Validate if the cookie is empty. Is so do not go ahead and return a 'not found' immediately.
		if (neocomCookieData.toUpperCase().contains( "INVALID" ))
			return new ResponseEntity<>( new CookieStateResponse.Builder()
					.withState( CookieStateResponse.SessionStateType.NOT_FOUND )
					.build(), HttpStatus.OK );
		else
			return new ResponseEntity<>( this.authorizationServiceV1.validateAuthenticationCookie( neocomCookieData ), HttpStatus.OK );
	}
}
