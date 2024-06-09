package org.dimensinfin.eveonline.neocom.infinity.app.usecases;

import java.util.List;
import java.util.Optional;
import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.dimensinfin.eveonline.neocom.industry.domain.ProcessedBlueprint;
import org.dimensinfin.eveonline.neocom.infinity.app.ports.DataStorePort;
import org.dimensinfin.eveonline.neocom.infinity.config.security.CredentialDetailsService;
import org.dimensinfin.eveonline.neocom.infinity.config.security.NeoComAuthenticationProvider;
import org.dimensinfin.eveonline.neocom.infinity.core.ExceptionMessagesExternalisedType;
import org.dimensinfin.eveonline.neocom.infinity.core.exception.NeoComRuntimeBackendExceptionObsolete;
import org.dimensinfin.eveonline.neocom.infinity.core.rest.NeoComAuthenticatedService;
import org.dimensinfin.eveonline.neocom.infinity.domain.exceptions.NeoComRuntimeHttpException;
import org.dimensinfin.logging.LogWrapper;

@Service
public class ProcessedBlueprintsUseCase extends NeoComAuthenticatedService {
	private final DataStorePort dataStoreService;

	// - C O N S T R U C T O R S
	@Autowired
	public ProcessedBlueprintsUseCase( @NotNull final NeoComAuthenticationProvider neoComAuthenticationProvider,
	                                   @NotNull final CredentialDetailsService credentialDetailsService,
	                                   @NotNull final DataStorePort dataStoreService ) {
		super( neoComAuthenticationProvider, credentialDetailsService );
		this.dataStoreService = dataStoreService;
	}

	/**
	 * Go to the Data Store and search for the specific blueprint identifier. If not found then throw a new exception explaining that case.
	 *
	 * @return the found blueprint or thrown an exception is the blueprint is not found.
	 */
	public Optional<ProcessedBlueprint> getBlueprints4Pilot4UID( final GetProcessedBlueprintUseCaseInput input ) {
		return this.dataStoreService.accessProcessedBlueprintsByUID( input.getPilotId(), input.getBlueprintUID() );
	}

	public List<ProcessedBlueprint> getBlueprints4PilotWithCostIndex(final @NotNull Integer pilotId ) {
			return this.dataStoreService.accessProcessedBlueprints(pilotId);
	}
}