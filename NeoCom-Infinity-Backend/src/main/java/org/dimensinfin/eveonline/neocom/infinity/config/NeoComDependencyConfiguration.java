package org.dimensinfin.eveonline.neocom.infinity.config;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.Key;
import com.google.inject.name.Names;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.dimensinfin.eveonline.neocom.database.core.ISDEDatabaseService;
import org.dimensinfin.eveonline.neocom.database.repositories.SDERepository;
import org.dimensinfin.eveonline.neocom.infinity.service.SBConfigurationService;
import org.dimensinfin.eveonline.neocom.infinity.adapter.implementers.SBFileSystemAdapter;
import org.dimensinfin.eveonline.neocom.infinity.backend.sde.service.SBSDEDatabaseService;
import org.dimensinfin.eveonline.neocom.provider.IConfigurationService;
import org.dimensinfin.eveonline.neocom.provider.IFileSystem;
import org.dimensinfin.eveonline.neocom.service.ESIDataService;
import org.dimensinfin.eveonline.neocom.service.IStoreCache;
import org.dimensinfin.eveonline.neocom.service.MemoryStoreCacheService;
import org.dimensinfin.eveonline.neocom.service.ResourceFactory;
import org.dimensinfin.eveonline.neocom.service.RetrofitService;
import org.dimensinfin.logging.LogWrapper;

/**
 * Configure the Guide dependencies defined at the Data Management library.
 */
@Configuration
public class NeoComDependencyConfiguration {
	private final Injector injector; // The global Guice injector singleton

	// Guice modules are initialized before the spring context completes
	{
		LogWrapper.info( "Creating Injector for Guice dependencies..." );
		injector = Guice.createInjector(
				//				new DMServicesDependenciesModule(),
				new NeoComInfinityBackendDependenciesModule()
		);
	}

	@Bean
	public String dependencyApplicationDirectory() {
		LogWrapper.enter();
		return this.injector.getInstance( Key.get( String.class, Names.named( "ApplicationDirectory" ) ) );
	}

	@Bean
	public String dependencyPropertiesDirectory() {
		LogWrapper.enter();
		return this.injector.getInstance( Key.get( String.class, Names.named( "PropertiesDirectory" ) ) );
	}

	@Bean
	public String dependencySDEDatabasePath() {
		LogWrapper.enter();
		return this.injector.getInstance( Key.get( String.class, Names.named( "SDEDatabasePath" ) ) );
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
	public ESIDataService dependency_02_ESIDataService() {
		LogWrapper.enter();
		return injector.getInstance( ESIDataService.class );
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
	public ISDEDatabaseService dependency_03_ISDEDatabaseService() {
		LogWrapper.enter();
		return injector.getInstance( SBSDEDatabaseService.class );
	}

	@Bean
	public ResourceFactory dependency_04_ResourceFactory() {
		LogWrapper.enter();
		return injector.getInstance( ResourceFactory.class );
	}

	@Bean
	public SDERepository dependency_05_SDERepository() {
		LogWrapper.enter();
		return injector.getInstance( SDERepository.class );
	}
}