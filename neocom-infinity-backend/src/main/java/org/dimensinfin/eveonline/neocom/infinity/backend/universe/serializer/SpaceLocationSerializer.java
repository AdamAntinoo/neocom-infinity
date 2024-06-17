package org.dimensinfin.eveonline.neocom.infinity.backend.universe.serializer;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import org.springframework.boot.jackson.JsonComponent;

import org.dimensinfin.eveonline.neocom.domain.space.SpaceConstellation;
import org.dimensinfin.eveonline.neocom.domain.space.SpaceLocationImplementation;
import org.dimensinfin.eveonline.neocom.domain.space.SpaceRegion;
import org.dimensinfin.eveonline.neocom.domain.space.SpaceSystem;
import org.dimensinfin.eveonline.neocom.domain.space.Station;
import org.dimensinfin.eveonline.neocom.domain.space.Structure;
import org.dimensinfin.eveonline.neocom.utility.LocationIdentifierType;

/**
 * @author Adam Antinoo (adamantinoo.git@gmail.com)
 * @since 0.20.0
 */
@JsonComponent
public class SpaceLocationSerializer extends JsonSerializer<SpaceLocationImplementation> {
	@Override
	public void serialize( final SpaceLocationImplementation value, final JsonGenerator jgen, final SerializerProvider serializers )
			throws IOException {
		jgen.writeStartObject();

		jgen.writeStringField( "jsonClass", value.getJsonClass() );
		final LocationIdentifierType type = value.getLocationType();
		jgen.writeStringField( "locationType", value.getLocationType().name() );
		jgen.writeNumberField( "locationId", value.getLocationId() );
		switch (type) {
			case STRUCTURE:
				jgen.writeNumberField( "structureId", ((Structure) value).getStructureId() );
				jgen.writeStringField( "structureName", ((Structure) value).getStructureName() );
				jgen.writeNumberField( "ownerId", ((Structure) value).getOwnerId() );
				jgen.writeNumberField( "structureTypeId", ((Structure) value).getStructureTypeId() );
				jgen.writeNumberField( "corporationId", ((Structure) value).getCorporationId() );
				jgen.writeStringField( "corporationName", ((Structure) value).getCorporationName() );
			case STATION:
				jgen.writeNumberField( "stationId", ((Station) value).getStationId() );
				jgen.writeStringField( "stationName", ((Station) value).getStationName() );
			case SOLAR_SYSTEM:
				jgen.writeNumberField( "solarSystemId", ((SpaceSystem) value).getSolarSystemId() );
				jgen.writeStringField( "solarSystemName", ((SpaceSystem) value).getSolarSystemName() );
				jgen.writeStringField( "securityClass", ((SpaceSystem) value).getSecurityClass() );
				jgen.writeNumberField( "securityStatus", ((SpaceSystem) value).getSecurityStatus() );
			case CONSTELLATION:
				jgen.writeNumberField( "constellationId", ((SpaceConstellation) value).getConstellationId() );
				jgen.writeStringField( "constellationName", ((SpaceConstellation) value).getConstellationName() );
			case REGION:
				jgen.writeNumberField( "regionId", ((SpaceRegion) value).getRegionId() );
				jgen.writeStringField( "regionName", ((SpaceRegion) value).getRegionName() );
		}

		jgen.writeEndObject();
	}
}