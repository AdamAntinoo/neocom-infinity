package org.dimensinfin.eveonline.neocom.infinity.backend.core.config;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.Key;
import com.google.inject.name.Names;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.dimensinfin.eveonline.neocom.infinity.adapter.implementers.SBConfigurationService;
import org.dimensinfin.eveonline.neocom.infinity.adapter.implementers.SBFileSystemAdapter;
import org.dimensinfin.eveonline.neocom.infinity.backend.sde.service.SBSDEDatabaseService;
import org.dimensinfin.eveonline.neocom.provider.IConfigurationService;
import org.dimensinfin.eveonline.neocom.provider.IFileSystem;
import org.dimensinfin.eveonline.neocom.service.ESIDataService;
import org.dimensinfin.eveonline.neocom.service.IStoreCache;
import org.dimensinfin.eveonline.neocom.service.MemoryStoreCacheService;
import org.dimensinfin.eveonline.neocom.service.RetrofitService;

/**
 * Configure the Guide dependencies defined at the Data Management library.
 */
@Configuration
public class NeoComDependencyConfiguration {
	private final Injector injector; // The global Guice injector singleton

	// Guice modules are initialized before the spring context completes
	{
		injector = Guice.createInjector(
				new NeoComInfinityBackendDependenciesModule()
		);
	}

	@Bean
	public String dependencyApplicationDirectory() {
		return this.injector.getInstance( Key.get( String.class, Names.named( "ApplicationDirectory" ) ) );
	}

	@Bean
	public ESIDataService dependencyESIDataService() {
		return injector.getInstance( ESIDataService.class );
	}

	@Bean
	public IConfigurationService dependencyIConfigurationService() {
		return injector.getInstance( SBConfigurationService.class );
	}

	@Bean
	public IFileSystem dependencyIFileSystem() {
		return injector.getInstance( SBFileSystemAdapter.class );
	}

	@Bean
	public SBSDEDatabaseService dependencyISDEDatabaseService() {
		return injector.getInstance( SBSDEDatabaseService.class );
	}

	@Bean
	public IStoreCache dependencyIStoreCache() {
		return injector.getInstance( MemoryStoreCacheService.class );
	}

	@Bean
	public String dependencyPropertiesDirectory() {
		return this.injector.getInstance( Key.get( String.class, Names.named( "PropertiesDirectory" ) ) );
	}

	@Bean
	public RetrofitService dependencyRetrofitService() {
		return injector.getInstance( RetrofitService.class );
	}

	@Bean
	public String dependencySDEDatabasePath() {
		return this.injector.getInstance( Key.get( String.class, Names.named( "SDEDatabasePath" ) ) );
	}
}