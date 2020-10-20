package org.dimensinfin.eveonline.neocom.infinity.adapter;

import java.sql.SQLException;
import java.util.Objects;
import javax.annotation.PostConstruct;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.support.ConnectionSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import org.dimensinfin.eveonline.neocom.database.entities.Credential;
import org.dimensinfin.eveonline.neocom.database.repositories.CredentialRepository;

@Component
public class CredentialRepositoryWrapper {
	protected final Dao<Credential, String> credentialDao;
	private CredentialRepository singleton;

	@Autowired
	public CredentialRepositoryWrapper( final NeoComDBWrapper neocomDBAdapter ) throws SQLException {
		this.credentialDao = neocomDBAdapter.getSingleton().getCredentialDao();
	}

	public CredentialRepository getSingleton() {
		return Objects.requireNonNull( this.singleton );
	}

	@PostConstruct
	void build() {
		this.singleton = new CredentialRepository.Builder()
				.withCredentialDao( this.credentialDao )
				.build();
	}
}
