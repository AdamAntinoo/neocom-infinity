package org.dimensinfin.eveonline.neocom.infinity.core.serializer;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import org.dimensinfin.eveonline.neocom.domain.space.Station;

@Deprecated
//@JsonComponent
public class StationSerializer extends JsonSerializer<Station> {
	@Override
	public void serialize( final Station value, final JsonGenerator jgen, final SerializerProvider provider )
			throws IOException, JsonProcessingException {
		jgen.writeStartObject();

		jgen.writeNumberField( "locationId", value.getLocationId() );
		jgen.writeNumberField( "regionId", value.getRegionId() );
		jgen.writeStringField( "regionName", value.getRegionName() );
		jgen.writeNumberField( "constellationId", value.getConstellationId() );
		jgen.writeStringField( "constellationName", value.getConstellationName() );
		jgen.writeNumberField( "solarSystemId", value.getSolarSystemId() );
		jgen.writeStringField( "solarSystemName", value.getSolarSystemName() );
		jgen.writeNumberField( "stationId", value.getStationId() );
		jgen.writeStringField( "stationName", value.getStationName() );

		jgen.writeEndObject();
	}
}
