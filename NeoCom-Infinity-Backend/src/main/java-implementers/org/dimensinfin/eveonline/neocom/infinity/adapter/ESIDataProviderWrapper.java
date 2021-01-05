package org.dimensinfin.eveonline.neocom.infinity.adapter;

import java.util.Objects;
import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import org.dimensinfin.eveonline.neocom.provider.ESIDataProvider;
import org.dimensinfin.eveonline.neocom.provider.IConfigurationService;
import org.dimensinfin.eveonline.neocom.provider.IFileSystem;
import org.dimensinfin.eveonline.neocom.service.LocationCatalogService;
import org.dimensinfin.eveonline.neocom.service.RetrofitService;

@Component
public class ESIDataProviderWrapper {
//	private final ConfigurationServiceWrapper configurationServiceWrapper;
	private final IConfigurationService configurationService;
	private final IFileSystem fileSystemAdapter;
//	private final RetrofitFactoryWrapper retrofitFactoryWrapper;
	private final RetrofitService retrofitService;
	private final LocationCatalogService locationCatalogService;
	private final StoreCacheManagerWrapper storeCacheManagerWrapper;
	private ESIDataProvider singleton;

	// - C O N S T R U C T O R S
	@Autowired
	public ESIDataProviderWrapper( final IConfigurationService configurationService,
	                               final IFileSystem fileSystemAdapter,
	                               final RetrofitService retrofitService,
	                               final LocationCatalogService locationCatalogService,
	                               final StoreCacheManagerWrapper storeCacheManagerWrapper ) {
//		this.configurationServiceWrapper = configurationServiceWrapper;
		this.configurationService=configurationService;
		this.fileSystemAdapter = fileSystemAdapter;
//		this.retrofitFactoryWrapper = retrofitFactoryWrapper;
		this.retrofitService=retrofitService;
		this.locationCatalogService = locationCatalogService;
		this.storeCacheManagerWrapper = storeCacheManagerWrapper;
	}

	public ESIDataProvider getSingleton() {
		return Objects.requireNonNull( this.singleton );
	}

	@PostConstruct
	protected void build() {
		this.singleton = new ESIDataProvider.Builder()
				.withConfigurationProvider( this.configurationService )
				.withFileSystemAdapter( this.fileSystemAdapter )
				.withRetrofitFactory( this.retrofitService )
				.withLocationCatalogService( this.locationCatalogService )
				.withStoreCacheManager( this.storeCacheManagerWrapper.getSingleton() )
				.build();
	}
}
