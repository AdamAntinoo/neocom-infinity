package org.dimensinfin.eveonline.neocom.infinity.adapter;

import java.sql.SQLException;
import java.util.Objects;
import javax.annotation.PostConstruct;

import com.j256.ormlite.dao.Dao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import org.dimensinfin.eveonline.neocom.database.entities.MiningExtractionEntity;
import org.dimensinfin.eveonline.neocom.database.repositories.MiningRepository;
import org.dimensinfin.eveonline.neocom.service.LocationCatalogService;

@Component
public class MiningRepositoryWrapper {
	private final Dao<MiningExtractionEntity, String> miningExtractionDao;
	private final LocationCatalogService locationCatalogService;
	private MiningRepository singleton;

	// - C O N S T R U C T O R S
	@Autowired
	public MiningRepositoryWrapper( final NeoComDBWrapper neocomDBAdapter,
	                                final LocationCatalogService locationCatalogService ) throws SQLException {
		this.miningExtractionDao = neocomDBAdapter.getSingleton().getMiningExtractionDao();
		this.locationCatalogService = locationCatalogService;
	}

	// - G E T T E R S   &   S E T T E R S
	public MiningRepository getSingleton() {
		return Objects.requireNonNull( this.singleton );
	}

	@PostConstruct
	void build() {
		this.singleton = new MiningRepository.Builder()
				.withMiningExtractionDao( this.miningExtractionDao )
				.withLocationCatalogService( this.locationCatalogService )
				.build();
	}
}
