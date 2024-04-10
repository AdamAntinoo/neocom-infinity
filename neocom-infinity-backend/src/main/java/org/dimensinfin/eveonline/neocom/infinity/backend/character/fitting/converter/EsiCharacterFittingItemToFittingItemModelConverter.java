package org.dimensinfin.eveonline.neocom.infinity.backend.character.fitting.converter;

import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;

import org.dimensinfin.core.interfaces.Converter;
import org.dimensinfin.eveonline.neocom.esiswagger.model.CharacterscharacterIdfittingsItems;
import org.dimensinfin.eveonline.neocom.infinity.backend.character.fitting.domain.FittingItemModel;
import org.dimensinfin.eveonline.neocom.infinity.backend.universe.item.rest.v1.NeoItemControllerV1;

public class EsiCharacterFittingItemToFittingItemModelConverter implements Converter<CharacterscharacterIdfittingsItems, FittingItemModel> {
	@Override
	public FittingItemModel convert( final CharacterscharacterIdfittingsItems input ) {
		return new FittingItemModel.Builder()
				.withTypeId( input.getTypeId() )
				.withSlotFlag( input.getFlag() )
				.withQuantity( input.getQuantity() )
				.withTypeLink( WebMvcLinkBuilder.linkTo(
						WebMvcLinkBuilder.methodOn( NeoItemControllerV1.class ).getItem( input.getTypeId() )
						).withRel("item")
				)
				.build();
	}
}
