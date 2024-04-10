package org.dimensinfin.eveonline.neocom.infinity.service;

import java.util.concurrent.TimeUnit;
import javax.servlet.http.Cookie;

import org.springframework.stereotype.Service;

import static org.dimensinfin.eveonline.neocom.infinity.NeoComInfinityBackendApplication.NEOCOM_COOKIE_NAME;

/**
 * @author Adam Antinoo (adamantinoo.git@gmail.com)
 * @since 0.20.0
 */
@Service
public class CookieService {
	public Cookie generateCookie( final String payload ) {
		final Cookie cookie = new Cookie( NEOCOM_COOKIE_NAME, payload );
		cookie.setSecure( false ); // Use HTTPS
		cookie.setHttpOnly( true ); // Only for server access
		cookie.setMaxAge( (int) TimeUnit.DAYS.toSeconds( 7 ) ); // expires in 7 days
		cookie.setPath( "/" );
		return cookie;
	}
}