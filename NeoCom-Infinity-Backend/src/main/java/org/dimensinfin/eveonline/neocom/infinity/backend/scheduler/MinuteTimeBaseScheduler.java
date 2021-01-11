package org.dimensinfin.eveonline.neocom.infinity.backend.scheduler;

import java.text.MessageFormat;
import java.time.LocalTime;
import javax.validation.constraints.NotNull;

import com.annimon.stream.Stream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import org.dimensinfin.eveonline.neocom.infinity.backend.scheduler.config.SchedulerConfiguration;
import org.dimensinfin.eveonline.neocom.provider.IConfigurationService;
import org.dimensinfin.eveonline.neocom.service.scheduler.JobScheduler;
import org.dimensinfin.logging.LogWrapper;

import static org.dimensinfin.eveonline.neocom.infinity.backend.scheduler.config.CronSchedulePropertyNameDefinitions.CRON_SCHEDULE_CREDENTIAL_JOB_GENERATOR_PROPERTY_NAME;

/**
 * This is the core beat scheduler used to run any of the periodic tasks required by the application to maintain the repositories updated with the
 * game data from the ESI servers. The scheduler has a minute beat time and on each minute it will check against the list of registered jobs which
 * of them match the current time for scheduling into a single runner queue.
 *
 * The scheduler registers some special jobs at initialization.
 *
 * @author Adam Antinoo (adamantinoo.git@gmail.com)
 * @since 0.20.0
 */
@Component
public class MinuteTimeBaseScheduler {
	private final IConfigurationService configurationService;
	//	private final CredentialRepository credentialRepository;
	private final SchedulerConfiguration schedulerConfiguration;
	//	private final MiningRepository miningRepository;
	//	private final LocationCatalogService locationCatalogService;
	//	private final ESIDataService esiDataService;
	//	private final SDERepository sdeRepository;
	//	private final DataStoreService dataStoreService;
	//	private final ResourceFactory resourceFactory;

	// - C O N S T R U C T O R S
	@Autowired
	public MinuteTimeBaseScheduler( @NotNull final IConfigurationService configurationService,
	                                @NotNull final SchedulerConfiguration schedulerConfiguration ) {
		this.configurationService = configurationService;
		this.schedulerConfiguration = schedulerConfiguration;
	}
	//	                                public MinuteTimeBaseScheduler( @NotNull final IConfigurationService configurationService,
	//	                                @NotNull final CredentialRepositoryWrapper credentialRepositoryWrapper,
	//	                                @NotNull final SchedulerConfiguration schedulerConfiguration,
	//	                                @NotNull final MiningRepositoryWrapper miningRepositoryWrapper,
	//	                                @NotNull final LocationCatalogService locationCatalogService,
	//	                                @NotNull final ESIDataService esiDataService,
	//	                                @NotNull final SDERepository sdeRepository,
	//	                                @NotNull final DataStoreService dataStoreService,
	//	                                @NotNull final ResourceFactory resourceFactory ) {
	//		this.configurationService = configurationService;
	//		this.credentialRepository = credentialRepositoryWrapper.getSingleton();
	//		this.schedulerConfiguration = schedulerConfiguration;
	//		this.miningRepository = miningRepositoryWrapper.getSingleton();
	//		this.locationCatalogService = locationCatalogService;
	//		this.esiDataService = esiDataService;
	//		this.sdeRepository = sdeRepository;
	//		this.dataStoreService = dataStoreService;
	//		this.resourceFactory = resourceFactory;
	//	}

	/**
	 * This method is executed after the application starts and registers the credential job generator into the minute scheduler.
	 * This job will scan all the Credentials registered and active and register all the jobs required from each one of them into the scheduler for
	 * next planing.
	 */
	@EventListener(ApplicationReadyEvent.class)
	public void registerCredentialJobGenerator() {
		LogWrapper.enter();
		JobScheduler.getJobScheduler().registerJob( new CredentialJobGenerator.Builder()
				.withConfigurationService( this.configurationService )
				//				.withCredentialRepository( this.credentialRepository )
				.withSchedulerConfiguration( this.schedulerConfiguration )
				//				.withLocationCatalogService( this.locationCatalogService )
				//				.withMiningRepository( this.miningRepository )
				//				.withEsiDataService( this.esiDataService )
				//				.withSDERepository( this.sdeRepository )
				//				.withDataStore( this.dataStoreService )
				//				.withResourceFactory( this.resourceFactory )
				.addCronSchedule( this.configurationService.getResourceString( CRON_SCHEDULE_CREDENTIAL_JOB_GENERATOR_PROPERTY_NAME, "* - *" ) )
				.build() );
		LogWrapper.exit();
	}

	@Scheduled(initialDelay = 60000, fixedDelay = 60000) // One minute schedule.
	public void timeBaseRun() {
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

	private boolean allowed2Run() {
		return this.schedulerConfiguration.getAllowedToRun();
	}

	private void printSchedulerJobsReport() {
		Stream.of( JobScheduler.getJobScheduler().getRegisteredJobs() )
				.forEach( ( job ) -> {
					LogWrapper.info( MessageFormat.format( "{0} [{1}] - {2}",
							job.getJobName(), job.getSchedule(), job.getStatus().name() )
					);
				} );
	}
}
