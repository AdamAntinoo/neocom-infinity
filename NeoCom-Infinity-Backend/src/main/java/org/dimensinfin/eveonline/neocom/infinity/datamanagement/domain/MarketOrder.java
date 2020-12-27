package org.dimensinfin.eveonline.neocom.infinity.datamanagement.domain;

import java.util.Objects;

import org.dimensinfin.eveonline.neocom.domain.space.Station;
import org.dimensinfin.eveonline.neocom.esiswagger.model.GetMarketsRegionIdOrders200Ok;

public class MarketOrder {
	private Station station;
	private long orderId;
	private double price;
	private int typeId;
	private int volumeRemain;
	private int volumeTotal;

	// - C O N S T R U C T O R S
	private MarketOrder() {}

	// - G E T T E R S   &   S E T T E R S
	public long getOrderId() {
		return this.orderId;
	}

	public double getPrice() {
		return this.price;
	}

	public Station getStation() {
		return this.station;
	}

	public int getTypeId() {
		return this.typeId;
	}

	public int getVolumeRemain() {
		return this.volumeRemain;
	}

	public int getVolumeTotal() {
		return this.volumeTotal;
	}

	// - B U I L D E R
	public static class Builder {
		private final MarketOrder onConstruction;

		// - C O N S T R U C T O R S
		public Builder() {
			this.onConstruction = new MarketOrder();
		}

		public MarketOrder build() {
			return this.onConstruction;
		}

		public MarketOrder.Builder withOrderData( final GetMarketsRegionIdOrders200Ok orderData ) {
			Objects.requireNonNull( orderData );
			this.onConstruction.orderId = orderData.getOrderId();
			this.onConstruction.price = orderData.getPrice();
			this.onConstruction.typeId = orderData.getTypeId();
			this.onConstruction.volumeRemain = orderData.getVolumeRemain();
			this.onConstruction.volumeTotal = orderData.getVolumeTotal();
			return this;
		}

		public MarketOrder.Builder withStation( final Station station ) {
			this.onConstruction.station = Objects.requireNonNull( station );
			return this;
		}
	}
}
