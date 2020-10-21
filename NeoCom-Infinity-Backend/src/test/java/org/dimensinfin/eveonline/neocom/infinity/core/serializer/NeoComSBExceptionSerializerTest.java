package org.dimensinfin.eveonline.neocom.infinity.core.serializer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import org.dimensinfin.eveonline.neocom.infinity.core.exceptions.ErrorInfo;
import org.dimensinfin.eveonline.neocom.infinity.core.exceptions.NeoComSBException;

public class NeoComSBExceptionSerializerTest {
	private static final ObjectMapper jsonMapper = new ObjectMapper();

	@Test
	public void errorInfoSerialization() {
		final NeoComSBException value = new NeoComSBException( ErrorInfo.AUTHORIZATION_TRANSLATION );
		Assertions.assertNotNull( value );
		final int httpStatusCode = value.getHttpStatus().value();
		Assertions.assertEquals( 400, httpStatusCode );
		final String httpStatusName = value.getHttpStatus().name();
		Assertions.assertEquals( "BAD_REQUEST", httpStatusName );
		final String classLongName = value.getSourceClass();
		Assertions.assertEquals( "org.dimensinfin.eveonline.neocom.infinity.core.serializer.NeoComSBExceptionSerializerTest",
				classLongName );
		final String[] nameParts = classLongName.split( "\\." );
		final String className = nameParts[nameParts.length - 1];
		Assertions.assertEquals( "NeoComSBExceptionSerializerTest", className );
		final String sourceMethod = value.getSourceMethod();
		Assertions.assertEquals( "errorInfoSerialization", sourceMethod );
	}

	@Test
	public void errorInfoSerializationCode() throws JsonProcessingException {
		final NeoComSBException value = new NeoComSBException( ErrorInfo.AUTHORIZATION_TRANSLATION );
		String json = jsonMapper.writeValueAsString( value );
		Assertions.assertNotNull( json );
	}
}
