package org.dimensinfin.eveonline.neocom.infinity.infrastructure.adapters.outbound.MarketData.converters;

import org.dimensinfin.core.interfaces.Converter;
import org.dimensinfin.eveonline.neocom.infinity.infrastructure.domain.FuzzWorkMarketData;
import org.dimensinfin.eveonline.neocom.infinity.infrastructure.domain.V2MarketDataDto;

public class FuzzWorkMarketDataConverter implements Converter<FuzzWorkMarketData, V2MarketDataDto> {

	@Override
	public V2MarketDataDto convert( final FuzzWorkMarketData fuzzWorkMarketData ) {
		return V2MarketDataDto.builder()
				.buyPrice( fuzzWorkMarketData.getBuy().getWeightedAverage() )
				.buyOrderCount( fuzzWorkMarketData.getBuy().getOrderCount() )
				.buyVolume( fuzzWorkMarketData.getBuy().getVolume() )
				.sellPrice( fuzzWorkMarketData.getSell().getWeightedAverage() )
				.sellOrderCount( fuzzWorkMarketData.getSell().getOrderCount() )
				.sellVolume( fuzzWorkMarketData.getSell().getVolume() )
				.build();
	}
}
