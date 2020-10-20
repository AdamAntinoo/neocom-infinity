package org.dimensinfin.eveonline.neocom.infinity.support.miningextraction.rest.v1;

import java.io.IOException;
import java.util.List;

import com.google.gson.GsonBuilder;
import org.joda.time.DateTime;
import org.joda.time.LocalDate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import org.dimensinfin.eveonline.neocom.core.support.GSONDateTimeDeserializer;
import org.dimensinfin.eveonline.neocom.core.support.GSONLocalDateDeserializer;
import org.dimensinfin.eveonline.neocom.database.entities.MiningExtractionEntity;
import org.dimensinfin.eveonline.neocom.infinity.support.rest.NeoComApiv1;
import org.dimensinfin.eveonline.neocom.service.logger.NeoComLogger;

import retrofit2.Converter;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Component
public class MiningExtractionsFeignClientV1 {
	public static final Converter.Factory GSON_CONVERTER_FACTORY =
			GsonConverterFactory.create(
					new GsonBuilder()
							.registerTypeAdapter( DateTime.class, new GSONDateTimeDeserializer() )
							.registerTypeAdapter( LocalDate.class, new GSONLocalDateDeserializer() )
							.create() );

	public ResponseEntity<List<MiningExtractionEntity>> getTodayMiningExtractions4Pilot( final Integer pilotId ,
	                                                                                     final String authorizationToken) throws IOException {
		final String ENDPOINT_MESSAGE = "Request today list of mining extractions for pilot.";
		final Response<List<MiningExtractionEntity>> response = new Retrofit.Builder()
				.baseUrl( NeoComApiv1.NEOCOM_BACKEND_APP_HOST )
				.addConverterFactory( GSON_CONVERTER_FACTORY )
				.build()
				.create( NeoComApiv1.class )
				.getTodayMiningExtractions4Pilot( NeoComApiv1.NEOCOM_BACKEND_ACCEPTED_CONTENT_TYPE,
						authorizationToken,
						pilotId )
				.execute();
		if (response.isSuccessful()) {
			NeoComLogger.info( ENDPOINT_MESSAGE );
			return new ResponseEntity<>( response.body(), HttpStatus.OK );
		} else {
			NeoComLogger.info( ENDPOINT_MESSAGE + " Failed." );
			return new ResponseEntity<>( HttpStatus.valueOf( response.code() ) );
		}
	}
}
