package org.dimensinfin.eveonline.neocom.infinity.backend.character.fitting.converter;

import javax.validation.constraints.NotNull;

import org.dimensinfin.core.interfaces.Converter;
import org.dimensinfin.eveonline.neocom.esiswagger.model.CharacterscharacterIdfittingsItems;
import org.dimensinfin.eveonline.neocom.esiswagger.model.GetCharactersCharacterIdFittings200Ok;
import org.dimensinfin.eveonline.neocom.infinity.backend.character.fitting.domain.FittingModel;
import org.dimensinfin.eveonline.neocom.infinity.backend.universe.item.rest.v2.EsiItemServiceV2;
import org.dimensinfin.eveonline.neocom.provider.ESIDataProvider;

public class EsiCharacterFittingToFittingModelConverter implements Converter<GetCharactersCharacterIdFittings200Ok, FittingModel> {
	private final ESIDataProvider esiDataProvider;
	private final EsiItemServiceV2 esiItemServiceV2;

	// - C O N S T R U C T O R S
	public EsiCharacterFittingToFittingModelConverter( final @NotNull ESIDataProvider esiDataProvider,
	                                                   final @NotNull EsiItemServiceV2 esiItemServiceV2 ) {
		this.esiDataProvider = esiDataProvider;
		this.esiItemServiceV2 = esiItemServiceV2;
	}

	@Override
	public FittingModel convert( final GetCharactersCharacterIdFittings200Ok input ) {
		final EsiCharacterFittingItemToFittingItemModelConverter fittingItemConverter = new EsiCharacterFittingItemToFittingItemModelConverter();
		final FittingModel fitting = new FittingModel.Builder()
				.withFittingId( input.getFittingId() )
				.withName( input.getName() )
				.withDescription( input.getDescription() )
				.withShipHull( this.esiItemServiceV2.getItem( input.getShipTypeId() ) )
				.build();
		for (CharacterscharacterIdfittingsItems item : input.getItems()) {
			fitting.addFittingItem( fittingItemConverter.convert( item ) );
		}
		return fitting;
	}
}
