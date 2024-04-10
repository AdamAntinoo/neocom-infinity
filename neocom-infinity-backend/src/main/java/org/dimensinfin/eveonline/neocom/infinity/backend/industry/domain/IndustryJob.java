package org.dimensinfin.eveonline.neocom.infinity.backend.industry.domain;

import org.dimensinfin.eveonline.neocom.domain.EsiType;
import org.dimensinfin.eveonline.neocom.domain.Pilot;
import org.dimensinfin.eveonline.neocom.domain.space.SpaceLocation;
import org.dimensinfin.eveonline.neocom.industry.persistence.JobEntity;

public class IndustryJob {
	private JobEntity job;
	private Pilot installer;
	private EsiType blueprintItem;
	private SpaceLocation blueprintLocation;
	private SpaceLocation jobLocation;
	private EsiType productItem = null;

	// - C O N S T R U C T O R S
	private IndustryJob() {}

	// - B U I L D E R
	public static class Builder {
		private final IndustryJob onConstruction;

		// - C O N S T R U C T O R S
		public Builder() {
			this.onConstruction = new IndustryJob();
		}

		public IndustryJob build() {
			return this.onConstruction;
		}
	}
}