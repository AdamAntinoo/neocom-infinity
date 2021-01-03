package org.dimensinfin.eveonline.neocom.infinity.core.rest;

import java.text.MessageFormat;
import java.util.Objects;
import javax.validation.constraints.NotNull;

import org.springframework.http.HttpStatus;

import org.dimensinfin.eveonline.neocom.database.entities.Credential;
import org.dimensinfin.eveonline.neocom.infinity.adapter.ESIDataProviderWrapper;
import org.dimensinfin.eveonline.neocom.infinity.core.exception.NeoComError;
import org.dimensinfin.eveonline.neocom.infinity.core.security.CredentialDetails;
import org.dimensinfin.eveonline.neocom.infinity.core.security.CredentialDetailsService;
import org.dimensinfin.eveonline.neocom.infinity.core.security.NeoComAuthenticationProvider;
import org.dimensinfin.eveonline.neocom.provider.ESIDataProvider;

public abstract class NeoComAuthenticatedService {

	public static NeoComError errorTARGETNOTFOUND( final String entityName, final Integer identifier ) {
		return new NeoComError.Builder()
				.withErrorName( "TARGET_NOT_FOUND" )
				.withHttpStatus( HttpStatus.NOT_FOUND )
				.withErrorCode( "dimensinfin.neocom.entity.not.found" )
				.withMessage( MessageFormat.format(
						"The entity of class {0} with identifier {1} is not found.",
						entityName, identifier ) )
				.build();
	}

	protected final ESIDataProvider esiDataProvider;
	protected final NeoComAuthenticationProvider neoComAuthenticationProvider;
	private final CredentialDetailsService credentialDetailsService;

	// - C O N S T R U C T O R S
	public NeoComAuthenticatedService( final @NotNull ESIDataProviderWrapper esiDataProviderWrapper,
	                                   final @NotNull NeoComAuthenticationProvider neoComAuthenticationProvider,
	                                   final @NotNull CredentialDetailsService credentialDetailsService ) {
		this.esiDataProvider = Objects.requireNonNull( esiDataProviderWrapper.getSingleton() );
		this.neoComAuthenticationProvider = neoComAuthenticationProvider;
		this.credentialDetailsService = credentialDetailsService;
	}

	// - G E T T E R S   &   S E T T E R S
	protected Credential getAuthorizedCredential() {
		final String uniqueId = this.neoComAuthenticationProvider.getAuthenticatedUniqueId();
		return ((CredentialDetails) this.credentialDetailsService.loadUserByUsername( uniqueId )).getCredential();
	}
}
