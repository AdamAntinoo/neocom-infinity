package org.dimensinfin.eveonline.neocom.infinity.core.rest;

import javax.validation.constraints.NotNull;

import org.springframework.http.HttpStatus;

import org.dimensinfin.eveonline.neocom.infinity.config.security.NeoComAuthenticationProvider;
import org.dimensinfin.eveonline.neocom.infinity.core.exception.NeoComRestError;
import org.dimensinfin.eveonline.neocom.infinity.core.exception.NeoComRuntimeBackendException;

public abstract class NeoComAuthenticatedController {
	public static NeoComRestError errorPILOTIDNOTAUTHORIZED() {
		return new NeoComRestError.Builder()
				.withErrorName( "PILOT_ID_NOT_AUTHORIZED" )
				.withHttpStatus( HttpStatus.UNAUTHORIZED )
				.withErrorCode( "dimensinfin.neocom.authorization.unauthorized" )
				.withMessage( "The access to the pilot data is not authorized to the requester credential" )
				.build();
	}

	private final NeoComAuthenticationProvider neoComAuthenticationProvider;

	// - C O N S T R U C T O R S
	public NeoComAuthenticatedController( @NotNull final NeoComAuthenticationProvider neoComAuthenticationProvider ) {
		this.neoComAuthenticationProvider = neoComAuthenticationProvider;
	}

	protected void validateAuthorizedPilot( final Integer pilotId ) {
		final Integer authorizedPilotId = this.neoComAuthenticationProvider.getAuthenticatedPilot();
		if (authorizedPilotId.intValue() != pilotId.intValue())
			throw new NeoComRuntimeBackendException( NeoComAuthenticatedController.errorPILOTIDNOTAUTHORIZED() );
	}
}
