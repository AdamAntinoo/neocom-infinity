package org.dimensinfin.eveonline.neocom.infinity.backend.universe.serializer;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;

import org.dimensinfin.eveonline.neocom.infinity.backend.universe.corporation.rest.v1.UniverseCorporationControllerV1;
import org.dimensinfin.eveonline.neocom.infinity.backend.universe.domain.PilotPublicDataV1;

/**
 * @author Adam Antinoo (adamantinoo.git@gmail.com)
 * @since 0.20.0
 */
public class PilotPublicDataV1Serializer extends JsonSerializer<PilotPublicDataV1> {
	@Override
	public void serialize( final PilotPublicDataV1 value, final JsonGenerator jgen, final SerializerProvider provider )
			throws IOException {
		jgen.writeStartObject();

		jgen.writeStringField( "jsonClass", value.getJsonClass() );
		jgen.writeNumberField( "pilotId", value.getPilotId() );
		jgen.writeStringField( "name", value.getPilotPublicData().getName() );
		jgen.writeStringField( "description", value.getPilotPublicData().getDescription() );
		final Link corporationLink = WebMvcLinkBuilder.linkTo(
				WebMvcLinkBuilder.methodOn( UniverseCorporationControllerV1.class )
						.getCorporationData( value.getPilotPublicData().getCorporationId() )
		).withRel( "corporation" );
		jgen.writeObjectField( "corporation", corporationLink );
		jgen.writeStringField( "birthday", value.getPilotPublicData().getBirthday().toString() );
		jgen.writeStringField( "gender", value.getPilotPublicData().getGender().toString() );
		jgen.writeNumberField( "securityStatus", value.getPilotPublicData().getSecurityStatus() );
		jgen.writeStringField( "url4Icon", value.getUrl4Icon() );
		//		final Link lastKnownLocationLink = WebMvcLinkBuilder.linkTo(
		//				WebMvcLinkBuilder.methodOn( SpaceLocationControllerV1.class )
		//						.getLocationById( value.get )
		//		).withRel( "lastKnownLocation" );
		//		jgen.writeObjectField( "lastKnownLocation", lastKnownLocationLink );
		jgen.writeObjectField( "raceData", value.getRaceData() );
		jgen.writeObjectField( "ancestryData", value.getAncestryData() );
		jgen.writeObjectField( "bloodlineData", value.getBloodlineData() );

		jgen.writeEndObject();
	}
}
