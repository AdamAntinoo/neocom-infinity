package org.dimensinfin.eveonline.neocom.infinity.credential.rest;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.dimensinfin.eveonline.neocom.database.entities.Credential;
import org.dimensinfin.eveonline.neocom.infinity.credential.rest.support.CredentialSupportController;
import org.dimensinfin.eveonline.neocom.infinity.credential.rest.support.CredentialSupportService;

public class CredentialSupportControllerTest {
	@Test
	public void createContract() {
		final CredentialSupportService credentialSupportService = Mockito.mock( CredentialSupportService.class );
		final CredentialSupportController credentialSupportController = new CredentialSupportController( credentialSupportService );
		Assertions.assertNotNull( credentialSupportController );
	}

	@Test
	public void findById() {
		final CredentialSupportService credentialSupportService = Mockito.mock( CredentialSupportService.class );
		final CredentialSupportController credentialSupportController = new CredentialSupportController( credentialSupportService );
		Assertions.assertNotNull( credentialSupportController );
		final String credentialId = "-TEST-CREDENTIAL-ID-";
		final Credential credential = new Credential.Builder( 123456 )
				.withAccountName( "-ACCOUNT-NAME-" )
				.build();
		final ResponseEntity<Credential> expected = new ResponseEntity<>( credential, HttpStatus.OK );
		// When
		Mockito.when( credentialSupportService.findCredentialById( Mockito.anyString() ) ).thenReturn( credential );
		final ResponseEntity<Credential> obtained = credentialSupportController.findById( credentialId );
		Assertions.assertEquals( expected, obtained );
	}
}
