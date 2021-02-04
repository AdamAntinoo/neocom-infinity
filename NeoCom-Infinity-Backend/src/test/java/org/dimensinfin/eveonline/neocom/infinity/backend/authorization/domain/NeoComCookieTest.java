package org.dimensinfin.eveonline.neocom.infinity.backend.authorization.domain;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;

import org.dimensinfin.logging.LogWrapper;

public class NeoComCookieTest {
	@Test
	public void toStringContract() throws JsonProcessingException {
		final NeoComCookie neoComCookie = new NeoComCookie.Builder().build();
		final ObjectMapper mapper = new ObjectMapper();
		final String jsonString = mapper.writerWithDefaultPrettyPrinter().writeValueAsString( neoComCookie );
		LogWrapper.info( jsonString );
	}
}