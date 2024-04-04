package org.dimensinfin.eveonline.neocom.infinity.backend.universe.domain;

import org.joda.time.DateTime;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import org.dimensinfin.eveonline.neocom.esiswagger.model.GetStatusOk;
import org.dimensinfin.eveonline.neocom.provider.ESIDataProvider;

public class ServerStatusTest {

	private GetStatusOk statusOk;

	@Test
	public void agoValidations() {
		// When
		Mockito.when( this.statusOk.getStartTime() ).thenReturn( DateTime.now().minusHours( 16 ).minusMinutes( 35 ) );
		// Test
		final ServerStatus status = new ServerStatus.Builder()
				.withServer( ESIDataProvider.DEFAULT_ESI_SERVER )
				.withStatus( this.statusOk )
				.build();
		// Assertions
		Assertions.assertEquals( ESIDataProvider.DEFAULT_ESI_SERVER, status.getServer() );
		Assertions.assertEquals( "16 hours, 35 minutes ago", status.getStartAgo() );
	}

	@BeforeEach
	public void beforeEach() {
		this.statusOk = Mockito.mock( GetStatusOk.class );
	}

	@Test
	public void buildContract() {
		final ServerStatus status = new ServerStatus.Builder()
				.withServer( ESIDataProvider.DEFAULT_ESI_SERVER )
				.withStatus( this.statusOk )
				.build();
		Assertions.assertNotNull( status );
	}

	@Test
	public void getterContract() {
		// When
		Mockito.when( this.statusOk.getStartTime() ).thenReturn( DateTime.now().minusMinutes( 5 ) );
		// Test
		final ServerStatus status = new ServerStatus.Builder()
				.withServer( ESIDataProvider.DEFAULT_ESI_SERVER )
				.withStatus( this.statusOk )
				.build();
		// Assertions
		Assertions.assertEquals( ESIDataProvider.DEFAULT_ESI_SERVER, status.getServer() );
		Assertions.assertEquals( "5 minutes ago", status.getStartAgo() );
		Assertions.assertNotNull( status.getTimeToNextDowntime() );
	}

	//	@Test
	public void nextDowntime() {
		// Given
		final DateTime nowTime = new DateTime(
				DateTime.now().getYear(),
				DateTime.now().getMonthOfYear(),
				DateTime.now().getDayOfMonth(),
				3, 22 );
		// When
		Mockito.when( this.statusOk.getStartTime() ).thenReturn( nowTime );
		// Test
		final ServerStatus status = new ServerStatus.Builder()
				.withServer( ESIDataProvider.DEFAULT_ESI_SERVER )
				.withStatus( this.statusOk )
				.build();
		// Assertions
		Assertions.assertEquals( ESIDataProvider.DEFAULT_ESI_SERVER, status.getServer() );
		Assertions.assertEquals( "Downtime in 21 hours, 21 minutes and 25 seconds", status.getTimeToNextDowntime() );
	}
}