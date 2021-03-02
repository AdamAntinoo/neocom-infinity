package org.dimensinfin.eveonline.neocom.infinity.backend.industry.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.dimensinfin.eveonline.neocom.domain.EsiType;
import org.dimensinfin.eveonline.neocom.industry.domain.PricedResource;
import org.dimensinfin.eveonline.neocom.market.MarketData;

public class ProcessedBlueprint implements Serializable {
	private static final long serialVersionUID = 1702676060995319018L;
	private int blueprintTypeId;
	private EsiType blueprint;
	private EsiType output;
	private MarketData outputMarketData;
	private List<PricedResource> bom = new ArrayList<>();

	// - C O N S T R U C T O R S
	private ProcessedBlueprint() {}

	// - G E T T E R S   &   S E T T E R S
	public EsiType getBlueprint() {
		return this.blueprint;
	}

	public int getBlueprintTypeId() {
		return this.blueprintTypeId;
	}

	public List<PricedResource> getBom() {
		return this.bom;
	}

	public double getManufactureCost() {
		return this.bom.stream().mapToDouble( resource -> resource.getMarketPrice() ).sum();
	}

	public EsiType getOutput() {
		return this.output;
	}

	public MarketData getOutputMarketData() {
		return this.outputMarketData;
	}

	// - B U I L D E R
	public static class Builder {
		private final ProcessedBlueprint onConstruction;

		// - C O N S T R U C T O R S
		public Builder() {
			this.onConstruction = new ProcessedBlueprint();
		}

		public ProcessedBlueprint build() {
			Objects.requireNonNull( this.onConstruction.blueprint );
			Objects.requireNonNull( this.onConstruction.output );
			Objects.requireNonNull( this.onConstruction.outputMarketData );
			return this.onConstruction;
		}

		public ProcessedBlueprint.Builder withBOM( final List<PricedResource> resources ) {
			this.onConstruction.bom = Objects.requireNonNull( resources );
			return this;
		}

		public ProcessedBlueprint.Builder withBlueprint( final EsiType blueprint ) {
			this.onConstruction.blueprint = Objects.requireNonNull( blueprint );
			this.onConstruction.blueprintTypeId = this.onConstruction.blueprint.getTypeId();
			return this;
		}

		public ProcessedBlueprint.Builder withOutput( final EsiType output ) {
			this.onConstruction.output = Objects.requireNonNull( output );
			return this;
		}

		public ProcessedBlueprint.Builder withOutputMarketData( final MarketData outputMarketData ) {
			this.onConstruction.outputMarketData = Objects.requireNonNull( outputMarketData );
			return this;
		}
	}
}