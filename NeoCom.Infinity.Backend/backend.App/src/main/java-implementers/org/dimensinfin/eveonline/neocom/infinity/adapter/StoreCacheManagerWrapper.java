package org.dimensinfin.eveonline.neocom.infinity.adapter;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import org.dimensinfin.eveonline.neocom.adapter.StoreCacheManager;

@Component
public class StoreCacheManagerWrapper extends StoreCacheManager {
	// - C O N S T R U C T O R S
	@Autowired
	public StoreCacheManagerWrapper( final ConfigurationProviderWrapper configurationProvider,
	                                 final FileSystemWrapper fileSystemAdapter,
	                                 final RetrofitFactoryWrapper retrofitFactory ) {
		this.configurationProvider = configurationProvider;
		this.fileSystemAdapter = fileSystemAdapter;
		this.retrofitFactory = retrofitFactory;
	}

	@PostConstruct
	private void build() {
		new StoreCacheManager.Builder()
				.withConfigurationProvider( this.configurationProvider )
				.withFileSystemAdapter( this.fileSystemAdapter )
				.withRetrofitFactory( this.retrofitFactory )
				.build();
	}
}
