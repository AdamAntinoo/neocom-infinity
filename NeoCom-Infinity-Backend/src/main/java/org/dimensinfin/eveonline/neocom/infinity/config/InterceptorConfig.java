package org.dimensinfin.eveonline.neocom.infinity.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import org.dimensinfin.eveonline.neocom.infinity.config.interceptor.LogRequestHeaderInterceptor;
import org.dimensinfin.eveonline.neocom.infinity.config.interceptor.LogRequestInterceptor;
import org.dimensinfin.eveonline.neocom.infinity.config.interceptor.LogResponseInterceptor;
import org.dimensinfin.eveonline.neocom.infinity.config.interceptor.LogTimingInterceptor;

@Configuration
public class InterceptorConfig implements WebMvcConfigurer {
	@Value("${P.runtime.logging.request}")
	private final String logRequestFlag = "false";
	@Value("${P.runtime.logging.response}")
	private final String logResponseFlag = "true";
	@Value("${P.runtime.logging.timing}")
	private final String logTimingFlag = "true";
	@Value("${P.runtime.logging.metrics}")
	private final String logMetricsFlag = "true";

	@Override
	public void addInterceptors( final InterceptorRegistry registry ) {
		if (this.logRequestFlag.equalsIgnoreCase( "true" ))
			registry.addInterceptor( new LogRequestInterceptor() ).addPathPatterns( "/api/**" );
		if (this.logResponseFlag.equalsIgnoreCase( "true" ))
			registry.addInterceptor( new LogResponseInterceptor() ).addPathPatterns( "/api/**" );
		if (this.logTimingFlag.equalsIgnoreCase( "true" ))
			registry.addInterceptor( new LogTimingInterceptor() ).addPathPatterns( "/api/**" );
		////		if ( logMetricsFlag.equalsIgnoreCase( "true" ))
		////		registry.addInterceptor(new MetricsInterceptor()).addPathPatterns("/api/**");
		registry.addInterceptor( new LogRequestHeaderInterceptor() ).addPathPatterns( "/api/**" );
		//		registry.addInterceptor( new HeaderResponseInterceptor() ).addPathPatterns( "/api/**" );
	}
}
