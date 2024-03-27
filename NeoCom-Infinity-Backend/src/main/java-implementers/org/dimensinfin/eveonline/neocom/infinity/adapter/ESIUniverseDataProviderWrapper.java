package org.dimensinfin.eveonline.neocom.infinity.adapter;

import java.util.Objects;
import javax.annotation.PostConstruct;

import org.hibernate.engine.config.spi.ConfigurationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import org.dimensinfin.eveonline.neocom.infinity.adapter.implementers.SBConfigurationService;
import org.dimensinfin.eveonline.neocom.provider.ESIUniverseDataProvider;
import org.dimensinfin.eveonline.neocom.provider.IConfigurationService;

@Component
public class ESIUniverseDataProviderWrapper {
	private final IConfigurationService configurationService;
	private final FileSystemWrapper fileSystemAdapter;
	private final RetrofitFactoryWrapper retrofitFactoryWrapper;
	private final StoreCacheManagerWrapper storeCacheManagerWrapper;
	private ESIUniverseDataProvider singleton;

	// - C O N S T R U C T O R S
	@Autowired
	public ESIUniverseDataProviderWrapper( final ConfigurationServiceWrapper configurationServiceWrapper,
	                                       final FileSystemWrapper fileSystemAdapter,
	                                       final RetrofitFactoryWrapper retrofitFactoryWrapper,
	                                       final StoreCacheManagerWrapper storeCacheManagerWrapper ) {
		this.configurationService = configurationServiceWrapper.getSingleton();
		this.fileSystemAdapter = fileSystemAdapter;
		this.retrofitFactoryWrapper = retrofitFactoryWrapper;
		this.storeCacheManagerWrapper = storeCacheManagerWrapper;
	}

	public ESIUniverseDataProvider getSingleton() {
		return Objects.requireNonNull( this.singleton );
	}

	@PostConstruct
	private void build() {
		this.singleton = new ESIUniverseDataProvider.Builder()
				.withConfigurationProvider( this.configurationService )
				.withFileSystemAdapter( this.fileSystemAdapter )
				.withRetrofitFactory( this.retrofitFactoryWrapper.getSingleton() )
				.withStoreCacheManager( this.storeCacheManagerWrapper.getSingleton() )
				.build();
	}
}
