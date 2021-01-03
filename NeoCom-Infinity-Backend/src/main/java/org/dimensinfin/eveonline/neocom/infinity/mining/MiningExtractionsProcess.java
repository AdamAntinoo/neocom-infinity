package org.dimensinfin.eveonline.neocom.infinity.mining;

import java.util.Objects;

import org.apache.commons.lang3.builder.HashCodeBuilder;

import org.dimensinfin.eveonline.neocom.adapter.LocationCatalogService;
import org.dimensinfin.eveonline.neocom.database.entities.Credential;
import org.dimensinfin.eveonline.neocom.database.repositories.MiningRepository;
import org.dimensinfin.eveonline.neocom.infinity.core.exception.NeoComSBException;
import org.dimensinfin.eveonline.neocom.miningextraction.service.MiningExtractionDownloader;
import org.dimensinfin.eveonline.neocom.miningextraction.service.MiningExtractionPersistent;
import org.dimensinfin.eveonline.neocom.provider.ESIDataProvider;
import org.dimensinfin.eveonline.neocom.service.logger.NeoComLogger;
import org.dimensinfin.eveonline.neocom.service.scheduler.domain.Job;

public class MiningExtractionsProcess extends Job {
	private MiningRepository miningRepository;
	private Credential credential;
	private ESIDataProvider esiDataProvider;
	private LocationCatalogService locationCatalogService;

	private MiningExtractionsProcess() {}

	// - J O B
	@Override
	public int getUniqueIdentifier() {
		return new HashCodeBuilder( 19, 137 )
				.appendSuper( super.hashCode() )
				.append( this.getClass().getSimpleName() )
				.append( this.credential.getAccountId() )
				.toHashCode();
	}

	// - C O R E
	@Override
	public int hashCode() {
		return super.hashCode();
	}

	@Override
	public boolean equals( Object o ) {
		return super.equals( o );
	}

	/**
	 * Process the mining extractions data for a Credential.
	 *
	 * It will download and then persist the list of records from the ESI endpoint related to the mining ledger.
	 *
	 * @return true if the process completed successfully.
	 * @throws NeoComSBException any exception intercepted during the process like io problems, network or database.
	 */
	@Override
	public Boolean call() {
		NeoComLogger.enter();
		try {
			new MiningExtractionPersistent.Builder()
					.withMiningRepository( this.miningRepository ).build()
					.persistMiningExtractions( new MiningExtractionDownloader.Builder()
							.withCredential( this.credential )
							.withEsiDataProvider( this.esiDataProvider )
							.withLocationCatalogService( this.locationCatalogService ).build()
							.downloadMiningExtractions() );
		} catch (final RuntimeException rtex) {
			NeoComLogger.error( rtex );
			throw new NeoComSBException( rtex );
		}
		NeoComLogger.exit();
		return true;
	}

	// - B U I L D E R
	public static class Builder extends Job.Builder<MiningExtractionsProcess, MiningExtractionsProcess.Builder> {
		private MiningExtractionsProcess onConstruction;

		public Builder() {
			this.onConstruction = new MiningExtractionsProcess();
		}

		@Override
		protected MiningExtractionsProcess getActual() {
			if (null == this.onConstruction) this.onConstruction = new MiningExtractionsProcess();
			return this.onConstruction;
		}

		@Override
		protected MiningExtractionsProcess.Builder getActualBuilder() {
			return this;
		}

		@Override
		public MiningExtractionsProcess build() {
			Objects.requireNonNull( this.onConstruction.credential );
			Objects.requireNonNull( this.onConstruction.esiDataProvider );
			Objects.requireNonNull( this.onConstruction.locationCatalogService );
			Objects.requireNonNull( this.onConstruction.miningRepository );
			return this.onConstruction;
		}

		public MiningExtractionsProcess.Builder withCredential( final Credential credential ) {
			Objects.requireNonNull( credential );
			this.onConstruction.credential = credential;
			return this;
		}

		public MiningExtractionsProcess.Builder withEsiDataProvider( final ESIDataProvider esiDataProvider ) {
			Objects.requireNonNull( esiDataProvider );
			this.onConstruction.esiDataProvider = esiDataProvider;
			return this;
		}

		public MiningExtractionsProcess.Builder withLocationCatalogService( final LocationCatalogService locationCatalogService ) {
			Objects.requireNonNull( locationCatalogService );
			this.onConstruction.locationCatalogService = locationCatalogService;
			return this;
		}

		public MiningExtractionsProcess.Builder withMiningRepository( final MiningRepository miningRepository ) {
			Objects.requireNonNull( miningRepository );
			this.onConstruction.miningRepository = miningRepository;
			return this;
		}
	}
}
