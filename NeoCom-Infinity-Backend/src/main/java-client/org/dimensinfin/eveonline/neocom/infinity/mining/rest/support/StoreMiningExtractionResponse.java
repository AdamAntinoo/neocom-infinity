package org.dimensinfin.eveonline.neocom.infinity.mining.rest.support;

import java.util.Objects;

public class StoreMiningExtractionResponse {
	private Integer miningExtractionsCount;

	private StoreMiningExtractionResponse() {}

	public Integer getMiningExtractionsCount() {
		return this.miningExtractionsCount;
	}

	// - B U I L D E R
	public static class Builder {
		private StoreMiningExtractionResponse onConstruction;

		public Builder() {
			this.onConstruction = new StoreMiningExtractionResponse();
		}

		public StoreMiningExtractionResponse build() {
			return this.onConstruction;
		}

		public StoreMiningExtractionResponse.Builder withMiningExtractionsCount( final Integer miningExtractionsCount ) {
			Objects.requireNonNull( miningExtractionsCount );
			this.onConstruction.miningExtractionsCount = miningExtractionsCount;
			return this;
		}
	}
}
