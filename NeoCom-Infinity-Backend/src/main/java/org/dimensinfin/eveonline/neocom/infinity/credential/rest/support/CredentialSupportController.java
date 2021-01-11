package org.dimensinfin.eveonline.neocom.infinity.credential.rest.support;

import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import org.dimensinfin.eveonline.neocom.database.entities.Credential;
import org.dimensinfin.eveonline.neocom.infinity.support.client.CredentialCountResponse;

@RestController
@Profile("acceptance")
@RequestMapping("/api/v1/neocom/support")
public class CredentialSupportController {
	private final CredentialSupportService credentialSupportService;

// - C O N S T R U C T O R S
	@Autowired
	public CredentialSupportController( final @NotNull CredentialSupportService credentialSupportService ) {
		this.credentialSupportService = credentialSupportService;
	}

	@GetMapping(path = "/credentials/count",
			consumes = "application/json",
			produces = "application/json")
	public ResponseEntity<CredentialCountResponse> countCredentials() {
		return new ResponseEntity<>( this.credentialSupportService.countCredentials(), HttpStatus.OK );
	}

	@GetMapping(path = "/credentials/deleteall",
			consumes = "application/json",
			produces = "application/json")
	public ResponseEntity<CredentialCountResponse> deleteAllCredentials() {
		return new ResponseEntity<>( this.credentialSupportService.deleteAllCredentials(), HttpStatus.OK );
	}

	@GetMapping(path = "/credentials/{credentialId}",
			consumes = "application/json",
			produces = "application/json")
	public ResponseEntity<Credential> findById( @PathVariable @NotNull final String credentialId ) {
		return new ResponseEntity<>( this.credentialSupportService.findCredentialById( credentialId ), HttpStatus.OK );
	}
}
