package org.dimensinfin.eveonline.neocom.infinity.mining.rest.v1;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.annimon.stream.Collectors;
import com.annimon.stream.Stream;
import org.springframework.stereotype.Service;

import org.dimensinfin.eveonline.neocom.database.entities.Credential;
import org.dimensinfin.eveonline.neocom.database.entities.MiningExtractionEntity;
import org.dimensinfin.eveonline.neocom.database.repositories.MiningRepository;
import org.dimensinfin.eveonline.neocom.infinity.adapter.MiningRepositoryWrapper;
import org.dimensinfin.eveonline.neocom.infinity.core.security.CredentialDetails;
import org.dimensinfin.eveonline.neocom.infinity.core.security.CredentialDetailsService;
import org.dimensinfin.eveonline.neocom.infinity.core.security.NeoComAuthenticationProvider;
import org.dimensinfin.eveonline.neocom.miningextraction.converter.MiningExtractionToMiningExtractionEntityConverter;
import org.dimensinfin.eveonline.neocom.miningextraction.domain.MiningExtraction;
import org.dimensinfin.eveonline.neocom.service.NeoItemFactory;

@Service
public class MiningExtractionsService {
	private final NeoComAuthenticationProvider neoComAuthenticationProvider;
	private final CredentialDetailsService credentialDetailsService;
	private final MiningRepository miningRepository;

	public MiningExtractionsService( final NeoComAuthenticationProvider neoComAuthenticationProvider,
	                                 final CredentialDetailsService credentialDetailsService,
	                                 final MiningRepositoryWrapper miningRepositoryWrapper ) {
		this.miningRepository = miningRepositoryWrapper.getSingleton();
		this.credentialDetailsService = credentialDetailsService;
		this.neoComAuthenticationProvider = neoComAuthenticationProvider;
	}

	/**
	 * The records retrieved from the repository for today downloads should be processed to remove duplicated quantities produced by the processing
	 * of the same set of data at different hours. If there is an extraction at 15 hours and then the download process is executed again at 16
	 * hours we found a duplicated record even there was no more mining.
	 *
	 * Sush differences should be reprocessed and no increase records removed and the other records reduced to delta information.
	 */
	public List<MiningExtractionEntity> getTodayMiningExtractions4Pilot() {
		final String uniqueId = this.neoComAuthenticationProvider.getAuthenticatedUniqueId();
		final Credential credential = ((CredentialDetails) this.credentialDetailsService.loadUserByUsername( uniqueId )).getCredential();
		return this.adjustMiningQuantities( this.miningRepository.accessTodayMiningExtractions4Pilot( credential ) );
	}

	private List<MiningExtractionEntity> adjustMiningQuantities( final List<MiningExtraction> extractions ) {
		final Map<String, MiningExtraction> extractionMap = new HashMap<>();
		final List<MiningExtraction> extractionResults = new ArrayList<>();
		for (MiningExtraction extraction : extractions)
			extractionMap.put( extraction.getId(), extraction );
		// Transform extractions by correcting the mined delta quantities and removing zero delta records.
		return Stream.of( extractions )
				.map( extraction -> {
					final String previousRecord = extraction.getPreviousHourId();
					final MiningExtraction hit = extractionMap.get( previousRecord );
					if (null != hit) {
						final long newQuantity = extraction.getQuantity() - hit.getQuantity();
						if (newQuantity > 0)
							return new MiningExtraction.Builder()
									.withOwnerId( (int) extraction.getOwnerId() )
									.withQuantity( newQuantity )
									.withExtractionDate( extraction.getExtractionDateName() )
									.withExtractionHour( extraction.getExtractionHour() )
									.withNeoItem( NeoItemFactory.getSingleton().getItemById( extraction.getTypeId() ) )
									.withSpaceSystem( extraction.getSolarSystemLocation() )
									.build(); // Update this hour mined quantity.
					} else
						return extraction;
					return null;
				} )
				.filter( extraction -> (null != extraction) )
				.map( extraction -> new MiningExtractionToMiningExtractionEntityConverter().convert( extraction ) )
				.collect( Collectors.toList() );
	}
}
