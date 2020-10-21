package org.dimensinfin.eveonline.neocom.infinity.neoitem;

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

import org.dimensinfin.eveonline.neocom.domain.NeoItem;
import org.dimensinfin.eveonline.neocom.infinity.backend.core.rest.NeoComController;

@RestController
@CrossOrigin
@Validated
@RequestMapping("/api/v1/neoitem")
public class NeoItemController extends NeoComController {
	private final NeoItemService neoItemService;

	@Autowired
	public NeoItemController( final NeoItemService neoItemService ) {
		this.neoItemService = neoItemService;
	}

	@GetMapping(path = "/item/basic/{itemId}",
			consumes = "application/json",
			produces = "application/json")
	public ResponseEntity<NeoItem> getItemBasic( @PathVariable @NotNull final Integer itemId ) {
		return new ResponseEntity<>( this.neoItemService.getItemBasic( itemId ), HttpStatus.OK );
	}
}
