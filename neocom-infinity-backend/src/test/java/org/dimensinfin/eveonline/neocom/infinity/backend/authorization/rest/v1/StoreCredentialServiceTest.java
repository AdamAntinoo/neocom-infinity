package org.dimensinfin.eveonline.neocom.infinity.backend.authorization.rest.v1;

import java.sql.SQLException;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import org.dimensinfin.eveonline.neocom.database.entities.Credential;
import org.dimensinfin.eveonline.neocom.database.repositories.CredentialRepository;
import org.dimensinfin.eveonline.neocom.infinity.authorization.client.v1.StoreCredentialRequest;
import org.dimensinfin.eveonline.neocom.infinity.authorization.client.v1.StoreCredentialResponse;
import org.dimensinfin.eveonline.neocom.infinity.core.exception.NeoComSBException;
import org.dimensinfin.eveonline.neocom.infinity.service.SBConfigurationService;
import org.dimensinfin.eveonline.neocom.provider.IConfigurationService;
import org.dimensinfin.eveonline.neocom.service.ESIDataService;

public class StoreCredentialServiceTest {
	private static final String TEST_JWT_TOKEN = "-TEST-JWT-TOKEN-";
	//	private ConfigurationServiceWrapper configurationServiceWrapper;
	private IConfigurationService configurationService;
	//	private ESIDataProviderWrapper esiDataProviderWrapper;
	private ESIDataService esiDataService;
	private StoreCredentialService storeCredentialService;

	@BeforeEach
	public void beforeEach() {
		// Given
		//		this.configurationServiceWrapper = Mockito.mock( ConfigurationServiceWrapper.class );
		this.configurationService = Mockito.mock( SBConfigurationService.class );
		//		this.esiDataProviderWrapper = Mockito.mock( ESIDataProviderWrapper.class );
		this.esiDataService = Mockito.mock( ESIDataService.class );
		this.storeCredentialService = Mockito.mock( StoreCredentialService.class );
		// When
		//		Mockito.when( this.configurationServiceWrapper.getSingleton() ).thenReturn( this.configurationService );
		//		Mockito.when( this.esiDataProviderWrapper.getSingleton() ).thenReturn( this.esiDataService );
	}

	@Test
	public void storeCredentialFailure() throws SQLException {
		// Given
		final CredentialRepository credentialRepository = Mockito.mock( CredentialRepository.class );
		//		final CredentialRepository credentialRepositoryWrapper = Mockito.mock( CredentialRepositoryWrapper.class );
		final StoreCredentialRequest storeCredentialRequest = Mockito.mock( StoreCredentialRequest.class );
		final Credential credential = Mockito.mock( Credential.class );
		// When
		//		Mockito.when( credentialRepositoryWrapper.getSingleton() ).thenReturn( credentialRepository );
		Mockito.when( storeCredentialRequest.getCredential() ).thenReturn( credential );
		Mockito.when( credential.getUniqueCredential() ).thenReturn( "tranquility/123456" );
		Mockito.when( credential.getAccountName() ).thenReturn( "-ACCOUNT-NAME-" );
		Mockito.when( credential.getCorporationId() ).thenReturn( 123456 );
		Mockito.when( credential.getAccountId() ).thenReturn( 123456 );
		Mockito.doThrow( new SQLException( "-TEST-EXCEPTION-MESSAGE-" ) )
				.when( credentialRepository ).persist( Mockito.any( Credential.class ) );
		// Test
		final StoreCredentialService storeCredentialService = new StoreCredentialService(
				credentialRepository );
		// Exception
		Assertions.assertThrows( NeoComSBException.class, () -> {
			final StoreCredentialResponse obtained = storeCredentialService.storeCredential( storeCredentialRequest );
		} );
	}

	@Test
	public void storeCredentialSuccess() {
		// Given
		final CredentialRepository credentialRepository = Mockito.mock( CredentialRepository.class );
		//		final CredentialRepositoryWrapper credentialRepositoryWrapper = Mockito.mock( CredentialRepositoryWrapper.class );
		final StoreCredentialRequest storeCredentialRequest = Mockito.mock( StoreCredentialRequest.class );
		final Credential credential = Mockito.mock( Credential.class );
		// When
		//		Mockito.when( credentialRepositoryWrapper.getSingleton() ).thenReturn( credentialRepository );
		Mockito.when( storeCredentialRequest.getCredential() ).thenReturn( credential );
		Mockito.when( credential.getUniqueCredential() ).thenReturn( "tranquility/123456" );
		Mockito.when( credential.getAccountName() ).thenReturn( "-ACCOUNT-NAME-" );
		Mockito.when( credential.getCorporationId() ).thenReturn( 123456 );
		Mockito.when( credential.getAccountId() ).thenReturn( 123456 );
		// Test
		final StoreCredentialService storeCredentialService = new StoreCredentialService(
				credentialRepository );
		final StoreCredentialResponse obtained = storeCredentialService.storeCredential( storeCredentialRequest );
		final String expectedJwtToken = "eyJhbGciOiJIUzUxMiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJOZW9Db20uSW5maW5pdHkuQmFja2VuZCIsInN1YiI6IkVTSSBPQXV0aDIgQXV0aGVudGljYXRpb24iLCJ1bmlxdWVJZCI6InRyYW5xdWlsaXR5LzEyMzQ1NiIsImFjY291bnROYW1lIjoiLUFDQ09VTlQtTkFNRS0iLCJjb3Jwb3JhdGlvbklkIjoxMjM0NTYsInBpbG90SWQiOjEyMzQ1Nn0.jU9uz-y4WGWQZk7VEyEwev5KAsyAkVDbx8JSlZI0Kadb-kYhVAM-CvLLRTWcdQWp4nINcjx9qJsiqFRGkL0uEw";
		// Asserts
		Assertions.assertNotNull( obtained );
		Assertions.assertEquals( expectedJwtToken, obtained.getJwtToken() );
	}
}
