package org.dimensinfin.eveonline.neocom.infinity.acceptance.support.authorization.rest.v1.deserializer;

import java.lang.reflect.Type;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

import org.dimensinfin.eveonline.neocom.infinity.backend.authorization.domain.AuthenticationStateResponse;

public class AuthenticationStateResponseDeserializer implements JsonDeserializer<AuthenticationStateResponse> {

	@Override
	public AuthenticationStateResponse deserialize( final JsonElement element, final Type typeOfT, final JsonDeserializationContext context ) throws JsonParseException {
		final String state = element.getAsJsonObject().get( "state" ).getAsString();
		return new AuthenticationStateResponse.Builder()
				.withState( AuthenticationStateResponse.AuthenticationStateType.valueOf( state ) )
				.build();
	}
}
