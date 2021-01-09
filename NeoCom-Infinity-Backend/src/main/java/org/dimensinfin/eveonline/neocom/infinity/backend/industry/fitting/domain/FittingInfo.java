package org.dimensinfin.eveonline.neocom.infinity.backend.industry.fitting.domain;

import java.util.Objects;

import org.dimensinfin.eveonline.neocom.domain.EsiType;
import org.dimensinfin.eveonline.neocom.domain.FittingV2;
import org.dimensinfin.eveonline.neocom.infinity.backend.universe.domain.EsiItem;

public class FittingInfo {
	private FittingV2 fitting;
	private EsiType hull;
	private BuildAction hullAction;

	// - C O N S T R U C T O R S
	private FittingInfo() {}

	// - G E T T E R S   &   S E T T E R S
	public FittingV2 getFitting() {
		return this.fitting;
	}

	public EsiType getHull() {
		return this.hull;
	}

	public BuildAction getHullAction() {
		return this.hullAction;
	}

	// - B U I L D E R
	public static class Builder {
		private final FittingInfo onConstruction;

		// - C O N S T R U C T O R S
		public Builder() {
			this.onConstruction = new FittingInfo();
		}

		public FittingInfo build() {
			Objects.requireNonNull( this.onConstruction.fitting );
			Objects.requireNonNull( this.onConstruction.hullAction );
			return this.onConstruction;
		}

		public FittingInfo.Builder withFitting( final FittingV2 fitting ) {
			this.onConstruction.fitting = Objects.requireNonNull( fitting );
			this.onConstruction.hull = this.onConstruction.fitting.getShipHull();
			return this;
		}

		public FittingInfo.Builder withHullAction( final BuildAction hullAction ) {
			this.onConstruction.hullAction = Objects.requireNonNull( hullAction );
			return this;
		}
	}
}
