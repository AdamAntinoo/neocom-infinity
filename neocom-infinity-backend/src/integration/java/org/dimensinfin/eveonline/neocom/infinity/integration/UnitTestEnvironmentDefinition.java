package org.dimensinfin.eveonline.neocom.infinity.integration;

import java.io.IOException;
import java.sql.SQLException;

import org.junit.jupiter.api.BeforeEach;
import org.testcontainers.containers.BindMode;
import org.testcontainers.containers.GenericContainer;

import org.dimensinfin.eveonline.neocom.infinity.service.SBConfigurationService;
import org.dimensinfin.eveonline.neocom.provider.IFileSystem;

import static org.dimensinfin.eveonline.neocom.provider.PropertiesDefinitionsConstants.ESI_TRANQUILITY_AUTHORIZATION_SERVER_URL;

public class UnitTestEnvironmentDefinition {
	//	protected static final Logger logger = LoggerFactory.getLogger( UnitTestEnvironmentDefinition.class );
	//	protected static final Integer TEST_CORPORATION_IDENTIFIER = 98384726;
	//	protected static final Integer TEST_ITEM_IDENTIFIER = 34;
	//	protected static final String TEST_ITEM_NAME="Tritanium";
	//	protected static final int DEFAULT_CHARACTER_IDENTIFIER = 92223647;
	//	protected static final int DEFAULT_PLANET_IDENTIFIER = 40208304;
	//	protected static final int DEFAULT_SCHEMATIC = 127;
	//
	//	protected static final JdbcConnectionSource connectionSource;
	protected static final int ESIOAUTH_UNITTESTING_PORT = 6090;
	//	protected static final int BACKEND_UNITTESTING_PORT = 6092;
	protected static final GenericContainer<?> esioauthsimulator;
	//	protected static final GenericContainer<?> backendSimulator;
	//	private static final PostgreSQLContainer postgres;
	//	private static final String connectionUrl;
	//	protected static Credential credential4Test;

	static {
		esioauthsimulator = new GenericContainer<>( "apimastery/apisimulator" )
				.withExposedPorts( ESIOAUTH_UNITTESTING_PORT )
				.withFileSystemBind( "/home/adam/Development/NeoCom/neocom-infinity-backend/backend.App/src/test/resources/esioauth-simulation",
						"/esioauth-simulation",
						BindMode.READ_WRITE )
				.withCommand( "bin/apisimulator start /esioauth-simulation" );
		esioauthsimulator.start();
		//		backendSimulator = new GenericContainer<>( "apimastery/apisimulator" )
		//				.withExposedPorts( BACKEND_UNITTESTING_PORT )
		//				.withFileSystemBind(
		//						"/home/adam/Development/NeoCom/neocom-datamanagement/NeoCom.DataManagement/src/test/resources/backend-unittesting",
		//						"/backend-unittesting",
		//						BindMode.READ_WRITE )
		//				.withCommand( "bin/apisimulator start /backend-unittesting -p 6092" );
		//		backendSimulator.start();
	}

	protected SBConfigurationService itConfigurationProvider;
	//	static {
	//		JdbcConnectionSource connectionSource1;
	//		postgres = new PostgreSQLContainer( "postgres:9.6.8" )
	//				.withDatabaseName( "postgres" )
	//				.withUsername( "neocom" )
	//				.withPassword( "01.Alpha" );
	//		postgres.start();
	//		connectionUrl = "jdbc:postgresql://"
	//				+ postgres.getContainerIpAddress()
	//				+ ":" + postgres.getMappedPort( PostgreSQLContainer.POSTGRESQL_PORT )
	//				+ "/" + "postgres" +
	//				"?user=" + "neocom" +
	//				"&password=" + "01.Alpha";
	//		NeoComLogger.info( "Postgres SQL URL: {}", connectionUrl );
	//		try {
	//			connectionSource1 = new JdbcConnectionSource( connectionUrl, new PostgresDatabaseType() );
	//		} catch (final SQLException sqle) {
	//			sqle.printStackTrace();
	//			connectionSource1 = null;
	//		}
	//		connectionSource = connectionSource1;
	//	}

	//	@BeforeAll
	//	public static void beforeAllCredential() {
	//		credential4Test = Mockito.mock( Credential.class );
	//		Mockito.when( credential4Test.getAccountId() ).thenReturn( 92223647 );
	//		Mockito.when( credential4Test.getDataSource() ).thenReturn( "tranquility" );
	//		Mockito.when( credential4Test.setMiningResourcesEstimatedValue( Mockito.anyDouble() ) ).thenReturn( credential4Test );
	//	}
	protected IFileSystem itFileSystemAdapter;
	//	protected IntegrationNeoComDBAdapter itNeoComIntegrationDBAdapter;
	//	protected AssetRepository itAssetRepository;
	//	protected CredentialRepository itCredentialRepository;
	//	protected StoreCacheManager itStoreCacheManager;
	//	protected ESIUniverseDataProvider itEsiUniverseDataProvider;
	//	protected ESIDataProvider esiDataProvider;
	//	protected LocationCatalogService itLocationCatalogService;
	//	protected RetrofitFactory itRetrofitFactory;


	@BeforeEach
	public void beforeAllSetupEnvironment() throws IOException, SQLException {
		this.setupEnvironment();
	}

	protected void setupEnvironment() throws SQLException, IOException {
		//		credential4Test = Mockito.mock( Credential.class );
		//		Mockito.when( credential4Test.getAccountId() ).thenReturn( 92223647 );
		//		Mockito.when( credential4Test.getDataSource() ).thenReturn( "tranquility" );
		//		Mockito.when( credential4Test.setMiningResourcesEstimatedValue( Mockito.anyDouble() ) ).thenReturn( credential4Test );

		//		this.itConfigurationProvider = new SBConfigurationService.Builder()
		//				.optionalPropertiesDirectory( "/src/test/resources/properties.unittest" ).build();
		this.itConfigurationProvider.setProperty( ESI_TRANQUILITY_AUTHORIZATION_SERVER_URL,
				"http://" +
						esioauthsimulator.getContainerIpAddress() +
						":" +
						esioauthsimulator.getMappedPort( ESIOAUTH_UNITTESTING_PORT ) );
		//		this.itFileSystemAdapter = new SBFileSystemAdapter.Builder()
		//				.optionalApplicationDirectory( "./src/test/NeoCom.UnitTesting/" )
		//				.build();
		//		final String databaseHostName = this.itConfigurationProvider.getResourceString( "P.database.neocom.databasehost" );
		//		final String databasePath = this.itConfigurationProvider.getResourceString( "P.database.neocom.databasepath" );
		//		final String databaseUser = this.itConfigurationProvider.getResourceString( "P.database.neocom.databaseuser" );
		//		final String databasePassword = this.itConfigurationProvider.getResourceString( "P.database.neocom.databasepassword" );
		//		this.itNeoComIntegrationDBAdapter = new IntegrationNeoComDBAdapter.Builder()
		//				.withDatabaseURLConnection( connectionUrl )
		//				.build();
		//		this.itAssetRepository = new AssetRepository.Builder()
		//				.withAssetDao( this.itNeoComIntegrationDBAdapter.getAssetDao() )
		//				.withConnection4Transaction( this.itNeoComIntegrationDBAdapter.getConnectionSource() )
		//				.build();
		//		this.itCredentialRepository = Mockito.mock( CredentialRepository.class );
		//		Mockito.doAnswer( ( credential ) -> {
		//			return null;
		//		} ).when( this.itCredentialRepository ).persist( Mockito.any( Credential.class ) );
		//		this.itRetrofitFactory = new RetrofitFactory.Builder()
		//				.withConfigurationProvider( this.itConfigurationProvider )
		//				.withFileSystemAdapter( this.itFileSystemAdapter )
		//				.build();
		//		this.itStoreCacheManager = new StoreCacheManager.Builder()
		//				.withConfigurationProvider( this.itConfigurationProvider )
		//				.withFileSystemAdapter( this.itFileSystemAdapter )
		//				.withRetrofitFactory( this.itRetrofitFactory )
		//				.build();
		//		this.itEsiUniverseDataProvider = new ESIUniverseDataProvider.Builder()
		//				.withConfigurationProvider( this.itConfigurationProvider )
		//				.withFileSystemAdapter( this.itFileSystemAdapter )
		//				.withStoreCacheManager( this.itStoreCacheManager )
		//				.withRetrofitFactory( this.itRetrofitFactory )
		//				.build();
		//		final LocationRepository locationRepository = Mockito.mock( LocationRepository.class );
		//		this.itLocationCatalogService = new LocationCatalogService.Builder()
		//				.withConfigurationProvider( this.itConfigurationProvider )
		//				.withFileSystemAdapter( this.itFileSystemAdapter )
		//				.withESIUniverseDataProvider( this.itEsiUniverseDataProvider )
		//				.withRetrofitFactory( this.itRetrofitFactory )
		//				.build();
		//		this.esiDataProvider = new ESIDataProvider.Builder()
		//				.withConfigurationProvider( this.itConfigurationProvider )
		//				.withFileSystemAdapter( this.itFileSystemAdapter )
		//				.withLocationCatalogService( this.itLocationCatalogService )
		//				.withRetrofitFactory( this.itRetrofitFactory )
		//				.withStoreCacheManager( this.itStoreCacheManager )
		//				.build();
	}
}
