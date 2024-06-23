package org.dimensinfin.eveonline.neocom.infinity.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.redisson.Redisson;
import org.redisson.api.RBucket;
import org.redisson.api.RMapCache;
import org.redisson.api.RedissonClient;
import org.redisson.codec.JsonJacksonCodec;
import org.redisson.config.Config;

import org.dimensinfin.eveonline.neocom.domain.space.SpaceLocationImplementation;
import org.dimensinfin.eveonline.neocom.domain.space.Station;
import org.dimensinfin.eveonline.neocom.esiswagger.model.GetMarketsRegionIdOrders200Ok;
import org.dimensinfin.eveonline.neocom.esiswagger.model.GetUniverseRegionsRegionIdOk;
import org.dimensinfin.eveonline.neocom.esiswagger.model.GetUniverseTypesTypeIdOk;
import org.dimensinfin.eveonline.neocom.infinity.infrastructure.adapters.outbound.datastore.RedisDataStoreImplementation;
import org.dimensinfin.eveonline.neocom.market.MarketOrder;

import io.micrometer.core.instrument.MeterRegistry;
import static org.dimensinfin.eveonline.neocom.infinity.backend.support.TestDataConstants.MarketOrderConstants.TEST_MARKET_ORDER_ID;
import static org.dimensinfin.eveonline.neocom.infinity.backend.support.TestDataConstants.MarketOrderConstants.TEST_MARKET_ORDER_PRICE;
import static org.dimensinfin.eveonline.neocom.infinity.backend.support.TestDataConstants.MarketOrderConstants.TEST_MARKET_ORDER_TYPE_ID;
import static org.dimensinfin.eveonline.neocom.infinity.backend.support.TestDataConstants.MarketOrderConstants.TEST_MARKET_ORDER_VOLUME_REMAIN;
import static org.dimensinfin.eveonline.neocom.infinity.backend.support.TestDataConstants.MarketOrderConstants.TEST_MARKET_ORDER_VOLUME_TOTAL;
import static org.dimensinfin.eveonline.neocom.utility.GlobalWideConstants.DataStoreKeys.ESI_TYPE_KEY_NAME;
import static org.dimensinfin.eveonline.neocom.utility.GlobalWideConstants.REDIS_SEPARATOR;

public class RedisDataStoreImplementationTest {
	private static final Integer TEST_ESI_ITEM_ID = 85;
	private static final String REDIS_LOCAL_DEFAULT_ADDRESS = "redis://localhost:5245";
	private static final String LOWEST_SELL_ORDER_MAP = "LSO";
	private final Integer regionId = 10000002;
	private final Integer typeId = 34;
	private GetUniverseRegionsRegionIdOk region;
	private Station station;
	private GetMarketsRegionIdOrders200Ok orderData;
	private MeterRegistry registry;

	//	@BeforeEach
	//	void setUp() {
	//		this.registry = Mockito.mock(MeterRegistry.class);
	//	}
	//	@Test
	//	void when_a_new_esitype_then_store_serialized_jackson() {
	//		// Given
	//		final EsiType target = new InstanceGenerator().getEsiType();
	//		final RedisDataStoreImplementation redisDataStore = new RedisDataStoreImplementation( REDIS_LOCAL_DEFAULT_ADDRESS );
	//		// When
	//		final EsiType sut = redisDataStore.storeEsiType4Id( target );
	//		// Then
	//		Assertions.assertNotNull( sut );
	//	}
	@Disabled
	@Test
	public void constructorContract() {
		final RedisDataStoreImplementation redisDataStore = this.getRedisDataStoreImplementation();
		Assertions.assertNotNull( redisDataStore );
	}

	//	@Test
	public void accessEsiUniverseItem4IdNotFound() {
		// Prepare
		this.clearESITCache();
		// Test
		final RedisDataStoreImplementation redisDataStore = this.getRedisDataStoreImplementation();
		final GetUniverseTypesTypeIdOk obtained = redisDataStore.accessEsiUniverseItem4Id( TEST_ESI_ITEM_ID, ( id ) -> null );
		// Assertions
		Assertions.assertNull( obtained );
	}

	//	@Test
	public void accessLowestSellOrderCached() {
		this.accessLowestSellOrderNotCached();
		// Given
		this.orderData.setOrderId( -45L );
		final MarketOrder marketOrder = new MarketOrder.Builder()
				.withStation( this.station )
				.withOrderData( this.orderData )
				.build();
		// Test
		final RedisDataStoreImplementation redisDataStore = this.getRedisDataStoreImplementation();
		final MarketOrder obtained = redisDataStore.accessLowestSellOrder( this.regionId, this.typeId,
				( r, t ) -> marketOrder
		);
		// Assertions
		Assertions.assertNotNull( obtained );
		Assertions.assertTrue( obtained instanceof MarketOrder );
		Assertions.assertEquals( TEST_MARKET_ORDER_ID, obtained.getOrderId() );
	}

	//	@Test
	public void accessLowestSellOrderNotCached() {
		// Given
		final MarketOrder marketOrder = new MarketOrder.Builder()
				.withStation( this.station )
				.withOrderData( this.orderData )
				.build();
		// Test
		this.clearLSOCache();
		final RedisDataStoreImplementation redisDataStore = this.getRedisDataStoreImplementation();
		final MarketOrder obtained = redisDataStore.accessLowestSellOrder( this.regionId, this.typeId,
				( r, t ) -> marketOrder
		);
		// Assertions
		Assertions.assertNotNull( obtained );
		Assertions.assertTrue( obtained instanceof MarketOrder );
		Assertions.assertEquals( TEST_MARKET_ORDER_ID, obtained.getOrderId() );
	}

	//	@BeforeEach
	public void beforeEach() {
		this.region = new GetUniverseRegionsRegionIdOk();
		this.region.setRegionId( this.regionId );
		this.region.setName( "-TEST-REGION-NAME-" );
		this.station = new SpaceLocationImplementation.Builder()
				.withRegion( this.region )
				.build();
		this.orderData = new GetMarketsRegionIdOrders200Ok();
		this.orderData.setOrderId( TEST_MARKET_ORDER_ID );
		this.orderData.setTypeId( TEST_MARKET_ORDER_TYPE_ID );
		this.orderData.setPrice( TEST_MARKET_ORDER_PRICE );
		this.orderData.setIsBuyOrder( false );
		this.orderData.setVolumeRemain( TEST_MARKET_ORDER_VOLUME_REMAIN );
		this.orderData.setVolumeTotal( TEST_MARKET_ORDER_VOLUME_TOTAL );
	}


	//	@AfterEach
	//	public void tearDown() {
	//		new RedisDataStoreImplementation( REDIS_LOCAL_DEFAULT_ADDRESS ).clearAll();
	//	}

	private void clearESITCache() {
		// Clear the Redis LSO map.
		final Config config = new Config();
		config.useSingleServer().setAddress( REDIS_LOCAL_DEFAULT_ADDRESS );
		final RedissonClient redisClient = Redisson.create( config );

		final RBucket<GetUniverseTypesTypeIdOk> esiIBucket = redisClient
				.getBucket( ESI_TYPE_KEY_NAME + REDIS_SEPARATOR + TEST_ESI_ITEM_ID, new JsonJacksonCodec() );
		esiIBucket.delete();
	}

	private void clearLSOCache() {
		// Clear the Redis LSO map.
		final Config config = new Config();
		config.useSingleServer().setAddress( REDIS_LOCAL_DEFAULT_ADDRESS );
		final RedissonClient redisClient = Redisson.create( config );
		final RMapCache<String, MarketOrder> cache = redisClient.getMapCache( LOWEST_SELL_ORDER_MAP );
		cache.clear();
	}

	private RedisDataStoreImplementation getRedisDataStoreImplementation() {
		return new RedisDataStoreImplementation( REDIS_LOCAL_DEFAULT_ADDRESS );
	}
}