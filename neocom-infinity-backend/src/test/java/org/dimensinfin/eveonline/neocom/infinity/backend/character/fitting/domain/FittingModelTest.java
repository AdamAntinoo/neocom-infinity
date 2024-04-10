package org.dimensinfin.eveonline.neocom.infinity.backend.character.fitting.domain;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import org.dimensinfin.eveonline.neocom.domain.EsiType;

import nl.jqno.equalsverifier.EqualsVerifier;
import static org.dimensinfin.eveonline.neocom.infinity.backend.support.TestDataConstants.FittingConstants.TEST_FITTING_DESCRIPTION;
import static org.dimensinfin.eveonline.neocom.infinity.backend.support.TestDataConstants.FittingConstants.TEST_FITTING_ID;
import static org.dimensinfin.eveonline.neocom.infinity.backend.support.TestDataConstants.FittingConstants.TEST_FITTING_NAME;
import static org.dimensinfin.eveonline.neocom.infinity.backend.support.TestDataConstants.FittingConstants.TEST_FITTING_SHIP_TYPE_ID;

public class FittingModelTest {
	private EsiType shipHull;

	@BeforeEach
	public void beforeEach() {
		// Given
		this.shipHull = Mockito.mock( EsiType.class );
	}

	@Test
	public void buildContract() {
		final FittingModel fittingModel = new FittingModel.Builder()
				.withFittingId( TEST_FITTING_ID )
				.withName( TEST_FITTING_NAME )
				.withDescription( TEST_FITTING_DESCRIPTION )
				.withShipHull( this.shipHull )
				.build();
		Assertions.assertNotNull( fittingModel );
	}

	@Test
	public void buildContractFailure() {
		Assertions.assertThrows( NullPointerException.class, () -> {
			new FittingModel.Builder()
					.withFittingId( null )
					.withName( TEST_FITTING_NAME )
					.withDescription( TEST_FITTING_DESCRIPTION )
					.withShipHull( this.shipHull )
					.build();
		} );
		Assertions.assertThrows( NullPointerException.class, () -> {
			new FittingModel.Builder()
					.withFittingId( TEST_FITTING_ID )
					.withName( null )
					.withDescription( TEST_FITTING_DESCRIPTION )
					.withShipHull( this.shipHull )
					.build();
		} );
		Assertions.assertThrows( NullPointerException.class, () -> {
			new FittingModel.Builder()
					.withFittingId( TEST_FITTING_ID )
					.withName( TEST_FITTING_NAME )
					.withDescription( null )
					.withShipHull( this.shipHull )
					.build();
		} );
		Assertions.assertThrows( NullPointerException.class, () -> {
			new FittingModel.Builder()
					.withFittingId( TEST_FITTING_ID )
					.withName( TEST_FITTING_NAME )
					.withDescription( TEST_FITTING_DESCRIPTION )
					.withShipHull( null )
					.build();
		} );
	}

	//	@Test
	public void equalsContract() {
		EqualsVerifier.forClass( FittingModel.class )
				.verify();
	}

	@Test
	public void getterContract() {
		// Given
		final FittingItemModel fittingItemModel = Mockito.mock( FittingItemModel.class );
		// When
		Mockito.when( this.shipHull.getTypeId() ).thenReturn( TEST_FITTING_SHIP_TYPE_ID );
		// Test
		final FittingModel fittingModel = new FittingModel.Builder()
				.withFittingId( TEST_FITTING_ID )
				.withName( TEST_FITTING_NAME )
				.withDescription( TEST_FITTING_DESCRIPTION )
				.withShipHull( this.shipHull )
				.build();
		// Assertions
		Assertions.assertEquals( TEST_FITTING_ID, fittingModel.getFittingId() );
		Assertions.assertEquals( TEST_FITTING_NAME, fittingModel.getName() );
		Assertions.assertEquals( TEST_FITTING_DESCRIPTION, fittingModel.getDescription() );
		Assertions.assertEquals( TEST_FITTING_SHIP_TYPE_ID, fittingModel.getShipTypeId() );
		Assertions.assertNotNull( fittingModel.getShipHull() );
		fittingModel.addFittingItem( fittingItemModel );
		Assertions.assertEquals( 1, fittingModel.getFittingItems().size() );
	}
}
