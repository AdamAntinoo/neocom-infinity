package org.dimensinfin.eveonline.neocom.infinity.backend.industry.fitting.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.hateoas.Link;

import org.dimensinfin.eveonline.neocom.domain.Fitting;
import org.dimensinfin.eveonline.neocom.domain.FittingItem;
import org.dimensinfin.eveonline.neocom.domain.NeoItem;
import org.dimensinfin.eveonline.neocom.infinity.backend.industry.fitting.persistence.ActionPreferenceEntity;
import org.dimensinfin.eveonline.neocom.infinity.datamanagement.industry.domain.ActionType;

public class FittingBuildConfiguration {
	private FittingInfo fittingInfo;
	private List<FittingBuildContent> contents = new ArrayList<>();

	// - C O N S T R U C T O R S
	private FittingBuildConfiguration() {}

	// - B U I L D E R
	public static class Builder {
		private final FittingBuildConfiguration onConstruction;

		// - C O N S T R U C T O R S
		public Builder() {
			this.onConstruction = new FittingBuildConfiguration();
		}

		public FittingBuildConfiguration build() {
			return this.onConstruction;
		}
	}
}

final class FittingInfo {
	private String fittingBuildId;
	private Fitting fitting;
	private Link shipHullLink;
	private BuildAction hullAction;
}

class BuildAction {
	private UUID id;
	private ActionType actionType = ActionType.BUY;
	private ActionPreference action;
	private NeoItem itemTarget;
}

final class BuyBuildAction extends BuildAction {
	private MarketData marketData;
}

final class ActionPreference extends ActionPreferenceEntity {
}

final class MarketData {
}
final class FittingBuildContent{
	private FittingItem fittingitem;
	private BuildAction action;
}
