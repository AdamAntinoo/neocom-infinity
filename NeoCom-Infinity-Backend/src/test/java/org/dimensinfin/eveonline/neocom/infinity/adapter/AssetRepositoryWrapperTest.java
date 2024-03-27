package org.dimensinfin.eveonline.neocom.infinity.adapter;


import java.sql.SQLException;
import java.util.UUID;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.support.ConnectionSource;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import org.dimensinfin.eveonline.neocom.database.entities.NeoAsset;
import org.dimensinfin.eveonline.neocom.infinity.adapter.implementers.SBNeoComDBAdapter;

public class AssetRepositoryWrapperTest {
	@Test
	public void build() throws SQLException {
		final NeoComDBWrapper neocomDBAdapter = Mockito.mock( NeoComDBWrapper.class );
		final SBNeoComDBAdapter sbNeoComDBAdapter = Mockito.mock( SBNeoComDBAdapter.class );
		Mockito.when( neocomDBAdapter.getSingleton() ).thenReturn( sbNeoComDBAdapter );
		final Dao<NeoAsset, UUID> assetDao = Mockito.mock( Dao.class );
		final ConnectionSource connectionSource = Mockito.mock( ConnectionSource.class );
		Mockito.when( sbNeoComDBAdapter.getAssetDao() ).thenReturn( assetDao );
		Mockito.when( sbNeoComDBAdapter.getConnectionSource() ).thenReturn( connectionSource );
		final AssetRepositoryWrapper assetRepositoryWrapper = new AssetRepositoryWrapper( neocomDBAdapter );

		assetRepositoryWrapper.build();
		Assertions.assertNotNull( assetRepositoryWrapper.getSingleton() );
	}

	@Test
	public void constructionContract() throws SQLException {
		// Given
		final NeoComDBWrapper neocomDBAdapterWrapper = Mockito.mock( NeoComDBWrapper.class );
		final SBNeoComDBAdapter neocomDBAdapter = Mockito.mock( SBNeoComDBAdapter.class );
		final Dao<NeoAsset, UUID> assetDao = Mockito.mock( Dao.class );
		final ConnectionSource connectionSource = Mockito.mock( ConnectionSource.class );
		// When
		Mockito.when( neocomDBAdapterWrapper.getSingleton() ).thenReturn( neocomDBAdapter );
		Mockito.when( neocomDBAdapter.getAssetDao() ).thenReturn( assetDao );
		Mockito.when( neocomDBAdapter.getConnectionSource() ).thenReturn( connectionSource );
		// Tests
		final AssetRepositoryWrapper assetRepositoryWrapper = new AssetRepositoryWrapper( neocomDBAdapterWrapper );
		Assertions.assertNotNull( assetRepositoryWrapper );
	}
}
