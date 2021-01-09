package acceptance.steps;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.springframework.beans.factory.annotation.Autowired;

import org.dimensinfin.eveonline.neocom.infinity.support.ConverterContainer;
import org.dimensinfin.eveonline.neocom.infinity.support.NeoComWorld;
import org.dimensinfin.eveonline.neocom.infinity.support.rest.NeoComSupportFeignClient;
import org.dimensinfin.eveonline.neocom.service.scheduler.domain.JobRecord;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class NIB08Scheduler extends SupportSteps {
	private final NeoComSupportFeignClient neoComSupportFeignClient;

	@Autowired
	public NIB08Scheduler( final ConverterContainer cucumberTableToRequestConverters,
	                       final NeoComWorld neocomWorld,
	                       final NeoComSupportFeignClient neoComSupportFeignClient ) {
		super( cucumberTableToRequestConverters, neocomWorld );
		this.neoComSupportFeignClient = neoComSupportFeignClient;
	}

	@Then("the list of scheduled jobs registered is")
	public void the_list_of_scheduled_jobs_registered_is( final List<Map<String, String>> dataTable ) throws IOException {
		final String JOB_NAME = "jobName";
		final String SCHEDULE = "schedule";
		final String STATUS = "status";
		final List<JobRecord> jobList = this.neoComSupportFeignClient.getRegisteredJobs();
		for (int i = 0; i < dataTable.size(); i++) {
			final Map<String, String> row = dataTable.get( i );
			final JobRecord job = jobList.get( i );
			Assert.assertEquals( row.get( JOB_NAME ), job.getJobName() );
			Assert.assertEquals( row.get( SCHEDULE ), job.getSchedule() );
			Assert.assertEquals( row.get( STATUS ), job.getStatus().name() );
		}
	}

	@Then("the number of scheduled jobs is {int}")
	public void the_number_of_scheduled_jobs_is( final Integer jobNumber ) throws IOException {
		Assert.assertEquals( jobNumber, this.neoComSupportFeignClient.countScheduleJobs() );
	}

	@When("the scheduler cycle runs")
	public void the_scheduler_cycle_runs() throws InterruptedException {
		// This requires only to wait 60 seconds that is the base time for the scheduler
		Thread.sleep( TimeUnit.SECONDS.toMillis( 65 ) );
		Assert.assertNotNull( this.neocomWorld );
	}
}
