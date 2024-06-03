package org.dimensinfin.eveonline.neocom.infinity.infrastructure.adapters.outbound.MarketData;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import org.dimensinfin.eveonline.neocom.infinity.infrastructure.domain.V2MarketDataDto;

class FuzzworksMarketDataAdapterTest {
	@Test
	void constructor_contract() {
		final FuzzworksMarketDataAdapter adapter = this.getFuzzworksMarketDataAdapter();
		Assertions.assertNotNull( adapter );
	}

	@Test
	void getMarketData4Type() {
		// Given
		final Integer typeId = 17464;
		final FuzzworksMarketDataAdapter adapter = this.getFuzzworksMarketDataAdapter();
		// When
		final V2MarketDataDto sut = adapter.getMarketData4Type( typeId );
		// Then
		Assertions.assertNotNull( sut );
	}

	@Test
	void getMarketData4Type_not_found() {
	}

	@Test
	void getMarketData4TypeAtStation() {
	}

	private FuzzworksMarketDataAdapter getFuzzworksMarketDataAdapter() {
		return new FuzzworksMarketDataAdapter();
	}
}