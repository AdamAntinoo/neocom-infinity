package org.dimensinfin.eveonline.neocom.infinity.backend.character.pilot.rest.v1;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import org.dimensinfin.eveonline.neocom.domain.Pilot;
import org.dimensinfin.eveonline.neocom.esiswagger.model.GetCharactersCharacterIdOk;
import org.dimensinfin.eveonline.neocom.esiswagger.model.GetUniverseAncestries200Ok;
import org.dimensinfin.eveonline.neocom.esiswagger.model.GetUniverseBloodlines200Ok;
import org.dimensinfin.eveonline.neocom.esiswagger.model.GetUniverseRaces200Ok;
import org.dimensinfin.eveonline.neocom.infinity.adapter.ESIDataProviderWrapper;
import org.dimensinfin.eveonline.neocom.provider.ESIDataProvider;

import static org.dimensinfin.eveonline.neocom.infinity.backend.support.TestDataConstants.PilotConstants.TEST_PILOT_ANCESTRY_ID;
import static org.dimensinfin.eveonline.neocom.infinity.backend.support.TestDataConstants.PilotConstants.TEST_PILOT_BLOODLINE_ID;
import static org.dimensinfin.eveonline.neocom.infinity.backend.support.TestDataConstants.PilotConstants.TEST_PILOT_ID;
import static org.dimensinfin.eveonline.neocom.infinity.backend.support.TestDataConstants.PilotConstants.TEST_PILOT_RACE_ID;

public class PilotServiceV1Test {
	private ESIDataProviderWrapper esiDataProviderWrapper;
	private ESIDataProvider esiDataProvider;

	@BeforeEach
	public void beforeEach() {
		this.esiDataProviderWrapper = Mockito.mock( ESIDataProviderWrapper.class );
		this.esiDataProvider = Mockito.mock( ESIDataProvider.class );
	}

	@Test
	public void getPilotData() {
		// Given
		final GetCharactersCharacterIdOk pilotData = Mockito.mock( GetCharactersCharacterIdOk.class );
		final GetUniverseRaces200Ok raceData = Mockito.mock( GetUniverseRaces200Ok.class );
		final GetUniverseAncestries200Ok ancestryData = Mockito.mock( GetUniverseAncestries200Ok.class );
		final GetUniverseBloodlines200Ok bloodLineData = Mockito.mock( GetUniverseBloodlines200Ok.class );
		// When
		Mockito.when( this.esiDataProviderWrapper.getSingleton() ).thenReturn( this.esiDataProvider );
		Mockito.when( this.esiDataProvider.getCharactersCharacterId( Mockito.anyInt() ) ).thenReturn( pilotData );
		Mockito.when( pilotData.getRaceId() ).thenReturn( TEST_PILOT_RACE_ID );
		Mockito.when( pilotData.getAncestryId() ).thenReturn( TEST_PILOT_ANCESTRY_ID );
		Mockito.when( pilotData.getBloodlineId() ).thenReturn( TEST_PILOT_BLOODLINE_ID );
		Mockito.when( this.esiDataProvider.searchSDERace( Mockito.anyInt() ) ).thenReturn( raceData );
		Mockito.when( this.esiDataProvider.searchSDEAncestry( Mockito.anyInt() ) ).thenReturn( ancestryData );
		Mockito.when( this.esiDataProvider.searchSDEBloodline( Mockito.anyInt() ) ).thenReturn( bloodLineData );
		// Test
		final PilotServiceV1 pilotServiceV1 = new PilotServiceV1( this.esiDataProviderWrapper );
		final Pilot obtained = pilotServiceV1.getPilotData( TEST_PILOT_ID );
		// Assertions
		Assertions.assertNotNull( obtained );
		Assertions.assertEquals( TEST_PILOT_ID, obtained.getPilotId() );
		Assertions.assertEquals( TEST_PILOT_RACE_ID, obtained.getRaceId() );
		Assertions.assertEquals( TEST_PILOT_ANCESTRY_ID, obtained.getAncestryId() );
		Assertions.assertEquals( TEST_PILOT_BLOODLINE_ID, obtained.getBloodlineId() );
	}
}
