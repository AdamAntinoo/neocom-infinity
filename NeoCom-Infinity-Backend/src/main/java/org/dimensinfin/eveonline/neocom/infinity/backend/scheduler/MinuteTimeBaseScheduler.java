package org.dimensinfin.eveonline.neocom.infinity.backend.scheduler;

import java.text.MessageFormat;
import java.time.LocalTime;
import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import org.dimensinfin.eveonline.neocom.database.repositories.CredentialRepository;
import org.dimensinfin.eveonline.neocom.infinity.backend.scheduler.config.SchedulerConfiguration;
import org.dimensinfin.eveonline.neocom.infinity.backend.scheduler.jobs.CredentialJobGenerator;
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
	private final SchedulerConfiguration schedulerConfiguration;
	private final CredentialRepository credentialRepository;
	private final JobServicePackager jobServicePackager;

	// - C O N S T R U C T O R S
	@Autowired
	public MinuteTimeBaseScheduler( @NotNull final IConfigurationService configurationService,
	                                @NotNull final SchedulerConfiguration schedulerConfiguration,
	                                @NotNull final CredentialRepository credentialRepository,
	                                @NotNull final JobServicePackager jobServicePackager ) {
		this.configurationService = configurationService;
		this.schedulerConfiguration = schedulerConfiguration;
		this.credentialRepository = credentialRepository;
		this.jobServicePackager = jobServicePackager;
	}

	/**
	 * This method is executed after the application starts and registers the credential job generator into the minute scheduler.
	 * This job will scan all the Credentials registered and active and register all the jobs required from each one of them into the scheduler for
	 * next planing.
	 */
	@EventListener(ApplicationReadyEvent.class)
	public void registerCredentialJobGenerator() {
		LogWrapper.enter();
		JobScheduler.getJobScheduler().registerJob( new CredentialJobGenerator.Builder()
				.withCredentialRepository( this.credentialRepository )
				.withJobServicePackager( this.jobServicePackager )
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
		JobScheduler.getJobScheduler().getRegisteredJobs()
				.stream()
				.forEach( ( job ) -> {
					LogWrapper.info( MessageFormat.format( "{0} [{1}] - {2}",
							job.getJobName(), job.getSchedule(), job.getStatus().name() )
					);
				} );
	}
}
