package org.dimensinfin.eveonline.neocom.infinity.adapter;

import java.util.Objects;
import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import org.dimensinfin.eveonline.neocom.infinity.adapter.implementers.SBConfigurationService;
import org.dimensinfin.eveonline.neocom.provider.ESIDataProvider;
import org.dimensinfin.eveonline.neocom.provider.IFileSystem;

@Component
public class ESIDataProviderWrapper {
	private final ConfigurationServiceWrapper configurationServiceWrapper;
	private final IFileSystem fileSystemAdapter;
	private final RetrofitFactoryWrapper retrofitFactoryWrapper;
	private final LocationCatalogServiceWrapper locationCatalogServiceWrapper;
	private final StoreCacheManagerWrapper storeCacheManagerWrapper;
	private ESIDataProvider singleton;

	// - C O N S T R U C T O R S
	@Autowired
	public ESIDataProviderWrapper( final ConfigurationServiceWrapper configurationServiceWrapper,
	                               final IFileSystem fileSystemAdapter,
	                               final RetrofitFactoryWrapper retrofitFactoryWrapper,
	                               final LocationCatalogServiceWrapper locationCatalogServiceWrapper,
	                               final StoreCacheManagerWrapper storeCacheManagerWrapper ) {
		this.configurationServiceWrapper = configurationServiceWrapper;
		this.fileSystemAdapter = fileSystemAdapter;
		this.retrofitFactoryWrapper = retrofitFactoryWrapper;
		this.locationCatalogServiceWrapper = locationCatalogServiceWrapper;
		this.storeCacheManagerWrapper = storeCacheManagerWrapper;
	}

	public ESIDataProvider getSingleton() {
		return Objects.requireNonNull( this.singleton );
	}

	@PostConstruct
	protected void build() {
		this.singleton = new ESIDataProvider.Builder()
				.withConfigurationProvider( this.configurationServiceWrapper.getSingleton() )
				.withFileSystemAdapter( this.fileSystemAdapter )
				.withRetrofitFactory( this.retrofitFactoryWrapper.getSingleton() )
				.withLocationCatalogService( this.locationCatalogServiceWrapper.getSingleton() )
				.withStoreCacheManager( this.storeCacheManagerWrapper.getSingleton() )
				.build();
	}
}
