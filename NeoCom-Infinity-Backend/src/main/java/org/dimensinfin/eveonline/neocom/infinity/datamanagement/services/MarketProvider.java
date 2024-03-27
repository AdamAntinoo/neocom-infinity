package org.dimensinfin.eveonline.neocom.infinity.datamanagement.services;

import java.util.List;
import java.util.Objects;

import org.dimensinfin.eveonline.neocom.domain.space.SpaceRegion;
import org.dimensinfin.eveonline.neocom.esiswagger.model.GetMarketsRegionIdOrders200Ok;
import org.dimensinfin.eveonline.neocom.provider.ESIUniverseDataProvider;

public class MarketProvider {
	private ESIUniverseDataProvider esiUniverseDataProvider;
	private SpaceRegion marketRegion;

	// - C O N S T R U C T O R S
	private MarketProvider() {}

	/**
	 * Returns all the orders for a item type on a market region. This method does not performs any filtering.
	 *
	 * @return the complete list of orders for the selected type.
	 */
	public List<GetMarketsRegionIdOrders200Ok> getOrders( final Integer typeId ) {
		return this.esiUniverseDataProvider.getUniverseMarketOrdersForId( this.marketRegion.getRegionId(), typeId );
	}

	// - B U I L D E R
	public static class Builder {
		private final MarketProvider onConstruction;

		// - C O N S T R U C T O R S
		public Builder() {
			this.onConstruction = new MarketProvider();
		}

		public MarketProvider build() {
			Objects.requireNonNull( this.onConstruction.esiUniverseDataProvider );
			Objects.requireNonNull( this.onConstruction.marketRegion );
			return this.onConstruction;
		}

		public MarketProvider.Builder withEsiDataProvider( final ESIUniverseDataProvider esiDataProvider ) {
			this.onConstruction.esiUniverseDataProvider = Objects.requireNonNull( esiDataProvider );
			return this;
		}

		public MarketProvider.Builder withRegion( final SpaceRegion region ) {
			this.onConstruction.marketRegion = Objects.requireNonNull( region );
			return this;
		}
	}
}
