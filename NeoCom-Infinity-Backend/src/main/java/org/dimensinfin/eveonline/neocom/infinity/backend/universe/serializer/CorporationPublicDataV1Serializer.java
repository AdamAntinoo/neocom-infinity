package org.dimensinfin.eveonline.neocom.infinity.backend.universe.serializer;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import org.springframework.boot.jackson.JsonComponent;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;

import org.dimensinfin.eveonline.neocom.domain.PublicCorporationV1;
import org.dimensinfin.eveonline.neocom.infinity.backend.universe.character.rest.v1.PublicCharacterControllerV1;
import org.dimensinfin.eveonline.neocom.infinity.backend.universe.spacelocations.rest.v1.SpaceLocationControllerV1;

/**
 * @author Adam Antinoo (adamantinoo.git@gmail.com)
 * @since 0.20.0
 */
@JsonComponent
public class CorporationPublicDataV1Serializer extends JsonSerializer<PublicCorporationV1> {
	@Override
	public void serialize( final PublicCorporationV1 value, final JsonGenerator jgen, final SerializerProvider provider )
			throws IOException {
		jgen.writeStartObject();

		jgen.writeStringField( "jsonClass", value.getJsonClass() );
		jgen.writeNumberField( "corporationId", value.getCorporationId() );
		jgen.writeStringField( "name", value.getCorporationPublicData().getName() );
		final Link ceoLink = WebMvcLinkBuilder.linkTo(
				WebMvcLinkBuilder.methodOn( PublicCharacterControllerV1.class )
						.getPilotPublicData( value.getCeoPilotData().getPilotId() )
		).withRel( "ceo" );
		jgen.writeObjectField( "ceo", ceoLink );
		jgen.writeNumberField( "creatorId", value.getCorporationPublicData().getCreatorId() );
		if (null != value.getCorporationPublicData().getFactionId())
			jgen.writeNumberField( "factionId", value.getCorporationPublicData().getFactionId() );
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
		if (null != value.getCorporationPublicData().getAllianceId()) {
			jgen.writeNumberField( "allianceId", value.getCorporationPublicData().getAllianceId() );
			jgen.writeObjectField( "alliance", value.getAlliance() );
		}

		jgen.writeEndObject();
	}
}