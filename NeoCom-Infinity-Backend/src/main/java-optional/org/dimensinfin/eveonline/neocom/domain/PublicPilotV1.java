package org.dimensinfin.eveonline.neocom.domain;

import java.util.Objects;

import org.joda.time.DateTime;
import org.springframework.hateoas.Link;

import org.dimensinfin.eveonline.neocom.esiswagger.model.GetCharactersCharacterIdOk;
import org.dimensinfin.eveonline.neocom.esiswagger.model.GetUniverseAncestries200Ok;
import org.dimensinfin.eveonline.neocom.esiswagger.model.GetUniverseBloodlines200Ok;
import org.dimensinfin.eveonline.neocom.esiswagger.model.GetUniverseRaces200Ok;

/**
 * Describes the data structures that can be accessed for any pilot identifier requested without needing any authenticated ESI endpoint.
 *
 * @author Adam Antinoo (adamantinoo.git@gmail.com)
 * @since 0.20.0
 */
public class PublicPilotV1 extends NeoComNode {
	private static final long serialVersionUID = -3178716949754945800L;
	/**
	 * The unique game character identifier assigned at character creation that is going to be used to identify this Pilot.
	 */
	protected Integer pilotId;
	protected Integer corporationId;
	protected GetCharactersCharacterIdOk pilotPublicData;
	protected Link corporation;
	protected GetUniverseRaces200Ok raceData;
	protected GetUniverseAncestries200Ok ancestryData;
	protected GetUniverseBloodlines200Ok bloodlineData;

	// - C O N S T R U C T O R S
	protected PublicPilotV1() {}

	// - G E T T E R S   &   S E T T E R S
	public GetUniverseAncestries200Ok getAncestryData() {
		return this.ancestryData;
	}

	public DateTime getBirthday() {return this.pilotPublicData.getBirthday();}

	public GetUniverseBloodlines200Ok getBloodlineData() {
		return this.bloodlineData;
	}

	public Integer getCorporationId() {
		return this.corporationId;
	}

	public String getDescription() {return this.pilotPublicData.getDescription();}

	public Integer getFactionId() {return this.pilotPublicData.getFactionId();}

	public GetCharactersCharacterIdOk.GenderEnum getGender() {return this.pilotPublicData.getGender();}

	public String getName() {return this.pilotPublicData.getName();}

	public Integer getPilotId() {
		return this.pilotId;
	}

	public GetCharactersCharacterIdOk getPilotPublicData() {
		return this.pilotPublicData;
	}

	public GetUniverseRaces200Ok getRaceData() {
		return this.raceData;
	}

	public Float getSecurityStatus() {return this.pilotPublicData.getSecurityStatus();}

	public String getTitle() {return this.pilotPublicData.getTitle();}

	// - V I R T U A L S
	public String getUrl4Icon() {
		return "https://image.eveonline.com/Character/" + this.pilotId + "_256.jpg";
	}

	// - B U I L D E R
	public static class Builder {
		private final PublicPilotV1 onConstruction;

		// - C O N S T R U C T O R S
		public Builder() {
			this.onConstruction = new PublicPilotV1();
		}

		public PublicPilotV1 build() {
			return this.onConstruction;
		}

		public PublicPilotV1.Builder withAncestryData( final GetUniverseAncestries200Ok ancestryData ) {
			if (null != ancestryData) this.onConstruction.ancestryData = ancestryData;
			return this;
		}

		public PublicPilotV1.Builder withBloodlineData( final GetUniverseBloodlines200Ok bloodlineData ) {
			if (null != bloodlineData) this.onConstruction.bloodlineData = bloodlineData;
			return this;
		}

		public PublicPilotV1.Builder withCorporationId( final Integer corporationId ) {
			this.onConstruction.corporationId = Objects.requireNonNull( corporationId );
			return this;
		}

		public PublicPilotV1.Builder withCorporationLink( final Link corporationLink ) {
			this.onConstruction.corporation = Objects.requireNonNull( corporationLink );
			return this;
		}

		public PublicPilotV1.Builder withPilotId( final Integer pilotId ) {
			this.onConstruction.pilotId = Objects.requireNonNull( pilotId );
			return this;
		}

		public PublicPilotV1.Builder withPilotPublicData( final GetCharactersCharacterIdOk pilotPublicData ) {
			this.onConstruction.pilotPublicData = Objects.requireNonNull( pilotPublicData );
			return this;
		}

		public PublicPilotV1.Builder withRaceData( final GetUniverseRaces200Ok raceData ) {
			if (null != raceData) this.onConstruction.raceData = raceData;
			return this;
		}
	}
}