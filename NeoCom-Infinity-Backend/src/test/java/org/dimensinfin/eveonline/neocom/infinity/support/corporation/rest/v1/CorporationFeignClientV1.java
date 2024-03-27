package org.dimensinfin.eveonline.neocom.infinity.support.corporation.rest.v1;

import java.io.IOException;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.datatype.joda.JodaModule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import org.dimensinfin.eveonline.neocom.infinity.support.rest.NeoComApiv1;
import org.dimensinfin.eveonline.neocom.infinity.support.NeoComWorld;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

@Component
public class CorporationFeignClientV1 {
	private final NeoComWorld neoComWorld;
	private final ObjectMapper mapper;

	@Autowired
	public CorporationFeignClientV1( final NeoComWorld neoComWorld ) {
		this.neoComWorld = neoComWorld;
		this.mapper = new ObjectMapper();
//		mapper.registerModule( new JodaModule() );
		SimpleModule module = new SimpleModule();
//		module.addDeserializer( LocationAssetContainer.class, new LocationAssetContainer() );
		mapper.registerModule( module );
	}

	public ResponseEntity<List<LocationAssetContainer>> getCorporationAssetsByLocation( final Integer corporationIdentifier,
	                                                                                    final String authorizationToken ) throws IOException {
		final NeoComApiv1 serviceCorporation = new Retrofit.Builder()
				.baseUrl( NeoComApiv1.NEOCOM_BACKEND_APP_HOST )
				.addConverterFactory( JacksonConverterFactory.create( mapper ) )
				.build()
				.create( NeoComApiv1.class );
		final Call<List<LocationAssetContainer>> request = serviceCorporation.getCorporationAssetsByLocation(
				"application/json",
				authorizationToken,
				corporationIdentifier
		);
		final Response<List<LocationAssetContainer>> response = request.execute();
		if (response.isSuccessful()) {
			this.neoComWorld.setCorporationAssetsByLocation( response.body() );
			return new ResponseEntity<>( response.body(), HttpStatus.OK );
		} else return new ResponseEntity<>( HttpStatus.valueOf( response.code() ) );
	}

	public ResponseEntity<CorporationResponse> getCorporationData( final Integer corporationIdentifier
			, final String authorizationToken ) throws IOException {
		ObjectMapper mapper = new ObjectMapper();
		mapper.registerModule( new JodaModule() );
		final NeoComApiv1 serviceCorporation = new Retrofit.Builder()
				.baseUrl( NeoComApiv1.NEOCOM_BACKEND_APP_HOST )
				.addConverterFactory( JacksonConverterFactory.create( mapper ) )
				.build()
				.create( NeoComApiv1.class );
		final Call<CorporationResponse> request = serviceCorporation.getCorporationData(
				"application/json",
				authorizationToken,
				corporationIdentifier
		);
		final Response<CorporationResponse> response = request.execute();
		if (response.isSuccessful()) {
			final CorporationResponse corporationResponse = response.body();
			return new ResponseEntity<>( corporationResponse, HttpStatus.OK );
		} else return new ResponseEntity<>( HttpStatus.valueOf( response.code() ) );
	}

	private void registerDeSerializers() {


	}
}
