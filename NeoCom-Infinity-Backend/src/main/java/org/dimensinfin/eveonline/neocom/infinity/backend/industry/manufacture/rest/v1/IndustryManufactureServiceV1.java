package org.dimensinfin.eveonline.neocom.infinity.backend.industry.manufacture.rest.v1;

import java.util.List;
import javax.validation.constraints.NotNull;

import org.springframework.stereotype.Service;

import org.dimensinfin.eveonline.neocom.database.entities.Credential;
import org.dimensinfin.eveonline.neocom.infinity.backend.industry.domain.ProcessedBlueprint;
import org.dimensinfin.eveonline.neocom.infinity.core.rest.NeoComCredentialService;
import org.dimensinfin.eveonline.neocom.infinity.config.security.CredentialDetailsService;
import org.dimensinfin.eveonline.neocom.infinity.config.security.NeoComAuthenticationProvider;
import org.dimensinfin.eveonline.neocom.infinity.service.DataStoreService;

@Service
public class IndustryManufactureServiceV1 extends NeoComCredentialService {
	private final DataStoreService dataStoreService;

	// - C O N S T R U C T O R S
	public IndustryManufactureServiceV1( final @NotNull NeoComAuthenticationProvider neoComAuthenticationProvider,
	                                     final @NotNull CredentialDetailsService credentialDetailsService,
	                                     final DataStoreService dataStoreService ) {
		super( neoComAuthenticationProvider, credentialDetailsService );
		this.dataStoreService = dataStoreService;
	}

	// - G E T T E R S   &   S E T T E R S
	public List<ProcessedBlueprint> getBlueprints4PilotWithCostIndex() {
		final Credential credential = this.getAuthorizedCredential();
		return this.dataStoreService.accessProcessedBlueprints( credential );
	}
}