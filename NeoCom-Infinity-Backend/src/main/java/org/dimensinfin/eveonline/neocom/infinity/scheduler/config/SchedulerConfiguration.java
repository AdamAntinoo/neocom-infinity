package org.dimensinfin.eveonline.neocom.infinity.scheduler.config;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import org.dimensinfin.eveonline.neocom.infinity.adapter.ConfigurationServiceWrapper;
import org.dimensinfin.eveonline.neocom.infinity.adapter.implementers.SBConfigurationService;

@Component
public class SchedulerConfiguration {
	private static final String ALLOWED_TORUN_SETTING = "S.scheduler.allowedtorun";
	private static final String ALLOWED_MININGEXTRACTIONS_SETTING = "S.scheduler.allowedminingextractions";
	private SBConfigurationService configurationService;

	// - C O N S T R U C T O R S
	@Autowired
	protected SchedulerConfiguration( final ConfigurationServiceWrapper configurationServiceWrapper ) {
		this.configurationService = (SBConfigurationService) Objects.requireNonNull( configurationServiceWrapper.getSingleton() );
	}

	// - G E T T E R S   &   S E T T E R S
	public Boolean getAllowedMiningExtractions() {
		return this.configurationService.getResourceBoolean( ALLOWED_MININGEXTRACTIONS_SETTING );
	}

	public Boolean getAllowedToRun() {
		return this.configurationService.getResourceBoolean( ALLOWED_TORUN_SETTING );
	}
}
