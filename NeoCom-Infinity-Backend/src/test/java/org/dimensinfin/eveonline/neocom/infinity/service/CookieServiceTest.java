package org.dimensinfin.eveonline.neocom.infinity.service;

import javax.servlet.http.Cookie;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class CookieServiceTest {
	@Test
	public void constructorContract() {
		final CookieService cookieService = new CookieService();
		Assertions.assertNotNull( cookieService );
	}

	@Test
	public void generateCookie() {
		// Given
		final String payload = "-PAYLOAD-";
		// Test
		final CookieService cookieService = new CookieService();
		final Cookie obtained = cookieService.generateCookie( payload );
		// Assertions
		Assertions.assertNotNull( obtained );
		Assertions.assertEquals( 7 * 24 * 60 * 60, obtained.getMaxAge() );
	}
}