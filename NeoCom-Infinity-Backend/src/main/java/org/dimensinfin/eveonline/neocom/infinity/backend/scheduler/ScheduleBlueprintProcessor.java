package org.dimensinfin.eveonline.neocom.infinity.backend.scheduler;

import javax.validation.constraints.NotNull;

import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import org.dimensinfin.annotation.LogEnterExit;
import org.dimensinfin.eveonline.neocom.infinity.backend.scheduler.jobs.BlueprintProcessorJob;
import org.dimensinfin.eveonline.neocom.provider.IConfigurationService;
import org.dimensinfin.eveonline.neocom.service.ESIDataService;
import org.dimensinfin.eveonline.neocom.service.scheduler.JobScheduler;
import org.dimensinfin.logging.LogWrapper;

import static org.dimensinfin.eveonline.neocom.infinity.backend.scheduler.config.CronSchedulePropertyDefinitions.CRON_SCHEDULE_PROCESSING_BLUEPRINTS;

//@Component
public class ScheduleBlueprintProcessor {
	private final IConfigurationService configurationService;
	private final ESIDataService esiDataService;

	public ScheduleBlueprintProcessor( final @NotNull IConfigurationService configurationService,
	                                   final @NotNull ESIDataService esiDataService ) {
		this.configurationService = configurationService;
		this.esiDataService = esiDataService;}

	/**
	 * This method is executed after the application starts and registers the credential hob generator into the minute scheduler.
	 */
//	@LogEnterExit
//	@EventListener(ApplicationReadyEvent.class)
	public void registerCredentialJobGenerator() {
		LogWrapper.enter();
		JobScheduler.getJobScheduler().registerJob( new BlueprintProcessorJob.Builder()
				.withEsiDataService( this.esiDataService )
				.addCronSchedule( this.configurationService.getResourceString( CRON_SCHEDULE_PROCESSING_BLUEPRINTS, "* - 0" ) )
				.build() );
		LogWrapper.exit();
	}
}