package org.dimensinfin.eveonline.neocom.infinity.mining.rest.support;

import java.sql.SQLException;

import org.dimensinfin.eveonline.neocom.database.entities.MiningExtractionEntity;
import org.dimensinfin.eveonline.neocom.database.repositories.MiningRepository;
import org.dimensinfin.eveonline.neocom.infinity.config.security.CredentialDetailsService;
import org.dimensinfin.eveonline.neocom.infinity.config.security.NeoComAuthenticationProvider;
import org.dimensinfin.eveonline.neocom.service.logger.NeoComLogger;

//@Service
public class MiningExtractionsServiceSupport {
	private final NeoComAuthenticationProvider neoComAuthenticationProvider;
	private final CredentialDetailsService credentialDetailsService;
	private final MiningRepository miningRepository;

// - C O N S T R U C T O R S
	public MiningExtractionsServiceSupport( final NeoComAuthenticationProvider neoComAuthenticationProvider,
	                                        final CredentialDetailsService credentialDetailsService,
	                                        final MiningRepository miningRepository ) {
		this.miningRepository = miningRepository;
		this.credentialDetailsService = credentialDetailsService;
		this.neoComAuthenticationProvider = neoComAuthenticationProvider;
	}

	public MiningExtractionCountResponse deleteAllMiningExtractions() {
		//		try {
		//			return new MiningExtractionCountResponse.Builder()
		//					.withMiningExtractionCount( this.miningRepository.deleteAll() )
		//					.build();
		//		} catch (final SQLException sqle) {
		//			NeoComLogger.error( sqle );
		//			throw new NeoComSBException( "SQL exception while deleting all mining extractions." );
		//		}
		return null;
	}

	public StoreMiningExtractionResponse storeMiningExtraction( final MiningExtractionEntity miningExtractionEntity ) {
		try {
			this.miningRepository.persist( miningExtractionEntity );
			return new StoreMiningExtractionResponse.Builder()
					.withMiningExtractionsCount( 1 ).build();
		} catch (final SQLException sqle) {
			NeoComLogger.error( sqle );
			return new StoreMiningExtractionResponse.Builder()
					.withMiningExtractionsCount( 0 ).build();
		}
	}
}
