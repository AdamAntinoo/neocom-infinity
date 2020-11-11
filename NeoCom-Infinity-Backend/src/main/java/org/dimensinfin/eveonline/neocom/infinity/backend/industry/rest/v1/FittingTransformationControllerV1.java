package org.dimensinfin.eveonline.neocom.infinity.backend.industry.rest.v1;

import javax.validation.constraints.NotNull;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import org.dimensinfin.eveonline.neocom.infinity.backend.industry.fitting.domain.FittingConfigurations;

@RestController
@Validated
@RequestMapping("/api/v1/neocom")
public class FittingTransformationControllerV1 {
	private final FittingTransformationServiceV1 fittingTransformationServiceV1;

	// - C O N S T R U C T O R S
	public FittingTransformationControllerV1( final @NotNull FittingTransformationServiceV1 fittingTransformationServiceV1 ) {this.fittingTransformationServiceV1 = fittingTransformationServiceV1;}

	@GetMapping(path = "/industry/fitting/buildOrder/{fittingOrderId}/make",
			consumes = "application/json",
			produces = "application/json")
	public ResponseEntity<FittingConfigurations> getFittingBuildOrderById( final @PathVariable @NotNull String fittingOrderId ) {
		return new ResponseEntity<>( null, HttpStatus.OK );
	}
}
