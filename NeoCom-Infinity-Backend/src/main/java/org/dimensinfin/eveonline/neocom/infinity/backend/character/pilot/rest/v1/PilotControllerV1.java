package org.dimensinfin.eveonline.neocom.infinity.backend.character.pilot.rest.v1;

import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import org.dimensinfin.eveonline.neocom.character.domain.PilotV1;
import org.dimensinfin.eveonline.neocom.infinity.config.security.NeoComAuthenticationProvider;
import org.dimensinfin.eveonline.neocom.infinity.core.rest.NeoComAuthenticatedController;

/**
 * Controller to the pilot set of endpoints. Those endpoints require authentication and are complementary to the public access endpoints.
 */
@RestController
@Validated
@RequestMapping("/api/v1/neocom")
public class PilotControllerV1 extends NeoComAuthenticatedController {
	private final PilotServiceV1 pilotServiceV1;

	// - C O N S T R U C T O R S
	@Autowired
	public PilotControllerV1( final @NotNull NeoComAuthenticationProvider neoComAuthenticationProvider,
	                          final @NotNull PilotServiceV1 pilotServiceV1 ) {
		super( neoComAuthenticationProvider );
		this.pilotServiceV1 = pilotServiceV1;
	}

	@GetMapping(path = "/pilots/{pilotId}",
			consumes = "application/json",
			produces = "application/json")
	public ResponseEntity<PilotV1> getPilotData( @PathVariable @NotNull final Integer pilotId ) {
		this.validateAuthorizedPilot( pilotId );
		return new ResponseEntity<>( this.pilotServiceV1.getAuthenticatedPilotData( pilotId ), HttpStatus.OK );
	}
}
