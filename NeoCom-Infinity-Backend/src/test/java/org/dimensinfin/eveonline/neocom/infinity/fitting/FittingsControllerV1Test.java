package org.dimensinfin.eveonline.neocom.infinity.fitting;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.dimensinfin.eveonline.neocom.domain.Fitting;
import org.dimensinfin.eveonline.neocom.infinity.backend.character.fitting.rest.v1.FittingsControllerV1;
import org.dimensinfin.eveonline.neocom.infinity.core.exception.NeoComAuthorizationException;
import org.dimensinfin.eveonline.neocom.infinity.config.security.NeoComAuthenticationProvider;

public class FittingsControllerV1Test {
	@Test
	public void getPilotFittingsNeoComAuthorizationException() {
		// Given
		final FittingService fittingService = Mockito.mock( FittingService.class );
		final NeoComAuthenticationProvider neoComAuthenticationProvider = Mockito.mock( NeoComAuthenticationProvider.class );
//		final Integer pilotId = 93813310;
		final FittingsControllerV1 fittingsControllerV1 = new FittingsControllerV1( fittingService, neoComAuthenticationProvider );
		final ResponseEntity<List<Fitting>> response = new ResponseEntity( new ArrayList<Fitting>(), HttpStatus.OK );

		//When
		Mockito.when( fittingService.getPilotFittings() ).thenReturn( response );
		Mockito.when( neoComAuthenticationProvider.getAuthenticatedPilot() ).thenReturn( 93813310 );

		// Test
		NeoComAuthorizationException thrown = Assertions.assertThrows( NeoComAuthorizationException.class,
				() -> fittingsControllerV1.getPilotFittings( 100 ),
				"Expected fittingsController.getPilotFittings() to throw null verification, but it didn't." );
	}

	@Test
	public void getPilotFittingsSuccess() {
		// Given
		final FittingService fittingService = Mockito.mock( FittingService.class );
		final NeoComAuthenticationProvider neoComAuthenticationProvider = Mockito.mock( NeoComAuthenticationProvider.class );
		final Integer pilotId = 93813310;
		final FittingsControllerV1 fittingsControllerV1 = new FittingsControllerV1( fittingService, neoComAuthenticationProvider );
		final ResponseEntity<List<Fitting>> response = new ResponseEntity( new ArrayList<Fitting>(), HttpStatus.OK );

		//When
		Mockito.when( fittingService.getPilotFittings() ).thenReturn( response );
		Mockito.when( neoComAuthenticationProvider.getAuthenticatedPilot() ).thenReturn( 93813310 );

		// Test
		ResponseEntity<List<Fitting>> obtainedResponseEntity = fittingsControllerV1.getPilotFittings( pilotId );

		// Asserts
		Assertions.assertEquals( HttpStatus.OK, obtainedResponseEntity.getStatusCode() );
		Assertions.assertNotNull( obtainedResponseEntity.getBody() );
	}
}
