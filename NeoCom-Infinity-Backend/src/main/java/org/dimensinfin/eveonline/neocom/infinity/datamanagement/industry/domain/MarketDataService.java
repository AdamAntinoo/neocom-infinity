package org.dimensinfin.eveonline.neocom.infinity.datamanagement.industry.domain;

import org.springframework.stereotype.Service;

@Service
public class MarketDataService {
	private MarketDataService() {}

	// - B U I L D E R
	public static class Builder {
		private final MarketDataService onConstruction;

		public Builder() {
			this.onConstruction = new MarketDataService();
		}

		public MarketDataService build() {
			return this.onConstruction;
		}
	}
}
