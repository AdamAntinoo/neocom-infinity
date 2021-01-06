package org.dimensinfin.eveonline.neocom.infinity.backend.universe.serializer;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import org.springframework.boot.jackson.JsonComponent;

import org.dimensinfin.eveonline.neocom.esiswagger.model.GetUniverseTypesTypeIdOk;

@JsonComponent
public class GetUniverseTypesTypeIdOkSerializer extends JsonSerializer<GetUniverseTypesTypeIdOk> {
	@Override
	public void serialize( final GetUniverseTypesTypeIdOk value, final JsonGenerator jgen, final SerializerProvider provider )
			throws IOException {
		jgen.writeStartObject();

		jgen.writeNumberField( "typeId", value.getTypeId() );
		jgen.writeStringField( "name", value.getName() );
		jgen.writeStringField( "description", value.getDescription() );
		jgen.writeNumberField( "groupId", value.getGroupId() );
		jgen.writeNumberField( "marketGroupId", value.getMarketGroupId() );
		jgen.writeNumberField( "capacity", value.getCapacity() );
		jgen.writeNumberField( "mass", value.getMass() );
		jgen.writeNumberField( "packagedVolume", value.getPackagedVolume() );
		jgen.writeNumberField( "volume", value.getVolume() );

		jgen.writeEndObject();
	}
}
