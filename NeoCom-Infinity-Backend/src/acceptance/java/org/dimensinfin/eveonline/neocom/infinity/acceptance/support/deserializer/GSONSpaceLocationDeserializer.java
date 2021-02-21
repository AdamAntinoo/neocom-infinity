package org.dimensinfin.eveonline.neocom.infinity.acceptance.support.deserializer;

import java.lang.reflect.Type;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

import org.dimensinfin.eveonline.neocom.domain.space.SpaceLocation;
import org.dimensinfin.eveonline.neocom.domain.space.SpaceLocationImplementation;
import org.dimensinfin.eveonline.neocom.esiswagger.model.GetUniverseConstellationsConstellationIdOk;
import org.dimensinfin.eveonline.neocom.esiswagger.model.GetUniverseRegionsRegionIdOk;
import org.dimensinfin.eveonline.neocom.esiswagger.model.GetUniverseStationsStationIdOk;
import org.dimensinfin.eveonline.neocom.esiswagger.model.GetUniverseSystemsSystemIdOk;
import org.dimensinfin.eveonline.neocom.utility.LocationIdentifierType;

public class GSONSpaceLocationDeserializer implements JsonDeserializer<SpaceLocation> {
	@Override
	public SpaceLocation deserialize( final JsonElement json, final Type typeOfT, final JsonDeserializationContext context ) throws JsonParseException {
		final JsonObject jsonObject = json.getAsJsonObject();
		final LocationIdentifierType locationType = LocationIdentifierType.valueOf( jsonObject.get( "locationType" ).getAsString() );
		final GetUniverseRegionsRegionIdOk region = new GetUniverseRegionsRegionIdOk();
		region.setRegionId( jsonObject.get( "regionId" ).getAsInt() );
		region.setName( jsonObject.get( "regionName" ).getAsString() );
		final GetUniverseConstellationsConstellationIdOk constellation = new GetUniverseConstellationsConstellationIdOk();
		constellation.setConstellationId( jsonObject.get( "constellationId" ).getAsInt() );
		constellation.setName( jsonObject.get( "constellationName" ).getAsString() );
		switch (locationType) {
			case REGION:
			case CONSTELLATION:
			case SOLAR_SYSTEM:
				final GetUniverseSystemsSystemIdOk solarSystem = new GetUniverseSystemsSystemIdOk();
				solarSystem.setSystemId( jsonObject.get( "solarSystemId" ).getAsInt() );
				solarSystem.setName( jsonObject.get( "solarSystemName" ).getAsString() );
				solarSystem.setSecurityClass( jsonObject.get( "securityClass" ).getAsString() );
				solarSystem.setSecurityStatus( jsonObject.get( "securityStatus" ).getAsFloat() );
				return new SpaceLocationImplementation.Builder()
						.withRegion( region )
						.withConstellation( constellation )
						.withSolarSystem( solarSystem )
						.build();
			case STATION:
				final GetUniverseSystemsSystemIdOk solarSystemStation = new GetUniverseSystemsSystemIdOk();
				solarSystemStation.setSystemId( jsonObject.get( "solarSystemId" ).getAsInt() );
				solarSystemStation.setName( jsonObject.get( "solarSystemName" ).getAsString() );
				solarSystemStation.setSecurityClass( jsonObject.get( "securityClass" ).getAsString() );
				solarSystemStation.setSecurityStatus( jsonObject.get( "securityStatus" ).getAsFloat() );
				final GetUniverseStationsStationIdOk station = new GetUniverseStationsStationIdOk();
				solarSystemStation.setSystemId( jsonObject.get( "stationId" ).getAsInt() );
				solarSystemStation.setName( jsonObject.get( "stationName" ).getAsString() );
				return new SpaceLocationImplementation.Builder()
						.withRegion( region )
						.withConstellation( constellation )
						.withSolarSystem( solarSystemStation )
						.withStation( station )
						.build();
		}
		return null;
	}
}