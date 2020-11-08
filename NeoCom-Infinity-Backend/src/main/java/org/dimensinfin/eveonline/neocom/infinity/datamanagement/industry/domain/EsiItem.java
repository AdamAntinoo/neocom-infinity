package org.dimensinfin.eveonline.neocom.infinity.datamanagement.industry.domain;

public class EsiItem {
	private EsiItem() {}

	// - B U I L D E R
	public static class Builder {
		private final EsiItem onConstruction;

		public Builder() {
			this.onConstruction = new EsiItem();
		}

		public EsiItem build() {
			return this.onConstruction;
		}
	}
}
