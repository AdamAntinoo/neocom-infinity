package org.dimensinfin.eveonline.neocom.infinity.scheduler.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import org.dimensinfin.eveonline.neocom.infinity.scheduler.MinuteSchedulerTimeBaseSchedule;
import org.dimensinfin.eveonline.neocom.infinity.support.client.ScheduleJobCountResponse;
import org.dimensinfin.eveonline.neocom.service.scheduler.JobScheduler;
import org.dimensinfin.eveonline.neocom.service.scheduler.domain.JobRecord;

@Service
@Profile("dev")
public class SchedulerSupportService {
	private MinuteSchedulerTimeBaseSchedule minuteSchedulerTimeBaseSchedule;

	@Autowired
	public SchedulerSupportService( final MinuteSchedulerTimeBaseSchedule minuteSchedulerTimeBaseSchedule ) {
		this.minuteSchedulerTimeBaseSchedule = minuteSchedulerTimeBaseSchedule;
	}

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
		this.minuteSchedulerTimeBaseSchedule.registerCredentialJobGenerator();
		return new ScheduleJobCountResponse.Builder()
				.withSchedulerJobCount( JobScheduler.getJobScheduler().getJobCount() )
				.build();
	}
}
