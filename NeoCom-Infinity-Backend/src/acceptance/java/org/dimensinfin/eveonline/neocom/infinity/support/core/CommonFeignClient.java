package org.dimensinfin.eveonline.neocom.infinity.support.core;

import java.util.List;
import java.util.Objects;
import java.util.concurrent.TimeUnit;
import javax.validation.constraints.NotNull;

import com.google.gson.GsonBuilder;
import org.joda.time.DateTime;
import org.joda.time.LocalDate;
import org.springframework.hateoas.Link;
import org.springframework.stereotype.Component;

import org.dimensinfin.eveonline.neocom.domain.NeoItem;
import org.dimensinfin.eveonline.neocom.domain.space.SpaceLocation;
import org.dimensinfin.eveonline.neocom.infinity.acceptance.support.ITargetConfiguration;
import org.dimensinfin.eveonline.neocom.infinity.acceptance.support.authorization.rest.v1.deserializer.AuthenticationStateResponseDeserializer;
import org.dimensinfin.eveonline.neocom.infinity.acceptance.support.character.rest.deserializer.GSONLinkDeserializer;
import org.dimensinfin.eveonline.neocom.infinity.acceptance.support.character.rest.deserializer.GSONNeoItemDeserializer;
import org.dimensinfin.eveonline.neocom.infinity.acceptance.support.deserializer.GSONSpaceLocationDeserializer;
import org.dimensinfin.eveonline.neocom.infinity.acceptance.support.industry.deserializer.GSONBuildActionDeserializer;
import org.dimensinfin.eveonline.neocom.infinity.backend.authorization.domain.AuthenticationStateResponse;
import org.dimensinfin.eveonline.neocom.infinity.backend.industry.fitting.domain.BuildAction;
import org.dimensinfin.eveonline.neocom.utility.GSONDateTimeDeserializer;
import org.dimensinfin.eveonline.neocom.utility.GSONLocalDateDeserializer;

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
							.registerTypeAdapter( SpaceLocation.class, new GSONSpaceLocationDeserializer() )
							.registerTypeAdapter( AuthenticationStateResponse.class, new AuthenticationStateResponseDeserializer() )
							//							.registerTypeAdapter( PilotV1.class, new GSONPilotV1Deserializer() )
							.create() );
	public static final OkHttpClient okHttpClient = new OkHttpClient().newBuilder()
			.connectTimeout( 60, TimeUnit.SECONDS )
			.readTimeout( 60, TimeUnit.SECONDS )
			.writeTimeout( 60, TimeUnit.SECONDS )
			.build();
	protected static final String FAILURE_SIGNAL_SUFFIX = " Failed.";
	protected final ITargetConfiguration acceptanceTargetConfig;

	// - C O N S T R U C T O R S
	public CommonFeignClient( final @NotNull ITargetConfiguration acceptanceTargetConfig ) {
		this.acceptanceTargetConfig = Objects.requireNonNull( acceptanceTargetConfig );
	}

	public String prepareCookies( final List<String> cookies ) {
		final StringBuilder cookieBuilder = new StringBuilder();
		for (final String cookie : cookies)
			cookieBuilder.append( cookie ).append( "; " );
		String cookieContent = cookieBuilder.toString();
		if (cookieContent.length() > 2) cookieContent = cookieContent.substring( 0, cookieContent.length() - 2 );
		return cookieContent;
	}
}
