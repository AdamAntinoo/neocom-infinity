package org.dimensinfin.eveonline.neocom.infinity.infrastructure.adapters.outbound.MarketData;

import java.io.IOException;
import java.text.MessageFormat;
import java.util.concurrent.TimeUnit;

import com.google.gson.GsonBuilder;
import org.joda.time.DateTime;
import org.joda.time.LocalDate;
import org.springframework.stereotype.Service;

import org.dimensinfin.eveonline.neocom.infinity.app.ports.MarketDataPort;
import org.dimensinfin.eveonline.neocom.infinity.client.core.dto.RestExceptionResponse;
import org.dimensinfin.eveonline.neocom.infinity.core.exception.NeoComRuntimeBackendException;
import org.dimensinfin.eveonline.neocom.infinity.infrastructure.adapters.outbound.MarketData.api.FuzzworksApi;
import org.dimensinfin.eveonline.neocom.infinity.infrastructure.adapters.outbound.MarketData.converters.FuzzWorkMarketDataConverter;
import org.dimensinfin.eveonline.neocom.infinity.infrastructure.domain.FuzzWorkMarketData;
import org.dimensinfin.eveonline.neocom.infinity.infrastructure.domain.V2MarketDataDto;
import org.dimensinfin.eveonline.neocom.utility.GSONDateTimeDeserializer;
import org.dimensinfin.eveonline.neocom.utility.GSONLocalDateDeserializer;
import org.dimensinfin.logging.LogWrapper;

import okhttp3.OkHttpClient;
import retrofit2.Converter;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import static org.dimensinfin.eveonline.neocom.infinity.infrastructure.adapters.outbound.MarketData.MarketDataAdapter.DEFAULT_MARKETDATA_SYSTEM;

/**
 * Provide the Market Data information from the new Fuzzworks data provider.
 */
@Service
public class FuzzworksMarketDataAdapter implements MarketDataPort {
	public static final Converter.Factory GSON_CONVERTER_FACTORY =
			GsonConverterFactory.create(
					new GsonBuilder()
							.registerTypeAdapter( DateTime.class, new GSONDateTimeDeserializer() )
							.registerTypeAdapter( LocalDate.class, new GSONLocalDateDeserializer() )
							//							.registerTypeAdapter( Link.class, new GSONLinkDeserializer() )
							//							.registerTypeAdapter( NeoItem.class, new GSONNeoItemDeserializer() )
							//							.registerTypeAdapter( BuildAction.class, new GSONBuildActionDeserializer() )
							//							.registerTypeAdapter( SpaceLocation.class, new GSONSpaceLocationDeserializer() )
							//							.registerTypeAdapter( AuthenticationStateResponse.class, new AuthenticationStateResponseDeserializer() )
							//							.registerTypeAdapter( PilotV1.class, new GSONPilotV1Deserializer() )
							.create() );
	public static final OkHttpClient okHttpClient = new OkHttpClient().newBuilder()
			.connectTimeout( 60, TimeUnit.SECONDS )
			.readTimeout( 60, TimeUnit.SECONDS )
			.writeTimeout( 60, TimeUnit.SECONDS )
			.build();
	private static final String BASE_URL = "https://market.fuzzwork.co.uk/";

	@Override
	public V2MarketDataDto getMarketData4Type( final int typeId ) {
		LogWrapper.enter( MessageFormat.format( "get Fuzz market data->{0}", typeId ) );
		final String ENDPOINT_MESSAGE = "Request the Build Configurations for a Fitting.";
		try {
			final Response<FuzzWorkMarketData> response = new Retrofit.Builder()
					.baseUrl( BASE_URL )
					.addConverterFactory( GSON_CONVERTER_FACTORY )
					.client( okHttpClient )
					.build()
					.create( FuzzworksApi.class )
					.getMarketData( DEFAULT_MARKETDATA_SYSTEM, typeId )
					.execute();
			if (response.isSuccessful()) {
				LogWrapper.info( ENDPOINT_MESSAGE );
				LogWrapper.exit();
				return new FuzzWorkMarketDataConverter().convert( response.body() )
						.setTypeId( typeId )
						.setLocationId( DEFAULT_MARKETDATA_SYSTEM );
			} else {
				final String errorMessage = response.errorBody().toString();
				LogWrapper.error( new RuntimeException( errorMessage ) );
				LogWrapper.exit();
				throw new NeoComRuntimeBackendException( new RestExceptionResponse()
						.withHttpStatusCode( response.code() )
						.withMessage( errorMessage ) );
			}
		} catch (final IOException e) {
			LogWrapper.error( e );
			throw new NeoComRuntimeBackendException( e.getMessage() );
		} finally {
			LogWrapper.exit();
		}
	}

	@Override
	public V2MarketDataDto getMarketData4TypeAtStation( final int typeId, final int stationId ) {
		return V2MarketDataDto.builder().build();
	}
}
