package org.dimensinfin.poc;

import com.google.inject.Guice;
import com.google.inject.Injector;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.dimensinfin.eveonline.neocom.provider.IConfigurationService;
import org.dimensinfin.eveonline.neocom.provider.IFileSystem;
import org.dimensinfin.eveonline.neocom.service.DMServicesDependenciesModule;
import org.dimensinfin.eveonline.neocom.service.ESIDataService;
import org.dimensinfin.eveonline.neocom.service.IStoreCache;
import org.dimensinfin.eveonline.neocom.service.MemoryStoreCacheService;
import org.dimensinfin.eveonline.neocom.service.ResourceFactory;
import org.dimensinfin.eveonline.neocom.service.RetrofitService;
import org.dimensinfin.logging.LogWrapper;
import org.dimensinfin.poc.service.SBConfigurationService;
import org.dimensinfin.poc.service.SBFileSystemAdapter;

@Configuration
public class DependenciesConfig {
	private final Injector injector; // The global Guice injector singleton

	// Guice modules are initialized before the spring context completes
	{
		LogWrapper.info( "Creating Injector for Guice dependencies..." );
		injector = Guice.createInjector(
				new DMServicesDependenciesModule()
		);
	}

	@Bean
	public IConfigurationService dependency_01_IConfigurationService() {
		LogWrapper.enter();
		final SBConfigurationService configurationService = injector.getInstance( SBConfigurationService.class );
		//		configurationService.readAllProperties();
		LogWrapper.exit();
		return configurationService;
	}
	@Bean
	public IFileSystem dependency_01_IFileSystem() {
		LogWrapper.enter();
		return injector.getInstance( SBFileSystemAdapter.class );
	}
	@Bean
	public IStoreCache dependency_02_IStoreCache() {
		LogWrapper.enter();
		return injector.getInstance( MemoryStoreCacheService.class );
	}

	@Bean
	public RetrofitService dependency_02_RetrofitService() {
		LogWrapper.enter();
		return injector.getInstance( RetrofitService.class );
	}
	@Bean
	public ResourceFactory dependency_04_ResourceFactory() {
		LogWrapper.enter();
		return injector.getInstance( ResourceFactory.class );
	}
	@Bean
	public ESIDataService dependency_02_ESIDataService() {
		LogWrapper.enter();
		return injector.getInstance( ESIDataService.class );
	}
}