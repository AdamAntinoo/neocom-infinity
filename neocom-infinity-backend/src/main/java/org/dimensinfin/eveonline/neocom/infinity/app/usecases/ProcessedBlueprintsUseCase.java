package org.dimensinfin.eveonline.neocom.infinity.app.usecases;

import java.util.List;
import java.util.Optional;
import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.dimensinfin.eveonline.neocom.industry.domain.ProcessedBlueprint;
import org.dimensinfin.eveonline.neocom.infinity.config.security.CredentialDetailsService;
import org.dimensinfin.eveonline.neocom.infinity.config.security.NeoComAuthenticationProvider;
import org.dimensinfin.eveonline.neocom.infinity.core.rest.NeoComAuthenticatedService;
import org.dimensinfin.eveonline.neocom.ports.IDataStorePort;

@Service
public class ProcessedBlueprintsUseCase extends NeoComAuthenticatedService {
	private final IDataStorePort dataStoreService;

	// - C O N S T R U C T O R S
	@Autowired
	public ProcessedBlueprintsUseCase( @NotNull final NeoComAuthenticationProvider neoComAuthenticationProvider,
	                                   @NotNull final CredentialDetailsService credentialDetailsService,
	                                   @NotNull final IDataStorePort dataStoreService ) {
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

	public List<ProcessedBlueprint> getProcessedBlueprints4Pilot( final @NotNull Integer pilotId ) {
		return this.dataStoreService.accessProcessedBlueprints( pilotId );
	}
}