package org.dimensinfin.eveonline.neocom.infinity.backend.industry.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.dimensinfin.eveonline.neocom.infinity.datamanagement.industry.domain.IAction;

public class FittingIndustryJob {
	private IAction hull;
	private List<IAction> actions = new ArrayList<>();

	// - C O N S T R U C T O R S
	private FittingIndustryJob() {}

	// - G E T T E R S   &   S E T T E R S
	public List<IAction> getActions() {
		return this.actions;
	}

	public IAction getHull() {
		return this.hull;
	}

	public FittingIndustryJob addJobActionToBom( final IAction action ) {
		this.actions.add( action );
		return this;
	}

	// - B U I L D E R
	public static class Builder {
		private final FittingIndustryJob onConstruction;

		// - C O N S T R U C T O R S
		public Builder() {
			this.onConstruction = new FittingIndustryJob();
		}

		public FittingIndustryJob build() {
			Objects.requireNonNull( this.onConstruction.hull );
			return this.onConstruction;
		}

		public FittingIndustryJob.Builder withHull( final IAction hullAction ) {
			this.onConstruction.hull = Objects.requireNonNull( hullAction );
			return this;
		}
	}
}
