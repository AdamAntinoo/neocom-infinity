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
		gen.writeNumberField( "marketGroupId", value.getType().getMarketGroupId() );
		gen.writeNumberField( "groupId", value.getGroup().getGroupId() );
		gen.writeStringField( "groupName", value.getGroup().getName() );
		gen.writeNumberField( "categoryId", value.getCategory().getCategoryId() );
		gen.writeStringField( "categoryName", value.getCategory().getName());
		gen.writeNumberField( "capacity", value.getType().getCapacity() );
		gen.writeNumberField( "packagedVolume", value.getType().getPackagedVolume() );
		gen.writeNumberField( "volume", value.getType().getVolume() );
		gen.writeStringField( "tech", value.getTech() );
		gen.writeNumberField( "volume", value.getType().getVolume() );
		gen.writeBooleanField( "isBlueprint", value.getCategoryName().equalsIgnoreCase( GlobalWideConstants.EveGlobal.BLUEPRINT ) );

		gen.writeEndObject();
	}
}
