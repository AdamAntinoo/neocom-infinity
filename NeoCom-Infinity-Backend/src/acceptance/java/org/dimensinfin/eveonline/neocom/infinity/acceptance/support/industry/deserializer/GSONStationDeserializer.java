package org.dimensinfin.eveonline.neocom.infinity.acceptance.support.industry.deserializer;

import java.lang.reflect.Type;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

import org.dimensinfin.eveonline.neocom.domain.space.SpaceLocationImplementation;
import org.dimensinfin.eveonline.neocom.domain.space.Station;

public class GSONStationDeserializer implements JsonDeserializer<Station> {
	@Override
	public Station deserialize( JsonElement json, Type typeOfT, JsonDeserializationContext context ) throws JsonParseException {
		final String type = "org.dimensinfin.eveonline.neocom.domain.space.SpaceLocationImplementation";
		try {
			final SpaceLocationImplementation space = context.deserialize( json, Class.forName( type ) );
			return (Station) space;
//			return new StationImplementation.Builder()
//					.withRegion( space.getRegion() )
//					.withConstellation( space.getConstellation() )
//					.withSolarSystem( space.getSolarSystem() )
//					.withStation( space.getStation() )
//					.build();
		} catch (ClassNotFoundException cnfe) {
			throw new JsonParseException( "Unknown element type: " + type, cnfe );
		}
	}
}
