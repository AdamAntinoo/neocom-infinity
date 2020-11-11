package org.dimensinfin.eveonline.neocom.infinity.backend.industry.fitting.service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.dimensinfin.eveonline.neocom.domain.FittingItem;
import org.dimensinfin.eveonline.neocom.domain.FittingV2;
import org.dimensinfin.eveonline.neocom.infinity.backend.industry.fitting.domain.FittingBuildConfiguration;
import org.dimensinfin.eveonline.neocom.infinity.backend.industry.fitting.domain.FittingBuildContent;
import org.dimensinfin.eveonline.neocom.infinity.backend.industry.fitting.domain.FittingInfo;
import org.dimensinfin.eveonline.neocom.infinity.backend.industry.fitting.persistence.ActionPreferenceEntity;
import org.dimensinfin.eveonline.neocom.infinity.datamanagement.industry.processor.IndustryBuildProcessor;

public class FittingBuildConfigurationGenerator {
	private IndustryBuildProcessor industryBuildProcessor;
	private FittingV2 fitting;
	private List<ActionPreferenceEntity> preferences;

	// - C O N S T R U C T O R S
	private FittingBuildConfigurationGenerator() {}

	public FittingBuildConfiguration transformInitialState( final int pilotId ) {
		final List<ActionPreferenceEntity> actions = this.preferences.stream()
				.filter( action -> action.isSaved() )
				.collect( Collectors.toList() );
		final FittingBuildConfiguration fittingConfiguration = new FittingBuildConfiguration.Builder()
				.generateUniqueId( pilotId, this.fitting.getFittingId() )
				.withFittingInfo(
						new FittingInfo.Builder()
								.withFitting( this.fitting )
								.withHullAction( this.industryBuildProcessor.generateBuyAction( this.fitting.getShipTypeId(), 1 ) )
								.build()
				)
				//				.withHull( this.industryBuildProcessor.generateBuyAction( this.fitting.getShipTypeId(), 1 ) )
				.build(); // Create a new fitting configuration
		for (FittingItem fittingItem : this.fitting.getFittingItems()) {
			fittingConfiguration.addFittingConfigurationContent(
					new FittingBuildContent.Builder()
							.withFittingItem( fittingItem )
							.withBuildAction( this.industryBuildProcessor.generateBuyAction( fittingItem.getTypeId(), fittingItem.getQuantity() ) )
							.build()
			);
		}
		return fittingConfiguration;
	}

	public FittingBuildConfiguration transformTargetState( final int pilotId ) {
		return this.transformInitialState( pilotId );
	}

	// - B U I L D E R
	public static class Builder {
		private final FittingBuildConfigurationGenerator onConstruction;

		// - C O N S T R U C T O R S
		public Builder() {
			this.onConstruction = new FittingBuildConfigurationGenerator();
		}

		public FittingBuildConfigurationGenerator build() {
			Objects.requireNonNull( this.onConstruction.industryBuildProcessor );
			Objects.requireNonNull( this.onConstruction.fitting );
			Objects.requireNonNull( this.onConstruction.preferences );
			return this.onConstruction;
		}

		public FittingBuildConfigurationGenerator.Builder withFitting( final FittingV2 targetFitting ) {
			this.onConstruction.fitting = Objects.requireNonNull( targetFitting );
			return this;
		}

		public FittingBuildConfigurationGenerator.Builder withIndustryBuildProcessor( final IndustryBuildProcessor industryBuildProcessor ) {
			this.onConstruction.industryBuildProcessor = Objects.requireNonNull( industryBuildProcessor );
			return this;
		}

		public FittingBuildConfigurationGenerator.Builder withPreferences( final List<ActionPreferenceEntity> actionPreferences ) {
			this.onConstruction.preferences = Objects.requireNonNull( actionPreferences );
			return this;
		}
	}
}
