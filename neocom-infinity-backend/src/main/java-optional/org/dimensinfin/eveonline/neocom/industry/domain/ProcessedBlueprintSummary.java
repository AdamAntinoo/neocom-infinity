package org.dimensinfin.eveonline.neocom.industry.domain;

import org.dimensinfin.eveonline.neocom.domain.EsiType;
import org.dimensinfin.eveonline.neocom.domain.space.Station;
import org.dimensinfin.eveonline.neocom.utility.NeoObjects;

/**
 * Contains an abstract for the full processed blueprint data. Enough to report the user the quality of the blueprint but not all the details.
 * If a user has dozens of blueprints the amount of data to transfer and process will be high and time consuming. Better transfer an abstract and
 * then select a target that will retrieve the full data.
 *
 * @author Adam Antinoo (adamantinoo.git@gmail.com)
 * @since 0.20.0
 */
public class ProcessedBlueprintSummary {
	private String uid;
	private Integer blueprintTypeId;
	private String blueprintTypeName;
	private String blueprintTypeIconURL;
	private Integer outputTypeId;
	private String outputTypeName;
	private String outputTypeIconURL;
	private Double outputPrice;
	private Station tradeStation;
	private Double manufactureMaterialCost;
	private Double costIndex;

	// - C O N S T R U C T O R S
	private ProcessedBlueprintSummary() {}

	// - G E T T E R S   &   S E T T E R S
	public String getBlueprintTypeIconURL() {
		return this.blueprintTypeIconURL;
	}

	public Integer getBlueprintTypeId() {
		return this.blueprintTypeId;
	}

	public String getBlueprintTypeName() {
		return this.blueprintTypeName;
	}

	public Double getCostIndex() {
		return this.costIndex;
	}

	public Double getManufactureMaterialCost() {
		return this.manufactureMaterialCost;
	}

	public Double getOutputPrice() {
		return this.outputPrice;
	}

	public String getOutputTypeIconURL() {
		return this.outputTypeIconURL;
	}

	public Integer getOutputTypeId() {
		return this.outputTypeId;
	}

	public String getOutputTypeName() {
		return this.outputTypeName;
	}

	public Station getTradeStation() {
		return this.tradeStation;
	}

	public String getUid() {
		return this.uid;
	}

	// - B U I L D E R
	public static class Builder {
		private final ProcessedBlueprintSummary onConstruction;

		// - C O N S T R U C T O R S
		public Builder() {
			this.onConstruction = new ProcessedBlueprintSummary();
		}

		public ProcessedBlueprintSummary build() {
			NeoObjects.requireNonNull( this.onConstruction.uid );
			NeoObjects.requireNonNull( this.onConstruction.tradeStation );
			NeoObjects.requireNonNull( this.onConstruction.manufactureMaterialCost );
			NeoObjects.requireNonNull( this.onConstruction.costIndex );
			return this.onConstruction;
		}

		public ProcessedBlueprintSummary.Builder withBlueprint( final EsiType blueprint ) {
			if (null != blueprint) {
				this.onConstruction.blueprintTypeId = blueprint.getTypeId();
				this.onConstruction.blueprintTypeName = blueprint.getName();
				this.onConstruction.blueprintTypeIconURL = blueprint.getTypeIconURL();
			}
			return this;
		}

		public ProcessedBlueprintSummary.Builder withCostIndex( final Double costIndex ) {
			this.onConstruction.costIndex = NeoObjects.requireNonNull( costIndex );
			return this;
		}

		public ProcessedBlueprintSummary.Builder withManufactureCost( final Double cost ) {
			this.onConstruction.manufactureMaterialCost = NeoObjects.requireNonNull( cost );
			return this;
		}

		public ProcessedBlueprintSummary.Builder withOutput( final EsiType output ) {
			if (null != output) {
				this.onConstruction.outputTypeId = output.getTypeId();
				this.onConstruction.outputTypeName = output.getName();
				this.onConstruction.outputTypeIconURL = output.getTypeIconURL();
//				this.onConstruction.outputPrice = output.getMarketPrice();
			}
			return this;
		}

		public ProcessedBlueprintSummary.Builder withTradeStation( final Station station ) {
			this.onConstruction.tradeStation = NeoObjects.requireNonNull( station );
			return this;
		}

		public ProcessedBlueprintSummary.Builder withUID( final String uid ) {
			this.onConstruction.uid = NeoObjects.requireNonNull( uid );
			return this;
		}
	}
}