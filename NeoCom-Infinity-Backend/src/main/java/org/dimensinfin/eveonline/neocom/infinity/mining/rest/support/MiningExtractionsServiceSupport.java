package org.dimensinfin.eveonline.neocom.infinity.mining.rest.support;

import java.sql.SQLException;

import org.springframework.stereotype.Service;

import org.dimensinfin.eveonline.neocom.database.entities.MiningExtractionEntity;
import org.dimensinfin.eveonline.neocom.database.repositories.MiningRepository;
import org.dimensinfin.eveonline.neocom.infinity.adapter.MiningRepositoryWrapper;
import org.dimensinfin.eveonline.neocom.infinity.adapter.support.SupportMiningRepository;
import org.dimensinfin.eveonline.neocom.infinity.adapter.support.SupportMiningRepositoryWrapper;
import org.dimensinfin.eveonline.neocom.infinity.core.exceptions.NeoComSBException;
import org.dimensinfin.eveonline.neocom.infinity.core.security.CredentialDetailsService;
import org.dimensinfin.eveonline.neocom.infinity.core.security.NeoComAuthenticationProvider;
import org.dimensinfin.eveonline.neocom.infinity.support.client.CredentialCountResponse;
import org.dimensinfin.eveonline.neocom.service.logger.NeoComLogger;

@Service
public class MiningExtractionsServiceSupport {
	private final NeoComAuthenticationProvider neoComAuthenticationProvider;
	private final CredentialDetailsService credentialDetailsService;
	private final SupportMiningRepository miningRepository;

	public MiningExtractionsServiceSupport( final NeoComAuthenticationProvider neoComAuthenticationProvider,
	                                        final CredentialDetailsService credentialDetailsService,
	                                        final SupportMiningRepositoryWrapper supportMiningRepositoryWrapper ) {
		this.miningRepository = supportMiningRepositoryWrapper.getSingleton();
		this.credentialDetailsService = credentialDetailsService;
		this.neoComAuthenticationProvider = neoComAuthenticationProvider;
	}

	public MiningExtractionCountResponse deleteAllMiningExtractions() {
		try {
			return new MiningExtractionCountResponse.Builder()
					.withMiningExtractionCount( this.miningRepository.deleteAll() )
					.build();
		} catch (final SQLException sqle) {
			NeoComLogger.error( sqle );
			throw new NeoComSBException( "SQL exception while deleting all mining extractions." );
		}
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
