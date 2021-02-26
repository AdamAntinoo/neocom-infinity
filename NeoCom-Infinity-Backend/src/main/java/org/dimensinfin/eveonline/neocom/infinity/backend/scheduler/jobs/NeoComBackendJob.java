package org.dimensinfin.eveonline.neocom.infinity.backend.scheduler.jobs;

import java.util.Objects;

import org.dimensinfin.eveonline.neocom.infinity.backend.scheduler.JobServicePackager;
import org.dimensinfin.eveonline.neocom.service.scheduler.domain.Job;

/**
 * @author Adam Antinoo (adamantinoo.git@gmail.com)
 * @since 0.20.0
 */
public abstract class NeoComBackendJob extends Job {
	protected JobServicePackager jobServicePackager;

	// - C O N S T R U C T O R S
	protected NeoComBackendJob() {}

	// - G E T T E R S   &   S E T T E R S
	public JobServicePackager getJobServicePackager() {
		return this.jobServicePackager;
	}

	// - B U I L D E R
	public abstract static class Builder<T extends Job, B extends Job.Builder<T, B>> extends Job.Builder<T, B> {
		@Override
		public T build() {
			Objects.requireNonNull( ((NeoComBackendJob) this.getActual()).getJobServicePackager() );
			return this.getActual();
		}

		public B withJobServicePackager( final JobServicePackager jobServicePackager ) {
			((NeoComBackendJob) this.getActual()).jobServicePackager = Objects.requireNonNull( jobServicePackager );
			return this.actualClassBuilder;
		}
	}
}