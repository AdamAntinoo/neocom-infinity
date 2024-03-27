package org.dimensinfin.eveonline.neocom.infinity.backend.universe.serializer;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import org.springframework.boot.jackson.JsonComponent;

import org.dimensinfin.eveonline.neocom.esiswagger.model.GetUniverseGroupsGroupIdOk;

@JsonComponent
public class GetUniverseGroupsGroupIdOkSerializer extends JsonSerializer<GetUniverseGroupsGroupIdOk> {
	@Override
	public void serialize( final GetUniverseGroupsGroupIdOk value, final JsonGenerator jgen, final SerializerProvider provider )
			throws IOException {
		jgen.writeStartObject();

		jgen.writeNumberField( "groupId", value.getGroupId() );
		jgen.writeStringField( "groupName", value.getName() );
		jgen.writeNumberField( "groupCategoryId", value.getCategoryId() );

		jgen.writeEndObject();
	}
}
