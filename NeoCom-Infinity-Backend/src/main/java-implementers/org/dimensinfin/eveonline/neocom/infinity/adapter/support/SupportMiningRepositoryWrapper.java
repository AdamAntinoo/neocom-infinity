package org.dimensinfin.eveonline.neocom.infinity.adapter.support;

import java.sql.SQLException;
import java.util.Objects;
import javax.annotation.PostConstruct;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.support.ConnectionSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import org.dimensinfin.eveonline.neocom.database.entities.MiningExtractionEntity;
import org.dimensinfin.eveonline.neocom.infinity.adapter.NeoComDBWrapper;

@Component
public class SupportMiningRepositoryWrapper {
	protected final Dao<MiningExtractionEntity, String> miningExtractionDao;
	protected final ConnectionSource connection4Transaction;
	private SupportMiningRepository singleton;

	@Autowired
	public SupportMiningRepositoryWrapper( final NeoComDBWrapper neocomDBAdapter ) throws SQLException {
		this.miningExtractionDao = neocomDBAdapter.getSingleton().getMiningExtractionDao();
		this.connection4Transaction = neocomDBAdapter.getSingleton().getConnectionSource();
	}

	public SupportMiningRepository getSingleton() {
		return Objects.requireNonNull( this.singleton );
	}

	@PostConstruct
	void build() {
		this.singleton = new SupportMiningRepository.Builder()
				.withMiningExtractionDao( this.miningExtractionDao )
				.withConnection4Transaction( this.connection4Transaction )
				.build();
	}
}
