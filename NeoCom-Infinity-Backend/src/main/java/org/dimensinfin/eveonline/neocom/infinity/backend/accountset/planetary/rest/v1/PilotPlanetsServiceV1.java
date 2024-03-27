package org.dimensinfin.eveonline.neocom.infinity.backend.accountset.planetary.rest.v1;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.dimensinfin.eveonline.neocom.infinity.backend.accountset.planetary.domain.ColonyDto;
import org.dimensinfin.eveonline.neocom.infinity.backend.core.rest.NeoComCredentialService;
import org.dimensinfin.eveonline.neocom.infinity.core.security.CredentialDetailsService;
import org.dimensinfin.eveonline.neocom.infinity.core.security.NeoComAuthenticationProvider;
import org.dimensinfin.eveonline.neocom.planetary.converter.ColonyToColonyDtoConverter;
import org.dimensinfin.eveonline.neocom.planetary.services.ColonyFactory;
import org.dimensinfin.eveonline.neocom.provider.ESIDataProvider;

@Service
public class PilotPlanetsServiceV1 extends NeoComCredentialService {
	private final ColonyFactory colonyFactory;
	private final ESIDataProvider esiDataProvider;

	@Autowired
	public PilotPlanetsServiceV1( final @NotNull NeoComAuthenticationProvider neoComAuthenticationProvider,
	                              final @NotNull CredentialDetailsService credentialDetailsService,
	                              final @NotNull ColonyFactory colonyFactory,
	                              final @NotNull ESIDataProvider esiDataProvider ) {
		this.super( neoComAuthenticationProvider, credentialDetailsService );
		this.colonyFactory = colonyFactory;
		this.esiDataProvider = esiDataProvider;
	}

	/**
	 * Get the list of Planets and build the list of colonies.
	 */
	public List<ColonyDto> getPilotPlanets( final @NotNull Integer pilotId ) {
		final ColonyToColonyDtoConverter colonyDtoConverter = new ColonyToColonyDtoConverter();
		return Objects.requireNonNull( this.esiDataProvider.getCharactersCharacterIdPlanets( this.getAuthorizedCredential() ) )
				.stream()
				.map( planet -> colonyDtoConverter.convert( this.colonyFactory.convertColony( planet ) ) )
				.collect( Collectors.toList() );
	}
}
