package org.dimensinfin.eveonline.neocom.infinity.serializer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import org.dimensinfin.eveonline.neocom.infinity.core.NeoComSBException;
import org.springframework.boot.jackson.JsonComponent;

import java.io.IOException;

@JsonComponent
public class NeoComSBExceptionSerializer extends JsonSerializer<NeoComSBException> {
	@Override
	public void serialize( final NeoComSBException value, final JsonGenerator jgen, final SerializerProvider provider )
			throws IOException, JsonProcessingException {
		jgen.writeStartObject();

		jgen.writeNumberField("httpStatus", value.getHttpStatus().value());
		jgen.writeStringField("httpStatusName", value.getHttpStatus().name());
		jgen.writeStringField("message", value.getMessage());
		if (null != value.getRootException()) {
			jgen.writeStringField("exceptionType", value.getRootException().getClass().getSimpleName());
			jgen.writeStringField("exceptionMessage", value.getRootException().getMessage());
		}
		final String classLongName = value.getSourceClass();
		final String[] nameParts = classLongName.split( "." );
		final String className = nameParts[nameParts.length-1];
		jgen.writeStringField("sourceClass", className);
		jgen.writeStringField("sourceMethod", value.getSourceMethod());

		jgen.writeEndObject();
	}
}
