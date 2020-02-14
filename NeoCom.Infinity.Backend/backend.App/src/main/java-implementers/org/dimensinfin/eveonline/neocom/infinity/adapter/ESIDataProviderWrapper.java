package org.dimensinfin.eveonline.neocom.infinity.adapter;

import java.util.Objects;
import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import org.dimensinfin.eveonline.neocom.provider.ESIDataProvider;

@Component
public class ESIDataProviderWrapper {
	private final ConfigurationProviderWrapper configurationProvider;
	private final FileSystemWrapper fileSystemAdapter;
	private final RetrofitFactoryWrapper retrofitFactoryWrapper;
	private final LocationCatalogServiceWrapper locationCatalogServiceWrapper;
	private final StoreCacheManagerWrapper storeCacheManagerWrapper;
	private ESIDataProvider singleton;

	// - C O N S T R U C T O R S
	@Autowired
	public ESIDataProviderWrapper( final ConfigurationProviderWrapper configurationProvider,
	                               final FileSystemWrapper fileSystemAdapter,
	                               final RetrofitFactoryWrapper retrofitFactoryWrapper,
	                               final LocationCatalogServiceWrapper locationCatalogServiceWrapper,
	                               final StoreCacheManagerWrapper storeCacheManagerWrapper ) {
		this.configurationProvider = configurationProvider;
		this.fileSystemAdapter = fileSystemAdapter;
		this.retrofitFactoryWrapper = retrofitFactoryWrapper;
		this.locationCatalogServiceWrapper = locationCatalogServiceWrapper;
		this.storeCacheManagerWrapper = storeCacheManagerWrapper;
	}

	public ESIDataProvider getSingleton() {
		return Objects.requireNonNull( this.singleton );
	}

	@PostConstruct
	private void build() {
		this.singleton = new ESIDataProvider.Builder()
				.withConfigurationProvider( this.configurationProvider )
				.withFileSystemAdapter( this.fileSystemAdapter )
				.withRetrofitFactory( this.retrofitFactoryWrapper.getSingleton() )
				.withLocationCatalogService( this.locationCatalogServiceWrapper.getSingleton() )
				.withStoreCacheManager( this.storeCacheManagerWrapper.getSingleton() )
				.build();
	}
}
