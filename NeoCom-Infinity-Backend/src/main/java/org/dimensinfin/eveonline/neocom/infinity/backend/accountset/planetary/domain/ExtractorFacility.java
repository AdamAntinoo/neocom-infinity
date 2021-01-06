package org.dimensinfin.eveonline.neocom.infinity.backend.accountset.planetary.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.joda.time.DateTime;

import org.dimensinfin.core.interfaces.ICollaboration;
import org.dimensinfin.eveonline.neocom.domain.NeoItem;
import org.dimensinfin.eveonline.neocom.esiswagger.model.GetCharactersCharacterIdPlanetsPlanetIdOkExtractorDetails;
import org.dimensinfin.eveonline.neocom.esiswagger.model.GetCharactersCharacterIdPlanetsPlanetIdOkPins;
import org.dimensinfin.eveonline.neocom.planetary.domain.PlanetaryFacility;

import liquibase.pro.packaged.R;

public class ExtractorFacility extends PlanetaryFacilityWrapper {
	public enum ExtractorState {
		EXTRACTING, IDLE
	}

	private GetCharactersCharacterIdPlanetsPlanetIdOkExtractorDetails extractorDetails;
	private ExtractorState state = ExtractorState.IDLE;
	private DateTime installTime;
	private DateTime expiryTime;

	private NeoItem extractionType;

	// - G E T T E R S   &   S E T T E R S
	public ExtractorState getState() {
		return this.state;
	}

	public ExtractorFacility setInstallTime( final DateTime installTime ) {
//		this.planetaryFacility.pinDelegate.setInstallTime(installTime);
		this.state = ExtractorState.EXTRACTING;
		return this;
	}

	public Integer getCycleTime() {
		if (this.state == ExtractorState.EXTRACTING)
			return this.extractorDetails.getCycleTime();
		else return 0;
	}

	/**
	 * Extractors can be extracting cycling until the whole extraction cycle completes. This long cycle can last for days but the small
	 * cycle is about to last 2 or 3 hours.
	 * Extractors can be stopped so the cycle information may be invalid when the complete cycle is not specified.
	 *
	 * @return the date time of the last cycle start instant.
	 */
	public DateTime getLastCycleStart() {
		return this.planetaryFacility.getLastCycleStart();
	}

	public String getExtractingResourceName() {
		//		final GetCharactersCharacterIdPlanetsPlanetIdOkExtractorDetails details = this.extractorDetails();
//		this.extractionType = new EveItem(this.extractorDetails.getProductTypeId());
		return this.extractionType.getName();
	}

	public String getExtractingResourceURLForItem() {
		return this.extractionType.getTypeIconURL();
	}

	public int getNextOutput() {
		if (null != this.extractorDetails) return this.extractorDetails.getQtyPerCycle();
		else return 0;
	}

	public String getCycleExpirationTime() {
		return this.expiryTime.toString();
	}

	public int getHeadsCount() {
		if (null != this.extractorDetails) return this.extractorDetails.getHeads().size();
		else return 0;
	}

//	@Override
//	public int getIconReferenceId() {
//		return R.drawable.extractor80_white;
//	}
//
//	@Override
//	public int getIconColorReference() {
//		return R.color.pi_extractoriconcolor;
//	}

	@Override
	public int getCpuUsage() {
		int cpu = super.getCpuUsage();
		cpu += this.extractorDetails.getHeads().size() * 110;
		return cpu;
	}

	@Override
	public int getPowerUsage() {
		int power = super.getPowerUsage();
		power += this.extractorDetails.getHeads().size() * 550;
		return power;
	}

	// - I C O L L A B O R A T I O N
	@Override
	public List<ICollaboration> collaborate2Model( final String variant ) {
		return new ArrayList<>();
	}

	// - B U I L D E R
	public static class Builder {
		private ExtractorFacility onConstruction;

		public Builder() {
			this.onConstruction = new ExtractorFacility();
		}

		public Builder withPlanetaryFacility( final PlanetaryFacility planetaryFacility ) {
			this.onConstruction.planetaryFacility = planetaryFacility;
			return this;
		}

		public Builder withExtractorDetails( final GetCharactersCharacterIdPlanetsPlanetIdOkPins pin ) {
			// Read facility data to check the extractor state.
			this.onConstruction.extractorDetails = pin.getExtractorDetails();
			this.onConstruction.installTime = pin.getInstallTime();
			this.onConstruction.expiryTime = pin.getExpiryTime();
//			if (null != this.onConstruction.installTime) {
//				// Check expired extractors
//				final DateTime expiryTime = pin.getExpiryTime();
//				if (null == expiryTime) return this;
//				if (expiryTime.isBefore(DateTime.now())) return this;
////				final Integer extractTypeId = this.onConstruction.extractorDetails.getProductTypeId();
////				if (null == extractTypeId) return this;
////				this.onConstruction.extractionType = new EveItem(extractTypeId);
//				this.onConstruction.state = ExtractorState.EXTRACTING;
//			}
			return this;
		}

		public ExtractorFacility build() {
			Objects.requireNonNull(this.onConstruction.planetaryFacility);
			Objects.requireNonNull(this.onConstruction.extractorDetails);
			this.updateExtractorStatus();
			this.setExtractionProductType();
			return this.onConstruction;
		}

		private void updateExtractorStatus() {
			this.onConstruction.state = ExtractorState.IDLE;
			if (null != this.onConstruction.installTime) {
				if (null == this.onConstruction.expiryTime) return;
				if (this.onConstruction.expiryTime.isBefore(DateTime.now())) return;
				this.onConstruction.state = ExtractorState.EXTRACTING;
			}
		}

		private void setExtractionProductType() {
			final Integer extractTypeId = this.onConstruction.extractorDetails.getProductTypeId();
			if (null == extractTypeId) return;
//			this.onConstruction.extractionType = new EveItem(extractTypeId);
		}
	}
}
