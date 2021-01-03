package org.dimensinfin.eveonline.neocom.infinity.backend.industry.domain;

import java.util.List;
import java.util.Objects;

import org.dimensinfin.eveonline.neocom.domain.Resource;

public class ProcessedBlueprint {
	private int blueprintType;
	private List<Resource> bom;

	// - C O N S T R U C T O R S
	private ProcessedBlueprint() {}

	public int getBlueprintType() {
		return this.blueprintType;
	}

	public List<Resource> getBom() {
		return this.bom;
	}

	// - B U I L D E R
	public static class Builder {
		private final ProcessedBlueprint onConstruction;

		// - C O N S T R U C T O R S
		public Builder() {
			this.onConstruction = new ProcessedBlueprint();
		}

		public ProcessedBlueprint build() {
			return this.onConstruction;
		}

		public ProcessedBlueprint.Builder withBOM( final List<Resource> resources ) {
			this.onConstruction.bom = Objects.requireNonNull( resources );
			return this;
		}
		public ProcessedBlueprint.Builder withType( final int blueprintType ) {
			this.onConstruction.blueprintType = blueprintType;
			return this;
		}
	}
}