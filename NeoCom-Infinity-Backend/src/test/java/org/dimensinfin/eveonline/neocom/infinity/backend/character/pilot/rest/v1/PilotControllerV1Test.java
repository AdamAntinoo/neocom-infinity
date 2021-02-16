package org.dimensinfin.eveonline.neocom.infinity.backend.character.pilot.rest.v1;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.http.ResponseEntity;

import org.dimensinfin.eveonline.neocom.character.domain.PilotV1;
import org.dimensinfin.eveonline.neocom.domain.Pilot;
import org.dimensinfin.eveonline.neocom.infinity.config.security.NeoComAuthenticationProvider;
import org.dimensinfin.eveonline.neocom.infinity.core.exception.NeoComRuntimeBackendException;

import static org.dimensinfin.eveonline.neocom.infinity.backend.support.TestDataConstants.PilotConstants.TEST_PILOT_ID;

public class PilotControllerV1Test {
	public static final Integer INVALID_PILOT_ID = 0;
	private PilotServiceV1 pilotServiceV1;
	private NeoComAuthenticationProvider neoComAuthenticationProvider;

	@BeforeEach
	public void beforeEach() {
		this.pilotServiceV1 = Mockito.mock( PilotServiceV1.class );
		this.neoComAuthenticationProvider = Mockito.mock( NeoComAuthenticationProvider.class );
	}

	//	@Test
	public void getPilotData() {
		// Given
		final Integer pilotId = TEST_PILOT_ID;
		final Pilot pilotInstance = Mockito.mock( Pilot.class );
		// When
		//		Mockito.when( this.pilotServiceV1.getPilotData( pilotId ) ).thenReturn( pilotInstance );
		Mockito.when( pilotInstance.getPilotId() ).thenReturn( pilotId );
		Mockito.when( this.neoComAuthenticationProvider.getAuthenticatedPilot() ).thenReturn( pilotId );
		// Test
		final PilotControllerV1 pilotControllerV1 = new PilotControllerV1( this.neoComAuthenticationProvider, this.pilotServiceV1 );
		final ResponseEntity<PilotV1> obtained = pilotControllerV1.getPilotData( pilotId );
		// Assertions
		Assertions.assertNotNull( obtained );
		Assertions.assertNotNull( obtained.getBody() );
		Assertions.assertEquals( pilotId, obtained.getBody().getPilotId() );
	}

	@Test
	public void getPilotDataException() {
		// Given
		final Integer pilotId = TEST_PILOT_ID;
		final PilotV1 pilotInstance = Mockito.mock( PilotV1.class );
		// When
		Mockito.when( this.pilotServiceV1.getAuthenticatedPilotData( pilotId ) ).thenReturn( pilotInstance );
		Mockito.when( pilotInstance.getPilotId() ).thenReturn( pilotId );
		Mockito.when( this.neoComAuthenticationProvider.getAuthenticatedPilot() ).thenReturn( INVALID_PILOT_ID );
		// Test
		final PilotControllerV1 pilotControllerV1 = new PilotControllerV1( this.neoComAuthenticationProvider, this.pilotServiceV1 );
		Assertions.assertThrows( NeoComRuntimeBackendException.class, () -> {
			pilotControllerV1.getPilotData( pilotId );
		} );
	}
}
