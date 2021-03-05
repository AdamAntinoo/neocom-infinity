package org.dimensinfin.eveonline.neocom.infinity.backend.industry.manufacture.rest.v1;

import java.util.List;
import java.util.Objects;
import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.dimensinfin.eveonline.neocom.industry.domain.ProcessedBlueprint;
import org.dimensinfin.eveonline.neocom.infinity.config.security.CredentialDetailsService;
import org.dimensinfin.eveonline.neocom.infinity.config.security.NeoComAuthenticationProvider;
import org.dimensinfin.eveonline.neocom.infinity.core.ExceptionMessagesExternalisedType;
import org.dimensinfin.eveonline.neocom.infinity.core.exception.NeoComRuntimeBackendException;
import org.dimensinfin.eveonline.neocom.infinity.core.rest.NeoComAuthenticatedService;
import org.dimensinfin.eveonline.neocom.service.IDataStore;
import org.dimensinfin.logging.LogWrapper;

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
	public List<ProcessedBlueprint> getBlueprints4PilotWithCostIndex( @NotNull final Integer pilotId ) {
		try {
			return this.dataStoreService.accessProcessedBlueprints( Objects.requireNonNull(
					this.getAuthorizedCredential().getAccountId()
			) );
		} catch (final NullPointerException npe) {
			LogWrapper.error( npe );
			throw new NeoComRuntimeBackendException( ExceptionMessagesExternalisedType.CREDENTIAL_NOT_FOUND.getMessage( pilotId ) );
		}
	}
}