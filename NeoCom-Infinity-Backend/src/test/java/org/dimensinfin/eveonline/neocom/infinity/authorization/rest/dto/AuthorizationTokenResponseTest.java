package org.dimensinfin.eveonline.neocom.infinity.authorization.rest.dto;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import org.dimensinfin.eveonline.neocom.database.entities.Credential;
import org.dimensinfin.eveonline.neocom.infinity.backend.authorization.domain.AuthorizationTokenResponse;

public class AuthorizationTokenResponseTest {
	private Credential credential;

	@BeforeEach
	public void beforeEach() {
		this.credential = Mockito.mock( Credential.class );
	}

	@Test
	public void buildComplete() {
		final AuthorizationTokenResponse request = new AuthorizationTokenResponse.Builder()
				.withCredential( this.credential )
				.withJwtToken( "FFJJWWTT-TTKKEENNFF" )
				.build();
		Assertions.assertNotNull( request );
		Assertions.assertNotNull( request.getCredential() );
	}

	@Test
	public void buildNotCompleted() {
		final NullPointerException thrown = Assertions.assertThrows( NullPointerException.class,
				() -> new AuthorizationTokenResponse.Builder()
						.withCredential( null )
						.withJwtToken( "FFJJWWTT-TTKKEENNFF" )
						.build(),
				"Expected ValidateAuthorizationTokenResponse.Builder() to throw null verification, but it didn't." );
	}

	@Test
	public void getterContract() {
		final AuthorizationTokenResponse request = new AuthorizationTokenResponse.Builder()
				.withCredential( this.credential )
				.withJwtToken( "FFJJWWTT-TTKKEENNFF" )
				.build();
		Assertions.assertNotNull( request );
		Assertions.assertNotNull( request.getCredential() );
		Assertions.assertNotNull( request.getJwtToken() );
	}
}
