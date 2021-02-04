package org.dimensinfin.eveonline.neocom.infinity.backend.authorization.serializer;

import java.lang.reflect.Type;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

import org.dimensinfin.eveonline.neocom.infinity.backend.authorization.domain.NeoComCookie;

/**
 * @author Adam Antinoo (adamantinoo.git@gmail.com)
 * @since 0.20.0
 */
public class NeoComCookieDeserializer implements JsonDeserializer<NeoComCookie> {
	@Override
	public NeoComCookie deserialize( final JsonElement json, final Type typeOfT, final JsonDeserializationContext context ) throws JsonParseException {
		final JsonObject cookie = json.getAsJsonObject();
		final String jwtToken = cookie.get( "jwtToken" ).getAsString();
		if (jwtToken.toUpperCase().contains( "INVALID" ))
			return new NeoComCookie.Builder()
					.build();
		else
			return new NeoComCookie.Builder()
					.withJWTToken( jwtToken )
					.build();
	}
}