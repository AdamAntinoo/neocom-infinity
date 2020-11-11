package org.dimensinfin.eveonline.neocom.infinity.backend.industry.fitting.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.validation.constraints.NotNull;

import org.dimensinfin.eveonline.neocom.infinity.backend.industry.fitting.persistence.ActionPreferenceEntity;

public class FittingBuildConfiguration {
	private static final String BUILD_FITTING_ACTION_PREFIX = "FB";

	public static String uniqueIdConstructor( final @NotNull Integer pilotId, final @NotNull Integer fittingId ) {
		return BUILD_FITTING_ACTION_PREFIX + "." + pilotId + "-" + fittingId;
	}

	private String fittingBuildId;
	private FittingInfo fittingInfo;
	private List<FittingBuildContent> contents = new ArrayList<>();

	// - C O N S T R U C T O R S
	private FittingBuildConfiguration() {}

	// - G E T T E R S   &   S E T T E R S
	public List<FittingBuildContent> getContents() {
		return this.contents;
	}

	public String getFittingBuildId() {
		return this.fittingBuildId;
	}

	public FittingInfo getFittingInfo() {
		return this.fittingInfo;
	}

	public FittingBuildConfiguration addFittingConfigurationContent( final FittingBuildContent contentRecord ) {
		this.contents.add( contentRecord );
		return this;
	}

	// - B U I L D E R
	public static class Builder {
		private final FittingBuildConfiguration onConstruction;

		// - C O N S T R U C T O R S
		public Builder() {
			this.onConstruction = new FittingBuildConfiguration();
		}

		public FittingBuildConfiguration build() {
			Objects.requireNonNull( this.onConstruction.fittingBuildId );
			Objects.requireNonNull( this.onConstruction.fittingInfo );
			return this.onConstruction;
		}

		public FittingBuildConfiguration.Builder generateUniqueId( final int pilotId, final int fittingId ) {
			this.onConstruction.fittingBuildId = uniqueIdConstructor( pilotId, fittingId );
			return this;
		}

		public FittingBuildConfiguration.Builder withFittingInfo( final FittingInfo fittingInfo ) {
			this.onConstruction.fittingInfo = Objects.requireNonNull( fittingInfo );
			return this;
		}
	}
}

final class ActionPreference extends ActionPreferenceEntity {
}

final class MarketData {
}
