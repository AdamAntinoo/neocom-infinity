package org.dimensinfin.eveonline.neocom.infinity.datamanagement.industry.domain;

public class Action {
	// - C O N S T R U C T O R S
	private Action() {}

	// - B U I L D E R
	public static class Builder {
		private final Action onConstruction;

		// - C O N S T R U C T O R S
		public Builder() {
			this.onConstruction = new Action();
		}

		public Action build() {
			return this.onConstruction;
		}
	}
}
