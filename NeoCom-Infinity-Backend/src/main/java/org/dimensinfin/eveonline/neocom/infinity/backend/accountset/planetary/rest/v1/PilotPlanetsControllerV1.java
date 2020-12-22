package org.dimensinfin.eveonline.neocom.infinity.backend.accountset.planetary.rest.v1;

import java.util.List;
import javax.validation.constraints.NotNull;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import org.dimensinfin.eveonline.neocom.infinity.backend.accountset.planetary.domain.ColonyDto;
import org.dimensinfin.eveonline.neocom.infinity.backend.core.rest.NeoComAuthenticatedController;
import org.dimensinfin.eveonline.neocom.infinity.core.security.NeoComAuthenticationProvider;
import org.dimensinfin.eveonline.neocom.planetary.ColonyPack;
import org.dimensinfin.eveonline.neocom.planetary.domain.Colony;

@RestController
@Validated
@RequestMapping("/api/v2/neocom")
public class PilotPlanetsControllerV1 extends NeoComAuthenticatedController {
	private final PilotPlanetsServiceV1 pilotPlanetsServiceV1;

	public PilotPlanetsControllerV1( final @NotNull NeoComAuthenticationProvider neoComAuthenticationProvider,
	                                 final @NotNull PilotPlanetsServiceV1 pilotPlanetsServiceV1 ) {
		super( neoComAuthenticationProvider );
		this.pilotPlanetsServiceV1 = pilotPlanetsServiceV1;
	}

	@GetMapping(path = "/pilots/{pilotId}/planets",
			consumes = "application/json",
			produces = "application/json")
	public ResponseEntity<List<ColonyDto>> getPilotPlanets( final @PathVariable @NotNull Integer pilotId ) {
		this.validateAuthorizedPilot( pilotId );
		return new ResponseEntity<>( this.pilotPlanetsServiceV1.getPilotPlanets( pilotId ), HttpStatus.OK );
	}
}
