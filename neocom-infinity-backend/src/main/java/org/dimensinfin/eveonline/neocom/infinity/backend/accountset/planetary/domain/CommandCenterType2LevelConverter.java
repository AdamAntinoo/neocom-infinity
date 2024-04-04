package org.dimensinfin.eveonline.neocom.infinity.backend.accountset.planetary.domain;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import org.dimensinfin.eveonline.neocom.planetary.PlanetType;

public class CommandCenterType2LevelConverter implements Serializable {
	private static final long serialVersionUID = 8607618856117516126L;
	private static Map<Integer, CommandCenterType2LevelConverter> commandCenterTypesMap = new HashMap<>();

	static {
		commandCenterTypesMap.put(0, new Builder()
				                             .withLevel(0)
				                             .withPrefix("")
				                             .withCpuCapacity(1675)
				                             .withPowerOutput(6000)
				                             .build());
		commandCenterTypesMap.put(1, new Builder()
				                             .withLevel(1)
				                             .withPrefix("Limited")
				                             .withCpuCapacity(7057)
				                             .withPowerOutput(9000)
				                             .build());
		commandCenterTypesMap.put(2, new Builder()
				                             .withLevel(2)
				                             .withPrefix("Standard")
				                             .withCpuCapacity(12136)
				                             .withPowerOutput(12000)
				                             .build());
		commandCenterTypesMap.put(3, new Builder()
				                             .withLevel(3)
				                             .withPrefix("Improved")
				                             .withCpuCapacity(17215)
				                             .withPowerOutput(15000)
				                             .build());
		commandCenterTypesMap.put(4, new Builder()
				                             .withLevel(4)
				                             .withPrefix("Advanced")
				                             .withCpuCapacity(21315)
				                             .withPowerOutput(17000)
				                             .build());
		commandCenterTypesMap.put(5, new Builder()
				                             .withLevel(5)
				                             .withPrefix("Elite")
				                             .withCpuCapacity(25415)
				                             .withPowerOutput(19000)
				                             .build());
	}

	public static CommandCenterType2LevelConverter getTypeByLevel( int level ) {
		if (level < 0) level = 0;
		if (level > 5) level = 5;
		return commandCenterTypesMap.get(level);
	}

	private int level;
	private String prefix;
	private int cpuCapacity;
	private int powerOutput;

	private CommandCenterType2LevelConverter() { }

	public String getName( final PlanetType planetType ) {
		return this.prefix + " " + planetType.getPrefix() + " Command Center";
	}

	public int getLevel() {
		return this.level;
	}

	public int getCpuCapacity() {
		return this.cpuCapacity;
	}

	public int getPowerOutput() {
		return this.powerOutput;
	}

	// - B U I L D E R
	protected static class Builder {
		private CommandCenterType2LevelConverter onConstruction;

		public Builder() {
			this.onConstruction = new CommandCenterType2LevelConverter();
		}

		public Builder withLevel( final int level ) {
			this.onConstruction.level = level;
			return this;
		}

		public Builder withPrefix( final String prefix ) {
			this.onConstruction.prefix = prefix;
			return this;
		}

		public Builder withCpuCapacity( final int cpuCapacity ) {
			this.onConstruction.cpuCapacity = cpuCapacity;
			return this;
		}

		public Builder withPowerOutput( final int powerOutput ) {
			this.onConstruction.powerOutput = powerOutput;
			return this;
		}

		public CommandCenterType2LevelConverter build() {
			return this.onConstruction;
		}
	}
}
