package org.dimensinfin.eveonline.neocom.infinity.backend.authorization.rest.v1;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import org.dimensinfin.eveonline.neocom.database.entities.Credential;
import org.dimensinfin.eveonline.neocom.infinity.authorization.client.v1.StoreCredentialRequest;
import org.dimensinfin.eveonline.neocom.infinity.authorization.client.v1.StoreCredentialResponse;
import org.dimensinfin.eveonline.neocom.infinity.core.exception.ErrorInfo;
import org.dimensinfin.eveonline.neocom.infinity.core.exception.NeoComSBException;

@RestController
@CrossOrigin()
@Validated
@RequestMapping("/api/v1/neocom")
public class StoreCredentialControllerV1 {
	private final StoreCredentialService storeCredentialService;

// - C O N S T R U C T O R S
	@Autowired
	public StoreCredentialControllerV1( final StoreCredentialService storeCredentialService ) {
		this.storeCredentialService = storeCredentialService;
	}

	@PutMapping(path = { "/credentials/{credentialId}" },
			consumes = "application/json",
			produces = "application/json")
	public ResponseEntity<StoreCredentialResponse> storeCredential( @PathVariable("credentialId") @NotNull final Integer credentialId,
	                                                                @RequestBody @Valid final Credential credential ) {
		if (!credentialId.equals( credential.getAccountId() ))
			throw new NeoComSBException( ErrorInfo.CREDENTIAL_DATA_NOT_MATCHING );
		final StoreCredentialRequest storeCredentialRequest = new StoreCredentialRequest.Builder()
				.withCredential( credential ).build();
		return new ResponseEntity<>( this.storeCredentialService.storeCredential( storeCredentialRequest ), HttpStatus.CREATED );
	}
}
