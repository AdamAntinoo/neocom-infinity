package org.dimensinfin.eveonline.neocom.infinity.backend.scheduler.config;

import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import org.dimensinfin.eveonline.neocom.provider.IConfigurationService;

import static org.dimensinfin.eveonline.neocom.infinity.backend.scheduler.config.CronSchedulePropertyNameDefinitions.ALLOWED_MININGEXTRACTIONS_SETTING;
import static org.dimensinfin.eveonline.neocom.infinity.backend.scheduler.config.CronSchedulePropertyNameDefinitions.ALLOWED_PROCESSING_BLUEPRINTS_SETTING;
import static org.dimensinfin.eveonline.neocom.infinity.backend.scheduler.config.CronSchedulePropertyNameDefinitions.ALLOWED_TO_RUN_SETTING;

/**
 * Get the flag configurations fro the different jobs.
 * By default the service is active but all the periodic jobs are disabled.
 */
@Component
public class SchedulerConfiguration {
	private final IConfigurationService configurationService;

	// - C O N S T R U C T O R S
	@Autowired
	protected SchedulerConfiguration( @NotNull final IConfigurationService configurationService ) {
		this.configurationService = configurationService;
	}

	// - G E T T E R S   &   S E T T E R S
	public Boolean getAllowedMiningExtractions() {
		return this.configurationService.getResourceBoolean( ALLOWED_MININGEXTRACTIONS_SETTING, false );
	}

	public Boolean getAllowedProcessingBlueprints() {
		return this.configurationService.getResourceBoolean( ALLOWED_PROCESSING_BLUEPRINTS_SETTING, false );
	}

	public Boolean getAllowedToRun() {
		return this.configurationService.getResourceBoolean( ALLOWED_TO_RUN_SETTING, true );
	}
}
