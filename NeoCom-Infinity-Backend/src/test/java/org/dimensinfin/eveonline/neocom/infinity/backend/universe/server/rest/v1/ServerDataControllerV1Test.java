package org.dimensinfin.eveonline.neocom.infinity.backend.universe.server.rest.v1;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.http.ResponseEntity;

import org.dimensinfin.eveonline.neocom.infinity.backend.universe.domain.ServerStatus;

public class ServerDataControllerV1Test {

	private ServerDataServiceV1 serviceV1;

	@BeforeEach
	public void beforeEach() {
		this.serviceV1 = Mockito.mock( ServerDataServiceV1.class );
	}

	@Test
	public void constructorContract() {
		final ServerDataControllerV1 controllerV1 = new ServerDataControllerV1( this.serviceV1 );
		Assertions.assertNotNull( controllerV1 );
	}

	@Test
	public void getServerStatus() {
		// Given
		final String TEST_STATUS_BACKENDVERSION = "-TEST_STATUS_BACKENDVERSION-";
		final ServerStatus serverStatus = Mockito.mock( ServerStatus.class );
		// When
		Mockito.when( this.serviceV1.getServerStatus() ).thenReturn( serverStatus );
		Mockito.when( serverStatus.getBackendVersion() ).thenReturn( TEST_STATUS_BACKENDVERSION );
		// Test
		final ServerDataControllerV1 controllerV1 = new ServerDataControllerV1( this.serviceV1 );
		final ResponseEntity<ServerStatus> obtained = controllerV1.getServerStatus();
		// Assertions
		Assertions.assertNotNull( obtained );
		Assertions.assertNotNull( obtained.getBody() );
		Assertions.assertEquals( TEST_STATUS_BACKENDVERSION, obtained.getBody().getBackendVersion() );
	}
}