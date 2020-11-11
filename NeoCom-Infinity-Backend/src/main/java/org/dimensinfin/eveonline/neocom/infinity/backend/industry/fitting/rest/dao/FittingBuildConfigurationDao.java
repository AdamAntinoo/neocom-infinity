package org.dimensinfin.eveonline.neocom.infinity.backend.industry.fitting.rest.dao;

import java.util.Objects;

import org.springframework.hateoas.Link;

public class FittingBuildConfigurationDao {
	private Link savedBuildData;
	private Link targetBuildData;

	// - C O N S T R U C T O R S
	private FittingBuildConfigurationDao() {}

	// - G E T T E R S   &   S E T T E R S
	public Link getSavedBuildData() {
		return this.savedBuildData;
	}

	public Link getTargetBuildData() {
		return this.targetBuildData;
	}

	// - B U I L D E R
	public static class Builder {
		private final FittingBuildConfigurationDao onConstruction;

		// - C O N S T R U C T O R S
		public Builder() {
			this.onConstruction = new FittingBuildConfigurationDao();
		}

		public FittingBuildConfigurationDao build() {
			return this.onConstruction;
		}

		public FittingBuildConfigurationDao.Builder withSavedLink( final Link saved ) {
			this.onConstruction.savedBuildData = Objects.requireNonNull( saved );
			return this;
		}

		public FittingBuildConfigurationDao.Builder withTargetLink( final Link target ) {
			this.onConstruction.targetBuildData = Objects.requireNonNull( target );
			return this;
		}
	}
}
