package org.dimensinfin.eveonline.neocom.infinity.adapter;

import java.util.Objects;
import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import org.dimensinfin.eveonline.neocom.adapter.StoreCacheManager;

@Component
public class StoreCacheManagerWrapper {
	private final ConfigurationProviderWrapper configurationProvider;
	private final FileSystemWrapper fileSystemAdapter;
	private final RetrofitFactoryWrapper retrofitFactoryWrapper;
	private StoreCacheManager singleton;

	// - C O N S T R U C T O R S
	@Autowired
	public StoreCacheManagerWrapper( final ConfigurationProviderWrapper configurationProvider,
	                                 final FileSystemWrapper fileSystemAdapter,
	                                 final RetrofitFactoryWrapper retrofitFactory ) {
		this.configurationProvider = configurationProvider;
		this.fileSystemAdapter = fileSystemAdapter;
		this.retrofitFactoryWrapper = retrofitFactory;
	}

	public StoreCacheManager getSingleton() {
		return Objects.requireNonNull( this.singleton );
	}

	@PostConstruct
	private void build() {
		this.singleton = new StoreCacheManager.Builder()
				.withConfigurationProvider( this.configurationProvider )
				.withFileSystemAdapter( this.fileSystemAdapter )
				.withRetrofitFactory( this.retrofitFactoryWrapper.getSingleton() )
				.build();
	}
}
