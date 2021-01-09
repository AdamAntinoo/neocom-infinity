package org.dimensinfin.eveonline.neocom.infinity.backend.industry.fitting.converter;

import javax.validation.constraints.NotNull;

import org.dimensinfin.core.interfaces.Converter;
import org.dimensinfin.eveonline.neocom.domain.FittingItem;
import org.dimensinfin.eveonline.neocom.domain.FittingV2;
import org.dimensinfin.eveonline.neocom.esiswagger.model.CharacterscharacterIdfittingsItems;
import org.dimensinfin.eveonline.neocom.esiswagger.model.GetCharactersCharacterIdFittings200Ok;
import org.dimensinfin.eveonline.neocom.service.ResourceFactory;

public class GetCharactersCharacterIdFittingsToFittingV2Converter implements Converter<GetCharactersCharacterIdFittings200Ok, FittingV2> {
	private final ResourceFactory resourceFactory;

	// - C O N S T R U C T O R S
	public GetCharactersCharacterIdFittingsToFittingV2Converter( final @NotNull ResourceFactory resourceFactory ) {
		this.resourceFactory = resourceFactory;
	}

	@Override
	public FittingV2 convert( final GetCharactersCharacterIdFittings200Ok input ) {
		final FittingV2 fitting = new FittingV2.Builder()
				.withShipHull( this.resourceFactory.generateType4Id( input.getShipTypeId() ) )
				.withFittingDescription( input )
				.build();
		for (CharacterscharacterIdfittingsItems item : input.getItems())
			fitting.addFittingItem( new FittingItem.Builder().withFittingItem( item ).build() );
		return fitting;
	}
}
