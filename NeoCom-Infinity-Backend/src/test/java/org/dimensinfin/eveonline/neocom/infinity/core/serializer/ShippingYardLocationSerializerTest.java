package org.dimensinfin.eveonline.neocom.infinity.core.serializer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import org.dimensinfin.eveonline.neocom.database.entities.NeoAsset;
import org.dimensinfin.eveonline.neocom.domain.space.Station;
import org.dimensinfin.eveonline.neocom.domain.space.StationImplementation;
import org.dimensinfin.eveonline.neocom.esiswagger.model.GetUniverseConstellationsConstellationIdOk;
import org.dimensinfin.eveonline.neocom.esiswagger.model.GetUniverseRegionsRegionIdOk;
import org.dimensinfin.eveonline.neocom.esiswagger.model.GetUniverseStationsStationIdOk;
import org.dimensinfin.eveonline.neocom.esiswagger.model.GetUniverseSystemsSystemIdOk;
import org.dimensinfin.eveonline.neocom.infinity.backend.corporation.domain.ShippingYardLocation;
import org.dimensinfin.eveonline.neocom.infinity.support.NeoAssetMockSerializer;
import org.dimensinfin.eveonline.neocom.infinity.support.StationImplementationMockSerializer;

class ShippingYardLocationSerializerTest {
	private ObjectMapper objectMapper;

	@BeforeEach
	public void beforeEach() {
		this.objectMapper = new ObjectMapper();
		final SimpleModule module = new SimpleModule();
		module.addSerializer( ShippingYardLocation.class, new ShippingYardLocationSerializer() );
		module.addSerializer( NeoAsset.class, new NeoAssetMockSerializer() );
		module.addSerializer( StationImplementation.class, new StationImplementationMockSerializer() );
		this.objectMapper.registerModule( module );
	}

	@Test
	public void serialize() throws JsonProcessingException {
		final NeoAsset deposit = new NeoAsset();
		final NeoAsset office = new NeoAsset();
		final GetUniverseRegionsRegionIdOk region = Mockito.mock( GetUniverseRegionsRegionIdOk.class );
		final GetUniverseConstellationsConstellationIdOk constellation = Mockito.mock( GetUniverseConstellationsConstellationIdOk.class );
		final GetUniverseSystemsSystemIdOk solarSystem = Mockito.mock( GetUniverseSystemsSystemIdOk.class );
		final GetUniverseStationsStationIdOk stationOk = Mockito.mock( GetUniverseStationsStationIdOk.class );
		final Station station = new StationImplementation.Builder()
				.withRegion( region )
				.withConstellation( constellation )
				.withSolarSystem( solarSystem )
				.withStation( stationOk )
				.build();
		final ShippingYardLocation yard = new ShippingYardLocation.Builder()
				.withYardDeposit( deposit )
				.withOfficeContainer( office )
				.withStation( station )
				.build();
		final String expected = "{\"jsonClass\":\"ShippingYardLocation\",\"deposit\":{\"jsonClass\":\"NeoAsset\"},\"officeContainer\":{\"jsonClass\":\"NeoAsset\"},\"station\":{\"jsonClass\":\"StationImplementation\"}}";

		final String obtained = this.objectMapper.writeValueAsString( yard );
		Assertions.assertEquals( expected, obtained );
	}
}
