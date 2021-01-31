package org.dimensinfin.eveonline.neocom.infinity.backend.scheduler.config;

import java.util.Objects;
import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import org.dimensinfin.eveonline.neocom.infinity.adapter.ConfigurationServiceWrapper;
import org.dimensinfin.eveonline.neocom.infinity.service.SBConfigurationService;

@Component
public class SchedulerConfiguration {
	private static final String ALLOWED_TO_RUN_SETTING = "P.scheduler.allowedtorun";
	private static final String ALLOWED_MININGEXTRACTIONS_SETTING = "P.scheduler.allowedminingextractions";
	private static final String ALLOWED_PROCESSING_BLUEPRINTS_SETTING = "P.scheduler.allowed.processingblueprints";

	private final SBConfigurationService configurationService;

	// - C O N S T R U C T O R S
	@Autowired
	protected SchedulerConfiguration( final @NotNull ConfigurationServiceWrapper configurationServiceWrapper ) {
		this.configurationService = (SBConfigurationService) Objects.requireNonNull( configurationServiceWrapper.getSingleton() );
	}

	// - G E T T E R S   &   S E T T E R S
	public Boolean getAllowedMiningExtractions() {
		return this.configurationService.getResourceBoolean( ALLOWED_MININGEXTRACTIONS_SETTING );
	}

	public Boolean getAllowedProcessingBlueprints() {
		return this.configurationService.getResourceBoolean( ALLOWED_PROCESSING_BLUEPRINTS_SETTING );
	}

	public Boolean getAllowedToRun() {
		return this.configurationService.getResourceBoolean( ALLOWED_TO_RUN_SETTING );
	}
}
