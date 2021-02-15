package org.dimensinfin.eveonline.neocom.infinity.backend.character.pilot.rest.v2;

import java.util.Objects;
import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import org.dimensinfin.eveonline.neocom.character.converter.GetCharactersCharacterIdLocationToSpaceLocationConverter;
import org.dimensinfin.eveonline.neocom.character.domain.PilotV1;
import org.dimensinfin.eveonline.neocom.character.domain.PublicCorporationV1;
import org.dimensinfin.eveonline.neocom.character.domain.PublicPilotV1;
import org.dimensinfin.eveonline.neocom.database.entities.Credential;
import org.dimensinfin.eveonline.neocom.esiswagger.model.GetCharactersCharacterIdLocationOk;
import org.dimensinfin.eveonline.neocom.esiswagger.model.GetCharactersCharacterIdOk;
import org.dimensinfin.eveonline.neocom.esiswagger.model.GetCharactersCharacterIdShipOk;
import org.dimensinfin.eveonline.neocom.esiswagger.model.GetCharactersCharacterIdSkillsOk;
import org.dimensinfin.eveonline.neocom.infinity.config.security.CredentialDetailsService;
import org.dimensinfin.eveonline.neocom.infinity.config.security.NeoComAuthenticationProvider;
import org.dimensinfin.eveonline.neocom.infinity.core.exception.NeoComRuntimeBackendException;
import org.dimensinfin.eveonline.neocom.infinity.core.rest.NeoComAuthenticatedService;
import org.dimensinfin.eveonline.neocom.infinity.core.rest.NeoComCredentialService;
import org.dimensinfin.eveonline.neocom.provider.IConfigurationService;
import org.dimensinfin.eveonline.neocom.service.ESIDataService;
import org.dimensinfin.eveonline.neocom.service.LocationCatalogService;
import org.dimensinfin.eveonline.neocom.service.ResourceFactory;

@Deprecated
@Profile("deprecated")
@Service
public class PilotServiceV2 extends NeoComCredentialService {
	private final IConfigurationService configurationService;
	private final ESIDataService esiDataService;
	private final LocationCatalogService locationCatalogService;
	private final ResourceFactory resourceFactory;

	// - C O N S T R U C T O R S
	@Autowired
	public PilotServiceV2( @NotNull final NeoComAuthenticationProvider neoComAuthenticationProvider,
	                       @NotNull final CredentialDetailsService credentialDetailsService,
	                       @NotNull final IConfigurationService configurationService,
	                       @NotNull final ESIDataService esiDataService,
	                       @NotNull final LocationCatalogService locationCatalogService,
	                       @NotNull final ResourceFactory resourceFactory ) {
		super( neoComAuthenticationProvider, credentialDetailsService );
		this.configurationService = configurationService;
		this.esiDataService = esiDataService;
		this.locationCatalogService = locationCatalogService;
		this.resourceFactory = resourceFactory;
	}

	/**
	 * Validates that the pilot data requested matches the current pilot logged in. And then gets the Credential to access the authenticated ESI
	 * backend data.
	 *
	 * @param pilotId requested pilot identifier. Should match with the logged pilot identifier.
	 * @return a complete Pilot data record with some data that can only be accessed when authenticated.
	 */
	@Deprecated
	public PilotV1 getAuthenticatedPilotData( final Integer pilotId ) {
		this.neoComAuthenticationProvider.validatePilotIdentifier( pilotId );
		return this.pilotV1Generator( Objects.requireNonNull( this.getAuthorizedCredential() ) );
	}

	@Deprecated
	public PublicPilotV1 getPilotPublicData( final Integer pilotId ) {
		final PublicCorporationV1 corporation = new PublicCorporationV1.Builder().build();
		return null;
	}

	private PilotV1 pilotV1Generator( final Credential credential ) {
		final GetCharactersCharacterIdOk pilotData = this.esiDataService.getCharactersCharacterId( credential.getAccountId() );
		if (null == pilotData)
			throw new NeoComRuntimeBackendException( NeoComAuthenticatedService.errorTARGETNOTFOUND( "Pilot", credential.getAccountId() ) );
		final GetCharactersCharacterIdSkillsOk skillPoints = this.esiDataService.getCharactersCharacterIdSkills( credential );
		final Double walletBalance = this.esiDataService.getCharactersCharacterIdWallet( credential );
		final GetCharactersCharacterIdLocationOk lastKnownLocation = this.esiDataService.getCharactersCharacterIdLocation( credential );
		final GetCharactersCharacterIdShipOk currentShip = this.esiDataService.getCharactersCharacterIdShip( credential );
		return new PilotV1.Builder()
				.withPilotId( credential.getAccountId() )
				//				.withCorporationId( pilotData.getCorporationId() )
				//				.withCorporationLink( WebMvcLinkBuilder.linkTo(
				//						WebMvcLinkBuilder.methodOn( CorporationControllerV1.class ).getCorporationData( pilotData.getCorporationId() )
				//				).withRel( "corporation" ) )
				.withPilotPublicData( pilotData )
				.withRaceData( this.esiDataService.searchSDERace( pilotData.getRaceId() ) )
				.withAncestryData( this.esiDataService.searchSDEAncestry( pilotData.getAncestryId() ) )
				.withBloodlineData( this.esiDataService.searchSDEBloodline( pilotData.getBloodlineId() ) )
				.withTotalSkillPoints( skillPoints.getTotalSp() )
				.withWalletBalance( walletBalance )
				.withLastKnownLocation( new GetCharactersCharacterIdLocationToSpaceLocationConverter(
						this.locationCatalogService, credential ).convert( lastKnownLocation ) )
				.withCurrentShip( currentShip )
				.withCurrentShipType( this.resourceFactory.generateType4Id( currentShip.getShipTypeId() ) )
				.build();
	}
}
