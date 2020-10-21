package org.dimensinfin.eveonline.neocom.infinity.authorization.rest.v1;

import java.io.IOException;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import org.dimensinfin.eveonline.neocom.database.repositories.CredentialRepository;
import org.dimensinfin.eveonline.neocom.esiswagger.model.GetCharactersCharacterIdOk;
import org.dimensinfin.eveonline.neocom.infinity.adapter.ConfigurationServiceWrapper;
import org.dimensinfin.eveonline.neocom.infinity.adapter.CredentialRepositoryWrapper;
import org.dimensinfin.eveonline.neocom.infinity.adapter.ESIDataProviderWrapper;
import org.dimensinfin.eveonline.neocom.infinity.authorization.client.v1.ValidateAuthorizationTokenRequest;
import org.dimensinfin.eveonline.neocom.infinity.authorization.client.v1.ValidateAuthorizationTokenResponse;
import org.dimensinfin.eveonline.neocom.infinity.authorization.rest.v1.AuthorizationService;
import org.dimensinfin.eveonline.neocom.infinity.support.UnitTestEnvironmentDefinition;
import org.dimensinfin.eveonline.neocom.provider.ESIDataProvider;

public class AuthorizationServiceTest extends UnitTestEnvironmentDefinition {
	private ConfigurationServiceWrapper configurationServiceWrapper;
	private ESIDataProviderWrapper esiDataProviderWrapper;
	private ESIDataProvider esiDataProvider;
	private CredentialRepository credentialRepository;
	private CredentialRepositoryWrapper credentialRepositoryWrapper;

	@BeforeEach
	public void beforeEach()  {
		// Given
		this.configurationServiceWrapper = Mockito.mock( ConfigurationServiceWrapper.class );
		this.esiDataProviderWrapper = Mockito.mock( ESIDataProviderWrapper.class );
		this.esiDataProvider = Mockito.mock( ESIDataProvider.class );
		this.credentialRepository = Mockito.mock( CredentialRepository.class );
		this.credentialRepositoryWrapper = Mockito.mock( CredentialRepositoryWrapper.class );
		// When
		Mockito.when( this.esiDataProviderWrapper.getSingleton() ).thenReturn( this.esiDataProvider );
		Mockito.when( this.credentialRepositoryWrapper.getSingleton() ).thenReturn( this.credentialRepository );
	}


	@Test
	public void validateAuthorizationToken() {
		// Given
		final ValidateAuthorizationTokenRequest validateAuthorizationTokenRequest =
				new ValidateAuthorizationTokenRequest.Builder()
						.withCode( "-VALID-CODE-" )
						.withState( "LU5FT0NPTS5JTkZJTklUWS1QUk9EVUNUSU9OLVZBTElEIFNUQVRFIFNUUklORy0=" )
						.build();
		final GetCharactersCharacterIdOk pilotData = Mockito.mock( GetCharactersCharacterIdOk.class );
		// When
		Mockito.when( this.configurationServiceWrapper.getSingleton() ).thenReturn( this.itConfigurationProvider );
		Mockito.when( this.esiDataProvider.getCharactersCharacterId( Mockito.anyInt() ) ).thenReturn( pilotData );
		Mockito.when( pilotData.getCorporationId() ).thenReturn( 98384726 );

		// Test
		final AuthorizationService authorizationService = new AuthorizationService(
				this.configurationServiceWrapper,
				this.esiDataProviderWrapper,
				this.credentialRepositoryWrapper );
		final ValidateAuthorizationTokenResponse obtainedResponse = authorizationService
				.validateAuthorizationToken( validateAuthorizationTokenRequest );

		// Asserts
		Assertions.assertNotNull( obtainedResponse );
	}
}
