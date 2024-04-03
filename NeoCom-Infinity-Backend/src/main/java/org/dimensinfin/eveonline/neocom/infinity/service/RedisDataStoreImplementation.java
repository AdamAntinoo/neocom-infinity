package org.dimensinfin.eveonline.neocom.infinity.service;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.TimeUnit;
import javax.annotation.Nullable;
import javax.validation.constraints.NotNull;

import com.google.inject.Inject;
import com.google.inject.name.Named;
import org.redisson.Redisson;
import org.redisson.api.RBucket;
import org.redisson.api.RMapCache;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;

import org.dimensinfin.eveonline.neocom.esiswagger.model.GetUniverseTypesTypeIdOk;
import org.dimensinfin.eveonline.neocom.exception.NeoComRuntimeException;
import org.dimensinfin.eveonline.neocom.industry.domain.ProcessedBlueprint;
import org.dimensinfin.eveonline.neocom.market.MarketOrder;
import org.dimensinfin.eveonline.neocom.market.service.MarketService;
import org.dimensinfin.eveonline.neocom.service.DMServicesDependenciesModule;
import org.dimensinfin.eveonline.neocom.service.ESIDataService;
import org.dimensinfin.eveonline.neocom.service.IDataStore;
import org.dimensinfin.eveonline.neocom.utility.NeoObjects;
import org.dimensinfin.logging.LogWrapper;

/**
 * @author Adam Antinoo (adamantinoo.git@gmail.com)
 * @since 0.20.0
 */
public class RedisDataStoreImplementation implements IDataStore {
	public static final String REDIS_SEPARATOR = ":";
	protected static final String LOWEST_SELL_ORDER_MAP = "LSO";
	protected static final String ESITYPE_CACHE_NAME = "ESIT";
	protected static final Integer LOWEST_SELL_ORDER_TTL = 300;
	private static final String COST_INDEX_BLUEPRINTS_CACHE_NAME = "BCI";
	private static final Integer COST_INDEX_BLUEPRINTS_TTL = 12;

	protected final RedissonClient redisClient;

	// - C O N S T R U C T O R S
	@Inject
	public RedisDataStoreImplementation( @NotNull @Named(DMServicesDependenciesModule.REDIS_DATABASE_URL) final String redisAddress ) {
		final Config config = new Config();
		config.useSingleServer().setAddress( redisAddress );
		this.redisClient = Redisson.create( config );
	}

	@Override
	@Nullable
	public GetUniverseTypesTypeIdOk accessEsiItem4Id( final int typeId, final ESIDataService.EsiItemPassThrough esiItemPassThrough ) {
		final RBucket<GetUniverseTypesTypeIdOk> esiIBucket = this.redisClient
				.getBucket( this.generateEsiItemUniqueId( typeId ) );
		if (null == esiIBucket.get()) {
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
	 * Reads the LSO from the map cache corresponding to the selected region. The uses the type to locate the map record.
	 * If the LSO is not found then it will use the callback to get a fresh value.
	 * LSO entries are time lived and after a time they are removed from the Map so new callbacks should be issued.
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
		if (null == entry) { // The data is not on the cache. Fetch it from the service and update the cache.
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
	 * Return the list of Processed Blueprints that have the BOM along with the price and cost. If the list is empty that means that the pilot has
	 * no blueprints or that the processing task has not been run and there are no updated blueprint data.
	 * The store uses a single key per pilot to store the complete list of processed blueprints in JSON coded format.
	 * The blueprint data generator is a background process that will scan the list of blueprints and the update the store.
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
	public ProcessedBlueprint accessProcessedBlueprintsByUID( final Integer pilotId, final String blueprintUID ) {
		final String uniqueLSOKey = this.generateBlueprintCostIndexUniqueId( pilotId );
		final RMapCache<String, ProcessedBlueprint> BCIMap = this.redisClient.getMapCache( uniqueLSOKey );
		return BCIMap.get( blueprintUID );
	}

	@Override
	public String generateBlueprintCostIndexUniqueId( final Integer pilotId ) {
		return COST_INDEX_BLUEPRINTS_CACHE_NAME + REDIS_SEPARATOR + pilotId;
	}

	@Override
	public void updateProcessedBlueprint( final Integer pilotId, final ProcessedBlueprint blueprint ) {
		final String uniqueLSOKey = this.generateBlueprintCostIndexUniqueId( pilotId );
		final RMapCache<String, ProcessedBlueprint> BCIMap = this.redisClient.getMapCache( uniqueLSOKey );
		try {
			BCIMap.put( uniqueLSOKey + REDIS_SEPARATOR + blueprint.getBlueprintTypeId(), blueprint );
		} catch (final RuntimeException rte) {
			LogWrapper.error( rte );
		}
	}

	private String generateEsiItemUniqueId( final Integer typeId ) {
		return ESITYPE_CACHE_NAME + REDIS_SEPARATOR + typeId;
	}

	private String generateLowestSellOrderUniqueId( final Integer regionId ) {
		return "LSO:" + regionId;
	}
}