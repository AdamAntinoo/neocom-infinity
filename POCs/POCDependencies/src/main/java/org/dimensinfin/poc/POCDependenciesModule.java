package org.dimensinfin.poc;

import com.google.inject.AbstractModule;
import com.google.inject.Singleton;
import com.google.inject.name.Names;

import org.dimensinfin.eveonline.neocom.provider.IConfigurationService;
import org.dimensinfin.eveonline.neocom.provider.IFileSystem;
import org.dimensinfin.eveonline.neocom.service.ESIDataService;
import org.dimensinfin.eveonline.neocom.service.IStoreCache;
import org.dimensinfin.eveonline.neocom.service.MemoryStoreCacheService;
import org.dimensinfin.eveonline.neocom.service.RetrofitService;
import org.dimensinfin.poc.service.SBConfigurationService;
import org.dimensinfin.poc.service.SBFileSystemAdapter;

public class POCDependenciesModule extends AbstractModule {
	@Override
	protected void configure() {
		String propDirectory = System.getProperty( "ENV_PROPERTIES_DIRECTORY" );
		String appDirectory = System.getProperty( "ENV_APPLICATION_DIRECTORY" );
		String sdeDatabasePath = System.getProperty( "ENV_SDE_DATABASE" );
		if (null == propDirectory) propDirectory = "/src/integration/resources/properties";
		if (null == appDirectory) appDirectory = "appDir";
		if (null == sdeDatabasePath) sdeDatabasePath = "/src/integration/resources/sde.db";
		bind( String.class )
				.annotatedWith( Names.named( "PropertiesDirectory" ) )
				.toInstance( propDirectory );
		bind( String.class )
				.annotatedWith( Names.named( "ApplicationDirectory" ) )
				.toInstance( appDirectory );
		bind( String.class )
				.annotatedWith( Names.named( "SDEDatabasePath" ) )
				.toInstance( sdeDatabasePath );

		// Bind platform specific implementations.
		bind( IConfigurationService.class )
				.annotatedWith( Names.named( "IConfigurationService" ) )
				.to( SBConfigurationService.class )
				.in( Singleton.class );
		bind( IFileSystem.class )
				.annotatedWith( Names.named( "IFileSystem" ) )
				.to( SBFileSystemAdapter.class )
				.in( Singleton.class );

		// Bind DM services until this is declared on the DM library.
		bind( RetrofitService.class )
				.annotatedWith( Names.named( "RetrofitService" ) )
				.to( RetrofitService.class )
				.in( Singleton.class );
		bind( IStoreCache.class )
				.annotatedWith( Names.named( "IStoreCache" ) )
				.to( MemoryStoreCacheService.class )
				.in( Singleton.class );
		bind( ESIDataService.class )
				.annotatedWith( Names.named( "ESIDataService" ) )
				.to( ESIDataService.class )
				.in( Singleton.class );
	}
}