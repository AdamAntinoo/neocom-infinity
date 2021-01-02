package org.dimensinfin.poc.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Validated
public class POCController {
	private final POCService pocService;

	// - C O N S T R U C T O R S
	@Autowired
	public POCController( final POCService pocService ) {this.pocService = pocService;}

	// - G E T T E R S   &   S E T T E R S
	@GetMapping(path = "/test/01")
	public ResponseEntity<Boolean> getPilotWalletBalance() {
		return new ResponseEntity<>( this.pocService.test01(), HttpStatus.OK );
	}
}