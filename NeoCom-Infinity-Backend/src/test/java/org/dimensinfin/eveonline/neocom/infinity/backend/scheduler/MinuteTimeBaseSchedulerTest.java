package org.dimensinfin.eveonline.neocom.infinity.backend.scheduler;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

import org.dimensinfin.eveonline.neocom.database.repositories.CredentialRepository;
import org.dimensinfin.eveonline.neocom.infinity.backend.scheduler.config.SchedulerConfiguration;
import org.dimensinfin.eveonline.neocom.provider.IConfigurationService;
import org.dimensinfin.eveonline.neocom.service.scheduler.JobScheduler;
import org.dimensinfin.eveonline.neocom.service.scheduler.domain.JobRecord;
import org.dimensinfin.eveonline.neocom.service.scheduler.domain.JobStatus;

public class MinuteTimeBaseSchedulerTest {
	private IConfigurationService configurationService;
	private SchedulerConfiguration schedulerConfiguration;
	private CredentialRepository credentialRepository;
	private JobServicePackager jobServicePackager;

	@BeforeEach
	public void beforeEach() {
		this.configurationService = Mockito.mock( IConfigurationService.class );
		this.schedulerConfiguration = Mockito.mock( SchedulerConfiguration.class );
		this.credentialRepository = Mockito.mock( CredentialRepository.class );
		this.jobServicePackager = Mockito.mock( JobServicePackager.class );
	}

	@Test
	public void constructorContract() {
		final MinuteTimeBaseScheduler minuteScheduler = new MinuteTimeBaseScheduler(
				this.configurationService,
				this.schedulerConfiguration,
				this.credentialRepository,
				this.jobServicePackager );
		Assertions.assertNotNull( minuteScheduler );
	}

	@Test
	public void printSchedulerJobsReport() {
		// Given
		final JobScheduler jobScheduler = Mockito.mock( JobScheduler.class );
		final JobRecord job = Mockito.mock( JobRecord.class );
		final List<JobRecord> jobList = new ArrayList<>();
		jobList.add( job );
		// When
		Mockito.when( this.configurationService.getResourceString( Mockito.anyString(), Mockito.anyString() ) ).thenReturn( "* - *" );
		Mockito.when( this.schedulerConfiguration.getAllowedToRun() ).thenReturn( true );
		Mockito.when( jobScheduler.getRegisteredJobs() ).thenReturn( jobList );
		Mockito.when( job.getJobName() ).thenReturn( "-JOB-NAME-" );
		Mockito.when( job.getSchedule() ).thenReturn( "-SCHEDULE-" );
		Mockito.when( job.getStatus() ).thenReturn( JobStatus.READY );
		// Test
		try (final MockedStatic<JobScheduler> mocked = Mockito.mockStatic( JobScheduler.class )) {
			mocked.when( JobScheduler::getJobScheduler ).thenReturn( jobScheduler );
			// Test
			final MinuteTimeBaseScheduler minuteScheduler = new MinuteTimeBaseScheduler(
					this.configurationService,
					this.schedulerConfiguration,
					this.credentialRepository,
					this.jobServicePackager );
			minuteScheduler.timeBaseRun();
			// Assertions
			Mockito.verify( jobScheduler, Mockito.times( 1 ) ).runSchedule();
		}
	}

	@Test
	public void registerCredentialJobGenerator() {
		// Check the scheduler is empty
		Assertions.assertTrue( JobScheduler.getJobScheduler().getRegisteredJobs().isEmpty() );
		// When
		Mockito.when( this.configurationService.getResourceString( Mockito.anyString(), Mockito.anyString() ) ).thenReturn( "* - *" );
		// Test
		final MinuteTimeBaseScheduler minuteScheduler = new MinuteTimeBaseScheduler(
				this.configurationService,
				this.schedulerConfiguration,
				this.credentialRepository,
				this.jobServicePackager );
		minuteScheduler.registerCredentialJobGenerator();
		// Assertions
		final List<JobRecord> jobs = JobScheduler.getJobScheduler().getRegisteredJobs();
		Assertions.assertEquals( 1, jobs.size() );
		Assertions.assertEquals( "CredentialJobGenerator", jobs.get( 0 ).getJobName() );
	}

	@Test
	public void timeBaseRun() {
		// Given
		final JobScheduler jobScheduler = Mockito.mock( JobScheduler.class );
		// When
		Mockito.when( this.schedulerConfiguration.getAllowedToRun() ).thenReturn( true );
		// Test
		try (final MockedStatic mocked = Mockito.mockStatic( JobScheduler.class )) {
			mocked.when( JobScheduler::getJobScheduler ).thenReturn( jobScheduler );
			// Test
			final MinuteTimeBaseScheduler minuteScheduler = new MinuteTimeBaseScheduler(
					this.configurationService,
					this.schedulerConfiguration,
					this.credentialRepository,
					this.jobServicePackager );
			minuteScheduler.timeBaseRun();
			// Assertions
			Mockito.verify( jobScheduler, Mockito.times( 1 ) ).runSchedule();
		}
	}
}