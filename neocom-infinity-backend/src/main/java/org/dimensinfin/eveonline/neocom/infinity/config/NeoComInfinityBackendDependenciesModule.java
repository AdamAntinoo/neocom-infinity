package org.dimensinfin.eveonline.neocom.infinity.config;

import com.google.inject.AbstractModule;
import com.google.inject.Singleton;
import com.google.inject.name.Names;

import org.dimensinfin.eveonline.neocom.database.DMDatabaseDependenciesModule;
import org.dimensinfin.eveonline.neocom.database.NeoComDatabaseService;
import org.dimensinfin.eveonline.neocom.database.core.ISDEDatabaseService;
import org.dimensinfin.eveonline.neocom.database.repositories.AssetRepository;
import org.dimensinfin.eveonline.neocom.database.repositories.CredentialRepository;
import org.dimensinfin.eveonline.neocom.database.repositories.PilotPreferencesRepository;
import org.dimensinfin.eveonline.neocom.infinity.infrastructure.adapters.outbound.datastore.RedisDataStoreImplementation;
import org.dimensinfin.eveonline.neocom.infinity.service.SBConfigurationService;
import org.dimensinfin.eveonline.neocom.infinity.service.SBFileSystemService;
import org.dimensinfin.eveonline.neocom.infinity.service.SBNeoComDBService;
import org.dimensinfin.eveonline.neocom.infinity.service.SBSDEDatabaseService;
import org.dimensinfin.eveonline.neocom.loyalty.persistence.LoyaltyOffersRepository;
import org.dimensinfin.eveonline.neocom.provider.IConfigurationService;
import org.dimensinfin.eveonline.neocom.provider.IFileSystem;
import org.dimensinfin.eveonline.neocom.service.DMServicesDependenciesModule;
import org.dimensinfin.eveonline.neocom.ports.IDataStorePort;
import org.dimensinfin.eveonline.neocom.service.IStoreCache;
import org.dimensinfin.eveonline.neocom.service.MemoryStoreCacheService;

public class NeoComInfinityBackendDependenciesModule extends AbstractModule {
	private static final String ENV_PROPERTIES_DIRECTORY = "PROPERTIES_DIRECTORY";
	private static final String ENV_APPLICATION_DIRECTORY = "APPLICATION_DIRECTORY";
	private static final String ENV_SDE_DATABASE = "SDE_DATABASE_PATH";
	private static final String ENV_NEOCOM_DATABASE_URL = "NEOCOM_DATABASE_URL";
	public static final String ENV_REDIS_DATABASE_URL = "REDIS_URL";
	private static final String DEFAULT_PROPERTIES_DIRECTORY = "/build/resources/main/properties";
	private static final String DEFAULT_APPLICATION_DIRECTORY = "./NeoCom.Infinity";
	private static final String DEFAULT_SDE_DATABASE = "/build/resources/main/sde.db";
	private static final String DEFAULT_NEOCOM_DATABASE_URL = "jdbc:postgresql://localhost:5432/postgres?user=adamantinoo&password=z.iliada.2020";
	private static final String DEFAULT_REDIS_DATABASE_URL = "redis://localhost:6379";

	@Override
	protected void configure() {
		// Bind configuration environment defined settings.
		String propDirectory = System.getenv( ENV_PROPERTIES_DIRECTORY );
		String appDirectory = System.getenv( ENV_APPLICATION_DIRECTORY );
		String sdeDatabasePath = System.getenv( ENV_SDE_DATABASE );
		String neoComDatabaseUrl = System.getenv( ENV_NEOCOM_DATABASE_URL );
		String redisDatabaseUrl = System.getenv( ENV_REDIS_DATABASE_URL );
		if (null == propDirectory) propDirectory = DEFAULT_PROPERTIES_DIRECTORY;
		if (null == appDirectory) appDirectory = DEFAULT_APPLICATION_DIRECTORY;
		if (null == sdeDatabasePath) sdeDatabasePath = DEFAULT_SDE_DATABASE;
		if (null == neoComDatabaseUrl) neoComDatabaseUrl = DEFAULT_NEOCOM_DATABASE_URL;
		if (null == redisDatabaseUrl) redisDatabaseUrl = DEFAULT_REDIS_DATABASE_URL;
		this.bind( String.class )
				.annotatedWith( Names.named( "PropertiesDirectory" ) )
				.toInstance( propDirectory );
		this.bind( String.class )
				.annotatedWith( Names.named( "ApplicationDirectory" ) )
				.toInstance( appDirectory );
		this.bind( String.class )
				.annotatedWith( Names.named( "SDEDatabasePath" ) )
				.toInstance( sdeDatabasePath );
		this.bind( String.class )
				.annotatedWith( Names.named( "NeoComDatabaseUrl" ) )
				.toInstance( neoComDatabaseUrl );
		this.bind( String.class )
				.annotatedWith( Names.named( DMServicesDependenciesModule.REDIS_DATABASE_URL ) )
				.toInstance( redisDatabaseUrl );

		// Bind platform specific implementations.
		this.bind( IConfigurationService.class )
				.annotatedWith( Names.named( DMServicesDependenciesModule.ICONFIGURATION_SERVICE ) )
				.to( SBConfigurationService.class )
				.in( Singleton.class );
		this.bind( IFileSystem.class )
				.annotatedWith( Names.named( DMServicesDependenciesModule.IFILE_SYSTEM ) )
				.to( SBFileSystemService.class )
				.in( Singleton.class );
		this.bind( IStoreCache.class )
				.annotatedWith( Names.named( DMServicesDependenciesModule.ISTORE_CACHE ) )
				.to( MemoryStoreCacheService.class )
				.in( Singleton.class );

		// Bind database services
		this.bind( ISDEDatabaseService.class )
				.annotatedWith( Names.named( "ISDEDatabaseService" ) )
				.to( SBSDEDatabaseService.class )
				.in( Singleton.class );
		this.bind( NeoComDatabaseService.class )
				.annotatedWith( Names.named( DMServicesDependenciesModule.INEOCOM_DATABASE_SERVICE ) )
				.to( SBNeoComDBService.class )
				.in( Singleton.class );
		this.bind( CredentialRepository.class )
				.annotatedWith( Names.named( "CredentialRepository" ) )
				.to( CredentialRepository.class )
				.in( Singleton.class );
		this.bind( AssetRepository.class )
				.annotatedWith( Names.named( "AssetRepository" ) )
				.to( AssetRepository.class )
				.in( Singleton.class );
		this.bind( LoyaltyOffersRepository.class )
				.annotatedWith( Names.named( DMDatabaseDependenciesModule.LOYALTYOFFERS_REPOSITORY ) )
				.to( LoyaltyOffersRepository.class )
				.in( Singleton.class );
		this.bind( PilotPreferencesRepository.class )
				.annotatedWith( Names.named( "PilotPreferencesRepository" ) )
				.to( PilotPreferencesRepository.class )
				.in( Singleton.class );

		this.bind( IDataStorePort.class )
				.annotatedWith( Names.named( DMServicesDependenciesModule.IDATA_STORE ) )
				.to( RedisDataStoreImplementation.class )
				.in( Singleton.class );
	}
}