package org.dimensinfin.eveonline.neocom.infinity.authorization.rest.v1;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import org.dimensinfin.eveonline.neocom.infinity.authorization.client.v1.ValidateAuthorizationTokenRequest;
import org.dimensinfin.eveonline.neocom.infinity.authorization.client.v1.ValidateAuthorizationTokenResponse;
import org.dimensinfin.eveonline.neocom.infinity.core.rest.NeoComController;

@RestController
@CrossOrigin()
@Validated
@RequestMapping("/api/v1/neocom")
public class AuthorizationControllerV1 extends NeoComController {
	private final AuthorizationService authorizationService;

	@Autowired
	public AuthorizationControllerV1( final AuthorizationService authorizationService ) {
		this.authorizationService = authorizationService;
	}

	@GetMapping(path = { "/validateAuthorizationToken" },
			consumes = "application/json",
			produces = "application/json")
	public ResponseEntity<ValidateAuthorizationTokenResponse> validate( @RequestParam(value = "code") final String code,
	                                                                    @RequestParam(value = "state") final String state,
	                                                                    @RequestParam(value = "dataSource", required = false) final Optional<String> dataSource ) {
		final ValidateAuthorizationTokenRequest.Builder requestBuilder = new ValidateAuthorizationTokenRequest.Builder()
				.withCode( code )
				.withState( state );
		dataSource.ifPresent( requestBuilder::optionalDataSource );
		return new ResponseEntity<>( this.authorizationService.validateAuthorizationToken( requestBuilder.build() ), HttpStatus.OK );
	}
}
