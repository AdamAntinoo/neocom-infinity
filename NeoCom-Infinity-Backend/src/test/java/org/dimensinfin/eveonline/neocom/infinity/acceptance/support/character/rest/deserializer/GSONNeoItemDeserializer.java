package org.dimensinfin.eveonline.neocom.infinity.acceptance.support.character.rest.deserializer;

import java.lang.reflect.Type;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

import org.dimensinfin.eveonline.neocom.domain.NeoItem;

public class GSONNeoItemDeserializer implements JsonDeserializer<NeoItem> {

	@Override
	public NeoItem deserialize( final JsonElement element, final Type typeOfT, final JsonDeserializationContext context ) throws JsonParseException {
		return new NeoItem( element.getAsJsonObject().get( "typeId" ).getAsInt() );
	}
}
