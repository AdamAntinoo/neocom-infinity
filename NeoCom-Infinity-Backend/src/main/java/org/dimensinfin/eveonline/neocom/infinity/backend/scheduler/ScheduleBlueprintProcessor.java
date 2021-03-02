package org.dimensinfin.eveonline.neocom.infinity.backend.scheduler;

import javax.validation.constraints.NotNull;

import org.dimensinfin.eveonline.neocom.infinity.backend.scheduler.jobs.BlueprintProcessorJob;
import org.dimensinfin.eveonline.neocom.provider.IConfigurationService;
import org.dimensinfin.eveonline.neocom.service.ESIDataService;
import org.dimensinfin.eveonline.neocom.service.scheduler.JobScheduler;
import org.dimensinfin.logging.LogWrapper;

import static org.dimensinfin.eveonline.neocom.infinity.backend.scheduler.config.CronSchedulePropertyNameDefinitions.CRON_SCHEDULE_PROCESSING_BLUEPRINTS_PROPERTY_NAME;

@Deprecated
//@Component
public class ScheduleBlueprintProcessor {
	private final IConfigurationService configurationService;
	private final ESIDataService esiDataService;

	// - C O N S T R U C T O R S
	public ScheduleBlueprintProcessor( final @NotNull IConfigurationService configurationService,
	                                   final @NotNull ESIDataService esiDataService ) {
		this.configurationService = configurationService;
		this.esiDataService = esiDataService;
	}

	/**
	 * This method is executed after the application starts and registers the credential hob generator into the minute scheduler.
	 */
	//	@LogEnterExit
	//	@EventListener(ApplicationReadyEvent.class)
	public void registerCredentialJobGenerator() {
		LogWrapper.enter();
		JobScheduler.getJobScheduler().registerJob( new BlueprintProcessorJob.Builder()
				//				.withEsiDataService( this.esiDataService )
				.addCronSchedule( this.configurationService.getResourceString( CRON_SCHEDULE_PROCESSING_BLUEPRINTS_PROPERTY_NAME, "* - 0" ) )
				.build() );
		LogWrapper.exit();
	}
}