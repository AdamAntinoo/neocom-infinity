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

import org.dimensinfin.eveonline.neocom.domain.Pilot;
import org.dimensinfin.eveonline.neocom.infinity.core.rest.NeoComAuthenticatedController;
import org.dimensinfin.eveonline.neocom.infinity.core.security.NeoComAuthenticationProvider;

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
	public ResponseEntity<Pilot> getPilotData( final @PathVariable @NotNull Integer pilotId ) {
		this.validateAuthorizedPilot( pilotId );
		return new ResponseEntity<>( this.pilotServiceV1.getPilotData( pilotId ), HttpStatus.OK );
	}
}
