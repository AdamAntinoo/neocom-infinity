package org.dimensinfin.eveonline.neocom.infinity.backend.universe.server.rest.v1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import org.dimensinfin.eveonline.neocom.infinity.adapter.ESIDataProviderWrapper;
import org.dimensinfin.eveonline.neocom.infinity.universe.client.v1.ServerStatus;
import org.dimensinfin.eveonline.neocom.provider.ESIDataProvider;

@Service
public class UniverseService {
	private ESIDataProvider esiDataProvider;

	// - C O N S T R U C T O R S
	@Autowired
	public UniverseService( final ESIDataProviderWrapper esiDataProviderWrapper ) {
		this.esiDataProvider = esiDataProviderWrapper.getSingleton();
	}

	// - G E T T E R S   &   S E T T E R S
	public ResponseEntity<ServerStatus> getServerStatus() {
		return new ResponseEntity<ServerStatus>( new ServerStatus.Builder()
				.withServer( ESIDataProvider.DEFAULT_ESI_SERVER )
				.withStatus( this.esiDataProvider.getUniverseStatus( ESIDataProvider.DEFAULT_ESI_SERVER ) )
				.build(), HttpStatus.OK );
	}
}
