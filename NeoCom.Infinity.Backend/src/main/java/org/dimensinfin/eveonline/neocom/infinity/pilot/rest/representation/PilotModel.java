package org.dimensinfin.eveonline.neocom.infinity.pilot.rest.representation;

import java.util.Objects;

import org.springframework.hateoas.Link;
import org.springframework.hateoas.RepresentationModel;

import org.dimensinfin.eveonline.neocom.esiswagger.model.GetCharactersCharacterIdOk;
import org.dimensinfin.eveonline.neocom.esiswagger.model.GetUniverseAncestries200Ok;
import org.dimensinfin.eveonline.neocom.esiswagger.model.GetUniverseBloodlines200Ok;
import org.dimensinfin.eveonline.neocom.esiswagger.model.GetUniverseRaces200Ok;

public class PilotModel extends RepresentationModel<PilotModel> {
	private Integer pilotId;
	private Integer corporationId;
	private Link corporation;
	private Link lastKnownLocation;
	private GetCharactersCharacterIdOk pilotPublicData;
	private GetUniverseRaces200Ok raceData;
	private GetUniverseAncestries200Ok ancestryData;
	private GetUniverseBloodlines200Ok bloodlineData;

	// - C O N S T R U C T O R S
	private PilotModel() {}

	// - G E T T E R S   &   S E T T E R S
	public GetUniverseAncestries200Ok getAncestryData() {
		return this.ancestryData;
	}

	public GetUniverseBloodlines200Ok getBloodlineData() {
		return this.bloodlineData;
	}

	public Link getCorporation() {
		return this.corporation;
	}

	public Integer getCorporationId() {
		return this.corporationId;
	}

	public Integer getPilotId() {
		return this.pilotId;
	}

	public GetCharactersCharacterIdOk getPilotPublicData() {
		return this.pilotPublicData;
	}

	public GetUniverseRaces200Ok getRaceData() {
		return this.raceData;
	}

	// - B U I L D E R
	public static class Builder {
		private final PilotModel onConstruction;

		// - C O N S T R U C T O R S
		public Builder() {
			this.onConstruction = new PilotModel();
		}

		public PilotModel build() {
			Objects.requireNonNull( this.onConstruction.pilotId );
			Objects.requireNonNull( this.onConstruction.pilotPublicData );
			return this.onConstruction;
		}

		public PilotModel.Builder withAncestryData( final GetUniverseAncestries200Ok ancestryData ) {
			if (null != ancestryData) this.onConstruction.ancestryData = ancestryData;
			return this;
		}

		public PilotModel.Builder withBloodlineData( final GetUniverseBloodlines200Ok bloodlineData ) {
			if (null != bloodlineData) this.onConstruction.bloodlineData = bloodlineData;
			return this;
		}

		public PilotModel.Builder withCorporationId( final Integer corporationId ) {
			this.onConstruction.corporationId = Objects.requireNonNull( corporationId );
			return this;
		}

		public PilotModel.Builder withCorporationLink( final Link corporationLink ) {
			this.onConstruction.corporation = Objects.requireNonNull( corporationLink );
			return this;
		}

		public PilotModel.Builder withLastLocationLink( final Link lastLocationLink ) {
			this.onConstruction.lastKnownLocation = Objects.requireNonNull( lastLocationLink );
			return this;
		}

		public PilotModel.Builder withPilotId( final Integer pilotId ) {
			this.onConstruction.pilotId = Objects.requireNonNull( pilotId );
			return this;
		}

		public PilotModel.Builder withPilotPublicData( final GetCharactersCharacterIdOk pilotPublicData ) {
			this.onConstruction.pilotPublicData = Objects.requireNonNull( pilotPublicData );
			return this;
		}

		public PilotModel.Builder withRaceData( final GetUniverseRaces200Ok raceData ) {
			if (null != raceData) this.onConstruction.raceData = raceData;
			return this;
		}
	}
}
