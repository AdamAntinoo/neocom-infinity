package org.dimensinfin.eveonline.neocom.infinity.backend.character.wallet.rest.v1;

import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import org.dimensinfin.eveonline.neocom.infinity.config.security.NeoComAuthenticationProvider;
import org.dimensinfin.eveonline.neocom.infinity.core.rest.NeoComAuthenticatedController;

@RestController
@Validated
@RequestMapping("/api/v1/neocom")
public class WalletControllerV1 extends NeoComAuthenticatedController {
	private final WalletServiceV1 walletServiceV1;

	// - C O N S T R U C T O R S
	@Autowired
	public WalletControllerV1( @NotNull final NeoComAuthenticationProvider neoComAuthenticationProvider,
	                           @NotNull final WalletServiceV1 walletServiceV1 ) {
		super( neoComAuthenticationProvider );
		this.walletServiceV1 = walletServiceV1;
	}

	@GetMapping(path = "/pilots/{pilotId}/wallet/balance",
			consumes = "application/json",
			produces = "application/json")
	public ResponseEntity<Double> getPilotWalletBalance( @PathVariable @NotNull final Integer pilotId ) {
		this.validateAuthorizedPilot( pilotId );
		return new ResponseEntity<>( this.walletServiceV1.getPilotWalletBalance(), HttpStatus.OK );
	}
}
