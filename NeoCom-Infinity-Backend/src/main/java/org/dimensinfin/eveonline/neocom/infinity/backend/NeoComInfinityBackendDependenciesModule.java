package org.dimensinfin.eveonline.neocom.infinity.backend;

import com.google.inject.AbstractModule;
import com.google.inject.name.Names;

import org.dimensinfin.eveonline.neocom.adapter.LocationCatalogService;
import org.dimensinfin.eveonline.neocom.adapter.StoreCacheManager;
import org.dimensinfin.eveonline.neocom.infinity.adapter.implementers.SBConfigurationService;
import org.dimensinfin.eveonline.neocom.infinity.adapter.implementers.SBFileSystemAdapter;
import org.dimensinfin.eveonline.neocom.provider.IConfigurationService;
import org.dimensinfin.eveonline.neocom.provider.IFileSystem;
import org.dimensinfin.eveonline.neocom.provider.RetrofitFactory;
import org.dimensinfin.eveonline.neocom.service.ESIDataService;

public class NeoComInfinityBackendDependenciesModule extends AbstractModule {

	@Override
	protected void configure() {
		bind( IConfigurationService.class )
				.annotatedWith( Names.named( "IConfigurationService" ) )
				.to( SBConfigurationService.class );
		bind( IFileSystem.class )
				.annotatedWith( Names.named( "IFileSystem" ) )
				.to( SBFileSystemAdapter.class );
		bind( StoreCacheManager.class )
				.annotatedWith( Names.named( "StoreCacheManager" ) )
				.to( StoreCacheManager.class );
		bind( RetrofitFactory.class )
				.annotatedWith( Names.named( "RetrofitFactory" ) )
				.to( RetrofitFactory.class );
		bind( ESIDataService.class )
				.annotatedWith( Names.named( "ESIDataService" ) )
				.to( ESIDataService.class );
		bind( LocationCatalogService.class )
				.annotatedWith( Names.named( "LocationCatalogService" ) )
				.to( LocationCatalogService.class );
	}
}