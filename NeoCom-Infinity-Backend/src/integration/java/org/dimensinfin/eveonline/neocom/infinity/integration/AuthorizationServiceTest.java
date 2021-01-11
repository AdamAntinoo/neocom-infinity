package org.dimensinfin.eveonline.neocom.infinity.integration;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.Mockito;

import org.dimensinfin.eveonline.neocom.database.repositories.CredentialRepository;
import org.dimensinfin.eveonline.neocom.esiswagger.model.GetCharactersCharacterIdOk;
import org.dimensinfin.eveonline.neocom.infinity.authorization.client.v1.ValidateAuthorizationTokenRequest;
import org.dimensinfin.eveonline.neocom.infinity.authorization.client.v1.ValidateAuthorizationTokenResponse;
import org.dimensinfin.eveonline.neocom.infinity.authorization.rest.v1.AuthorizationService;
import org.dimensinfin.eveonline.neocom.provider.IConfigurationService;
import org.dimensinfin.eveonline.neocom.service.ESIDataService;

public class AuthorizationServiceTest /*extends UnitTestEnvironmentDefinition */ {
	private IConfigurationService configurationService;
	//	private ESIDataProviderWrapper esiDataProviderWrapper;
	private ESIDataService esiDataService;
	private CredentialRepository credentialRepository;
	//	private CredentialRepository credentialRepositoryWrapper;
	//	private SBConfigurationService configurationService;

	@BeforeEach
	public void beforeEach() {
		// Given
		//		this.configurationServiceWrapper = Mockito.mock( ConfigurationServiceWrapper.class );
		//		this.esiDataProviderWrapper = Mockito.mock( ESIDataProviderWrapper.class );
		this.esiDataService = Mockito.mock( ESIDataService.class );
		this.credentialRepository = Mockito.mock( CredentialRepository.class );
		//		this.credentialRepositoryWrapper = Mockito.mock( CredentialRepositoryWrapper.class );
		this.configurationService = Mockito.mock( IConfigurationService.class );
		// When
		//		Mockito.when( this.esiDataProviderWrapper.getSingleton() ).thenReturn( this.esiDataService );
		//		Mockito.when( this.credentialRepositoryWrapper.getSingleton() ).thenReturn( this.credentialRepository );
	}


	//	@Test
	public void validateAuthorizationToken() {
		// Given
		final ValidateAuthorizationTokenRequest validateAuthorizationTokenRequest =
				new ValidateAuthorizationTokenRequest.Builder()
						.withCode( "-VALID-CODE-" )
						.withState( "LU5FT0NPTS5JTkZJTklUWS1QUk9EVUNUSU9OLVZBTElEIFNUQVRFIFNUUklORy0=" )
						.build();
		final GetCharactersCharacterIdOk pilotData = Mockito.mock( GetCharactersCharacterIdOk.class );
		// When
		//		Mockito.when( this.configurationServiceWrapper.getSingleton() ).thenReturn( this.configurationService );
		Mockito.when( this.esiDataService.getCharactersCharacterId( Mockito.anyInt() ) ).thenReturn( pilotData );
		Mockito.when( pilotData.getCorporationId() ).thenReturn( 98384726 );

		// Test
		final AuthorizationService authorizationService = new AuthorizationService(
				this.configurationService,
				this.esiDataService,
				this.credentialRepository );
		final ValidateAuthorizationTokenResponse obtainedResponse = authorizationService
				.validateAuthorizationToken( validateAuthorizationTokenRequest );

		// Asserts
		Assertions.assertNotNull( obtainedResponse );
	}
}
