package org.dimensinfin.eveonline.neocom.infinity.core.serializer;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import org.springframework.boot.jackson.JsonComponent;

import org.dimensinfin.eveonline.neocom.infinity.backend.universe.domain.ServerStatus;

@JsonComponent
public class ServerStatusSerializer extends JsonSerializer<ServerStatus> {
	@Override
	public void serialize( final ServerStatus value, final JsonGenerator jgen, final SerializerProvider provider )
			throws IOException, JsonProcessingException {
		jgen.writeStartObject();

		jgen.writeStringField( "server", value.getServer() );
		jgen.writeNumberField( "players", value.getStatus().getPlayers() );
		jgen.writeStringField( "backendVersion", value.getBackendVersion() );
		jgen.writeStringField( "SDEVersion", value.getSDEVersion() );
		jgen.writeObjectField( "start_time", value.getStatus().getStartTime() );
		jgen.writeStringField( "startAgo", value.getStartAgo() );
		jgen.writeStringField( "nextDowntime", value.getTimeToNextDowntime() );

		jgen.writeEndObject();
	}
}
