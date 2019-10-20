package org.dimensinfin.eveonline.neocom.infinity.pilot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import org.dimensinfin.eveonline.neocom.domain.Pilot;
import org.dimensinfin.eveonline.neocom.esiswagger.model.GetCharactersCharacterIdOk;
import org.dimensinfin.eveonline.neocom.infinity.adapter.ESIDataAdapterWrapper;

@Service
public class PilotService {
	private ESIDataAdapterWrapper esiDataAdapter;

	@Autowired
	public PilotService( final ESIDataAdapterWrapper esiDataAdapter ) {
		this.esiDataAdapter = esiDataAdapter;
	}

	public ResponseEntity<Pilot> getPilotData( final int pilotId ) {
		final GetCharactersCharacterIdOk pilotData = this.esiDataAdapter.getCharactersCharacterId( pilotId );
		if (null == pilotData)
			return new ResponseEntity<>( HttpStatus.NOT_FOUND );

		// Access the rest of the pilot's esi data from the service.
		final Pilot pilot = new Pilot.Builder()
				.withPilotIdentifier( pilotId )
				.withCharacterPublicData( pilotData )
				.withRaceData( this.esiDataAdapter.searchSDERace( pilotData.getRaceId() ) )
				.withAncestryData( this.esiDataAdapter.searchSDEAncestry( pilotData.getAncestryId() ) )
				.withBloodlineData( this.esiDataAdapter.searchSDEBloodline( pilotData.getBloodlineId() ) )
				.build();
		return new ResponseEntity<Pilot>( pilot, HttpStatus.OK );
	}
}