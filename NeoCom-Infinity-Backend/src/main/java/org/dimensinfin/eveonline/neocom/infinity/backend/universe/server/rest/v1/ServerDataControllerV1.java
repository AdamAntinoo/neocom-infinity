package org.dimensinfin.eveonline.neocom.infinity.backend.universe.server.rest.v1;

import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import org.dimensinfin.eveonline.neocom.infinity.backend.universe.domain.ServerStatus;

@RestController
@CrossOrigin()
@Validated
@RequestMapping("/api/v1/public")
public class ServerDataControllerV1 {
	private final ServerDataServiceV1 serverDataServiceV1;

	// - C O N S T R U C T O R S
	@Autowired
	public ServerDataControllerV1( @NotNull final ServerDataServiceV1 serverDataServiceV1 ) {
		this.serverDataServiceV1 = serverDataServiceV1;
	}

	// - G E T T E R S   &   S E T T E R S
	@GetMapping("/server/status")
	public ResponseEntity<ServerStatus> getServerStatus() {
		return new ResponseEntity<>( this.serverDataServiceV1.getServerStatus(), HttpStatus.OK );
	}
}
