package org.dimensinfin.eveonline.neocom.infinity.backend.universe.spacelocations.rest.v1;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import org.dimensinfin.eveonline.neocom.domain.space.SpaceLocation;

@RestController
@Validated
@RequestMapping("/api/v1/neocom")
public class SpaceLocationControllerV1 {
	private SpaceLocationServiceV1 spaceLocationServiceV1;

	// - C O N S T R U C T O R S
	@Autowired
	public SpaceLocationControllerV1( @NotNull final SpaceLocationServiceV1 spaceLocationServiceV1 ) {
		this.spaceLocationServiceV1 = spaceLocationServiceV1;
	}

	@GetMapping(path = "/spacelocations/{locationId}",
			consumes = "application/json",
			produces = "application/json")
	public ResponseEntity<SpaceLocation> getLocationById( @Valid @PathVariable final Long locationId ) {
		return new ResponseEntity<>( this.spaceLocationServiceV1.getLocationById( locationId ), HttpStatus.OK );
	}

}
