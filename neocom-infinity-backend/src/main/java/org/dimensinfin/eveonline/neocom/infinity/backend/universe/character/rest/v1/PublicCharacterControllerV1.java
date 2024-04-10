package org.dimensinfin.eveonline.neocom.infinity.backend.universe.character.rest.v1;

import javax.validation.constraints.NotNull;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import org.dimensinfin.eveonline.neocom.character.domain.PublicPilotV1;
import org.dimensinfin.eveonline.neocom.infinity.backend.character.pilot.rest.v1.PilotServiceV1;

/**
 * @author Adam Antinoo (adamantinoo.git@gmail.com)
 * @since 0.20.0
 */
@RestController
@Validated
@RequestMapping("/api/v1/public")
public class PublicCharacterControllerV1 {
	private final PilotServiceV1 pilotServiceV1;

	// - C O N S T R U C T O R S
	public PublicCharacterControllerV1( @NotNull final PilotServiceV1 pilotServiceV1 ) {
		this.pilotServiceV1 = pilotServiceV1;
	}

	@GetMapping(path = "/pilots/{pilotId}",
			consumes = "application/json",
			produces = "application/json")
	public ResponseEntity<PublicPilotV1> getPilotPublicData( @PathVariable @NotNull final Integer pilotId ) {
		return new ResponseEntity<>( this.pilotServiceV1.getPilotPublicData( pilotId ), HttpStatus.OK );
	}
}