package org.dimensinfin.eveonline.neocom.infinity.adapter;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import org.dimensinfin.eveonline.neocom.infinity.service.SBConfigurationService;
import org.dimensinfin.eveonline.neocom.provider.RetrofitFactory;

public class StoreCacheManagerWrapperTest {
	@Test
	public void build() {
		// Given
		final ConfigurationServiceWrapper configurationServiceWrapper = Mockito.mock( ConfigurationServiceWrapper.class );
		final SBConfigurationService configurationService = Mockito.mock( SBConfigurationService.class );
		final FileSystemWrapper fileSystemAdapter = new FileSystemWrapper( "NeoCom.Infinity.unittesting" );
		final RetrofitFactoryWrapper retrofitFactoryWrapper = Mockito.mock( RetrofitFactoryWrapper.class );
		final RetrofitFactory retrofitFactory = Mockito.mock( RetrofitFactory.class );
		// When
		Mockito.when( configurationServiceWrapper.getSingleton() ).thenReturn( configurationService );
		Mockito.when( retrofitFactoryWrapper.getSingleton() ).thenReturn( retrofitFactory );

		final StoreCacheManagerWrapper storeCacheManagerWrapper = new StoreCacheManagerWrapper(
				configurationServiceWrapper,
				fileSystemAdapter,
				retrofitFactoryWrapper );
		Assertions.assertNotNull( storeCacheManagerWrapper );

		storeCacheManagerWrapper.build();
		Assertions.assertNotNull( storeCacheManagerWrapper.getSingleton() );
	}

	@Test
	public void constructionContract() {
		final ConfigurationServiceWrapper configurationProvider = Mockito.mock( ConfigurationServiceWrapper.class );
		final FileSystemWrapper fileSystemAdapter = new FileSystemWrapper( "NeoCom.Infinity.unittesting" );
		final RetrofitFactoryWrapper retrofitFactoryWrapper = Mockito.mock( RetrofitFactoryWrapper.class );

		final StoreCacheManagerWrapper storeCacheManagerWrapper = new StoreCacheManagerWrapper(
				configurationProvider,
				fileSystemAdapter,
				retrofitFactoryWrapper );
		Assertions.assertNotNull( storeCacheManagerWrapper );
	}

}
