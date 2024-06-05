package org.dimensinfin.eveonline.neocom.infinity.infrastructure.adapters.outbound.MarketData;

import java.util.List;
import javax.validation.constraints.NotNull;

import org.dimensinfin.eveonline.neocom.esiswagger.model.GetMarketsRegionIdOrders200Ok;
import org.dimensinfin.eveonline.neocom.infinity.app.ports.MarketDataPort;
import org.dimensinfin.eveonline.neocom.infinity.infrastructure.domain.V2MarketDataDto;
import org.dimensinfin.eveonline.neocom.service.ESIDataService;

import static org.dimensinfin.eveonline.neocom.infinity.infrastructure.adapters.outbound.MarketData.MarketDataAdapter.DEFAULT_MARKETDATA_SYSTEM;

public class ESIMarketDataAdapter implements MarketDataPort {
	private final ESIDataService esiDataService;

	public ESIMarketDataAdapter( final @NotNull ESIDataService esiDataService ) {this.esiDataService = esiDataService;}

	@Override
	public V2MarketDataDto getMarketData4Type( final int typeId ) {
		return this.getMarketData4TypeAtStation( DEFAULT_MARKETDATA_SYSTEM, typeId );
	}

	@Override
	public V2MarketDataDto getMarketData4TypeAtStation( final int regionId, final int typeId ) {
		final List<GetMarketsRegionIdOrders200Ok> orders = this.esiDataService.getUniverseMarketOrdersForId(
				regionId, typeId );
		return null;
	}
}
