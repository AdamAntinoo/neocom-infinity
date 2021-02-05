package org.dimensinfin.eveonline.neocom.infinity.backend.authorization.rest.v1;

import java.sql.SQLException;
import java.text.MessageFormat;
import java.util.Objects;
import javax.servlet.http.HttpServletResponse;
import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import org.dimensinfin.annotation.TimeElapsed;
import org.dimensinfin.eveonline.neocom.auth.NeoComOAuth2Flow;
import org.dimensinfin.eveonline.neocom.auth.TokenTranslationResponse;
import org.dimensinfin.eveonline.neocom.auth.TokenVerification;
import org.dimensinfin.eveonline.neocom.database.entities.Credential;
import org.dimensinfin.eveonline.neocom.database.repositories.CredentialRepository;
import org.dimensinfin.eveonline.neocom.esiswagger.model.GetCharactersCharacterIdOk;
import org.dimensinfin.eveonline.neocom.infinity.authorization.client.v1.ValidateAuthorizationTokenRequest;
import org.dimensinfin.eveonline.neocom.infinity.authorization.client.v1.ValidateAuthorizationTokenResponse;
import org.dimensinfin.eveonline.neocom.infinity.backend.authorization.domain.AuthenticationStateResponse;
import org.dimensinfin.eveonline.neocom.infinity.config.security.NeoComAuthenticationProvider;
import org.dimensinfin.eveonline.neocom.infinity.core.exception.NeoComRestError;
import org.dimensinfin.eveonline.neocom.infinity.core.exception.NeoComRuntimeBackendException;
import org.dimensinfin.eveonline.neocom.infinity.service.CookieService;
import org.dimensinfin.eveonline.neocom.infinity.service.JWTTokenService;
import org.dimensinfin.eveonline.neocom.provider.IConfigurationService;
import org.dimensinfin.eveonline.neocom.service.ESIDataService;
import org.dimensinfin.logging.LogWrapper;

@Service
public class AuthorizationServiceV1 {
	public static NeoComRestError errorINVALIDSTATEVERIFICATION() {
		return new NeoComRestError.Builder()
				.withErrorName( "INVALID_STATE_VERIFICATION" )
				.withHttpStatus( HttpStatus.UNAUTHORIZED )
				.withErrorCode( "dimensinfin.neocom.authorization.invalid.state" )
				.withMessage( "Authorization failure because invalid state verification." )
				.build();
	}

	private final IConfigurationService configurationService;
	private final ESIDataService esiDataService;
	private final CredentialRepository credentialRepository;
	private final NeoComAuthenticationProvider neoComAuthenticationProvider;
	private final CookieService cookieService;
	private final JWTTokenService jwtTokenService;

	// - C O N S T R U C T O R S
	@Autowired
	public AuthorizationServiceV1( @NotNull final IConfigurationService configurationService,
	                               @NotNull final ESIDataService esiDataService,
	                               @NotNull final CredentialRepository credentialRepository,
	                               @NotNull final NeoComAuthenticationProvider neoComAuthenticationProvider,
	                               @NotNull final CookieService cookieService,
	                               @NotNull final JWTTokenService jwtTokenService ) {
		this.configurationService = configurationService;
		this.esiDataService = esiDataService;
		this.credentialRepository = credentialRepository;
		this.neoComAuthenticationProvider = neoComAuthenticationProvider;
		this.cookieService = cookieService;
		this.jwtTokenService = jwtTokenService;
	}

	/**
	 * Check the cookie contents. There should contain an valid JWT token.
	 * If the cookie has contents then see it we can create the JWT token. If affirmative then the cookie is valid. If there is any exception
	 * then we can invalidate the cookie and return again the 'not valid' message
	 *
	 * @return the response message depending on the scenario found.
	 */
	public AuthenticationStateResponse validateAuthenticationState( final String sourceJWT, final HttpServletResponse response ) {
		LogWrapper.enter( sourceJWT );
		if (this.jwtTokenService.validateToken( sourceJWT )) {
			// Create a new cookie with a new expiration time.
			response.addCookie( this.cookieService.generateCookie( sourceJWT ) );
			return new AuthenticationStateResponse.Builder().withState( AuthenticationStateResponse.AuthenticationStateType.VALID ).build();
		} else
			return new AuthenticationStateResponse.Builder().withState( AuthenticationStateResponse.AuthenticationStateType.NOT_VALID ).build();
	}

	@TimeElapsed
	public ValidateAuthorizationTokenResponse validateAuthorizationToken( final ValidateAuthorizationTokenRequest validateAuthorizationTokenRequest ) {
		LogWrapper.enter();
		final NeoComOAuth2Flow oauthFlow = new NeoComOAuth2Flow.Builder()
				.withConfigurationService( this.configurationService )
				.build();
		validateAuthorizationTokenRequest.setRunningFlow( oauthFlow );
		this.verifyState( validateAuthorizationTokenRequest ); // Check if the state matches the backend state configured.
		final TokenVerification tokenStore = this.verifyCharacter( validateAuthorizationTokenRequest ); // Validate pointer character is valid.
		final GetCharactersCharacterIdOk pilotData = this.esiDataService.getCharactersCharacterId(
				tokenStore.getAccountIdentifier()
		);
		// - C R E D E N T I A L
		// Create and persist the credential. Do an update if it already exists.
		LogWrapper.info( "Creating Credential..." );
		final TokenTranslationResponse token = tokenStore.getTokenTranslationResponse();
		final Credential credential = new Credential.Builder( tokenStore.getAccountIdentifier() )
				.withAccountName( tokenStore.getVerifyCharacterResponse().getCharacterName() )
				.withCorporationId( pilotData.getCorporationId() )
				.withTokenType( token.getTokenType() )
				.withAccessToken( token.getAccessToken() )
				.withRefreshToken( token.getRefreshToken() )
				.withDataSource( tokenStore.getDataSource() )
				.withScope( tokenStore.getScopes() )
				.build();
		try {
			this.credentialRepository.persist( credential );
		} catch (final SQLException sqle) {
			throw new NeoComRuntimeBackendException( NeoComRuntimeBackendException.errorUNEXPECTEDSQLEXCEPTION( sqle ) );
		}
		LogWrapper.info( MessageFormat.format( "Credential #{0}-{1} created successfully.",
				credential.getAccountId() + "", credential.getAccountName() ) );
		// TODO - Seems the updated enters endless loop. Review later.
		//				new CredentialUpdater( credential ).onRun();
		//			UpdaterJobManager.submit( new CredentialUpdater( credential ) ); // Post the update request to the scheduler.

		// - J W T T O K E N
		final String jwtToken = this.jwtTokenService.createJWTToken( credential.getUniqueCredential(), pilotData.getCorporationId() );

		// - R E S P O N S E
		try {
			return new ValidateAuthorizationTokenResponse.Builder()
					.withCredential( credential )
					.withJwtToken( jwtToken )
					.withCookie( this.cookieService.generateCookie( jwtToken ) )
					.build();
		} finally {
			LogWrapper.exit();
		}
	}

	private TokenVerification verifyCharacter( final ValidateAuthorizationTokenRequest validateAuthorizationTokenRequest ) {
		LogWrapper.enter();
		final NeoComOAuth2Flow oauthFlow = validateAuthorizationTokenRequest.getOauthFlow();
		try {
			final TokenVerification tokenStore = oauthFlow.onTranslationStep();
			return Objects.requireNonNull( tokenStore );
		} finally {
			LogWrapper.exit( "Character verification: OK." );
		}
	}

	private void verifyState( final ValidateAuthorizationTokenRequest validateAuthorizationTokenRequest ) {
		LogWrapper.enter();
		validateAuthorizationTokenRequest.getOauthFlow().onStartFlow( validateAuthorizationTokenRequest.getCode(),
				validateAuthorizationTokenRequest.getState(),
				validateAuthorizationTokenRequest.getDataSourceName() );
		if (!validateAuthorizationTokenRequest.getOauthFlow().verifyState( validateAuthorizationTokenRequest.getState() ))
			throw new NeoComRuntimeBackendException( errorINVALIDSTATEVERIFICATION() );
		LogWrapper.exit( "Calling state verification: OK." );
	}
}
