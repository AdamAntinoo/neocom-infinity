package org.dimensinfin.eveonline.neocom.infinity.backend.universe.server.rest.v1;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import org.dimensinfin.eveonline.neocom.infinity.universe.client.v1.ServerStatus;

@RestController
@CrossOrigin()
//@Validated
//@RequestMapping("/api/v1/neocom")
public class UniverseController {
	private final UniverseService universeService;

	// - C O N S T R U C T O R S
	@Autowired
	public UniverseController( final UniverseService universeService ) {
		this.universeService = universeService;
	}

	// - G E T T E R S   &   S E T T E R S
	@GetMapping("/api/v1/server/status")
	public ResponseEntity<ServerStatus> getServerStatus() {
		return this.universeService.getServerStatus();
	}

	@GetMapping(path = { "/api/v1/neocom/server/datasource/{dataSource}",
			"/api/v1/neocom/server/datasource/",
			"/api/v1/neocom/server/",
			"/api/v1/neocom/server" },
			produces = "application/json")
	@Deprecated
	public ResponseEntity<ServerStatus> getServerStatusDeprecated( @PathVariable final Optional<String> dataSource ) {
		return this.universeService.getServerStatus();
	}
}
