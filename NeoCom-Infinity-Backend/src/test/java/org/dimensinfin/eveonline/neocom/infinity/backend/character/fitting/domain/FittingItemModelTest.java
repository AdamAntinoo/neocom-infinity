package org.dimensinfin.eveonline.neocom.infinity.backend.character.fitting.domain;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;

import org.dimensinfin.eveonline.neocom.esiswagger.model.CharacterscharacterIdfittingsItems;
import org.dimensinfin.eveonline.neocom.infinity.backend.universe.item.rest.v1.NeoItemControllerV1;

import nl.jqno.equalsverifier.EqualsVerifier;
import static org.dimensinfin.eveonline.neocom.infinity.backend.support.TestDataConstants.FittingItemConstants.TEST_FITTING_ITEM_QUANTITY;
import static org.dimensinfin.eveonline.neocom.infinity.backend.support.TestDataConstants.FittingItemConstants.TEST_FITTING_ITEM_TYPE_ID;

public class FittingItemModelTest {

	private Link typeLink;

	@BeforeEach
	public void beforeEach() {
		this.typeLink = Mockito.mock( Link.class );
	}

	@Test
	public void buildContract() {
		final FittingItemModel fittingItemModel = new FittingItemModel.Builder()
				.withTypeId( TEST_FITTING_ITEM_TYPE_ID )
				.withQuantity( TEST_FITTING_ITEM_QUANTITY )
				.withSlotFlag( CharacterscharacterIdfittingsItems.FlagEnum.CARGO )
				.withTypeLink( this.typeLink )
				.build();
		Assertions.assertNotNull( fittingItemModel );
	}

	@Test
	public void buildContractFailure() {
		Assertions.assertThrows( NullPointerException.class, () -> {
			new FittingItemModel.Builder()
					.withTypeId( null )
					.withQuantity( TEST_FITTING_ITEM_QUANTITY )
					.withSlotFlag( CharacterscharacterIdfittingsItems.FlagEnum.CARGO )
					.withTypeLink( typeLink )
					.build();
			new FittingItemModel.Builder()
					.withTypeId( TEST_FITTING_ITEM_TYPE_ID )
					.withQuantity( null )
					.withSlotFlag( CharacterscharacterIdfittingsItems.FlagEnum.CARGO )
					.withTypeLink( typeLink )
					.build();
			new FittingItemModel.Builder()
					.withTypeId( TEST_FITTING_ITEM_TYPE_ID )
					.withQuantity( TEST_FITTING_ITEM_QUANTITY )
					.withSlotFlag( null )
					.withTypeLink( typeLink )
					.build();
			new FittingItemModel.Builder()
					.withTypeId( TEST_FITTING_ITEM_TYPE_ID )
					.withQuantity( TEST_FITTING_ITEM_QUANTITY )
					.withSlotFlag( CharacterscharacterIdfittingsItems.FlagEnum.CARGO )
					.withTypeLink( null )
					.build();
		} );
	}

//	@Test
	public void equalsContract() {
		EqualsVerifier.forClass( FittingItemModel.class )
				.withPrefabValues( Link.class, WebMvcLinkBuilder.linkTo(
						WebMvcLinkBuilder.methodOn( NeoItemControllerV1.class ).getItem( TEST_FITTING_ITEM_TYPE_ID )
						).withRel( "item" ),
						WebMvcLinkBuilder.linkTo(
								WebMvcLinkBuilder.methodOn( NeoItemControllerV1.class ).getItem( TEST_FITTING_ITEM_TYPE_ID + 1 )
						).withRel( "item" ) )
				.verify();
	}

	@Test
	public void getterContract() {
		// Test
		final FittingItemModel fittingItemModel = new FittingItemModel.Builder()
				.withTypeId( TEST_FITTING_ITEM_TYPE_ID )
				.withQuantity( TEST_FITTING_ITEM_QUANTITY )
				.withSlotFlag( CharacterscharacterIdfittingsItems.FlagEnum.CARGO )
				.withTypeLink( this.typeLink )
				.build();
		// Assertions
		Assertions.assertEquals( TEST_FITTING_ITEM_TYPE_ID, fittingItemModel.getTypeId() );
		Assertions.assertEquals( CharacterscharacterIdfittingsItems.FlagEnum.CARGO, fittingItemModel.getFlag() );
		Assertions.assertEquals( TEST_FITTING_ITEM_QUANTITY, fittingItemModel.getQuantity() );
		Assertions.assertNotNull( fittingItemModel.getTypeData() );
	}

	@Test
	public void setterContract() {
		// Given
		final Link typeData = Mockito.mock( Link.class );
		// Test
		final FittingItemModel fittingItemModel = new FittingItemModel.Builder()
				.withTypeId( TEST_FITTING_ITEM_TYPE_ID )
				.withQuantity( TEST_FITTING_ITEM_QUANTITY )
				.withSlotFlag( CharacterscharacterIdfittingsItems.FlagEnum.CARGO )
				.withTypeLink( this.typeLink )
				.build();
		// When
		Mockito.when( typeData.getHref() ).thenReturn( "-HREF-" );
		// Assertions
		final FittingItemModel returnedData = fittingItemModel.setTypeData( typeData );
		final Link type = fittingItemModel.getTypeData();
		Assertions.assertNotNull( type );
		Assertions.assertEquals( type.getHref(), type.getHref() );
	}

	@Test
	public void toStringContract() {
		// Given
		final Link typeLink = WebMvcLinkBuilder.linkTo(
				WebMvcLinkBuilder.methodOn( NeoItemControllerV1.class ).getItem( TEST_FITTING_ITEM_TYPE_ID )
		).withRel( "item" );
		final FittingItemModel fittingItemModel = new FittingItemModel.Builder()
				.withTypeId( TEST_FITTING_ITEM_TYPE_ID )
				.withQuantity( TEST_FITTING_ITEM_QUANTITY )
				.withSlotFlag( CharacterscharacterIdfittingsItems.FlagEnum.CARGO )
				.withTypeLink( typeLink )
				.build();
		final String expected = "{\"typeId\":246,\"flag\":\"Cargo\",\"quantity\":3,\"typeData\":\"<\\/api\\/v1\\/universe\\/items\\/246>;rel=\\\"item\\\"\"}";
		final String obtained = fittingItemModel.toString();
		// Assertions
		Assertions.assertEquals( expected, obtained );
	}
}
