package org.dimensinfin.eveonline.neocom.infinity.backend.character.pilot.rest.v1;

import java.util.Objects;
import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.dimensinfin.eveonline.neocom.character.domain.PilotV1;
import org.dimensinfin.eveonline.neocom.character.domain.PublicPilotV1;
import org.dimensinfin.eveonline.neocom.character.service.CharacterService;
import org.dimensinfin.eveonline.neocom.infinity.config.security.CredentialDetailsService;
import org.dimensinfin.eveonline.neocom.infinity.config.security.NeoComAuthenticationProvider;
import org.dimensinfin.eveonline.neocom.infinity.core.rest.NeoComAuthenticatedService;

@Service
public class PilotServiceV1 extends NeoComAuthenticatedService {
	private final CharacterService characterService;

	// - C O N S T R U C T O R S
	@Autowired
	public PilotServiceV1( @NotNull final NeoComAuthenticationProvider neoComAuthenticationProvider,
	                       @NotNull final CredentialDetailsService credentialDetailsService,
	                       @NotNull final CharacterService characterService ) {
		super( neoComAuthenticationProvider, credentialDetailsService );
		this.characterService = characterService;
	}

	/**
	 * Validates that the pilot data requested matches the current pilot logged in. And then gets the Credential to access the authenticated ESI
	 * backend data.
	 *
	 * @param pilotId requested pilot identifier. Should match with the logged pilot identifier.
	 * @return a complete Pilot data record with some data that can only be accessed when authenticated.
	 */
	public PilotV1 getAuthenticatedPilotData( final Integer pilotId ) {
		this.neoComAuthenticationProvider.validatePilotIdentifier( pilotId );
		return this.characterService.getPilotV1( Objects.requireNonNull( this.getAuthorizedCredential() ) );
	}

	public PublicPilotV1 getPilotPublicData( @NotNull final Integer pilotId ) {
		return this.characterService.getPilotPublicData( Objects.requireNonNull( pilotId ) );
	}
}
