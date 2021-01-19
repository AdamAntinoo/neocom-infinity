package org.dimensinfin.eveonline.neocom.infinity.backend.universe.converter;

import javax.validation.constraints.NotNull;

import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;

import org.dimensinfin.core.interfaces.Converter;
import org.dimensinfin.eveonline.neocom.esiswagger.model.GetCharactersCharacterIdOk;
import org.dimensinfin.eveonline.neocom.infinity.backend.universe.domain.PilotPublicDataV1;
import org.dimensinfin.eveonline.neocom.infinity.corporation.rest.CorporationControllerV1;
import org.dimensinfin.eveonline.neocom.service.ESIDataService;

/**
 * @author Adam Antinoo (adamantinoo.git@gmail.com)
 * @since 0.20.0
 */
public class GetCharactersCharacterIdToPilotPublicDataV1Converter implements Converter<GetCharactersCharacterIdOk, PilotPublicDataV1> {
	private final Integer pilotId;
	private final ESIDataService esiDataService;

	// - C O N S T R U C T O R S
	public GetCharactersCharacterIdToPilotPublicDataV1Converter( @NotNull final Integer pilotId,
	                                                             @NotNull final ESIDataService esiDataService ) {
		this.pilotId = pilotId;
		this.esiDataService = esiDataService;
	}

	@Override
	public PilotPublicDataV1 convert( final GetCharactersCharacterIdOk pilotData ) {
		return new PilotPublicDataV1.Builder()
				.withPilotId( this.pilotId )
				.withCorporationId( pilotData.getCorporationId() )
				.withCorporationLink( WebMvcLinkBuilder.linkTo(
						WebMvcLinkBuilder.methodOn( CorporationControllerV1.class ).getCorporationData( pilotData.getCorporationId() )
				).withRel( "corporation" ) )
				.withPilotPublicData( pilotData )
				.withRaceData( this.esiDataService.searchSDERace( pilotData.getRaceId() ) )
				.withAncestryData( this.esiDataService.searchSDEAncestry( pilotData.getAncestryId() ) )
				.withBloodlineData( this.esiDataService.searchSDEBloodline( pilotData.getBloodlineId() ) )
				.build();
	}
}