package org.dimensinfin.eveonline.neocom.infinity.scheduler.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import org.dimensinfin.eveonline.neocom.infinity.support.client.ScheduleJobCountResponse;
import org.dimensinfin.eveonline.neocom.service.scheduler.domain.JobRecord;

@RestController
@Profile("dev")
@RequestMapping("/api/v1/neocom/support")
public class SchedulerSupportController {
	private SchedulerSupportService schedulerSupportService;

	@Autowired
	public SchedulerSupportController( final SchedulerSupportService schedulerSupportService ) {
		this.schedulerSupportService = schedulerSupportService;
	}

	@GetMapping(path = "/scheduler/jobs",
			consumes = "application/json",
			produces = "application/json")
	public ResponseEntity<List<JobRecord>> getSchedulerJobs() {
		return new ResponseEntity<>( this.schedulerSupportService.getSchedulerJobs(), HttpStatus.OK );
	}

	@GetMapping(path = "/scheduler/jobs/count",
			consumes = "application/json",
			produces = "application/json")
	public ResponseEntity<ScheduleJobCountResponse> countSchedulerJobs() {
		return new ResponseEntity<>( this.schedulerSupportService.countSchedulerJobs(), HttpStatus.OK );
	}

	@GetMapping(path = "/scheduler/restart",
			consumes = "application/json",
			produces = "application/json")
	public ResponseEntity<ScheduleJobCountResponse> restartScheduler() {
		return new ResponseEntity<>( this.schedulerSupportService.restartScheduler(), HttpStatus.OK );
	}
}
