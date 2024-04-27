package org.dimensinfin.eveonline.neocom.infinity.config.filter;

import java.io.IOException;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.constraints.NotNull;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
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
import static org.dimensinfin.eveonline.neocom.infinity.infrastructure.config.GlobalAppConstants.TransportConstants.NEOCOM_COOKIE_NAME;

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
	 * Process the request to validate that the Authorization header exists and then load that authentication data into the authentication service.
	 */
	@Override
	protected void doFilterInternal( @NotNull final HttpServletRequest request,
	                                 @NotNull final HttpServletResponse response,
	                                 @NotNull final FilterChain chain ) throws IOException, ServletException {
		if ((this.validateCookie( request )) &&
			(this.validateSignature( request )) &&
			(this.validateJWTToken( request, this.accessCookieToken( request ) ))) {
			final UsernamePasswordAuthenticationToken authentication = this.getAuthentication( request );
			if (null != authentication) SecurityContextHolder.getContext().setAuthentication( authentication );
		}
		chain.doFilter( request, response );
	}

	private String accessCookieToken( @NotNull final HttpServletRequest request ) {
		final Cookie[] cookies = request.getCookies();
		if (null != cookies)
			for (final Cookie cookie : cookies)
				if (cookie.getName().equalsIgnoreCase( NEOCOM_COOKIE_NAME ))
					return cookie.getValue();
		return null;
	}

	private UsernamePasswordAuthenticationToken getAuthentication( @NotNull final HttpServletRequest request ) {
		final String token = request.getHeader( AUTHORIZATION_HEADER_NAME );
		if (token != null) {
			final DecodedJWT jwtToken = JWT.require( Algorithm.HMAC512( SECRET.getBytes() ) )
				.build()
				.verify( token.replace( TOKEN_PREFIX, "" ) );
			if (this.validateSubject( token )) { // Check this is the subject we expect
				return new UsernamePasswordAuthenticationToken( jwtToken.getPayload(), null, new ArrayList<>() );
			}
		}
		return null;
	}

	private boolean validateCookie( @NotNull final HttpServletRequest request ) {
		final Cookie[] cookies = request.getCookies();
		if (null != cookies)
			for (final Cookie cookie : cookies)
				if (cookie.getName().equalsIgnoreCase( NEOCOM_COOKIE_NAME ))
					return true;
		LogWrapper.info( "Failing Cookie authentication validation." );
		return false;
	}

	private boolean validateJWTToken( @NotNull final HttpServletRequest request, final String cookieJWTToken ) {
		if (null == cookieJWTToken) return false;
		final String header = request.getHeader( AUTHORIZATION_HEADER_NAME );
		if (header == null || !header.startsWith( TOKEN_PREFIX )) {
			LogWrapper.info( "Failing JWT Token structure authentication validation." );
			return false;
		}
		LogWrapper.info( MessageFormat.format( "Check JWT Token consistency: {0}",
			header.equals( TOKEN_PREFIX + cookieJWTToken ) ) );
		return header.equals( TOKEN_PREFIX + cookieJWTToken );
	}

	private boolean validateSignature( final HttpServletRequest request ) {
		final String signature = request.getHeader( SIGNATURE_HEADER_NAME );
		if (null != signature)
			for (final String validation : signatures)
				if (signature.equals( validation )) return true;
		LogWrapper.info( "Failing Signature authentication validation." );
		return false;
	}

	private boolean validateSubject( final String token ) {
		final String subject = JWT.require( Algorithm.HMAC512( SECRET.getBytes() ) )
			.build()
			.verify( token.replace( TOKEN_PREFIX, "" ) )
			.getSubject();

		if (null != subject)
			if (subject.equalsIgnoreCase( SUBJECT )) return true;
		LogWrapper.info( "Failing JWT Subject authentication validation." );
		return false;
	}
}
