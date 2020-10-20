package org.dimensinfin.eveonline.neocom.infinity.support.client;

import java.util.Objects;

public class ScheduleJobCountResponse {
	private Integer schedulerJobCount;

	private ScheduleJobCountResponse() {}

	public Integer getSchedulerJobCount() {
		return this.schedulerJobCount;
	}

	// - B U I L D E R
	public static class Builder {
		private ScheduleJobCountResponse onConstruction;

		public Builder() {
			this.onConstruction = new ScheduleJobCountResponse();
		}

		public ScheduleJobCountResponse build() {
			return this.onConstruction;
		}

		public ScheduleJobCountResponse.Builder withSchedulerJobCount( final Integer schedulerJobCount ) {
			Objects.requireNonNull( schedulerJobCount );
			this.onConstruction.schedulerJobCount = schedulerJobCount;
			return this;
		}
	}
}