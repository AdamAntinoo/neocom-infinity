package org.dimensinfin.eveonline.neocom.infinity.adapter;

import java.sql.SQLException;
import java.util.Objects;
import javax.annotation.PostConstruct;

import org.springframework.stereotype.Component;

import org.dimensinfin.eveonline.neocom.infinity.adapter.implementers.SBNeoComDBAdapter;
import org.dimensinfin.eveonline.neocom.infinity.core.exception.NeoComRuntimeBackendException;
import org.dimensinfin.logging.LogWrapper;

@Deprecated
@Component
public class NeoComDBWrapper {
	//	private String connectionDescriptor;
	private SBNeoComDBAdapter singleton;

	//	@Autowired
	//	public NeoComDBWrapper( @Value("${P.runtime.configuration.database.connection}") final String connectionDescriptor ) {
	//		LogWrapper.enter();
	//		this.connectionDescriptor = Objects.requireNonNull( connectionDescriptor );
	//		LogWrapper.exit( this.connectionDescriptor );
	//	}

	// - G E T T E R S   &   S E T T E R S
	public SBNeoComDBAdapter getSingleton() {
		return Objects.requireNonNull( this.singleton );
	}

	//	private boolean isHerokuConfiguration() throws SQLException {
	//		final String neocomUrl = System.getenv( "JDBC_DATABASE_URL" );
	//		if (null != neocomUrl) {
	//			this.singleton = new SBNeoComDBAdapter.Builder()
	//					.optionalDatabaseType( "postgres" )
	//					.optionalDatabaseUrl( neocomUrl )
	//					.build();
	//			this.singleton.onCreate( this.singleton.getConnectionSource() );
	//			return true;
	//		}
	//		return false;
	//	}

	/**
	 * Read the database configuration from an external environment variable. If not defined the raise an initialization exception.
	 */
	@PostConstruct
	private void build() throws SQLException {
		LogWrapper.enter();
		final String connectionDescriptor = System.getenv( "NEOCOM_DATABASE_URL" );
		if (null == connectionDescriptor)
			throw new NeoComRuntimeBackendException( NeoComRuntimeBackendException.errorINITIALIZATIONEXCEPTION(
					new RuntimeException( "Required environment variable 'NEOCOM_DATABASE_URL' is not declared." )
			) );
		// Check if there is Heroku running configuration.
		//		if (!this.isHerokuConfiguration()) {
		//			final String databaseHost = this.configurationService.getResourceString( "P.database.neocom.databasehost" );
		//			final String databasePath = this.configurationService.getResourceString( "P.database.neocom.databasepath" );
		//			final String user = this.configurationService.getResourceString( "P.database.neocom.databaseuser" );
		//			final String password = this.configurationService.getResourceString( "P.database.neocom.databasepassword" );
		this.singleton = new SBNeoComDBAdapter.Builder()
				.withDatabaseConnectionDescriptor( connectionDescriptor )
				//					.withDatabasePath( databasePath )
				//					.withDatabaseUser( user )
				//					.withDatabasePassword( password )
				//					.optionalDatabaseType( this.configurationService.getResourceString( "P.database.neocom.databasetype" ) )
				//					.optionalDatabaseOptions(
				//							this.configurationService.getResourceString( "P.database.neocom.databaseoptions" )
				//					)
				.build();
		this.getSingleton().onCreate( this.getSingleton().getConnectionSource() );
		//		}
		LogWrapper.exit();
	}
}
