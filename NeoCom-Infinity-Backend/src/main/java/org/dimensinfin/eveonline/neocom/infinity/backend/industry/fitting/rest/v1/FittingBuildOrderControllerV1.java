package org.dimensinfin.eveonline.neocom.infinity.backend.industry.fitting.rest.v1;

import javax.validation.constraints.NotNull;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import org.dimensinfin.eveonline.neocom.infinity.backend.industry.domain.FittingIndustryJob;
import org.dimensinfin.eveonline.neocom.infinity.backend.industry.fitting.rest.dao.FittingBuildOrderDao;

@RestController
@Validated
@RequestMapping("/api/v1/neocom")
public class FittingBuildOrderControllerV1 {
	private final FittingBuildOrderServiceV1 fittingBuildOrderServiceV1;

	// - C O N S T R U C T O R S
	public FittingBuildOrderControllerV1( final @NotNull FittingBuildOrderServiceV1 fittingBuildOrderServiceV1 ) {
		this.fittingBuildOrderServiceV1 =
				fittingBuildOrderServiceV1;
	}

	@GetMapping(path = "/industry/fitting/buildOrder/{fittingId}",
			consumes = "application/json",
			produces = "application/json")
	public ResponseEntity<FittingBuildOrderDao> getFittingBuildOrderById( final @PathVariable @NotNull Integer fittingId ) {
		return new ResponseEntity<>( this.fittingBuildOrderServiceV1.getFittingBuildOrderById( fittingId ), HttpStatus.OK );
	}

	@GetMapping(path = "/industry/fitting/buildOrder/{fittingId}/savedConfiguration",
			consumes = "application/json",
			produces = "application/json")
	public ResponseEntity<FittingIndustryJob> getFittingBuildOrderSavedConfiguration( final @PathVariable @NotNull Integer fittingId ) {
		return new ResponseEntity<>( this.fittingBuildOrderServiceV1.getFittingBuildOrderSavedConfiguration( fittingId ), HttpStatus.OK );
	}

	@GetMapping(path = "/industry/fitting/buildOrder/{fittingId}/targetConfiguration",
			consumes = "application/json",
			produces = "application/json")
	public ResponseEntity<FittingIndustryJob> getFittingBuildOrderTargetConfiguration( final @PathVariable @NotNull Integer fittingId ) {
		return new ResponseEntity<>( this.fittingBuildOrderServiceV1.getFittingBuildOrderTargetConfiguration( fittingId ), HttpStatus.OK );
	}
}
