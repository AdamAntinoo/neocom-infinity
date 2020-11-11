package org.dimensinfin.eveonline.neocom.infinity.backend.industry.fitting.domain;

import java.util.Objects;

import org.springframework.hateoas.Link;

public class FittingConfigurations {
	private Link savedBuildData;
	private Link targetBuildData;

	// - C O N S T R U C T O R S
	private FittingConfigurations() {}

	// - G E T T E R S   &   S E T T E R S
	public Link getSavedBuildData() {
		return this.savedBuildData;
	}

	public Link getTargetBuildData() {
		return this.targetBuildData;
	}

	// - B U I L D E R
	public static class Builder {
		private final FittingConfigurations onConstruction;

		// - C O N S T R U C T O R S
		public Builder() {
			this.onConstruction = new FittingConfigurations();
		}

		public FittingConfigurations build() {
			return this.onConstruction;
		}

		public FittingConfigurations.Builder withSavedLink( final Link saved ) {
			this.onConstruction.savedBuildData = Objects.requireNonNull( saved );
			return this;
		}

		public FittingConfigurations.Builder withTargetLink( final Link target ) {
			this.onConstruction.targetBuildData = Objects.requireNonNull( target );
			return this;
		}
	}
}
