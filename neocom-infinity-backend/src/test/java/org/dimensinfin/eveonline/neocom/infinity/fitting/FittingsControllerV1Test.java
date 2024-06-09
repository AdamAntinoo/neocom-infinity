package org.dimensinfin.eveonline.neocom.infinity.fitting;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.dimensinfin.eveonline.neocom.domain.Fitting;
import org.dimensinfin.eveonline.neocom.infinity.backend.character.fitting.rest.v1.FittingsControllerV1;
import org.dimensinfin.eveonline.neocom.infinity.config.security.NeoComAuthenticationProvider;
import org.dimensinfin.eveonline.neocom.infinity.core.exception.NeoComRuntimeBackendExceptionObsolete;

public class FittingsControllerV1Test {

	private NeoComAuthenticationProvider neoComAuthenticationProvider;
	private FittingService fittingService;

	@BeforeEach
	public void beforeEach() {
		this.neoComAuthenticationProvider = Mockito.mock( NeoComAuthenticationProvider.class );
		this.fittingService = Mockito.mock( FittingService.class );
	}

	@Test
	public void getPilotFittingsNeoComAuthorizationException() {
		// Given
		final FittingsControllerV1 fittingsControllerV1 = new FittingsControllerV1( this.neoComAuthenticationProvider, this.fittingService );
		final ResponseEntity<List<Fitting>> response = new ResponseEntity( new ArrayList<Fitting>(), HttpStatus.OK );
		//When
		Mockito.when( this.fittingService.getPilotFittings() ).thenReturn( response );
		Mockito.when( this.neoComAuthenticationProvider.getAuthenticatedPilot() ).thenReturn( 93813310 );
		// Test
		final NeoComRuntimeBackendExceptionObsolete thrown = Assertions.assertThrows( NeoComRuntimeBackendExceptionObsolete.class,
				() -> fittingsControllerV1.getPilotFittings( 100 ),
				"Expected fittingsController.getPilotFittings() to throw null verification, but it didn't."
		);
	}

	@Test
	public void getPilotFittingsSuccess() {
		// Given
		final Integer pilotId = 93813310;
		final FittingsControllerV1 fittingsControllerV1 = new FittingsControllerV1( this.neoComAuthenticationProvider, this.fittingService );
		final ResponseEntity<List<Fitting>> response = new ResponseEntity( new ArrayList<Fitting>(), HttpStatus.OK );
		//When
		Mockito.when( this.fittingService.getPilotFittings() ).thenReturn( response );
		Mockito.when( this.neoComAuthenticationProvider.getAuthenticatedPilot() ).thenReturn( 93813310 );
		// Test
		final ResponseEntity<List<Fitting>> obtainedResponseEntity = fittingsControllerV1.getPilotFittings( pilotId );

		// Asserts
		Assertions.assertEquals( HttpStatus.OK, obtainedResponseEntity.getStatusCode() );
		Assertions.assertNotNull( obtainedResponseEntity.getBody() );
	}
}
