package org.dimensinfin.eveonline.neocom.infinity.backend.authorization.serializer;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import org.springframework.boot.jackson.JsonComponent;

import org.dimensinfin.eveonline.neocom.infinity.backend.authorization.domain.AuthenticationStateResponse;

/**
 * @author Adam Antinoo (adamantinoo.git@gmail.com)
 * @since 0.20.0
 */
@JsonComponent
public class AuthenticationStateResponseSerializer extends JsonSerializer<AuthenticationStateResponse> {
	@Override
	public void serialize( final AuthenticationStateResponse value, final JsonGenerator jgen, final SerializerProvider serializers ) throws IOException {
		jgen.writeStartObject();

		jgen.writeStringField( "state", value.getState().name() );
		if (null != value.getJwtToken()) jgen.writeObjectField( "jwtToken", value.getJwtToken() );
		if (null != value.getEsiToken()) jgen.writeObjectField( "esiToken", value.getEsiToken() );
		if (null != value.getCredential()) jgen.writeObjectField( "credential", value.getCredential() );

		jgen.writeEndObject();
	}
}
