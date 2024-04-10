package org.dimensinfin.eveonline.neocom.infinity.backend.character.fitting.rest.v2;

import java.util.List;
import java.util.stream.Collectors;
import javax.validation.constraints.NotNull;

import org.springframework.stereotype.Service;

import org.dimensinfin.eveonline.neocom.esiswagger.model.GetCharactersCharacterIdFittings200Ok;
import org.dimensinfin.eveonline.neocom.infinity.backend.character.fitting.converter.EsiCharacterFittingToFittingModelConverter;
import org.dimensinfin.eveonline.neocom.infinity.backend.character.fitting.domain.FittingModel;
import org.dimensinfin.eveonline.neocom.infinity.config.security.CredentialDetailsService;
import org.dimensinfin.eveonline.neocom.infinity.config.security.NeoComAuthenticationProvider;
import org.dimensinfin.eveonline.neocom.infinity.core.rest.NeoComCredentialService;
import org.dimensinfin.eveonline.neocom.service.ESIDataService;
import org.dimensinfin.eveonline.neocom.service.ResourceFactory;

@Service
public class PilotFittingsServiceV2 extends NeoComCredentialService {
	private final ESIDataService esiDataService;
	private final ResourceFactory resourceFactory;

	// - C O N S T R U C T O R S
	public PilotFittingsServiceV2( @NotNull final NeoComAuthenticationProvider neoComAuthenticationProvider,
	                               @NotNull final CredentialDetailsService credentialDetailsService,
	                               @NotNull final ESIDataService esiDataService,
	                               @NotNull final ResourceFactory resourceFactory ) {
		super( neoComAuthenticationProvider, credentialDetailsService );
		this.esiDataService = esiDataService;
		this.resourceFactory = resourceFactory;
	}

	// - G E T T E R S   &   S E T T E R S
	public List<FittingModel> getPilotFittings() {
		final EsiCharacterFittingToFittingModelConverter fittingModelConverter =
				new EsiCharacterFittingToFittingModelConverter( this.resourceFactory );
		final List<GetCharactersCharacterIdFittings200Ok> fittingList = this.esiDataService.getCharactersCharacterIdFittings(
				this.getAuthorizedCredential()
		);
		return fittingList.stream()
				.map( fittingModelConverter::convert )
				.collect( Collectors.toList() );
	}
}
