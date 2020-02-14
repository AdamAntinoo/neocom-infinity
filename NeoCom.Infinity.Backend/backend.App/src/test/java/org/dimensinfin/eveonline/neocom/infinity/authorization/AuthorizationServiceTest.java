package org.dimensinfin.eveonline.neocom.infinity.authorization;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.http.ResponseEntity;

import org.dimensinfin.eveonline.neocom.esiswagger.model.GetCharactersCharacterIdOk;
import org.dimensinfin.eveonline.neocom.infinity.adapter.CredentialRepositoryWrapper;
import org.dimensinfin.eveonline.neocom.infinity.adapter.ESIDataProviderWrapper;
import org.dimensinfin.eveonline.neocom.infinity.authorization.client.v1.ValidateAuthorizationTokenRequest;
import org.dimensinfin.eveonline.neocom.infinity.authorization.client.v1.ValidateAuthorizationTokenResponse;
import org.dimensinfin.eveonline.neocom.infinity.support.SupportConfigurationProviderWrapper;
import org.dimensinfin.eveonline.neocom.provider.ESIDataProvider;

//@RunWith(MockitoJUnitRunner.class)
public class AuthorizationServiceTest {
	private SupportConfigurationProviderWrapper configurationProvider;
	private ESIDataProvider esiDataProvider;
	private ESIDataProviderWrapper esiDataProviderWrapper;
	private CredentialRepositoryWrapper credentialRepository;
	private AuthorizationService authorizationService;

	@Before
	public void setUp() throws Exception {
		this.configurationProvider = new SupportConfigurationProviderWrapper( "default" );
		this.esiDataProvider = Mockito.mock( ESIDataProvider.class );
		this.esiDataProviderWrapper = Mockito.mock( ESIDataProviderWrapper.class );
		Mockito.when( this.esiDataProviderWrapper.getSingleton() ).thenReturn( this.esiDataProvider );
		this.credentialRepository = Mockito.mock( CredentialRepositoryWrapper.class );
		this.authorizationService = new AuthorizationService( this.configurationProvider,
				this.esiDataProviderWrapper,
				this.credentialRepository );
	}

	@Test
	public void validateAuthorizationToken() {
		final GetCharactersCharacterIdOk characterIdOkBlock = Mockito.mock( GetCharactersCharacterIdOk.class );
		Mockito.when( this.esiDataProvider.getCharactersCharacterId( Mockito.anyInt() ) ).thenReturn( characterIdOkBlock );
		Mockito.when( characterIdOkBlock.getCorporationId() ).thenReturn( 98384726 );
		final ValidateAuthorizationTokenRequest validateAuthorizationTokenRequest =
				new ValidateAuthorizationTokenRequest.Builder()
						.withCode( "-VALID-CODE-" )
						.withState( "-ANY-STATE-IS-VALID-" )
						.build();

		final ResponseEntity<ValidateAuthorizationTokenResponse> obtainedResponse = this.authorizationService
				.validateAuthorizationToken( validateAuthorizationTokenRequest );

		Assert.assertNotNull( obtainedResponse );
	}
}
