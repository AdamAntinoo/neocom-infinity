package org.dimensinfin.eveonline.neocom.infinity.adapter;

import java.util.Objects;
import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import org.dimensinfin.eveonline.neocom.adapter.StoreCacheManager;
import org.dimensinfin.eveonline.neocom.infinity.adapter.implementers.SBConfigurationService;

@Component
public class StoreCacheManagerWrapper {
	private final SBConfigurationService configurationService;
	private final FileSystemWrapper fileSystemAdapter;
	private final RetrofitFactoryWrapper retrofitFactoryWrapper;
	private StoreCacheManager singleton;

	// - C O N S T R U C T O R S
	@Autowired
	public StoreCacheManagerWrapper( final ConfigurationServiceWrapper configurationServiceWrapper,
	                                 final FileSystemWrapper fileSystemAdapter,
	                                 final RetrofitFactoryWrapper retrofitFactory ) {
		this.configurationService = configurationServiceWrapper.getSingleton();
		this.fileSystemAdapter = fileSystemAdapter;
		this.retrofitFactoryWrapper = retrofitFactory;
	}

	public StoreCacheManager getSingleton() {
		return Objects.requireNonNull( this.singleton );
	}

	@PostConstruct
	void build() {
		this.singleton = new StoreCacheManager.Builder()
				.withConfigurationProvider( this.configurationService )
				.withFileSystemAdapter( this.fileSystemAdapter )
				.withRetrofitFactory( this.retrofitFactoryWrapper.getSingleton() )
				.build();
	}
}
