package org.dimensinfin.eveonline.neocom.infinity.backend.scheduler.config;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import org.dimensinfin.eveonline.neocom.provider.IConfigurationService;

public class SchedulerConfigurationTest {
	private IConfigurationService configurationService;

	@BeforeEach
	public void beforeEach() {
		this.configurationService = Mockito.mock( IConfigurationService.class );
		//		this.configurationService = Mockito.mock( SBConfigurationService.class );
		//		Mockito.when( this.configurationService.getSingleton() ).thenReturn( this.configurationService );
	}

	@Test
	public void constructorContract() {
		final SchedulerConfiguration schedulerConfiguration = new SchedulerConfiguration( this.configurationService );
		Assertions.assertNotNull( schedulerConfiguration );
	}

	@Test
	public void getAllowedMiningExtractions() {
		final SchedulerConfiguration schedulerConfiguration = new SchedulerConfiguration( this.configurationService );
		Mockito.when( this.configurationService.getResourceBoolean( Mockito.anyString() ) ).thenReturn( false );
		Assertions.assertFalse( schedulerConfiguration.getAllowedMiningExtractions() );
	}

//	@Test
	public void getAllowedToRun() {
		final SchedulerConfiguration schedulerConfiguration = new SchedulerConfiguration( this.configurationService );
		Mockito.when( this.configurationService.getResourceBoolean( Mockito.anyString() ) ).thenReturn( true );
		Assertions.assertTrue( schedulerConfiguration.getAllowedToRun() );
	}
}
