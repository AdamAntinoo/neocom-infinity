package org.dimensinfin.eveonline.neocom.infinity.backend.character.fitting.rest.v2;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.http.ResponseEntity;

import org.dimensinfin.eveonline.neocom.domain.EsiType;
import org.dimensinfin.eveonline.neocom.infinity.backend.character.fitting.domain.FittingModel;
import org.dimensinfin.eveonline.neocom.infinity.config.security.NeoComAuthenticationProvider;

import static org.dimensinfin.eveonline.neocom.infinity.backend.support.TestDataConstants.FittingConstants.TEST_FITTING_DESCRIPTION;
import static org.dimensinfin.eveonline.neocom.infinity.backend.support.TestDataConstants.FittingConstants.TEST_FITTING_ID;
import static org.dimensinfin.eveonline.neocom.infinity.backend.support.TestDataConstants.FittingConstants.TEST_FITTING_NAME;
import static org.dimensinfin.eveonline.neocom.infinity.backend.support.TestDataConstants.PilotConstants.TEST_PILOT_ID;

public class PilotFittingsControllerV2Test {

	private NeoComAuthenticationProvider neoComAuthenticationProvider;
	private PilotFittingsServiceV2 pilotFittingsServiceV2;

	@BeforeEach
	public void beforeEach() {
		this.neoComAuthenticationProvider = Mockito.mock( NeoComAuthenticationProvider.class );
		this.pilotFittingsServiceV2 = Mockito.mock( PilotFittingsServiceV2.class );
	}

	@Test
	public void getPilotFittings() {
		// Given
		final Integer pilotId = TEST_PILOT_ID;
		final EsiType shipHull = Mockito.mock( EsiType.class );
		final List<FittingModel> fittingList = new ArrayList<>();
		fittingList.add( new FittingModel.Builder()
				.withFittingId( TEST_FITTING_ID )
				.withName( TEST_FITTING_NAME )
				.withDescription( TEST_FITTING_DESCRIPTION )
				.withShipHull( shipHull )
				.build() );
		fittingList.add( new FittingModel.Builder()
				.withFittingId( TEST_FITTING_ID )
				.withName( TEST_FITTING_NAME )
				.withDescription( TEST_FITTING_DESCRIPTION )
				.withShipHull( shipHull )
				.build() );
		// When
		Mockito.when( this.pilotFittingsServiceV2.getPilotFittings() ).thenReturn( fittingList );
		Mockito.when( this.neoComAuthenticationProvider.getAuthenticatedPilot() ).thenReturn( pilotId );
		// Test
		final PilotFittingsControllerV2 pilotFittingsController = new PilotFittingsControllerV2(
				this.neoComAuthenticationProvider,
				this.pilotFittingsServiceV2
		);
		final ResponseEntity<List<FittingModel>> obtained = pilotFittingsController.getPilotFittings( pilotId );
		// Assertions
		Assertions.assertNotNull( obtained );
		Assertions.assertNotNull( obtained.getBody() );
		Assertions.assertEquals( 2, obtained.getBody().size() );
	}
}
