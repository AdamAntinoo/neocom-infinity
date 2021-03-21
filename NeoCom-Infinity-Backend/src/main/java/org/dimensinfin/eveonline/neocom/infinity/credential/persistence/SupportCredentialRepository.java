package org.dimensinfin.eveonline.neocom.infinity.credential.persistence;

import java.sql.SQLException;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.validation.constraints.NotNull;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.table.TableUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import org.dimensinfin.eveonline.neocom.database.NeoComDatabaseService;
import org.dimensinfin.eveonline.neocom.database.entities.Credential;
import org.dimensinfin.logging.LogWrapper;

@Profile({ "acceptance", "dev" })
@Component("SupportCredentialRepository")
public class SupportCredentialRepository {
	private Dao<Credential, String> credentialDao;

	// - C O N S T R U C T O R S
	@Autowired
	public SupportCredentialRepository( @NotNull final NeoComDatabaseService neoComDatabaseService ) {
		try {
			this.credentialDao = Objects.requireNonNull( neoComDatabaseService.getCredentialDao() );
		} catch (final SQLException throwables) {
			throwables.printStackTrace();
		}
	}

	public List<Credential> accessAllCredentials() {
		final List<Credential> credentialList = new ArrayList<>();
		try {
			return this.credentialDao.queryForAll();
		} catch (final SQLException sqle) {
			LogWrapper.error( sqle );
		}
		return credentialList;

	}

	public int deleteAll() throws SQLException {
		final int recordCount = this.accessAllCredentials().size();
		TableUtils.clearTable( this.credentialDao.getConnectionSource(), Credential.class );
		return recordCount;
	}

	public List<Credential> findAllByServer( final String esiServer ) {
		try {
			return this.credentialDao.queryForEq( "dataSource", esiServer.toLowerCase() );
		} catch (final SQLException sqle) {
			LogWrapper.error( MessageFormat.format( "Exception reading all Credentials. {0}", esiServer ), sqle );
			return new ArrayList<>();
		}
	}

	public Credential findCredentialById( final String credentialId ) throws SQLException {
		return this.credentialDao.queryForId( credentialId );
	}

	public void persist( final Credential record ) throws SQLException {
		if (null != record) {
			record.timeStamp();
			this.credentialDao.createOrUpdate( record );
		}
	}
}
