package acceptance.support.industry.deserializer;

import java.lang.reflect.Type;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

import org.dimensinfin.eveonline.neocom.infinity.backend.industry.fitting.domain.BuildAction;

public class GSONBuildActionDeserializer implements JsonDeserializer<BuildAction> {
	@Override
	public BuildAction deserialize( JsonElement json, Type typeOfT, JsonDeserializationContext context ) throws JsonParseException {
		JsonObject jsonObject = json.getAsJsonObject();
		String type = jsonObject.get( "type" ).getAsString();
		try {
			return context.deserialize( json, Class.forName( type ) );
		} catch (ClassNotFoundException cnfe) {
			throw new JsonParseException( "Unknown element type: " + type, cnfe );
		}
	}
}
