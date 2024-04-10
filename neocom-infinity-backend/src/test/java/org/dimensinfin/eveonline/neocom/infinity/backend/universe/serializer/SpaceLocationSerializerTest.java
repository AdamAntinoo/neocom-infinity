package org.dimensinfin.eveonline.neocom.infinity.backend.universe.serializer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import org.dimensinfin.eveonline.neocom.domain.space.SpaceLocationImplementation;
import org.dimensinfin.eveonline.neocom.domain.space.Structure;
import org.dimensinfin.eveonline.neocom.esiswagger.model.GetUniverseConstellationsConstellationIdOk;
import org.dimensinfin.eveonline.neocom.esiswagger.model.GetUniverseRegionsRegionIdOk;
import org.dimensinfin.eveonline.neocom.esiswagger.model.GetUniverseStationsStationIdOk;
import org.dimensinfin.eveonline.neocom.esiswagger.model.GetUniverseStructuresStructureIdOk;
import org.dimensinfin.eveonline.neocom.esiswagger.model.GetUniverseSystemsSystemIdOk;

public class SpaceLocationSerializerTest {
	public static final Integer TEST_REGION_ID = 1000043;
	public static final String TEST_REGION_NAME = "Domain";
	public static final Integer TEST_CONSTELLATION_ID = 20000322;
	public static final String TEST_CONSTELLATION_NAME = "Throne Worlds";
	public static final Integer TEST_SOLAR_SYSTEM_ID = 30002187;
	public static final String TEST_SOLAR_SYSTEM_NAME = "Amarr";
	public static final String TEST_SOLAR_SYSTEM_SECURITY_CLASS = "E1";
	public static final Float TEST_SOLAR_SYSTEM_SECURITY_STATUS = 0.5623334F;
	public static final Integer TEST_STATION_ID = 60008494;
	public static final String TEST_STATION_NAME = "Amarr VIII (Oris) - Emperor Family Academy";
	public static final Long TEST_STRUCTURE_ID = 60008494L;
	public static final Integer TEST_STRUCTURE_TYPE_ID = 60008494;
	public static final String TEST_STRUCTURE_NAME = "Amarr VIII (Oris) - Emperor Family Academy";
	public static final Integer TEST_CORPORATION_ID = 98384726;
	public static final String TEST_CORPORATION_NAME = "Industrias Machaque";
	public static final Integer TEST_CORPORATION_OWNER_ID = 98384726;
	private ObjectMapper objectMapper;

	@BeforeEach
	public void beforeEach() {
		this.objectMapper = new ObjectMapper();
		final SimpleModule module = new SimpleModule();
		module.addSerializer( SpaceLocationImplementation.class, new SpaceLocationSerializer() );
		this.objectMapper.registerModule( module );
	}

	@Test
	public void serializeCONSTELLATION() throws JsonProcessingException {
		// Given
		final GetUniverseRegionsRegionIdOk region = Mockito.mock( GetUniverseRegionsRegionIdOk.class );
		final GetUniverseConstellationsConstellationIdOk constellation =
				Mockito.mock( GetUniverseConstellationsConstellationIdOk.class );
		// When
		Mockito.when( region.getRegionId() ).thenReturn( TEST_REGION_ID );
		Mockito.when( region.getName() ).thenReturn( TEST_REGION_NAME );
		Mockito.when( constellation.getConstellationId() ).thenReturn( TEST_CONSTELLATION_ID );
		Mockito.when( constellation.getName() ).thenReturn( TEST_CONSTELLATION_NAME );
		// Test
		final SpaceLocationImplementation location = new SpaceLocationImplementation.Builder()
				.withRegion( region )
				.withConstellation( constellation )
				.build();
		final String json = this.objectMapper.writeValueAsString( location );
		final String expected = "{\"locationType\":\"CONSTELLATION\",\"locationId\":20000322,\"constellationId\":20000322,\"constellationName\":\"Throne Worlds\",\"regionId\":1000043,\"regionName\":\"Domain\"}";
		Assertions.assertEquals( expected, json );
	}

	@Test
	public void serializeREGION() throws JsonProcessingException {
		// Given
		final GetUniverseRegionsRegionIdOk region = Mockito.mock( GetUniverseRegionsRegionIdOk.class );
		// When
		Mockito.when( region.getRegionId() ).thenReturn( TEST_REGION_ID );
		Mockito.when( region.getName() ).thenReturn( TEST_REGION_NAME );
		// Test
		final SpaceLocationImplementation location = new SpaceLocationImplementation.Builder()
				.withRegion( region )
				.build();
		final String json = this.objectMapper.writeValueAsString( location );
		final String expected = "{\"locationType\":\"REGION\",\"locationId\":1000043,\"regionId\":1000043,\"regionName\":\"Domain\"}";
		Assertions.assertEquals( expected, json );
	}

	@Test
	public void serializeSOLAR_SYSTEM() throws JsonProcessingException {
		// Given
		final GetUniverseRegionsRegionIdOk region = Mockito.mock( GetUniverseRegionsRegionIdOk.class );
		final GetUniverseConstellationsConstellationIdOk constellation =
				Mockito.mock( GetUniverseConstellationsConstellationIdOk.class );
		final GetUniverseSystemsSystemIdOk solarSystem = Mockito.mock( GetUniverseSystemsSystemIdOk.class );
		// When
		Mockito.when( region.getRegionId() ).thenReturn( TEST_REGION_ID );
		Mockito.when( region.getName() ).thenReturn( TEST_REGION_NAME );
		Mockito.when( constellation.getConstellationId() ).thenReturn( TEST_CONSTELLATION_ID );
		Mockito.when( constellation.getName() ).thenReturn( TEST_CONSTELLATION_NAME );
		Mockito.when( solarSystem.getSystemId() ).thenReturn( TEST_SOLAR_SYSTEM_ID );
		Mockito.when( solarSystem.getName() ).thenReturn( TEST_SOLAR_SYSTEM_NAME );
		Mockito.when( solarSystem.getSecurityClass() ).thenReturn( TEST_SOLAR_SYSTEM_SECURITY_CLASS );
		Mockito.when( solarSystem.getSecurityStatus() ).thenReturn( TEST_SOLAR_SYSTEM_SECURITY_STATUS );
		// Test
		final SpaceLocationImplementation location = new SpaceLocationImplementation.Builder()
				.withRegion( region )
				.withConstellation( constellation )
				.withSolarSystem( solarSystem )
				.build();
		final String json = this.objectMapper.writeValueAsString( location );
		final String expected = "{\"locationType\":\"SOLAR_SYSTEM\",\"locationId\":30002187,\"solarSystemId\":30002187,\"solarSystemName\":\"Amarr\",\"securityClass\":\"E1\",\"securityStatus\":0.5623334,\"constellationId\":20000322,\"constellationName\":\"Throne Worlds\",\"regionId\":1000043,\"regionName\":\"Domain\"}";
		Assertions.assertEquals( expected, json );
	}

	@Test
	public void serializeSTATION() throws JsonProcessingException {
		// Given
		final GetUniverseRegionsRegionIdOk region = Mockito.mock( GetUniverseRegionsRegionIdOk.class );
		final GetUniverseConstellationsConstellationIdOk constellation =
				Mockito.mock( GetUniverseConstellationsConstellationIdOk.class );
		final GetUniverseSystemsSystemIdOk solarSystem = Mockito.mock( GetUniverseSystemsSystemIdOk.class );
		final GetUniverseStationsStationIdOk station = Mockito.mock( GetUniverseStationsStationIdOk.class );
		// When
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
		final SpaceLocationImplementation location = new SpaceLocationImplementation.Builder()
				.withRegion( region )
				.withConstellation( constellation )
				.withSolarSystem( solarSystem )
				.withStation( station )
				.build();
		final String json = this.objectMapper.writeValueAsString( location );
		final String expected = "{\"locationType\":\"STATION\",\"locationId\":60008494,\"stationId\":60008494,\"stationName\":\"Amarr VIII (Oris) - Emperor Family Academy\",\"solarSystemId\":30002187,\"solarSystemName\":\"Amarr\",\"securityClass\":\"E1\",\"securityStatus\":0.5623334,\"constellationId\":20000322,\"constellationName\":\"Throne Worlds\",\"regionId\":1000043,\"regionName\":\"Domain\"}";
		Assertions.assertEquals( expected, json );
	}

	@Test
	public void serializeSTRUCTURE() throws JsonProcessingException {
		final Integer TEST_CORPORATION_ID = 98384726;
		final String TEST_CORPORATION_NAME = "Industrias Machaque";
		final Integer TEST_CORPORATION_OWNER_ID = 98384726;
		// Given
		final GetUniverseRegionsRegionIdOk region = Mockito.mock( GetUniverseRegionsRegionIdOk.class );
		final GetUniverseConstellationsConstellationIdOk constellation =
				Mockito.mock( GetUniverseConstellationsConstellationIdOk.class );
		final GetUniverseSystemsSystemIdOk solarSystem = Mockito.mock( GetUniverseSystemsSystemIdOk.class );
		final GetUniverseStationsStationIdOk station = Mockito.mock( GetUniverseStationsStationIdOk.class );
		final Long structureId = TEST_STRUCTURE_ID;
		final GetUniverseStructuresStructureIdOk structure = Mockito.mock( GetUniverseStructuresStructureIdOk.class );
		// When
		Mockito.when( region.getRegionId() ).thenReturn( TEST_REGION_ID );
		Mockito.when( region.getName() ).thenReturn( TEST_REGION_NAME );
		Mockito.when( constellation.getConstellationId() ).thenReturn( TEST_CONSTELLATION_ID );
		Mockito.when( constellation.getName() ).thenReturn( TEST_CONSTELLATION_NAME );
		Mockito.when( solarSystem.getSystemId() ).thenReturn( TEST_SOLAR_SYSTEM_ID );
		Mockito.when( solarSystem.getName() ).thenReturn( TEST_SOLAR_SYSTEM_NAME );
		Mockito.when( solarSystem.getSecurityClass() ).thenReturn( TEST_SOLAR_SYSTEM_SECURITY_CLASS );
		Mockito.when( solarSystem.getSecurityStatus() ).thenReturn( TEST_SOLAR_SYSTEM_SECURITY_STATUS );
		Mockito.when( structure.getName() ).thenReturn( TEST_STRUCTURE_NAME );
		Mockito.when( structure.getOwnerId() ).thenReturn( TEST_CORPORATION_OWNER_ID );
		Mockito.when( structure.getTypeId() ).thenReturn( TEST_STRUCTURE_TYPE_ID );
		// Test
		final Structure structureTarget = new Structure.Builder()
				.withRegion( region )
				.withConstellation( constellation )
				.withSolarSystem( solarSystem )
				.withStructure( structureId, structure )
				.withCorporation( TEST_CORPORATION_ID, TEST_CORPORATION_NAME )
				.build();
		final String json = this.objectMapper.writeValueAsString( structureTarget );
		final String expected = "{\"locationType\":\"STRUCTURE\",\"locationId\":60008494,\"structureId\":60008494,\"structureName\":\"Amarr VIII (Oris) - Emperor Family Academy\",\"ownerId\":98384726,\"structureTypeId\":60008494,\"corporationId\":98384726,\"corporationName\":\"Industrias Machaque\",\"stationId\":60008494,\"stationName\":\"Amarr VIII (Oris) - Emperor Family Academy\",\"solarSystemId\":30002187,\"solarSystemName\":\"Amarr\",\"securityClass\":\"E1\",\"securityStatus\":0.5623334,\"constellationId\":20000322,\"constellationName\":\"Throne Worlds\",\"regionId\":1000043,\"regionName\":\"Domain\"}";
		Assertions.assertEquals( expected, json );
	}
}