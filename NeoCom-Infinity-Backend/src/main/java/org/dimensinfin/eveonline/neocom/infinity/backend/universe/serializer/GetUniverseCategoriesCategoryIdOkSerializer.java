package org.dimensinfin.eveonline.neocom.infinity.backend.universe.serializer;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import org.springframework.boot.jackson.JsonComponent;

import org.dimensinfin.eveonline.neocom.esiswagger.model.GetUniverseCategoriesCategoryIdOk;

@JsonComponent
public class GetUniverseCategoriesCategoryIdOkSerializer extends JsonSerializer<GetUniverseCategoriesCategoryIdOk> {
	@Override
	public void serialize( final GetUniverseCategoriesCategoryIdOk value, final JsonGenerator jgen, final SerializerProvider provider )
			throws IOException {
		jgen.writeStartObject();

		jgen.writeNumberField( "categoryId", value.getCategoryId() );
		jgen.writeStringField( "categoryName", value.getName() );

		jgen.writeEndObject();
	}
}
