package org.dimensinfin.eveonline.neocom.infinity.backend.character.pilot.rest.v1;

import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.dimensinfin.eveonline.neocom.domain.Pilot;
import org.dimensinfin.eveonline.neocom.esiswagger.model.GetCharactersCharacterIdOk;
import org.dimensinfin.eveonline.neocom.infinity.core.exception.NeoComRuntimeBackendException;
import org.dimensinfin.eveonline.neocom.infinity.core.rest.NeoComAuthenticatedService;
import org.dimensinfin.eveonline.neocom.service.ESIDataService;

@Service
public class PilotServiceV1 {
	protected final ESIDataService esiDataService;

	// - C O N S T R U C T O R S
	@Autowired
	public PilotServiceV1( @NotNull final ESIDataService esiDataService ) {
		this.esiDataService = esiDataService;
	}

	public Pilot buildPilotData( final int pilotId ) {
		final GetCharactersCharacterIdOk pilotData = this.esiDataService.getCharactersCharacterId( pilotId );
		return new Pilot.Builder()
				.withPilotIdentifier( pilotId )
				.withCharacterPublicData( pilotData )
				.withRaceData( this.esiDataService.searchSDERace( pilotData.getRaceId() ) )
				.withAncestryData( this.esiDataService.searchSDEAncestry( pilotData.getAncestryId() ) )
				.withBloodlineData( this.esiDataService.searchSDEBloodline( pilotData.getBloodlineId() ) )
				.build();
	}

	public Pilot getPilotData( final int pilotId ) {
		final GetCharactersCharacterIdOk pilotData = this.esiDataService.getCharactersCharacterId( pilotId );
		if (null == pilotData)
			throw new NeoComRuntimeBackendException( NeoComAuthenticatedService.errorTARGETNOTFOUND( "Pilot", pilotId ) );
		return this.buildPilotData( pilotId );
	}
}
