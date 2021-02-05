package org.dimensinfin.eveonline.neocom.infinity.backend.authorization.rest.v1;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import org.dimensinfin.eveonline.neocom.auth.NeoComOAuth2Flow;
import org.dimensinfin.eveonline.neocom.auth.TokenTranslationResponse;
import org.dimensinfin.eveonline.neocom.auth.TokenVerification;
import org.dimensinfin.eveonline.neocom.auth.VerifyCharacterResponse;
import org.dimensinfin.eveonline.neocom.database.repositories.CredentialRepository;
import org.dimensinfin.eveonline.neocom.esiswagger.model.GetCharactersCharacterIdOk;
import org.dimensinfin.eveonline.neocom.infinity.authorization.client.v1.ValidateAuthorizationTokenRequest;
import org.dimensinfin.eveonline.neocom.infinity.authorization.client.v1.ValidateAuthorizationTokenResponse;
import org.dimensinfin.eveonline.neocom.infinity.backend.authorization.domain.AuthenticationStateResponse;
import org.dimensinfin.eveonline.neocom.infinity.config.security.NeoComAuthenticationProvider;
import org.dimensinfin.eveonline.neocom.infinity.service.CookieService;
import org.dimensinfin.eveonline.neocom.infinity.service.JWTTokenService;
import org.dimensinfin.eveonline.neocom.provider.IConfigurationService;
import org.dimensinfin.eveonline.neocom.service.ESIDataService;

public class AuthorizationServiceV1Test {
	private static final Integer TEST_CREDENTIAL_ACCOUNT_ID = 543457;
	private static final String TEST_ENCODED_STATE = "-TEST-ENCODED-STATE-";
	private static final String TEST_CHARACTER_NAME = "-CHARACTER-NAME-";
	private static final String TEST_TOKEN_TYPE = "-TOKEN-TYPE-";
	private static final String TEST_TOKEN_CONTENT = "-TOKEN-CONTENT-";
	private static final String TEST_DATASOURCE = "-DATASOURCE-";
	private static final String TEST_SCOPES = "-SCOPES-";
	private static final String TEST_JWT_TOKEN = "-JWT-TOKEN-";

	private IConfigurationService configurationService;
	private ESIDataService esiDataService;
	private CredentialRepository credentialRepository;
	private NeoComAuthenticationProvider neoComAuthenticationProvider;
	private CookieService cookieService;
	private JWTTokenService jwtTokenService;

	@BeforeEach
	public void beforeEach() {
		this.configurationService = Mockito.mock( IConfigurationService.class );
		this.esiDataService = Mockito.mock( ESIDataService.class );
		this.credentialRepository = Mockito.mock( CredentialRepository.class );
		this.neoComAuthenticationProvider = Mockito.mock( NeoComAuthenticationProvider.class );
		this.cookieService = Mockito.mock( CookieService.class );
		this.jwtTokenService = Mockito.mock( JWTTokenService.class );
	}

	@Test
	public void constructorContract() {
		final AuthorizationServiceV1 authorizationServiceV1 = new AuthorizationServiceV1(
				this.configurationService,
				this.esiDataService,
				this.credentialRepository,
				this.neoComAuthenticationProvider,
				this.cookieService,
				this.jwtTokenService );
		Assertions.assertNotNull( authorizationServiceV1 );
	}

	@Test
	public void validateAuthenticationStateValid() {
		// Given
		final String sourceJWT = "-NOT-AFFECTS-";
		final HttpServletResponse servletResponse = Mockito.mock( HttpServletResponse.class );
		// When
		Mockito.when( this.jwtTokenService.validateToken( Mockito.anyString() ) ).thenReturn( true );
		// Test
		final AuthorizationServiceV1 authorizationServiceV1 = new AuthorizationServiceV1(
				this.configurationService,
				this.esiDataService,
				this.credentialRepository,
				this.neoComAuthenticationProvider,
				this.cookieService,
				this.jwtTokenService );
		final AuthenticationStateResponse obtained = authorizationServiceV1.validateAuthenticationState( sourceJWT, servletResponse );
		// Assertions
		Assertions.assertNotNull( obtained );
		Assertions.assertEquals( AuthenticationStateResponse.AuthenticationStateType.VALID, obtained.getState() );
	}

	@Test
	public void validateAuthorizationTokenSuccess() {
		// Given
		final ValidateAuthorizationTokenRequest validateAuthorizationTokenRequest = Mockito.mock( ValidateAuthorizationTokenRequest.class );
		final NeoComOAuth2Flow flow = Mockito.mock( NeoComOAuth2Flow.class );
		final TokenVerification tokenStore = Mockito.mock( TokenVerification.class );
		final GetCharactersCharacterIdOk characterData = Mockito.mock( GetCharactersCharacterIdOk.class );
		final VerifyCharacterResponse characterResponse = Mockito.mock( VerifyCharacterResponse.class );
		final TokenTranslationResponse token = Mockito.mock( TokenTranslationResponse.class );
		final Cookie cookie = Mockito.mock( Cookie.class );
		// When
		Mockito.when( this.configurationService.getResourceString( Mockito.anyString() ) ).thenReturn( TEST_ENCODED_STATE );
		Mockito.when( validateAuthorizationTokenRequest.getOauthFlow() ).thenReturn( flow );
		Mockito.when( validateAuthorizationTokenRequest.getState() ).thenReturn( "LVRFU1QtRU5DT0RFRC1TVEFURS0=" );
		Mockito.when( flow.verifyState( Mockito.anyString() ) ).thenReturn( Boolean.TRUE );
		Mockito.when( validateAuthorizationTokenRequest.setRunningFlow( Mockito.any( NeoComOAuth2Flow.class ) ) )
				.thenReturn( validateAuthorizationTokenRequest );
		Mockito.when( flow.onTranslationStep() ).thenReturn( tokenStore );
		Mockito.when( this.esiDataService.getCharactersCharacterId( Mockito.anyInt() ) ).thenReturn( characterData );
		Mockito.when( tokenStore.getVerifyCharacterResponse() ).thenReturn( characterResponse );
		Mockito.when( tokenStore.getAccountIdentifier() ).thenReturn( TEST_CREDENTIAL_ACCOUNT_ID );
		Mockito.when( characterResponse.getCharacterName() ).thenReturn( TEST_CHARACTER_NAME );
		Mockito.when( tokenStore.getTokenTranslationResponse() ).thenReturn( token );
		Mockito.when( token.getTokenType() ).thenReturn( TEST_TOKEN_TYPE );
		Mockito.when( token.getAccessToken() ).thenReturn( TEST_TOKEN_CONTENT );
		Mockito.when( token.getRefreshToken() ).thenReturn( TEST_TOKEN_CONTENT );
		Mockito.when( tokenStore.getDataSource() ).thenReturn( TEST_DATASOURCE );
		Mockito.when( tokenStore.getScopes() ).thenReturn( TEST_SCOPES );
		Mockito.when( this.jwtTokenService.createJWTToken( Mockito.anyString(), Mockito.anyInt() ) ).thenReturn( TEST_JWT_TOKEN );
		Mockito.when( this.cookieService.generateCookie( Mockito.anyString() ) ).thenReturn( cookie );
		// Test
		final AuthorizationServiceV1 authorizationServiceV1 = new AuthorizationServiceV1(
				this.configurationService,
				this.esiDataService,
				this.credentialRepository,
				this.neoComAuthenticationProvider,
				this.cookieService,
				this.jwtTokenService );
		final ValidateAuthorizationTokenResponse obtained = authorizationServiceV1.validateAuthorizationToken( validateAuthorizationTokenRequest );
		// Assertions
		Assertions.assertNotNull( obtained );
	}
}