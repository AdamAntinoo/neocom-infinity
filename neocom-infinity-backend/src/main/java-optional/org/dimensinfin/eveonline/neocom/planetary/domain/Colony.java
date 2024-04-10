package org.dimensinfin.eveonline.neocom.planetary.domain;

import java.util.List;
import java.util.Objects;

import org.dimensinfin.eveonline.neocom.esiswagger.model.GetCharactersCharacterIdPlanets200Ok;
import org.dimensinfin.eveonline.neocom.esiswagger.model.GetCharactersCharacterIdPlanetsPlanetIdOk;
import org.dimensinfin.eveonline.neocom.esiswagger.model.GetUniversePlanetsPlanetIdOk;
import org.dimensinfin.eveonline.neocom.planetary.ICommandCenterFacility;
import org.dimensinfin.eveonline.neocom.planetary.IPlanetaryFacility;

public class Colony {
	private Integer pilotIdentifier; // Use this identifier to get the owner of the planet when clicking on the item.
	private GetCharactersCharacterIdPlanets200Ok planet; // Some colony and planet data
	private GetUniversePlanetsPlanetIdOk planetData; // Planet location and generic planet data
	private GetCharactersCharacterIdPlanetsPlanetIdOk planetFacilities; // The raw data for the colony structures
	private List<IPlanetaryFacility> facilities; // NeoCom instances for the colony facilities with all extended data
	private ICommandCenterFacility commandCenter; // The colony Command center to be used to center the colony installations.

	private Colony() {}

	public Colony addFacility( final IPlanetaryFacility facility ) {
		if (null != facility) this.facilities.add( facility );
		return this;
	}

	// - B U I L D E R
	public static class Builder {
		private Colony onConstruction;

		public Builder() {
			this.onConstruction = new Colony();
		}

		public Colony.Builder withPilotIdentifier( final int pilotIdentifier ) {
			this.onConstruction.pilotIdentifier = pilotIdentifier;
			return this;
		}

		public Colony.Builder withPlanet( final GetCharactersCharacterIdPlanets200Ok planet ) {
			this.onConstruction.planet = Objects.requireNonNull( planet );
			return this;
		}

		public Colony.Builder withPlanetData( final GetUniversePlanetsPlanetIdOk planetData ) {
			this.onConstruction.planetData = Objects.requireNonNull( planetData );
			return this;
		}

		public Colony.Builder withPlanetFacilitiesData( final GetCharactersCharacterIdPlanetsPlanetIdOk planetFacilities ) {
			this.onConstruction.planetFacilities = Objects.requireNonNull( planetFacilities );
			return this;
		}
	}
}
