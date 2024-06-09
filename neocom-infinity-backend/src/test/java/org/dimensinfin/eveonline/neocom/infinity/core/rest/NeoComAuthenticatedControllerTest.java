package org.dimensinfin.eveonline.neocom.infinity.core.rest;

import javax.validation.constraints.NotNull;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import org.dimensinfin.eveonline.neocom.infinity.config.security.NeoComAuthenticationProvider;
import org.dimensinfin.eveonline.neocom.infinity.core.exception.NeoComRuntimeBackendExceptionObsolete;

public class NeoComAuthenticatedControllerTest {

	private NeoComAuthenticationProvider neoComAuthenticationProvider;

	@BeforeEach
	public void beforeEach() {
		this.neoComAuthenticationProvider = Mockito.mock( NeoComAuthenticationProvider.class );
	}

	@Test
	public void constructorContract() {
		final TestAuthenticatedController controller = new TestAuthenticatedController( this.neoComAuthenticationProvider );
		Assertions.assertNotNull( controller );
	}

	@Test
	public void validateAuthorizedPilot() {
		// Given
		final Integer TEST_PILOT_ID = 98667543;
		// When
		Mockito.when( this.neoComAuthenticationProvider.getAuthenticatedPilot() ).thenReturn( TEST_PILOT_ID );
		// Test
		final TestAuthenticatedController controller = new TestAuthenticatedController( this.neoComAuthenticationProvider );
		controller.validateAuthorizedPilot( TEST_PILOT_ID );
		// Assertions
		Mockito.verify( this.neoComAuthenticationProvider, Mockito.times( 1 ) ).getAuthenticatedPilot();
	}

	@Test
	public void validateAuthorizedPilotException() {
		// Given
		final Integer TEST_PILOT_ID = 98667543;
		// When
		Mockito.when( this.neoComAuthenticationProvider.getAuthenticatedPilot() ).thenReturn( TEST_PILOT_ID + 1000 );
		// Test
		final TestAuthenticatedController controller = new TestAuthenticatedController( this.neoComAuthenticationProvider );
		final NeoComRuntimeBackendExceptionObsolete exception = Assertions.assertThrows( NeoComRuntimeBackendExceptionObsolete.class, () -> {
			controller.validateAuthorizedPilot( TEST_PILOT_ID );
		} );
		// Assertions
		Assertions.assertNotNull( controller );
		Assertions.assertNotNull( exception );
		Assertions.assertEquals( "PILOT_ID_NOT_AUTHORIZED", exception.getErrorName() );
		Assertions.assertEquals( "dimensinfin.neocom.authorization.unauthorized", exception.getErrorCode() );
	}
}

final class TestAuthenticatedController extends NeoComAuthenticatedController {

	// - C O N S T R U C T O R S
	public TestAuthenticatedController( final @NotNull NeoComAuthenticationProvider neoComAuthenticationProvider ) {
		super( neoComAuthenticationProvider );
	}
}