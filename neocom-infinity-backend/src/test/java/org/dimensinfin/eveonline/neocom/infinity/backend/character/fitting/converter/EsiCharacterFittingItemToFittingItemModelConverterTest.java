package org.dimensinfin.eveonline.neocom.infinity.backend.character.fitting.converter;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;

import org.dimensinfin.eveonline.neocom.esiswagger.model.CharacterscharacterIdfittingsItems;
import org.dimensinfin.eveonline.neocom.infinity.backend.character.fitting.domain.FittingItemModel;
import org.dimensinfin.eveonline.neocom.infinity.backend.universe.item.rest.v1.NeoItemControllerV1;

import static org.dimensinfin.eveonline.neocom.infinity.backend.support.TestDataConstants.FittingItemConstants.TEST_FITTING_ITEM_QUANTITY;
import static org.dimensinfin.eveonline.neocom.infinity.backend.support.TestDataConstants.FittingItemConstants.TEST_FITTING_ITEM_TYPE_ID;

public class EsiCharacterFittingItemToFittingItemModelConverterTest {

	@Test
	public void convert() {
		// Given
		final CharacterscharacterIdfittingsItems source = new CharacterscharacterIdfittingsItems();
		source.setTypeId( TEST_FITTING_ITEM_TYPE_ID );
		source.setQuantity( TEST_FITTING_ITEM_QUANTITY );
		source.setFlag( CharacterscharacterIdfittingsItems.FlagEnum.CARGO );
		final Link typeLink = WebMvcLinkBuilder.linkTo(
				WebMvcLinkBuilder.methodOn( NeoItemControllerV1.class ).getItem( TEST_FITTING_ITEM_TYPE_ID )
		).withRel( "item" );
		// Test
		final FittingItemModel expected = new FittingItemModel.Builder()
				.withTypeId( TEST_FITTING_ITEM_TYPE_ID )
				.withQuantity( TEST_FITTING_ITEM_QUANTITY )
				.withSlotFlag( CharacterscharacterIdfittingsItems.FlagEnum.CARGO )
				.withTypeLink( typeLink )
				.build();
		final FittingItemModel obtained = new EsiCharacterFittingItemToFittingItemModelConverter().convert( source );
		// Assertions
		Assertions.assertEquals( expected.toString(), obtained.toString() );
	}
}
