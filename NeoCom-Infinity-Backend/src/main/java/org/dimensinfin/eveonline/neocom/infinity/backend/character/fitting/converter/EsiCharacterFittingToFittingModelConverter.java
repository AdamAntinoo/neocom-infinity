package org.dimensinfin.eveonline.neocom.infinity.backend.character.fitting.converter;

import javax.validation.constraints.NotNull;

import org.dimensinfin.core.interfaces.Converter;
import org.dimensinfin.eveonline.neocom.esiswagger.model.CharacterscharacterIdfittingsItems;
import org.dimensinfin.eveonline.neocom.esiswagger.model.GetCharactersCharacterIdFittings200Ok;
import org.dimensinfin.eveonline.neocom.infinity.backend.character.fitting.domain.FittingModel;
import org.dimensinfin.eveonline.neocom.service.ResourceFactory;

public class EsiCharacterFittingToFittingModelConverter implements Converter<GetCharactersCharacterIdFittings200Ok, FittingModel> {
	private final ResourceFactory resourceFactory;

	// - C O N S T R U C T O R S
	public EsiCharacterFittingToFittingModelConverter( @NotNull final ResourceFactory resourceFactory ) {
		this.resourceFactory = resourceFactory;
	}

	@Override
	public FittingModel convert( final GetCharactersCharacterIdFittings200Ok input ) {
		final EsiCharacterFittingItemToFittingItemModelConverter fittingItemConverter = new EsiCharacterFittingItemToFittingItemModelConverter();
		final FittingModel fitting = new FittingModel.Builder()
				.withFittingId( input.getFittingId() )
				.withName( input.getName() )
				.withDescription( input.getDescription() )
				.withShipHull( this.resourceFactory.generateType4Id( input.getShipTypeId() ) )
				.build();
		for (final CharacterscharacterIdfittingsItems item : input.getItems()) {
			fitting.addFittingItem( fittingItemConverter.convert( item ) );
		}
		return fitting;
	}
}
