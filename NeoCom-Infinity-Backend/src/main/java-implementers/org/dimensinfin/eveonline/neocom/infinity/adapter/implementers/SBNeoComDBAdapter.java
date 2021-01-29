package org.dimensinfin.eveonline.neocom.infinity.adapter.implementers;

import java.sql.SQLException;
import java.text.MessageFormat;
import java.util.Objects;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.jdbc.JdbcPooledConnectionSource;
import com.j256.ormlite.support.ConnectionSource;
import org.springframework.stereotype.Component;

import org.dimensinfin.eveonline.neocom.database.NeoComDatabaseService;
import org.dimensinfin.eveonline.neocom.database.entities.Credential;
import org.dimensinfin.eveonline.neocom.database.entities.DatabaseVersion;
import org.dimensinfin.eveonline.neocom.database.entities.MiningExtractionEntity;
import org.dimensinfin.eveonline.neocom.database.entities.NeoAsset;
import org.dimensinfin.eveonline.neocom.database.entities.PilotPreferencesEntity;
import org.dimensinfin.eveonline.neocom.industry.persistence.JobEntity;
import org.dimensinfin.eveonline.neocom.infinity.core.exception.NeoComRuntimeBackendException;
import org.dimensinfin.eveonline.neocom.loyalty.persistence.LoyaltyOfferEntity;
import org.dimensinfin.logging.LogWrapper;

/**
 * NeoCom private database connector that will have the same api as the connector to be used on Android. This
 * version already uses the postgres database JDBC implementation instead the SQLite copied from the Android
 * platform.
 * The class will encapsulate all dao and connection access.
 * There are two ways to configure the service. They are exclusive so using one removed the other even if configured:
 * The first option is to specify each of the database values with their own setter. They are the name, type, path,
 * user and password.
 * The second option is to declare all that parameters inside a single database url.
 *
 * The different parameters configuration is deprecated and recommended to use a single URL database descriptor for configuration. This way the
 * validation and setup process has not different steps and missing data is detected earlier.
 *
 * @author Adam Antinoo
 */
@Component
public class SBNeoComDBAdapter implements NeoComDatabaseService {
	private String databaseConnectionDescriptor;
	private boolean isOpen = false;
	private JdbcPooledConnectionSource connectionSource;

	private Dao<DatabaseVersion, String> versionDao;
	private Dao<Credential, String> credentialDao;
	private Dao<NeoAsset, UUID> assetDao;
	private Dao<MiningExtractionEntity, String> miningExtractionDao;
	private Dao<PilotPreferencesEntity, UUID> pilotPreferencesDao;
	private Dao<JobEntity, String> industryJobDao;
	private Dao<LoyaltyOfferEntity, Integer> loyaltyOfferDao;

	// - C O N S T R U C T O R S
	protected SBNeoComDBAdapter() {
		LogWrapper.enter();
		final String connectionDescriptor = System.getenv( "NEOCOM_DATABASE_URL" );
		if (null == connectionDescriptor)
			throw new NeoComRuntimeBackendException( NeoComRuntimeBackendException.errorINITIALIZATIONEXCEPTION(
					new RuntimeException( "Required environment variable 'NEOCOM_DATABASE_URL' is not declared." )
			) );
		this.databaseConnectionDescriptor = Objects.requireNonNull( connectionDescriptor );
		LogWrapper.exit();
	}

	// - G E T T E R S   &   S E T T E R S
	@Override
	public Dao<NeoAsset, UUID> getAssetDao() throws SQLException {
		if (null == this.assetDao) {
			this.assetDao = DaoManager.createDao( this.getConnectionSource(), NeoAsset.class );
		}
		return this.assetDao;
	}

	@Override
	public Dao<Credential, String> getCredentialDao() throws SQLException {
		if (null == this.credentialDao) {
			this.credentialDao = DaoManager.createDao( this.getConnectionSource(), Credential.class );
		}
		return this.credentialDao;
	}

	@Override
	public Dao<JobEntity, String> getIndustryJobDao() throws SQLException {
		if (null == this.industryJobDao) {
			this.industryJobDao = DaoManager.createDao( this.getConnectionSource(), JobEntity.class );
		}
		return this.industryJobDao;
	}

	@Override
	public Dao<MiningExtractionEntity, String> getMiningExtractionDao() throws SQLException {
		if (null == this.miningExtractionDao) {
			this.miningExtractionDao = DaoManager.createDao( this.getConnectionSource(), MiningExtractionEntity.class );
		}
		return this.miningExtractionDao;
	}

	@Override
	public Dao<PilotPreferencesEntity, UUID> getPilotPreferencesDao() throws SQLException {
		if (null == this.pilotPreferencesDao) {
			this.pilotPreferencesDao = DaoManager.createDao( this.getConnectionSource(), PilotPreferencesEntity.class );
		}
		return this.pilotPreferencesDao;
	}

	@Override
	public Dao<DatabaseVersion, String> getVersionDao() throws SQLException {
		if (null == this.versionDao) {
			this.versionDao = DaoManager.createDao( this.getConnectionSource(), DatabaseVersion.class );
		}
		return this.versionDao;
	}

	@Override
	public Dao<LoyaltyOfferEntity, Integer> getLoyaltyOfferDao() throws SQLException {
		if (null == this.loyaltyOfferDao) {
			this.loyaltyOfferDao = DaoManager.createDao( this.getConnectionSource(), LoyaltyOfferEntity.class );
		}
		return this.loyaltyOfferDao;
	}

	public ConnectionSource getConnectionSource() throws SQLException {
		if (null == this.connectionSource) this.openNeoComDB();
		return this.connectionSource;
	}

	public void onCreate( final ConnectionSource connectionSource ) {
		if (null != connectionSource)
			LogWrapper.info( "Database OnCreate not in use since Liquibase is taking care of database leveling." );
	}

	/**
	 * Creates and configures the connection pool to access the application database.
	 */
	private boolean createConnectionSource() throws SQLException {
		this.connectionSource = new JdbcPooledConnectionSource( this.databaseConnectionDescriptor );
		// Configure the new connection pool.
		this.connectionSource.setMaxConnectionAgeMillis(
				TimeUnit.MINUTES.toMillis( 5 ) ); // only keep the connections open for 5 minutes
		this.connectionSource.setCheckConnectionsEveryMillis(
				TimeUnit.SECONDS.toMillis( 60 ) ); // change the check-every milliseconds from 30 seconds to 60
		this.connectionSource.setTestBeforeGet( true );
		return true;
	}

	/**
	 * Open a new pooled JDBC datasource connection list and stores its reference for use of the whole set of
	 * services. Being a pooled connection it can create as many connections as required to do requests in
	 * parallel to the database instance. This only is effective for MySql databases.
	 */
	private void openNeoComDB() throws SQLException {
		LogWrapper.enter();
		if (!this.isOpen) {
			this.isOpen = this.createConnectionSource();
			LogWrapper.info( MessageFormat.format( "Opened database {0} successfully with version {1}.",
					this.databaseConnectionDescriptor, "production" ) );
		}
		LogWrapper.exit( MessageFormat.format( "Current database state: {0}", this.isOpen ) );
	}

	// - B U I L D E R
	public static class Builder {
		private final SBNeoComDBAdapter onConstruction;

		// - C O N S T R U C T O R S
		public Builder() {
			this.onConstruction = new SBNeoComDBAdapter();
		}

		public SBNeoComDBAdapter build() /*throws SQLException */ {
			return this.onConstruction;
		}

		public SBNeoComDBAdapter.Builder withDatabaseConnectionDescriptor( final String databaseConnectionDescriptor ) {
			this.onConstruction.databaseConnectionDescriptor = Objects.requireNonNull( databaseConnectionDescriptor );
			return this;
		}
	}
}
