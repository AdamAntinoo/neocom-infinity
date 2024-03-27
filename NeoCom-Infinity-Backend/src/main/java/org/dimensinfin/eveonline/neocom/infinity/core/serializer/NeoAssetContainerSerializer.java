package org.dimensinfin.eveonline.neocom.infinity.core.serializer;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import org.springframework.boot.jackson.JsonComponent;

import org.dimensinfin.eveonline.neocom.asset.domain.NeoAssetAssetContainer;

@JsonComponent
public class NeoAssetContainerSerializer extends JsonSerializer<NeoAssetAssetContainer> {
	@Override
	public void serialize( final NeoAssetAssetContainer value, final JsonGenerator jgen, final SerializerProvider provider )
			throws IOException, JsonProcessingException {
		jgen.writeStartObject();

		jgen.writeStringField( "jsonClass", value.getJsonClass() );
		jgen.writeObjectField( "face", value.getContainerFace() );
		jgen.writeStringField( "containerType", value.getType().name() );
		jgen.writeObjectField( "contents", value.getContents() );

		jgen.writeEndObject();
	}
}
