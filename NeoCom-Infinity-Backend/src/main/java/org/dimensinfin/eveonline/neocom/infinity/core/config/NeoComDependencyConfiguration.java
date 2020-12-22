package org.dimensinfin.eveonline.neocom.infinity.core.config;

import com.google.inject.Guice;
import com.google.inject.Injector;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.dimensinfin.eveonline.neocom.adapter.LocationCatalogService;
import org.dimensinfin.eveonline.neocom.adapter.StoreCacheManager;
import org.dimensinfin.eveonline.neocom.database.NeoComDatabaseService;
import org.dimensinfin.eveonline.neocom.database.repositories.PilotPreferencesRepository;
import org.dimensinfin.eveonline.neocom.infinity.adapter.implementers.SBConfigurationService;
import org.dimensinfin.eveonline.neocom.infinity.adapter.implementers.SBFileSystemAdapter;
import org.dimensinfin.eveonline.neocom.infinity.backend.NeoComInfinityBackendDependenciesModule;
import org.dimensinfin.eveonline.neocom.provider.IConfigurationService;
import org.dimensinfin.eveonline.neocom.provider.IFileSystem;
import org.dimensinfin.eveonline.neocom.provider.RetrofitFactory;
import org.dimensinfin.eveonline.neocom.service.ESIDataService;

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
	public IConfigurationService configurationServiceAccess() {
		return injector.getInstance( SBConfigurationService.class );
	}

	@Bean
	public ESIDataService esiDataServiceAccess() {
		return injector.getInstance( ESIDataService.class );
	}

	@Bean
	public IFileSystem fileSystemAccess() {
		return injector.getInstance( SBFileSystemAdapter.class );
	}

	@Bean
	public LocationCatalogService locationCatalogServiceAccess() {
		return injector.getInstance( LocationCatalogService.class );
	}

	@Bean
	public NeoComDatabaseService neoComDatabaseServiceAccess() {
		return injector.getInstance( NeoComDatabaseService.class );
	}

	@Bean
	public PilotPreferencesRepository pilotPreferencesRepositoryAccess() {
		return injector.getInstance( PilotPreferencesRepository.class );
	}

	@Bean
	public RetrofitFactory retrofitFactoryAccess() {
		return injector.getInstance( RetrofitFactory.class );
	}

	@Bean
	public StoreCacheManager storeCacheManagerAccess() {
		return injector.getInstance( StoreCacheManager.class );
	}
}