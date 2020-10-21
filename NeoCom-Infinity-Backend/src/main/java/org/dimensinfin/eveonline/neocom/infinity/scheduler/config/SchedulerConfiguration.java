package org.dimensinfin.eveonline.neocom.infinity.scheduler.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import org.dimensinfin.eveonline.neocom.infinity.adapter.ConfigurationServiceWrapper;

@Component
public class SchedulerConfiguration {
	private static final String ALLOWED_TORUN_SETTING = "S.scheduler.allowedtorun";
	private static final String ALLOWED_MININGEXTRACTIONS_SETTING = "S.scheduler.allowedminingextractions";
	private ConfigurationServiceWrapper configurationServiceWrapper;

	@Autowired
	protected SchedulerConfiguration( final ConfigurationServiceWrapper configurationServiceWrapper ) {
		this.configurationServiceWrapper = configurationServiceWrapper;
	}

	public Boolean getAllowedToRun() {
		return this.configurationServiceWrapper.getSingleton().getResourceBoolean( ALLOWED_TORUN_SETTING );
	}

	public Boolean getAllowedMiningExtractions() {
		return this.configurationServiceWrapper.getSingleton().getResourceBoolean( ALLOWED_MININGEXTRACTIONS_SETTING );
	}
}
