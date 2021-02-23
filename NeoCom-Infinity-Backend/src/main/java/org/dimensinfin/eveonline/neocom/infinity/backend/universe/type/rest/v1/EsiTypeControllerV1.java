package org.dimensinfin.eveonline.neocom.infinity.backend.universe.type.rest.v1;

import javax.validation.constraints.NotNull;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import org.dimensinfin.eveonline.neocom.domain.EsiType;

@RestController
@Validated
@RequestMapping("/api/v1/universe")
public class EsiTypeControllerV1 {
	private final EsiTypeServiceV1 esiTypeServiceV1;

	// - C O N S T R U C T O R S
	public EsiTypeControllerV1( final @NotNull EsiTypeServiceV1 esiTypeServiceV1 ) {this.esiTypeServiceV1 = esiTypeServiceV1;}

	@GetMapping(path = "/types/{typeId}",
			//			consumes = "application/json",
			produces = "application/json")
	public ResponseEntity<EsiType> getItem( final @PathVariable @NotNull Integer typeId ) {
		return new ResponseEntity<>( this.esiTypeServiceV1.getEsiType( typeId ), HttpStatus.OK );
	}
}
