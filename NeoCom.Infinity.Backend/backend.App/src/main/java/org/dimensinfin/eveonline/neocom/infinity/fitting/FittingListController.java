package org.dimensinfin.eveonline.neocom.infinity.fitting;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import org.dimensinfin.eveonline.neocom.domain.Fitting;
import org.dimensinfin.eveonline.neocom.infinity.core.NeoComController;
import org.dimensinfin.eveonline.neocom.infinity.core.exceptions.ErrorInfo;
import org.dimensinfin.eveonline.neocom.infinity.core.exceptions.NeoComAuthorizationException;
import org.dimensinfin.eveonline.neocom.infinity.core.security.NeoComAuthenticationProvider;

/**
 * This controller will be responsible to manage the Fitting list and all the Fitting management duties like the registration
 * of new fitting requests of the processing for a fitting into the list of actions and tasks that have to be accomplished to
 * complete a request.
 * <p>
 * Fitting Requests are volatile and should be checked for completion of associated jobs and logistics. While the Pilot
 * download for such data is responsibility of the Pilot controller all data matching and request processing responsibility for
 * this controller.
 *
 * @author Adam Antinoo
 */
@RestController
@CrossOrigin()
@Validated
@RequestMapping("/api/v1/neocom")
public class FittingListController extends NeoComController {
	private final FittingService fittingService;
	private final NeoComAuthenticationProvider neoComAuthenticationProvider;

	@Autowired
	public FittingListController( final FittingService fittingService,
	                              final NeoComAuthenticationProvider neoComAuthenticationProvider ) {
		this.fittingService = fittingService;
		this.neoComAuthenticationProvider = neoComAuthenticationProvider;
	}

	@GetMapping(path = "/fittings/pilot/{pilotId}",
			consumes = "application/json",
			produces = "application/json")
	public ResponseEntity<List<Fitting>> getPilotFittings( @PathVariable final Integer pilotId ) {
		final Integer authorizedPilotId = this.neoComAuthenticationProvider.getAuthenticatedPilot();
		if (authorizedPilotId.intValue() != pilotId.intValue())
			throw new NeoComAuthorizationException( ErrorInfo.PILOT_ID_NOT_AUTHORIZED );
		return this.fittingService.getPilotFittings( authorizedPilotId );
	}
}
