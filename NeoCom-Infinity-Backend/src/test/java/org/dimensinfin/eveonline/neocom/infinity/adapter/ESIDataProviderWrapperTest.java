package org.dimensinfin.eveonline.neocom.infinity.adapter;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import org.dimensinfin.eveonline.neocom.adapter.LocationCatalogService;
import org.dimensinfin.eveonline.neocom.adapter.StoreCacheManager;
import org.dimensinfin.eveonline.neocom.infinity.service.SBConfigurationService;
import org.dimensinfin.eveonline.neocom.provider.RetrofitFactory;

public class ESIDataProviderWrapperTest {
	private ConfigurationServiceWrapper configurationServiceWrapper;
	private SBConfigurationService configurationService;
	private FileSystemWrapper fileSystemAdapter;
	private RetrofitFactoryWrapper retrofitFactoryWrapper;
	private RetrofitFactory retrofitFactory;
	private LocationCatalogServiceWrapper locationCatalogServiceWrapper;
	private LocationCatalogService locationCatalogService;
	private StoreCacheManagerWrapper storeCacheManagerWrapper;
	private StoreCacheManager storeCacheManager;

	@BeforeEach
	public void beforeEach() {
		// Given
		this.configurationServiceWrapper = Mockito.mock( ConfigurationServiceWrapper.class );
		this.configurationService = Mockito.mock( SBConfigurationService.class );
		this.fileSystemAdapter = Mockito.mock( FileSystemWrapper.class );
		this.retrofitFactoryWrapper = Mockito.mock( RetrofitFactoryWrapper.class );
		this.retrofitFactory = Mockito.mock( RetrofitFactory.class );
		this.locationCatalogServiceWrapper = Mockito.mock( LocationCatalogServiceWrapper.class );
		this.locationCatalogService = Mockito.mock( LocationCatalogService.class );
		this.storeCacheManagerWrapper = Mockito.mock( StoreCacheManagerWrapper.class );
		this.storeCacheManager = Mockito.mock( StoreCacheManager.class );
		// When
		Mockito.when( this.configurationServiceWrapper.getSingleton() ).thenReturn( this.configurationService );
		Mockito.when( this.retrofitFactoryWrapper.getSingleton() ).thenReturn( this.retrofitFactory );
		Mockito.when( this.locationCatalogServiceWrapper.getSingleton() ).thenReturn( this.locationCatalogService );
		Mockito.when( this.storeCacheManagerWrapper.getSingleton() ).thenReturn( this.storeCacheManager );
	}

	// TODO - This build test require many mock ups because the universe family initialization. Need to move it out.
//	@Test
	public void build() {
		final ESIDataProviderWrapper esiDataProviderWrapper = new ESIDataProviderWrapper(
				this.configurationServiceWrapper,
				this.fileSystemAdapter,
				this.retrofitFactoryWrapper,
				this.locationCatalogServiceWrapper,
				this.storeCacheManagerWrapper
		);
		esiDataProviderWrapper.build();
		Assertions.assertNotNull( esiDataProviderWrapper.getSingleton() );
	}

	@Test
	public void constructionContract() {
		// Tests
		final ESIDataProviderWrapper esiDataProviderWrapper = new ESIDataProviderWrapper(
				this.configurationServiceWrapper,
				this.fileSystemAdapter,
				this.retrofitFactoryWrapper,
				this.locationCatalogServiceWrapper,
				this.storeCacheManagerWrapper
		);
		Assertions.assertNotNull( esiDataProviderWrapper );
	}
}
