package org.dimensinfin.eveonline.neocom.infinity.backend.character.pilot.converter;

import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;

import org.dimensinfin.core.interfaces.Converter;
import org.dimensinfin.eveonline.neocom.esiswagger.model.GetCharactersCharacterIdOk;
import org.dimensinfin.eveonline.neocom.infinity.backend.character.pilot.rest.v2.PilotControllerV2;
import org.dimensinfin.eveonline.neocom.infinity.corporation.rest.CorporationControllerV1;
import org.dimensinfin.eveonline.neocom.infinity.pilot.rest.representation.PilotModel;
import org.dimensinfin.eveonline.neocom.provider.ESIDataProvider;
import org.dimensinfin.eveonline.neocom.provider.ESIUniverseDataProvider;
import org.dimensinfin.eveonline.neocom.provider.IConfigurationService;

public class EsiPilotDataToPilotModelConverter implements Converter<GetCharactersCharacterIdOk, PilotModel> {
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
	public PilotModel convert( final GetCharactersCharacterIdOk pilotData ) {
		final String esiUniverseServer = this.configurationService.getResourceString( "P.universe.retrofit.server.location" );
		return new PilotModel.Builder()
				.withPilotId( this.pilotId )
				.withCorporationId( pilotData.getCorporationId() )
				.withCorporationLink( WebMvcLinkBuilder.linkTo(
						WebMvcLinkBuilder.methodOn( CorporationControllerV1.class ).getCorporationData( pilotData.getCorporationId() )
				).withSelfRel() )
				//				.withLastLocationLink( WebMvcLinkBuilder.linkTo(
				//						WebMvcLinkBuilder.methodOn( SpaceLocationControllerV1.class ).getLocationById( pilotData.g )
				//				))
				.withPilotPublicData( pilotData )
				.withRaceData( this.esiUniverseDataProvider.searchSDERace( pilotData.getRaceId() ) )
				.withAncestryData( this.esiUniverseDataProvider.searchSDEAncestry( pilotData.getAncestryId() ) )
				.withBloodlineData( this.esiUniverseDataProvider.searchSDEBloodline( pilotData.getBloodlineId() ) )
				.build()
				.add( WebMvcLinkBuilder.linkTo(
						WebMvcLinkBuilder.methodOn( PilotControllerV2.class ).getPilotData( this.pilotId )
				).withSelfRel() );
	}
}
