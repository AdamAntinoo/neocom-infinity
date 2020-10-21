package org.dimensinfin.eveonline.neocom.infinity.backend.character.pilot.rest.v2;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.http.ResponseEntity;

import org.dimensinfin.eveonline.neocom.infinity.core.exception.NeoComRuntimeBackendException;
import org.dimensinfin.eveonline.neocom.infinity.core.security.NeoComAuthenticationProvider;
import org.dimensinfin.eveonline.neocom.infinity.pilot.rest.representation.PilotModel;

import static org.dimensinfin.eveonline.neocom.infinity.backend.character.pilot.rest.v1.PilotControllerV1Test.INVALID_PILOT_ID;
import static org.dimensinfin.eveonline.neocom.infinity.backend.support.TestDataConstants.PilotConstants.TEST_PILOT_ID;

public class PilotControllerV2Test {

	private PilotServiceV2 pilotServiceV2;
	private NeoComAuthenticationProvider neoComAuthenticationProvider;

	@BeforeEach
	public void beforeEach() {
		this.pilotServiceV2 = Mockito.mock( PilotServiceV2.class );
		this.neoComAuthenticationProvider = Mockito.mock( NeoComAuthenticationProvider.class );
	}

	@Test
	public void getPilotData() {
		// Given
		final Integer pilotId = TEST_PILOT_ID;
		final PilotModel pilotInstance = Mockito.mock( PilotModel.class );
		// When
		Mockito.when( this.pilotServiceV2.getPilotData( pilotId ) ).thenReturn( pilotInstance );
		Mockito.when( pilotInstance.getPilotId() ).thenReturn( pilotId );
		Mockito.when( this.neoComAuthenticationProvider.getAuthenticatedPilot() ).thenReturn( pilotId );
		// Test
		final PilotControllerV2 pilotControllerV2 = new PilotControllerV2( this.neoComAuthenticationProvider, this.pilotServiceV2 );
		final ResponseEntity<PilotModel> obtained = pilotControllerV2.getPilotData( pilotId );
		// Assertions
		Assertions.assertNotNull( obtained );
		Assertions.assertNotNull( obtained.getBody() );
		Assertions.assertEquals( pilotId, obtained.getBody().getPilotId() );
	}

	@Test
	public void getPilotDataException() {
		// Given
		final Integer pilotId = TEST_PILOT_ID;
		final PilotModel pilotInstance = Mockito.mock( PilotModel.class );
		// When
		Mockito.when( this.pilotServiceV2.getPilotData( pilotId ) ).thenReturn( pilotInstance );
		Mockito.when( pilotInstance.getPilotId() ).thenReturn( pilotId );
		Mockito.when( this.neoComAuthenticationProvider.getAuthenticatedPilot() ).thenReturn( INVALID_PILOT_ID );
		// Test
		final PilotControllerV2 pilotControllerV2 = new PilotControllerV2( this.neoComAuthenticationProvider, this.pilotServiceV2 );
		Assertions.assertThrows( NeoComRuntimeBackendException.class, () -> {
			pilotControllerV2.getPilotData( pilotId );
		} );
	}
}
