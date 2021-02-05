package org.dimensinfin.eveonline.neocom.infinity.backend.authorization.domain;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class AuthenticationStateResponseTest {
	@Test
	public void buildContract() {
		final AuthenticationStateResponse authenticationStateResponse = new AuthenticationStateResponse.Builder()
				.withState( AuthenticationStateResponse.AuthenticationStateType.VALID ).build();
		Assertions.assertNotNull( authenticationStateResponse );
		Assertions.assertEquals( AuthenticationStateResponse.AuthenticationStateType.VALID, authenticationStateResponse.getState() );
	}

	@Test
	public void buildIncomplete() {
		final AuthenticationStateResponse authenticationStateResponse = new AuthenticationStateResponse.Builder().build();
		Assertions.assertNotNull( authenticationStateResponse );
		Assertions.assertEquals( AuthenticationStateResponse.AuthenticationStateType.NOT_VALID, authenticationStateResponse.getState() );
	}

	@Test
	public void enumContract() {
		// Test
		final AuthenticationStateResponse authenticationStateResponse = new AuthenticationStateResponse.Builder()
				.withState( AuthenticationStateResponse.AuthenticationStateType.NOT_FOUND ).build();
		// Assertions
		Assertions.assertEquals( AuthenticationStateResponse.AuthenticationStateType.NOT_FOUND, authenticationStateResponse.getState() );
	}

	@Test
	public void gettersContract() {
		// Test
		final AuthenticationStateResponse authenticationStateResponse = new AuthenticationStateResponse.Builder()
				.withState( AuthenticationStateResponse.AuthenticationStateType.NOT_FOUND ).build();
		// Assertions
		Assertions.assertEquals( AuthenticationStateResponse.AuthenticationStateType.NOT_FOUND, authenticationStateResponse.getState() );
	}
}