package org.dimensinfin.eveonline.neocom.infinity.backend.character.fitting.rest.v2;

import java.util.List;
import javax.validation.constraints.NotNull;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import org.dimensinfin.eveonline.neocom.infinity.backend.character.fitting.domain.FittingModel;
import org.dimensinfin.eveonline.neocom.infinity.core.rest.NeoComAuthenticatedController;
import org.dimensinfin.eveonline.neocom.infinity.config.security.NeoComAuthenticationProvider;

@RestController
@Validated
@RequestMapping("/api/v2/neocom")
public class PilotFittingsControllerV2 extends NeoComAuthenticatedController {
	private final PilotFittingsServiceV2 pilotFittingsServiceV2;

	// - C O N S T R U C T O R S
	public PilotFittingsControllerV2( final @NotNull NeoComAuthenticationProvider neoComAuthenticationProvider,
	                                  final @NotNull PilotFittingsServiceV2 pilotFittingsServiceV2 ) {
		super( neoComAuthenticationProvider );
		this.pilotFittingsServiceV2 = pilotFittingsServiceV2;
	}

	@GetMapping(path = "/pilots/{pilotId}/fittings",
			consumes = "application/json",
			produces = "application/json")
	public ResponseEntity<List<FittingModel>> getPilotFittings( final @PathVariable @NotNull Integer pilotId ) {
		this.validateAuthorizedPilot( pilotId );
		return new ResponseEntity<>( this.pilotFittingsServiceV2.getPilotFittings( pilotId ), HttpStatus.OK );
	}
}
