package org.dimensinfin.eveonline.neocom.infinity.backend.accountset.planetary.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.dimensinfin.core.interfaces.ICollaboration;
import org.dimensinfin.eveonline.neocom.planetary.IPlanetaryStorage;
import org.dimensinfin.eveonline.neocom.planetary.PlanetType;
import org.dimensinfin.eveonline.neocom.planetary.domain.PlanetaryFacility;

public class StorageFacility extends PlanetaryFacilityWrapper implements IPlanetaryStorage {
	private PlanetaryStorage planetaryStorage;

	public PlanetType getPlanetType() {
		return this.planetaryFacility.getPlanetType();
	}

	public Float getTotalVolume() {
		if (null != this.planetaryStorage) return this.planetaryStorage.getTotalVolume();
		else return 0.0F;
	}

	public Double getTotalValue() {
		return this.planetaryStorage.getTotalValue();
	}

	/**
	 * Change the icon depending on the storage type.
	 *
	 * @return the storage type icon reference identifier.
	 */
//	@Override
//	public int getIconReferenceId() {
//		if (this.getFacilityType() == PlanetaryFacilityType.STORAGE)
//			return R.drawable.storage80_white;
//		if (this.getFacilityType() == PlanetaryFacilityType.LAUNCHPAD)
//			return R.drawable.launchpad80_white;
//		return R.drawable.storage80_white;
//	}
//
//	@Override
//	public int getIconColorReference() {
//		return R.color.pi_storageiconcolor;
//	}

	// - I C O L L A B O R A T I O N
	@Override
	public List<ICollaboration> collaborate2Model( final String variant ) {
		if (variant.equalsIgnoreCase("PageNamesType.PLANET_FACILITIES_LAYOUT.name()")) return new ArrayList<>();
		else {
			final List<ICollaboration> collaboration = new ArrayList<>(this.planetaryStorage.getContents());
//			collaboration.add(new Spacer.Builder().withType(SpacerType.LINE_YELLOW).build());
			return collaboration;
		}
	}

	// - B U I L D E R
	public static class Builder {
		private StorageFacility onConstruction;

		public Builder() {
			this.onConstruction = new StorageFacility();
		}

		public Builder withPlanetaryFacility( final PlanetaryFacility planetaryFacility ) {
			this.onConstruction.planetaryFacility = planetaryFacility;
			return this;
		}

		public Builder withPlanetaryStorage( final PlanetaryStorage planetaryStorage ) {
			this.onConstruction.planetaryStorage = planetaryStorage;
			return this;
		}

		public StorageFacility build() {
			Objects.requireNonNull(this.onConstruction.planetaryFacility);
			Objects.requireNonNull(this.onConstruction.planetaryStorage);
			return this.onConstruction;
		}

	}
}
