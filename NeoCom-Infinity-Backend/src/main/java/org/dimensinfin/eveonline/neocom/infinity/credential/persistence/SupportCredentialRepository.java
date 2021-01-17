package org.dimensinfin.eveonline.neocom.infinity.credential.persistence;

import java.sql.SQLException;
import javax.validation.constraints.NotNull;

import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;

import org.dimensinfin.eveonline.neocom.database.NeoComDatabaseService;
import org.dimensinfin.eveonline.neocom.database.entities.Credential;
import org.dimensinfin.eveonline.neocom.database.repositories.CredentialRepository;
import org.dimensinfin.logging.LogWrapper;

@Profile("acceptance")
//@Component
public class SupportCredentialRepository extends CredentialRepository {
	protected ConnectionSource connection4Transaction;

	// - C O N S T R U C T O R S
	@Autowired
	public SupportCredentialRepository( @NotNull final NeoComDatabaseService neoComDatabaseService ) {
		super( neoComDatabaseService );
		try {
			this.connection4Transaction = neoComDatabaseService.getCredentialDao().getConnectionSource();
		} catch (final SQLException sqle) {
			LogWrapper.error( sqle );
		}
	}

	public int deleteAll() throws SQLException {
		final int recordCount = this.accessAllCredentials().size();
		TableUtils.clearTable( this.connection4Transaction, Credential.class );
		return recordCount;
	}
}
