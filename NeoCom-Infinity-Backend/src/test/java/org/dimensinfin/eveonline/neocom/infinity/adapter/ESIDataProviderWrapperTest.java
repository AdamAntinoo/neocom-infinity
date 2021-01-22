package org.dimensinfin.eveonline.neocom.infinity.adapter;

import org.junit.jupiter.api.BeforeEach;
import org.mockito.Mockito;

import org.dimensinfin.eveonline.neocom.adapter.StoreCacheManager;
import org.dimensinfin.eveonline.neocom.infinity.service.SBConfigurationService;
import org.dimensinfin.eveonline.neocom.provider.IFileSystem;
import org.dimensinfin.eveonline.neocom.service.LocationCatalogService;
import org.dimensinfin.eveonline.neocom.service.RetrofitService;

public class ESIDataProviderWrapperTest {
	private ConfigurationServiceWrapper configurationServiceWrapper;
	private SBConfigurationService configurationService;
	private IFileSystem fileSystemAdapter;
	//	private RetrofitFactoryWrapper retrofitFactoryWrapper;
	private RetrofitService retrofitService;
	//	private LocationCatalogService locationCatalogServiceWrapper;
	private LocationCatalogService locationCatalogService;
	private StoreCacheManagerWrapper storeCacheManagerWrapper;
	private StoreCacheManager storeCacheManager;

	@BeforeEach
	public void beforeEach() {
		// Given
		this.configurationServiceWrapper = Mockito.mock( ConfigurationServiceWrapper.class );
		this.configurationService = Mockito.mock( SBConfigurationService.class );
		this.fileSystemAdapter = Mockito.mock( IFileSystem.class );
		//		this.retrofitFactoryWrapper = Mockito.mock( RetrofitFactoryWrapper.class );
		this.retrofitService = Mockito.mock( RetrofitService.class );
		this.locationCatalogService = Mockito.mock( LocationCatalogService.class );
		this.locationCatalogService = Mockito.mock( LocationCatalogService.class );
		this.storeCacheManagerWrapper = Mockito.mock( StoreCacheManagerWrapper.class );
		this.storeCacheManager = Mockito.mock( StoreCacheManager.class );
		// When
		Mockito.when( this.configurationServiceWrapper.getSingleton() ).thenReturn( this.configurationService );
		//		Mockito.when( this.retrofitFactoryWrapper.getSingleton() ).thenReturn( this.retrofitService );
		//		Mockito.when( this.locationCatalogServiceWrapper.getSingleton() ).thenReturn( this.locationCatalogService );
		Mockito.when( this.storeCacheManagerWrapper.getSingleton() ).thenReturn( this.storeCacheManager );
	}

	// TODO - This build test require many mock ups because the universe family initialization. Need to move it out.
	//	@Test
	//	public void build() {
	//		final ESIDataProviderWrapper esiDataProviderWrapper = new ESIDataProviderWrapper(
	//				this.configurationService,
	//				this.fileSystemAdapter,
	//				this.retrofitFactory,
	//				this.locationCatalogService,
	//				this.storeCacheManagerWrapper
	//		);
	//		esiDataProviderWrapper.build();
	//		Assertions.assertNotNull( esiDataProviderWrapper.getSingleton() );
	//	}
	//
	//	@Test
	//	public void constructionContract() {
	//		// Tests
	//		final ESIDataProviderWrapper esiDataProviderWrapper = new ESIDataProviderWrapper(
	//				this.configurationService,
	//				this.fileSystemAdapter,
	//				this.retrofitFactoryWrapper,
	//				this.locationCatalogService,
	//				this.storeCacheManagerWrapper
	//		);
	//		Assertions.assertNotNull( esiDataProviderWrapper );
	//	}
}
