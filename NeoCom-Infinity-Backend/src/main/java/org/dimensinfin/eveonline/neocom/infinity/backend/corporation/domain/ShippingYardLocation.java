package org.dimensinfin.eveonline.neocom.infinity.backend.corporation.domain;

import java.util.Objects;

import org.dimensinfin.eveonline.neocom.database.entities.NeoAsset;
import org.dimensinfin.eveonline.neocom.domain.NeoComNode;
import org.dimensinfin.eveonline.neocom.domain.space.Station;

public class ShippingYardLocation extends NeoComNode {
	private static final long serialVersionUID = 8977497772656010306L;
	private NeoAsset deposit;
	private NeoAsset officeContainer;
	private Station station;

// - C O N S T R U C T O R S
	private ShippingYardLocation() {}

// - G E T T E R S   &   S E T T E R S
	public NeoAsset getDeposit() {
		return this.deposit;
	}

	public NeoAsset getOfficeContainer() {
		return this.officeContainer;
	}

	public Station getStation() {
		return this.station;
	}

	// - B U I L D E R
	public static class Builder {
		private final ShippingYardLocation onConstruction;

// - C O N S T R U C T O R S
		public Builder() {
			this.onConstruction = new ShippingYardLocation();
		}

		public ShippingYardLocation build() {
			Objects.requireNonNull( this.onConstruction.deposit );
			Objects.requireNonNull( this.onConstruction.officeContainer );
			return this.onConstruction;
		}

		public ShippingYardLocation.Builder withOfficeContainer( final NeoAsset officeContainer ) {
			Objects.requireNonNull( officeContainer );
			this.onConstruction.officeContainer = officeContainer;
			return this;
		}

		public ShippingYardLocation.Builder withStation( final Station location ) {
			Objects.requireNonNull( location );
			this.onConstruction.station = location;
			return this;
		}

		public ShippingYardLocation.Builder withYardDeposit( final NeoAsset deposit ) {
			Objects.requireNonNull( deposit );
			this.onConstruction.deposit = deposit;
			return this;
		}
	}
}
