package org.dimensinfin.eveonline.neocom.infinity.adapter;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import org.dimensinfin.eveonline.neocom.adapter.StoreCacheManager;
import org.dimensinfin.eveonline.neocom.infinity.adapter.implementers.SBConfigurationService;
import org.dimensinfin.eveonline.neocom.provider.ESIUniverseDataProvider;
import org.dimensinfin.eveonline.neocom.provider.RetrofitFactory;

public class LocationCatalogServiceWrapperTest {
	@Test
	public void build() {
		// Given
		final ConfigurationServiceWrapper configurationService = Mockito.mock( ConfigurationServiceWrapper.class );
		final SBConfigurationService configuration = Mockito.mock( SBConfigurationService.class );
		final FileSystemWrapper fileSystemAdapter = Mockito.mock( FileSystemWrapper.class );
		final ESIUniverseDataProviderWrapper esiUniverseDataProviderWrapper = Mockito.mock( ESIUniverseDataProviderWrapper.class );
		final ESIUniverseDataProvider esiUniverseDataProvider = Mockito.mock( ESIUniverseDataProvider.class );
		final RetrofitFactoryWrapper retrofitFactoryWrapper = Mockito.mock( RetrofitFactoryWrapper.class );
		final RetrofitFactory retrofitFactory = Mockito.mock( RetrofitFactory.class );
		final StoreCacheManagerWrapper storeCacheManagerWrapper = Mockito.mock( StoreCacheManagerWrapper.class );
		final StoreCacheManager storeCacheManager = Mockito.mock( StoreCacheManager.class );
		// When
		Mockito.when( configurationService.getSingleton() ).thenReturn( configuration );
		Mockito.when( esiUniverseDataProviderWrapper.getSingleton() ).thenReturn( esiUniverseDataProvider );
		Mockito.when( retrofitFactoryWrapper.getSingleton() ).thenReturn( retrofitFactory );
		Mockito.when( storeCacheManagerWrapper.getSingleton() ).thenReturn( storeCacheManager );
		// test
		final LocationCatalogServiceWrapper locationCatalogServiceWrapper = new LocationCatalogServiceWrapper(
				configurationService,
				fileSystemAdapter,
				esiUniverseDataProviderWrapper,
				retrofitFactoryWrapper,
				storeCacheManagerWrapper );
		// Asserts
		Assertions.assertNotNull( locationCatalogServiceWrapper );
		locationCatalogServiceWrapper.build();
		Assertions.assertNotNull( locationCatalogServiceWrapper.getSingleton() );
	}

	@Test
	public void constructionContract() {
		final ConfigurationServiceWrapper configurationProvider = Mockito.mock( ConfigurationServiceWrapper.class );
		final FileSystemWrapper fileSystemAdapter = Mockito.mock( FileSystemWrapper.class );
		final ESIUniverseDataProviderWrapper esiUniverseDataProviderWrapper = Mockito.mock( ESIUniverseDataProviderWrapper.class );
		final RetrofitFactoryWrapper retrofitFactoryWrapper = Mockito.mock( RetrofitFactoryWrapper.class );
		final StoreCacheManagerWrapper storeCacheManagerWrapper = Mockito.mock( StoreCacheManagerWrapper.class );

		final LocationCatalogServiceWrapper locationCatalogServiceWrapper = new LocationCatalogServiceWrapper(
				configurationProvider,
				fileSystemAdapter,
				esiUniverseDataProviderWrapper,
				retrofitFactoryWrapper,
				storeCacheManagerWrapper );
		Assertions.assertNotNull( locationCatalogServiceWrapper );
	}
}
