package org.dimensinfin.eveonline.neocom.infinity.adapter;

import java.util.Objects;
import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import org.dimensinfin.eveonline.neocom.provider.ESIUniverseDataProvider;
import org.dimensinfin.eveonline.neocom.provider.IConfigurationService;
import org.dimensinfin.eveonline.neocom.provider.IFileSystem;
import org.dimensinfin.eveonline.neocom.service.RetrofitService;

@Component
public class ESIUniverseDataProviderWrapper {
	private final IConfigurationService configurationService;
	private final IFileSystem fileSystemAdapter;
	private final RetrofitService retrofitService;
	private final StoreCacheManagerWrapper storeCacheManagerWrapper;
	private ESIUniverseDataProvider singleton;

	// - C O N S T R U C T O R S
	@Autowired
	public ESIUniverseDataProviderWrapper( final ConfigurationServiceWrapper configurationServiceWrapper,
	                                       final IFileSystem fileSystemAdapter,
	                                       final RetrofitService retrofitService,
	                                       final StoreCacheManagerWrapper storeCacheManagerWrapper ) {
		this.configurationService = configurationServiceWrapper.getSingleton();
		this.fileSystemAdapter = fileSystemAdapter;
		this.retrofitService = retrofitService;
		this.storeCacheManagerWrapper = storeCacheManagerWrapper;
	}

// - G E T T E R S   &   S E T T E R S
	public ESIUniverseDataProvider getSingleton() {
		return Objects.requireNonNull( this.singleton );
	}

	@PostConstruct
	private void build() {
		this.singleton = new ESIUniverseDataProvider.Builder()
				.withConfigurationProvider( this.configurationService )
				.withFileSystemAdapter( this.fileSystemAdapter )
				.withRetrofitFactory( this.retrofitService )
				.withStoreCacheManager( this.storeCacheManagerWrapper.getSingleton() )
				.build();
	}
}
