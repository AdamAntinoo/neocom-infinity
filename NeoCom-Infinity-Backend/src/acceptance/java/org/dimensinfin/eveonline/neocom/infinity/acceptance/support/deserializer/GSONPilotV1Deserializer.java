package org.dimensinfin.eveonline.neocom.infinity.acceptance.support.deserializer;

import java.lang.reflect.Type;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import org.joda.time.DateTime;

import org.dimensinfin.eveonline.neocom.character.domain.PilotV1;
import org.dimensinfin.eveonline.neocom.domain.space.SpaceLocation;
import org.dimensinfin.eveonline.neocom.esiswagger.model.GetCharactersCharacterIdOk;
import org.dimensinfin.eveonline.neocom.esiswagger.model.GetUniverseAncestries200Ok;
import org.dimensinfin.eveonline.neocom.esiswagger.model.GetUniverseBloodlines200Ok;
import org.dimensinfin.eveonline.neocom.esiswagger.model.GetUniverseRaces200Ok;

/**
 * @author Adam Antinoo (adamantinoo.git@gmail.com)
 * @since 0.20.0
 */
public class GSONPilotV1Deserializer implements JsonDeserializer<PilotV1> {
	@Override
	public PilotV1 deserialize( final JsonElement json, final Type typeOfT, final JsonDeserializationContext context ) throws JsonParseException {
		final JsonObject jsonObject = json.getAsJsonObject();
		final long totalSkillpoints = jsonObject.get( "totalSkillpoints" ).getAsLong();
		final GetCharactersCharacterIdOk pilotPublicData = new GetCharactersCharacterIdOk();
		pilotPublicData.setName( jsonObject.get( "name" ).getAsString() );
		pilotPublicData.setDescription( jsonObject.get( "description" ).getAsString() );
		pilotPublicData.setBirthday( DateTime.parse( jsonObject.get( "birthday" ).getAsString() ) );

		return new PilotV1.Builder()
				.withPilotId( jsonObject.get( "pilotId" ).getAsInt() )
				.withPilotPublicData( pilotPublicData )
				//				.withCorporation( corporation )
				.withRaceData( context.deserialize( jsonObject.get( "raceData" ).getAsJsonObject(), GetUniverseRaces200Ok.class ) )
				.withBloodlineData( context.deserialize( jsonObject.get( "bloodlineData" ).getAsJsonObject(), GetUniverseBloodlines200Ok.class ) )
				.withAncestryData( context.deserialize( jsonObject.get( "ancestryData" ).getAsJsonObject(), GetUniverseAncestries200Ok.class ) )
				.withLastKnownLocation( context.deserialize( jsonObject.get( "lastKnownLocation" ).getAsJsonObject(), SpaceLocation.class ) )
				.withTotalSkillPoints( totalSkillpoints )
				.build();
	}
}