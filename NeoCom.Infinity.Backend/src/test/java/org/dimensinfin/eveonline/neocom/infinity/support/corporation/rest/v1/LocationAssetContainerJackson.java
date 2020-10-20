package org.dimensinfin.eveonline.neocom.infinity.support.corporation.rest.v1;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

//@JsonInclude(JsonInclude.Include.NON_NULL)
//@JsonPropertyOrder({
//		"jsonClass",
//		"spaceLocationJson",
//		"containerType",
//		"neoAssetAssetContainerJsons"
//})
public class LocationAssetContainerJackson extends StdDeserializer<LocationAssetContainer> {
	public LocationAssetContainerJackson() {
		this( null );
	}

	public LocationAssetContainerJackson( Class<?> vc ) {
		super( vc );
	}

	@Override
	public LocationAssetContainer deserialize( JsonParser jp, DeserializationContext ctxt ) throws IOException, JsonProcessingException {
		JsonNode node = jp.getCodec().readTree( jp );

		String jsonClass = node.get( "jsonClass" ).asText();
		String containerType = node.get( "containerType" ).asText();
//		SpaceLocationJson spaceLocationJson = node.get( "spaceLocationJson" ).
//		int userId = (Integer) ((IntNode) node.get("createdBy")).numberValue();
//
		return new LocationAssetContainer();
	}
}
