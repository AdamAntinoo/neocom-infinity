package org.dimensinfin.eveonline.neocom.infinity.authorization.rest.v1;

import java.io.UnsupportedEncodingException;
import java.sql.SQLException;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.dimensinfin.eveonline.neocom.annotation.LogEnterExit;
import org.dimensinfin.eveonline.neocom.database.entities.Credential;
import org.dimensinfin.eveonline.neocom.database.repositories.CredentialRepository;
import org.dimensinfin.eveonline.neocom.infinity.adapter.CredentialRepositoryWrapper;
import org.dimensinfin.eveonline.neocom.infinity.authorization.client.v1.StoreCredentialRequest;
import org.dimensinfin.eveonline.neocom.infinity.authorization.client.v1.StoreCredentialResponse;
import org.dimensinfin.eveonline.neocom.infinity.core.exceptions.ErrorInfo;
import org.dimensinfin.eveonline.neocom.infinity.core.exceptions.NeoComSBException;
import org.dimensinfin.eveonline.neocom.service.logger.NeoComLogger;

import static org.dimensinfin.eveonline.neocom.infinity.core.security.SecurityConstants.ISSUER;
import static org.dimensinfin.eveonline.neocom.infinity.core.security.SecurityConstants.SECRET;
import static org.dimensinfin.eveonline.neocom.infinity.core.security.SecurityConstants.SUBJECT;
import static org.dimensinfin.eveonline.neocom.infinity.core.security.SecurityConstants.TOKEN_ACCOUNT_NAME_FIELD_NAME;
import static org.dimensinfin.eveonline.neocom.infinity.core.security.SecurityConstants.TOKEN_CORPORATION_ID_FIELD_NAME;
import static org.dimensinfin.eveonline.neocom.infinity.core.security.SecurityConstants.TOKEN_PILOT_ID_FIELD_NAME;
import static org.dimensinfin.eveonline.neocom.infinity.core.security.SecurityConstants.TOKEN_UNIQUE_IDENTIFIER_FIELD_NAME;

@Service
public class StoreCredentialService {
	private final CredentialRepository credentialRepository;

	@Autowired
	public StoreCredentialService( final CredentialRepositoryWrapper credentialRepository ) {
		this.credentialRepository = credentialRepository.getSingleton();
	}

	@LogEnterExit
	public StoreCredentialResponse storeCredential( final StoreCredentialRequest storeCredentialRequest ) {
		try {
			final Credential credential = storeCredentialRequest.getCredential();
			this.credentialRepository.persist( credential );
			final String jwtToken = JWT.create()
					.withIssuer( ISSUER )
					.withSubject( SUBJECT )
					.withClaim( TOKEN_UNIQUE_IDENTIFIER_FIELD_NAME, credential.getUniqueCredential() )
					.withClaim( TOKEN_ACCOUNT_NAME_FIELD_NAME, credential.getAccountName() )
					.withClaim( TOKEN_CORPORATION_ID_FIELD_NAME, credential.getCorporationId() )
					.withClaim( TOKEN_PILOT_ID_FIELD_NAME, credential.getAccountId() )
					.sign( Algorithm.HMAC512( SECRET ) );
			return new StoreCredentialResponse.Builder()
					.withJwtToken( jwtToken )
					.build();
		} catch (final SQLException | UnsupportedEncodingException sqle) {
			NeoComLogger.error( sqle );
			final Throwable cause = sqle.getCause(); // Cause can be null.
			if (null != cause)
				throw new NeoComSBException( ErrorInfo.CREDENTIAL_STORE_REPOSITORY_FAILURE.getErrorMessage( sqle.getCause().toString() ) );
			else
				throw new NeoComSBException( ErrorInfo.CREDENTIAL_STORE_REPOSITORY_FAILURE.getErrorMessage( sqle.getMessage() ) );
		}
	}
}
