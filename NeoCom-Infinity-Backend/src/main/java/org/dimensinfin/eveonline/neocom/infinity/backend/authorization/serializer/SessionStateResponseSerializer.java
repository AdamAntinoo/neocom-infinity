package org.dimensinfin.eveonline.neocom.infinity.backend.authorization.serializer;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import org.dimensinfin.eveonline.neocom.infinity.backend.authorization.domain.CookieStateResponse;

/**
 * @author Adam Antinoo (adamantinoo.git@gmail.com)
 * @since 0.20.0
 */
public class SessionStateResponseSerializer extends JsonSerializer<CookieStateResponse> {
	@Override
	public void serialize( final CookieStateResponse value, final JsonGenerator jgen, final SerializerProvider serializers ) throws IOException {
		jgen.writeStartObject();
		jgen.writeStringField( "state", value.getState().getMessage() );
		jgen.writeEndObject();
	}
}