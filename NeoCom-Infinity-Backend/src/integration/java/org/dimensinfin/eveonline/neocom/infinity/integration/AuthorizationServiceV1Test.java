package org.dimensinfin.eveonline.neocom.infinity.integration;

import javax.servlet.http.HttpSession;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import org.dimensinfin.eveonline.neocom.database.repositories.CredentialRepository;
import org.dimensinfin.eveonline.neocom.esiswagger.model.GetCharactersCharacterIdOk;
import org.dimensinfin.eveonline.neocom.infinity.authorization.client.v1.ValidateAuthorizationTokenRequest;
import org.dimensinfin.eveonline.neocom.infinity.authorization.client.v1.ValidateAuthorizationTokenResponse;
import org.dimensinfin.eveonline.neocom.infinity.backend.authorization.rest.v1.AuthorizationServiceV1;
import org.dimensinfin.eveonline.neocom.infinity.config.security.NeoComAuthenticationProvider;
import org.dimensinfin.eveonline.neocom.provider.IConfigurationService;
import org.dimensinfin.eveonline.neocom.service.ESIDataService;

public class AuthorizationServiceV1Test /*extends UnitTestEnvironmentDefinition */ {
	private IConfigurationService configurationService;
	//	private ESIDataProviderWrapper esiDataProviderWrapper;
	private ESIDataService esiDataService;
	private CredentialRepository credentialRepository;
	private HttpSession session;
	private NeoComAuthenticationProvider neoComAuthenticationProvider;

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
		this.session = Mockito.mock( HttpSession.class );
		this.neoComAuthenticationProvider = Mockito.mock( NeoComAuthenticationProvider.class );
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
		Mockito.when( this.esiDataService.getCharactersCharacterId( Mockito.anyInt() ) ).thenReturn( pilotData );
		Mockito.when( pilotData.getCorporationId() ).thenReturn( 98384726 );

		// Test
		final AuthorizationServiceV1 authorizationServiceV1 = new AuthorizationServiceV1(
				this.configurationService,
				this.esiDataService,
				this.credentialRepository,
				this.session,
				this.neoComAuthenticationProvider,
				cookieService );
		final ValidateAuthorizationTokenResponse obtainedResponse = authorizationServiceV1
				.validateAuthorizationToken( validateAuthorizationTokenRequest );

		// Asserts
		Assertions.assertNotNull( obtainedResponse );
	}
}
