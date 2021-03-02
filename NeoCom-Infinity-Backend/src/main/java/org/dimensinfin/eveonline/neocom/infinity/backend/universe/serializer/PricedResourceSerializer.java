package org.dimensinfin.eveonline.neocom.infinity.backend.universe.serializer;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import org.springframework.boot.jackson.JsonComponent;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;

import org.dimensinfin.eveonline.neocom.industry.domain.PricedResource;
import org.dimensinfin.eveonline.neocom.infinity.backend.universe.market.rest.v1.UniverseMarketControllerV1;
import org.dimensinfin.eveonline.neocom.utility.GlobalWideConstants;

/**
 * @author Adam Antinoo (adamantinoo.git@gmail.com)
 * @since 0.20.0
 */
@JsonComponent
public class PricedResourceSerializer extends JsonSerializer<PricedResource> {
	@Override
	public void serialize( final PricedResource value, final JsonGenerator jgen, final SerializerProvider provider )
			throws IOException {
		jgen.writeStartObject();

		jgen.writeNumberField( "typeId", value.getTypeId() );
		jgen.writeStringField( "name", value.getName() );
		jgen.writeNumberField( "quantity", value.getQuantity() );
		jgen.writeObjectField( "group", value.getGroup() );
		jgen.writeObjectField( "category", value.getCategory() );
		jgen.writeObjectField( "type", value.getType() );
		jgen.writeStringField( "tech", value.getTech() );
		jgen.writeNumberField( "volume", value.getType().getVolume() );
		jgen.writeBooleanField( "isBlueprint", value.getCategoryName().equalsIgnoreCase( GlobalWideConstants.EveGlobal.BLUEPRINT ) );
		jgen.writeStringField( "typeIconURL", value.getTypeIconURL() );
		jgen.writeNumberField( "price", value.getMarketPrice() );

		// Additional HAL fields for market data.
		final Link marketLink = WebMvcLinkBuilder.linkTo(
				WebMvcLinkBuilder.methodOn( UniverseMarketControllerV1.class )
						.getMarketConsolidatedByRegion4ItemId( value.getMarketData().getSellRegionId(), value.getTypeId() )
		).withRel( "marketData" );
		jgen.writeObjectField( "marketData", marketLink );

		jgen.writeEndObject();
	}
}