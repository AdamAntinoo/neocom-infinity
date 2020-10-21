package org.dimensinfin.eveonline.neocom.infinity.backend.core.rest;

import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RestController;

import org.dimensinfin.eveonline.neocom.infinity.core.exception.NeoComError;
import org.dimensinfin.eveonline.neocom.infinity.core.exception.NeoComRuntimeBackendException;
import org.dimensinfin.eveonline.neocom.infinity.core.exceptions.ErrorInfo;
import org.dimensinfin.eveonline.neocom.infinity.core.security.NeoComAuthenticationProvider;

public abstract class NeoComAuthenticatedController extends NeoComController {
	public static NeoComError errorPILOTIDNOTAUTHORIZED() {
		return new NeoComError.Builder()
				.withErrorName( "PILOT_ID_NOT_AUTHORIZED" )
				.withHttpStatus( HttpStatus.UNAUTHORIZED )
				.withErrorCode( "dimensinfin.neocom.authorization.unauthorized" )
				.withMessage( "The access to the pilot data is not authorized to the requester credential" )
				.build();
	}

	private final NeoComAuthenticationProvider neoComAuthenticationProvider;

	// - C O N S T R U C T O R S
	public NeoComAuthenticatedController( final @NotNull NeoComAuthenticationProvider neoComAuthenticationProvider ) {
		this.neoComAuthenticationProvider = neoComAuthenticationProvider;
	}

	protected void validateAuthorizedPilot( final Integer pilotId ) {
		final Integer authorizedPilotId = this.neoComAuthenticationProvider.getAuthenticatedPilot();
		if (authorizedPilotId.intValue() != pilotId.intValue())
			throw new NeoComRuntimeBackendException( NeoComAuthenticatedController.errorPILOTIDNOTAUTHORIZED() );
	}
}
