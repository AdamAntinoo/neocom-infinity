package org.dimensinfin.eveonline.neocom.infinity.universe;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import org.dimensinfin.eveonline.neocom.infinity.core.rest.NeoComController;
import org.dimensinfin.eveonline.neocom.infinity.universe.client.v1.ServerStatus;
import org.dimensinfin.eveonline.neocom.provider.ESIDataProvider;

@RestController
@CrossOrigin()
//@Validated
//@RequestMapping("/api/v1/neocom")
public class UniverseController extends NeoComController {
	private final UniverseService universeService;

	@Autowired
	public UniverseController( final UniverseService universeService ) {
		this.universeService = universeService;
	}

	@GetMapping(path = { "/api/v1/neocom/server/datasource/{dataSource}",
			"/api/v1/neocom/server/datasource/",
			"/api/v1/neocom/server/",
			"/api/v1/neocom/server" },
			produces = "application/json")
	public ResponseEntity<ServerStatus> getServerStatus( @PathVariable final Optional<String> dataSource ) {
//		logger.info( ">>>>>>>>>>>>>>>>>>>>NEW REQUEST: " + "/server/datasource/{}", dataSource );
		String server;
		if (!dataSource.isPresent()) server = ESIDataProvider.DEFAULT_ESI_SERVER;
		else server = dataSource.get();
		return this.universeService.getServerStatus( server );
	}
}
