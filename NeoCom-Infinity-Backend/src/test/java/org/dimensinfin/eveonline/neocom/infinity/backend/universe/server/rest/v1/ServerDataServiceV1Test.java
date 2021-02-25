package org.dimensinfin.eveonline.neocom.infinity.backend.universe.server.rest.v1;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.info.BuildProperties;

import org.dimensinfin.eveonline.neocom.database.repositories.SDERepository;
import org.dimensinfin.eveonline.neocom.esiswagger.model.GetStatusOk;
import org.dimensinfin.eveonline.neocom.infinity.backend.universe.domain.ServerStatus;
import org.dimensinfin.eveonline.neocom.service.ESIDataService;

public class ServerDataServiceV1Test {

	private BuildProperties buildProperties;
	private ESIDataService esiDataService;
	private SDERepository sdeRepository;

	@BeforeEach
	public void beforeEach() {
		this.buildProperties = Mockito.mock( BuildProperties.class );
		this.esiDataService = Mockito.mock( ESIDataService.class );
		this.sdeRepository = Mockito.mock( SDERepository.class );
	}

	@Test
	public void constructorContract() {
		final ServerDataServiceV1 serviceV1 = new ServerDataServiceV1(
				this.buildProperties,
				this.esiDataService,
				this.sdeRepository
		);
		Assertions.assertNotNull( serviceV1 );
	}

	@Test
	public void getServerStatus() {
		// Given
		final String TEST_STATUS_APPLICATION_VERSION = "-TEST_STATUS_APPLICATION_VERSION-";
		final Integer TEST_STATUS_SERVER_PLAYERS = 3224;
		final GetStatusOk esiServerStatus = Mockito.mock( GetStatusOk.class );
		// When
		Mockito.when( this.buildProperties.getVersion() ).thenReturn( TEST_STATUS_APPLICATION_VERSION );
		Mockito.when( this.esiDataService.getUniverseStatus( Mockito.anyString() ) ).thenReturn( esiServerStatus );
		Mockito.when( esiServerStatus.getPlayers() ).thenReturn( TEST_STATUS_SERVER_PLAYERS );
		// Test
		final ServerDataServiceV1 serviceV1 = new ServerDataServiceV1(
				this.buildProperties,
				this.esiDataService,
				this.sdeRepository
		);
		final ServerStatus obtained = serviceV1.getServerStatus();
		// Assertions
		Assertions.assertNotNull( obtained );
		Assertions.assertEquals( TEST_STATUS_APPLICATION_VERSION, obtained.getBackendVersion() );
		Assertions.assertNotNull( obtained.getStatus() );
		Assertions.assertEquals( TEST_STATUS_SERVER_PLAYERS, obtained.getStatus().getPlayers() );
	}
}