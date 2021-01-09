package org.dimensinfin.eveonline.neocom.domain;


import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.dimensinfin.eveonline.neocom.esiswagger.model.GetCharactersCharacterIdFittings200Ok;

public class FittingV2 {
	private final List<FittingItem> fittingItems = new ArrayList<>();
	private GetCharactersCharacterIdFittings200Ok fittingDescription;
	private EsiType shipHull;

	// - C O N S T R U C T O R S
	private FittingV2() {}

	// - G E T T E R S   &   S E T T E R S
	public Integer getFittingId() {return this.fittingDescription.getFittingId();}

	public List<FittingItem> getFittingItems() {
		return this.fittingItems;
	}

	public String getGroupName() {return this.shipHull.getGroupName();}

	public String getHullGroup() {return this.shipHull.getHullGroup().toUpperCase();}

	public String getName() {return this.fittingDescription.getName();}

	public EsiType getShipHull() {
		return this.shipHull;
	}

	public Integer getShipTypeId() {return this.fittingDescription.getShipTypeId();}

	public String getTypeIconURL() {return this.shipHull.getTypeIconURL();}

	public void addFittingItem( final FittingItem fittingItem ) {
		this.fittingItems.add( fittingItem );
	}

	// - B U I L D E R
	public static class Builder {
		private final FittingV2 onConstruction;

		// - C O N S T R U C T O R S
		public Builder() {
			this.onConstruction = new FittingV2();
		}

		public FittingV2 build() {
			Objects.requireNonNull( this.onConstruction.fittingDescription );
			Objects.requireNonNull( this.onConstruction.shipHull );
			return this.onConstruction;
		}

		public FittingV2.Builder withFittingDescription( final GetCharactersCharacterIdFittings200Ok fittingDescription ) {
			this.onConstruction.fittingDescription = Objects.requireNonNull( fittingDescription );
			return this;
		}

		public FittingV2.Builder withShipHull( final EsiType shipHull ) {
			this.onConstruction.shipHull = Objects.requireNonNull( shipHull );
			return this;
		}
	}
}
