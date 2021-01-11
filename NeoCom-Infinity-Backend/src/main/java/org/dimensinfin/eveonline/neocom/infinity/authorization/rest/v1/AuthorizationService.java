package org.dimensinfin.eveonline.neocom.infinity.authorization.rest.v1;

import java.io.UnsupportedEncodingException;
import java.sql.SQLException;
import java.text.MessageFormat;
import java.util.Objects;
import javax.validation.constraints.NotNull;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
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
import org.dimensinfin.eveonline.neocom.infinity.core.exception.NeoComError;
import org.dimensinfin.eveonline.neocom.infinity.core.exception.NeoComRuntimeBackendException;
import org.dimensinfin.eveonline.neocom.provider.ESIDataProvider;
import org.dimensinfin.eveonline.neocom.provider.IConfigurationService;
import org.dimensinfin.eveonline.neocom.service.ESIDataService;
import org.dimensinfin.logging.LogWrapper;

import static org.dimensinfin.eveonline.neocom.infinity.config.security.SecurityConstants.ISSUER;
import static org.dimensinfin.eveonline.neocom.infinity.config.security.SecurityConstants.SECRET;
import static org.dimensinfin.eveonline.neocom.infinity.config.security.SecurityConstants.SUBJECT;
import static org.dimensinfin.eveonline.neocom.infinity.config.security.SecurityConstants.TOKEN_ACCOUNT_NAME_FIELD_NAME;
import static org.dimensinfin.eveonline.neocom.infinity.config.security.SecurityConstants.TOKEN_CORPORATION_ID_FIELD_NAME;
import static org.dimensinfin.eveonline.neocom.infinity.config.security.SecurityConstants.TOKEN_PILOT_ID_FIELD_NAME;
import static org.dimensinfin.eveonline.neocom.infinity.config.security.SecurityConstants.TOKEN_UNIQUE_IDENTIFIER_FIELD_NAME;

@Service
public class AuthorizationService {
	public static NeoComError errorINVALIDSTATEVERIFICATION() {
		return new NeoComError.Builder()
				.withErrorName( "INVALID_STATE_VERIFICATION" )
				.withHttpStatus( HttpStatus.UNAUTHORIZED )
				.withErrorCode( "dimensinfin.neocom.authorization.invalid.state" )
				.withMessage( "Authorization failure because invalid state verification." )
				.build();
	}

	public static NeoComError errorINVALIDTOKENCREATION( final Exception exception ) {
		return new NeoComError.Builder()
				.withErrorName( "INVALID_TOKEN_CREATION" )
				.withHttpStatus( HttpStatus.UNAUTHORIZED )
				.withErrorCode( "dimensinfin.neocom.authorization.validation" )
				.withMessage( MessageFormat.format(
						"Unable to complete authentication call because token contents missed validation. {0}",
						exception.getMessage() )
				)
				.build();
	}

	private final IConfigurationService configurationService;
	private final ESIDataProvider esiDataAdapter;
	private final CredentialRepository credentialRepository;

	// - C O N S T R U C T O R S
	@Autowired
	public AuthorizationService( @NotNull final IConfigurationService configurationService,
	                             @NotNull final ESIDataService esiDataService,
	                             @NotNull final CredentialRepository credentialRepository ) {
		this.configurationService = configurationService;
		this.esiDataAdapter = esiDataService;
		this.credentialRepository = credentialRepository;
	}

	@TimeElapsed
	public ValidateAuthorizationTokenResponse validateAuthorizationToken( final ValidateAuthorizationTokenRequest validateAuthorizationTokenRequest ) {
		LogWrapper.enter();
		final NeoComOAuth2Flow oauthFlow = new NeoComOAuth2Flow.Builder()
				.withConfigurationService( this.configurationService )
				.build();
		validateAuthorizationTokenRequest.setRunningFlow( oauthFlow );
		this.verifyState( validateAuthorizationTokenRequest ); // Check if the state matches the backend state configured.
		final TokenVerification tokenStore = this.verifyCharacter( oauthFlow );
		final GetCharactersCharacterIdOk pilotData = this.esiDataAdapter.getCharactersCharacterId(
				tokenStore.getAccountIdentifier() );
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
		try {
			final String jwtToken = JWT.create()
					.withIssuer( ISSUER )
					.withSubject( SUBJECT )
					.withClaim( TOKEN_UNIQUE_IDENTIFIER_FIELD_NAME, credential.getUniqueCredential() )
					.withClaim( TOKEN_ACCOUNT_NAME_FIELD_NAME, credential.getAccountName() )
					.withClaim( TOKEN_CORPORATION_ID_FIELD_NAME, pilotData.getCorporationId() )
					.withClaim( TOKEN_PILOT_ID_FIELD_NAME, credential.getAccountId() )
					.sign( Algorithm.HMAC512( SECRET ) );
			return new ValidateAuthorizationTokenResponse.Builder()
					.withCredential( credential )
					.withJwtToken( jwtToken )
					.build();
		} catch (final UnsupportedEncodingException uce) {
			throw new NeoComRuntimeBackendException( errorINVALIDTOKENCREATION( uce ) );
		} finally {
			LogWrapper.exit();
		}
	}

	private TokenVerification verifyCharacter( final NeoComOAuth2Flow oauthFlow ) {
		LogWrapper.enter();
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
