package org.dimensinfin.eveonline.neocom.infinity.config;

import com.google.inject.AbstractModule;
import com.google.inject.Scopes;
import com.google.inject.Singleton;
import com.google.inject.name.Names;

import org.dimensinfin.eveonline.neocom.database.NeoComDatabaseService;
import org.dimensinfin.eveonline.neocom.database.core.ISDEDatabaseService;
import org.dimensinfin.eveonline.neocom.database.repositories.SDERepository;
import org.dimensinfin.eveonline.neocom.infinity.adapter.implementers.SBConfigurationService;
import org.dimensinfin.eveonline.neocom.infinity.adapter.implementers.SBFileSystemAdapter;
import org.dimensinfin.eveonline.neocom.infinity.adapter.implementers.SBNeoComDBAdapter;
import org.dimensinfin.eveonline.neocom.infinity.backend.sde.service.SBSDEDatabaseService;
import org.dimensinfin.eveonline.neocom.provider.IConfigurationService;
import org.dimensinfin.eveonline.neocom.provider.IFileSystem;
import org.dimensinfin.eveonline.neocom.service.ESIDataService;
import org.dimensinfin.eveonline.neocom.service.IStoreCache;
import org.dimensinfin.eveonline.neocom.service.MemoryStoreCacheService;
import org.dimensinfin.eveonline.neocom.service.ResourceFactory;
import org.dimensinfin.eveonline.neocom.service.RetrofitService;

public class NeoComInfinityBackendDependenciesModule extends AbstractModule {
	private static final String ENV_PROPERTIES_DIRECTORY = "PROPERTIES_DIRECTORY";
	private static final String ENV_APPLICATION_DIRECTORY = "APPLICATION_DIRECTORY";
	private static final String ENV_SDE_DATABASE = "SDE_DATABASE_PATH";
	private static final String DEFAULT_PROPERTIES_DIRECTORY = "/src/integration/resources/properties";
	private static final String DEFAULT_APPLICATION_DIRECTORY = "appDir";
	private static final String DEFAULT_SDE_DATABASE = "sde.db";

	@Override
	protected void configure() {
		// Bind configuration environment defined settings.
		String propDirectory = System.getProperty( ENV_PROPERTIES_DIRECTORY );
		String appDirectory = System.getProperty( ENV_APPLICATION_DIRECTORY );
		String sdeDatabasePath = System.getProperty( ENV_SDE_DATABASE );
		if (null == propDirectory) propDirectory = "/src/integration/resources/properties";
		if (null == appDirectory) appDirectory = "appDir";
		if (null == sdeDatabasePath) sdeDatabasePath = "./build/resources/main/sde.db";
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
		bind( IStoreCache.class )
				.annotatedWith( Names.named( "IStoreCache" ) )
				.to( MemoryStoreCacheService.class )
				.in( Singleton.class );
		bind( RetrofitService.class )
				.annotatedWith( Names.named( "RetrofitService" ) )
				.to( RetrofitService.class )
				.in( Singleton.class );
		bind( ESIDataService.class )
				.annotatedWith( Names.named( "ESIDataService" ) )
				.to( ESIDataService.class )
				.in( Singleton.class );
		bind( ISDEDatabaseService.class )
				.annotatedWith( Names.named( "ISDEDatabaseService" ) )
				.to( SBSDEDatabaseService.class )
				.in( Singleton.class );
		bind( ResourceFactory.class )
				.annotatedWith( Names.named( "ResourceFactory" ) )
				.to( ResourceFactory.class )
				.in( Singleton.class );
//		bind( SDERepository.class )
//				.annotatedWith( Names.named( "SDERepository" ) )
//				.to( SDERepository.class )
//				.in( Singleton.class );
	}
}