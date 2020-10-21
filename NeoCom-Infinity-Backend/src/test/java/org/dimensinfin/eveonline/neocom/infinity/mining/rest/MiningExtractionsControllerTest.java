package org.dimensinfin.eveonline.neocom.infinity.mining.rest;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.http.ResponseEntity;

import org.dimensinfin.eveonline.neocom.database.entities.MiningExtractionEntity;
import org.dimensinfin.eveonline.neocom.infinity.core.exceptions.NeoComAuthorizationException;
import org.dimensinfin.eveonline.neocom.infinity.core.security.NeoComAuthenticationProvider;
import org.dimensinfin.eveonline.neocom.infinity.mining.rest.v1.MiningExtractionsController;
import org.dimensinfin.eveonline.neocom.infinity.mining.rest.v1.MiningExtractionsService;

public class MiningExtractionsControllerTest {
	private NeoComAuthenticationProvider authenticationProvider;
	private MiningExtractionsService miningExtractionsService;

	@BeforeEach
	public void beforeEach() {
		this.authenticationProvider = Mockito.mock( NeoComAuthenticationProvider.class );
		this.miningExtractionsService = Mockito.mock( MiningExtractionsService.class );
	}

	@Test
	public void constructionContract() {
		final MiningExtractionsController miningExtractionsController = new MiningExtractionsController(
				this.authenticationProvider, this.miningExtractionsService );
		Assertions.assertNotNull( miningExtractionsController );
	}

	@Test
	public void getTodayMiningExtractions4Pilot() {
		// Given
		final MiningExtractionsController miningExtractionsController = new MiningExtractionsController(
				this.authenticationProvider, this.miningExtractionsService );
		final MiningExtractionEntity miningExtraction = Mockito.mock( MiningExtractionEntity.class );
		List<MiningExtractionEntity> responseMiningExtractionsList = new ArrayList<>();
		responseMiningExtractionsList.add( miningExtraction );
		// When
		Mockito.when( this.authenticationProvider.getAuthenticatedPilot() ).thenReturn( 321654 );
		Mockito.when( this.miningExtractionsService.getTodayMiningExtractions4Pilot() ).thenReturn(
				responseMiningExtractionsList
		);
		// Test
		final ResponseEntity<List<MiningExtractionEntity>> obtained = miningExtractionsController.getTodayMiningExtractions4Pilot( 321654 );
		// Assertions
		Assertions.assertNotNull( obtained );
		Assertions.assertNotNull( obtained.getBody() );
		Assertions.assertEquals( miningExtraction, obtained.getBody().get( 0 ) );
	}

	@Test
	public void getTodayMiningExtractions4PilotNotAuthenticated() {
		// Given
		final MiningExtractionsController miningExtractionsController = new MiningExtractionsController(
				this.authenticationProvider, this.miningExtractionsService );
		final MiningExtractionEntity miningExtraction = Mockito.mock( MiningExtractionEntity.class );
		List<MiningExtractionEntity> responseMiningExtractionsList = new ArrayList<>();
		responseMiningExtractionsList.add( miningExtraction );
		// When
		Mockito.when( this.authenticationProvider.getAuthenticatedPilot() ).thenReturn( 321654 );
		Mockito.when( this.miningExtractionsService.getTodayMiningExtractions4Pilot() ).thenReturn(
				responseMiningExtractionsList
		);
		// Exception
		Assertions.assertThrows( NeoComAuthorizationException.class, () -> {
			miningExtractionsController.getTodayMiningExtractions4Pilot( -321654 );
		} );
	}
}
