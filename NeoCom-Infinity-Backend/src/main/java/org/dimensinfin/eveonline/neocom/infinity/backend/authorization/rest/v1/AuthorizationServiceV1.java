package org.dimensinfin.eveonline.neocom.infinity.backend.authorization.rest.v1;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.sql.SQLException;
import java.text.MessageFormat;
import java.util.Objects;
import javax.servlet.http.HttpSession;
import javax.validation.constraints.NotNull;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.codec.binary.Base64;
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
import org.dimensinfin.eveonline.neocom.infinity.backend.authorization.domain.CookieStateResponse;
import org.dimensinfin.eveonline.neocom.infinity.config.security.JwtPayload;
import org.dimensinfin.eveonline.neocom.infinity.config.security.NeoComAuthenticationProvider;
import org.dimensinfin.eveonline.neocom.infinity.core.exception.NeoComRestError;
import org.dimensinfin.eveonline.neocom.infinity.core.exception.NeoComRuntimeBackendException;
import org.dimensinfin.eveonline.neocom.infinity.core.exception.NeoComSBException;
import org.dimensinfin.eveonline.neocom.infinity.service.CookieService;
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
public class AuthorizationServiceV1 {
	public static final ObjectMapper jsonMapper = new ObjectMapper();
	private static final String JWTTOKEN_SESSION_FIELD_NAME = "JWTToken";

	public static NeoComRestError errorINVALIDSTATEVERIFICATION() {
		return new NeoComRestError.Builder()
				.withErrorName( "INVALID_STATE_VERIFICATION" )
				.withHttpStatus( HttpStatus.UNAUTHORIZED )
				.withErrorCode( "dimensinfin.neocom.authorization.invalid.state" )
				.withMessage( "Authorization failure because invalid state verification." )
				.build();
	}

	public static NeoComRestError errorINVALIDTOKENCREATION( final Exception exception ) {
		return new NeoComRestError.Builder()
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
	private final ESIDataService esiDataService;
	private final CredentialRepository credentialRepository;
	private final HttpSession session;
	private final NeoComAuthenticationProvider neoComAuthenticationProvider;
	private final CookieService cookieService;

	// - C O N S T R U C T O R S
	@Autowired
	public AuthorizationServiceV1( @NotNull final IConfigurationService configurationService,
	                               @NotNull final ESIDataService esiDataService,
	                               @NotNull final CredentialRepository credentialRepository,
	                               @NotNull final HttpSession session,
	                               @NotNull final NeoComAuthenticationProvider neoComAuthenticationProvider,
	                               @NotNull final CookieService cookieService ) {
		this.configurationService = configurationService;
		this.esiDataService = esiDataService;
		this.credentialRepository = credentialRepository;
		this.session = session;
		this.neoComAuthenticationProvider = neoComAuthenticationProvider;
		this.cookieService = cookieService;
	}

	/**
	 * Check the cookie contents. There should contain an valid JWT token.
	 * If the cookie has contents then see it we can create the JWT token. If affirmative then the cookie is valid. If there is any exception
	 * then we can invalidate the cookie and return again the 'not valid' message
	 *
	 * @return the response message depending on the scenario found.
	 */
	public CookieStateResponse validateAuthenticationCookie( final String sourceJWT ) {
		// Try to update the token.
		final String jwtToken = this.createJWTToken( this.accessDecodedPayload( sourceJWT ).getUniqueId() );
		return new CookieStateResponse.Builder().withState( CookieStateResponse.SessionStateType.VALID ).build();
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
		final GetCharactersCharacterIdOk pilotData = this.esiDataService.getCharactersCharacterId(
				tokenStore.getAccountIdentifier()
		);
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
			//			final String jwtToken = JWT.create()
			//					.withIssuer( ISSUER )
			//					.withSubject( SUBJECT )
			//					.withClaim( TOKEN_UNIQUE_IDENTIFIER_FIELD_NAME, credential.getUniqueCredential() )
			//					.withClaim( TOKEN_ACCOUNT_NAME_FIELD_NAME, credential.getAccountName() )
			//					.withClaim( TOKEN_CORPORATION_ID_FIELD_NAME, pilotData.getCorporationId() )
			//					.withClaim( TOKEN_PILOT_ID_FIELD_NAME, credential.getAccountId() )
			//					.sign( Algorithm.HMAC512( SECRET ) );
			final String jwtToken = this.createJWTToken( credential.getUniqueCredential() );
			return new ValidateAuthorizationTokenResponse.Builder()
					.withCredential( credential )
					.withJwtToken( jwtToken )
					.withCookie( this.cookieService.generateCookie( jwtToken ) )
					.build();
			//		} catch (final UnsupportedEncodingException uce) {
			//			throw new NeoComRuntimeBackendException( errorINVALIDTOKENCREATION( uce ) );
		} finally {
			LogWrapper.exit();
		}
	}

	private JwtPayload accessDecodedPayload( final String payloadDataEncoded ) {
		try {
			final String[] split_string = payloadDataEncoded.split( "\\." );
			final String base64EncodedHeader = split_string[0];
			final String base64EncodedBody = split_string[1];
			final String base64EncodedSignature = split_string[2];
			final String payloadData = new String( Base64.decodeBase64( base64EncodedBody.getBytes() ) );
			final JwtPayload payload = jsonMapper.readValue( payloadData, JwtPayload.class );
			if (null == payload) throw new NeoComSBException( "configuredError" );
			return payload;
		} catch (final IOException ioe) {
			throw new NeoComSBException( ioe );
		}
	}

	private String createJWTToken( final String uniqueId ) {
		try {
			final Credential credential = this.credentialRepository.findCredentialById( uniqueId );
			return JWT.create()
					.withIssuer( ISSUER )
					.withSubject( SUBJECT )
					.withClaim( TOKEN_UNIQUE_IDENTIFIER_FIELD_NAME, credential.getUniqueCredential() )
					.withClaim( TOKEN_ACCOUNT_NAME_FIELD_NAME, credential.getAccountName() )
					.withClaim( TOKEN_CORPORATION_ID_FIELD_NAME, 123456 )
					.withClaim( TOKEN_PILOT_ID_FIELD_NAME, credential.getAccountId() )
					.sign( Algorithm.HMAC512( SECRET ) );
		} catch (final UnsupportedEncodingException | SQLException uce) {
			throw new NeoComRuntimeBackendException( errorINVALIDTOKENCREATION( uce ) );
		}
	}

	private void updateSession( final String jwtToken ) {
		this.session.setAttribute( JWTTOKEN_SESSION_FIELD_NAME, jwtToken );
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
