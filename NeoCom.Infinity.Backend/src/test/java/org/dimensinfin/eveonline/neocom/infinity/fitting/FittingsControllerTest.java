package org.dimensinfin.eveonline.neocom.infinity.fitting;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.dimensinfin.eveonline.neocom.domain.Fitting;
import org.dimensinfin.eveonline.neocom.infinity.core.exceptions.NeoComAuthorizationException;
import org.dimensinfin.eveonline.neocom.infinity.core.security.NeoComAuthenticationProvider;

public class FittingsControllerTest {
	@Test
	public void getPilotFittingsNeoComAuthorizationException() {
		// Given
		final FittingService fittingService = Mockito.mock( FittingService.class );
		final NeoComAuthenticationProvider neoComAuthenticationProvider = Mockito.mock( NeoComAuthenticationProvider.class );
//		final Integer pilotId = 93813310;
		final FittingsController fittingsController = new FittingsController( fittingService, neoComAuthenticationProvider );
		final ResponseEntity<List<Fitting>> response = new ResponseEntity( new ArrayList<Fitting>(), HttpStatus.OK );

		//When
		Mockito.when( fittingService.getPilotFittings() ).thenReturn( response );
		Mockito.when( neoComAuthenticationProvider.getAuthenticatedPilot() ).thenReturn( 93813310 );

		// Test
		NeoComAuthorizationException thrown = Assertions.assertThrows( NeoComAuthorizationException.class,
				() -> fittingsController.getPilotFittings( 100 ),
				"Expected fittingsController.getPilotFittings() to throw null verification, but it didn't." );
	}

	@Test
	public void getPilotFittingsSuccess() {
		// Given
		final FittingService fittingService = Mockito.mock( FittingService.class );
		final NeoComAuthenticationProvider neoComAuthenticationProvider = Mockito.mock( NeoComAuthenticationProvider.class );
		final Integer pilotId = 93813310;
		final FittingsController fittingsController = new FittingsController( fittingService, neoComAuthenticationProvider );
		final ResponseEntity<List<Fitting>> response = new ResponseEntity( new ArrayList<Fitting>(), HttpStatus.OK );

		//When
		Mockito.when( fittingService.getPilotFittings() ).thenReturn( response );
		Mockito.when( neoComAuthenticationProvider.getAuthenticatedPilot() ).thenReturn( 93813310 );

		// Test
		ResponseEntity<List<Fitting>> obtainedResponseEntity = fittingsController.getPilotFittings( pilotId );

		// Asserts
		Assertions.assertEquals( HttpStatus.OK, obtainedResponseEntity.getStatusCode() );
		Assertions.assertNotNull( obtainedResponseEntity.getBody() );
	}
}
