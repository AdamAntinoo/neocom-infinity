package org.dimensinfin.eveonline.neocom.infinity.backend.authorization.serializer;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.stereotype.Service;

import org.dimensinfin.eveonline.neocom.infinity.backend.authorization.domain.NeoComCookie;

/**
 * @author Adam Antinoo (adamantinoo.git@gmail.com)
 * @since 0.20.0
 */
@Service
public class DeserializationService {
	private static final GsonBuilder gsonBuilder = new GsonBuilder()
			.registerTypeAdapter( NeoComCookie.class, new NeoComCookieDeserializer() );
	private static final Gson gson = gsonBuilder.create();

	public NeoComCookie deserializeNeoComCookie( final String source ) {
		return gson.fromJson( source, NeoComCookie.class );
	}
}