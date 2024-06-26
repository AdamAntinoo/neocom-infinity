package org.dimensinfin.eveonline.neocom.infinity.backend.industry.manufacture.rest.v1;

import java.util.List;
import java.util.stream.Collectors;
import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.dimensinfin.eveonline.neocom.industry.domain.ProcessedBlueprint;
import org.dimensinfin.eveonline.neocom.industry.domain.ProcessedBlueprintSummary;
import org.dimensinfin.eveonline.neocom.infinity.backend.industry.converter.ProcessedBlueprintToSummaryConverter;
import org.dimensinfin.eveonline.neocom.infinity.config.security.CredentialDetailsService;
import org.dimensinfin.eveonline.neocom.infinity.config.security.NeoComAuthenticationProvider;
import org.dimensinfin.eveonline.neocom.infinity.core.ExceptionMessagesExternalisedType;
import org.dimensinfin.eveonline.neocom.infinity.core.exception.NeoComRuntimeBackendException;
import org.dimensinfin.eveonline.neocom.infinity.core.rest.NeoComAuthenticatedService;
import org.dimensinfin.eveonline.neocom.service.IDataStore;
import org.dimensinfin.eveonline.neocom.utility.NeoObjects;
import org.dimensinfin.logging.LogWrapper;

import static org.dimensinfin.eveonline.neocom.infinity.service.RedisDataStoreImplementation.REDIS_SEPARATOR;

@Service
public class IndustryManufactureServiceV1 extends NeoComAuthenticatedService {
	private final IDataStore dataStoreService;

	// - C O N S T R U C T O R S
	@Autowired
	public IndustryManufactureServiceV1( @NotNull final NeoComAuthenticationProvider neoComAuthenticationProvider,
	                                     @NotNull final CredentialDetailsService credentialDetailsService,
	                                     @NotNull final IDataStore dataStoreService ) {
		super( neoComAuthenticationProvider, credentialDetailsService );
		this.dataStoreService = dataStoreService;
	}

	// - G E T T E R S   &   S E T T E R S
	public ProcessedBlueprint getBlueprints4Pilot4UID( final Integer pilotId, final String blueprintUID ) {
		return this.dataStoreService.accessProcessedBlueprintsByUID( pilotId, blueprintUID );
	}

	public List<ProcessedBlueprintSummary> getBlueprints4PilotWithCostIndex( @NotNull final Integer pilotId ) {
		try {
			return this.dataStoreService.accessProcessedBlueprints( NeoObjects.requireNonNull(
					this.getAuthorizedCredential().getAccountId() ) )
					.stream()
					.map( blueprint -> new ProcessedBlueprintToSummaryConverter(
							this.dataStoreService.generateBlueprintCostIndexUniqueId( pilotId ) +
									REDIS_SEPARATOR +
									blueprint.getBlueprintTypeId() )
							.convert( blueprint )
					)
					.collect( Collectors.toList() );
		} catch (final NullPointerException npe) {
			LogWrapper.error( npe );
			throw new NeoComRuntimeBackendException( ExceptionMessagesExternalisedType.CREDENTIAL_NOT_FOUND.getMessage( pilotId ) );
		}
	}
}