package org.dimensinfin.eveonline.neocom.infinity.backend.universe.serializer;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import org.springframework.boot.jackson.JsonComponent;

import org.dimensinfin.eveonline.neocom.industry.domain.ProcessedBlueprint;

/**
 * @author Adam Antinoo (adamantinoo.git@gmail.com)
 * @since 0.20.0
 */
@JsonComponent
public class ProcessedBlueprintSerializer extends JsonSerializer<ProcessedBlueprint> {
	@Override
	public void serialize( final ProcessedBlueprint value, final JsonGenerator gen, final SerializerProvider provider )
			throws IOException {
		gen.writeStartObject();

		gen.writeNumberField( "blueprintTypeId", value.getTypeId() );
		if (null != value.getBlueprintItem()) gen.writeObjectField( "blueprint", value.getBlueprintItem() );
		if (null != value.getOutputItem()) gen.writeObjectField( "output", value.getOutputItem() );
//		if (null != value.getOutputMarketData()) gen.writeObjectField( "outputMarketData", value.getOutputMarketData() );
		if (null != value.getBom()) gen.writeObjectField( "billOfMaterials", value.getBom() );

		gen.writeEndObject();
	}
}