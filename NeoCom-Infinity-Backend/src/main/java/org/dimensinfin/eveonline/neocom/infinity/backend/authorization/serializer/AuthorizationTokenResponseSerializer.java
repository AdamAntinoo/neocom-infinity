package org.dimensinfin.eveonline.neocom.infinity.backend.authorization.serializer;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import org.springframework.boot.jackson.JsonComponent;

import org.dimensinfin.eveonline.neocom.infinity.backend.authorization.domain.AuthorizationTokenResponse;

@JsonComponent
public class AuthorizationTokenResponseSerializer extends JsonSerializer<AuthorizationTokenResponse> {
	@Override
	public void serialize( final AuthorizationTokenResponse value, final JsonGenerator jgen, final SerializerProvider provider )
			throws IOException, JsonProcessingException {
		jgen.writeStartObject();

		jgen.writeStringField( "responseType", "ValidateAuthorizationTokenResponse" );
		jgen.writeObjectField( "jwtToken", value.getJwtToken() );
		jgen.writeObjectField( "credential", value.getCredential() );

		jgen.writeEndObject();
	}
}
