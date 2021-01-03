package org.dimensinfin.eveonline.neocom.infinity.adapter;

import java.util.Objects;
import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import org.dimensinfin.eveonline.neocom.adapter.LocationCatalogService;
import org.dimensinfin.eveonline.neocom.provider.IConfigurationService;
import org.dimensinfin.eveonline.neocom.provider.IFileSystem;

@Component
public class LocationCatalogServiceWrapper {
	private final IConfigurationService configurationService;
	private final IFileSystem fileSystemAdapter;
	private final ESIUniverseDataProviderWrapper esiUniverseDataProviderWrapper;
	private final RetrofitFactoryWrapper retrofitFactoryWrapper;
	private final StoreCacheManagerWrapper storeCacheManagerWrapper;
	private LocationCatalogService singleton;

	// - C O N S T R U C T O R S
	@Autowired
	public LocationCatalogServiceWrapper( final ConfigurationServiceWrapper configurationServiceWrapper,
	                                      final IFileSystem fileSystemAdapter,
	                                      final ESIUniverseDataProviderWrapper esiUniverseDataProviderWrapper,
	                                      final RetrofitFactoryWrapper retrofitFactoryWrapper,
	                                      final StoreCacheManagerWrapper storeCacheManagerWrapper ) {
		this.configurationService = configurationServiceWrapper.getSingleton();
		this.fileSystemAdapter = fileSystemAdapter;
		this.esiUniverseDataProviderWrapper=esiUniverseDataProviderWrapper;
		this.retrofitFactoryWrapper = retrofitFactoryWrapper;
		this.storeCacheManagerWrapper = storeCacheManagerWrapper;
	}

	public LocationCatalogService getSingleton() {
		return Objects.requireNonNull( this.singleton );
	}

	@PostConstruct
	void build() {
		this.singleton = new LocationCatalogService.Builder()
				.withConfigurationProvider( this.configurationService )
				.withFileSystemAdapter( this.fileSystemAdapter )
				.withESIUniverseDataProvider( this.esiUniverseDataProviderWrapper.getSingleton() )
				.withRetrofitFactory( this.retrofitFactoryWrapper.getSingleton() )
				.build();
	}
}
