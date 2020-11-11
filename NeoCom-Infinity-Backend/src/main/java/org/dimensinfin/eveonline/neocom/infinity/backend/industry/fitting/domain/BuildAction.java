package org.dimensinfin.eveonline.neocom.infinity.backend.industry.fitting.domain;

import java.util.Objects;
import java.util.UUID;

import org.dimensinfin.eveonline.neocom.esiswagger.model.GetUniverseTypesTypeIdOk;
import org.dimensinfin.eveonline.neocom.infinity.datamanagement.industry.domain.ActionType;
import org.dimensinfin.eveonline.neocom.infinity.datamanagement.industry.domain.IAction;

public abstract class BuildAction implements IAction {
	protected ActionType actionType = ActionType.BUY;
	protected GetUniverseTypesTypeIdOk itemTarget;
	protected int quantity = 1;
	protected ActionPreference action;
	private UUID id = UUID.randomUUID();

	// - C O N S T R U C T O R S
	protected BuildAction() {}

	// - G E T T E R S   &   S E T T E R S
	public ActionType getActionType() {
		return this.actionType;
	}

	public BuildAction setActionType( final ActionType actionType ) {
		this.actionType = actionType;
		return this;
	}

	// - B U I L D E R
	protected static abstract class Builder<T extends BuildAction, B extends BuildAction.Builder> {
		protected B actualClassBuilder;

		// - C O N S T R U C T O R S
		public Builder() {
			this.actualClassBuilder = getActualBuilder();
		}

		// - G E T T E R S   &   S E T T E R S
		protected abstract T getActual();

		protected abstract B getActualBuilder();

		public T build() {
			return this.getActual();
		}

		public B withActionPreference( final ActionPreference action ) {
			this.getActual().action = Objects.requireNonNull( action );
			return this.actualClassBuilder;
		}

		public B withItem( final GetUniverseTypesTypeIdOk itemTarget ) {
			this.getActual().itemTarget = Objects.requireNonNull( itemTarget );
			return this.actualClassBuilder;
		}

		public B withQuantity( final int quantity ) {
			if (quantity > 0) this.getActual().quantity = quantity;
			return this.actualClassBuilder;
		}
	}
}
