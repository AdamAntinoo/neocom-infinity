package org.dimensinfin.eveonline.neocom.infinity.backend.industry.fitting.service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.dimensinfin.eveonline.neocom.domain.Fitting;
import org.dimensinfin.eveonline.neocom.domain.FittingItem;
import org.dimensinfin.eveonline.neocom.esiswagger.model.CharacterscharacterIdfittingsItems;
import org.dimensinfin.eveonline.neocom.infinity.backend.character.fitting.domain.FittingItemModel;
import org.dimensinfin.eveonline.neocom.infinity.backend.character.fitting.domain.FittingModel;
import org.dimensinfin.eveonline.neocom.infinity.backend.industry.domain.FittingIndustryJob;
import org.dimensinfin.eveonline.neocom.infinity.backend.industry.fitting.persistence.ActionPreferenceEntity;
import org.dimensinfin.eveonline.neocom.infinity.datamanagement.industry.processor.IndustryBuildProcessor;

public class FittingBuildTransformationService {
	private IndustryBuildProcessor industryBuildProcessor;
	private FittingModel fitting;
	private List<ActionPreferenceEntity> preferences;

	// - C O N S T R U C T O R S
	private FittingBuildTransformationService() {}

	public FittingIndustryJob transformInitialState() {
		final List<ActionPreferenceEntity> actions = this.preferences.stream()
				.filter( action -> action.isSaved() )
				.collect( Collectors.toList() );
		final FittingIndustryJob fittingJob = new FittingIndustryJob.Builder()
				.withFitting( this.fitting )
				.withHull( this.industryBuildProcessor.generateBuyAction( this.fitting.getShipTypeId(), 1 ) )
				.build(); // Create a new industry job.
		for (FittingItemModel fittingItem : this.fitting.getFittingItems()) {
			fittingJob.addJobActionToBom(
					this.industryBuildProcessor.generateBuyAction( fittingItem.getTypeId(), fittingItem.getQuantity() )
			);
		}
		return fittingJob;
	}

	public FittingIndustryJob transformTargetState() {
		return this.transformInitialState();
	}

	// - B U I L D E R
	public static class Builder {
		private final FittingBuildTransformationService onConstruction;

		// - C O N S T R U C T O R S
		public Builder() {
			this.onConstruction = new FittingBuildTransformationService();
		}

		public FittingBuildTransformationService build() {
			Objects.requireNonNull( this.onConstruction.industryBuildProcessor );
			Objects.requireNonNull( this.onConstruction.fitting );
			Objects.requireNonNull( this.onConstruction.preferences );
			return this.onConstruction;
		}

		public FittingBuildTransformationService.Builder withFitting( final FittingModel targetFitting ) {
			this.onConstruction.fitting = Objects.requireNonNull( targetFitting );
			return this;
		}

		public FittingBuildTransformationService.Builder withIndustryBuildProcessor( final IndustryBuildProcessor industryBuildProcessor ) {
			this.onConstruction.industryBuildProcessor = Objects.requireNonNull( industryBuildProcessor );
			return this;
		}

		public FittingBuildTransformationService.Builder withPreferences( final List<ActionPreferenceEntity> actionPreferences ) {
			this.onConstruction.preferences = Objects.requireNonNull( actionPreferences );
			return this;
		}
	}
}
