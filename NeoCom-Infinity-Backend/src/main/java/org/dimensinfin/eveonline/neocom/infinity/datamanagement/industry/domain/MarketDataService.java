package org.dimensinfin.eveonline.neocom.infinity.datamanagement.industry.domain;

import org.springframework.stereotype.Service;

@Deprecated
@Service
public class MarketDataService {
// - C O N S T R U C T O R S
	private MarketDataService() {}

	// - B U I L D E R
	public static class Builder {
		private final MarketDataService onConstruction;

// - C O N S T R U C T O R S
		public Builder() {
			this.onConstruction = new MarketDataService();
		}

		public MarketDataService build() {
			return this.onConstruction;
		}
	}
}
