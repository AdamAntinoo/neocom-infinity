package org.dimensinfin.eveonline.neocom.infinity.adapter;

import java.sql.SQLException;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.support.ConnectionSource;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import org.dimensinfin.eveonline.neocom.database.entities.Credential;
import org.dimensinfin.eveonline.neocom.infinity.adapter.implementers.SBNeoComDBAdapter;

public class CredentialRepositoryWrapperTest {
	@Test
	public void build() throws SQLException {
		final NeoComDBWrapper neocomDBAdapter = Mockito.mock( NeoComDBWrapper.class );
		final SBNeoComDBAdapter sbNeoComDBAdapter = Mockito.mock( SBNeoComDBAdapter.class );
		Mockito.when( neocomDBAdapter.getSingleton() ).thenReturn( sbNeoComDBAdapter );
		final Dao<Credential, String> credentialDao = Mockito.mock( Dao.class );
		Mockito.when( sbNeoComDBAdapter.getCredentialDao() ).thenReturn( credentialDao );
		final CredentialRepositoryWrapper credentialRepositoryWrapper = new CredentialRepositoryWrapper( neocomDBAdapter );

		credentialRepositoryWrapper.build();
		Assertions.assertNotNull( credentialRepositoryWrapper.getSingleton() );
	}

	@Test
	public void constructionContract() throws SQLException {
		// Given
		final NeoComDBWrapper neocomDBAdapterWrapper = Mockito.mock( NeoComDBWrapper.class );
		final SBNeoComDBAdapter neocomDBAdapter = Mockito.mock( SBNeoComDBAdapter.class );
		final Dao<Credential, String> credentialDao = Mockito.mock( Dao.class );
		final ConnectionSource connectionSource = Mockito.mock( ConnectionSource.class );
		// When
		Mockito.when( neocomDBAdapterWrapper.getSingleton() ).thenReturn( neocomDBAdapter );
		Mockito.when( neocomDBAdapter.getCredentialDao() ).thenReturn( credentialDao );
		// Tests
		final CredentialRepositoryWrapper credentialRepositoryWrapper = new CredentialRepositoryWrapper( neocomDBAdapterWrapper );
		Assertions.assertNotNull( credentialRepositoryWrapper );
	}

}
