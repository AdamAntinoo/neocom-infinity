package org.dimensinfin.eveonline.neocom.infinity.mining.rest.support;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import org.dimensinfin.eveonline.neocom.database.entities.MiningExtractionEntity;

/**
 * Controller to define the endpoints related to Mining Extractions, both for Pilots or Corporations.
 *
 * @author Adam Antinoo (adamantinoo.git@gmail.com)
 * @since 0.19.0
 */
@RestController
@CrossOrigin
@Validated
@RequestMapping("/api/v1/neocom/support")
public class MiningExtractionsControllerSupport {
	private final MiningExtractionsServiceSupport miningExtractionsServiceSupport;

	@Autowired
	public MiningExtractionsControllerSupport( final MiningExtractionsServiceSupport miningExtractionsServiceSupport ) {
		this.miningExtractionsServiceSupport = miningExtractionsServiceSupport;
	}

	@GetMapping(path = "/miningextractions/deleteall",
			consumes = "application/json",
			produces = "application/json")
	public ResponseEntity<MiningExtractionCountResponse> deleteAllMiningExtractions() {
		return new ResponseEntity<>( this.miningExtractionsServiceSupport.deleteAllMiningExtractions(), HttpStatus.OK );
	}

	@PostMapping(path = "/miningextractions",
			consumes = "application/json",
			produces = "application/json")
	public ResponseEntity<StoreMiningExtractionResponse> storeMiningExtraction(
			@RequestBody @Valid @NotNull final MiningExtractionEntity miningExtractionEntity ) {
		return new ResponseEntity<>( this.miningExtractionsServiceSupport.storeMiningExtraction( miningExtractionEntity ), HttpStatus.OK );
	}
}
