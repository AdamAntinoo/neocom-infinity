package org.dimensinfin.eveonline.neocom.infinity.infrastructure.adapters.outbound.marketdata;

import java.util.HashMap;
import java.util.Map;
import javax.validation.constraints.NotNull;

import com.google.inject.Inject;
import com.google.inject.name.Named;

import org.dimensinfin.eveonline.neocom.infinity.app.ports.MarketDataPort;
import org.dimensinfin.eveonline.neocom.infinity.infrastructure.domain.V2MarketDataDto;

import lombok.extern.slf4j.Slf4j;

/**
 * The task for this adapter is to provide an api to access Market Data for Eve Items. The api will include the DTO classes that will provide the
 * data.
 *
 * The first version will only provide data for the selected main hub but next versions will try to provide additional data from other hubs.
 *
 * Data should be cached at this level so providers implementations will not have to replicate that funcionality.
 */
@Slf4j
public class MarketDataAdapter implements MarketDataPort {
	private static final Map<String, V2MarketDataDto> marketDataCache = new HashMap<>();
	private static final String CACHE_ID_SEPARATOR = ":";
	public static final int DEFAULT_MARKETDATA_SYSTEM = 60003760;
	private final MarketDataPort marketDataProvider;

	@Inject
	public MarketDataAdapter( @Named("IMarketDataAdapter") @NotNull MarketDataPort marketDataProvider ) {
		this.marketDataProvider = marketDataProvider;
	}

	@Override
	public V2MarketDataDto getMarketData4Type( final int typeId ) {
		return marketDataCache.computeIfAbsent( this.getCacheIdentifier( typeId ),
				k -> this.marketDataProvider.getMarketData4Type( typeId )
		);
	}

	@Override
	public V2MarketDataDto getMarketData4TypeAtStation( final int regionId, final int typeId ) {
		return marketDataCache.computeIfAbsent( this.getCacheIdentifier( regionId, typeId ),
				k -> this.marketDataProvider.getMarketData4TypeAtStation( regionId, typeId )
		);
	}

	private String getCacheIdentifier( final int typeId ) {
		log.debug( "--- MarketData cache idenfifier->{}", typeId + CACHE_ID_SEPARATOR + DEFAULT_MARKETDATA_SYSTEM );
		return typeId + CACHE_ID_SEPARATOR + DEFAULT_MARKETDATA_SYSTEM;
	}

	private String getCacheIdentifier( final int stationId, final int typeId ) {
		return typeId + CACHE_ID_SEPARATOR + stationId;
	}
}
