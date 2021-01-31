package org.dimensinfin.eveonline.neocom.infinity.backend.universe.serializer;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import org.springframework.boot.jackson.JsonComponent;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;

import org.dimensinfin.eveonline.neocom.infinity.backend.universe.market.rest.v1.UniverseMarketControllerV1;
import org.dimensinfin.eveonline.neocom.loyalty.persistence.LoyaltyOfferEntity;

/**
 * @author Adam Antinoo (adamantinoo.git@gmail.com)
 * @since 0.20.0
 */
@JsonComponent
public class LoyaltyOfferEntitySerializer extends JsonSerializer<LoyaltyOfferEntity> {

	@Override
	public void serialize( final LoyaltyOfferEntity value, final JsonGenerator jgen, final SerializerProvider serializers ) throws IOException {
		jgen.writeStartObject();

		jgen.writeStringField( "jsonClass", value.getJsonClass() );
		jgen.writeNumberField( "offerId", value.getOfferId() );
		jgen.writeNumberField( "typeId", value.getTypeId() );
		jgen.writeStringField( "typeName", value.getTypeName() );
		jgen.writeNumberField( "corporationId", value.getCorporationId() );
		jgen.writeStringField( "corporationName", value.getCorporationName() );
		jgen.writeNumberField( "lpValue", value.getLpValue() );
		jgen.writeNumberField( "iskCost", value.getIskCost() );
		jgen.writeNumberField( "lpCost", value.getLpCost() );
		jgen.writeNumberField( "quantity", value.getQuantity() );
		jgen.writeNumberField( "marketRegionId", value.getMarketRegionId() );
		jgen.writeNumberField( "price", value.getPrice() );
		// Additional HAL fields for market data.
		final Link marketLink = WebMvcLinkBuilder.linkTo(
				WebMvcLinkBuilder.methodOn( UniverseMarketControllerV1.class )
						.getMarketConsolidatedByRegion4ItemId( value.getMarketRegionId(), value.getTypeId() )
		).withRel( "marketData" );
		jgen.writeObjectField( "marketData", marketLink );

		jgen.writeEndObject();

	}
}