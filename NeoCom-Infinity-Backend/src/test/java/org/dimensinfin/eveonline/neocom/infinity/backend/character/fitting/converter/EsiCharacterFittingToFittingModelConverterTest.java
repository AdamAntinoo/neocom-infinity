package org.dimensinfin.eveonline.neocom.infinity.backend.character.fitting.converter;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import org.dimensinfin.eveonline.neocom.domain.EsiType;
import org.dimensinfin.eveonline.neocom.esiswagger.model.CharacterscharacterIdfittingsItems;
import org.dimensinfin.eveonline.neocom.esiswagger.model.GetCharactersCharacterIdFittings200Ok;
import org.dimensinfin.eveonline.neocom.infinity.backend.character.fitting.domain.FittingModel;
import org.dimensinfin.eveonline.neocom.service.ResourceFactory;

import static org.dimensinfin.eveonline.neocom.infinity.backend.support.TestDataConstants.FittingConstants.TEST_FITTING_DESCRIPTION;
import static org.dimensinfin.eveonline.neocom.infinity.backend.support.TestDataConstants.FittingConstants.TEST_FITTING_ID;
import static org.dimensinfin.eveonline.neocom.infinity.backend.support.TestDataConstants.FittingConstants.TEST_FITTING_NAME;
import static org.dimensinfin.eveonline.neocom.infinity.backend.support.TestDataConstants.FittingConstants.TEST_FITTING_SHIP_TYPE_ID;
import static org.dimensinfin.eveonline.neocom.infinity.backend.support.TestDataConstants.FittingItemConstants.TEST_FITTING_ITEM_QUANTITY;
import static org.dimensinfin.eveonline.neocom.infinity.backend.support.TestDataConstants.FittingItemConstants.TEST_FITTING_ITEM_TYPE_ID;

public class EsiCharacterFittingToFittingModelConverterTest {
	private ResourceFactory resourceFactory;
	//	private EsiItemServiceV2 esiItemService;

	@BeforeEach
	public void beforeEach() {
		//		this.esiItemService = Mockito.mock( EsiItemServiceV2.class );
		this.resourceFactory = Mockito.mock( ResourceFactory.class );
	}

	@Test
	public void constructorContract() {
		final EsiCharacterFittingToFittingModelConverter converter = new EsiCharacterFittingToFittingModelConverter( this.resourceFactory );
		Assertions.assertNotNull( converter );
	}

	@Test
	public void convert() {
		// Given
		final EsiType shipHull = Mockito.mock( EsiType.class );
		final CharacterscharacterIdfittingsItems fittingItem = new CharacterscharacterIdfittingsItems();
		fittingItem.setTypeId( TEST_FITTING_ITEM_TYPE_ID );
		fittingItem.setQuantity( TEST_FITTING_ITEM_QUANTITY );
		fittingItem.setFlag( CharacterscharacterIdfittingsItems.FlagEnum.CARGO );
		final GetCharactersCharacterIdFittings200Ok source = new GetCharactersCharacterIdFittings200Ok();
		source.setFittingId( TEST_FITTING_ID );
		source.setShipTypeId( TEST_FITTING_SHIP_TYPE_ID );
		source.setName( TEST_FITTING_NAME );
		source.setDescription( TEST_FITTING_DESCRIPTION );
		final List<CharacterscharacterIdfittingsItems> items = new ArrayList<>();
		items.add( fittingItem );
		source.setItems( items );
		// When
		Mockito.when( this.resourceFactory.generateType4Id( Mockito.anyInt() ) ).thenReturn( shipHull );
		// Test
		final FittingModel expected = new FittingModel.Builder()
				.withFittingId( TEST_FITTING_ID )
				.withName( TEST_FITTING_NAME )
				.withDescription( TEST_FITTING_DESCRIPTION )
				.withShipHull( shipHull )
				.build();
		final FittingModel obtained = new EsiCharacterFittingToFittingModelConverter( this.resourceFactory ).convert( source );
		// Assertions
		Assertions.assertEquals( expected.toString(), obtained.toString() );
	}
}
