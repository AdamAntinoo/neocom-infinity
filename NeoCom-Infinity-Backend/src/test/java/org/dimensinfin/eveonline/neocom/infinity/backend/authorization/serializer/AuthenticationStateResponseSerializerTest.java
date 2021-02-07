package org.dimensinfin.eveonline.neocom.infinity.backend.authorization.serializer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.dimensinfin.eveonline.neocom.infinity.backend.authorization.domain.AuthenticationStateResponse;

public class AuthenticationStateResponseSerializerTest {
	private ObjectMapper objectMapper;

	@BeforeEach
	public void beforeEach() {
		this.objectMapper = new ObjectMapper();
		final SimpleModule module = new SimpleModule();
		module.addSerializer( AuthenticationStateResponse.class, new AuthenticationStateResponseSerializer() );
		this.objectMapper.registerModule( module );
	}

	@Test
	public void serialize() throws JsonProcessingException {
		final AuthenticationStateResponse response = new AuthenticationStateResponse.Builder().build();
		final String json = this.objectMapper.writeValueAsString( response );
		final String expected = "{\"state\":\"NOT_VALID\",\"jwtToken\":null,\"credential\":null}";
		Assertions.assertEquals( expected, json );
	}
}