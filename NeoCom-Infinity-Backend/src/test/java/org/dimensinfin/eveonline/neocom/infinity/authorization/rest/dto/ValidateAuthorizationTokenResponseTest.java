package org.dimensinfin.eveonline.neocom.infinity.authorization.rest.dto;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import org.dimensinfin.eveonline.neocom.database.entities.Credential;
import org.dimensinfin.eveonline.neocom.infinity.authorization.client.v1.ValidateAuthorizationTokenResponse;

public class ValidateAuthorizationTokenResponseTest {
	private Credential credential;

	@BeforeEach
	public void beforeEach() {
		this.credential = Mockito.mock( Credential.class );
	}

	@Test
	public void buildComplete() {
		final ValidateAuthorizationTokenResponse request = new ValidateAuthorizationTokenResponse.Builder()
				.withCredential( credential )
				.withJwtToken( "FFJJWWTT-TTKKEENNFF" )
				.build();
		Assertions.assertNotNull( request );
		Assertions.assertNotNull( request.getCredential() );
	}

	@Test
	public void buildNotCompleted() {
		NullPointerException thrown = Assertions.assertThrows( NullPointerException.class,
				() -> new ValidateAuthorizationTokenResponse.Builder()
						.withCredential( null )
						.withJwtToken( "FFJJWWTT-TTKKEENNFF" )
						.build(),
				"Expected ValidateAuthorizationTokenResponse.Builder() to throw null verification, but it didn't." );
	}

	@Test
	public void getterContract() {
		final ValidateAuthorizationTokenResponse request = new ValidateAuthorizationTokenResponse.Builder()
				.withCredential( credential )
				.withJwtToken( "FFJJWWTT-TTKKEENNFF" )
				.build();
		Assertions.assertNotNull( request );
		Assertions.assertNotNull( request.getCredential() );
		Assertions.assertNotNull( request.getJwtToken() );
	}
}
