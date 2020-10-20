package org.dimensinfin.eveonline.neocom.infinity.support;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import org.springframework.boot.jackson.JsonComponent;

import org.dimensinfin.eveonline.neocom.domain.space.StationImplementation;

@JsonComponent
public class StationImplementationMockSerializer extends JsonSerializer<StationImplementation> {
	@Override
	public void serialize( final StationImplementation value, final JsonGenerator jgen, final SerializerProvider provider )
			throws IOException, JsonProcessingException {
		jgen.writeStartObject();

		jgen.writeStringField( "jsonClass", value.getJsonClass() );

		jgen.writeEndObject();
	}
}
