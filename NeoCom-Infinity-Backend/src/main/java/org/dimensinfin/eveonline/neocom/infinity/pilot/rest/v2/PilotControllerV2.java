package org.dimensinfin.eveonline.neocom.infinity.pilot.rest.v2;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import org.dimensinfin.eveonline.neocom.infinity.core.exception.NeoComError;
import org.dimensinfin.eveonline.neocom.infinity.core.exception.NeoComRuntimeBackendException;
import org.dimensinfin.eveonline.neocom.infinity.core.security.NeoComAuthenticationProvider;
import org.dimensinfin.eveonline.neocom.infinity.pilot.rest.representation.PilotModel;

@RestController
@Validated
@RequestMapping("/api/v2/neocom")
public class PilotControllerV2 {
	private static NeoComError errorPILOTIDNOTAUTHORIZED() {
		return new NeoComError.Builder()
				.withErrorName( "PILOT_ID_NOT_AUTHORIZED" )
				.withHttpStatus( HttpStatus.UNAUTHORIZED )
				.withErrorCode( "dimensinfin.neocom.authorization.unauthorized" )
				.withMessage( "The access to the pilot data is not authorized to the requester credential" )
				.build();
	}

	private final PilotServiceV2 pilotServiceV2;
	private final NeoComAuthenticationProvider neoComAuthenticationProvider;

	// - C O N S T R U C T O R S
	public PilotControllerV2( @NotNull final PilotServiceV2 pilotServiceV2,
	                          @NotNull final NeoComAuthenticationProvider neoComAuthenticationProvider ) {
		this.pilotServiceV2 = pilotServiceV2;
		this.neoComAuthenticationProvider = neoComAuthenticationProvider;
	}
	@GetMapping(path = "/pilots/{pilotId}",
			consumes = "application/hal+json",
			produces = "application/hal+json")
	public ResponseEntity<PilotModel> getPilotData( @Valid @PathVariable final Integer pilotId ) {
		this.validateAuthorizedPilot( pilotId );
		return  new ResponseEntity<>( this.pilotServiceV2.getPilotData( pilotId ), HttpStatus.OK );
	}

	private void validateAuthorizedPilot( final Integer pilotId ) {
		final Integer authorizedPilotId = this.neoComAuthenticationProvider.getAuthenticatedPilot();
		if (authorizedPilotId.intValue() != pilotId.intValue())
			throw new NeoComRuntimeBackendException( errorPILOTIDNOTAUTHORIZED() );
	}
}
