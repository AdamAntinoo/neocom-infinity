package org.dimensinfin.eveonline.neocom.infinity.backend.market.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.annotation.Nullable;

import org.dimensinfin.eveonline.neocom.esiswagger.model.GetMarketsRegionIdOrders200Ok;
import org.dimensinfin.eveonline.neocom.infinity.datamanagement.domain.MarketOrder;

public class MarketData {
	public static final Double MARKET_DEEP_RANGE = 1.05;

	private Integer typeId;
	@Nullable
	private MarketOrder bestSellOrder;
	@Nullable
	private MarketOrder bestBuyOrder;
	private List<GetMarketsRegionIdOrders200Ok> sellOrders = new ArrayList<>();
	private int sellDeep = 0;
	private double sellAverage = 0.0;
	private double marketWidth = 0.0;

	// - C O N S T R U C T O R S
	private MarketData() {}

	// - G E T T E R S   &   S E T T E R S
	@Nullable
	public MarketOrder getBestBuyOrder() {
		return this.bestBuyOrder;
	}

	@Nullable
	public MarketOrder getBestSellOrder() {
		return this.bestSellOrder;
	}

	public double getMarketWidth() {
		return this.marketWidth;
	}

	public double getSellAverage() {
		return this.sellAverage;
	}

	public int getSellDeep() {
		return this.sellDeep;
	}

	public List<GetMarketsRegionIdOrders200Ok> getSellOrders() {
		return this.sellOrders;
	}

	public int getTypeId() {
		return this.typeId;
	}

	// - B U I L D E R
	public static class Builder {
		private final MarketData onConstruction;

		// - C O N S T R U C T O R S
		public Builder() {
			this.onConstruction = new MarketData();
		}

		public MarketData build() {
			Objects.requireNonNull( this.onConstruction.typeId );
			this.onConstruction.marketWidth = this.calculateMarketWidth( this.onConstruction.bestSellOrder, this.onConstruction.bestBuyOrder );
			return this.onConstruction;
		}

		public MarketData.Builder withBestBuyOrder( final MarketOrder bestBuyOrder ) {
			this.onConstruction.bestBuyOrder = bestBuyOrder;
			return this;
		}

		public MarketData.Builder withBestSellOrder( final MarketOrder bestSellOrder ) {
			this.onConstruction.bestSellOrder = bestSellOrder;
			return this;
		}

		public Builder withItemId( final int itemId ) {
			this.onConstruction.typeId = itemId;
			return this;
		}

		public MarketData.Builder withSellOrders( final List<GetMarketsRegionIdOrders200Ok> sellOrders ) {
			this.onConstruction.sellOrders = Objects.requireNonNull( sellOrders );
			this.onConstruction.sellDeep = this.calculateSellDeep( this.onConstruction.sellOrders );
			this.onConstruction.sellAverage = this.calculateSellAverage( this.onConstruction.sellOrders );
			return this;
		}

		private double calculateMarketWidth( final MarketOrder sellOrder, final MarketOrder buyOrder ) {
			if (null == sellOrder) return -1.0;
			if (null == buyOrder) return -1.0;
			return Math.abs( sellOrder.getPrice() - buyOrder.getPrice() );
		}

		private double calculateSellAverage( final List<GetMarketsRegionIdOrders200Ok> sellOrders ) {
			if (!sellOrders.isEmpty()) {
				return sellOrders.stream().mapToDouble( GetMarketsRegionIdOrders200Ok::getPrice ).average().orElse( Double.NaN );
			} else return 0.0;
		}

		/**
		 * Calculate the sell market deep that is the accumulated volume ofr all orders inside a price range. By default the price range is set to the
		 * 5% of the best sell value.
		 *
		 * @param sellOrders the orders where to search for the data values.
		 * @return the volume of sell orders for this price range.
		 */
		private int calculateSellDeep( final List<GetMarketsRegionIdOrders200Ok> sellOrders ) {
			if (!sellOrders.isEmpty()) {
				final double priceLimit = sellOrders.get( 0 ).getPrice() * MARKET_DEEP_RANGE;
				return sellOrders.stream()
						.filter( order -> order.getPrice() <= priceLimit )
						.mapToInt( GetMarketsRegionIdOrders200Ok::getVolumeRemain )
						.sum();
			} else return 0;
		}
	}
}