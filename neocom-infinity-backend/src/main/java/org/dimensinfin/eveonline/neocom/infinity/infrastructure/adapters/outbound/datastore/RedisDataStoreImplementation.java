package org.dimensinfin.eveonline.neocom.infinity.infrastructure.adapters.outbound.datastore;

import java.text.MessageFormat;
import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;
import javax.annotation.Nullable;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.inject.Inject;
import com.google.inject.name.Named;
import org.redisson.Redisson;
import org.redisson.api.RBucket;
import org.redisson.api.RMapCache;
import org.redisson.api.RedissonClient;
import org.redisson.codec.JsonJacksonCodec;
import org.redisson.config.Config;

import org.dimensinfin.eveonline.neocom.database.entities.Credential;
import org.dimensinfin.eveonline.neocom.domain.EsiType;
import org.dimensinfin.eveonline.neocom.domain.space.SpaceLocation;
import org.dimensinfin.eveonline.neocom.esiswagger.model.GetUniverseTypesTypeIdOk;
import org.dimensinfin.eveonline.neocom.exception.NeoComRuntimeException;
import org.dimensinfin.eveonline.neocom.industry.domain.ProcessedBlueprint;
import org.dimensinfin.eveonline.neocom.infinity.config.NeoComDependencyConfig;
import org.dimensinfin.eveonline.neocom.infinity.infrastructure.config.LogMessageCatalog;
import org.dimensinfin.eveonline.neocom.market.MarketOrder;
import org.dimensinfin.eveonline.neocom.market.service.MarketService;
import org.dimensinfin.eveonline.neocom.ports.IDataStorePort;
import org.dimensinfin.eveonline.neocom.service.DMServicesDependenciesModule;
import org.dimensinfin.eveonline.neocom.service.ESIDataService;
import org.dimensinfin.eveonline.neocom.utility.NeoObjects;
import org.dimensinfin.logging.LogWrapper;

import io.micrometer.core.instrument.Counter;
import static org.dimensinfin.eveonline.neocom.utility.GlobalWideConstants.DataStoreKeys.ESI_TYPE_KEY_NAME;
import static org.dimensinfin.eveonline.neocom.utility.GlobalWideConstants.DataStoreKeys.ESI_TYPE_KEY_TTL;
import static org.dimensinfin.eveonline.neocom.utility.GlobalWideConstants.DataStoreKeys.ESI_UNIVERSE_TYPE_KEY_NAME;
import static org.dimensinfin.eveonline.neocom.utility.GlobalWideConstants.DataStoreKeys.EXTENDED_BLUEPRINTS_KEY_NAME;
import static org.dimensinfin.eveonline.neocom.utility.GlobalWideConstants.DataStoreKeys.LOWEST_SELL_ORDER_TTL;
import static org.dimensinfin.eveonline.neocom.utility.GlobalWideConstants.DataStoreKeys.SPACE_LOCATIONS_CACHE_TTL;
import static org.dimensinfin.eveonline.neocom.utility.GlobalWideConstants.DataStoreKeys.SPACE_LOCATIONS_KEY_NAME;
import static org.dimensinfin.eveonline.neocom.utility.GlobalWideConstants.REDIS_SEPARATOR;

/**
 * This is the Redis implementation for a permanent Data Store. Data will not expire and will be persisted.
 *
 * @author Adam Antinoo (adamantinoo.git@gmail.com)
 * @since 0.20.0
 */
public class RedisDataStoreImplementation implements IDataStorePort {
	@Deprecated
	private static final ObjectMapper neocomObjectMapper = new ObjectMapper();
	@Deprecated
	private static final JsonJacksonCodec codec = new JsonJacksonCodec( neocomObjectMapper );

	protected final RedissonClient redisClient;
	protected final Map<String, DataStoreDescriptor> cacheDescriptors = new HashMap<>();


	// - C O N S T R U C T O R S
	@Inject
	public RedisDataStoreImplementation(
			final @NotNull @Named(DMServicesDependenciesModule.REDIS_DATABASE_URL) String redisAddress
	) {
		LogWrapper.enter();
		LogWrapper.info( "Redis connection link: " + redisAddress );
		final Config config = new Config();
		config.useSingleServer().setAddress( redisAddress );
		this.redisClient = Redisson.create( config );

		// - Create cache descriptors
		cacheDescriptors.put( ESI_TYPE_KEY_NAME, DataStoreDescriptor.builder()
				.withKeyPrefix( ESI_TYPE_KEY_NAME )
				.withTTL( ESI_TYPE_KEY_TTL )
				.withUniqueKeyGenerator( ( Long typeId ) -> ESI_TYPE_KEY_NAME + REDIS_SEPARATOR + typeId )
				.withTotalCounter( Counter.builder( ESI_TYPE_KEY_NAME + "_cache_total" )
						.tag( "version", "v1" )
						.description( ESI_TYPE_KEY_NAME + "-Cache Total uses Count" )
						.register( NeoComDependencyConfig.globalRegistry ) )
				.withHitsCounter( Counter.builder( ESI_TYPE_KEY_NAME + "_hits" )
						.tag( "version", "v1" )
						.description( ESI_TYPE_KEY_NAME + "-Cache Hits Count" )
						.register( NeoComDependencyConfig.globalRegistry ) )
				.withMissCounter( Counter.builder( ESI_TYPE_KEY_NAME + "_misses" )
						.tag( "version", "v1" )
						.description( ESI_TYPE_KEY_NAME + "-Cache Misses Count" )
						.register( NeoComDependencyConfig.globalRegistry ) )
				.build()
		);
		LogWrapper.info( LogMessageCatalog.CACHE_DESCRIPTOR_CONFIGURED.getResolvedMessage(
				ESI_TYPE_KEY_NAME,
				cacheDescriptors.get( ESI_TYPE_KEY_NAME ).toString() )
		);
		cacheDescriptors.put( SPACE_LOCATIONS_KEY_NAME, DataStoreDescriptor.builder()
				.withKeyPrefix( SPACE_LOCATIONS_KEY_NAME )
				.withTTL( SPACE_LOCATIONS_CACHE_TTL )
				.withUniqueKeyGenerator( ( Long locationId ) -> SPACE_LOCATIONS_KEY_NAME + REDIS_SEPARATOR + locationId )
				.withTotalCounter( Counter.builder( SPACE_LOCATIONS_KEY_NAME + "_cache_total" )
						.tag( "version", "v1" )
						.description( SPACE_LOCATIONS_KEY_NAME + "-Cache Total uses Count" )
						.register( NeoComDependencyConfig.globalRegistry ) )
				.withHitsCounter( Counter.builder( SPACE_LOCATIONS_KEY_NAME + "_hits" )
						.tag( "version", "v1" )
						.description( SPACE_LOCATIONS_KEY_NAME + "-Cache Hits Count" )
						.register( NeoComDependencyConfig.globalRegistry ) )
				.withMissCounter( Counter.builder( SPACE_LOCATIONS_KEY_NAME + "_misses" )
						.tag( "version", "v1" )
						.description( SPACE_LOCATIONS_KEY_NAME + "-Cache Misses Count" )
						.register( NeoComDependencyConfig.globalRegistry ) )
				.build()
		);
		LogWrapper.info( LogMessageCatalog.CACHE_DESCRIPTOR_CONFIGURED.getResolvedMessage(
				ESI_TYPE_KEY_NAME,
				cacheDescriptors.get( SPACE_LOCATIONS_KEY_NAME ).toString() )
		);
		LogWrapper.exit();
	}

	@Deprecated
	@Override
	public Optional<EsiType> accessEsiType4Id( final int typeId ) {
		final String key = this.generateDataStoreUniqueKeyEsiType( typeId );
		final RBucket<EsiType> bucket = this.redisClient.getBucket( key );

		return Optional.ofNullable( bucket.get() );
	}

	// - C A C H E D   A C C E S S O R S

	/**
	 * Search for the <code>EsiType</code> on the corresponding cache key. If not found use the provided generator call to get a new instance to be
	 * cached. Set the expiration time to the configured property.
	 *
	 * @param typeId           the esi type id to ge located.
	 * @param generatorEsiType the esi type generator in the case the item is not available at the cache.
	 * @return an optional with the esi type. Empty if that tipe is not available at the ESI data services.
	 */
	@Override
	public Optional<EsiType> accessType4Id( final int typeId, final @NotNull Function<Integer, EsiType> generatorEsiType ) {
		final DataStoreDescriptor descriptor = this.cacheDescriptors.get( ESI_TYPE_KEY_NAME );
		final String key = descriptor.getUniqueKeyGenerator().apply( Long.valueOf( typeId ) );
		final RBucket<EsiType> bucket = this.redisClient.getBucket( key );
		if ( bucket.isExists() ) return Optional.of( descriptor.countHit( bucket.get() ) );
		else return Optional.ofNullable(
				descriptor.countMiss(
						this.storeEsiType4Id( bucket, generatorEsiType.apply( typeId ) )
				)
		);
	}

	@Override
	public Optional<SpaceLocation> accessLocation4Id( final Long locationId, final Credential credential, final Function<Long, SpaceLocation> generatorLocation ) {
		final DataStoreDescriptor descriptor = this.cacheDescriptors.get( SPACE_LOCATIONS_KEY_NAME );
		final String key = descriptor.getUniqueKeyGenerator().apply( locationId );
		final RBucket<SpaceLocation> bucket = this.redisClient.getBucket( key );
		if ( bucket.isExists() ) return Optional.of( descriptor.countHit( bucket.get() ) );
		else return Optional.ofNullable(
				descriptor.countMiss(
						this.storeSpaceLocation4Id( bucket, generatorLocation.apply( locationId ) )
				)
		);
	}

	// -----------------------------------------------------------------------------
	@Deprecated
	private String generateDataStoreUniqueKeyEsiType( final int typeId ) {
		return ESI_TYPE_KEY_NAME + REDIS_SEPARATOR + typeId;
	}

	private EsiType storeEsiType4Id( final RBucket<EsiType> bucket, final EsiType target ) {
		bucket.set( target );
		bucket.expire( Duration.of( ESI_TYPE_KEY_TTL, ChronoUnit.HOURS ) );
		return target;
	}

	private SpaceLocation storeSpaceLocation4Id( final RBucket<SpaceLocation> bucket, final SpaceLocation target ) {
		bucket.set( target );
		bucket.expire( Duration.of( ESI_TYPE_KEY_TTL, ChronoUnit.HOURS ) );
		return target;
	}

	@Override
	@Nullable
	public GetUniverseTypesTypeIdOk accessEsiUniverseItem4Id( final int typeId, final ESIDataService.EsiItemPassThrough esiItemPassThrough ) {
		final RBucket<GetUniverseTypesTypeIdOk> esiIBucket = this.redisClient
				.getBucket( this.generateDataStoreUniqueKeyEsiUniverseType( typeId ) );
		if ( null == esiIBucket.get() ) {
			final GetUniverseTypesTypeIdOk type;
			try {
				type = NeoObjects.requireNonNull( esiItemPassThrough.downloadEsiType( typeId ), "ESI Type detected null after downloading." );
				esiIBucket.set( type );
				return type;
			} catch (final NullPointerException exception) {
				LogWrapper.error( new NeoComRuntimeException(
						MessageFormat.format( "The downloader did not find the requested ESI type [{0}].", typeId )
				) );
				return null;
			}
		} else return esiIBucket.get();
	}

	/**
	 * Reads the LSO from the map cache corresponding to the selected region. The uses the type to locate the map record. If the LSO is not found then
	 * it will use the callback to get a fresh value. LSO entries are time lived and after a time they are removed from the Map so new callbacks
	 * should be issued.
	 *
	 * @param regionId                    the region where to find the orders.
	 * @param typeId                      the type identifier for the orders to search.
	 * @param lowestSellOrderReloadMethod the call back method to retrieve the order in case there is not found at the cache or expired.
	 * @return the LSO with the lowest sell price or NULL is there are no orders (this is possible for an empty market type slot).
	 */
	@Override
	@Nullable
	public MarketOrder accessLowestSellOrder( final Integer regionId, final Integer typeId,
	                                          final MarketService.LowestSellOrderPassThrough lowestSellOrderReloadMethod ) {
		final String uniqueLSOKey = this.generateLowestSellOrderUniqueId( regionId );
		final RMapCache<String, MarketOrder> LSOMap = this.redisClient.getMapCache( uniqueLSOKey );
		final MarketOrder entry = LSOMap.get( uniqueLSOKey + REDIS_SEPARATOR + typeId );
		if ( null == entry ) { // The data is not on the cache. Fetch it from the service and update the cache.
			final MarketOrder order;
			try {
				order = Objects.requireNonNull( lowestSellOrderReloadMethod.getLowestSellOrder( regionId, typeId ) );
				LSOMap.put( uniqueLSOKey + REDIS_SEPARATOR + typeId, order, LOWEST_SELL_ORDER_TTL, TimeUnit.SECONDS );
				return order;
			} catch (final NullPointerException exception) {
				LogWrapper.error( new NeoComRuntimeException(
						MessageFormat.format( "There is no Lowest Sell Order available on type [{0,number,#}] at region [{1,number,#}]",
								typeId, regionId )
				) );
				return null;
			}
		}
		return entry;
	}

	/**
	 * Return the list of Extended Blueprints that have the BOM along with the price and cost. If the list is empty that means that the pilot has no
	 * blueprints or that the processing task has not been run and there are no updated blueprint data. The store uses a single key per pilot to store
	 * the complete list of Extended Blueprints in JSON coded format. The blueprint data generator is a background process that will scan the list of
	 * blueprints and the update the store.
	 */
	@Override
	public List<ProcessedBlueprint> accessProcessedBlueprints( final Integer pilotId ) {
		final String uniqueLSOKey = this.generateBlueprintCostIndexUniqueId( pilotId );
		final RMapCache<String, ProcessedBlueprint> BCIMap = this.redisClient.getMapCache( uniqueLSOKey );
		try {
			return new ArrayList<>( BCIMap.values() );
		} catch (final RuntimeException rte) {
			LogWrapper.error( rte );
			return new ArrayList<>();
		}
	}

	@Override
	public Optional<ProcessedBlueprint> accessProcessedBlueprintsByUID( final Integer pilotId, final String blueprintUID ) {
		final String uniqueLSOKey = this.generateBlueprintCostIndexUniqueId( pilotId );
		final RMapCache<String, ProcessedBlueprint> BCIMap = this.redisClient.getMapCache( uniqueLSOKey );
		return Optional.ofNullable( BCIMap.get( blueprintUID ) );
	}

	@Override
	public String generateBlueprintCostIndexUniqueId( final Integer pilotId ) {
		return EXTENDED_BLUEPRINTS_KEY_NAME + REDIS_SEPARATOR + pilotId;
	}

	@Override
	public void updateProcessedBlueprint( final Integer pilotId, final ProcessedBlueprint blueprint ) {
		LogWrapper.enter();
		final String uniqueLSOKey = this.generateBlueprintCostIndexUniqueId( pilotId );
		final RMapCache<String, ProcessedBlueprint> BCIMap = this.redisClient.getMapCache( uniqueLSOKey );
		try {
			BCIMap.put( uniqueLSOKey + REDIS_SEPARATOR + blueprint.getLocation().getLocationId() + REDIS_SEPARATOR + blueprint.getTypeId(),
					blueprint );
			LogWrapper.exit();
		} catch (final RuntimeException rte) {
			LogWrapper.error( rte );
			LogWrapper.exit();
		}
	}

	@Deprecated
	@Override
	public Optional<SpaceLocation> accessLocation( final String locationCacheId ) {
		final RMapCache<String, SpaceLocation> BCIMap = this.redisClient.getMapCache( SPACE_LOCATIONS_KEY_NAME, codec );
		final SpaceLocation location = BCIMap.get( locationCacheId );
		if ( Objects.isNull( location ) ) return Optional.empty();
		else return Optional.of( location );
	}

	@Override
	public SpaceLocation updateLocation( final String locationCacheId, final SpaceLocation location ) {
		LogWrapper.enter();
		final RMapCache<String, SpaceLocation> BCIMap = this.redisClient.getMapCache( SPACE_LOCATIONS_KEY_NAME, codec );
		try {
			BCIMap.put( SPACE_LOCATIONS_KEY_NAME + REDIS_SEPARATOR + locationCacheId, location );
		} catch (final RuntimeException rte) {
			LogWrapper.error( rte );
		} finally {
			LogWrapper.exit();
		}
		return location;
	}

	private String generateDataStoreUniqueKeyEsiUniverseType( final Integer typeId ) {
		return ESI_UNIVERSE_TYPE_KEY_NAME + REDIS_SEPARATOR + typeId;
	}

	private String generateLowestSellOrderUniqueId( final Integer regionId ) {
		return "LSO:" + regionId;
	}
}
