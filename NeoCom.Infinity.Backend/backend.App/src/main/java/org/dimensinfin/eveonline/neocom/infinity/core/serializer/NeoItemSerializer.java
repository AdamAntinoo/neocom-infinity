package org.dimensinfin.eveonline.neocom.infinity.core.serializer;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import org.springframework.boot.jackson.JsonComponent;

import org.dimensinfin.eveonline.neocom.domain.NeoItem;

@JsonComponent
public class NeoItemSerializer extends JsonSerializer<NeoItem> {
	@Override
	public void serialize( final NeoItem value, final JsonGenerator jgen, final SerializerProvider provider )
			throws IOException {
		jgen.writeStartObject();

		jgen.writeStringField( "jsonClass", value.getJsonClass() );
		jgen.writeNumberField( "typeId", value.getTypeId() );
		jgen.writeNumberField( "groupId", value.getGroupId() );
		jgen.writeStringField( "groupName", value.getGroupName() );
		jgen.writeNumberField( "categoryId", value.getCategoryId() );
		jgen.writeStringField( "categoryName", value.getCategoryName() );
		jgen.writeStringField( "tech", value.getTech() );
		jgen.writeNumberField( "volume", value.getVolume() );
		jgen.writeNumberField( "price", value.getPrice() );
		jgen.writeBooleanField( "isBlueprint", value.isBlueprint() );
		jgen.writeStringField( "urlforItem", value.getURLForItem() );

		jgen.writeEndObject();
	}
}
