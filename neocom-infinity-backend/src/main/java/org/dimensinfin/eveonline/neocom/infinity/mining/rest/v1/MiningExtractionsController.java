package org.dimensinfin.eveonline.neocom.infinity.mining.rest.v1;

import java.util.List;
import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import org.dimensinfin.eveonline.neocom.database.entities.MiningExtractionEntity;
import org.dimensinfin.eveonline.neocom.infinity.config.security.NeoComAuthenticationProvider;
import org.dimensinfin.eveonline.neocom.infinity.core.exception.NeoComRuntimeBackendExceptionObsolete;

/**
 * Controller to define the endpoints related to Mining Extractions, both for Pilots or Corporations.
 *
 * @author Adam Antinoo (adamantinoo.git@gmail.com)
 * @since 0.19.0
 */
@RestController
@CrossOrigin
@Validated
@RequestMapping("/api/v1/neocom")
public class MiningExtractionsController {
	private final NeoComAuthenticationProvider neoComAuthenticationProvider;
	private final MiningExtractionsService miningExtractionsService;

// - C O N S T R U C T O R S
	@Autowired
	public MiningExtractionsController( final NeoComAuthenticationProvider neoComAuthenticationProvider,
	                                    final MiningExtractionsService miningExtractionsService ) {
		this.neoComAuthenticationProvider = neoComAuthenticationProvider;
		this.miningExtractionsService = miningExtractionsService;
	}

	@GetMapping(path = "/miningextractions/pilot/{pilotIdentifier}/today",
			consumes = "application/json",
			produces = "application/json")
	public ResponseEntity<List<MiningExtractionEntity>> getTodayMiningExtractions4Pilot( @PathVariable @NotNull final Integer pilotIdentifier ) {
		final Integer authorizedPilotId = this.neoComAuthenticationProvider.getAuthenticatedPilot();
		if (authorizedPilotId.intValue() != pilotIdentifier.intValue())
			throw new NeoComRuntimeBackendExceptionObsolete( NeoComAuthenticationProvider.errorPILOT_ACCESS_NOT_AUTHORIZED() );
		return new ResponseEntity<>( this.miningExtractionsService.getTodayMiningExtractions4Pilot(), HttpStatus.OK );
	}
}
