package org.dimensinfin.eveonline.neocom.infinity.backend.mining.persistence;

import java.sql.SQLException;

import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import org.dimensinfin.eveonline.neocom.database.entities.MiningExtractionEntity;
import org.dimensinfin.eveonline.neocom.database.repositories.MiningRepository;

public class SupportMiningRepository extends MiningRepository {
	protected ConnectionSource connection4Transaction;

	public long countAllMiningExtractions() throws SQLException {
		return this.miningExtractionDao.countOf();
	}

	public int deleteAll() throws SQLException {
		final int recordCount = (int) this.countAllMiningExtractions();
		TableUtils.clearTable( this.connection4Transaction, MiningExtractionEntity.class );
		return recordCount;
	}
}
