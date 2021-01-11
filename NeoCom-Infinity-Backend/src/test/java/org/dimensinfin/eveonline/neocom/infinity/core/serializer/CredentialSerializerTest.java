package org.dimensinfin.eveonline.neocom.infinity.core.serializer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.springframework.beans.factory.annotation.Autowired;

import org.dimensinfin.eveonline.neocom.database.entities.Credential;

//@JsonTest
//@RunWith(SpringRunner.class)
public class CredentialSerializerTest {

	@Autowired
	private ObjectMapper objectMapper;

	//	@Test
	public void testSerialization() throws JsonProcessingException {
		final Credential user = new Credential();
		final String json = this.objectMapper.writeValueAsString( user );

		Assertions.assertEquals( "{\"favoriteColor\":\"#f0f8ff\"}", json );
	}
}
