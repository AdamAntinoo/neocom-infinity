package org.dimensinfin.eveonline.neocom.infinity.authorization.rest.v1;

import java.sql.SQLException;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import org.dimensinfin.eveonline.neocom.database.entities.Credential;
import org.dimensinfin.eveonline.neocom.database.repositories.CredentialRepository;
import org.dimensinfin.eveonline.neocom.infinity.adapter.ConfigurationServiceWrapper;
import org.dimensinfin.eveonline.neocom.infinity.adapter.CredentialRepositoryWrapper;
import org.dimensinfin.eveonline.neocom.infinity.adapter.ESIDataProviderWrapper;
import org.dimensinfin.eveonline.neocom.infinity.service.SBConfigurationService;
import org.dimensinfin.eveonline.neocom.infinity.authorization.client.v1.StoreCredentialRequest;
import org.dimensinfin.eveonline.neocom.infinity.authorization.client.v1.StoreCredentialResponse;
import org.dimensinfin.eveonline.neocom.infinity.core.exceptions.NeoComSBException;
import org.dimensinfin.eveonline.neocom.provider.ESIDataProvider;

public class StoreCredentialServiceTest {
	private static final String TEST_JWT_TOKEN = "-TEST-JWT-TOKEN-";
	private ConfigurationServiceWrapper configurationServiceWrapper;
	private SBConfigurationService configurationService;
	private ESIDataProviderWrapper esiDataProviderWrapper;
	private ESIDataProvider esiDataProvider;
	private StoreCredentialService storeCredentialService;

	@BeforeEach
	public void beforeEach() {
		// Given
		this.configurationServiceWrapper = Mockito.mock( ConfigurationServiceWrapper.class );
		this.configurationService = Mockito.mock( SBConfigurationService.class );
		this.esiDataProviderWrapper = Mockito.mock( ESIDataProviderWrapper.class );
		this.esiDataProvider = Mockito.mock( ESIDataProvider.class );
		this.storeCredentialService = Mockito.mock( StoreCredentialService.class );
		// When
		Mockito.when( this.configurationServiceWrapper.getSingleton() ).thenReturn( this.configurationService );
		Mockito.when( this.esiDataProviderWrapper.getSingleton() ).thenReturn( this.esiDataProvider );
	}

	@Test
	public void storeCredentialFailure() throws SQLException {
		// Given
		final CredentialRepository credentialRepository = Mockito.mock( CredentialRepository.class );
		final CredentialRepositoryWrapper credentialRepositoryWrapper = Mockito.mock( CredentialRepositoryWrapper.class );
		final StoreCredentialRequest storeCredentialRequest = Mockito.mock( StoreCredentialRequest.class );
		final Credential credential = Mockito.mock( Credential.class );
		// When
		Mockito.when( credentialRepositoryWrapper.getSingleton() ).thenReturn( credentialRepository );
		Mockito.when( storeCredentialRequest.getCredential() ).thenReturn( credential );
		Mockito.when( credential.getUniqueCredential() ).thenReturn( "tranquility/123456" );
		Mockito.when( credential.getAccountName() ).thenReturn( "-ACCOUNT-NAME-" );
		Mockito.when( credential.getCorporationId() ).thenReturn( 123456 );
		Mockito.when( credential.getAccountId() ).thenReturn( 123456 );
		Mockito.doThrow( new SQLException( "-TEST-EXCEPTION-MESSAGE-" ) )
				.when( credentialRepository ).persist( Mockito.any( Credential.class ) );
		// Test
		final StoreCredentialService storeCredentialService = new StoreCredentialService(
				credentialRepositoryWrapper );
		// Exception
		Assertions.assertThrows( NeoComSBException.class, () -> {
					final StoreCredentialResponse obtained = storeCredentialService.storeCredential( storeCredentialRequest );
				} );
	}

	@Test
	public void storeCredentialSuccess() {
		// Given
		final CredentialRepository credentialRepository = Mockito.mock( CredentialRepository.class );
		final CredentialRepositoryWrapper credentialRepositoryWrapper = Mockito.mock( CredentialRepositoryWrapper.class );
		final StoreCredentialRequest storeCredentialRequest = Mockito.mock( StoreCredentialRequest.class );
		final Credential credential = Mockito.mock( Credential.class );
		// When
		Mockito.when( credentialRepositoryWrapper.getSingleton() ).thenReturn( credentialRepository );
		Mockito.when( storeCredentialRequest.getCredential() ).thenReturn( credential );
		Mockito.when( credential.getUniqueCredential() ).thenReturn( "tranquility/123456" );
		Mockito.when( credential.getAccountName() ).thenReturn( "-ACCOUNT-NAME-" );
		Mockito.when( credential.getCorporationId() ).thenReturn( 123456 );
		Mockito.when( credential.getAccountId() ).thenReturn( 123456 );
		// Test
		final StoreCredentialService storeCredentialService = new StoreCredentialService(
				credentialRepositoryWrapper );
		final StoreCredentialResponse obtained = storeCredentialService.storeCredential( storeCredentialRequest );
		final String expectedJwtToken = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJFU0kgT0F1dGgyIEF1dGhlbnRpY2F0aW9uIiwiY29ycG9yYXRpb25JZCI6MTIzNDU2LCJhY2NvdW50TmFtZSI6Ii1BQ0NPVU5ULU5BTUUtIiwiaXNzIjoiTmVvQ29tLkluZmluaXR5LkJhY2tlbmQiLCJ1bmlxdWVJZCI6InRyYW5xdWlsaXR5LzEyMzQ1NiIsInBpbG90SWQiOjEyMzQ1Nn0.61ejAdp_YmaeIBnwFRjRfEFYUDyNBXLrJSm71nz82wdOaaFS38TEUy7sngIEQKWN9Xp4lqhFU3O98xeQu6UmKA";
		// Asserts
		Assertions.assertNotNull( obtained );
		Assertions.assertEquals( expectedJwtToken, obtained.getJwtToken() );
	}
}
