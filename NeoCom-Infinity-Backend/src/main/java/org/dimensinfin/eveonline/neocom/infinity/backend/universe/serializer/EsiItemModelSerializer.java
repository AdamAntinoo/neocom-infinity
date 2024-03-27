package org.dimensinfin.eveonline.neocom.infinity.backend.universe.serializer;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import org.springframework.boot.jackson.JsonComponent;

import org.dimensinfin.eveonline.neocom.core.EveGlobalConstants;
import org.dimensinfin.eveonline.neocom.infinity.backend.universe.domain.EsiItemModel;

@JsonComponent
public class EsiItemModelSerializer extends JsonSerializer<EsiItemModel> {
	@Override
	public void serialize( final EsiItemModel value, final JsonGenerator jgen, final SerializerProvider provider )
			throws IOException {
		jgen.writeStartObject();

		jgen.writeNumberField( "typeId", value.getTypeId() );
		jgen.writeStringField( "name", value.getName() );
		jgen.writeObjectField( "group", value.getGroup() );
		jgen.writeObjectField( "category", value.getCategory() );
		jgen.writeObjectField( "item", value.getItem() );
		jgen.writeStringField( "tech", "Tech I" );
		jgen.writeNumberField( "volume", value.getItem().getVolume() );
		jgen.writeBooleanField( "isBlueprint", value.getCategoryName().equalsIgnoreCase( EveGlobalConstants.Blueprint ) );
		jgen.writeStringField( "urlforItem", value.getURLForItem() );

		jgen.writeEndObject();
	}
}
