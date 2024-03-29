package org.dimensinfin.eveonline.neocom.infinity.backend.scheduler;

import java.text.MessageFormat;
import java.time.LocalTime;

import com.annimon.stream.Stream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import org.dimensinfin.eveonline.neocom.adapter.LocationCatalogService;
import org.dimensinfin.eveonline.neocom.database.repositories.CredentialRepository;
import org.dimensinfin.eveonline.neocom.database.repositories.MiningRepository;
import org.dimensinfin.eveonline.neocom.infinity.adapter.ConfigurationServiceWrapper;
import org.dimensinfin.eveonline.neocom.infinity.adapter.CredentialRepositoryWrapper;
import org.dimensinfin.eveonline.neocom.infinity.adapter.ESIDataProviderWrapper;
import org.dimensinfin.eveonline.neocom.infinity.adapter.LocationCatalogServiceWrapper;
import org.dimensinfin.eveonline.neocom.infinity.adapter.MiningRepositoryWrapper;
import org.dimensinfin.eveonline.neocom.infinity.backend.scheduler.config.SchedulerConfiguration;
import org.dimensinfin.eveonline.neocom.provider.ESIDataProvider;
import org.dimensinfin.eveonline.neocom.provider.IConfigurationService;
import org.dimensinfin.eveonline.neocom.service.scheduler.JobScheduler;
import org.dimensinfin.logging.LogWrapper;

import static org.dimensinfin.eveonline.neocom.infinity.backend.scheduler.CronSchedulePropertyDefinitions.CRON_SCHEDULE_CREDENTIAL_JOB_GENERATOR;

@Component
public class MinuteSchedulerTimeBaseSchedule {
	private IConfigurationService configurationService;
	private CredentialRepository credentialRepository;
	private SchedulerConfiguration schedulerConfiguration;
	private MiningRepository miningRepository;
	private ESIDataProvider esiDataProvider;
	private LocationCatalogService locationCatalogService;

	// - C O N S T R U C T O R S
	@Autowired
	public MinuteSchedulerTimeBaseSchedule( final ConfigurationServiceWrapper configurationServiceWrapper,
	                                        final CredentialRepositoryWrapper credentialRepositoryWrapper,
	                                        final SchedulerConfiguration schedulerConfiguration,
	                                        final MiningRepositoryWrapper miningRepositoryWrapper,
	                                        final ESIDataProviderWrapper esiDataProviderWrapper,
	                                        final LocationCatalogServiceWrapper locationCatalogServiceWrapper ) {
		this.configurationService = configurationServiceWrapper.getSingleton();
		this.credentialRepository = credentialRepositoryWrapper.getSingleton();
		this.schedulerConfiguration = schedulerConfiguration;
		this.miningRepository = miningRepositoryWrapper.getSingleton();
		this.esiDataProvider = esiDataProviderWrapper.getSingleton();
		this.locationCatalogService = locationCatalogServiceWrapper.getSingleton();
	}

	@EventListener(ApplicationReadyEvent.class)
	public void registerCredentialJobGenerator() {
		LogWrapper.enter();
		JobScheduler.getJobScheduler().registerJob( new CredentialJobGenerator.Builder()
				.withConfigurationService( this.configurationService )
				.withCredentialRepository( this.credentialRepository )
				.withSchedulerConfiguration( this.schedulerConfiguration )
				.withEsiDataProvider( this.esiDataProvider )
				.withLocationCatalogService( this.locationCatalogService )
				.withMiningRepository( this.miningRepository )
				.addCronSchedule( this.configurationService.getResourceString( CRON_SCHEDULE_CREDENTIAL_JOB_GENERATOR, "* - *" ) )
				.build() );
		LogWrapper.exit();
	}

	private boolean allowed2Run() {
		return this.schedulerConfiguration.getAllowedToRun();
	}

	private void printSchedulerJobsReport() {
		Stream.of( JobScheduler.getJobScheduler().getRegisteredJobs() )
				.forEach( ( job ) -> {
					LogWrapper.info( job.getJobName() + "[" + job.getSchedule() + "] - " + job.getStatus().name() );
				} );
	}

	@Scheduled(initialDelay = 60000, fixedDelay = 60000) // One minute schedule.
	private void timeBaseRun() {
		LogWrapper.enter( MessageFormat.format( "{0}:{1}",
				String.format( "%02d", LocalTime.now().getHour() ),
				String.format( "%02d", LocalTime.now().getMinute() ) )
		);
		if (this.allowed2Run()) { // Check if the properties allow scheduler to run
			this.printSchedulerJobsReport();
			JobScheduler.getJobScheduler().runSchedule(); // Run the list of jobs registered for time launch hits.
		}
		LogWrapper.exit();
	}
}
