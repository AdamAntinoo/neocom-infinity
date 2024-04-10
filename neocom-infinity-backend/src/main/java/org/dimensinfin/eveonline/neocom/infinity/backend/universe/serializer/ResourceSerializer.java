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
	public void serialize( final Resource value, final JsonGenerator gen, final SerializerProvider provider )
			throws IOException {
		gen.writeStartObject();

		gen.writeNumberField( "typeId", value.getTypeId() );
		gen.writeStringField( "name", value.getName() );
		gen.writeNumberField( "quantity", value.getQuantity() );
		if (null != value.getGroup()) gen.writeObjectField( "group", value.getGroup() );
		if (null != value.getCategory()) gen.writeObjectField( "category", value.getCategory() );
		if (null != value.getType()) gen.writeObjectField( "type", value.getType() );
		gen.writeStringField( "tech", value.getTech() );
		gen.writeNumberField( "volume", value.getType().getVolume() );
		gen.writeBooleanField( "isBlueprint", value.getCategoryName().equalsIgnoreCase( GlobalWideConstants.EveGlobal.BLUEPRINT ) );
		gen.writeStringField( "typeIconURL", value.getTypeIconURL() );

		gen.writeEndObject();
	}
}
