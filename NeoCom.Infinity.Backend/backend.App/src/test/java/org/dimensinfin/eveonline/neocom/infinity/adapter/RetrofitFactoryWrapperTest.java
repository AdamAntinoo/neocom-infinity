package org.dimensinfin.eveonline.neocom.infinity.adapter;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class RetrofitFactoryWrapperTest {
	@Test
	public void constructionConstract() {
		final ConfigurationProviderWrapper configurationProvider = Mockito.mock(ConfigurationProviderWrapper.class);
		final FileSystemWrapper fileSystemAdapter = Mockito.mock(FileSystemWrapper.class);
		final RetrofitFactoryWrapper retrofitFactoryWrapper = new RetrofitFactoryWrapper(configurationProvider,
				fileSystemAdapter);
		Assertions.assertNotNull( retrofitFactoryWrapper );
	}
}
