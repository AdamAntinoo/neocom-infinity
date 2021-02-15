package org.dimensinfin.eveonline.neocom.infinity.backend.character.fitting.rest.v1;

import java.util.List;
import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import org.dimensinfin.eveonline.neocom.domain.Fitting;
import org.dimensinfin.eveonline.neocom.infinity.config.security.NeoComAuthenticationProvider;
import org.dimensinfin.eveonline.neocom.infinity.core.exception.NeoComRuntimeBackendException;
import org.dimensinfin.eveonline.neocom.infinity.core.rest.NeoComController;
import org.dimensinfin.eveonline.neocom.infinity.fitting.FittingService;

/**
 * This controller will be responsible to manage the Fitting list and all the Fitting management duties like the registration
 * of new fitting requests of the processing for a fitting into the list of actions and tasks that have to be accomplished to
 * complete a request.
 *
 * Fitting Requests are volatile and should be checked for completion of associated jobs and logistics. While the Pilot
 * download for such data is responsibility of the Pilot controller all data matching and request processing responsibility for
 * this controller.
 *
 * @author Adam Antinoo
 */
@RestController
@CrossOrigin
@Validated
@RequestMapping("/api/v1/neocom")
public class FittingsControllerV1 extends NeoComController {
	private final FittingService fittingService;
	private final NeoComAuthenticationProvider neoComAuthenticationProvider;

// - C O N S T R U C T O R S
	@Autowired
	public FittingsControllerV1( final FittingService fittingService,
	                             final NeoComAuthenticationProvider neoComAuthenticationProvider ) {
		this.fittingService = fittingService;
		this.neoComAuthenticationProvider = neoComAuthenticationProvider;
	}

	@GetMapping(path = "/fittings/pilot/{pilotId}",
			consumes = "application/json",
			produces = "application/json")
	public ResponseEntity<List<Fitting>> getPilotFittings( @PathVariable @NotNull final Integer pilotId ) {
		final Integer authorizedPilotId = this.neoComAuthenticationProvider.getAuthenticatedPilot();
		if (authorizedPilotId.intValue() != pilotId.intValue())
			throw new NeoComRuntimeBackendException( NeoComAuthenticationProvider.errorPILOT_ACCESS_NOT_AUTHORIZED() );
		return this.fittingService.getPilotFittings();
	}
}
