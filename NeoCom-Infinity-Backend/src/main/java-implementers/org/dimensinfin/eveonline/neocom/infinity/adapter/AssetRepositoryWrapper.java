package org.dimensinfin.eveonline.neocom.infinity.adapter;

import java.sql.SQLException;
import java.util.Objects;
import java.util.UUID;
import javax.annotation.PostConstruct;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.support.ConnectionSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import org.dimensinfin.eveonline.neocom.database.entities.NeoAsset;
import org.dimensinfin.eveonline.neocom.database.repositories.AssetRepository;

@Component
public class AssetRepositoryWrapper {
	protected final Dao<NeoAsset, UUID> assetDao;
	protected final ConnectionSource connection4Transaction;
	private AssetRepository singleton;

	@Autowired
	public AssetRepositoryWrapper( final NeoComDBWrapper neocomDBAdapter ) throws SQLException {
		this.assetDao = neocomDBAdapter.getSingleton().getAssetDao();
		this.connection4Transaction = neocomDBAdapter.getSingleton().getConnectionSource();
	}

	public AssetRepository getSingleton() {
		return Objects.requireNonNull( this.singleton );
	}

	@PostConstruct
	void build() {
		this.singleton = new AssetRepository.Builder()
				.withAssetDao( this.assetDao )
				.withConnection4Transaction( this.connection4Transaction )
				.build();
	}
}
