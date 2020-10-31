package org.dimensinfin.eveonline.neocom.infinity.backend.character.fitting.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.springframework.hateoas.RepresentationModel;

import org.dimensinfin.eveonline.neocom.domain.NeoItem;

public class FittingModel extends RepresentationModel<FittingModel> {
	private final List<FittingItemModel> fittingItems = new ArrayList<>();
	private Integer fittingId;
	private String name;
	private String description;
	private ShipHull shipHull;

	// - C O N S T R U C T O R S
	private FittingModel() {}

	// - G E T T E R S   &   S E T T E R S
	public String getDescription() {
		return this.description;
	}

	public Integer getFittingId() {
		return this.fittingId;
	}

	public List<FittingItemModel> getFittingItems() {
		return this.fittingItems;
	}

	public String getName() {
		return this.name;
	}

	public ShipHull getShipHull() {
		return this.shipHull;
	}

	public void addFittingItem( final FittingItemModel fittingItem ) {
		this.fittingItems.add( fittingItem );
	}

	// - B U I L D E R
	public static class Builder {
		private final FittingModel onConstruction;

		// - C O N S T R U C T O R S
		public Builder() {
			this.onConstruction = new FittingModel();
		}

		public FittingModel build() {
			Objects.requireNonNull( this.onConstruction.fittingId );
			Objects.requireNonNull( this.onConstruction.shipHull );
			return this.onConstruction;
		}

		public FittingModel.Builder withDescription( final String description ) {
			this.onConstruction.description = Objects.requireNonNull( description );
			return this;
		}

		public FittingModel.Builder withFittingId( final Integer fittingId ) {
			this.onConstruction.fittingId = Objects.requireNonNull( fittingId );
			return this;
		}

		//		public FittingModel.Builder withShipType( final Integer shipTypeId ) {
		//			this.onConstruction.shipTypeId = Objects.requireNonNull( shipTypeId );
		//			return this;
		//		}
		public FittingModel.Builder withName( final String name ) {
			this.onConstruction.name = Objects.requireNonNull( name );
			return this;
		}

		public FittingModel.Builder withShipHull( final ShipHull shipHull ) {
			this.onConstruction.shipHull = Objects.requireNonNull( shipHull );
			return this;
		}
	}
}
