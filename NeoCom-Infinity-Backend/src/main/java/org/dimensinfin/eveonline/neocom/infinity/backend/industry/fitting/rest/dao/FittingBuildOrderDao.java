package org.dimensinfin.eveonline.neocom.infinity.backend.industry.fitting.rest.dao;

import java.util.Objects;

import org.springframework.hateoas.Link;

public class FittingBuildOrderDao {
	private Link savedBuildData;
	private Link targetBuildData;
	//	private FittingIndustryJob fittingJob;

	// - C O N S T R U C T O R S
	private FittingBuildOrderDao() {}

	//	public FittingIndustryJob getFittingJob() {
	//		return this.fittingJob;
	//	}

	public Link getSavedBuildData() {
		return this.savedBuildData;
	}

	public Link getTargetBuildData() {
		return this.targetBuildData;
	}

	// - B U I L D E R
	public static class Builder {
		private final FittingBuildOrderDao onConstruction;

		// - C O N S T R U C T O R S
		public Builder() {
			this.onConstruction = new FittingBuildOrderDao();
		}

		public FittingBuildOrderDao build() {
			//			Objects.requireNonNull( this.onConstruction.fittingJob );
			return this.onConstruction;
		}

		//		public FittingBuildOrderDao.Builder withFittingJob( final FittingIndustryJob fittingJob ) {
		//			this.onConstruction.fittingJob = Objects.requireNonNull( fittingJob );
		//			return this;
		//		}
		public FittingBuildOrderDao.Builder withSavedLink( final Link saved ) {
			this.onConstruction.savedBuildData = Objects.requireNonNull( saved );
			return this;
		}

		public FittingBuildOrderDao.Builder withTargetLink( final Link target ) {
			this.onConstruction.targetBuildData = Objects.requireNonNull( target );
			return this;
		}
	}
}
