package org.dimensinfin.eveonline.neocom.infinity.backend.character.pilot.rest.v2;

import java.util.Objects;
import javax.validation.constraints.NotNull;

import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Service;

import org.dimensinfin.eveonline.neocom.converter.GetCharactersCharacterIdToPublicPilotV1Converter;
import org.dimensinfin.eveonline.neocom.domain.PilotV1;
import org.dimensinfin.eveonline.neocom.domain.PublicPilotV1;
import org.dimensinfin.eveonline.neocom.esiswagger.model.GetCharactersCharacterIdOk;
import org.dimensinfin.eveonline.neocom.infinity.backend.character.pilot.converter.EsiPilotDataToPilotModelConverter;
import org.dimensinfin.eveonline.neocom.infinity.backend.character.pilot.rest.v1.PilotServiceV1;
import org.dimensinfin.eveonline.neocom.infinity.core.exception.NeoComRuntimeBackendException;
import org.dimensinfin.eveonline.neocom.infinity.core.rest.NeoComAuthenticatedService;
import org.dimensinfin.eveonline.neocom.infinity.corporation.rest.CorporationControllerV1;
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

	public PilotV1 getPilotData( final Integer pilotId ) {
		final GetCharactersCharacterIdOk pilotData = this.esiDataService.getCharactersCharacterId( pilotId );
		if (null == pilotData)
			throw new NeoComRuntimeBackendException( NeoComAuthenticatedService.errorTARGETNOTFOUND( "Pilot", pilotId ) );
		return new EsiPilotDataToPilotModelConverter( this.configurationService, this.esiDataService, pilotId ).convert( pilotData );
	}

	public PublicPilotV1 getPilotPublicData( final Integer pilotId ) {
		return new GetCharactersCharacterIdToPublicPilotV1Converter( pilotId, this.esiDataService ).convert(
				Objects.requireNonNull( this.esiDataService.getCharactersCharacterId( pilotId ) )
		);
	}

	private PilotV1 pilotV1Generator( final int pilotId ) {
		final GetCharactersCharacterIdOk pilotData = this.esiDataService.getCharactersCharacterId( pilotId );
		if (null == pilotData)
			throw new NeoComRuntimeBackendException( NeoComAuthenticatedService.errorTARGETNOTFOUND( "Pilot", pilotId ) );
		skillPoints = this.esiDataService.getSkills( credential );
		walletBalance = this.esiDataService.getCharactersCharacterIdWallet( credential );
		lastKnownLocation = this.esiDataService.getCharacterLocation( credential );
		currentShip = this.esiDataService.getcurrentShip( credential );
		return new PilotV1.Builder()
				.withPilotId( pilotId )
				.withCorporationId( pilotData.getCorporationId() )
				.withCorporationLink( WebMvcLinkBuilder.linkTo(
						WebMvcLinkBuilder.methodOn( CorporationControllerV1.class ).getCorporationData( pilotData.getCorporationId() )
				).withRel( "corporation" ) )
				.withPilotPublicData( pilotData )
				.withRaceData( this.esiDataService.searchSDERace( pilotData.getRaceId() ) )
				.withAncestryData( this.esiDataService.searchSDEAncestry( pilotData.getAncestryId() ) )
				.withBloodlineData( this.esiDataService.searchSDEBloodline( pilotData.getBloodlineId() ) )
				.withSkillPoints( skillPoints )
				.withWalletBalance( walletBalance )
				.withLastKnownLocation( lastKnownLocation )
				.withCurrentShip( currentShip )
				.build();


	}
}
