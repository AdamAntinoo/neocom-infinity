package org.dimensinfin.eveonline.neocom.infinity.core.rest;

import java.text.MessageFormat;
import java.util.Objects;
import javax.validation.constraints.NotNull;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import org.dimensinfin.eveonline.neocom.database.entities.Credential;
import org.dimensinfin.eveonline.neocom.infinity.config.security.CredentialDetails;
import org.dimensinfin.eveonline.neocom.infinity.config.security.CredentialDetailsService;
import org.dimensinfin.eveonline.neocom.infinity.config.security.NeoComAuthenticationProvider;
import org.dimensinfin.eveonline.neocom.infinity.core.exception.NeoComRestError;
import org.dimensinfin.eveonline.neocom.infinity.core.exception.NeoComRuntimeBackendException;
import org.dimensinfin.logging.LogWrapper;

import static org.dimensinfin.eveonline.neocom.infinity.NeoComInfinityBackendApplication.APPLICATION_ERROR_CODE_PREFIX;

@Deprecated
public abstract class NeoComCredentialService {

	public static NeoComRestError errorTARGETNOTFOUND( final String entityName, final Integer identifier ) {
		return new NeoComRestError.Builder()
				.withErrorName( "TARGET_NOT_FOUND" )
				.withHttpStatus( HttpStatus.NOT_FOUND )
				.withErrorCode( APPLICATION_ERROR_CODE_PREFIX + ".entity.not.found" )
				.withMessage( MessageFormat.format(
						"The entity of class {0} with identifier {1} is not found.",
						entityName, identifier )
				)
				.build();
	}

	public static NeoComRestError Error_CREDENTIALNOTFOUND() {
		return new NeoComRestError.Builder()
				.withErrorName( "CREDENTIAL_NOT_FOUND" )
				.withHttpStatus( HttpStatus.UNAUTHORIZED )
				.withErrorCode( APPLICATION_ERROR_CODE_PREFIX + ".credential.not.found" )
				.withMessage( "There is no valid credential to backup the authentication record." )
				.build();
	}

	protected final NeoComAuthenticationProvider neoComAuthenticationProvider;
	private final CredentialDetailsService credentialDetailsService;

	// - C O N S T R U C T O R S
	public NeoComCredentialService( @NotNull final NeoComAuthenticationProvider neoComAuthenticationProvider,
	                                @NotNull final CredentialDetailsService credentialDetailsService ) {
		this.neoComAuthenticationProvider = neoComAuthenticationProvider;
		this.credentialDetailsService = credentialDetailsService;
	}

	// - G E T T E R S   &   S E T T E R S
	protected Credential getAuthorizedCredential() {
		LogWrapper.enter();
		try {
			final String uniqueId = this.neoComAuthenticationProvider.getAuthenticatedUniqueId();
			try {
				final UserDetails credentialService = Objects.requireNonNull( this.credentialDetailsService.loadUserByUsername( uniqueId ) );
				final Credential credential = Objects.requireNonNull( ((CredentialDetails) credentialService).getCredential() );
				LogWrapper.info( MessageFormat.format( "Credential account: {0} - name: {1}", credential.getAccountId(), credential.getName() ) );
				return credential;
			} catch (final NullPointerException npe) {
				throw new NeoComRuntimeBackendException( Error_CREDENTIALNOTFOUND() );
			}
		} catch (final UsernameNotFoundException unfe) {
			throw new NeoComRuntimeBackendException( unfe.getMessage() );
		} finally {
			LogWrapper.exit();
		}
	}
}
