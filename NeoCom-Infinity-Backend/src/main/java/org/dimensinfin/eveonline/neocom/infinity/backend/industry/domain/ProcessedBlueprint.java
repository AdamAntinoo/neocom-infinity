package org.dimensinfin.eveonline.neocom.infinity.backend.industry.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.dimensinfin.eveonline.neocom.domain.EsiType;
import org.dimensinfin.eveonline.neocom.domain.Resource;

public class ProcessedBlueprint {
	private int blueprintType;
	private EsiType output;
	private List<Resource> bom = new ArrayList<>();

	// - C O N S T R U C T O R S
	private ProcessedBlueprint() {}

	// - G E T T E R S   &   S E T T E R S
	public int getBlueprintType() {
		return this.blueprintType;
	}

	public List<Resource> getBom() {
		return this.bom;
	}

	public EsiType getOutput() {
		return this.output;
	}

	// - B U I L D E R
	public static class Builder {
		private final ProcessedBlueprint onConstruction;

		// - C O N S T R U C T O R S
		public Builder() {
			this.onConstruction = new ProcessedBlueprint();
		}

		public ProcessedBlueprint build() {
			Objects.requireNonNull( this.onConstruction.output );
			return this.onConstruction;
		}

		public ProcessedBlueprint.Builder withBOM( final List<Resource> resources ) {
			this.onConstruction.bom = Objects.requireNonNull( resources );
			return this;
		}

		public ProcessedBlueprint.Builder withOutput( final EsiType output ) {
			this.onConstruction.output = Objects.requireNonNull( output );
			return this;
		}

		public ProcessedBlueprint.Builder withType( final int blueprintType ) {
			this.onConstruction.blueprintType = blueprintType;
			return this;
		}
	}
}