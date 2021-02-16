package org.dimensinfin.eveonline.neocom.infinity;

import com.google.inject.AbstractModule;
import com.google.inject.Singleton;
import com.google.inject.name.Names;

import org.dimensinfin.eveonline.neocom.character.service.CharacterService;
import org.dimensinfin.eveonline.neocom.database.DMDatabaseDependenciesModule;
import org.dimensinfin.eveonline.neocom.database.NeoComDatabaseService;
import org.dimensinfin.eveonline.neocom.database.core.ISDEDatabaseService;
import org.dimensinfin.eveonline.neocom.database.repositories.AssetRepository;
import org.dimensinfin.eveonline.neocom.database.repositories.CredentialRepository;
import org.dimensinfin.eveonline.neocom.infinity.adapter.implementers.SBFileSystemAdapter;
import org.dimensinfin.eveonline.neocom.infinity.adapter.implementers.SBNeoComDBAdapter;
import org.dimensinfin.eveonline.neocom.infinity.backend.sde.service.SBSDEDatabaseService;
import org.dimensinfin.eveonline.neocom.infinity.service.SBConfigurationService;
import org.dimensinfin.eveonline.neocom.loyalty.persistence.LoyaltyOffersRepository;
import org.dimensinfin.eveonline.neocom.loyalty.service.LoyaltyService;
import org.dimensinfin.eveonline.neocom.market.service.MarketService;
import org.dimensinfin.eveonline.neocom.provider.IConfigurationService;
import org.dimensinfin.eveonline.neocom.provider.IFileSystem;
import org.dimensinfin.eveonline.neocom.service.DMServicesDependenciesModule;
import org.dimensinfin.eveonline.neocom.service.ESIDataService;
import org.dimensinfin.eveonline.neocom.service.IStoreCache;
import org.dimensinfin.eveonline.neocom.service.LocationCatalogService;
import org.dimensinfin.eveonline.neocom.service.MemoryStoreCacheService;
import org.dimensinfin.eveonline.neocom.service.ResourceFactory;
import org.dimensinfin.eveonline.neocom.service.RetrofitService;

public class NeoComInfinityBackendDependenciesModule extends AbstractModule {
	private static final String ENV_PROPERTIES_DIRECTORY = "PROPERTIES_DIRECTORY";
	private static final String ENV_APPLICATION_DIRECTORY = "APPLICATION_DIRECTORY";
	private static final String ENV_SDE_DATABASE = "SDE_DATABASE_PATH";
	private static final String ENV_NEOCOM_DATABASE_URL = "NEOCOM_DATABASE_URL";
	private static final String DEFAULT_PROPERTIES_DIRECTORY = "/build/resources/main/properties";
	private static final String DEFAULT_APPLICATION_DIRECTORY = "./NeoCom.Infinity";
	private static final String DEFAULT_SDE_DATABASE = "/build/resources/main/sde.db";
	private static final String DEFAULT_NEOCOM_DATABASE_URL = "jdbc:postgresql://localhost:5432/postgres?user=adamantinoo&password=z.iliada.2020";

	@Override
	protected void configure() {
		// Bind configuration environment defined settings.
		String propDirectory = System.getenv( ENV_PROPERTIES_DIRECTORY );
		String appDirectory = System.getenv( ENV_APPLICATION_DIRECTORY );
		String sdeDatabasePath = System.getenv( ENV_SDE_DATABASE );
		String neoComDatabaseUrl = System.getenv( ENV_NEOCOM_DATABASE_URL );
		if (null == propDirectory) propDirectory = DEFAULT_PROPERTIES_DIRECTORY;
		if (null == appDirectory) appDirectory = DEFAULT_APPLICATION_DIRECTORY;
		if (null == sdeDatabasePath) sdeDatabasePath = DEFAULT_SDE_DATABASE;
		if (null == neoComDatabaseUrl) neoComDatabaseUrl = DEFAULT_NEOCOM_DATABASE_URL;
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

		// Bind platform specific implementations.
		this.bind( IConfigurationService.class )
				.annotatedWith( Names.named( DMServicesDependenciesModule.ICONFIGURATION_SERVICE ) )
				.to( SBConfigurationService.class )
				.in( Singleton.class );
		this.bind( IFileSystem.class )
				.annotatedWith( Names.named( DMServicesDependenciesModule.IFILE_SYSTEM ) )
				.to( SBFileSystemAdapter.class )
				.in( Singleton.class );
		this.bind( IStoreCache.class )
				.annotatedWith( Names.named( DMServicesDependenciesModule.ISTORE_CACHE ) )
				.to( MemoryStoreCacheService.class )
				.in( Singleton.class );

		// Bind DM services until this is declared on the DM library.
		this.bind( RetrofitService.class )
				.annotatedWith( Names.named( DMServicesDependenciesModule.RETROFIT_SERVICE ) )
				.to( RetrofitService.class )
				.in( Singleton.class );
		this.bind( LocationCatalogService.class )
				.annotatedWith( Names.named( DMServicesDependenciesModule.LOCATION_CATALOG_SERVICE ) )
				.to( LocationCatalogService.class )
				.in( Singleton.class );
		this.bind( ESIDataService.class )
				.annotatedWith( Names.named( DMServicesDependenciesModule.ESIDATA_SERVICE ) )
				.to( ESIDataService.class )
				.in( Singleton.class );
		this.bind( ResourceFactory.class )
				.annotatedWith( Names.named( DMServicesDependenciesModule.RESOURCE_FACTORY ) )
				.to( ResourceFactory.class )
				.in( Singleton.class );
		this.bind( LoyaltyService.class )
				.annotatedWith( Names.named( DMServicesDependenciesModule.LOYALTY_SERVICE ) )
				.to( LoyaltyService.class )
				.in( Singleton.class );
		this.bind( MarketService.class )
				.annotatedWith( Names.named( DMServicesDependenciesModule.MARKET_SERVICE ) )
				.to( MarketService.class )
				.in( Singleton.class );
		this.bind( CharacterService.class )
				.annotatedWith( Names.named( DMServicesDependenciesModule.CHARACTER_SERVICE ) )
				.to( CharacterService.class )
				.in( Singleton.class );

		this.bind( ISDEDatabaseService.class )
				.annotatedWith( Names.named( "ISDEDatabaseService" ) )
				.to( SBSDEDatabaseService.class )
				.in( Singleton.class );

		// Bind database services
		this.bind( NeoComDatabaseService.class )
				.annotatedWith( Names.named( DMServicesDependenciesModule.NEOCOM_DATABASE_SERVICE ) )
				.to( SBNeoComDBAdapter.class )
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
	}
}