package org.dimensinfin.eveonline.neocom.infinity.backend.industry.fitting.serializer;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import org.springframework.boot.jackson.JsonComponent;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;

import org.dimensinfin.eveonline.neocom.domain.FittingItem;
import org.dimensinfin.eveonline.neocom.infinity.backend.universe.item.rest.v2.EsiItemControllerV2;

@JsonComponent
public class FittingItemSerializer extends JsonSerializer<FittingItem> {
	@Override
	public void serialize( final FittingItem value, final JsonGenerator jgen, final SerializerProvider provider )
			throws IOException {
		jgen.writeStartObject();

		jgen.writeNumberField( "typeId", value.getTypeId() );
		jgen.writeStringField( "name", value.getTypeName() );
		jgen.writeStringField( "location", value.getFlag().toString().toUpperCase() );
		jgen.writeObjectField( "item", WebMvcLinkBuilder.linkTo(
				WebMvcLinkBuilder.methodOn( EsiItemControllerV2.class ).getItem( value.getTypeId() )
		).withRel( "item" ) );

		jgen.writeEndObject();
	}
}
