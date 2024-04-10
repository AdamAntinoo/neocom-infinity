package org.dimensinfin.poc;

import com.google.inject.Guice;
import com.google.inject.Injector;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import org.dimensinfin.eveonline.neocom.service.ESIDataService;
import org.dimensinfin.eveonline.neocom.service.RetrofitService;
import org.dimensinfin.logging.LogWrapper;

@SpringBootApplication
@EnableAutoConfiguration
public class NeoComInfinityBackendApplication {
	public static void main( String[] args ) {
		SpringApplication.run( NeoComInfinityBackendApplication.class, args );
	}

	private final Injector injector; // The global Guice injector singleton

	// Guice modules are initialized before the spring context completes
	{
		LogWrapper.info("Activating the injector");
		injector = Guice.createInjector(
				new POCDependenciesModule()
		);
	}

	@Bean
	public ESIDataService injectESIdataservice() {
		LogWrapper.enter();
		return injector.getInstance( ESIDataService.class );
	}
	@Bean
	public RetrofitService injectRetrofitService() {
		LogWrapper.enter();
		return injector.getInstance( RetrofitService.class );
	}
}
