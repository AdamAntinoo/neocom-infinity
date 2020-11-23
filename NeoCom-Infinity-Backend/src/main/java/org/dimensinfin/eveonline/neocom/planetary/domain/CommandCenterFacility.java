package org.dimensinfin.eveonline.neocom.planetary.domain;

import java.util.Objects;

import org.dimensinfin.eveonline.neocom.infinity.backend.accountset.planetary.domain.CommandCenterType2LevelConverter;
import org.dimensinfin.eveonline.neocom.infinity.backend.accountset.planetary.domain.PlanetaryFacilityWrapper;
import org.dimensinfin.eveonline.neocom.infinity.backend.accountset.planetary.domain.PlanetaryStorage;
import org.dimensinfin.eveonline.neocom.planetary.ICommandCenterFacility;
import org.dimensinfin.eveonline.neocom.planetary.IPlanetaryStorage;
import org.dimensinfin.eveonline.neocom.planetary.PlanetType;

public class CommandCenterFacility extends PlanetaryFacilityWrapper implements IPlanetaryStorage, ICommandCenterFacility {
	private int upgradeLevel = 0;
	private int cpuInUse = -1;
	private int powerInUse = -1;
	private PlanetaryStorage planetaryStorage;
	private CommandCenterType2LevelConverter commandCenterLevel = CommandCenterType2LevelConverter.getTypeByLevel(this.upgradeLevel);

	// - I C O L L A B O R A T I O N
//	@Override
//	public List<ICollaboration> collaborate2Model( final String variant ) {
//		if (variant.equalsIgnoreCase(PageNamesType.PLANET_FACILITIES_LAYOUT.name())) return new ArrayList<>();
//		return new ArrayList<>(this.planetaryStorage.getContents());
//	}

	// - C O M M A N D C E N T E R
	@Override
	public String getName() {
		return CommandCenterType2LevelConverter.getTypeByLevel(upgradeLevel).getName(this.getPlanetType());
	}

	public Integer getUpgradeLevel() {
		return this.upgradeLevel;
	}

	public void setUpgradeLevel( final int upgradeLevel ) {
		this.upgradeLevel = upgradeLevel;
		this.commandCenterLevel = CommandCenterType2LevelConverter.getTypeByLevel(this.upgradeLevel);
//		return this;
	}

	public PlanetType getPlanetType() {
		return this.planetaryFacility.getPlanetType();
	}

	public Integer getCpuCapacity() {
		return this.commandCenterLevel.getCpuCapacity();
	}

	public Integer getPowerOutput() {
		return this.commandCenterLevel.getPowerOutput();
	}

	public void setCpuInUse( final int cpuInUse ) {
		this.cpuInUse = cpuInUse;
//		return this;
	}

	public void setPowerInUse( final int powerInUse ) {
		this.powerInUse = powerInUse;
//		return this;
	}

	/**
	 * Get the CPU already used by the planet facilities. This calculation requires to scan through all the facilities and add the used capacities
	 * from each of them.
	 */
	public Integer cpuInUse() {
		return this.cpuInUse;
	}

	public Integer powerInUse() {
		return this.powerInUse;
	}

	public Float getTotalVolume() {
		return this.planetaryStorage.getTotalVolume();
	}

	public Double getTotalValue() {
		return this.planetaryStorage.getTotalValue();
	}

//	@Override
//	public int getIconReferenceId() {
//		return R.drawable.commandcenter80_white;
//	}
//
//	@Override
//	public int getIconColorReference() {
//		return R.color.pi_commandcentericoncolor;
//	}

	// - B U I L D E R
	public static class Builder {
		private CommandCenterFacility onConstruction;

		public Builder() {
			this.onConstruction = new CommandCenterFacility();
		}

		public Builder withPlanetaryFacility( final PlanetaryFacility planetaryFacility ) {
			this.onConstruction.planetaryFacility = planetaryFacility;
			return this;
		}

//		public Builder withLevel( final int level ) {
//			this.onConstruction.level = level;
//			this.onConstruction.commandCenterLevel = CommandCenterType2LevelConverter.getTypeByLevel(this.onConstruction.level);
////			this.onConstruction.cpuCapacity = CommandCenterType2LevelConverter.getTypeByLevel(level).getCpuCapacity();
////			this.onConstruction.powerOutput = CommandCenterType2LevelConverter.getTypeByLevel(level).getPowerOutput();
//			return this;
//		}

		public Builder withPlanetaryStorage( final PlanetaryStorage planetaryStorage ) {
			this.onConstruction.planetaryStorage = planetaryStorage;
			return this;
		}

		public CommandCenterFacility build() {
			Objects.requireNonNull(this.onConstruction.planetaryFacility);
			Objects.requireNonNull(this.onConstruction.planetaryStorage);
			return this.onConstruction;
		}
	}
}
