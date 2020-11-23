package org.dimensinfin.eveonline.neocom.infinity.backend.accountset.planetary.domain;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import org.dimensinfin.eveonline.neocom.database.entities.Credential;
import org.dimensinfin.eveonline.neocom.database.repositories.PlanetaryRepository;
import org.dimensinfin.eveonline.neocom.esiswagger.model.GetCharactersCharacterIdPlanets200Ok;
import org.dimensinfin.eveonline.neocom.esiswagger.model.GetCharactersCharacterIdPlanetsPlanetIdOk;
import org.dimensinfin.eveonline.neocom.esiswagger.model.GetUniversePlanetsPlanetIdOk;
import org.dimensinfin.eveonline.neocom.planetary.ColonyPack;
import org.dimensinfin.eveonline.neocom.planetary.IPlanetaryFacility;
import org.dimensinfin.eveonline.neocom.provider.ESIDataProvider;

/**
 * Colony creation and data gathering is a complex and lengthy operation. This class will concentrate all the creation code on a
 * single place and also can give caching and validation among other functionality like initial data calculation
 * and setup.
 */
public class ColonyFactoryDM {
	private static Map<Integer, ColonyPack> colonyCache = new HashMap<>();

	// - C O M P O N E N T S
	private ESIDataProvider esiDataAdapter;
	private Credential credential;
	private PlanetaryRepository planetaryRepository;

	private List<IPlanetaryFacility> facilities = new ArrayList<>();

	public ColonyPack accessColony( final GetCharactersCharacterIdPlanets200Ok esiColonyData ) {
		if (colonyCache.containsKey(esiColonyData.getPlanetId())) {
			final ColonyPack colony = colonyCache.get(esiColonyData.getPlanetId());
			if (colony.needsRefresh()) return this.createColony(esiColonyData);
			else return colony;
		} else return this.createColony(esiColonyData);
	}

	protected ColonyPack createColony( final GetCharactersCharacterIdPlanets200Ok esiColonyData ) {
		final GetUniversePlanetsPlanetIdOk planetData = this.esiDataAdapter.getUniversePlanetsPlanetId(esiColonyData.getPlanetId());
		Objects.requireNonNull(planetData);
		final GetCharactersCharacterIdPlanetsPlanetIdOk structureContainer = this.esiDataAdapter.getCharactersCharacterIdPlanetsPlanetId(
				esiColonyData.getPlanetId(),
				credential
		);
//		if (null != structureContainer) {
//			final PlanetType planetType = PlanetType.valueOf(esiColonyData.getPlanetType().name());
//			for (GetCharactersCharacterIdPlanetsPlanetIdOkPins pin : structureContainer.getPins()) {
//				this.facilities.add(this.convertPin2Facility(pin, planetType));
//			}
//		}
		final ColonyPack colony = new ColonyPack.Builder()
				.withPilotIdentifier(this.credential.getAccountId())
				.withPlanetFacilities(structureContainer)
				.withColony(esiColonyData)
				.withPlanetData(planetData)
				.withFacilities(this.facilities)
				.build();
//		colony.timeStamp();
		colonyCache.put(esiColonyData.getPlanetId(), colony);
		return colony;
	}

//	/**
//	 * Convert the list of facilities received from ESI to out own model structures. Facilities should be interconnected in another way
//	 * to allow the access to some other functions. Also there are special characteristics only available on some facilities depending
//	 * on the facility type.
//	 *
//	 * The planet item record is required to identify the pin group and from it the facility type.
//	 *
//	 * The colony is required by a command center to be able to request the used power and cpu. The colony is the only able to scan all
//	 * the facilities and get such information.
//	 */
//	protected IPlanetaryFacility convertPin2Facility( final GetCharactersCharacterIdPlanetsPlanetIdOkPins pin,
//	                                                  final PlanetType planetType ) {
//		final NeoItem facilityItem = new NeoItem(pin.getTypeId()); // The item for this facility.
//		// Create the right facility class depending on the type.
//		final PlanetaryFacilityType facilityType = PlanetaryFacilityType.getTypeByStructureGroup(facilityItem.getGroupId());
//		final PlanetaryFacility baseFacility = new PlanetaryFacility.Builder()
//				.withPin(pin)
//				.withPlanetType(planetType)
//				.withFacilityItem(facilityItem)
//				.build();
//		switch (facilityType) {
//			case COMMAND_CENTER:
//				return new CommandCenterFacility.Builder()
//						.withPlanetaryFacility(baseFacility)
//						.withPlanetaryStorage(new PlanetaryStorage(baseFacility.getContents()))
//						.build();
//			case STORAGE:
//				return new StorageFacility.Builder()
//						.withPlanetaryFacility(baseFacility)
//						.withPlanetaryStorage(new PlanetaryStorage(baseFacility.getContents()))
//						.build();
//			case LAUNCHPAD:
//				return new StorageFacility.Builder()
//						.withPlanetaryFacility(baseFacility)
//						.withPlanetaryStorage(new PlanetaryStorage(baseFacility.getContents()))
//						.build();
//			case EXTRACTOR_CONTROL_UNIT:
//				return new ExtractorFacility.Builder()
//						.withPlanetaryFacility(baseFacility)
//						.withExtractorDetails(pin)
//						.build();
//			case PLANETARY_FACTORY:
//				return new FactoryFacility.Builder()
//						.withPlanetaryFacility(baseFacility)
//						.withSchematics(baseFacility.getSchematicId())
//						.withPlanetaryRepository(this.planetaryRepository)
//						.build();
//		}
//		return baseFacility;
//	}

	// - B U I L D E R
	public static class Builder {
		private ColonyFactoryDM onConstruction;

		public Builder() {
			this.onConstruction = new ColonyFactoryDM();
		}

		public Builder withCredential( final Credential credential ) {
			Objects.requireNonNull(credential);
			this.onConstruction.credential = credential;
			return this;
		}

//		public Builder withEsiDataAdapter( final ESIDataAdapter esiDataAdapter ) {
//			Objects.requireNonNull(esiDataAdapter);
//			this.onConstruction.esiDataAdapter = esiDataAdapter;
//			return this;
//		}

		public Builder withPlanetaryRepository( final PlanetaryRepository planetaryRepository ) {
			Objects.requireNonNull(planetaryRepository);
			this.onConstruction.planetaryRepository = planetaryRepository;
			return this;
		}

		public ColonyFactoryDM build() {
			Objects.requireNonNull(this.onConstruction.esiDataAdapter);
			Objects.requireNonNull(this.onConstruction.credential);
			Objects.requireNonNull(this.onConstruction.planetaryRepository);
			return this.onConstruction;
		}
	}
}
