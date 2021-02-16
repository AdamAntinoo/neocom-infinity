package org.dimensinfin.eveonline.neocom.infinity.backend.character.pilot.converter;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.Mockito;

import org.dimensinfin.eveonline.neocom.character.domain.PilotV1;
import org.dimensinfin.eveonline.neocom.esiswagger.model.GetCharactersCharacterIdOk;
import org.dimensinfin.eveonline.neocom.esiswagger.model.GetUniverseAncestries200Ok;
import org.dimensinfin.eveonline.neocom.esiswagger.model.GetUniverseBloodlines200Ok;
import org.dimensinfin.eveonline.neocom.esiswagger.model.GetUniverseRaces200Ok;
import org.dimensinfin.eveonline.neocom.provider.ESIDataProvider;
import org.dimensinfin.eveonline.neocom.provider.ESIUniverseDataProvider;
import org.dimensinfin.eveonline.neocom.provider.IConfigurationService;

import static org.dimensinfin.eveonline.neocom.infinity.backend.support.TestDataConstants.PilotConstants.TEST_PILOT_CORPORATION_ID;
import static org.dimensinfin.eveonline.neocom.infinity.backend.support.TestDataConstants.PilotConstants.TEST_PILOT_ID;

public class EsiPilotDataToPilotV1ConverterTest {

	private IConfigurationService configurationService;
	//	private ESIDataProviderWrapper esiDataProviderWrapper;
	private ESIUniverseDataProvider esiUniverseDataProvider;

	@BeforeEach
	public void beforeEach() {
		this.configurationService = Mockito.mock( IConfigurationService.class );
		//		this.esiDataProviderWrapper = Mockito.mock(ESIDataProviderWrapper.class);
		this.esiUniverseDataProvider = Mockito.mock( ESIDataProvider.class );
	}

	//	@Test
	public void convert() {
		// Given
		final Integer pilotId = TEST_PILOT_ID;
		final EsiPilotDataToPilotModelConverter esiPilotDataToPilotModelConverter = new EsiPilotDataToPilotModelConverter(
				this.configurationService,
				this.esiUniverseDataProvider,
				pilotId
		);
		final GetCharactersCharacterIdOk esiCharacterData = Mockito.mock( GetCharactersCharacterIdOk.class );
		final GetUniverseRaces200Ok raceData = Mockito.mock( GetUniverseRaces200Ok.class );
		final GetUniverseAncestries200Ok ancestryData = Mockito.mock( GetUniverseAncestries200Ok.class );
		final GetUniverseBloodlines200Ok bloodLineData = Mockito.mock( GetUniverseBloodlines200Ok.class );
		// When
		Mockito.when( esiCharacterData.getCorporationId() ).thenReturn( TEST_PILOT_CORPORATION_ID );
		Mockito.when( this.esiUniverseDataProvider.searchSDERace( Mockito.anyInt() ) ).thenReturn( raceData );
		Mockito.when( this.esiUniverseDataProvider.searchSDEAncestry( Mockito.anyInt() ) ).thenReturn( ancestryData );
		Mockito.when( this.esiUniverseDataProvider.searchSDEBloodline( Mockito.anyInt() ) ).thenReturn( bloodLineData );
		// Test
		final PilotV1 obtained = esiPilotDataToPilotModelConverter.convert( esiCharacterData );
		// Assertions
		Assertions.assertEquals( pilotId, obtained.getPilotId() );
	}
}
