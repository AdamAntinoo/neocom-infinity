package org.dimensinfin.eveonline.neocom.infinity.backend.industry.rest.dao;

import java.util.ArrayList;
import java.util.List;

import org.dimensinfin.eveonline.neocom.infinity.datamanagement.industry.domain.IAction;

public class FittingIndustryJobDao {
	private IAction hull;
	private List<IAction> actions = new ArrayList<>();

	// - C O N S T R U C T O R S
	private FittingIndustryJobDao() {}

	// - B U I L D E R
	public static class Builder {
		private final FittingIndustryJobDao onConstruction;

		// - C O N S T R U C T O R S
		public Builder() {
			this.onConstruction = new FittingIndustryJobDao();
		}

		public FittingIndustryJobDao build() {
			return this.onConstruction;
		}
	}
}
