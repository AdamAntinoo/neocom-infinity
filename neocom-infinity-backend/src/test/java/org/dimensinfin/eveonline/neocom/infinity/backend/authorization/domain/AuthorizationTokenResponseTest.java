package org.dimensinfin.eveonline.neocom.infinity.backend.authorization.domain;

import javax.servlet.http.Cookie;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import org.dimensinfin.eveonline.neocom.database.entities.Credential;

public class AuthorizationTokenResponseTest {
	private Credential credential;
	private Cookie cookie;

	@BeforeEach
	public void beforeEach() {
		this.credential = Mockito.mock( Credential.class );
		this.cookie = Mockito.mock( Cookie.class );
	}

	@Test
	public void buildComplete() {
		final AuthorizationTokenResponse request = new AuthorizationTokenResponse.Builder()
				.withCredential( this.credential )
				.withJwtToken( "FFJJWWTT-TTKKEENNFF" )
				.withEsiToken("-ESI-TOKEN-")
				.withCookie( this.cookie )
				.build();
		Assertions.assertNotNull( request );
		Assertions.assertNotNull( request.getCredential() );
		Assertions.assertNotNull( request.getJwtToken() );
		Assertions.assertNotNull( request.getEsiToken() );
		Assertions.assertNotNull( request.getCookie() );
	}

	@Test
	public void buildNotCompleted() {
		Assertions.assertThrows( NullPointerException.class,
				() -> new AuthorizationTokenResponse.Builder()
						.withCredential( null )
						.withJwtToken( "FFJJWWTT-TTKKEENNFF" )
						.withEsiToken("-ESI-TOKEN-")
						.withCookie( this.cookie )
						.build(),
				"Expected ValidateAuthorizationTokenResponse.Builder() to throw null verification, but it didn't." );
		Assertions.assertThrows( NullPointerException.class,
				() -> new AuthorizationTokenResponse.Builder()
						.withCredential( this.credential )
						.withJwtToken( null )
						.withEsiToken("-ESI-TOKEN-")
						.withCookie( this.cookie )
						.build(),
				"Expected ValidateAuthorizationTokenResponse.Builder() to throw null verification, but it didn't." );
		Assertions.assertThrows( NullPointerException.class,
				() -> new AuthorizationTokenResponse.Builder()
						.withCredential( this.credential )
						.withJwtToken( "FFJJWWTT-TTKKEENNFF" )
						.withEsiToken(null)
						.withCookie( this.cookie )
						.build(),
				"Expected ValidateAuthorizationTokenResponse.Builder() to throw null verification, but it didn't." );
		Assertions.assertThrows( NullPointerException.class,
				() -> new AuthorizationTokenResponse.Builder()
						.withCredential( this.credential )
						.withJwtToken( "FFJJWWTT-TTKKEENNFF" )
						.withEsiToken("-ESI-TOKEN-")
						.withCookie( null )
						.build(),
				"Expected ValidateAuthorizationTokenResponse.Builder() to throw null verification, but it didn't." );
	}

	@Test
	public void getterContract() {
		final Cookie cookie = Mockito.mock( Cookie.class );
		final AuthorizationTokenResponse request = new AuthorizationTokenResponse.Builder()
				.withCredential( this.credential )
				.withJwtToken( "FFJJWWTT-TTKKEENNFF" )
				.withEsiToken("-ESI-TOKEN-")
				.withCookie( cookie )
				.build();
		Assertions.assertNotNull( request );
		Assertions.assertNotNull( request.getCredential() );
		Assertions.assertNotNull( request.getJwtToken() );
		Assertions.assertNotNull( request.getEsiToken() );
		Assertions.assertNotNull( request.getCookie() );
	}
}
