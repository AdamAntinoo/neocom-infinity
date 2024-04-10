package org.dimensinfin.eveonline.neocom.infinity.core.serializer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.dimensinfin.eveonline.neocom.database.entities.Credential;

public class CredentialSerializerTest {
	private ObjectMapper objectMapper;

	@BeforeEach
	public void beforeEach() {
		this.objectMapper = new ObjectMapper();
		final SimpleModule module = new SimpleModule();
		module.addSerializer( Credential.class, new CredentialSerializer() );
		this.objectMapper.registerModule( module );
	}

	@Test
	public void serialize() throws JsonProcessingException {
		final Credential user = new Credential();
		final String json = this.objectMapper.writeValueAsString( user );
		final String expected = "{\"jsonClass\":\"Credential\",\"uniqueCredential\":\"tranquility.-2\",\"accountId\":-2,\"accountName\":null,\"dataSource\":\"tranquility\",\"corporationId\":-3,\"assetsCount\":0,\"walletBalance\":0.0,\"miningResourcesEstimatedValue\":0.0,\"raceName\":null}";
		Assertions.assertEquals( expected, json );
	}
}
