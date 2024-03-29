package org.dimensinfin.eveonline.neocom.infinity.core.serializer;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import org.springframework.boot.jackson.JsonComponent;

import org.dimensinfin.eveonline.neocom.domain.FittingItem;

//@JsonComponent
@Deprecated
public class FittingItemSerializerDeprecated extends JsonSerializer<FittingItem> {
	@Override
	public void serialize( final FittingItem value, final JsonGenerator jgen, final SerializerProvider provider )
			throws IOException, JsonProcessingException {
		jgen.writeStartObject();

		jgen.writeStringField( "jsonClass", value.getJsonClass() );
		jgen.writeNumberField( "neoItemId", value.getTypeId() );
		jgen.writeStringField( "name", value.getTypeName() );
		jgen.writeStringField( "location", value.getFlag().toString() );

		jgen.writeEndObject();
	}
}
