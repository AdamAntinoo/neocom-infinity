package org.dimensinfin.eveonline.neocom.infinity.backend.character.fitting.rest.v2;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import javax.validation.constraints.NotNull;

import org.springframework.stereotype.Service;

import org.dimensinfin.eveonline.neocom.esiswagger.model.GetCharactersCharacterIdFittings200Ok;
import org.dimensinfin.eveonline.neocom.infinity.adapter.ESIDataProviderWrapper;
import org.dimensinfin.eveonline.neocom.infinity.backend.character.fitting.converter.EsiCharacterFittingToFittingModelConverter;
import org.dimensinfin.eveonline.neocom.infinity.backend.character.fitting.domain.FittingModel;
import org.dimensinfin.eveonline.neocom.infinity.backend.core.rest.NeoComCredentialService;
import org.dimensinfin.eveonline.neocom.infinity.backend.universe.item.rest.v2.EsiItemServiceV2;
import org.dimensinfin.eveonline.neocom.infinity.core.security.CredentialDetailsService;
import org.dimensinfin.eveonline.neocom.infinity.core.security.NeoComAuthenticationProvider;
import org.dimensinfin.eveonline.neocom.provider.ESIDataProvider;

@Service
public class PilotFittingsServiceV2 extends NeoComCredentialService {
	private final ESIDataProvider esiDataProvider;
	private final EsiItemServiceV2 esiItemServiceV2;

	// - C O N S T R U C T O R S
	public PilotFittingsServiceV2( final @NotNull ESIDataProviderWrapper esiDataProviderWrapper,
	                               final @NotNull NeoComAuthenticationProvider neoComAuthenticationProvider,
	                               final @NotNull CredentialDetailsService credentialDetailsService,
	                               final @NotNull EsiItemServiceV2 esiItemServiceV2 ) {
		super( neoComAuthenticationProvider, credentialDetailsService );
		this.esiDataProvider = Objects.requireNonNull( esiDataProviderWrapper.getSingleton() );
		this.esiItemServiceV2 = esiItemServiceV2;
	}

	public List<FittingModel> getPilotFittings( final @NotNull Integer pilotId ) {
		final EsiCharacterFittingToFittingModelConverter fittingModelConverter =
				new EsiCharacterFittingToFittingModelConverter( this.esiDataProvider, this.esiItemServiceV2 );
		final List<GetCharactersCharacterIdFittings200Ok> fittingList = this.esiDataProvider.getCharactersCharacterIdFittings(
				this.getAuthorizedCredential()
		);
		return fittingList.stream()
				.map( fitting -> fittingModelConverter.convert( fitting ) )
				.collect( Collectors.toList() );
	}
}
