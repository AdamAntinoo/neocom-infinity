package org.dimensinfin.eveonline.neocom.infinity.adapter;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import org.dimensinfin.eveonline.neocom.adapter.LocationCatalogService;
import org.dimensinfin.eveonline.neocom.provider.ESIUniverseDataProvider;

@Component
public class LocationCatalogServiceWrapper extends LocationCatalogService {
	private StoreCacheManagerWrapper storeCacheManagerLocal;

	// - C O N S T R U C T O R S
	@Autowired
	public LocationCatalogServiceWrapper( final ConfigurationProviderWrapper configurationProvider,
	                                      final FileSystemWrapper fileSystemAdapter,
	                                      final RetrofitFactoryWrapper retrofitFactory,
	                                      final StoreCacheManagerWrapper storeCacheManager ) {
		this.configurationProvider = configurationProvider;
		this.fileSystemAdapter = fileSystemAdapter;
		this.retrofitFactory = retrofitFactory;
		this.storeCacheManagerLocal = storeCacheManager;
	}

	@PostConstruct
	private void build() {
		final ESIUniverseDataProvider esiUniverseDataProviderLocal = new ESIUniverseDataProvider.Builder()
				.withConfigurationProvider( this.configurationProvider )
				.withFileSystemAdapter( this.fileSystemAdapter )
				.withRetrofitFactory( this.retrofitFactory )
				.withStoreCacheManager( this.storeCacheManagerLocal )
				.build();
		new LocationCatalogService.Builder( this )
				.withConfigurationProvider( this.configurationProvider )
				.withFileSystemAdapter( this.fileSystemAdapter )
				.withESIUniverseDataProvider( esiUniverseDataProviderLocal )
				.withRetrofitFactory( this.retrofitFactory )
				.build();
	}
}
