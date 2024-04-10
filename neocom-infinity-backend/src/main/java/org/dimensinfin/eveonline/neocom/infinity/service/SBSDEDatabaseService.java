package org.dimensinfin.eveonline.neocom.infinity.service;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.text.MessageFormat;
import javax.validation.constraints.NotNull;

import com.google.inject.Inject;
import com.google.inject.name.Named;

import org.dimensinfin.eveonline.neocom.database.core.ISDEDatabaseService;
import org.dimensinfin.eveonline.neocom.database.core.ISDEStatement;
import org.dimensinfin.eveonline.neocom.infinity.service.domain.SBRawStatement;
import org.dimensinfin.logging.LogWrapper;

public class SBSDEDatabaseService implements ISDEDatabaseService {
	private final String databasePath;

	private Connection connectionSource;

	// - C O N S T R U C T O R S
	@Inject
	public SBSDEDatabaseService( final @NotNull @Named("SDEDatabasePath") String databasePath ) throws SQLException {
		this.databasePath = databasePath;
		this.openSDEDB();
	}

	// - G E T T E R S   &   S E T T E R S
	@Override
	public String getDatabaseVersion() {
		return "20201231-Integration";
	}

	@Override
	public ISDEStatement constructStatement( final String query, final String[] parameters ) throws SQLException {
		return new SBRawStatement( this.getSDEConnection(), query, parameters );
	}

	protected Connection getSDEConnection() throws SQLException {
		if (null == this.connectionSource) this.openSDEDB();
		return this.connectionSource;
	}

	/**
	 * Open a new pooled JDBC datasource connection list and stores its reference for use of the whole set of
	 * services. Being a pooled connection it can create as many connections as required to do requests in
	 * parallel to the database instance. This only is effective for MySql databases.
	 * <p>
	 * Check database definition before trying to open the database.
	 */
	protected void openSDEDB() throws SQLException {
		LogWrapper.enter();
		if (null == this.connectionSource) {
			this.createConnectionSource();
			LogWrapper.info( MessageFormat.format(
					"Opened database {0} successfully with version {1}.",
					this.databasePath,
					this.getDatabaseVersion() )
			);
		}
		LogWrapper.exit();
	}

	private void createConnectionSource() throws SQLException {
		try {
			Class.forName( "org.sqlite.JDBC" );
		} catch (final ClassNotFoundException cnfe) {
			throw new SQLException( MessageFormat.format( "Cannot create connection. {0}.", cnfe.getMessage() ) );
		}
		//		try {
		final String dbPath = new File( System.getProperty( "user.dir" ) + this.databasePath ).getAbsolutePath();
		this.connectionSource = DriverManager.getConnection( "jdbc:sqlite:" + dbPath );
		this.connectionSource.setAutoCommit( false );
		//		} catch (IOException e) {
		//			throw new SQLException( MessageFormat.format( "Cannot open resource file {0}.", this.databasePath ) );
		//		}
	}
}