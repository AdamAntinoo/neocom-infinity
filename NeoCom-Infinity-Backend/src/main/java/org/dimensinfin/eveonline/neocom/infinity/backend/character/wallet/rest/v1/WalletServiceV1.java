package org.dimensinfin.eveonline.neocom.infinity.backend.character.wallet.rest.v1;

import java.util.Objects;
import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.dimensinfin.eveonline.neocom.infinity.adapter.ESIDataProviderWrapper;
import org.dimensinfin.eveonline.neocom.infinity.backend.core.rest.NeoComCredentialService;
import org.dimensinfin.eveonline.neocom.infinity.core.security.CredentialDetailsService;
import org.dimensinfin.eveonline.neocom.infinity.core.security.NeoComAuthenticationProvider;
import org.dimensinfin.eveonline.neocom.provider.ESIDataProvider;

@Service
public class WalletServiceV1 extends NeoComCredentialService {
	private final ESIDataProvider esiDataProvider;

	// - C O N S T R U C T O R S
	@Autowired
	public WalletServiceV1( final @NotNull ESIDataProviderWrapper esiDataProviderWrapper,
	                        final @NotNull NeoComAuthenticationProvider neoComAuthenticationProvider,
	                        final @NotNull CredentialDetailsService credentialDetailsService ) {
		super( neoComAuthenticationProvider, credentialDetailsService );
		this.esiDataProvider = Objects.requireNonNull( esiDataProviderWrapper.getSingleton() );
	}

	public Double getPilotWalletBalance( final Integer pilotId ) {
		return this.esiDataProvider.getCharactersCharacterIdWallet( this.getAuthorizedCredential() );
	}
}
