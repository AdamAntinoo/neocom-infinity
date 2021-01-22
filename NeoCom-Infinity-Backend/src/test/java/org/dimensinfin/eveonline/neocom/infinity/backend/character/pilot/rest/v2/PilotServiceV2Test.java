package org.dimensinfin.eveonline.neocom.infinity.backend.character.pilot.rest.v2;

import org.junit.jupiter.api.BeforeEach;
import org.mockito.Mockito;

import org.dimensinfin.eveonline.neocom.esiswagger.model.GetCharactersCharacterIdOk;
import org.dimensinfin.eveonline.neocom.esiswagger.model.GetUniverseAncestries200Ok;
import org.dimensinfin.eveonline.neocom.esiswagger.model.GetUniverseBloodlines200Ok;
import org.dimensinfin.eveonline.neocom.esiswagger.model.GetUniverseRaces200Ok;
import org.dimensinfin.eveonline.neocom.infinity.adapter.ConfigurationServiceWrapper;
import org.dimensinfin.eveonline.neocom.infinity.adapter.ESIDataProviderWrapper;
import org.dimensinfin.eveonline.neocom.provider.IConfigurationService;
import org.dimensinfin.eveonline.neocom.service.ESIDataService;

import static org.dimensinfin.eveonline.neocom.infinity.backend.support.TestDataConstants.PilotConstants.TEST_PILOT_ANCESTRY_ID;
import static org.dimensinfin.eveonline.neocom.infinity.backend.support.TestDataConstants.PilotConstants.TEST_PILOT_BLOODLINE_ID;
import static org.dimensinfin.eveonline.neocom.infinity.backend.support.TestDataConstants.PilotConstants.TEST_PILOT_RACE_ID;

public class PilotServiceV2Test {
	private ESIDataProviderWrapper esiDataProviderWrapper;
	private ESIDataService esiDataService;
	private ConfigurationServiceWrapper configurationServiceWrapper;
	private IConfigurationService configurationService;

	@BeforeEach
	public void beforeEach() {
		this.esiDataProviderWrapper = Mockito.mock( ESIDataProviderWrapper.class );
		this.esiDataService = Mockito.mock( ESIDataService.class );
		this.configurationServiceWrapper = Mockito.mock( ConfigurationServiceWrapper.class );
		this.configurationService = Mockito.mock( IConfigurationService.class );
	}

	//	@Test
	public void getPilotData() {
		// Given
		final GetCharactersCharacterIdOk pilotData = Mockito.mock( GetCharactersCharacterIdOk.class );
		final GetUniverseRaces200Ok raceData = Mockito.mock( GetUniverseRaces200Ok.class );
		final GetUniverseAncestries200Ok ancestryData = Mockito.mock( GetUniverseAncestries200Ok.class );
		final GetUniverseBloodlines200Ok bloodLineData = Mockito.mock( GetUniverseBloodlines200Ok.class );
		// When
		Mockito.when( this.esiDataProviderWrapper.getSingleton() ).thenReturn( this.esiDataService );
		Mockito.when( this.esiDataService.getCharactersCharacterId( Mockito.anyInt() ) ).thenReturn( pilotData );
		Mockito.when( this.configurationServiceWrapper.getSingleton() ).thenReturn( this.configurationService );
		Mockito.when( this.configurationService.getResourceString( Mockito.anyString() ) ).thenReturn( "P.universe.retrofit.server.location" );
		Mockito.when( pilotData.getRaceId() ).thenReturn( TEST_PILOT_RACE_ID );
		Mockito.when( pilotData.getAncestryId() ).thenReturn( TEST_PILOT_ANCESTRY_ID );
		Mockito.when( pilotData.getBloodlineId() ).thenReturn( TEST_PILOT_BLOODLINE_ID );
		Mockito.when( this.esiDataService.searchSDERace( Mockito.anyInt() ) ).thenReturn( raceData );
		Mockito.when( this.esiDataService.searchSDEAncestry( Mockito.anyInt() ) ).thenReturn( ancestryData );
		Mockito.when( this.esiDataService.searchSDEBloodline( Mockito.anyInt() ) ).thenReturn( bloodLineData );
		// Test
		// TODO - Reimplement the tests for the new code
		//		final PilotServiceV2 pilotServiceV2 = new PilotServiceV2( this.esiDataService, this.configurationServiceWrapper );
		//		final PilotV1 obtained = pilotServiceV2.getAuthenticatedPilotData( TEST_PILOT_ID );
		//		// Assertions
		//		Assertions.assertNotNull( obtained );
		//		Assertions.assertEquals( TEST_PILOT_ID, obtained.getPilotId() );
		//		Assertions.assertEquals( TEST_PILOT_RACE_ID, obtained.getPilotPublicData().getRaceId() );
		//		Assertions.assertEquals( TEST_PILOT_ANCESTRY_ID, obtained.getPilotPublicData().getAncestryId() );
		//		Assertions.assertEquals( TEST_PILOT_BLOODLINE_ID, obtained.getPilotPublicData().getBloodlineId() );
	}
}
