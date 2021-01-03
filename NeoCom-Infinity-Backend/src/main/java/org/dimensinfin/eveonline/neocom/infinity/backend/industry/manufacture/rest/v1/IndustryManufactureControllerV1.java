package org.dimensinfin.eveonline.neocom.infinity.backend.industry.manufacture.rest.v1;

import java.util.List;
import java.util.concurrent.TimeUnit;

import com.jcabi.aspects.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import org.dimensinfin.eveonline.neocom.infinity.backend.industry.IndustryControllerV1;
import org.dimensinfin.eveonline.neocom.infinity.backend.industry.domain.ProcessedBlueprint;

@RestController
@Validated
public class IndustryManufactureControllerV1 extends IndustryControllerV1 {
	private final IndustryManufactureServiceV1 industryManufactureServiceV1;

	// - C O N S T R U C T O R S
	public IndustryManufactureControllerV1( final IndustryManufactureServiceV1 industryManufactureServiceV1 ) {this.industryManufactureServiceV1 = industryManufactureServiceV1;}

	// - G E T T E R S   &   S E T T E R S
	@GetMapping(path = "/manufacture/blueprints/costindex",
			consumes = "application/json",
			produces = "application/json")
	@Cacheable(lifetime = 15, unit = TimeUnit.MINUTES)
	public ResponseEntity<List<ProcessedBlueprint>> getBlueprints4PilotWithCostIndex() {
		return new ResponseEntity<>( this.industryManufactureServiceV1.getBlueprints4PilotWithCostIndex(), HttpStatus.OK );
	}
}