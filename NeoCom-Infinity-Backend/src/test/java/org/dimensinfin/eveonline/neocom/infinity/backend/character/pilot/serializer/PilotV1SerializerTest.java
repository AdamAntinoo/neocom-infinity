package org.dimensinfin.eveonline.neocom.infinity.backend.character.pilot.serializer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import org.dimensinfin.eveonline.neocom.character.domain.PilotV1;
import org.dimensinfin.eveonline.neocom.character.domain.PublicCorporationV1;
import org.dimensinfin.eveonline.neocom.domain.EsiType;
import org.dimensinfin.eveonline.neocom.domain.space.SpaceLocation;
import org.dimensinfin.eveonline.neocom.domain.space.SpaceLocationImplementation;
import org.dimensinfin.eveonline.neocom.esiswagger.model.GetCharactersCharacterIdOk;
import org.dimensinfin.eveonline.neocom.esiswagger.model.GetCharactersCharacterIdShipOk;
import org.dimensinfin.eveonline.neocom.esiswagger.model.GetUniverseAncestries200Ok;
import org.dimensinfin.eveonline.neocom.esiswagger.model.GetUniverseBloodlines200Ok;
import org.dimensinfin.eveonline.neocom.esiswagger.model.GetUniverseConstellationsConstellationIdOk;
import org.dimensinfin.eveonline.neocom.esiswagger.model.GetUniverseRaces200Ok;
import org.dimensinfin.eveonline.neocom.esiswagger.model.GetUniverseRegionsRegionIdOk;
import org.dimensinfin.eveonline.neocom.esiswagger.model.GetUniverseStationsStationIdOk;
import org.dimensinfin.eveonline.neocom.esiswagger.model.GetUniverseSystemsSystemIdOk;

import static org.dimensinfin.eveonline.neocom.esiswagger.model.GetCharactersCharacterIdOk.GenderEnum.FEMALE;
import static org.dimensinfin.eveonline.neocom.infinity.backend.support.TestDataConstants.PilotConstants.TEST_PILOT_BIRTHDATE;
import static org.dimensinfin.eveonline.neocom.infinity.backend.support.TestDataConstants.PilotConstants.TEST_PILOT_CORPORATION_ID;
import static org.dimensinfin.eveonline.neocom.infinity.backend.support.TestDataConstants.PilotConstants.TEST_PILOT_DESCRIPTION;
import static org.dimensinfin.eveonline.neocom.infinity.backend.support.TestDataConstants.PilotConstants.TEST_PILOT_ID;
import static org.dimensinfin.eveonline.neocom.infinity.backend.support.TestDataConstants.PilotConstants.TEST_PILOT_NAME;
import static org.dimensinfin.eveonline.neocom.infinity.backend.support.TestDataConstants.PilotConstants.TEST_PILOT_SECURITY_STATUS;
import static org.dimensinfin.eveonline.neocom.infinity.backend.support.TestDataConstants.PilotConstants.TEST_PILOT_SKILL_POINTS;
import static org.dimensinfin.eveonline.neocom.infinity.backend.support.TestDataConstants.PilotConstants.TEST_PILOT_WALLET_BALANCE;
import static org.dimensinfin.eveonline.neocom.infinity.backend.universe.serializer.SpaceLocationSerializerTest.TEST_CONSTELLATION_ID;
import static org.dimensinfin.eveonline.neocom.infinity.backend.universe.serializer.SpaceLocationSerializerTest.TEST_CONSTELLATION_NAME;
import static org.dimensinfin.eveonline.neocom.infinity.backend.universe.serializer.SpaceLocationSerializerTest.TEST_REGION_ID;
import static org.dimensinfin.eveonline.neocom.infinity.backend.universe.serializer.SpaceLocationSerializerTest.TEST_REGION_NAME;
import static org.dimensinfin.eveonline.neocom.infinity.backend.universe.serializer.SpaceLocationSerializerTest.TEST_SOLAR_SYSTEM_ID;
import static org.dimensinfin.eveonline.neocom.infinity.backend.universe.serializer.SpaceLocationSerializerTest.TEST_SOLAR_SYSTEM_NAME;
import static org.dimensinfin.eveonline.neocom.infinity.backend.universe.serializer.SpaceLocationSerializerTest.TEST_SOLAR_SYSTEM_SECURITY_CLASS;
import static org.dimensinfin.eveonline.neocom.infinity.backend.universe.serializer.SpaceLocationSerializerTest.TEST_SOLAR_SYSTEM_SECURITY_STATUS;
import static org.dimensinfin.eveonline.neocom.infinity.backend.universe.serializer.SpaceLocationSerializerTest.TEST_STATION_ID;
import static org.dimensinfin.eveonline.neocom.infinity.backend.universe.serializer.SpaceLocationSerializerTest.TEST_STATION_NAME;

public class PilotV1SerializerTest {
	private ObjectMapper objectMapper;
	private GetCharactersCharacterIdOk pilotPublicData;
	private GetUniverseAncestries200Ok ancestryData;
	private GetUniverseBloodlines200Ok bloodlineData;
	private GetUniverseRaces200Ok raceData;
	private PublicCorporationV1 corporation;
	private SpaceLocation location;
	private EsiType shipType;
	private GetCharactersCharacterIdShipOk esiPilotShip;

	@BeforeEach
	public void beforeEach() {
		this.objectMapper = new ObjectMapper();
		final SimpleModule module = new SimpleModule();
		module.addSerializer( PilotV1.class, new PilotV1Serializer() );
		this.objectMapper.registerModule( module );

		this.pilotPublicData = Mockito.mock( GetCharactersCharacterIdOk.class );
		this.ancestryData = Mockito.mock( GetUniverseAncestries200Ok.class );
		this.bloodlineData = Mockito.mock( GetUniverseBloodlines200Ok.class );
		this.raceData = Mockito.mock( GetUniverseRaces200Ok.class );
		this.corporation = Mockito.mock( PublicCorporationV1.class );
		this.shipType = Mockito.mock( EsiType.class );
		this.esiPilotShip = Mockito.mock( GetCharactersCharacterIdShipOk.class );
	}

	@Test
	public void serialize() throws JsonProcessingException {
		// Given
		final GetUniverseRegionsRegionIdOk region = Mockito.mock( GetUniverseRegionsRegionIdOk.class );
		final GetUniverseConstellationsConstellationIdOk constellation =
				Mockito.mock( GetUniverseConstellationsConstellationIdOk.class );
		final GetUniverseSystemsSystemIdOk solarSystem = Mockito.mock( GetUniverseSystemsSystemIdOk.class );
		final GetUniverseStationsStationIdOk station = Mockito.mock( GetUniverseStationsStationIdOk.class );
		final SpaceLocationImplementation location = new SpaceLocationImplementation.Builder()
				.withRegion( region )
				.withConstellation( constellation )
				.withSolarSystem( solarSystem )
				.withStation( station )
				.build();
		// When
		Mockito.when( this.pilotPublicData.getName() ).thenReturn( TEST_PILOT_NAME );
		Mockito.when( this.pilotPublicData.getDescription() ).thenReturn( TEST_PILOT_DESCRIPTION );
		Mockito.when( this.pilotPublicData.getCorporationId() ).thenReturn( TEST_PILOT_CORPORATION_ID );
		Mockito.when( this.pilotPublicData.getBirthday() ).thenReturn( TEST_PILOT_BIRTHDATE );
		Mockito.when( this.pilotPublicData.getGender() ).thenReturn( FEMALE );
		Mockito.when( this.pilotPublicData.getSecurityStatus() ).thenReturn( TEST_PILOT_SECURITY_STATUS );
		Mockito.when( this.shipType.getName() ).thenReturn( "-SHIP-" );
		Mockito.when( this.esiPilotShip.getShipName() ).thenReturn( "-PILOT-SHIP-" );
		Mockito.when( region.getRegionId() ).thenReturn( TEST_REGION_ID );
		Mockito.when( region.getName() ).thenReturn( TEST_REGION_NAME );
		Mockito.when( constellation.getConstellationId() ).thenReturn( TEST_CONSTELLATION_ID );
		Mockito.when( constellation.getName() ).thenReturn( TEST_CONSTELLATION_NAME );
		Mockito.when( solarSystem.getSystemId() ).thenReturn( TEST_SOLAR_SYSTEM_ID );
		Mockito.when( solarSystem.getName() ).thenReturn( TEST_SOLAR_SYSTEM_NAME );
		Mockito.when( solarSystem.getSecurityClass() ).thenReturn( TEST_SOLAR_SYSTEM_SECURITY_CLASS );
		Mockito.when( solarSystem.getSecurityStatus() ).thenReturn( TEST_SOLAR_SYSTEM_SECURITY_STATUS );
		Mockito.when( station.getStationId() ).thenReturn( TEST_STATION_ID );
		Mockito.when( station.getName() ).thenReturn( TEST_STATION_NAME );
		// Test
		final PilotV1 pilot = new PilotV1.Builder()
				.withPilotId( TEST_PILOT_ID )
				.withPilotPublicData( this.pilotPublicData )
				.withAncestryData( this.ancestryData )
				.withBloodlineData( this.bloodlineData )
				.withRaceData( this.raceData )
				.withCorporation( this.corporation )
				.withLastKnownLocation( location )
				.withCurrentShipType( this.shipType )
				.withCurrentShip( this.esiPilotShip )
				.withTotalSkillPoints( TEST_PILOT_SKILL_POINTS )
				.withWalletBalance( TEST_PILOT_WALLET_BALANCE )
				.build();
		final String json = this.objectMapper.writeValueAsString( pilot );
		final String expected = "{\"pilotId\":93813310,\"name\":\"-TEST_PILOT_NAME-\",\"description\":\"-DESCRIPTION-\",\"corporationId\":98384726,\"corporation\":{\"rel\":\"corporation\",\"href\":\"/api/v1/public/corporations/98384726\"},\"birthday\":\"2012-07-05T21:53:15.000Z\",\"gender\":\"female\",\"securityStatus\":0.1,\"url4Icon\":\"https://image.eveonline.com/Character/93813310_256.jpg\",\"raceData\":{\"allianceId\":0,\"description\":null,\"name\":null,\"raceId\":0},\"ancestryData\":{\"bloodlineId\":0,\"description\":null,\"iconId\":0,\"id\":0,\"name\":null,\"shortDescription\":null},\"bloodlineData\":{\"bloodlineId\":0,\"charisma\":0,\"corporationId\":0,\"description\":null,\"intelligence\":0,\"memory\":0,\"name\":null,\"perception\":0,\"raceId\":0,\"shipTypeId\":0,\"willpower\":0},\"totalSkillpoints\":1436765,\"walletBalance\":432567.0,\"currentShipName\":\"-PILOT-SHIP-\",\"currentShipTypeName\":\"-SHIP-\",\"lastKnownLocation\":{\"regionId\":0,\"regionName\":null,\"constellationId\":0,\"constellationName\":null,\"solarSystemId\":0,\"solarSystemName\":null,\"stationId\":0,\"stationName\":null,\"securityClass\":null,\"securityStatus\":0.0,\"locationId\":0,\"locationType\":\"STATION\",\"jsonClass\":\"SpaceLocationImplementation\"}}";
		Assertions.assertEquals( expected, json );
	}
}