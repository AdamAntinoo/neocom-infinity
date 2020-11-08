package org.dimensinfin.eveonline.neocom.infinity.backend.industry.fitting.service;

import java.util.List;
import java.util.Objects;

import org.springframework.stereotype.Service;

import org.dimensinfin.eveonline.neocom.esiswagger.model.CharacterscharacterIdfittingsItems;
import org.dimensinfin.eveonline.neocom.esiswagger.model.GetCharactersCharacterIdFittings200Ok;
import org.dimensinfin.eveonline.neocom.infinity.backend.industry.domain.FittingIndustryJob;
import org.dimensinfin.eveonline.neocom.infinity.backend.industry.fitting.persistence.ActionPreferenceEntity;
import org.dimensinfin.eveonline.neocom.infinity.datamanagement.industry.processor.IndustryBuildProcessor;

@Service
public class FittingBuildTransformationService {
	private IndustryBuildProcessor industryBuildProcessor;
	private GetCharactersCharacterIdFittings200Ok fittingData;
	private List<ActionPreferenceEntity> preferences;

	// - C O N S T R U C T O R S
	private FittingBuildTransformationService() {}

	public FittingIndustryJob transformInitialState() {
		final FittingIndustryJob fittingJob = new FittingIndustryJob.Builder()
				.withHull( this.industryBuildProcessor.generateBuyAction( this.fittingData.getShipTypeId(), 1 ) )
				.build(); // Create a new industry job.
		for (CharacterscharacterIdfittingsItems fittingItem : this.fittingData.getItems()) {
			fittingJob.addJobActionToBom(
					this.industryBuildProcessor.generateBuyAction( fittingItem.getTypeId(), fittingItem.getQuantity() )
			);
		}
		return fittingJob;
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
			Objects.requireNonNull( this.onConstruction.fittingData );
			Objects.requireNonNull( this.onConstruction.preferences );
			return this.onConstruction;
		}

		public FittingBuildTransformationService.Builder withFitting( final GetCharactersCharacterIdFittings200Ok targetFitting ) {
			this.onConstruction.fittingData = Objects.requireNonNull( targetFitting );
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
