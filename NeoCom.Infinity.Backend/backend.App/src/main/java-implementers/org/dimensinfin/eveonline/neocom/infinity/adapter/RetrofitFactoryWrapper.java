package org.dimensinfin.eveonline.neocom.infinity.adapter;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import org.dimensinfin.eveonline.neocom.provider.RetrofitFactory;

@Component
public class RetrofitFactoryWrapper extends RetrofitFactory {
	// - C O N S T R U C T O R S
	@Autowired
	public RetrofitFactoryWrapper( final ConfigurationProviderWrapper configurationProvider,
	                               final FileSystemWrapper fileSystemAdapter ) {
		this.configurationProvider = configurationProvider;
		this.fileSystemAdapter = fileSystemAdapter;
	}

	@PostConstruct
	private void build() {
		new RetrofitFactory.Builder()
				.withConfigurationProvider( this.configurationProvider )
				.withFileSystemAdapter( this.fileSystemAdapter )
				.build();
	}
}
