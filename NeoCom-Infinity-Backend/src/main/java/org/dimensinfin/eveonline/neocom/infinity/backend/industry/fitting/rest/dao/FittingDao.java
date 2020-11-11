package org.dimensinfin.eveonline.neocom.infinity.backend.industry.fitting.rest.dao;

import java.util.ArrayList;
import java.util.List;

import org.dimensinfin.eveonline.neocom.domain.FittingItem;
import org.dimensinfin.eveonline.neocom.infinity.backend.universe.domain.EsiItem;

public class FittingDao {
	private Integer fittingId;
	private String name;
	private String description;
	private EsiItem shipHull;
	private final List<FittingItem> fittingItems = new ArrayList<>();

	// - C O N S T R U C T O R S
	private FittingDao() {}

	// - B U I L D E R
	public static class Builder {
		private final FittingDao onConstruction;

		// - C O N S T R U C T O R S
		public Builder() {
			this.onConstruction = new FittingDao();
		}

		public FittingDao build() {
			return this.onConstruction;
		}
	}
}
