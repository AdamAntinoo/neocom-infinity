package org.dimensinfin.eveonline.neocom.infinity.backend.accountset.planetary.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.dimensinfin.core.interfaces.ICollaboration;
import org.dimensinfin.eveonline.neocom.database.repositories.PlanetaryRepository;
import org.dimensinfin.eveonline.neocom.esiswagger.model.GetCharactersCharacterIdPlanetsPlanetIdOkContents;
import org.dimensinfin.eveonline.neocom.planetary.FactoryType;
import org.dimensinfin.eveonline.neocom.planetary.PlanetaryResourceTierType;
import org.dimensinfin.eveonline.neocom.planetary.PlanetarySchematic;
import org.dimensinfin.eveonline.neocom.planetary.Schematics;
import org.dimensinfin.eveonline.neocom.planetary.domain.PlanetaryFacility;
import org.dimensinfin.eveonline.neocom.planetary.domain.PlanetaryResource;

import liquibase.pro.packaged.R;

public class FactoryFacility extends PlanetaryFacilityWrapper {
	private static final long serialVersionUID = -4614362639541002313L;
	private Integer schematicId;
	private PlanetarySchematic schematic;
	private List<FactoryInput> inputs = new ArrayList<>();
	//	private Schematics output;
	private FactoryType factoryType = FactoryType.BASIC_INDUSTRY;

	// -  C O M P O N E N T S
	private PlanetaryRepository planetaryRepository;

	public List<FactoryInput> getInputs() {
		return this.inputs;
	}

	public Schematics getOutput() {
		if (null != this.schematic) return this.schematic.getOutput();
		else return null;
	}

	// - I C O L L A B O R A T I O N
	@Override
	public List<ICollaboration> collaborate2Model( final String variant ) {
		return new ArrayList<>();
	}

	public FactoryType getFactoryType() {
		return this.factoryType;
	}

	public int getCycleTime() {
		return this.schematic.getCycleTime();
	}

	public int getRequiredQuantity() {
		return this.schematic.getInputRequiredQuantity();
	}

//	@Override
//	public int getIconReferenceId() {
//		return R.drawable.facility80_white;
//	}
//
//	@Override
//	public int getIconColorReference() {
//		return R.color.pi_factoryiconcolor;
//	}

	// - B U I L D E R
	public static class Builder {
		private FactoryFacility onConstruction;

		public Builder() {
			this.onConstruction = new FactoryFacility();
		}

		public Builder withPlanetaryFacility( final PlanetaryFacility planetaryFacility ) {
			this.onConstruction.planetaryFacility = planetaryFacility;
			Objects.requireNonNull(this.onConstruction.planetaryFacility);
			// Convert the contents to the factory list of contents and get Schematics data from repositories.
			for (GetCharactersCharacterIdPlanetsPlanetIdOkContents content : this.onConstruction.planetaryFacility.getContents()) {
				this.onConstruction.inputs.add(new FactoryInput(content));
			}
			return this;
		}

		public Builder withPlanetaryRepository( final PlanetaryRepository planetaryRepository ) {
			this.onConstruction.planetaryRepository = planetaryRepository;
			return this;
		}

		public Builder withSchematics( final Integer schematicId ) {
			Objects.requireNonNull(schematicId);
			this.onConstruction.schematicId = schematicId;
			return this;
		}

		public FactoryFacility build() {
			Objects.requireNonNull(this.onConstruction.planetaryFacility);
			Objects.requireNonNull(this.onConstruction.schematicId);
			Objects.requireNonNull(this.onConstruction.planetaryRepository);
			// Initialize the schematic.
			this.onConstruction.schematic = new PlanetarySchematic.Builder(this.onConstruction.schematicId)
					.withPlanetaryRepository(this.onConstruction.planetaryRepository)
					.build();
//			this.onConstruction.output = this.onConstruction.schematic.getOutput();
			this.withFactoryType(this.onConstruction.schematic.getOutput().getGroupName()); // Setup the factory factoryType.
			return this.onConstruction;
		}

		private void withFactoryType( final String groupName ) {
			final PlanetaryResourceTierType tierType = PlanetaryResourceTierType.searchTierType4Group(groupName);
			if (tierType == PlanetaryResourceTierType.TIER1) this.onConstruction.factoryType = FactoryType.BASIC_INDUSTRY;
			if (tierType == PlanetaryResourceTierType.TIER3) this.onConstruction.factoryType = FactoryType.HIGH_INDUSTRY;

			if ((tierType == PlanetaryResourceTierType.TIER2) || (tierType == PlanetaryResourceTierType.TIER3))
				this.onConstruction.factoryType = FactoryType.facility4Schematics(this.onConstruction.schematicId);
		}
	}

	// - F A C T O R Y I N P U T S
	public static class FactoryInput implements Serializable {
		private static final long serialVersionUID = -6257539970882239251L;
		private transient GetCharactersCharacterIdPlanetsPlanetIdOkContents planetaryContent;
		private PlanetaryResource resource;

		FactoryInput( final GetCharactersCharacterIdPlanetsPlanetIdOkContents content ) {
			this.planetaryContent = content;
//			this.resource = new PlanetaryResource(this.planetaryContent.getTypeId(), this.planetaryContent.getAmount().intValue());
		}

		public int getTypeId() {
			return this.resource.getTypeId();
		}

		public String getName() {
			return this.resource.getName();
		}

		public double getPrice() {
			return this.resource.getPrice();
		}

		public double getVolume() {
			return this.resource.getVolume();
		}

		public String getGroupName() {
			return this.resource.getGroupName();
		}

		public Long getAmount() {
			return this.planetaryContent.getAmount();
		}

		public PlanetaryResourceTierType getTier() {return resource.getTier();}
	}

//	public static class FactoryOutput extends FactoryInput implements Serializable {
//		private static final long serialVersionUID = -4730030220635006541L;
//
//		FactoryOutput( final GetCharactersCharacterIdPlanetsPlanetIdOkContents content ) {
//			super(content);
//		}
//	}
}
