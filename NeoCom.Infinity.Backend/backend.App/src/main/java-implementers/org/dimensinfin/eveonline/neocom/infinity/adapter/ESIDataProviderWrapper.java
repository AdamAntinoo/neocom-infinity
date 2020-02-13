package org.dimensinfin.eveonline.neocom.infinity.adapter;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import org.dimensinfin.eveonline.neocom.provider.ESIDataProvider;

@Component
public class ESIDataProviderWrapper extends ESIDataProvider {
	// - C O N S T R U C T O R S
	@Autowired
	public ESIDataProviderWrapper( final ConfigurationProviderWrapper configurationProvider,
	                               final FileSystemWrapper fileSystemAdapter,
	                               final RetrofitFactoryWrapper retrofitFactory,
	                               final LocationCatalogServiceWrapper locationCatalogService ) {
		this.configurationProvider = configurationProvider;
		this.fileSystemAdapter = fileSystemAdapter;
		this.retrofitFactory=retrofitFactory;
		this.locationCatalogService = locationCatalogService;
	}

	@PostConstruct
	private void build() {
		new ESIDataProvider.Builder( this )
				.withConfigurationProvider( this.configurationProvider )
				.withFileSystemAdapter( this.fileSystemAdapter )
				.withRetrofitFactory( this.retrofitFactory )
				.withLocationCatalogService( this.locationCatalogService )
				.withStoreCacheManager(  )
				.build();
	}
}
