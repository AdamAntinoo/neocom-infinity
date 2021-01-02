package org.dimensinfin.eveonline.neocom.infinity.adapter;

import java.util.Objects;
import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import org.dimensinfin.eveonline.neocom.provider.ESIDataProvider;
import org.dimensinfin.eveonline.neocom.provider.IFileSystem;
import org.dimensinfin.eveonline.neocom.service.ESIDataService;

//@Component
public class ESIDataServiceWrapper {
	private final ConfigurationServiceWrapper configurationServiceWrapper;
	private final IFileSystem fileSystemAdapter;
	private final RetrofitFactoryWrapper retrofitFactoryWrapper;
	private final LocationCatalogServiceWrapper locationCatalogServiceWrapper;
	private final StoreCacheManagerWrapper storeCacheManagerWrapper;
	private ESIDataProvider singleton;
	private final ESIDataService esiDataService;

	// - C O N S T R U C T O R S
	@Autowired
	public ESIDataServiceWrapper( final ConfigurationServiceWrapper configurationServiceWrapper,
	                              final IFileSystem fileSystemAdapter,
	                              final RetrofitFactoryWrapper retrofitFactoryWrapper,
	                              final LocationCatalogServiceWrapper locationCatalogServiceWrapper,
	                              final StoreCacheManagerWrapper storeCacheManagerWrapper,
	                              final ESIDataService esiDataService ) {
		this.configurationServiceWrapper = configurationServiceWrapper;
		this.fileSystemAdapter = fileSystemAdapter;
		this.retrofitFactoryWrapper = retrofitFactoryWrapper;
		this.locationCatalogServiceWrapper = locationCatalogServiceWrapper;
		this.storeCacheManagerWrapper = storeCacheManagerWrapper;
		this.esiDataService = esiDataService;
	}

	// - G E T T E R S   &   S E T T E R S
	public ESIDataProvider getSingleton() {
		return Objects.requireNonNull( this.singleton );
	}

//	@PostConstruct
	protected void build() {
//		this.singleton = new ESIDataProvider( this.configurationServiceWrapper.getSingleton(),
//				this.fileSystemAdapter,
//				this.storeCacheManagerWrapper.getSingleton(),
//				this.retrofitFactoryWrapper.getSingleton()
////				this.locationCatalogServiceWrapper.getSingleton()
//		);
	}
}
