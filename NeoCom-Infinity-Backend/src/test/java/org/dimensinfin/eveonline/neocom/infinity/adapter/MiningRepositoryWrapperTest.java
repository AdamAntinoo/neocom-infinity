package org.dimensinfin.eveonline.neocom.infinity.adapter;

import java.sql.SQLException;

import com.j256.ormlite.dao.Dao;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import org.dimensinfin.eveonline.neocom.database.entities.MiningExtractionEntity;
import org.dimensinfin.eveonline.neocom.infinity.adapter.implementers.SBNeoComDBAdapter;
import org.dimensinfin.eveonline.neocom.service.LocationCatalogService;

public class MiningRepositoryWrapperTest {

	private NeoComDBWrapper neocomDBAdapter;
	private SBNeoComDBAdapter sbNeoComDBAdapter;
	private Dao<MiningExtractionEntity, String> miningExtractionDao;
//	private LocationCatalogService locationCatalogServiceWrapper;
	private LocationCatalogService locationCatalogService;

	@BeforeEach
	public void beforeEach() throws SQLException {
		// Given
		this.neocomDBAdapter = Mockito.mock( NeoComDBWrapper.class );
		this.sbNeoComDBAdapter = Mockito.mock( SBNeoComDBAdapter.class );
		this.miningExtractionDao = Mockito.mock( Dao.class );
//		this.locationCatalogServiceWrapper = Mockito.mock( LocationCatalogServiceWrapper.class );
		this.locationCatalogService = Mockito.mock( LocationCatalogService.class );
		// When
		Mockito.when( this.neocomDBAdapter.getSingleton() ).thenReturn( this.sbNeoComDBAdapter );
		Mockito.when( this.sbNeoComDBAdapter.getMiningExtractionDao() ).thenReturn( this.miningExtractionDao );
//		Mockito.when( this.locationCatalogService ).thenReturn( this.locationCatalogService );
	}

	@Test
	public void constructionContract() throws SQLException {
		// Test
		final MiningRepositoryWrapper miningRepositoryWrapper = new MiningRepositoryWrapper(
				this.neocomDBAdapter,
				this.locationCatalogService
		);
		// Assertions
		Assertions.assertNotNull( miningRepositoryWrapper );
	}

	@Test
	public void build() throws SQLException {
		// Test
		final MiningRepositoryWrapper miningRepositoryWrapper = new MiningRepositoryWrapper(
				this.neocomDBAdapter,
				this.locationCatalogService
		);
		miningRepositoryWrapper.build();
		// Assertions
		Assertions.assertNotNull( miningRepositoryWrapper.getSingleton() );
	}
}
