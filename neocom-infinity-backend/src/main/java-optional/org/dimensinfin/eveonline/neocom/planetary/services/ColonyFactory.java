package org.dimensinfin.eveonline.neocom.planetary.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.validation.constraints.NotNull;

import org.springframework.stereotype.Service;

import org.dimensinfin.eveonline.neocom.database.entities.Credential;
import org.dimensinfin.eveonline.neocom.esiswagger.model.GetCharactersCharacterIdPlanets200Ok;
import org.dimensinfin.eveonline.neocom.esiswagger.model.GetCharactersCharacterIdPlanetsPlanetIdOk;
import org.dimensinfin.eveonline.neocom.esiswagger.model.GetCharactersCharacterIdPlanetsPlanetIdOkPins;
import org.dimensinfin.eveonline.neocom.esiswagger.model.GetUniversePlanetsPlanetIdOk;
import org.dimensinfin.eveonline.neocom.planetary.ColonyPack;
import org.dimensinfin.eveonline.neocom.planetary.IPlanetaryFacility;
import org.dimensinfin.eveonline.neocom.planetary.PlanetType;
import org.dimensinfin.eveonline.neocom.planetary.converter.PlanetPinToPlanetfacilityConverter;
import org.dimensinfin.eveonline.neocom.planetary.domain.Colony;
import org.dimensinfin.eveonline.neocom.provider.ESIDataProvider;
import org.dimensinfin.eveonline.neocom.service.ResourceFactory;

@Service
public class ColonyFactory {
	private final ESIDataProvider esiDataProvider;
	private final ResourceFactory resourceFactory;

	private final List<IPlanetaryFacility> facilities = new ArrayList<>();

	// - C O N S T R U C T O R S
	public ColonyFactory( @NotNull final ESIDataProvider esiDataProvider,
	                      @NotNull final ResourceFactory resourceFactory ) {
		this.esiDataProvider = esiDataProvider;
		this.resourceFactory = resourceFactory;
	}

	/**
	 * Creates a new Colony instance from the planet esi data records. It will require the access to the esi server to get the detailed data fro
	 * the planet installations and planer resources.
	 */
	public Colony convertColony( final GetCharactersCharacterIdPlanets200Ok planet, final Credential credential ) {
		final GetUniversePlanetsPlanetIdOk planetData = Objects.requireNonNull(
				this.esiDataProvider.getUniversePlanetsPlanetId( planet.getPlanetId() )
		);
		final GetCharactersCharacterIdPlanetsPlanetIdOk structureContainer = Objects.requireNonNull(
				this.esiDataProvider.getCharactersCharacterIdPlanetsPlanetId(
						planet.getPlanetId(),
						credential
				)
		);
		//		if (null != structureContainer) {
		final PlanetType planetType = PlanetType.valueOf( planet.getPlanetType().name() );
		for (final GetCharactersCharacterIdPlanetsPlanetIdOkPins pin : structureContainer.getPins()) {
			this.facilities.add( new PlanetPinToPlanetfacilityConverter( this.resourceFactory, planetType ).convert( pin ) );
		}
		//		}
		final ColonyPack colony = new ColonyPack.Builder()
				.withPilotIdentifier( credential.getAccountId() )
				.withPlanetFacilities( structureContainer )
				//				.withColony( esiColonyData )
				.withPlanetData( planetData )
				.withFacilities( this.facilities )
				.build();
		//		colony.timeStamp();
		//		colonyCache.put( esiColonyData.getPlanetId(), colony );
		return null;
	}
}
