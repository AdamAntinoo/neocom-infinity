package org.dimensinfin.eveonline.neocom.infinity.adapter.support;

import java.sql.SQLException;
import java.util.Objects;
import javax.annotation.PostConstruct;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.support.ConnectionSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import org.dimensinfin.eveonline.neocom.database.entities.Credential;
import org.dimensinfin.eveonline.neocom.infinity.adapter.NeoComDBWrapper;
import org.dimensinfin.eveonline.neocom.infinity.credential.persistence.SupportCredentialRepository;

@Deprecated
@Component
public class SupportCredentialRepositoryWrapper {
	protected final Dao<Credential, String> credentialDao;
	protected final ConnectionSource connection4Transaction;
	private SupportCredentialRepository singleton;

	// - C O N S T R U C T O R S
	@Autowired
	public SupportCredentialRepositoryWrapper( final NeoComDBWrapper neocomDBAdapter ) throws SQLException {
		this.credentialDao = neocomDBAdapter.getSingleton().getCredentialDao();
		this.connection4Transaction = neocomDBAdapter.getSingleton().getConnectionSource();
	}

	// - G E T T E R S   &   S E T T E R S
	public SupportCredentialRepository getSingleton() {
		return Objects.requireNonNull( this.singleton );
	}

	@PostConstruct
	void build() {
		//		this.singleton = new SupportCredentialRepository.Builder()
		//				.withCredentialDao( this.credentialDao )
		//				.withConnection4Transaction( this.connection4Transaction )
		//				.build();
	}
}
