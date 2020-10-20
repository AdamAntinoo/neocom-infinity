package org.dimensinfin.eveonline.neocom.infinity.adapter;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import org.dimensinfin.eveonline.neocom.infinity.adapter.implementers.SBConfigurationService;

public class RetrofitFactoryWrapperTest {
	@Test
	public void build() {
		final ConfigurationServiceWrapper configurationServiceWrapper = Mockito.mock( ConfigurationServiceWrapper.class );
		final SBConfigurationService configurationService = Mockito.mock( SBConfigurationService.class );
		final FileSystemWrapper fileSystemAdapter = Mockito.mock( FileSystemWrapper.class );
		// When
		Mockito.when( configurationServiceWrapper.getSingleton() ).thenReturn( configurationService );
		// Test
		final RetrofitFactoryWrapper retrofitFactoryWrapper = new RetrofitFactoryWrapper(
				configurationServiceWrapper,
				fileSystemAdapter );
		retrofitFactoryWrapper.build();
		// Assertions
		Assertions.assertNotNull( retrofitFactoryWrapper.getSingleton() );
	}

	@Test
	public void constructionContract() {
		final ConfigurationServiceWrapper configurationProvider = Mockito.mock( ConfigurationServiceWrapper.class );
		final FileSystemWrapper fileSystemAdapter = Mockito.mock( FileSystemWrapper.class );
		final RetrofitFactoryWrapper retrofitFactoryWrapper = new RetrofitFactoryWrapper( configurationProvider,
				fileSystemAdapter );
		Assertions.assertNotNull( retrofitFactoryWrapper );
	}
}
