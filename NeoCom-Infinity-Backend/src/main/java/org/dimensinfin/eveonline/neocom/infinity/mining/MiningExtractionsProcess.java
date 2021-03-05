package org.dimensinfin.eveonline.neocom.infinity.mining;

import java.util.Objects;

import org.apache.commons.lang3.builder.HashCodeBuilder;

import org.dimensinfin.eveonline.neocom.database.entities.Credential;
import org.dimensinfin.eveonline.neocom.infinity.backend.scheduler.jobs.NeoComBackendJob;
import org.dimensinfin.eveonline.neocom.infinity.core.exception.NeoComSBException;
import org.dimensinfin.eveonline.neocom.service.logger.NeoComLogger;

public class MiningExtractionsProcess extends NeoComBackendJob {
	private Credential credential;

// - C O N S T R U C T O R S
	private MiningExtractionsProcess() {}

// - G E T T E R S   &   S E T T E R S
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
	public boolean equals( final Object o ) {
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
			//			new MiningExtractionPersistent.Builder()
			//					.withMiningRepository( this.jobServicePackager.getMiningRepository() ).build()
			//					.persistMiningExtractions( new MiningExtractionDownloader.Builder()
			//							.withCredential( this.credential )
			//							.withEsiDataProvider( this.jobServicePackager.getEsiDataService() )
			//							.withLocationCatalogService( this.jobServicePackager.getLocationCatalogService() ).build()
			//							.downloadMiningExtractions() );
		} catch (final RuntimeException rtex) {
			NeoComLogger.error( rtex );
			throw new NeoComSBException( rtex );
		}
		NeoComLogger.exit();
		return true;
	}

	// - B U I L D E R
	public static class Builder extends NeoComBackendJob.Builder<MiningExtractionsProcess, MiningExtractionsProcess.Builder> {
		private MiningExtractionsProcess onConstruction;

		// - C O N S T R U C T O R S
		public Builder() {
			this.onConstruction = new MiningExtractionsProcess();
		}

		@Override
		public MiningExtractionsProcess build() {
			return this.onConstruction;
		}

		public MiningExtractionsProcess.Builder withCredential( final Credential credential ) {
			this.getActual().credential = Objects.requireNonNull( credential );
			return this;
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
	}
}
