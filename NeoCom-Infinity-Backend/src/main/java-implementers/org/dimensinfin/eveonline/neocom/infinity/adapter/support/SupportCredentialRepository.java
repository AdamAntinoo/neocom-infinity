package org.dimensinfin.eveonline.neocom.infinity.adapter.support;

import java.sql.SQLException;
import java.util.Objects;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import org.dimensinfin.eveonline.neocom.database.entities.Credential;
import org.dimensinfin.eveonline.neocom.database.repositories.CredentialRepository;

public class SupportCredentialRepository extends CredentialRepository {
	protected ConnectionSource connection4Transaction;

	public int deleteAll() throws SQLException {
		final int recordCount = this.accessAllCredentials().size();
		TableUtils.clearTable( this.connection4Transaction, Credential.class );
		return recordCount;
	}

	// - B U I L D E R
	public static class Builder {
		protected SupportCredentialRepository onConstruction;

		public Builder() {
			this.onConstruction = new SupportCredentialRepository();
		}

		public SupportCredentialRepository build() {
			Objects.requireNonNull( this.onConstruction.credentialDao );
			return this.onConstruction;
		}

		public SupportCredentialRepository.Builder withConnection4Transaction( final ConnectionSource connection ) {
			Objects.requireNonNull( connection );
			this.onConstruction.connection4Transaction = connection;
			return this;
		}

		public SupportCredentialRepository.Builder withCredentialDao( final Dao<Credential, String> credentialDao ) {
			Objects.requireNonNull( credentialDao );
			this.onConstruction.credentialDao = credentialDao;
			return this;
		}
	}
}
