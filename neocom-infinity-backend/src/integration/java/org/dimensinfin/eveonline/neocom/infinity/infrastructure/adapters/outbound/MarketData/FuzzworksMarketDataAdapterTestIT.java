package org.dimensinfin.eveonline.neocom.infinity.infrastructure.adapters.outbound.MarketData;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import org.dimensinfin.eveonline.neocom.infinity.infrastructure.domain.V2MarketDataDto;

class FuzzworksMarketDataAdapterTestIT {

	@Test
	void getMarketData4Type_returning_200() {
		// Given
		final Integer typeId = 17464;
		final FuzzworksMarketDataAdapter adapter = new FuzzworksMarketDataAdapter();
		// When
		final V2MarketDataDto sut = adapter.getMarketData4Type( typeId );
		// Then
		Assertions.assertNotNull( sut );
	}
}