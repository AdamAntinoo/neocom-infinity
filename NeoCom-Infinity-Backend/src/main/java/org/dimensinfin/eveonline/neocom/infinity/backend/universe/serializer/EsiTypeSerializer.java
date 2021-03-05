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
import org.dimensinfin.eveonline.neocom.market.service.MarketService;
import org.dimensinfin.eveonline.neocom.utility.GlobalWideConstants;

@JsonComponent
public class EsiTypeSerializer extends JsonSerializer<EsiType> {
	@Override
	public void serialize( final EsiType value, final JsonGenerator gen, final SerializerProvider provider )
			throws IOException {
		gen.writeStartObject();

		gen.writeNumberField( "typeId", value.getTypeId() );
		gen.writeStringField( "name", value.getName() );
		gen.writeStringField( "description", value.getType().getDescription() );
		if (null != value.getGroup()) gen.writeObjectField( "group", value.getGroup() );
		if (null != value.getCategory()) gen.writeObjectField( "category", value.getCategory() );
		if (null != value.getType()) gen.writeObjectField( "type", value.getType() );
		gen.writeStringField( "tech", value.getTech() );
		gen.writeNumberField( "volume", value.getType().getVolume() );
		gen.writeBooleanField( "isBlueprint", value.getCategoryName().equalsIgnoreCase( GlobalWideConstants.EveGlobal.BLUEPRINT ) );
		gen.writeStringField( "typeIconURL", value.getTypeIconURL() );

		// Additional HAL fields for market data.
		final Link marketLink = WebMvcLinkBuilder.linkTo(
				WebMvcLinkBuilder.methodOn( UniverseMarketControllerV1.class )
						.getMarketConsolidatedByRegion4ItemId( MarketService.PREDEFINED_MARKET_REGION_ID, value.getTypeId() )
		).withRel( "marketData" );
		gen.writeObjectField( "marketData", marketLink );

		gen.writeEndObject();
	}
}
