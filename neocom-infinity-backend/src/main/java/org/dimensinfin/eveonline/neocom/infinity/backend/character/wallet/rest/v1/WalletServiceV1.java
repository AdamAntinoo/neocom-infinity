package org.dimensinfin.eveonline.neocom.infinity.backend.character.wallet.rest.v1;

import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.dimensinfin.eveonline.neocom.infinity.config.security.CredentialDetailsService;
import org.dimensinfin.eveonline.neocom.infinity.config.security.NeoComAuthenticationProvider;
import org.dimensinfin.eveonline.neocom.infinity.core.rest.NeoComAuthenticatedService;
import org.dimensinfin.eveonline.neocom.service.ESIDataService;

@Service
public class WalletServiceV1 extends NeoComAuthenticatedService {
	private final ESIDataService esiDataService;

	// - C O N S T R U C T O R S
	@Autowired
	public WalletServiceV1( @NotNull final NeoComAuthenticationProvider neoComAuthenticationProvider,
	                        @NotNull final CredentialDetailsService credentialDetailsService,
	                        @NotNull final ESIDataService esiDataService ) {
		super( neoComAuthenticationProvider, credentialDetailsService );
		this.esiDataService = esiDataService;
	}

	// - G E T T E R S   &   S E T T E R S
	public Double getPilotWalletBalance() {
		return this.esiDataService.getCharactersCharacterIdWallet( this.getAuthorizedCredential() );
	}
}
