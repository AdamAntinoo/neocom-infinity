package org.dimensinfin.eveonline.neocom.infinity.adapter;

import java.util.Objects;
import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import org.dimensinfin.eveonline.neocom.adapter.LocationCatalogService;
import org.dimensinfin.eveonline.neocom.provider.ESIUniverseDataProvider;

@Component
public class LocationCatalogServiceWrapper {
	private final ConfigurationProviderWrapper configurationProvider;
	private final FileSystemWrapper fileSystemAdapter;
	private final RetrofitFactoryWrapper retrofitFactoryWrapper;
	private final StoreCacheManagerWrapper storeCacheManagerWrapper;
	private LocationCatalogService singleton;

	// - C O N S T R U C T O R S
	@Autowired
	public LocationCatalogServiceWrapper( final ConfigurationProviderWrapper configurationProvider,
	                                      final FileSystemWrapper fileSystemAdapter,
	                                      final RetrofitFactoryWrapper retrofitFactoryWrapper,
	                                      final StoreCacheManagerWrapper storeCacheManagerWrapper ) {
		this.configurationProvider = configurationProvider;
		this.fileSystemAdapter = fileSystemAdapter;
		this.retrofitFactoryWrapper = retrofitFactoryWrapper;
		this.storeCacheManagerWrapper = storeCacheManagerWrapper;
	}

	public LocationCatalogService getSingleton() {
		return Objects.requireNonNull( this.singleton );
	}

	@PostConstruct
	private void build() {
		final ESIUniverseDataProvider esiUniverseDataProviderLocal = new ESIUniverseDataProvider.Builder()
				.withConfigurationProvider( this.configurationProvider )
				.withFileSystemAdapter( this.fileSystemAdapter )
				.withRetrofitFactory( this.retrofitFactoryWrapper.getSingleton() )
				.withStoreCacheManager( this.storeCacheManagerWrapper.getSingleton() )
				.build();
		this.singleton = new LocationCatalogService.Builder(  )
				.withConfigurationProvider( this.configurationProvider )
				.withFileSystemAdapter( this.fileSystemAdapter )
				.withESIUniverseDataProvider( esiUniverseDataProviderLocal )
				.withRetrofitFactory( this.retrofitFactoryWrapper.getSingleton() )
				.build();
	}
}
