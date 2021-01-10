package org.dimensinfin.eveonline.neocom.infinity.backend.industry.fitting.domain;

import java.util.Objects;

import org.dimensinfin.eveonline.neocom.domain.space.Station;
import org.dimensinfin.eveonline.neocom.infinity.backend.market.domain.MarketOrder;

public class BuyBuildAction extends BuildAction {
	private Station corporationHome;
	private MarketOrder marketOrder;

	// - C O N S T R U C T O R S
	protected BuyBuildAction() {}

	// - G E T T E R S   &   S E T T E R S
	// - I A C T I O N
	public Station getCorporationHome() {
		return this.corporationHome;
	}

	@Override
	public double getCost() {
		return 0;
	}

	public MarketOrder getMarketOrder() {
		return this.marketOrder;
	}

	// - B U I L D E R
	public static class Builder extends BuildAction.Builder<BuyBuildAction, BuyBuildAction.Builder> {
		private BuyBuildAction onConstruction;

		// - C O N S T R U C T O R S
		public Builder() {
			this.onConstruction = new BuyBuildAction();
		}

		// - G E T T E R S   &   S E T T E R S
		@Override
		protected BuyBuildAction getActual() {
			if (null == this.onConstruction)
				this.onConstruction = new BuyBuildAction();
			return this.onConstruction;
		}

		@Override
		protected BuyBuildAction.Builder getActualBuilder() {
			return this;
		}

		public BuyBuildAction build() {
			return this.onConstruction;
		}

		public BuyBuildAction.Builder withCorporationHome( final Station corporationHome ) {
			this.getActual().corporationHome = Objects.requireNonNull( corporationHome );
			return this.getActualBuilder();
		}

		public BuyBuildAction.Builder withMarketOrder( final MarketOrder marketOrder ) {
			this.getActual().marketOrder = Objects.requireNonNull( marketOrder );
			return this.getActualBuilder();
		}
	}
}
