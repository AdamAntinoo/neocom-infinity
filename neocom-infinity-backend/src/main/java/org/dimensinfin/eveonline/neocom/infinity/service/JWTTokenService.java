package org.dimensinfin.eveonline.neocom.infinity.service;

import java.io.IOException;
import java.sql.SQLException;
import java.text.MessageFormat;
import javax.validation.constraints.NotNull;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import org.dimensinfin.eveonline.neocom.database.entities.Credential;
import org.dimensinfin.eveonline.neocom.database.repositories.CredentialRepository;
import org.dimensinfin.eveonline.neocom.infinity.config.security.JwtPayload;
import org.dimensinfin.eveonline.neocom.infinity.core.exception.NeoComRestError;
import org.dimensinfin.eveonline.neocom.infinity.core.exception.NeoComRuntimeBackendException;

import static org.dimensinfin.eveonline.neocom.infinity.config.security.SecurityConstants.ISSUER;
import static org.dimensinfin.eveonline.neocom.infinity.config.security.SecurityConstants.SECRET;
import static org.dimensinfin.eveonline.neocom.infinity.config.security.SecurityConstants.SUBJECT;
import static org.dimensinfin.eveonline.neocom.infinity.config.security.SecurityConstants.TOKEN_ACCOUNT_NAME_FIELD_NAME;
import static org.dimensinfin.eveonline.neocom.infinity.config.security.SecurityConstants.TOKEN_CORPORATION_ID_FIELD_NAME;
import static org.dimensinfin.eveonline.neocom.infinity.config.security.SecurityConstants.TOKEN_PILOT_ID_FIELD_NAME;
import static org.dimensinfin.eveonline.neocom.infinity.config.security.SecurityConstants.TOKEN_UNIQUE_IDENTIFIER_FIELD_NAME;

/**
 * @author Adam Antinoo (adamantinoo.git@gmail.com)
 * @since 0.20.0
 */
@Service
public class JWTTokenService {
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

	public static NeoComRestError errorINVALIDTOKEN( final Exception exception ) {
		return new NeoComRestError.Builder()
				.withErrorName( "INVALID_TOKEN" )
				.withHttpStatus( HttpStatus.UNAUTHORIZED )
				.withErrorCode( "dimensinfin.neocom.authorization.validation" )
				.withMessage( MessageFormat.format(
						"JWT token is not valid and caanot access payload. {0}",
						exception.getMessage() )
				)
				.build();
	}

	private final CredentialRepository credentialRepository;

	// - C O N S T R U C T O R S
	@Autowired
	public JWTTokenService( @NotNull final CredentialRepository credentialRepository ) {this.credentialRepository = credentialRepository;}

	public String createJWTToken( final String uniqueId, final Integer corporationId ) {
		try {
			final Credential credential = this.credentialRepository.findCredentialById( uniqueId );
			return JWT.create()
					.withIssuer( ISSUER )
					.withSubject( SUBJECT )
					.withClaim( TOKEN_UNIQUE_IDENTIFIER_FIELD_NAME, credential.getUniqueCredential() )
					.withClaim( TOKEN_ACCOUNT_NAME_FIELD_NAME, credential.getAccountName() )
					.withClaim( TOKEN_CORPORATION_ID_FIELD_NAME, corporationId )
					.withClaim( TOKEN_PILOT_ID_FIELD_NAME, credential.getAccountId() )
					.sign( Algorithm.HMAC512( SECRET ) );
		} catch (final IllegalArgumentException | SQLException uce) {
			throw new NeoComRuntimeBackendException( errorINVALIDTOKENCREATION( uce ) );
		}
	}

	public JwtPayload extractPayload( final String payloadDataEncoded ) {
		return this.accessDecodedPayload( payloadDataEncoded );
	}

	public boolean validateToken( final String sourceJWT ) {
		try {
			this.accessDecodedPayload( sourceJWT );
		} catch (final RuntimeException rte) {
			return false;
		}
		return true;
	}

	private JwtPayload accessDecodedPayload( final String payloadDataEncoded ) {
		try {
			final String[] splitSJWTFields = payloadDataEncoded.split( "\\." );
			if (this.validateTokenHeader( splitSJWTFields[0] )) { // Validate the header contents
				final String base64EncodedBody = splitSJWTFields[1];
				final String payloadData = new String( Base64.decodeBase64( base64EncodedBody.getBytes() ) );
				final JwtPayload payload = new ObjectMapper().readValue( payloadData, JwtPayload.class );
				if (null == payload)
					throw new NeoComRuntimeBackendException( errorINVALIDTOKEN( new Exception( "JWT token malformed payload." ) ) );
				return payload;
			} else throw new NeoComRuntimeBackendException( errorINVALIDTOKEN( new Exception( "JWT token header is malformed. Invalid token" ) ) );
		} catch (final IOException ioe) {
			throw new NeoComRuntimeBackendException( errorINVALIDTOKEN( ioe ) );
		}
	}

	private boolean validateTokenHeader( final String headerEncoded ) {
		final String headerData = new String( Base64.decodeBase64( headerEncoded.getBytes() ) );
		return (headerData.equals( "{\"typ\":\"JWT\",\"alg\":\"HS512\"}" ));
	}
}