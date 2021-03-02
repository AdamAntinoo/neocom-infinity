package org.dimensinfin.eveonline.neocom.infinity.backend.universe.serializer;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import org.springframework.boot.jackson.JsonComponent;

import org.dimensinfin.eveonline.neocom.infinity.backend.industry.domain.ProcessedBlueprint;

/**
 * @author Adam Antinoo (adamantinoo.git@gmail.com)
 * @since 0.20.0
 */
@JsonComponent
public class ProcessedBlueprintSerializer extends JsonSerializer<ProcessedBlueprint> {
	@Override
	public void serialize( final ProcessedBlueprint value, final JsonGenerator jgen, final SerializerProvider provider )
			throws IOException {
		jgen.writeStartObject();

		jgen.writeNumberField( "blueprintTypeId", value.getBlueprintTypeId() );
		jgen.writeObjectField( "blueprint", value.getBlueprint() );
		jgen.writeObjectField( "output", value.getOutput() );
		jgen.writeObjectField( "outputMarketData", value.getOutputMarketData() );
		jgen.writeObjectField( "billOfMaterials", value.getBom() );

		jgen.writeEndObject();
	}
}