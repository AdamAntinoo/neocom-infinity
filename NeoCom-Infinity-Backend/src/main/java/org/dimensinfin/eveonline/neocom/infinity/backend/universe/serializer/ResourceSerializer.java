package org.dimensinfin.eveonline.neocom.infinity.backend.universe.serializer;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import org.springframework.boot.jackson.JsonComponent;

import org.dimensinfin.eveonline.neocom.industry.domain.Resource;
import org.dimensinfin.eveonline.neocom.utility.GlobalWideConstants;

@JsonComponent
public class ResourceSerializer extends JsonSerializer<Resource> {
	@Override
	public void serialize( final Resource value, final JsonGenerator jgen, final SerializerProvider provider )
			throws IOException {
		jgen.writeStartObject();

		jgen.writeNumberField( "typeId", value.getTypeId() );
		jgen.writeStringField( "name", value.getName() );
		jgen.writeNumberField( "quantity", value.getQuantity() );
		jgen.writeObjectField( "group", value.getGroup() );
		jgen.writeObjectField( "category", value.getCategory() );
		jgen.writeObjectField( "type", value.getType() );
		jgen.writeStringField( "tech", value.getTech() );
		jgen.writeNumberField( "volume", value.getType().getVolume() );
		jgen.writeBooleanField( "isBlueprint", value.getCategoryName().equalsIgnoreCase( GlobalWideConstants.EveGlobal.BLUEPRINT ) );
		jgen.writeStringField( "typeIconURL", value.getTypeIconURL() );

		jgen.writeEndObject();
	}
}
