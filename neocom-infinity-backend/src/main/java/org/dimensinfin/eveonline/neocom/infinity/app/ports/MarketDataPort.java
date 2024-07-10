package org.dimensinfin.eveonline.neocom.infinity.app.ports;

import org.dimensinfin.eveonline.neocom.infinity.infrastructure.domain.V2MarketDataDto;

public interface MarketDataPort {
	V2MarketDataDto getMarketData4Type( final int typeId );

	V2MarketDataDto getMarketData4TypeAtStation( final int typeId, final int stationId );
}
