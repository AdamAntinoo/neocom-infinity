package org.dimensinfin.eveonline.neocom.infinity.adapter;

import java.sql.SQLException;
import java.util.Objects;
import javax.annotation.PostConstruct;

import com.j256.ormlite.dao.Dao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import org.dimensinfin.eveonline.neocom.database.entities.Credential;
import org.dimensinfin.eveonline.neocom.database.repositories.CredentialRepository;
import org.dimensinfin.eveonline.neocom.infinity.adapter.implementers.SBNeoComDBAdapter;

@Component
public class CredentialRepositoryWrapper {
	protected final Dao<Credential, String> credentialDao;
	private CredentialRepository singleton;
	private final SBNeoComDBAdapter neocomDBManager;

	// - C O N S T R U C T O R S
	@Autowired
	public CredentialRepositoryWrapper( final NeoComDBWrapper neocomDBAdapter ) throws SQLException {
		this.neocomDBManager = neocomDBAdapter.getSingleton();
		this.credentialDao = neocomDBAdapter.getSingleton().getCredentialDao();
	}

	// - G E T T E R S   &   S E T T E R S
	public CredentialRepository getSingleton() {
		return Objects.requireNonNull( this.singleton );
	}

	@PostConstruct
	void build() {
		this.singleton = new CredentialRepository( this.neocomDBManager );
	}
}
