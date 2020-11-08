package org.dimensinfin.eveonline.neocom.infinity.datamanagement.domain;

import java.util.Objects;

import org.dimensinfin.eveonline.neocom.domain.space.Station;
import org.dimensinfin.eveonline.neocom.esiswagger.model.GetMarketsRegionIdOrders200Ok;

import io.swagger.annotations.ApiModelProperty;

public class MarketOrder {
	private GetMarketsRegionIdOrders200Ok orderData;
	private Station station;

	// - C O N S T R U C T O R S
	private MarketOrder() {}

	// - G E T T E R S   &   S E T T E R S
	@ApiModelProperty(example = "null", required = true, value = "order_id integer")
	public Long getOrderId() {return this.orderData.getOrderId();}

	@ApiModelProperty(example = "null", required = true, value = "price number")
	public Double getPrice() {return this.orderData.getPrice();}

	public Station getStation() {
		return this.station;
	}

	@ApiModelProperty(example = "null", required = true, value = "type_id integer")
	public Integer getTypeId() {return this.orderData.getTypeId();}

	@ApiModelProperty(example = "null", required = true, value = "volume_remain integer")
	public Integer getVolumeRemain() {return this.orderData.getVolumeRemain();}

	public GetMarketsRegionIdOrders200Ok isBuyOrder( final Boolean isBuyOrder ) {return this.orderData.isBuyOrder( isBuyOrder );}

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
			this.onConstruction.orderData = Objects.requireNonNull( orderData );
			return this;
		}

		public MarketOrder.Builder withStation( final Station station ) {
			this.onConstruction.station = Objects.requireNonNull( station );
			return this;
		}
	}
}
