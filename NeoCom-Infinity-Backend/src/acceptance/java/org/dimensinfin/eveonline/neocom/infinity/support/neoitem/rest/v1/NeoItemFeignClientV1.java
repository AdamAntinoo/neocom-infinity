package org.dimensinfin.eveonline.neocom.infinity.support.neoitem.rest.v1;

import java.io.IOException;

import com.google.gson.GsonBuilder;
import org.joda.time.DateTime;
import org.joda.time.LocalDate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.dimensinfin.eveonline.neocom.infinity.acceptance.support.AcceptanceTargetConfig;
import org.dimensinfin.eveonline.neocom.infinity.support.rest.NeoComApiv1;
import org.dimensinfin.eveonline.neocom.infinity.support.rest.NeoItemApiv1;
import org.dimensinfin.eveonline.neocom.service.logger.NeoComLogger;
import org.dimensinfin.eveonline.neocom.utility.GSONDateTimeDeserializer;
import org.dimensinfin.eveonline.neocom.utility.GSONLocalDateDeserializer;

import retrofit2.Converter;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class NeoItemFeignClientV1 {
	public static final Converter.Factory GSON_CONVERTER_FACTORY = GsonConverterFactory
			.create( (new GsonBuilder())
					.registerTypeAdapter( DateTime.class, new GSONDateTimeDeserializer() )
					.registerTypeAdapter( LocalDate.class, new GSONLocalDateDeserializer() ).create() );

	public ResponseEntity<NeoItemTransport> getItemBasic( final Integer itemId ) throws IOException {
		final Response<NeoItemTransport> response = new Retrofit.Builder()
				.baseUrl( new AcceptanceTargetConfig().getBackendServer() )
				.addConverterFactory( GSON_CONVERTER_FACTORY )
				.build()
				.create( NeoItemApiv1.class )
				.getItemBasic( NeoComApiv1.NEOCOM_BACKEND_ACCEPTED_CONTENT_TYPE, itemId )
				.execute();
		if (response.isSuccessful()) {
			NeoComLogger.info( "> Response is 200 OK." );
			return new ResponseEntity<>( response.body(), HttpStatus.OK );
		} else return new ResponseEntity<>( HttpStatus.BAD_REQUEST );
	}
}