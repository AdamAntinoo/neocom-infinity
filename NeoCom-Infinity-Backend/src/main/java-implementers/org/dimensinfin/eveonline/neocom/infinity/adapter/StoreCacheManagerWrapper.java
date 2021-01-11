package org.dimensinfin.eveonline.neocom.infinity.adapter;

import java.util.Objects;
import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import org.dimensinfin.eveonline.neocom.adapter.StoreCacheManager;
import org.dimensinfin.eveonline.neocom.provider.IConfigurationService;
import org.dimensinfin.eveonline.neocom.provider.IFileSystem;
import org.dimensinfin.eveonline.neocom.provider.RetrofitFactory;

@Deprecated
@Component
public class StoreCacheManagerWrapper {
	private final IConfigurationService configurationService;
	private final IFileSystem fileSystemAdapter;
	private final RetrofitFactory retrofitFactory;
	private StoreCacheManager singleton;

	// - C O N S T R U C T O R S
	@Autowired
	public StoreCacheManagerWrapper( final IConfigurationService configurationService,
	                                 final IFileSystem fileSystemAdapter,
	                                 final RetrofitFactory retrofitFactory ) {
		this.configurationService = configurationService;
		this.fileSystemAdapter = fileSystemAdapter;
		this.retrofitFactory = retrofitFactory;
	}

	// - G E T T E R S   &   S E T T E R S
	public StoreCacheManager getSingleton() {
		return Objects.requireNonNull( this.singleton );
	}

	@PostConstruct
	void build() {
		this.singleton = new StoreCacheManager.Builder()
				.withConfigurationProvider( this.configurationService )
				.withFileSystemAdapter( this.fileSystemAdapter )
				.withRetrofitFactory( this.retrofitFactory )
				.build();
	}
}
