package org.dimensinfin.eveonline.neocom.infinity.backend.character.pilot.rest.v1;

import java.util.Objects;
import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.dimensinfin.eveonline.neocom.domain.Pilot;
import org.dimensinfin.eveonline.neocom.esiswagger.model.GetCharactersCharacterIdOk;
import org.dimensinfin.eveonline.neocom.infinity.adapter.ESIDataProviderWrapper;
import org.dimensinfin.eveonline.neocom.infinity.core.rest.NeoComAuthenticatedService;
import org.dimensinfin.eveonline.neocom.infinity.core.exception.NeoComRuntimeBackendException;
import org.dimensinfin.eveonline.neocom.provider.ESIDataProvider;

@Service
public class PilotServiceV1 {
	private final ESIDataProvider esiDataProvider;

	// - C O N S T R U C T O R S
	@Autowired
	public PilotServiceV1( final @NotNull ESIDataProviderWrapper esiDataProviderWrapper ) {
		this.esiDataProvider = Objects.requireNonNull( esiDataProviderWrapper.getSingleton() );
	}

	public Pilot buildPilotData( final int pilotId ) {
		final GetCharactersCharacterIdOk pilotData = this.esiDataProvider.getCharactersCharacterId( pilotId );
		return new Pilot.Builder()
				.withPilotIdentifier( pilotId )
				.withCharacterPublicData( pilotData )
				.withRaceData( this.esiDataProvider.searchSDERace( pilotData.getRaceId() ) )
				.withAncestryData( this.esiDataProvider.searchSDEAncestry( pilotData.getAncestryId() ) )
				.withBloodlineData( this.esiDataProvider.searchSDEBloodline( pilotData.getBloodlineId() ) )
				.build();
	}

	public Pilot getPilotData( final int pilotId ) {
		final GetCharactersCharacterIdOk pilotData = this.esiDataProvider.getCharactersCharacterId( pilotId );
		if (null == pilotData)
			throw new NeoComRuntimeBackendException( NeoComAuthenticatedService.errorTARGETNOTFOUND( "Pilot", pilotId ) );
		return this.buildPilotData( pilotId );
	}
}
