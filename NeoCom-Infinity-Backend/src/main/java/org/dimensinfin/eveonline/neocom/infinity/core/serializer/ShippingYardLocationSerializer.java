package org.dimensinfin.eveonline.neocom.infinity.core.serializer;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import org.springframework.boot.jackson.JsonComponent;

import org.dimensinfin.eveonline.neocom.infinity.backend.corporation.domain.ShippingYardLocation;

@JsonComponent
public class ShippingYardLocationSerializer extends JsonSerializer<ShippingYardLocation> {
	@Override
	public void serialize( final ShippingYardLocation value, final JsonGenerator jgen, final SerializerProvider provider )
			throws IOException, JsonProcessingException {
		jgen.writeStartObject();

		jgen.writeStringField( "jsonClass", value.getJsonClass() );
		jgen.writeObjectField( "deposit", value.getDeposit() );
		jgen.writeObjectField( "officeContainer", value.getOfficeContainer() );
		jgen.writeObjectField( "station", value.getStation() );

		jgen.writeEndObject();
	}
}
