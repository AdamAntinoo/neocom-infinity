package org.dimensinfin.eveonline.neocom.infinity.backend.scheduler;

import org.awaitility.Awaitility;
import org.awaitility.Duration;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import org.dimensinfin.eveonline.neocom.infinity.config.SchedulerConfig;

/**
 * @author Adam Antinoo (adamantinoo.git@gmail.com)
 * @since 0.20.0
 */
@SpringJUnitConfig(SchedulerConfig.class)
public class MinuteTimeBasedSchedulerAwaitylityIT {
	@SpyBean
	private MinuteTimeBaseScheduler scheduler;

	@Test
	public void whenWaitOneSecond_thenScheduledIsCalledAtLeastTenTimes() {
		Awaitility.await()
				.atMost( Duration.FIVE_MINUTES )
				.untilAsserted( () -> Mockito.verify( this.scheduler, Mockito.atLeast( 4 ) ).timeBaseRun() );
	}
}