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
	public void serialize( final PricedResource value, final JsonGenerator gen, final SerializerProvider provider )
			throws IOException {
		gen.writeStartObject();

		gen.writeNumberField( "typeId", value.getTypeId() );
		gen.writeStringField( "name", value.getName() );
		gen.writeNumberField( "quantity", value.getQuantity() );
		if (null != value.getGroup()) gen.writeObjectField( "group", value.getGroup() );
		if (null != value.getCategory()) gen.writeObjectField( "category", value.getCategory() );
		if (null != value.getType()) gen.writeObjectField( "type", value.getType() );
		gen.writeStringField( "tech", value.getTech() );
		gen.writeNumberField( "volume", value.getType().getVolume() );
		gen.writeBooleanField( "isBlueprint", value.getCategoryName().equalsIgnoreCase( GlobalWideConstants.EveGlobal.BLUEPRINT ) );
		gen.writeStringField( "typeIconURL", value.getTypeIconURL() );
		gen.writeNumberField( "price", value.getMarketPrice() );

		// Additional HAL fields for market data.
		final Link marketLink = WebMvcLinkBuilder.linkTo(
				WebMvcLinkBuilder.methodOn( UniverseMarketControllerV1.class )
						.getMarketConsolidatedByRegion4ItemId( value.getMarketData().getSellRegionId(), value.getTypeId() )
		).withRel( "marketData" );
		gen.writeObjectField( "marketData", marketLink );

		gen.writeEndObject();
	}
}