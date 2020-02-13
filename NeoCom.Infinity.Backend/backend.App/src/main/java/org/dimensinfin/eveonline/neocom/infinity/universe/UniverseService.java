package org.dimensinfin.eveonline.neocom.infinity.universe;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import org.dimensinfin.eveonline.neocom.infinity.adapter.ESIDataProviderWrapper;
import org.dimensinfin.eveonline.neocom.infinity.universe.client.v1.ServerStatus;

@Service
public class UniverseService {
	private ESIDataProviderWrapper esiDataAdapter;

	@Autowired
	public UniverseService( final ESIDataProviderWrapper esiDataAdapter ) {
		this.esiDataAdapter = esiDataAdapter;
	}

	public ResponseEntity<ServerStatus> getServerStatus( final String server ) {
		return new ResponseEntity<ServerStatus>( new ServerStatus.Builder()
				.withServer( server )
				.withStatus( this.esiDataAdapter.getUniverseStatus( server ) )
				.build(), HttpStatus.OK );
	}
}
