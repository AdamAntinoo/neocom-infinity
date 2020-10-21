package org.dimensinfin.eveonline.neocom.infinity.backend.character.pilot.rest.v2;

import java.util.Objects;
import javax.validation.constraints.NotNull;

import org.springframework.stereotype.Service;

import org.dimensinfin.eveonline.neocom.esiswagger.model.GetCharactersCharacterIdOk;
import org.dimensinfin.eveonline.neocom.infinity.adapter.ConfigurationServiceWrapper;
import org.dimensinfin.eveonline.neocom.infinity.adapter.ESIDataProviderWrapper;
import org.dimensinfin.eveonline.neocom.infinity.backend.core.rest.NeoComBaseService;
import org.dimensinfin.eveonline.neocom.infinity.core.exception.NeoComRuntimeBackendException;
import org.dimensinfin.eveonline.neocom.infinity.backend.character.pilot.converter.EsiPilotDataToPilotModelConverter;
import org.dimensinfin.eveonline.neocom.infinity.pilot.rest.representation.PilotModel;
import org.dimensinfin.eveonline.neocom.provider.IConfigurationService;

@Service
public class PilotServiceV2 extends NeoComBaseService {
	private final IConfigurationService configurationService;

	// - C O N S T R U C T O R S
	public PilotServiceV2( final @NotNull ESIDataProviderWrapper esiDataProviderWrapper,
	                       final @NotNull ConfigurationServiceWrapper configurationServiceWrapper ) {
		super( esiDataProviderWrapper );
		this.configurationService = Objects.requireNonNull( configurationServiceWrapper.getSingleton() );
	}

	public PilotModel getPilotData( final Integer pilotId ) {
		final GetCharactersCharacterIdOk pilotData = this.esiDataProvider.getCharactersCharacterId( pilotId );
		if (null == pilotData)
			throw new NeoComRuntimeBackendException( errorTARGETNOTFOUND( "Pilot", pilotId ) );
		return new EsiPilotDataToPilotModelConverter( this.configurationService, this.esiDataProvider, pilotId ).convert( pilotData );
	}
}
