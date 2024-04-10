package org.dimensinfin.eveonline.neocom.infinity.backend.corporation.domain;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import org.dimensinfin.eveonline.neocom.database.entities.NeoAsset;
import org.dimensinfin.eveonline.neocom.domain.space.Station;

class ShippingYardLocationTest {
	@Test
	public void buildComplete() {
		final NeoAsset deposit = Mockito.mock( NeoAsset.class );
		final NeoAsset office = Mockito.mock( NeoAsset.class );
		final Station station = Mockito.mock( Station.class );
		final ShippingYardLocation yard = new ShippingYardLocation.Builder()
				.withYardDeposit( deposit )
				.withOfficeContainer( office )
				.withStation( station )
				.build();

		Assertions.assertNotNull( yard );
	}

	@Test
	public void buildFailure() {
		final NeoAsset deposit = Mockito.mock( NeoAsset.class );
		final NeoAsset office = Mockito.mock( NeoAsset.class );
		final Station station = Mockito.mock( Station.class );
		Assertions.assertThrows( NullPointerException.class, () -> {
			final ShippingYardLocation yard = new ShippingYardLocation.Builder()
					.withYardDeposit( null )
					.withOfficeContainer( office )
					.withStation( station )
					.build();
		}, "Expected ShippingYardLocation.Builder() to throw null verification, but it didn't." );
		Assertions.assertThrows( NullPointerException.class, () -> {
			final ShippingYardLocation yard = new ShippingYardLocation.Builder()
					.withYardDeposit( deposit )
					.withOfficeContainer( null )
					.withStation( station )
					.build();
		}, "Expected ShippingYardLocation.Builder() to throw null verification, but it didn't." );
		Assertions.assertThrows( NullPointerException.class, () -> {
			final ShippingYardLocation yard = new ShippingYardLocation.Builder()
					.withYardDeposit( deposit )
					.withOfficeContainer( office )
					.withStation( null )
					.build();
		}, "Expected ShippingYardLocation.Builder() to throw null verification, but it didn't." );
		Assertions.assertThrows( NullPointerException.class, () -> {
			final ShippingYardLocation yard = new ShippingYardLocation.Builder()
					.withOfficeContainer( office )
					.withStation( station )
					.build();
		}, "Expected ShippingYardLocation.Builder() to throw null verification, but it didn't." );
		Assertions.assertThrows( NullPointerException.class, () -> {
			final ShippingYardLocation yard = new ShippingYardLocation.Builder()
					.withYardDeposit( deposit )
					.withStation( station )
					.build();
		}, "Expected ShippingYardLocation.Builder() to throw null verification, but it didn't." );
	}

	@Test
	public void gettersContract() {
		final NeoAsset deposit = Mockito.mock( NeoAsset.class );
		final NeoAsset office = Mockito.mock( NeoAsset.class );
		final Station station = Mockito.mock( Station.class );
		final ShippingYardLocation yard = new ShippingYardLocation.Builder()
				.withYardDeposit( deposit )
				.withOfficeContainer( office )
				.withStation( station )
				.build();

		Assertions.assertNotNull( yard );
		Assertions.assertNotNull( yard.getDeposit() );
		Assertions.assertNotNull( yard.getOfficeContainer() );
		Assertions.assertNotNull( yard.getStation() );
	}
}
