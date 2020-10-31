package org.dimensinfin.eveonline.neocom.infinity.backend.universe.item.rest.v2;

import javax.validation.constraints.NotNull;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import org.dimensinfin.eveonline.neocom.domain.NeoItem;
import org.dimensinfin.eveonline.neocom.infinity.backend.universe.domain.EsiItemModel;

@RestController
@Validated
@RequestMapping("/api/v2/universe")
public class EsiItemControllerV2 {
	private final EsiItemServiceV2 esiItemServiceV2;

	public EsiItemControllerV2( final @NotNull EsiItemServiceV2 esiItemServiceV2 ) {this.esiItemServiceV2 = esiItemServiceV2;}

	@GetMapping(path = "/items/{itemId}",
			consumes = "application/json",
			produces = "application/json")
	public ResponseEntity<EsiItemModel> getItem( final  @PathVariable @NotNull Integer itemId ) {
		return new ResponseEntity<>( this.esiItemServiceV2.getItem( itemId ), HttpStatus.OK );
	}
}
