package org.dimensinfin.eveonline.neocom.loyalty.domain;

/**
 * @author Adam Antinoo (adamantinoo.git@gmail.com)
 * @since 0.20.0
 */
public class LoyaltyServiceConfiguration {
	private Integer marketRegionId;

	// - C O N S T R U C T O R S
	private LoyaltyServiceConfiguration() {}

	// - G E T T E R S   &   S E T T E R S
	public Integer getMarketRegionId() {
		return this.marketRegionId;
	}

	public LoyaltyServiceConfiguration setMarketRegionId( final Integer marketRegionId ) {
		this.marketRegionId = marketRegionId;
		return this;
	}

	// - B U I L D E R
	public static class Builder {
		private final LoyaltyServiceConfiguration onConstruction;

// - C O N S T R U C T O R S
		public Builder() {
			this.onConstruction = new LoyaltyServiceConfiguration();
		}

		public LoyaltyServiceConfiguration build() {
			return this.onConstruction;
		}

		public LoyaltyServiceConfiguration.Builder withMarketRegionId( final Integer marketRegionId ) {
			if (null != marketRegionId) this.onConstruction.marketRegionId = marketRegionId;
			return this;
		}
	}
}