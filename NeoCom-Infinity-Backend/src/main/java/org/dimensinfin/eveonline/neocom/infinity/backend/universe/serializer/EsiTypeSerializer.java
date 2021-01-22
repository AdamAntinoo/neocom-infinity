package org.dimensinfin.eveonline.neocom.infinity.backend.universe.serializer;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import org.springframework.boot.jackson.JsonComponent;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;

import org.dimensinfin.eveonline.neocom.domain.EsiType;
import org.dimensinfin.eveonline.neocom.infinity.backend.universe.market.rest.v1.UniverseMarketControllerV1;
import org.dimensinfin.eveonline.neocom.utility.GlobalWideConstants;

@JsonComponent
public class EsiTypeSerializer extends JsonSerializer<EsiType> {
	@Override
	public void serialize( final EsiType value, final JsonGenerator jgen, final SerializerProvider provider )
			throws IOException {
		jgen.writeStartObject();

		jgen.writeStringField( "jsonClass", value.getJsonClass() );
		jgen.writeNumberField( "typeId", value.getTypeId() );
		jgen.writeStringField( "name", value.getName() );
		jgen.writeObjectField( "group", value.getGroup() );
		jgen.writeObjectField( "category", value.getCategory() );
		jgen.writeObjectField( "type", value.getType() );
		jgen.writeStringField( "tech", value.getTech() );
		jgen.writeNumberField( "volume", value.getType().getVolume() );
		jgen.writeBooleanField( "isBlueprint", value.getCategoryName().equalsIgnoreCase( GlobalWideConstants.EveGlobal.BLUEPRINT ) );
		jgen.writeStringField( "typeIconURL", value.getTypeIconURL() );

		// Additional HAL fields for market data.
		final Integer regionId = 10000043;
		final Link marketLink = WebMvcLinkBuilder.linkTo(
				WebMvcLinkBuilder.methodOn( UniverseMarketControllerV1.class )
						.getMarketConsolidatedByRegion4ItemId( regionId, value.getTypeId() )
		).withRel( "marketData" );
		jgen.writeObjectField( "marketData", marketLink );

		jgen.writeEndObject();
	}
}
