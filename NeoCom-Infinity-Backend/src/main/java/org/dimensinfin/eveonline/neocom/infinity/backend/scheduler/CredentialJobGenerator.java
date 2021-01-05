package org.dimensinfin.eveonline.neocom.infinity.backend.scheduler;

import java.util.Objects;

import org.apache.commons.lang3.builder.HashCodeBuilder;

import org.dimensinfin.eveonline.neocom.database.entities.Credential;
import org.dimensinfin.eveonline.neocom.database.repositories.CredentialRepository;
import org.dimensinfin.eveonline.neocom.database.repositories.MiningRepository;
import org.dimensinfin.eveonline.neocom.database.repositories.SDERepository;
import org.dimensinfin.eveonline.neocom.infinity.service.DataStoreService;
import org.dimensinfin.eveonline.neocom.infinity.backend.scheduler.jobs.BlueprintProcessorJob;
import org.dimensinfin.eveonline.neocom.infinity.mining.MiningExtractionsProcess;
import org.dimensinfin.eveonline.neocom.infinity.backend.scheduler.config.SchedulerConfiguration;
import org.dimensinfin.eveonline.neocom.provider.ESIDataProvider;
import org.dimensinfin.eveonline.neocom.provider.IConfigurationService;
import org.dimensinfin.eveonline.neocom.service.ESIDataService;
import org.dimensinfin.eveonline.neocom.service.LocationCatalogService;
import org.dimensinfin.eveonline.neocom.service.ResourceFactory;
import org.dimensinfin.eveonline.neocom.service.logger.NeoComLogger;
import org.dimensinfin.eveonline.neocom.service.scheduler.JobScheduler;
import org.dimensinfin.eveonline.neocom.service.scheduler.domain.Job;

import static org.dimensinfin.eveonline.neocom.infinity.backend.scheduler.config.CronSchedulePropertyDefinitions.CRON_SCHEDULE_MINING_EXTRACTIONS;
import static org.dimensinfin.eveonline.neocom.infinity.backend.scheduler.config.CronSchedulePropertyDefinitions.CRON_SCHEDULE_PROCESSING_BLUEPRINTS;

public class CredentialJobGenerator extends Job {
	private IConfigurationService configurationService;
	private CredentialRepository credentialRepository;
	private SchedulerConfiguration schedulerConfiguration;
	private MiningRepository miningRepository;
	private ESIDataProvider esiDataProvider;
	private LocationCatalogService locationCatalogService;
	private ESIDataService esiDataService;
	private SDERepository sdeRepository;
	private DataStoreService dataStoreService;
	private ResourceFactory resourceFactory;

	private CredentialJobGenerator() {
		this.setSchedule( "0/5 - *" );
	}

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
		for (Credential credential : this.credentialRepository.accessAllCredentials()) {
			if (this.schedulerConfiguration.getAllowedToRun())
				if (this.schedulerConfiguration.getAllowedMiningExtractions())
					JobScheduler.getJobScheduler().registerJob( new MiningExtractionsProcess.Builder()
							.withCredential( credential )
							.withEsiDataProvider( this.esiDataProvider )
							.withLocationCatalogService( this.locationCatalogService )
							.withMiningRepository( this.miningRepository )
							.addCronSchedule( this.configurationService.getResourceString( CRON_SCHEDULE_MINING_EXTRACTIONS, "* - *" ) )
							.build() );
			if (this.schedulerConfiguration.getAllowedProcessingBlueprints())
				JobScheduler.getJobScheduler().registerJob( new BlueprintProcessorJob.Builder()
						.withCredential( credential )
						.withEsiDataService( this.esiDataService )
						.withSDERepository( this.sdeRepository )
						.withDataStore( this.dataStoreService )
						.withResourceFactory( this.resourceFactory )
						.addCronSchedule( this.configurationService.getResourceString( CRON_SCHEDULE_PROCESSING_BLUEPRINTS, "* - 0" ) )
						.build() );
		}
		NeoComLogger.exit();
		return true;
	}

	// - B U I L D E R
	public static class Builder extends Job.Builder<CredentialJobGenerator, CredentialJobGenerator.Builder> {
		private CredentialJobGenerator onConstruction;

		public Builder() {
			this.onConstruction = new CredentialJobGenerator();
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

		public CredentialJobGenerator build() {
			super.build();
			Objects.requireNonNull( this.onConstruction.configurationService );
			Objects.requireNonNull( this.onConstruction.credentialRepository );
			Objects.requireNonNull( this.onConstruction.esiDataProvider );
			Objects.requireNonNull( this.onConstruction.locationCatalogService );
			Objects.requireNonNull( this.onConstruction.miningRepository );
			Objects.requireNonNull( this.onConstruction.schedulerConfiguration );
			return this.onConstruction;
		}

		public CredentialJobGenerator.Builder withConfigurationService( final IConfigurationService configurationService ) {
			Objects.requireNonNull( configurationService );
			this.onConstruction.configurationService = configurationService;
			return this;
		}

		public CredentialJobGenerator.Builder withCredentialRepository( final CredentialRepository credentialRepository ) {
			Objects.requireNonNull( credentialRepository );
			this.onConstruction.credentialRepository = credentialRepository;
			return this;
		}

		public CredentialJobGenerator.Builder withEsiDataProvider( final ESIDataProvider esiDataProvider ) {
			Objects.requireNonNull( esiDataProvider );
			this.onConstruction.esiDataProvider = esiDataProvider;
			return this;
		}
		public CredentialJobGenerator.Builder withEsiDataService( final ESIDataService esiDataService ) {
			this.onConstruction.esiDataService = Objects.requireNonNull( esiDataService );
			return this;
		}

		public CredentialJobGenerator.Builder withSDERepository ( final SDERepository sdeRepository){
			this.onConstruction.sdeRepository = Objects.requireNonNull( sdeRepository );
			return this;
		}
		public CredentialJobGenerator.Builder withDataStore( final DataStoreService dataStoreService ){
			this.onConstruction.dataStoreService = Objects.requireNonNull( dataStoreService );
			return this;
		}
		public CredentialJobGenerator.Builder withLocationCatalogService( final LocationCatalogService locationCatalogService ) {
			Objects.requireNonNull( locationCatalogService );
			this.onConstruction.locationCatalogService = locationCatalogService;
			return this;
		}
		public CredentialJobGenerator.Builder withResourceFactory( final ResourceFactory resourceFactory ) {
			this.onConstruction.resourceFactory = Objects.requireNonNull( resourceFactory );
			return this;
		}

		public CredentialJobGenerator.Builder withMiningRepository( final MiningRepository miningRepository ) {
			Objects.requireNonNull( miningRepository );
			this.onConstruction.miningRepository = miningRepository;
			return this;
		}

		public CredentialJobGenerator.Builder withSchedulerConfiguration( final SchedulerConfiguration schedulerConfiguration ) {
			Objects.requireNonNull( schedulerConfiguration );
			this.onConstruction.schedulerConfiguration = schedulerConfiguration;
			return this;
		}
	}
}
