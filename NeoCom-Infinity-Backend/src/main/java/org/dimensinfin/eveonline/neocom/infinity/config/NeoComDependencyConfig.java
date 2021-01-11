package org.dimensinfin.eveonline.neocom.infinity.config;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.Key;
import com.google.inject.name.Names;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.dimensinfin.eveonline.neocom.database.NeoComDatabaseService;
import org.dimensinfin.eveonline.neocom.database.core.ISDEDatabaseService;
import org.dimensinfin.eveonline.neocom.database.repositories.CredentialRepository;
import org.dimensinfin.eveonline.neocom.database.repositories.SDERepository;
import org.dimensinfin.eveonline.neocom.infinity.NeoComInfinityBackendDependenciesModule;
import org.dimensinfin.eveonline.neocom.infinity.adapter.implementers.SBFileSystemAdapter;
import org.dimensinfin.eveonline.neocom.infinity.adapter.implementers.SBNeoComDBAdapter;
import org.dimensinfin.eveonline.neocom.infinity.backend.sde.service.SBSDEDatabaseService;
import org.dimensinfin.eveonline.neocom.infinity.service.SBConfigurationService;
import org.dimensinfin.eveonline.neocom.provider.IConfigurationService;
import org.dimensinfin.eveonline.neocom.provider.IFileSystem;
import org.dimensinfin.eveonline.neocom.service.ESIDataService;
import org.dimensinfin.eveonline.neocom.service.IStoreCache;
import org.dimensinfin.eveonline.neocom.service.LocationCatalogService;
import org.dimensinfin.eveonline.neocom.service.MemoryStoreCacheService;
import org.dimensinfin.eveonline.neocom.service.ResourceFactory;
import org.dimensinfin.eveonline.neocom.service.RetrofitService;
import org.dimensinfin.logging.LogWrapper;

/**
 * Configure the Guide dependencies defined at the Data Management library.
 */
@Configuration
public class NeoComDependencyConfig {
	private final Injector injector; // The global Guice injector singleton

	// Guice modules are initialized before the spring context completes
	{
		LogWrapper.info( "Creating Injector for Guice dependencies..." );
		this.injector = Guice.createInjector( new NeoComInfinityBackendDependenciesModule() );
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
		return this.injector.getInstance( SBConfigurationService.class );
	}

	@Bean
	public IFileSystem dependency_01_IFileSystem() {
		LogWrapper.enter();
		return this.injector.getInstance( SBFileSystemAdapter.class );
	}

	@Bean
	public IStoreCache dependency_01_IStoreCache() {
		LogWrapper.enter();
		return this.injector.getInstance( MemoryStoreCacheService.class );
	}

	@Bean
	public RetrofitService dependency_02_RetrofitService() {
		LogWrapper.enter();
		return this.injector.getInstance( RetrofitService.class );
	}

	@Bean
	public ISDEDatabaseService dependency_03_ISDEDatabaseService() {
		LogWrapper.enter();
		return this.injector.getInstance( SBSDEDatabaseService.class );
	}

	@Bean
	public LocationCatalogService dependency_03_LocationCatalogService() {
		LogWrapper.enter();
		return this.injector.getInstance( LocationCatalogService.class );
	}

	@Bean
	public ESIDataService dependency_04_ESIDataService() {
		LogWrapper.enter();
		return this.injector.getInstance( ESIDataService.class );
	}

	@Bean
	public ResourceFactory dependency_06_ResourceFactory() {
		LogWrapper.enter();
		return this.injector.getInstance( ResourceFactory.class );
	}

	@Bean
	public SDERepository dependency_07_SDERepository() {
		LogWrapper.enter();
		return this.injector.getInstance( SDERepository.class );
	}

	@Bean
	public NeoComDatabaseService dependency_08_NeoComDatabaseService() {
		LogWrapper.enter();
		return this.injector.getInstance( SBNeoComDBAdapter.class );
	}

	@Bean
	public CredentialRepository dependency_09_CredentialRepository() {
		LogWrapper.enter();
		return this.injector.getInstance( CredentialRepository.class );
	}
}