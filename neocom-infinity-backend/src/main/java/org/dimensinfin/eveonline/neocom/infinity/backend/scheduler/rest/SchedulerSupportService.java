package org.dimensinfin.eveonline.neocom.infinity.backend.scheduler.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import org.dimensinfin.eveonline.neocom.infinity.backend.scheduler.MinuteTimeBaseScheduler;
import org.dimensinfin.eveonline.neocom.infinity.support.client.ScheduleJobCountResponse;
import org.dimensinfin.eveonline.neocom.service.scheduler.JobScheduler;
import org.dimensinfin.eveonline.neocom.service.scheduler.domain.JobRecord;

@Service
@Profile("dev")
public class SchedulerSupportService {
	private final MinuteTimeBaseScheduler minuteTimeBaseScheduler;

// - C O N S T R U C T O R S
	@Autowired
	public SchedulerSupportService( final MinuteTimeBaseScheduler minuteTimeBaseScheduler ) {
		this.minuteTimeBaseScheduler = minuteTimeBaseScheduler;
	}

// - G E T T E R S   &   S E T T E R S
	public List<JobRecord> getSchedulerJobs() {
		return JobScheduler.getJobScheduler().getRegisteredJobs();
	}

	public ScheduleJobCountResponse countSchedulerJobs() {
		return new ScheduleJobCountResponse.Builder()
				.withSchedulerJobCount( JobScheduler.getJobScheduler().getJobCount() )
				.build();
	}

	public ScheduleJobCountResponse restartScheduler() {
		JobScheduler.getJobScheduler().clear();
		this.minuteTimeBaseScheduler.registerCredentialJobGenerator();
		return new ScheduleJobCountResponse.Builder()
				.withSchedulerJobCount( JobScheduler.getJobScheduler().getJobCount() )
				.build();
	}
}
