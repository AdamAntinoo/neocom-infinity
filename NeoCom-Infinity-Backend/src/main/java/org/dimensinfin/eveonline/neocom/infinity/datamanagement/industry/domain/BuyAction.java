package org.dimensinfin.eveonline.neocom.infinity.datamanagement.industry.domain;

import java.util.Objects;

import org.dimensinfin.eveonline.neocom.domain.space.Station;
import org.dimensinfin.eveonline.neocom.esiswagger.model.GetUniverseTypesTypeIdOk;
import org.dimensinfin.eveonline.neocom.infinity.datamanagement.domain.MarketOrder;

/**
 * A BUY action represents the data that is required to complete an item but getting it from the market. The item price to be used to calculate the
 * cost comes from the sell orders available at the region selected for the action.
 *
 * Each region has a market hub that is the preferred point to but any item. Only if there are no sell orders for that item at this hub then
 * another market place is selected and then reported.
 *
 * Price data is represented by the lowest sell price found at the target market and by the average price for all orders present on the selected
 * Region.
 */
public class BuyAction implements IAction {
	private GetUniverseTypesTypeIdOk item;
	private int quantity = 1;
	private Station corporationHome;
	private MarketOrder marketOrder;

	// - C O N S T R U C T O R S
	private BuyAction() {}

	// - G E T T E R S   &   S E T T E R S
	public Station getCorporationHome() {
		return this.corporationHome;
	}

	@Override
	public double getCost() {
		return this.quantity * this.marketOrder.getPrice();
	}

	public int getHops() {
		return 1;
	}

	public GetUniverseTypesTypeIdOk getItem() {
		return this.item;
	}

	public MarketOrder getMarketOrder() {
		return this.marketOrder;
	}

	public int getQuantity() {
		return this.quantity;
	}

	// - B U I L D E R
	public static class Builder {
		private final BuyAction onConstruction;

		// - C O N S T R U C T O R S
		public Builder() {
			this.onConstruction = new BuyAction();
		}

		public BuyAction build() {
			Objects.requireNonNull( this.onConstruction.item );
			Objects.requireNonNull( this.onConstruction.corporationHome );
			Objects.requireNonNull( this.onConstruction.marketOrder );
			return this.onConstruction;
		}

		public BuyAction.Builder withCorporationHome( final Station home ) {
			this.onConstruction.corporationHome = Objects.requireNonNull( home );
			return this;
		}

		public BuyAction.Builder withItem( final GetUniverseTypesTypeIdOk item ) {
			this.onConstruction.item = Objects.requireNonNull( item );
			return this;
		}

		public BuyAction.Builder withMarketOrder( final MarketOrder markerOrder ) {
			this.onConstruction.marketOrder = Objects.requireNonNull( markerOrder );
			return this;
		}

		public BuyAction.Builder withQuantity( final Integer quantity ) {
			if (null != quantity) this.onConstruction.quantity = quantity;
			return this;
		}
	}
}
