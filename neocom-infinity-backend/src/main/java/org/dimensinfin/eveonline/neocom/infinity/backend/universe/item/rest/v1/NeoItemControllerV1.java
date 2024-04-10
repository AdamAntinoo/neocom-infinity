package org.dimensinfin.eveonline.neocom.infinity.backend.universe.item.rest.v1;

import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import org.dimensinfin.eveonline.neocom.domain.NeoItem;
import org.dimensinfin.eveonline.neocom.infinity.core.rest.NeoComController;
@Deprecated
@RestController
@Validated
@RequestMapping("/api/v1/universe")
public class NeoItemControllerV1 extends NeoComController {
	private final NeoItemServiceV1 neoItemServiceV1;

	@Autowired
	public NeoItemControllerV1( final NeoItemServiceV1 neoItemServiceV1 ) {
		this.neoItemServiceV1 = neoItemServiceV1;
	}

	@GetMapping(path = "/items/{itemId}",
			consumes = "application/json",
			produces = "application/json")
	public ResponseEntity<NeoItem> getItem( final  @PathVariable @NotNull  Integer itemId ) {
		return new ResponseEntity<>( this.neoItemServiceV1.getItem( itemId ), HttpStatus.OK );
	}
}
