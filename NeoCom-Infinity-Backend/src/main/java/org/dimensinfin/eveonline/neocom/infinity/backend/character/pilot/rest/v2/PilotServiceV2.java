package org.dimensinfin.eveonline.neocom.infinity.backend.character.pilot.rest.v2;

import java.util.Objects;
import javax.validation.constraints.NotNull;

import org.springframework.stereotype.Service;

import org.dimensinfin.eveonline.neocom.esiswagger.model.GetCharactersCharacterIdOk;
import org.dimensinfin.eveonline.neocom.infinity.backend.character.pilot.converter.EsiPilotDataToPilotModelConverter;
import org.dimensinfin.eveonline.neocom.infinity.backend.character.pilot.rest.v1.PilotServiceV1;
import org.dimensinfin.eveonline.neocom.infinity.backend.universe.converter.GetCharactersCharacterIdToPilotPublicDataV1Converter;
import org.dimensinfin.eveonline.neocom.infinity.backend.universe.domain.PilotPublicDataV1;
import org.dimensinfin.eveonline.neocom.infinity.core.exception.NeoComRuntimeBackendException;
import org.dimensinfin.eveonline.neocom.infinity.core.rest.NeoComAuthenticatedService;
import org.dimensinfin.eveonline.neocom.infinity.pilot.rest.representation.PilotModel;
import org.dimensinfin.eveonline.neocom.provider.IConfigurationService;
import org.dimensinfin.eveonline.neocom.service.ESIDataService;

@Service
public class PilotServiceV2 extends PilotServiceV1 {
	private final IConfigurationService configurationService;

	// - C O N S T R U C T O R S
	public PilotServiceV2( @NotNull final ESIDataService esiDataService,
	                       @NotNull final IConfigurationService configurationService ) {
		super( esiDataService );
		this.configurationService = configurationService;
	}

	public PilotModel getPilotData( final Integer pilotId ) {
		final GetCharactersCharacterIdOk pilotData = this.esiDataService.getCharactersCharacterId( pilotId );
		if (null == pilotData)
			throw new NeoComRuntimeBackendException( NeoComAuthenticatedService.errorTARGETNOTFOUND( "Pilot", pilotId ) );
		return new EsiPilotDataToPilotModelConverter( this.configurationService, this.esiDataService, pilotId ).convert( pilotData );
	}

	public PilotPublicDataV1 getPilotPublicData( final Integer pilotId ) {
		return new GetCharactersCharacterIdToPilotPublicDataV1Converter( pilotId, this.esiDataService ).convert(
				Objects.requireNonNull( this.esiDataService.getCharactersCharacterId( pilotId ) )
		);
	}
}
