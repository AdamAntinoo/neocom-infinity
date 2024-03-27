package org.dimensinfin.eveonline.neocom.infinity.core.serializer;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import org.springframework.boot.jackson.JsonComponent;

import org.dimensinfin.eveonline.neocom.database.entities.NeoAsset;

@JsonComponent
public class NeoAssetSerializer extends JsonSerializer<NeoAsset> {
	@Override
	public void serialize( final NeoAsset value, final JsonGenerator jgen, final SerializerProvider provider )
			throws IOException, JsonProcessingException {
		jgen.writeStartObject();

		jgen.writeStringField( "jsonClass", value.getJsonClass() );
		jgen.writeObjectField( "UUID", value.getUid() );
		jgen.writeNumberField( "assetId", value.getAssetId() );
		jgen.writeNumberField( "type", value.getTypeId() );
		jgen.writeNumberField( "locationId", value.getLocationId().getSpaceIdentifier() );
		jgen.writeStringField( "name", value.getName() );
		jgen.writeNumberField( "groupId", value.getGroupId() );
		jgen.writeStringField( "groupName", value.getGroupName() );
		jgen.writeNumberField( "categoryId", value.getCategoryId() );
		jgen.writeStringField( "categoryName", value.getCategoryName() );
		jgen.writeStringField( "tech", value.getTech() );
		jgen.writeNumberField( "volume", value.getVolume() );
		jgen.writeNumberField( "price", value.getPrice() );
		jgen.writeNumberField( "quantity", value.getQuantity() );
		if (null != value.getParentContainerId()) jgen.writeNumberField( "parentContainerId", value.getParentContainerId() );

		jgen.writeEndObject();
	}
}
