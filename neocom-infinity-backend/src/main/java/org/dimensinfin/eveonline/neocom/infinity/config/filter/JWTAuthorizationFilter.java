package org.dimensinfin.eveonline.neocom.infinity.config.filter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.constraints.NotNull;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import org.dimensinfin.logging.LogWrapper;

import static org.dimensinfin.eveonline.neocom.infinity.config.security.SecurityConstants.AUTHORIZATION_HEADER_NAME;
import static org.dimensinfin.eveonline.neocom.infinity.config.security.SecurityConstants.SECRET;
import static org.dimensinfin.eveonline.neocom.infinity.config.security.SecurityConstants.SIGNATURE_HEADER_NAME;
import static org.dimensinfin.eveonline.neocom.infinity.config.security.SecurityConstants.SUBJECT;
import static org.dimensinfin.eveonline.neocom.infinity.config.security.SecurityConstants.TOKEN_PREFIX;
import static org.dimensinfin.eveonline.neocom.infinity.infrastructure.config.GlobalAppConstants.NetworkConstants.NEOCOM_COOKIE_NAME;

/**
 * Responsibility: Extract from the request Header the authentication data to be used on authenticated requests. The class previously used a Cookie to
 * transport the authoriation informaiton but that is deprecated in favor to the use of the standard authentication Header. This Filter in called on
 * each request despite being set to be used only on requests that require authorization.
 */
public class JWTAuthorizationFilter extends BasicAuthenticationFilter {
	private static final List<String> signatures = new ArrayList<>();

	static {
		signatures.add( "S0000.0016.0001" );
		signatures.add( "S0000.0020.0000" );
	}

	// - C O N S T R U C T O R S
	public JWTAuthorizationFilter( @NotNull final AuthenticationManager authManager ) {
		super( authManager );
	}

	/**
	 * Process the request to validate when Authorization header exists and then load that authentication data into the authentication service.
	 */
	@Override
	protected void doFilterInternal( @NotNull final HttpServletRequest request,
	                                 @NotNull final HttpServletResponse response,
	                                 @NotNull final FilterChain chain ) throws IOException, ServletException {
		final Optional<String> token = this.validateJWTToken( request );
		if ( token.isPresent() ) {
			final UsernamePasswordAuthenticationToken authentication = this.generateAuthentication( token.get() );
			if ( !Objects.isNull( authentication ) ) SecurityContextHolder.getContext().setAuthentication( authentication );
		}
		chain.doFilter( request, response );
	}

	private UsernamePasswordAuthenticationToken generateAuthentication( @NotNull final String token ) {
		try {
			final DecodedJWT jwtToken = JWT.require( Algorithm.HMAC512( SECRET.getBytes() ) )
					.build()
					.verify( token.replace( TOKEN_PREFIX, "" ).trim() );
			if ( this.validateSubject( token ) ) { // Check this is the subject we expect
				return new UsernamePasswordAuthenticationToken( jwtToken.getPayload(), null, new ArrayList<>() );
			}
		} catch (final JWTDecodeException runtime) {
			LogWrapper.error( runtime );
		}
		return null;
	}

	private Optional<String> accessCookie( @NotNull final HttpServletRequest request ) {
		final Cookie[] cookies = request.getCookies();
		if ( null != cookies )
			for (final Cookie cookie : cookies)
				if ( cookie.getName().equalsIgnoreCase( NEOCOM_COOKIE_NAME ) )
					return Optional.of( cookie.getValue() );
		//		LogWrapper.info( "Failing Cookie authentication validation." );
		return Optional.empty();
	}

	private Optional<String> accessHeaderToken( final @NotNull HttpServletRequest request ) {
		return Optional.ofNullable( request.getHeader( AUTHORIZATION_HEADER_NAME ) );
	}


	/**
	 * Validate if the authorization token is present. During the refactorization phase we can assume that the token can also be received on a Cookie.
	 * Header has preference and if the header does not exist then there is no token.
	 *
	 * @param request the request to use to validate the authorization
	 * @return the token string if it exists
	 */
	private Optional<String> validateJWTToken( @NotNull final HttpServletRequest request ) {
		final Optional<String> header = this.accessHeaderToken( request );
		if ( header.isEmpty() ) return Optional.empty();
		final Optional<String> cookie = this.accessCookie( request );
		final String token = cookie.orElseGet( header::get );
		if ( !token.startsWith( TOKEN_PREFIX ) ) {
			LogWrapper.info( "Failing JWT Token structure authentication validation." );
			return Optional.empty();
		}
		return Optional.of( token );
	}

	/**
	 * Checks the aplication signature against a list of accepted signatures. This security method is disabled.
	 */
	@Deprecated
	private boolean validateSignature( final HttpServletRequest request ) {
		final String signature = request.getHeader( SIGNATURE_HEADER_NAME );
		if ( null != signature )
			for (final String validation : signatures)
				if ( signature.equals( validation ) ) return true;
		//		LogWrapper.info( "Failing Signature authentication validation." );
		return true;
	}

	private boolean validateSubject( final String token ) {
		final String subject = JWT.require( Algorithm.HMAC512( SECRET.getBytes() ) )
				.build()
				.verify( token.replace( TOKEN_PREFIX, "" ) )
				.getSubject();

		if ( null != subject )
			if ( subject.equalsIgnoreCase( SUBJECT ) ) return true;
		LogWrapper.info( "Failing JWT Subject authentication validation." );
		return false;
	}
}
