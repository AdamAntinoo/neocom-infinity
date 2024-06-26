package org.dimensinfin.eveonline.neocom.infinity.backend.scheduler.jobs;

import java.util.Objects;

import org.apache.commons.lang3.builder.HashCodeBuilder;

import org.dimensinfin.eveonline.neocom.database.entities.Credential;
import org.dimensinfin.eveonline.neocom.database.repositories.CredentialRepository;
import org.dimensinfin.eveonline.neocom.infinity.backend.scheduler.JobServicePackager;
import org.dimensinfin.eveonline.neocom.infinity.mining.MiningExtractionsProcess;
import org.dimensinfin.eveonline.neocom.service.logger.NeoComLogger;
import org.dimensinfin.eveonline.neocom.service.scheduler.JobScheduler;
import org.dimensinfin.eveonline.neocom.service.scheduler.domain.Job;

import static org.dimensinfin.eveonline.neocom.infinity.backend.scheduler.config.CronSchedulePropertyNameDefinitions.CRON_SCHEDULE_MINING_EXTRACTIONS_PROPERTY_NAME;
import static org.dimensinfin.eveonline.neocom.infinity.backend.scheduler.config.CronSchedulePropertyNameDefinitions.CRON_SCHEDULE_PROCESSING_BLUEPRINTS_PROPERTY_NAME;

/**
 * Each Credential will have a set of periodic jobs that should be scheduled into the queue at different times. Because all the Credentials share
 * the same jobs then they can be launched from the same start point, even the job generators would be coded separately and registered on this
 * class for launching.
 *
 * With this composition changes on the jobs or on the list of jobs related to a Credential will not have to impact on the code of this launcher.
 *
 * But the change is that class should be a component and a singleton accessible by dependency injection so the jobs can locate the instance and
 * register into it at application startup component configuration.
 *
 * @author Adam Antinoo (adamantinoo.git@gmail.com)
 * @since 0.20.0
 */
public class CredentialJobGenerator extends Job {
	private JobServicePackager jobServicePackager;
	private CredentialRepository credentialRepository;

	// - C O N S T R U C T O R S
	private CredentialJobGenerator() {
		this.setSchedule( "0/5 - *" );
	}

	// - G E T T E R S   &   S E T T E R S
	// - J O B
	@Override
	public int getUniqueIdentifier() {
		return new HashCodeBuilder( 19, 137 )
				.appendSuper( super.hashCode() )
				.append( this.getClass().getSimpleName() )
				.toHashCode();
	}

	/**
	 * Generate the list of jobs that should be run by every Credential, possible depending on type and state. Check also the property flags for
	 * features not allowed to be scheduled.
	 * The list of jobs generated is then registered on the scheduler for time schedule.
	 *
	 * @return true if the process completed successfully
	 * @throws Exception if there any exception during processing this is thrown to report to scheduler.
	 */
	@Override
	public Boolean call() throws Exception {
		NeoComLogger.enter();
		// Read the list of Credentials and process them.
		for (final Credential credential : this.credentialRepository.accessAllCredentials()) {
			if (this.jobServicePackager.getSchedulerConfiguration().getAllowedToRun()) {
				if (this.jobServicePackager.getSchedulerConfiguration().getAllowedMiningExtractions())
					JobScheduler.getJobScheduler().registerJob( new MiningExtractionsProcess.Builder()
							.withCredential( credential )
							.withJobServicePackager( this.jobServicePackager )
							.addCronSchedule( this.jobServicePackager.getConfigurationService().getResourceString(
									CRON_SCHEDULE_MINING_EXTRACTIONS_PROPERTY_NAME, "* - *" )
							)
							.build() );
				if (this.jobServicePackager.getSchedulerConfiguration().getAllowedProcessingBlueprints())
					JobScheduler.getJobScheduler().registerJob( new BlueprintProcessorJob.Builder()
							.withCredential( credential )
							.withJobServicePackager( this.jobServicePackager )
							.addCronSchedule(
									this.jobServicePackager.getConfigurationService().getResourceString(
											CRON_SCHEDULE_PROCESSING_BLUEPRINTS_PROPERTY_NAME, "* - 0" )
							)
							.build() );
			}
		}
		NeoComLogger.exit();
		return true;
	}

	// - B U I L D E R
	public static class Builder extends Job.Builder<CredentialJobGenerator, CredentialJobGenerator.Builder> {
		private CredentialJobGenerator onConstruction;

		// - C O N S T R U C T O R S
		public Builder() {
			this.onConstruction = new CredentialJobGenerator();
		}

		public CredentialJobGenerator.Builder withCredentialRepository( final CredentialRepository credentialRepository ) {
			Objects.requireNonNull( credentialRepository );
			this.onConstruction.credentialRepository = credentialRepository;
			return this;
		}

		public CredentialJobGenerator.Builder withJobServicePackager( final JobServicePackager jobServicePackager ) {
			this.onConstruction.jobServicePackager = Objects.requireNonNull( jobServicePackager );
			return this;
		}

		@Override
		protected CredentialJobGenerator getActual() {
			if (null == this.onConstruction) this.onConstruction = new CredentialJobGenerator();
			return this.onConstruction;
		}

		@Override
		protected Builder getActualBuilder() {
			return this;
		}

		@Override
		public CredentialJobGenerator build() {
			super.build();
			Objects.requireNonNull( this.onConstruction.credentialRepository );
			Objects.requireNonNull( this.onConstruction.jobServicePackager );
			return this.onConstruction;
		}
	}
}
