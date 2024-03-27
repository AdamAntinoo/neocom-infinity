package org.dimensinfin.eveonline.neocom.infinity.acceptance.support.industry.deserializer;

import java.lang.reflect.Type;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

import org.dimensinfin.eveonline.neocom.domain.space.Station;

public class GSONStationDeserializer implements JsonDeserializer<Station> {
	@Override
	public Station deserialize( JsonElement json, Type typeOfT, JsonDeserializationContext context ) throws JsonParseException {
		final String type = "org.dimensinfin.eveonline.neocom.domain.space.StationImplementation";
		try {
			return context.deserialize( json, Class.forName( type ) );
		} catch (ClassNotFoundException cnfe) {
			throw new JsonParseException( "Unknown element type: " + type, cnfe );
		}
	}
}
