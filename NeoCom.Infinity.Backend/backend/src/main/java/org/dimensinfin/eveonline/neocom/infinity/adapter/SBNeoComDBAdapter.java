package org.dimensinfin.eveonline.neocom.infinity.adapter;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.jdbc.JdbcPooledConnectionSource;
import com.j256.ormlite.support.ConnectionSource;
import org.apache.commons.lang3.StringUtils;
import org.dimensinfin.eveonline.neocom.database.entities.Credential;
import org.dimensinfin.eveonline.neocom.entities.DatabaseVersion;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.SQLException;
import java.util.concurrent.TimeUnit;

/**
 * NeoCom private database connector that will have the same api as the connector to be used on Android. This
 * version already uses the mySql database JDBC implementation instead the SQLite copied from the Android
 * platform.
 * The class will encapsulate all dao and connection access.
 *
 * @author Adam Antinoo
 */
public class SBNeoComDBAdapter /*implements INeoComDBHelper*/ {
	protected static Logger logger = LoggerFactory.getLogger(SBNeoComDBAdapter.class);

	private String databaseHostName = "";
	private String databaseType = "postgres";
	private String databasePath;
	//	private String databaseName = "";
	private String databaseUser;
	private String databasePassword;
	private String databaseOptions = "&verifyServerCertificate=false&useSSL=true";
	protected String localConnectionDescriptor;
	private int databaseVersion = 0;
	private boolean databaseValid = false;
	private boolean isOpen = false;
	private JdbcPooledConnectionSource connectionSource;
	private DatabaseVersion storedVersion;

	private Dao<DatabaseVersion, String> versionDao = null;
	private Dao<Credential, String> credentialDao = null;

	// - C O N S T R U C T O R S
	protected SBNeoComDBAdapter() {
	}

	// - D A O - A C C E S S
	public ConnectionSource getConnectionSource() throws SQLException {
		if (null == this.connectionSource) this.openNeoComDB();
		return this.connectionSource;
	}

	//	@Override
	public Dao<DatabaseVersion, String> getVersionDao() throws SQLException {
		if (null == this.versionDao) {
			this.versionDao = DaoManager.createDao(this.getConnectionSource(), DatabaseVersion.class);
		}
		return this.versionDao;
	}

	//	@Override
	public Dao<Credential, String> getCredentialDao() throws SQLException {
		if (null == this.credentialDao) {
			this.credentialDao = DaoManager.createDao(this.getConnectionSource(), Credential.class);
		}
		return this.credentialDao;
	}

	public boolean isDatabaseDescriptorValid() throws SQLException {
		if (null != this.localConnectionDescriptor) return true;
		if (StringUtils.isEmpty(this.databaseHostName))
			throw new SQLException("Cannot create connection: 'hostName' is empty.");
		if (StringUtils.isEmpty(this.databaseType))
			throw new SQLException("Cannot create connection: 'databaseType' is empty.");
		if (StringUtils.isEmpty(this.databasePath))
			throw new SQLException("Cannot create connection: 'databasePath' is empty.");
		if (StringUtils.isEmpty(this.databaseUser))
			throw new SQLException("Cannot create connection: 'databaseUser' is empty.");
		if (StringUtils.isEmpty(this.databasePassword))
			throw new SQLException("Cannot create connection: 'databasePassword' is empty.");
//		if ( null == this.databaseOptions) // Read the default configured options
		// Compose the descriptor.
		this.localConnectionDescriptor = this.databaseHostName + "/" + this.databasePath +
				"?user=" + this.databaseUser +
				"&password=" + this.databasePassword +
				this.databaseOptions;
		return true;
	}

	/**
	 * Open a new pooled JDBC datasource connection list and stores its reference for use of the whole set of
	 * services. Being a pooled connection it can create as many connections as required to do requests in
	 * parallel to the database instance. This only is effective for MySql databases.
	 *
	 * @return
	 */
	private boolean openNeoComDB() throws SQLException {
		logger.info(">> [SBNeoComDBHelper.openNeoComDB]");
		if (!this.isOpen) if (null == this.connectionSource) {
			this.isOpen = this.createConnectionSource();
			final String localConnectionDescriptor = this.databaseHostName + "/" + this.databasePath;
			logger.info("-- [SBNeoComDBHelper.openNeoComDB]> Opened database {} successfully with version {}.",
					localConnectionDescriptor, this.databaseVersion);
		}
		logger.info("<< [SBNeoComDBHelper.openNeoComDB]");
		return isOpen;
	}

	/**
	 * Creates and configures the connection pool to access the application database.
	 *
	 * @throws SQLException
	 */
	private boolean createConnectionSource() throws SQLException {
		this.isDatabaseDescriptorValid(); // Database should be valid and the descriptor ready.
		this.connectionSource = new JdbcPooledConnectionSource(this.localConnectionDescriptor);
		// Configure the new connection pool.
		connectionSource.setMaxConnectionAgeMillis(TimeUnit.MINUTES.toMillis(5)); // only keep the connections open for 5 minutes
		connectionSource.setCheckConnectionsEveryMillis(TimeUnit.SECONDS.toMillis(60)); // change the check-every milliseconds from 30 seconds to 60
		connectionSource.setTestBeforeGet(true);
		return true;
	}

	// - B U I L D E R
	public static class Builder {
		private SBNeoComDBAdapter onConstruction;

		public Builder() {
			this.onConstruction = new SBNeoComDBAdapter();
			// Set default or configured values.
			// TODO - Connect tot the configuration manager component
//			this.onConstruction.databaseOptions = GlobalDataManager.getResourceString("P.database.neocom.databaseoptions");
		}

		private SBNeoComDBAdapter.Builder withDatabaseHostName(final String databaseHostName) {
			this.onConstruction.databaseHostName = databaseHostName;
			return this;
		}

		private SBNeoComDBAdapter.Builder withDatabaseType(final String databaseType) {
			this.onConstruction.databaseType = databaseType;
			return this;
		}

		private SBNeoComDBAdapter.Builder withDatabasePath(final String databasePath) {
			this.onConstruction.databasePath = databasePath;
			return this;
		}

		private SBNeoComDBAdapter.Builder withDatabaseUser(final String databaseUser) {
			this.onConstruction.databaseUser = databaseUser;
			return this;
		}

		private SBNeoComDBAdapter.Builder withDatabasePassword(final String databasePassword) {
			this.onConstruction.databasePassword = databasePassword;
			return this;
		}

		private SBNeoComDBAdapter.Builder optionalDatabaseUrl(final String databaseUrl) {
			if (null != databaseUrl) this.onConstruction.localConnectionDescriptor = databaseUrl;
			return this;
		}

		private SBNeoComDBAdapter.Builder optionalDatabaseOptions(final String databaseOptions) {
			this.onConstruction.databaseOptions = databaseOptions;
			return this;
		}

		public SBNeoComDBAdapter build() throws SQLException {
			// Connect to the database instance.
			this.onConstruction.databaseValid = this.onConstruction.isDatabaseDescriptorValid();
			this.onConstruction.openNeoComDB(); // Open and connect the database.
			return this.onConstruction;
		}
	}

	// ------------------------------------------------------------
	//[02]
//[04]
//	@Override
//	public Dao<ColonyStorage, String> getColonyStorageDao() throws SQLException {
//		if (null == colonyStorageDao) {
//			colonyStorageDao = DaoManager.createDao(this.getConnectionSource(), ColonyStorage.class);
//		}
//		return colonyStorageDao;
//	}
//
//	public Dao<ColonySerialized, String> getColonySerializedDao() throws SQLException {
//		if (null == colonySerializedDao) {
//			colonySerializedDao = DaoManager.createDao(this.getConnectionSource(), ColonySerialized.class);
//		}
//		return colonySerializedDao;
//	}

// [03]
	//[05]
}
// - UNUSED CODE ............................................................................................
//[01]
//				ApiKey key = new ApiKey("Beth Ripley").setKeynumber(2889577)
//				                                      .setValidationcode("Mb6iDKR14m9Xjh9maGTQCGTkpjRHPjOgVUkvK6E9r6fhMtOWtipaqybp0qCzxuuw")
//				                                      .setActive(true);
//				key = new ApiKey("Perico").setKeynumber(3106761)
//				                          .setValidationcode("gltCmvVoZl5akrM8d6DbNKZn7Jm2SaukrmqjnSOyqKbvzz5CtNfknTEwdBe6IIFf").setActive(false);
//				ApiKey		key = new ApiKey("CapitanHaddock09").setKeynumber(924767)
//				                                    .setValidationcode("2qBKUY6I9ozYhKxYUBPnSIix0fHFCqveD1UEAv0GbYqLenLLTIfkkIWeOBejKX5P").setActive(true);
//				key = new ApiKey("CapitanHaddock29").setKeynumber(6472981)
//				                                    .setValidationcode("pj1NJKKb0pNO8LTp0qN2yJSxZoZUO0UYYq8qLtOeFXNsNBRpiz7orcqVAu7UGF7z").setActive(true);
// [02]
//@Deprecated
//public SBNeoComDBHelper setDatabaseType(final String databaseType) {
//	this.databaseType = databaseType;
//	return this;
//}
//
//	@Deprecated
//	public SBNeoComDBHelper setDatabaseHost(final String hostName) {
//		this.hostName = hostName;
//		return this;
//	}
//
//	@Deprecated
//	public SBNeoComDBHelper setDatabaseName(final String instanceName) {
//		this.databaseName = instanceName;
//		return this;
//	}
//
//	@Deprecated
//	public SBNeoComDBHelper setDatabaseUser(final String user) {
//		this.databaseUser = user;
//		return this;
//	}
//
//	@Deprecated
//	public SBNeoComDBHelper setDatabasePassword(final String password) {
//		this.databasePassword = password;
//		return this;
//	}
//
//	@Deprecated
//	public SBNeoComDBHelper setDatabaseOptions(final String options) {
//		this.databaseOptions = options;
//		return this;
//	}
//
//	@Deprecated
//	public SBNeoComDBHelper setDatabaseVersion(final int newVersion) {
//		this.databaseVersion = newVersion;
//		return this;
//	}

//[03]
//public Dao<NeoComAsset, String> getAssetDao() throws SQLException {
//	if (null == assetDao) {
//		assetDao = DaoManager.createDao(this.getConnectionSource(), NeoComAsset.class);
//	}
//	return assetDao;
//}
//
//	public Dao<EveLocation, String> getLocationDao() throws SQLException {
//		if (null == locationDao) {
//			locationDao = DaoManager.createDao(this.getConnectionSource(), EveLocation.class);
//		}
//		return locationDao;
//	}
//
//	public Dao<Property, String> getPropertyDao() throws SQLException {
//		if (null == propertyDao) {
//			propertyDao = DaoManager.createDao(this.getConnectionSource(), Property.class);
//		}
//		return propertyDao;
//	}
//
//	public Dao<NeoComBlueprint, String> getBlueprintDao() throws SQLException {
//		if (null == blueprintDao) {
//			blueprintDao = DaoManager.createDao(this.getConnectionSource(), NeoComBlueprint.class);
//		}
//		return blueprintDao;
//	}
//
//	public Dao<Job, String> getJobDao() throws SQLException {
//		if (null == jobDao) {
//			jobDao = DaoManager.createDao(this.getConnectionSource(), Job.class);
//		}
//		return jobDao;
//	}
//
//	public Dao<MarketOrder, String> getMarketOrderDao() throws SQLException {
//		if (null == marketOrderDao) {
//			marketOrderDao = DaoManager.createDao(this.getConnectionSource(), MarketOrder.class);
//		}
//		return marketOrderDao;
//	}
//
//	public Dao<FittingRequest, String> getFittingRequestDao() throws SQLException {
//		if (null == fittingRequestDao) {
//			fittingRequestDao = DaoManager.createDao(this.getConnectionSource(), FittingRequest.class);
//		}
//		return fittingRequestDao;
//	}
//
//	public Dao<MiningExtraction, String> getMiningExtractionDao() throws SQLException {
//		if (null == miningExtractionDao) {
//			miningExtractionDao = DaoManager.createDao(this.getConnectionSource(), MiningExtraction.class);
//		}
//		return miningExtractionDao;
//	}
//
//	public Dao<RefiningData, String> getRefiningDataDao() throws SQLException {
//		if (null == refiningDataDao) {
//			refiningDataDao = DaoManager.createDao(this.getConnectionSource(), RefiningData.class);
//		}
//		return refiningDataDao;
//	}
//[04]
//@Deprecated
//public SBNeoComDBHelper build() throws SQLException {
////		databaseValid = true;
//	if (openNeoComDB()) {
//		// Warning. Delay database initialization after the helper is assigned to the Global.
//		InfinityGlobalDataManager.submitJob2ui(() -> {
//			// Wait for some time units.
//			GlobalDataManager.suspendThread(TimeUnit.SECONDS.toMillis(2));
//			int currentVersion = readDatabaseVersion();
//			// During the current POC version force the creation of the tables and forget the version control.
//			// Read the version information from the database. If version mismatch upgrade the database.
//			if (0 == currentVersion) {
//				onUpgrade(connectionSource, currentVersion, databaseVersion);
//			} else {
//				// Check if the version is equal to the current software version.
//				if (currentVersion != databaseVersion) onUpgrade(connectionSource, currentVersion, databaseVersion);
//			}
//			// Pass the creation tables routine even in case all tables are up to date.
//			onCreate(connectionSource);
//		});
//	}
//	return this;
//}
//
//	// --- INEOCOMDBHELPER INTERFACE
//	@Deprecated
//	public boolean isDatabaseValid() {
//		return databaseValid;
//	}
//
//	public boolean isOpen() {
//		return isOpen;
//	}
//
//	public int getDatabaseVersion() {
//		return databaseVersion;
//	}
//
//	public int getStoredVersion() {
//		if (null == storedVersion) {
//			// Access the version object persistent on the database.
//			try {
//				List<DatabaseVersion> versionList = new GlobalDataManager().getNeocomDBHelper().getVersionDao().queryForAll();
//				if (versionList.size() > 0) {
//					storedVersion = versionList.get(0);
//					return storedVersion.getVersionNumber();
//				} else
//					return 0;
//			} catch (SQLException sqle) {
//				logger.warn("W- [NeoComSBDBHelper.getStoredVersion]> Database exception: " + sqle.getMessage());
//				return 0;
//			} catch (RuntimeException rtex) {
//				logger.warn("W- [NeoComSBDBHelper.getStoredVersion]> Database exception: " + rtex.getMessage());
//				return 0;
//			}
//		} else return storedVersion.getVersionNumber();
//	}
//
//
//	public void onCreate(final ConnectionSource databaseConnection) {
//		logger.info(">> [NeoComSBDBHelper.onCreate]");
//		// Create the tables that do not exist
//		try {
//			TableUtils.createTableIfNotExists(databaseConnection, DatabaseVersion.class);
//		} catch (SQLException sqle) {
//			logger.warn("SQL [NeoComSBDBHelper.onCreate]> SQL NeoComDatabase: {}", sqle.getMessage());
//		}
//		try {
//			TableUtils.createTableIfNotExists(databaseConnection, TimeStamp.class);
//		} catch (SQLException sqle) {
//			logger.warn("SQL [NeoComSBDBHelper.onCreate]> SQL NeoComDatabase: {}", sqle.getMessage());
//		}
////		try {
////			TableUtils.createTableIfNotExists(databaseConnection, ApiKey.class);
////		} catch (SQLException sqle) {
////			logger.warn("SQL [NeoComSBDBHelper.onCreate]> SQL NeoComDatabase: {}", sqle.getMessage());
////		}
//		try {
//			TableUtils.createTableIfNotExists(databaseConnection, Credential.class);
//		} catch (SQLException sqle) {
//			logger.warn("SQL [NeoComSBDBHelper.onCreate]> SQL NeoComDatabase: {}", sqle.getMessage());
//		}
////		try {
////			TableUtils.createTableIfNotExists(databaseConnection, ColonyStorage.class);
////		} catch (SQLException sqle) {
////			logger.warn("SQL [NeoComSBDBHelper.onCreate]> SQL NeoComDatabase: {}", sqle.getMessage());
////		}
//		try {
//			TableUtils.createTableIfNotExists(databaseConnection, NeoComAsset.class);
//		} catch (SQLException sqle) {
//			logger.warn("SQL [NeoComSBDBHelper.onCreate]> SQL NeoComDatabase: {}", sqle.getMessage());
//		}
//		try {
//			TableUtils.createTableIfNotExists(databaseConnection, NeoComBlueprint.class);
//		} catch (SQLException sqle) {
//			logger.warn("SQL [NeoComSBDBHelper.onCreate]> SQL NeoComDatabase: {}", sqle.getMessage());
//		}
//		try {
//			TableUtils.createTableIfNotExists(databaseConnection, EveLocation.class);
//		} catch (SQLException sqle) {
//			logger.warn("SQL [NeoComSBDBHelper.onCreate]> SQL NeoComDatabase: {}", sqle.getMessage());
//		}
//		try {
//			TableUtils.createTableIfNotExists(databaseConnection, Job.class);
//		} catch (SQLException sqle) {
//			logger.warn("SQL [NeoComSBDBHelper.onCreate]> SQL NeoComDatabase: {}", sqle.getMessage());
//		}
//		try {
//			TableUtils.createTableIfNotExists(databaseConnection, MarketOrder.class);
//		} catch (SQLException sqle) {
//			logger.warn("SQL [NeoComSBDBHelper.onCreate]> SQL NeoComDatabase: {}", sqle.getMessage());
//		}
//		try {
//			TableUtils.createTableIfNotExists(databaseConnection, FittingRequest.class);
//		} catch (SQLException sqle) {
//			logger.warn("SQL [NeoComSBDBHelper.onCreate]> SQL NeoComDatabase: {}", sqle.getMessage());
//		}
//		try {
//			TableUtils.createTableIfNotExists(databaseConnection, Property.class);
//		} catch (SQLException sqle) {
//			logger.warn("SQL [NeoComSBDBHelper.onCreate]> SQL NeoComDatabase: {}", sqle.getMessage());
//		}
//		//		try {
//		//			TableUtils.createTableIfNotExists(databaseConnection, ResourceList.class);
//		//		} catch (SQLException sqle) {
//		//		}
//		//		try {
//		//			TableUtils.createTableIfNotExists(databaseConnection, PlanetaryResource.class);
//		//		} catch (SQLException sqle) {
//		//		}
//		//		try {
//		//			TableUtils.createTableIfNotExists(databaseConnection, NeoComBlueprint.class);
//		//		} catch (SQLException sqle) {
//		//		}
//		this.loadSeedData();
//		logger.info("<< [NeoComSBDBHelper.onCreate]");
//	}
//
//	public void onUpgrade(final ConnectionSource databaseConnection, final int oldVersion, final int newVersion) {
//		logger.info(">> [NeoComSBDBHelper.onUpgrade]");
//		// Execute different actions depending on the new version.
//		if (oldVersion < 109) {
//			try {
//				// Drop all the tables to force a new update from the latest SQLite version.
//				TableUtils.dropTable(databaseConnection, DatabaseVersion.class, true);
//				try {
//					TableUtils.createTableIfNotExists(databaseConnection, DatabaseVersion.class);
//					DatabaseVersion version = new DatabaseVersion(newVersion)
//							.store();
//				} catch (SQLException sqle) {
//					logger.warn("SQL [NeoComSBDBHelper.onCreate]> SQL NeoComDatabase: {}", sqle.getMessage());
//				}
//			} catch (RuntimeException rtex) {
//				logger.error("E> Error dropping table on Database new version.");
//				rtex.printStackTrace();
//			} catch (SQLException sqle) {
//				logger.error("E> Error dropping table on Database new version.");
//				sqle.printStackTrace();
//			}
//			try {
//				// Drop all the tables to force a new update from the latest SQLite version.
//				TableUtils.dropTable(databaseConnection, TimeStamp.class, true);
//			} catch (RuntimeException rtex) {
//				logger.error("E> Error dropping table on Database new version.");
//				rtex.printStackTrace();
//			} catch (SQLException sqle) {
//				logger.error("E> Error dropping table on Database new version.");
//				sqle.printStackTrace();
//			}
////			try {
////				// Drop all the tables to force a new update from the latest SQLite version.
////				TableUtils.dropTable(databaseConnection, ApiKey.class, true);
////			} catch (RuntimeException rtex) {
////				logger.error("E> Error dropping table on Database new version.");
////				rtex.printStackTrace();
////			} catch (SQLException sqle) {
////				logger.error("E> Error dropping table on Database new version.");
////				sqle.printStackTrace();
////			}
//			try {
//				// Drop all the tables to force a new update from the latest SQLite version.
//				TableUtils.dropTable(databaseConnection, Credential.class, true);
//			} catch (RuntimeException rtex) {
//				logger.error("E> Error dropping table on Database new version.");
//				rtex.printStackTrace();
//			} catch (SQLException sqle) {
//				logger.error("E> Error dropping table on Database new version.");
//				sqle.printStackTrace();
//			}
//			try {
//				// Drop all the tables to force a new update from the latest SQLite version.
//				TableUtils.dropTable(databaseConnection, NeoComAsset.class, true);
//			} catch (RuntimeException rtex) {
//				logger.error("E> Error dropping table on Database new version.");
//				rtex.printStackTrace();
//			} catch (SQLException sqle) {
//				logger.error("E> Error dropping table on Database new version.");
//				sqle.printStackTrace();
//			}
//		}
//		this.onCreate(databaseConnection);
//		logger.info("<< [NeoComSBDBHelper.onUpgrade]");
//	}
//
//	/**
//	 * Checks if the key tables had been cleaned and then reinserts the seed data on them.
//	 */
//	public void loadSeedData() {
//		logger.info(">> [NeoComSBDBHelper.loadSeedData]");
//		// Add seed data to the new database is the tables are empty.
//		try {
//			//---  D A T A B A S E    V E R S I O N
//			logger.info("-- [NeoComSBDBHelper.loadSeedData]> Loading seed data for DatabaseVersion");
//			Dao<DatabaseVersion, String> version = this.getVersionDao();
//			QueryBuilder<DatabaseVersion, String> queryBuilder = version.queryBuilder();
//			queryBuilder.setCountOf(true);
//			// Check that at least one Version record exists on the database. It is a singleton.
//			long records = version.countOf(queryBuilder.prepare());
//			logger.info("-- [NeoComSBDBHelper.loadSeedData]> DatabaseVersion records: " + records);
//
//			// If the table is empty then insert the seeded Api Keys
//			if (records < 1) {
//				DatabaseVersion key = new DatabaseVersion(GlobalDataManager.getResourceInt("R.database.neocom.databaseversion"));
//				logger.info("-- [NeoComSBDBHelper.loadSeedData]> Setting DatabaseVersion to: " + key.getVersionNumber());
//			}
//		} catch (SQLException sqle) {
//			logger.error("E [NeoComSBDBHelper.loadSeedData]> Error creating the initial table on the app database.");
//			sqle.printStackTrace();
//		}
//
//		if (InfinityGlobalDataManager.getResourceBoolean("R.runtime.loadseeddata.credentials")) {
//			try {
//				//--- C R E D E N T I A L S
//				logger.info("-- [NeoComSBDBHelper.loadSeedData]> Loading seed data for Credentials");
//				final long records = this.getCredentialDao().countOf();
//				// If the table is empty then insert the seeded Credentials
//				if (records < 1) {
//					Credential credential = null;
////				credential = new Credential(92002067)
////						.setAccountName("Adam Antinoo")
////						.setAccessToken("51oilTxRLOECg5HJueiTEVcPvCWcltJ79-h-CUjHq6jYFuWdkZ9zcl0PCTRmGXuPtBuoX7jOe-3kVj7mODC57w2")
////						.setRefreshToken("XLFPeyEdC3o-gb5N3cWlU1pGYyNXOppVQ5f30")
////						.setDataSource(GlobalDataManager.SERVER_DATASOURCE)
////						.setScope(ESINetworkManager.getStringScopes())
////						.store();
////				Credential credential = new Credential(91734031)
////						.setAccessToken("m58y5NBSK7T_1ki9jx4XsGgfu4laHIF9-3WRLeNqkABe-VKZ57tGFee8kpwFBO8RTtSIrHyz9UKtC17clitqsw2")
////						.setAccountName("Zach Zender")
////						.setRefreshToken("HB68Z3aeNjQxpA8ebcNijfMGv9wfkcn-dkcy5qchW88Pe0ackWDHCy2yr5RrY_ERE4aKNCsR-J2a_-V_tS2sV_21HMTYcIKQ-QJHhz6GugotFfrdRcl6nsVjEuxOay5c7t-0tFu2diGy-2cF9y4qYCJ53da5slsLjNBWiIvUTxP7PUOQIs0y23_LhMPku1O1AXZsKG383NOLYQTCFL6vrVjThKJKXX9xRqm2rsRoe7xA_hyV0PiSmxjclUl9XzYULQEpVi_yl65jw8Xhn88bADOcsjeh4dAIyW2U5_b5GOf7KfKTLf2JzpIWP6jtcJreMD6L228hYYGrG-F0tQxPp1pEhQjVwS0KepHSJV-o4s9-tEfDRfhTd5FtvJm_pNwbbyVEdtzoU81L834jZz9c5U3gxhRMvTfT5dp8ZiF8TbqLTAYnyL6QICqOU7uSSVV59hFtZF7lbZOFnWpis-2_4YsUXMzdgfW-dMFAjPlE-bGCvEF3tteFPhbSWj24JJQ1sfbVCdXQ6WEEsM5DJoeo_hdW-_nsORIaI3Q3hjOWC_Wb9ucF3465IqzuFFJEhL2m3zpX_V4gk0_9ARU8XaT7GTrkCpbq-Ds-q0bTmz5zh1w1")
////						.store();
//					credential = new Credential(92223647)
//							.setAccessToken("Su9nYs3_qDQBHB2utYKQyZVfDy1l4ScMo81rtiDmDTDpRKV4yIln_cxfXDQaAR81wj1oBu8S1Hjxbf7VcJavaA2")
//							.setAccountName("Beth Ripley")
//							.setRefreshToken("NyjPkFKg1nr1nBK1e8bSaezKENbLZKtXOu0hkvnbK1LyghAHim-shdiXjMXZ8z8uQwCxUGPmow-BnSF5BX--zvbKI_bEQ5tGE6jiCZNKv0EoUrM205wRtq7QEWt-I0E51_YzMMHW05YWAG7ds4I72fsKJMtA0HZmfrtRQtf6q_tCoGErf0cpwuwHtNxeTg87UkEqEXicWHAdRRXTHONtDqrWiZbzOL48BQNXcgV3goL-hMzzi0V6sY1JolAxQ47MDKzJf6Fri7Zk2am4qwv2dZioVsGQ4j-U-COZhiyPphWyBUVWVpmuMqhwlYVrxah0n503rl3-dUEn05agnumHRu-KA22M8z6CHwtGx3ta2v_p63iy6n4DGjXjXI9efFKPEa3h-gmT9qRF4QQUNQ8tvJGB62a6YvkHCAsFy7FeVX7c7Bgb73w88ToGo5AH5kn4aBhx-kOnBQEyW11hi1xc1uZIHZOX4jn5OmrOYnJcYhYSnSBKtkpFgsgqiYrcO4zcRK__5XhoX1Owb2d0yj3B_y0m4FZ2-fYmgNlSGyQjbB-o1l_Fy7056bxoC28JLGkGPa-3c2jTfAhx7kltvW6q4oqGHqX7i2YXUulamfIe7I81")
//							.setDataSource(GlobalDataManager.TRANQUILITY_DATASOURCE)
//							.setScope(ESINetworkManager.getStringScopes())
//							.store();
////				credential = new Credential(93813310)
////						.setAccessToken("_WWshtZkjlNwXLRmvs3T0ZUaKAVo4QEl6JFwzIVIyNmdgjfqHhb41uY7ambYFDmjZsFZyLBjtH-90ONWu1E1sA2")
////						.setAccountName("Perico Tuerto")
////						.setRefreshToken("_rOthuCEPyRdKjNv6XyX84dguFmSkK4byrP3tTOj0Kv_3F_8GBvxsrUhrFZoRQPCjXXgzn5n0a5gdLeWA_hlS8Uv0LsK6upwKz2kfyG3mlANsAxfIDa2iGaGKq1pmFpe2w3lYuHl8cKGCItzL9uW4LL8gc8Uznqi9_jFNYC3Z-AXAPKNwN7hwQxcV7Znn2aprUC5BjaKrhBin-ptEPyVnNYvqBRBdXHYQcc-m4aaPu-4qD4lK4PXbcZanxrfDP_m2Tjd0EZNHMktlJgfVAwOMF7lBXxua6uXols7OKDYbJSadBeIa0Xrt9woLtbwQ7ZrKZMiXWOxbBH1QVbSbPkE-D5gNoR5Yl57D8ph4Q66w2CCWmYtQwdKR1Bx8hwPNtISfGQoTKHjrCIhtHL4ydBiRp_5V-4A1jJ2joJbc5rxfB2P9IRh-Qo42hO70BS5NCZMl1U3VMvmYkgauGGKSAKR_ckSbKDheE_Bv4yGKv2fW1HaLdNk59cD_PcHPX9Gb1DUA6BI_nUv9TG3SwcbEnqvKNnh3SzSD1tpn2IbxOzbwlyUz9rHcdqDbM9eh8oiXGxJCW3-FEPYwBnkI7I5DrARu0hthD-wtn6iKrPdeURCXGQ1")
////						.store();
//					credential = new Credential(92002067)
//							.setAccessToken("D8KEMVvY2zcbRXMh6B7ldShWWM2rnoqMomH-PbPegPZH00vfC9yeMXMWo-Nl94LBDQwcl76LOgoDdl2F3qQfZA2")
//							.setAccountName("Adam Antinoo")
//							.setRefreshToken("veGsthIl6AWifDZHEjqmZFBrxwu0ZGOEPgLtfrAnzKJxrbD9HEAploBalAS40AgJeM5siWM1oEQu-At790COHTSpSzITYLJoN_BFpe337bGEU3r9HPDFtka5_rKRvFlG1kF1GawXQOgL0pZ6lxC6CsDEscUGLwSaxe1KG9cJbWK1KDC024fyoTmYZeVyUZMnNV3AX064E4eak7jOfPiijEPc2jKMefI0zJwZl_g3nhE1pVzJA_Cexlb-YEnh1zl0xsiLhPCfjdLs3blFoX-Y1IeVLV9iordxd_llrc54z2-Rvz2R8atpM1tN2NI7GIuKX21HEp2fVP6m4TRy854oFz1Bw1StaLzS2XprLEzPjIc1gHTlaC5GysdQROxY57VHyyEQqCtxsBDzbJZ3k4WaaO-QuWEE-by3V_N_I0vX6LdxFwfhw15eSUbHE2Zm2uOvgdJrwotks7kXmKWL9erOGxcBK1Q7W-ckpRgYCY3yClGG9ptEq9fTEhvDupcgbaff70yTtYX_VAqysbD35KKdpFpnVu76EuVkrdyqWD1CAJbZgUhbZe_CYk5lbBQfjziQ6GYRSdt_6AVjrxN08_oTqbXdzH8f5Vtn6BRUKr0DtVw1")
//							.setDataSource(GlobalDataManager.TRANQUILITY_DATASOURCE)
//							.setScope(ESINetworkManager.getStringScopes())
//							.store();
//				}
//			} catch (SQLException sqle) {
//				logger.error("E [NeoComSBDBHelper.loadSeedData]> Error creating the initial table on the app database.");
//				sqle.printStackTrace();
//			} catch (RuntimeException rtex) {
//				logger.error("E [NeoComSBDBHelper.loadSeedData]> Error creating the initial table on the app database.");
//				rtex.printStackTrace();
//			}
//		} else
//			logger.info("-- [NeoComSBDBHelper.loadSeedData]> Seed data for Credentials skipped.");
//
//
//		try {
//			//--- F I T T I N G   R E Q U E S T
//			logger.info("-- [NeoComSBDBHelper.loadSeedData]> Loading seed data for FittingRequest");
//			// Check that at least one FittingRequest record exists on the database.
//			long records = this.getFittingRequestDao().countOf();
//			logger.info("-- [NeoComSBDBHelper.loadSeedData]> FittingRequest records: " + records);
//
//			// If the table is empty then insert the seeded FittingRequests.
//			if (records < 1) {
//				FittingRequest key = new FittingRequest()
//						.setCorporationId(1427661573)
//						.setTargetFitting(47773679)
//						.store();
//				key = new FittingRequest()
//						.setCorporationId(92002067)
//						.setTargetFitting(48137848)
//						.store();
//			}
//		} catch (SQLException sqle) {
//			logger.error("E [NeoComSBDBHelper.loadSeedData]> Error creating the initial table on the app database.");
//			sqle.printStackTrace();
//		}
//
//		try {
//			//--- P R O P E R T I E S
//			logger.info("-- [NeoComSBDBHelper.loadSeedData]> Loading Properties");
//			final long records = this.getPropertyDao().countOf();
//			// If the table is empty then insert the seeded Properties
//			if (records < 1) {
//				Property property = new Property(EPropertyTypes.LOCATIONROLE)
//						.setOwnerId(92002067)
//						.setStringValue("MANUFACTURE")
//						.setNumericValue(60006526)
//						.store();
//				property = new Property(EPropertyTypes.LOCATIONROLE)
//						.setOwnerId(92223647)
//						.setStringValue("MANUFACTURE")
//						.setNumericValue(60006526)
//						.store();
//			}
//		} catch (SQLException sqle) {
//			logger.error("E [NeoComSBDBHelper.loadSeedData]> Error creating the initial table on the app database.");
//			sqle.printStackTrace();
//		} catch (RuntimeException rtex) {
//			logger.error("E [NeoComSBDBHelper.loadSeedData]> Error creating the initial table on the app database.");
//			rtex.printStackTrace();
//		}
//		logger.info("<< [NeoComSBDBHelper.loadSeedData]");
//	}
//
//
//	@Override
//	public Dao<Colony, String> getColonyDao() throws SQLException {
//		if (null == colonyDao) {
//			colonyDao = DaoManager.createDao(this.getConnectionSource(), Colony.class);
//		}
//		return colonyDao;
//	}
//[05]
//	// --- PUBLIC CONNECTION SPECIFIC ACTIONS
//
//	/**
//	 * removes from the application database any asset and blueprint that contains the special -1 code as the
//	 * owner identifier. Those records are from older downloads and have to be removed to avoid merging with the
//	 * new download.
//	 */
//	public synchronized void clearInvalidRecords(final long pilotid) {
//		logger.info(">> [NeoComSBDBHelper.clearInvalidRecords]> pilotid: {}", pilotid);
//		synchronized (connectionSource) {
//			try {
//				TransactionManager.callInTransaction(connectionSource, new Callable<Void>() {
//					public Void call() throws Exception {
//						// Remove all assets that do not have a valid owner.
//						final DeleteBuilder<NeoComAsset, String> deleteBuilder = getAssetDao().deleteBuilder();
//						deleteBuilder.where().eq("ownerId", (pilotid * -1));
//						int count = deleteBuilder.delete();
//						logger.info("-- [NeoComSBDBHelper.clearInvalidRecords]> Invalid assets cleared for owner {}: {}", (pilotid * -1), count);
//
//						// Remove all blueprints that do not have a valid owner.
//						final DeleteBuilder<NeoComBlueprint, String> deleteBuilderBlueprint = getBlueprintDao().deleteBuilder();
//						deleteBuilderBlueprint.where().eq("ownerId", (pilotid * -1));
//						count = deleteBuilderBlueprint.delete();
//						logger.info("-- [NeoComSBDBHelper.clearInvalidRecords]> Invalid blueprints cleared for owner {}: {}", (pilotid * -1),
//								count);
//						return null;
//					}
//				});
//			} catch (final SQLException ex) {
//				logger.warn("W> [NeoComSBDBHelper.clearInvalidRecords]> Problem clearing invalid records. " + ex.getMessage());
//			} finally {
//				logger.info("<< [NeoComSBDBHelper.clearInvalidRecords]");
//			}
//		}
//	}
//
//	/**
//	 * Changes the owner id for all records from a new download with the id of the current character. This
//	 * completes the download and the assignment of the resources to the character without interrupting the
//	 * processing of data by the application.
//	 */
//	public synchronized void replaceAssets(final long pilotid) {
//		logger.info(">> [NeoComSBDBHelper.clearInvalidRecords]> pilotid: {}", pilotid);
//		synchronized (connectionSource) {
//			try {
//				TransactionManager.callInTransaction(connectionSource, new Callable<Void>() {
//					public Void call() throws Exception {
//						// Remove all assets from this owner before adding the new set.
//						final DeleteBuilder<NeoComAsset, String> deleteBuilder = getAssetDao().deleteBuilder();
//						deleteBuilder.where().eq("ownerId", pilotid);
//						int count = deleteBuilder.delete();
//						logger.info("-- [NeoComSBDBHelper.clearInvalidAssets]> Invalid assets cleared for owner {}: {}", pilotid, count);
//
//						// Replace the owner to vake the assets valid.
//						final UpdateBuilder<NeoComAsset, String> updateBuilder = getAssetDao().updateBuilder();
//						updateBuilder.updateColumnValue("ownerId", pilotid)
//								.where().eq("ownerId", (pilotid * -1));
//						count = updateBuilder.update();
//						logger.info("-- [NeoComSBDBHelper.replaceAssets]> Replace owner {} for assets: {}", pilotid, count);
//						return null;
//					}
//				});
//			} catch (final SQLException ex) {
//				logger.warn("W> [NeoComSBDBHelper.replaceAssets]> Problem replacing records. " + ex.getMessage());
//			} finally {
//				logger.info("<< [NeoComSBDBHelper.replaceAssets]");
//			}
//		}
//	}
//
//	public synchronized void replaceBlueprints(final long pilotid) {
//		logger.info(">> [NeoComSBDBHelper.replaceBlueprints]> pilotid: {}", pilotid);
//		synchronized (connectionSource) {
//			try {
//				TransactionManager.callInTransaction(connectionSource, new Callable<Void>() {
//					public Void call() throws Exception {
//						// Remove all assets that do not have a valid owner.
//						final UpdateBuilder<NeoComBlueprint, String> updateBuilder = getBlueprintDao().updateBuilder();
//						updateBuilder.updateColumnValue("ownerId", pilotid)
//								.where().eq("ownerId", (pilotid * -1));
//						int count = updateBuilder.update();
//						logger.info("-- [NeoComSBDBHelper.replaceBlueprints]> Replace owner {} for blueprints: {}", pilotid, count);
//						return null;
//					}
//				});
//			} catch (final SQLException ex) {
//				logger.warn("W> [NeoComSBDBHelper.replaceBlueprints]> Problem replacing records. " + ex.getMessage());
//			} finally {
//				logger.info("<< [NeoComSBDBHelper.replaceBlueprints]");
//			}
//		}
//	}
//
//
//	private int readDatabaseVersion() {
//		// Access the version object persistent on the database.
//		try {
//			Dao<DatabaseVersion, String> versionDao = this.getVersionDao();
//			QueryBuilder<DatabaseVersion, String> queryBuilder = versionDao.queryBuilder();
//			PreparedQuery<DatabaseVersion> preparedQuery = queryBuilder.prepare();
//			List<DatabaseVersion> versionList = versionDao.query(preparedQuery);
//			if (versionList.size() > 0) {
//				DatabaseVersion version = versionList.get(0);
//				return version.getVersionNumber();
//			} else
//				return 0;
//		} catch (SQLException sqle) {
//			logger.warn("W- [NeoComSBDBHelper.readDatabaseVersion]> Database exception: " + sqle.getMessage());
//			return 0;
//		}
//	}
//
//	@Override
//	public String toString() {
//		StringBuffer buffer = new StringBuffer("NeoComSBDBHelper [");
//		final String localConnectionDescriptor = hostName + "/" + databaseName + "?user=" + databaseUser + "&password=" + databasePassword;
//		buffer.append("Descriptor: ").append(localConnectionDescriptor);
//		buffer.append("]");
//		//		buffer.append("->").append(super.toString());
//		return buffer.toString();
//	}