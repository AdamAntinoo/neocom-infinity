package org.dimensinfin.eveonline.neocom.infinity.backend.universe.serializer;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import org.springframework.boot.jackson.JsonComponent;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;

import org.dimensinfin.eveonline.neocom.domain.Corporation;
import org.dimensinfin.eveonline.neocom.infinity.backend.character.pilot.rest.v2.PilotControllerV2;
import org.dimensinfin.eveonline.neocom.infinity.backend.universe.spacelocations.rest.v1.SpaceLocationControllerV1;

@JsonComponent
public class CorporationSerializer extends JsonSerializer<Corporation> {
	@Override
	public void serialize( final Corporation value, final JsonGenerator jgen, final SerializerProvider provider )
			throws IOException, JsonProcessingException {
		jgen.writeStartObject();

		jgen.writeStringField( "jsonClass", value.getJsonClass() );
		jgen.writeNumberField( "corporationId", value.getCorporationId() );
		jgen.writeStringField( "name", value.getCorporationPublicData().getName() );
		jgen.writeNumberField( "ceoId", value.getCeoPilotData().getPilotId() );
		final Link ceoLink = WebMvcLinkBuilder.linkTo(
				WebMvcLinkBuilder.methodOn( PilotControllerV2.class )
						.getPilotData( value.getCeoPilotData().getPilotId() )
		).withRel( "ceo" );
		jgen.writeObjectField( "ceo", ceoLink );
		jgen.writeNumberField( "creatorId", value.getCorporationPublicData().getCreatorId() );
		jgen.writeStringField( "dateFounded", value.getCorporationPublicData().getDateFounded().toString() );
		jgen.writeStringField( "description", value.getCorporationPublicData().getDescription() );
		final Link homeStationLink = WebMvcLinkBuilder.linkTo(
				WebMvcLinkBuilder.methodOn( SpaceLocationControllerV1.class )
						.getLocationById( (long) value.getCorporationPublicData().getHomeStationId() )
		).withRel( "homeStation" );
		jgen.writeObjectField( "homeStation", homeStationLink );
		jgen.writeNumberField( "memberCount", value.getCorporationPublicData().getMemberCount() );
		jgen.writeNumberField( "shares", value.getCorporationPublicData().getShares() );
		jgen.writeNumberField( "taxRate", value.getCorporationPublicData().getTaxRate() );
		jgen.writeStringField( "ticker", value.getCorporationPublicData().getTicker() );
		jgen.writeStringField( "url4Icon", value.getUrl4Icon() );
		jgen.writeNumberField( "allianceId", value.getCorporationPublicData().getAllianceId() );
		if (value.getCorporationPublicData().getAllianceId() > 0) {
			jgen.writeObjectField( "alliance", value.getAlliance() );
		}

		jgen.writeEndObject();
	}
}
