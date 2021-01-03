package org.dimensinfin.eveonline.neocom.infinity.adapter;

import java.sql.SQLException;
import java.util.Objects;
import javax.annotation.PostConstruct;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.support.ConnectionSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import org.dimensinfin.eveonline.neocom.database.entities.MiningExtractionEntity;
import org.dimensinfin.eveonline.neocom.database.repositories.MiningRepository;

@Component
public class MiningRepositoryWrapper {
	private final Dao<MiningExtractionEntity, String> miningExtractionDao;
	private final LocationCatalogServiceWrapper locationCatalogServiceWrapper;
	private MiningRepository singleton;

	@Autowired
	public MiningRepositoryWrapper( final NeoComDBWrapper neocomDBAdapter,
	                                final LocationCatalogServiceWrapper locationCatalogServiceWrapper ) throws SQLException {
		this.miningExtractionDao = neocomDBAdapter.getSingleton().getMiningExtractionDao();
		this.locationCatalogServiceWrapper = locationCatalogServiceWrapper;
	}

	public MiningRepository getSingleton() {
		return Objects.requireNonNull( this.singleton );
	}

	@PostConstruct
	void build() {
		this.singleton = new MiningRepository.Builder()
				.withMiningExtractionDao( this.miningExtractionDao )
				.withLocationCatalogService( this.locationCatalogServiceWrapper.getSingleton() )
				.build();
	}
}
