package org.dimensinfin.eveonline.neocom.infinity.backend.authorization.domain;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import org.dimensinfin.eveonline.neocom.database.entities.Credential;

public class AuthenticationStateResponseTest {
	private static final String TEST_AUTHENTICATION_STATE_TOKEN = "-TEST-JWT-TOKEN-";
	private Credential credential;

	@BeforeEach
	public void beforeEach() {
		this.credential = Mockito.mock( Credential.class );
	}

	@Test
	public void buildContract() {
		final AuthenticationStateResponse authenticationStateResponse = new AuthenticationStateResponse.Builder()
				.withState( AuthenticationStateResponse.AuthenticationStateType.VALID )
				.withJwtToken( TEST_AUTHENTICATION_STATE_TOKEN )
				.withCredential( this.credential )
				.build();
		Assertions.assertNotNull( authenticationStateResponse );
		Assertions.assertEquals( AuthenticationStateResponse.AuthenticationStateType.VALID, authenticationStateResponse.getState() );
	}

	@Test
	public void buildIncomplete() {
		Assertions.assertNotNull( new AuthenticationStateResponse.Builder()
				.withState( AuthenticationStateResponse.AuthenticationStateType.VALID )
				.withCredential( this.credential )
				.build()
		);
		Assertions.assertNotNull( new AuthenticationStateResponse.Builder()
				.withState( AuthenticationStateResponse.AuthenticationStateType.VALID )
				.withJwtToken( TEST_AUTHENTICATION_STATE_TOKEN )
				.build()
		);
	}

	@Test
	public void enumContract() {
		// Test
		final AuthenticationStateResponse authenticationStateResponse = new AuthenticationStateResponse.Builder()
				.withState( AuthenticationStateResponse.AuthenticationStateType.NOT_FOUND )
				.withJwtToken( TEST_AUTHENTICATION_STATE_TOKEN )
				.withCredential( this.credential )
				.build();
		// Assertions
		Assertions.assertEquals( AuthenticationStateResponse.AuthenticationStateType.NOT_FOUND, authenticationStateResponse.getState() );
	}

	@Test
	public void gettersContract() {
		// Test
		final AuthenticationStateResponse authenticationStateResponse = new AuthenticationStateResponse.Builder()
				.withState( AuthenticationStateResponse.AuthenticationStateType.NOT_FOUND )
				.withJwtToken( TEST_AUTHENTICATION_STATE_TOKEN )
				.withCredential( this.credential )
				.build();
		// Assertions
		Assertions.assertEquals( AuthenticationStateResponse.AuthenticationStateType.NOT_FOUND, authenticationStateResponse.getState() );
		Assertions.assertEquals( TEST_AUTHENTICATION_STATE_TOKEN, authenticationStateResponse.getJwtToken() );
		Assertions.assertEquals( this.credential.toString(), authenticationStateResponse.getCredential().toString() );
	}
}