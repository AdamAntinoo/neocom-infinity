package org.dimensinfin.eveonline.neocom.infinity.infrastructure.adapters.outbound.marketdata;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import org.dimensinfin.eveonline.neocom.infinity.app.ports.MarketDataPort;
import org.dimensinfin.eveonline.neocom.infinity.infrastructure.domain.V2MarketDataDto;

class MarketDataAdapterTest {
	private MarketDataPort marketDataProvider;

	@BeforeEach
	void setUp() {
		this.marketDataProvider = Mockito.mock( MarketDataPort.class );
	}

	@Test
	void constructor_contract() {
		final MarketDataAdapter adapter = this.getMarketDataAdapter();
		Assertions.assertNotNull( adapter );
	}

	@Test
	void getMarketData4Type_not_cached() {
		// Given
		final Integer typeId = 17464;
		final V2MarketDataDto marketData = Mockito.mock( V2MarketDataDto.class );
		final MarketDataAdapter adapter = this.getMarketDataAdapter();
		// When
		Mockito.when( this.marketDataProvider.getMarketData4Type( Mockito.anyInt() ) )
				.thenReturn( marketData );
		final V2MarketDataDto sut = adapter.getMarketData4Type( typeId );
		// Then
		Assertions.assertNotNull( sut );
		Mockito.verify( this.marketDataProvider, Mockito.atMostOnce() )
				.getMarketData4Type( Mockito.anyInt() );
	}

	@Test
	void getMarketData4Type_cached() {
		// Given
		final Integer typeId = 17464;
		final V2MarketDataDto marketData = Mockito.mock( V2MarketDataDto.class );
		final MarketDataAdapter adapter = this.getMarketDataAdapter();
		// When
		Mockito.when( this.marketDataProvider.getMarketData4Type( Mockito.anyInt() ) )
				.thenReturn( marketData );
		final V2MarketDataDto sut = adapter.getMarketData4Type( typeId );
		final V2MarketDataDto sutCached = adapter.getMarketData4Type( typeId );
		// Then
		Assertions.assertNotNull( sut );
		Assertions.assertNotNull( sutCached );
		Assertions.assertEquals( sut, sutCached );
		Mockito.verify( this.marketDataProvider, Mockito.atMostOnce() )
				.getMarketData4Type( Mockito.anyInt() );
	}

	@Test
	void getMarketData4TypeAtStation_not_cached() {
		// Given
		final Integer typeId = 17464;
		final Integer stationId = 100005;
		final V2MarketDataDto marketData = Mockito.mock( V2MarketDataDto.class );
		final MarketDataAdapter adapter = this.getMarketDataAdapter();
		// When
		Mockito.when( this.marketDataProvider.getMarketData4TypeAtStation( Mockito.anyInt(), Mockito.anyInt() ) )
				.thenReturn( marketData );
		final V2MarketDataDto sut = adapter.getMarketData4TypeAtStation( typeId, stationId );
		// Then
		Assertions.assertNotNull( sut );
		Mockito.verify( this.marketDataProvider, Mockito.atMostOnce() )
				.getMarketData4TypeAtStation( Mockito.anyInt(), Mockito.anyInt() );
	}

	@Test
	void getMarketData4TypeAtStation_cached() {
		// Given
		final Integer typeId = 17464;
		final Integer stationId = 100005;
		final V2MarketDataDto marketData = Mockito.mock( V2MarketDataDto.class );
		final MarketDataAdapter adapter = this.getMarketDataAdapter();
		// When
		Mockito.when( this.marketDataProvider.getMarketData4TypeAtStation( Mockito.anyInt(), Mockito.anyInt() ) )
				.thenReturn( marketData );
		final V2MarketDataDto sut = adapter.getMarketData4TypeAtStation( typeId, stationId );
		final V2MarketDataDto sutCached = adapter.getMarketData4TypeAtStation( typeId, stationId );
		// Then
		Assertions.assertNotNull( sut );
		Assertions.assertNotNull( sutCached );
		Assertions.assertEquals( sut, sutCached );
		Mockito.verify( this.marketDataProvider, Mockito.atMostOnce() )
				.getMarketData4TypeAtStation( Mockito.anyInt(), Mockito.anyInt() );
	}

	private MarketDataAdapter getMarketDataAdapter() {
		return new MarketDataAdapter( this.marketDataProvider );
	}
}