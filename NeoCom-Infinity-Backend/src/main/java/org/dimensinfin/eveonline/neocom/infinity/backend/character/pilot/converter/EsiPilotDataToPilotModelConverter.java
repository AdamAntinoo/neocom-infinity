package org.dimensinfin.eveonline.neocom.infinity.backend.character.pilot.converter;

import javax.validation.constraints.NotNull;

import org.dimensinfin.core.interfaces.Converter;
import org.dimensinfin.eveonline.neocom.character.domain.PilotV1;
import org.dimensinfin.eveonline.neocom.character.domain.PublicCorporationV1;
import org.dimensinfin.eveonline.neocom.esiswagger.model.GetCharactersCharacterIdOk;
import org.dimensinfin.eveonline.neocom.esiswagger.model.GetCorporationsCorporationIdOk;
import org.dimensinfin.eveonline.neocom.provider.ESIUniverseDataProvider;
import org.dimensinfin.eveonline.neocom.provider.IConfigurationService;

@Deprecated
public class EsiPilotDataToPilotModelConverter implements Converter<GetCharactersCharacterIdOk, PilotV1> {
	private final IConfigurationService configurationService;
	private final ESIUniverseDataProvider esiUniverseDataProvider;
	private final Integer pilotId;

	// - C O N S T R U C T O R S
	public EsiPilotDataToPilotModelConverter( final @NotNull IConfigurationService configurationService,
	                                          final @NotNull ESIUniverseDataProvider esiUniverseDataProvider,
	                                          final @NotNull Integer pilotId ) {
		this.configurationService = configurationService;
		this.esiUniverseDataProvider = esiUniverseDataProvider;
		this.pilotId = pilotId;
	}

	@Override
	public PilotV1 convert( final GetCharactersCharacterIdOk pilotData ) {
		final String esiUniverseServer = this.configurationService.getResourceString( "P.universe.retrofit.server.location" );
		final GetCorporationsCorporationIdOk corporationPublicData = this.esiUniverseDataProvider
				.getCorporationsCorporationId( pilotData.getCorporationId() );
		final PublicCorporationV1 corporation = new PublicCorporationV1.Builder()
				.withCorporationId( pilotData.getCorporationId() )
				.withCorporationPublicData( corporationPublicData )
				.build();
		return new PilotV1.Builder()
				.withCurrentShip( null )
				.withPilotId( this.pilotId )
				.withCorporation( corporation )
				.withPilotPublicData( pilotData )
				.withRaceData( this.esiUniverseDataProvider.searchSDERace( pilotData.getRaceId() ) )
				.withAncestryData( this.esiUniverseDataProvider.searchSDEAncestry( pilotData.getAncestryId() ) )
				.withBloodlineData( this.esiUniverseDataProvider.searchSDEBloodline( pilotData.getBloodlineId() ) )
				.build();
	}
}
