package org.dimensinfin.eveonline.neocom.infinity.backend.industry.fitting.domain;

import java.util.Objects;
import java.util.UUID;

import org.dimensinfin.eveonline.neocom.domain.FittingItem;
import org.dimensinfin.eveonline.neocom.infinity.datamanagement.industry.domain.IAction;

public class FittingBuildContent {
	private UUID id = UUID.randomUUID();
	private FittingItem fittingItem;
	private BuildAction action;

	// - C O N S T R U C T O R S
	private FittingBuildContent() {}

	// - G E T T E R S   &   S E T T E R S
	public BuildAction getAction() {
		return this.action;
	}

	public FittingItem getFittingItem() {
		return this.fittingItem;
	}

	public UUID getId() {
		return this.id;
	}

	// - B U I L D E R
	public static class Builder {
		private final FittingBuildContent onConstruction;

		// - C O N S T R U C T O R S
		public Builder() {
			this.onConstruction = new FittingBuildContent();
		}

		public FittingBuildContent build() {
			Objects.requireNonNull( this.onConstruction.fittingItem );
			Objects.requireNonNull( this.onConstruction.action );
			return this.onConstruction;
		}

		public FittingBuildContent.Builder withBuildAction( final BuildAction action ) {
			this.onConstruction.action = Objects.requireNonNull( action );
			return this;
		}

		public FittingBuildContent.Builder withFittingItem( final FittingItem fittingItem ) {
			this.onConstruction.fittingItem = Objects.requireNonNull( fittingItem );
			return this;
		}
	}
}
