package org.dimensinfin.eveonline.neocom.infinity.adapter;

import java.util.Objects;
import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import org.dimensinfin.eveonline.neocom.provider.IConfigurationService;
import org.dimensinfin.eveonline.neocom.provider.IFileSystem;
import org.dimensinfin.eveonline.neocom.provider.RetrofitFactory;

//@Component
public class RetrofitFactoryWrapper {
	private final IConfigurationService configurationService;
	private final IFileSystem fileSystemAdapter;
	private RetrofitFactory singleton;

	// - C O N S T R U C T O R S
	@Autowired
	public RetrofitFactoryWrapper( final IConfigurationService configurationServiceWrapper,
	                               final IFileSystem fileSystemAdapter ) {
		this.configurationService = configurationServiceWrapper;
		this.fileSystemAdapter = fileSystemAdapter;
	}

	public RetrofitFactory getSingleton() {
		return Objects.requireNonNull( this.singleton );
	}

	@PostConstruct
	void build() {
//		this.singleton = new RetrofitFactory.Builder()
//				.withConfigurationProvider( this.configurationService )
//				.withFileSystemAdapter( this.fileSystemAdapter )
//				.build();
	}
}
