package org.dimensinfin.eveonline.neocom.infinity.service;

import java.util.concurrent.TimeUnit;
import javax.servlet.http.Cookie;

import org.springframework.stereotype.Service;

import static org.dimensinfin.eveonline.neocom.infinity.infrastructure.config.GlobalAppConstants.TransportConstants.NEOCOM_COOKIE_NAME;

/**
 * @author Adam Antinoo (adamantinoo.git@gmail.com)
 * @since 0.20.0
 * @deprecated it is only used at a single place and there is no purpose in creating a service of a simple constructor.
 */
@Service
@Deprecated
public class CookieService {
	@Deprecated
	public Cookie generateCookie( final String payload ) {
		final Cookie cookie = new Cookie( NEOCOM_COOKIE_NAME, payload );
		cookie.setSecure( false ); // Use HTTPS
		cookie.setHttpOnly( true ); // Only for server access
		cookie.setMaxAge( (int) TimeUnit.DAYS.toSeconds( 7 ) ); // expires in 7 days
		cookie.setPath( "/" );
		return cookie;
	}
}
