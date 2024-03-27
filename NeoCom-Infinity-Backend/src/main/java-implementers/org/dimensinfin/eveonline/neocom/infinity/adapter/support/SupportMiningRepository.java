package org.dimensinfin.eveonline.neocom.infinity.adapter.support;

import java.sql.SQLException;
import java.util.Objects;

import com.j256.ormlite.dao.Dao;
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

	// - B U I L D E R
	public static class Builder {
		protected SupportMiningRepository onConstruction;

		public Builder() {
			this.onConstruction = new SupportMiningRepository();
		}

		public SupportMiningRepository build() {
			Objects.requireNonNull( this.onConstruction.miningExtractionDao );
			return this.onConstruction;
		}

		public SupportMiningRepository.Builder withConnection4Transaction( final ConnectionSource connection ) {
			Objects.requireNonNull( connection );
			this.onConstruction.connection4Transaction = connection;
			return this;
		}

		public SupportMiningRepository.Builder withMiningExtractionDao( final Dao<MiningExtractionEntity, String> miningExtractionDao ) {
			Objects.requireNonNull( miningExtractionDao );
			this.onConstruction.miningExtractionDao = miningExtractionDao;
			return this;
		}
	}
}
