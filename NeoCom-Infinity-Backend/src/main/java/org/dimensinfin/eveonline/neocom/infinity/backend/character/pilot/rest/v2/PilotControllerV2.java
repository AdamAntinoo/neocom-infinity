package org.dimensinfin.eveonline.neocom.infinity.backend.character.pilot.rest.v2;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import org.dimensinfin.eveonline.neocom.domain.PilotV1;
import org.dimensinfin.eveonline.neocom.infinity.config.security.NeoComAuthenticationProvider;
import org.dimensinfin.eveonline.neocom.infinity.core.rest.NeoComAuthenticatedController;

@RestController
@Validated
@RequestMapping("/api/v2/neocom")
public class PilotControllerV2 extends NeoComAuthenticatedController {
	private final PilotServiceV2 pilotServiceV2;

	// - C O N S T R U C T O R S
	@Autowired
	public PilotControllerV2( @NotNull final NeoComAuthenticationProvider neoComAuthenticationProvider,
	                          @NotNull final PilotServiceV2 pilotServiceV2 ) {
		super( neoComAuthenticationProvider );
		this.pilotServiceV2 = pilotServiceV2;
	}

	@GetMapping(path = "/pilots/{pilotId}",
			consumes = "application/json",
			produces = "application/hal+json")
	public ResponseEntity<PilotV1> getPilotData( @Valid @PathVariable @NotNull final Integer pilotId ) {
		this.validateAuthorizedPilot( pilotId );
		return new ResponseEntity<>( this.pilotServiceV2.getAuthenticatedPilotData( pilotId ), HttpStatus.OK );
	}
}
