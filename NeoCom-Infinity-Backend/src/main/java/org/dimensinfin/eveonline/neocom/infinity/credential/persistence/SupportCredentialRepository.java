package org.dimensinfin.eveonline.neocom.infinity.credential.persistence;

import java.sql.SQLException;
import javax.validation.constraints.NotNull;

import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import org.dimensinfin.eveonline.neocom.database.entities.Credential;
import org.dimensinfin.eveonline.neocom.database.repositories.CredentialRepository;
import org.dimensinfin.eveonline.neocom.infinity.adapter.implementers.SBNeoComDBAdapter;
import org.dimensinfin.logging.LogWrapper;

@Component
public class SupportCredentialRepository extends CredentialRepository {
	protected ConnectionSource connection4Transaction;

	// - C O N S T R U C T O R S
	@Autowired
	public SupportCredentialRepository( @NotNull final SBNeoComDBAdapter neoComDatabaseService ) {
		super( neoComDatabaseService );
		try {
			this.connection4Transaction = neoComDatabaseService.getConnectionSource();
		} catch (final SQLException sqle) {
			LogWrapper.error( sqle );
		}
	}

	public int deleteAll() throws SQLException {
		final int recordCount = this.accessAllCredentials().size();
		TableUtils.clearTable( this.connection4Transaction, Credential.class );
		return recordCount;
	}

	//	// - B U I L D E R
	//	public static class Builder {
	//		protected SupportCredentialRepository onConstruction;
	//
	//		// - C O N S T R U C T O R S
	//		public Builder() {
	//			this.onConstruction = new SupportCredentialRepository();
	//		}
	//
	//		public SupportCredentialRepository build() {
	//			Objects.requireNonNull( this.onConstruction.credentialDao );
	//			return this.onConstruction;
	//		}
	//
	//		public SupportCredentialRepository.Builder withConnection4Transaction( final ConnectionSource connection ) {
	//			Objects.requireNonNull( connection );
	//			this.onConstruction.connection4Transaction = connection;
	//			return this;
	//		}
	//
	//		public SupportCredentialRepository.Builder withCredentialDao( final Dao<Credential, String> credentialDao ) {
	//			Objects.requireNonNull( credentialDao );
	//			this.onConstruction.credentialDao = credentialDao;
	//			return this;
	//		}
	//	}
}
