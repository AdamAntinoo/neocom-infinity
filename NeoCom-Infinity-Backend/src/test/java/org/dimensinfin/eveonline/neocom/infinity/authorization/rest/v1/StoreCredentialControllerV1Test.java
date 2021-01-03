package org.dimensinfin.eveonline.neocom.infinity.authorization.rest.v1;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.dimensinfin.eveonline.neocom.database.entities.Credential;
import org.dimensinfin.eveonline.neocom.infinity.authorization.client.v1.StoreCredentialRequest;
import org.dimensinfin.eveonline.neocom.infinity.authorization.client.v1.StoreCredentialResponse;
import org.dimensinfin.eveonline.neocom.infinity.core.exception.NeoComSBException;

public class StoreCredentialControllerV1Test {
	private static final String TEST_JWT_TOKEN = "-TEST-JWT-TOKEN-";
	private StoreCredentialService storeCredentialService;

	@BeforeEach
	public void beforeEach() {
		this.storeCredentialService = Mockito.mock( StoreCredentialService.class );
	}

	@Test
	public void storeCredentialFailure() {
		// Given
		final StoreCredentialControllerV1 storeCredentialControllerV1 = new StoreCredentialControllerV1( this.storeCredentialService );
		final Credential credential = Mockito.mock( Credential.class );
		final Integer credentialId = 123456;
		// When
		Mockito.when( credential.getAccountId() ).thenReturn( 654321 );
		// Test
		Assertions.assertThrows( NeoComSBException.class, () -> {
			final ResponseEntity<StoreCredentialResponse> obtained = storeCredentialControllerV1.storeCredential( credentialId,
					credential );
		} );
	}

	@Test
	public void storeCredentialSuccess() {
		// Given
		final StoreCredentialControllerV1 storeCredentialControllerV1 = new StoreCredentialControllerV1( this.storeCredentialService );
		final Credential credential = Mockito.mock( Credential.class );
		final Integer credentialId = 123456;
		final StoreCredentialResponse storeCredentialResponse = new StoreCredentialResponse.Builder()
				.withJwtToken( TEST_JWT_TOKEN )
				.build();
		// When
		Mockito.when( credential.getAccountId() ).thenReturn( credentialId );
		Mockito.when( this.storeCredentialService.storeCredential( Mockito.any( StoreCredentialRequest.class ) ) )
				.thenReturn( storeCredentialResponse );
		// Test
		final ResponseEntity<StoreCredentialResponse> obtained = storeCredentialControllerV1.storeCredential( credentialId, credential );
		Assertions.assertNotNull( obtained );
		Assertions.assertEquals( HttpStatus.CREATED, obtained.getStatusCode() );
		Assertions.assertEquals( TEST_JWT_TOKEN, obtained.getBody().getJwtToken() );
	}
}
