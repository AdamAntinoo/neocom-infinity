package org.dimensinfin.eveonline.neocom.infinity.backend.universe.corporation.rest.v1;

import javax.validation.constraints.NotNull;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import org.dimensinfin.eveonline.neocom.domain.Corporation;
import org.dimensinfin.eveonline.neocom.infinity.corporation.rest.CorporationServiceV1;

/**
 * @author Adam Antinoo (adamantinoo.git@gmail.com)
 * @since 0.20.0
 */
@RestController
@Validated
@RequestMapping("/api/v1/universe")
public class UniverseCorporationControllerV1 {
	private final CorporationServiceV1 corporationServiceV1;

	// - C O N S T R U C T O R S
	public UniverseCorporationControllerV1( @NotNull final CorporationServiceV1 corporationServiceV1 ) {
		this.corporationServiceV1 = corporationServiceV1;
	}

	@GetMapping(path = "/corporations/{corporationId}",
			consumes = "application/json",
			produces = "application/json")
	public ResponseEntity<Corporation> getCorporationData( @PathVariable final Integer corporationId ) {
		return this.corporationServiceV1.getCorporationData( corporationId );
	}
}