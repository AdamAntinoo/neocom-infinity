package org.dimensinfin.eveonline.neocom.infinity.support.core;

import java.util.Objects;
import java.util.concurrent.TimeUnit;
import javax.validation.constraints.NotNull;

import com.google.gson.GsonBuilder;
import org.joda.time.DateTime;
import org.joda.time.LocalDate;
import org.springframework.hateoas.Link;
import org.springframework.stereotype.Component;

import org.dimensinfin.eveonline.neocom.core.support.GSONDateTimeDeserializer;
import org.dimensinfin.eveonline.neocom.core.support.GSONLocalDateDeserializer;
import org.dimensinfin.eveonline.neocom.domain.NeoItem;
import org.dimensinfin.eveonline.neocom.domain.space.Station;
import org.dimensinfin.eveonline.neocom.infinity.acceptance.support.ITargetConfiguration;
import org.dimensinfin.eveonline.neocom.infinity.acceptance.support.character.rest.deserializer.GSONLinkDeserializer;
import org.dimensinfin.eveonline.neocom.infinity.acceptance.support.character.rest.deserializer.GSONNeoItemDeserializer;
import org.dimensinfin.eveonline.neocom.infinity.acceptance.support.industry.deserializer.GSONBuildActionDeserializer;
import org.dimensinfin.eveonline.neocom.infinity.acceptance.support.industry.deserializer.GSONStationDeserializer;
import org.dimensinfin.eveonline.neocom.infinity.backend.industry.fitting.domain.BuildAction;

import okhttp3.OkHttpClient;
import retrofit2.Converter;
import retrofit2.converter.gson.GsonConverterFactory;

@Component
public class CommonFeignClient {
	public static final Converter.Factory GSON_CONVERTER_FACTORY =
			GsonConverterFactory.create(
					new GsonBuilder()
							.registerTypeAdapter( DateTime.class, new GSONDateTimeDeserializer() )
							.registerTypeAdapter( LocalDate.class, new GSONLocalDateDeserializer() )
							.registerTypeAdapter( Link.class, new GSONLinkDeserializer() )
							.registerTypeAdapter( NeoItem.class, new GSONNeoItemDeserializer() )
							.registerTypeAdapter( BuildAction.class, new GSONBuildActionDeserializer() )
							.registerTypeAdapter( Station.class, new GSONStationDeserializer() )
							.create() );
	public static final OkHttpClient okHttpClient = new OkHttpClient().newBuilder()
			.connectTimeout( 60, TimeUnit.SECONDS )
			.readTimeout( 60, TimeUnit.SECONDS )
			.writeTimeout( 60, TimeUnit.SECONDS )
			.build();
	protected final ITargetConfiguration acceptanceTargetConfig;

	// - C O N S T R U C T O R S
	public CommonFeignClient( final @NotNull ITargetConfiguration acceptanceTargetConfig ) {
		this.acceptanceTargetConfig = Objects.requireNonNull( acceptanceTargetConfig );
	}
}
