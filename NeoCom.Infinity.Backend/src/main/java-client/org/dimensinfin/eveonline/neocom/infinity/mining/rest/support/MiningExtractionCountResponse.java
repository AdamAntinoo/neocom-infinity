package org.dimensinfin.eveonline.neocom.infinity.mining.rest.support;

import java.util.Objects;

public class MiningExtractionCountResponse {
	private Integer miningExtractionCount;

	private MiningExtractionCountResponse() {}

	public Integer getMiningExtractionCount() {
		return this.miningExtractionCount;
	}

	// - B U I L D E R
	public static class Builder {
		private MiningExtractionCountResponse onConstruction;

		public Builder() {
			this.onConstruction = new MiningExtractionCountResponse();
		}

		public MiningExtractionCountResponse build() {
			return this.onConstruction;
		}

		public MiningExtractionCountResponse.Builder withMiningExtractionCount( final Integer miningExtractionCount ) {
			Objects.requireNonNull( miningExtractionCount );
			this.onConstruction.miningExtractionCount = miningExtractionCount;
			return this;
		}
	}
}
