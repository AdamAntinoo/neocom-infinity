package org.dimensinfin.eveonline.neocom.infinity.backend.industry.manufacture.rest.v1;

import java.util.List;
import java.util.concurrent.TimeUnit;
import javax.validation.constraints.NotNull;

import com.jcabi.aspects.Cacheable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import org.dimensinfin.eveonline.neocom.industry.domain.ProcessedBlueprint;
import org.dimensinfin.eveonline.neocom.industry.domain.ProcessedBlueprintSummary;
import org.dimensinfin.eveonline.neocom.infinity.backend.industry.IndustryControllerV1;
import org.dimensinfin.eveonline.neocom.infinity.config.security.NeoComAuthenticationProvider;

@RestController
@Validated
public class IndustryManufactureControllerV1 extends IndustryControllerV1 {
	private final IndustryManufactureServiceV1 industryManufactureServiceV1;

	// - C O N S T R U C T O R S
	@Autowired
	public IndustryManufactureControllerV1( @NotNull final NeoComAuthenticationProvider neoComAuthenticationProvider,
	                                        @NotNull final IndustryManufactureServiceV1 industryManufactureServiceV1 ) {
		super( neoComAuthenticationProvider );
		this.industryManufactureServiceV1 = industryManufactureServiceV1;
	}

	// - G E T T E R S   &   S E T T E R S
	@GetMapping(path = "/pilots/{pilotId}/manufacture/blueprints/{blueprintUID}",
			consumes = "application/json",
			produces = "application/json")
	@Cacheable(lifetime = 15, unit = TimeUnit.MINUTES)
	public ResponseEntity<ProcessedBlueprint> getBlueprints4Pilot4UID( @PathVariable @NotNull final Integer pilotId,
	                                                                   @PathVariable @NotNull final String blueprintUID ) {
		this.neoComAuthenticationProvider.validatePilotIdentifier( pilotId );
		return new ResponseEntity<>( this.industryManufactureServiceV1.getBlueprints4Pilot4UID( pilotId, blueprintUID ), HttpStatus.OK );
	}

	/**
	 * Analyze the list of blueprints accessible to the selected pilot. Calculate the Bill Of Materials, the production cost and the current market
	 * cost.
	 * For variable scenarios use the pilot configuration properties to identify the preferred manufacture factory or the trading market hub.
	 * The endpoint is authenticated so validate the selected pilot identifier before going to fetch the data.
	 *
	 * @return the list of Blueprints along with the information required to display and help to make decisions at the front end user interface.
	 */
	@GetMapping(path = "/pilots/{pilotId}/manufacture/blueprints",
			consumes = "application/json",
			produces = "application/json")
	@Cacheable(lifetime = 15, unit = TimeUnit.MINUTES)
	public ResponseEntity<List<ProcessedBlueprint>> getBlueprints4PilotWithCostIndex( @PathVariable @NotNull final Integer pilotId ) {
		this.neoComAuthenticationProvider.validatePilotIdentifier( pilotId );
		return new ResponseEntity<>( this.industryManufactureServiceV1.getBlueprints4PilotWithCostIndex( pilotId ), HttpStatus.OK );
	}
}