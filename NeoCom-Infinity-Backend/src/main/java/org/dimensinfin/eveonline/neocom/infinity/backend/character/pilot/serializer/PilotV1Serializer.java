package org.dimensinfin.eveonline.neocom.infinity.backend.character.pilot.serializer;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import org.springframework.boot.jackson.JsonComponent;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;

import org.dimensinfin.eveonline.neocom.character.domain.PilotV1;
import org.dimensinfin.eveonline.neocom.infinity.backend.universe.corporation.rest.v1.PublicCorporationControllerV1;

/**
 * @author Adam Antinoo (adamantinoo.git@gmail.com)
 * @since 0.20.0
 */
@JsonComponent
public class PilotV1Serializer extends JsonSerializer<PilotV1> {
	@Override
	public void serialize( final PilotV1 value, final JsonGenerator jgen, final SerializerProvider provider )
			throws IOException {
		jgen.writeStartObject();

		//		jgen.writeStringField( "jsonClass", value.getJsonClass() );
		jgen.writeNumberField( "pilotId", value.getPilotId() );
		jgen.writeStringField( "name", value.getPilotPublicData().getName() );
		jgen.writeStringField( "description", value.getPilotPublicData().getDescription() );
		final Link corporationLink = WebMvcLinkBuilder.linkTo(
				WebMvcLinkBuilder.methodOn( PublicCorporationControllerV1.class )
						.getCorporationData( value.getPilotPublicData().getCorporationId() )
		).withRel( "corporation" );
		jgen.writeObjectField( "corporation", corporationLink );
		jgen.writeStringField( "birthday", value.getPilotPublicData().getBirthday().toString() );
		jgen.writeStringField( "gender", value.getPilotPublicData().getGender().toString() );
		jgen.writeNumberField( "securityStatus", value.getPilotPublicData().getSecurityStatus() );
		jgen.writeStringField( "url4Icon", value.getUrl4Icon() );
		jgen.writeObjectField( "raceData", value.getRaceData() );
		jgen.writeObjectField( "ancestryData", value.getAncestryData() );
		jgen.writeObjectField( "bloodlineData", value.getBloodlineData() );

		jgen.writeNumberField( "totalSkillpoints", value.getTotalSkillpoints() );
		jgen.writeNumberField( "walletBalance", value.getWalletBalance() );
		jgen.writeStringField( "currentShipName", value.getCurrentShip().getShipName() );
		jgen.writeStringField( "currentShipTypeName", value.getCurrentShipType().getName() );
		jgen.writeObjectField( "lastKnownLocation", value.getLastKnownLocation() );

		jgen.writeEndObject();
	}
}
