package org.dimensinfin.eveonline.neocom.infinity.universe;

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

	@Autowired
	public UniverseService( final ESIDataProviderWrapper esiDataProviderWrapper ) {
		this.esiDataProvider = esiDataProviderWrapper.getSingleton();
	}

	public ResponseEntity<ServerStatus> getServerStatus( final String server ) {
		return new ResponseEntity<ServerStatus>( new ServerStatus.Builder()
				.withServer( server )
				.withStatus( this.esiDataProvider.getUniverseStatus( server ) )
				.build(), HttpStatus.OK );
	}
}
