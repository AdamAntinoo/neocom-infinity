package org.dimensinfin.eveonline.neocom.infinity.acceptance.support.character.rest.deserializer;

import java.lang.reflect.Type;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import org.springframework.hateoas.Link;

public class GSONLinkDeserializer implements JsonDeserializer<Link> {

	@Override
	public Link deserialize( final JsonElement element, final Type typeOfT, final JsonDeserializationContext context ) throws JsonParseException {
		final String href = element.getAsJsonObject().get( "href" ).getAsString();
		return Link.of( href );
	}
}
