package org.dimensinfin.eveonline.neocom.infinity.backend.industry.domain;

import javax.validation.constraints.NotNull;

import org.dimensinfin.eveonline.neocom.infinity.backend.universe.domain.EsiItemModel;
import org.dimensinfin.eveonline.neocom.infinity.datamanagement.industry.domain.ActionType;

public class IndustryAction {
	private ActionType action = ActionType.BUY;

	// - C O N S T R U C T O R S
	private IndustryAction() {}

	// - B U I L D E R
	public static class Builder {
		private final IndustryAction onConstruction;

		// - C O N S T R U C T O R S
		public Builder() {
			this.onConstruction = new IndustryAction();
		}

		public IndustryAction build() {
			return this.onConstruction;
		}

		public IndustryAction.Builder withAction( final @NotNull ActionType actionType ) {
			this.onConstruction.action = actionType;
			return this;
		}

		public IndustryAction.Builder withItem( final EsiItemModel itemModel ) {
			return null;
		}
	}
}
