package org.dimensinfin.eveonline.neocom.infinity.core.serializer;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import org.springframework.boot.jackson.JsonComponent;

import org.dimensinfin.eveonline.neocom.domain.Fitting;

@JsonComponent
public class FittingSerializer extends JsonSerializer<Fitting> {
	@Override
	public void serialize( final Fitting value, final JsonGenerator jgen, final SerializerProvider provider )
			throws IOException, JsonProcessingException {
		jgen.writeStartObject();

		jgen.writeStringField( "jsonClass", value.getJsonClass() );
		jgen.writeNumberField( "fittingId", value.getFittingId() );
		jgen.writeStringField( "name", value.getName() );
		jgen.writeStringField( "description", value.getDescription() );
		jgen.writeStringField( "hullClass", value.getGroupName() );
		jgen.writeStringField( "hullGroup", value.getHullGroup() );
		jgen.writeStringField( "hullIcon", value.getURLForItem() );
		jgen.writeObjectField( "items", value.getItems() );

		jgen.writeEndObject();
	}
}
