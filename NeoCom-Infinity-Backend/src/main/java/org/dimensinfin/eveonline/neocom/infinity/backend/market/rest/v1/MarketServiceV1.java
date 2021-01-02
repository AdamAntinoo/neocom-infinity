package org.dimensinfin.eveonline.neocom.infinity.backend.market.rest.v1;

import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;
import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.dimensinfin.eveonline.neocom.adapter.LocationCatalogService;
import org.dimensinfin.eveonline.neocom.database.entities.Credential;
import org.dimensinfin.eveonline.neocom.database.entities.PilotPreferencesEntity;
import org.dimensinfin.eveonline.neocom.domain.space.SpaceLocation;
import org.dimensinfin.eveonline.neocom.domain.space.Station;
import org.dimensinfin.eveonline.neocom.esiswagger.model.GetCorporationsCorporationIdOk;
import org.dimensinfin.eveonline.neocom.esiswagger.model.GetMarketsRegionIdOrders200Ok;
import org.dimensinfin.eveonline.neocom.infinity.adapter.LocationCatalogServiceWrapper;
import org.dimensinfin.eveonline.neocom.infinity.backend.core.rest.NeoComCredentialService;
import org.dimensinfin.eveonline.neocom.infinity.backend.market.domain.MarketData;
import org.dimensinfin.eveonline.neocom.infinity.core.security.CredentialDetailsService;
import org.dimensinfin.eveonline.neocom.infinity.core.security.NeoComAuthenticationProvider;
import org.dimensinfin.eveonline.neocom.infinity.datamanagement.converter.GetMarketsRegionIdOrdersToMarketOrderConverter;
import org.dimensinfin.eveonline.neocom.service.ESIDataService;
import org.dimensinfin.logging.LogWrapper;

import static org.dimensinfin.eveonline.neocom.infinity.backend.market.domain.MarketData.MARKET_DEEP_RANGE;
import static org.dimensinfin.eveonline.neocom.service.ESIDataService.PREDEFINED_MARKET_HUB_STATION_ID;

@Service
public class MarketServiceV1 extends NeoComCredentialService {
	private final ESIDataService esiDataService;
	private final LocationCatalogService locationCatalogService;
	//	private final PilotPreferencesRepository pilotPreferencesRepository;

	// - C O N S T R U C T O R S
	@Autowired
	public MarketServiceV1( final @NotNull NeoComAuthenticationProvider neoComAuthenticationProvider,
	                        final @NotNull CredentialDetailsService credentialDetailsService,
	                        //	                        final @NotNull ESIDataServiceWrapper esiDataServiceWrapper,
	                        final @NotNull ESIDataService esiDataService,
	                        final @NotNull LocationCatalogServiceWrapper locationCatalogServiceWrapper/*,
	                        final @NotNull PilotPreferencesRepository pilotPreferencesRepository */ ) {
		super( neoComAuthenticationProvider, credentialDetailsService );
		this.esiDataService = esiDataService;
		//		this.esiDataService = Objects.requireNonNull( esiDataServiceWrapper.getSingleton() );
		this.locationCatalogService = Objects.requireNonNull( locationCatalogServiceWrapper.getSingleton() );
		//		this.pilotPreferencesRepository = pilotPreferencesRepository;
	}

	/**
	 * Get the current selected preferred Pilot market hub or obtain the default hub depending on the Pilot's corporation home region. There is a
	 * table for the market hub that corresponds to each game region or that if not found defaults to the Jita special market hub.
	 * With the region and the item download the market data from esi servers and then process it into a consolidated <code>MarketData</code>
	 * instance.
	 *
	 * This is an authenticated endpoint that relies on the authentication data to identify the current Pilot.
	 *
	 * @param typeId the type id for the item to search on the market for market information.
	 * @return an instance of the consolidates market data on a <code>MarketData</code>.
	 */
	public MarketData getMarketConsolidated4ItemId( final Integer typeId ) {
		final Station hub = this.getPreferredMarketHub();
		return this.getMarketConsolidatedByRegion4ItemId( hub.getRegionId(), typeId );
	}

	/**
	 * Creates a new <code>MarketData</code> record to consolidate the available market data for the requested iten at the requested region.
	 *
	 * @param regionId the target region where to search for the market data.
	 * @param typeId   the market item type id to retrieve the data.
	 * @return a new <code>MarketData</code> instance with the consolidated ESI market data.
	 */
	public MarketData getMarketConsolidatedByRegion4ItemId( final Integer regionId, final Integer typeId ) {
		final Station regionHub = this.esiDataService.getRegionMarketHub( regionId );
		final List<GetMarketsRegionIdOrders200Ok> sellOrders = this.getMarketHubSellOrders4Id( regionHub, typeId );
		final List<GetMarketsRegionIdOrders200Ok> buyOrders = this.getMarketHubBuyOrders4Id( regionHub, typeId );
		return new MarketData.Builder()
				.withItemId( typeId )
				.withBestSellOrder( sellOrders.isEmpty() ?
						null :
						new GetMarketsRegionIdOrdersToMarketOrderConverter( this.locationCatalogService ).convert( sellOrders.get( 0 ) ) )
				.withSellOrders( sellOrders )
				.withBestBuyOrder( buyOrders.isEmpty() ?
						null :
						new GetMarketsRegionIdOrdersToMarketOrderConverter( this.locationCatalogService ).convert( buyOrders.get( 0 ) ) )
				.build();
	}

	/**
	 * Calculates the region space location that corresponds to the home office for the corporation identifier selected. This is a default value
	 * that can be used as a general reference when calculating prices when related to Corporation activities.
	 */
	private Integer accessCorporationHomeRegionId( final Credential credential ) {
		final GetCorporationsCorporationIdOk corporationData = Objects.requireNonNull( this.esiDataService.getCorporationsCorporationId(
				credential.getCorporationId()
		) );
		final SpaceLocation station = this.locationCatalogService.searchLocation4Id( (long) corporationData.getHomeStationId() );
		if (station instanceof Station) return ((Station) station).getRegionId();
		else return PREDEFINED_MARKET_HUB_STATION_ID.intValue();
	}

	/**
	 * Get the lowest sell price on a target system from the list of market orders (buys and sells).
	 *
	 * @param orders       the list of market orders.
	 * @param targetSystem the hub target system. Other systems on the same region are filtered out.
	 * @return the minimal sell price found on the set of orders.
	 */
	private double getLowestSellPrice( final List<GetMarketsRegionIdOrders200Ok> orders, final int targetSystem ) {
		double minPrice = Double.MAX_VALUE;
		for (GetMarketsRegionIdOrders200Ok order : orders) {
			if ((order.getIsBuyOrder()) || (order.getSystemId().intValue() != targetSystem)) continue;
			if (order.getPrice() < minPrice) minPrice = order.getPrice();
		}
		return minPrice;
	}

	private List<GetMarketsRegionIdOrders200Ok> getMarketHubBuyOrders4Id( final Station hub, final Integer itemId ) {
		return this.esiDataService.getUniverseMarketOrdersForId( hub.getRegionId(), itemId )
				.stream()
				.filter( GetMarketsRegionIdOrders200Ok::getIsBuyOrder )
				.filter( order -> order.getSystemId().equals( hub.getSolarSystemId() ) ) // Filter only orders for the hub system
				.sorted( Comparator.comparingDouble( GetMarketsRegionIdOrders200Ok::getPrice ) )
				.collect( Collectors.toList() );
	}

	/**
	 * Filter the market orders and keep only the sell orders. On this new list keep only orders that are in the predefined price range. Other sell
	 * orders can be discarded since that information is not being consumed.
	 * The final list of orders is sorted by price descending.
	 *
	 * @param hub    the station hub where to search for the orders.
	 * @param itemId the item identifier to be used for order search.
	 * @return the trimmed list of sell orders that fall in the price range selected.
	 */
	private List<GetMarketsRegionIdOrders200Ok> getMarketHubSellOrders4Id( final Station hub, final Integer itemId ) {
		final List<GetMarketsRegionIdOrders200Ok> orders = this.esiDataService.getUniverseMarketOrdersForId( hub.getRegionId(), itemId );
		//		final Optional<Double> priceLimitOptional = orders.stream()
		//				.filter( order -> !order.getIsBuyOrder() ) // Filter only SELL orders
		//				.filter( order -> order.getSystemId() == hub.getSolarSystemId() ) // Filter only orders for the hub system
		//				.map( GetMarketsRegionIdOrders200Ok::getPrice )
		//				.min( Double::compare );
		//		if (priceLimitOptional.isPresent()) {
		final double priceLimit = this.getLowestSellPrice( orders, hub.getSolarSystemId() ) * MARKET_DEEP_RANGE;
		return orders
				.stream()
				.filter( order -> !order.getIsBuyOrder() ) // Filter only SELL orders
				.filter( order -> order.getSystemId().equals( hub.getSolarSystemId() ) ) // Filter only orders for the hub system
				.filter( order -> order.getPrice() <= priceLimit )
				.sorted( Comparator.comparingDouble( GetMarketsRegionIdOrders200Ok::getPrice ) )
				.collect( Collectors.toList() );
		//		} else return new ArrayList<>();
	}

	/**
	 * Returns the <code>Station</code> record for the pilot selects or corporation home region selected market hub.
	 *
	 * @return the market hub Station
	 */
	private Station getPreferredMarketHub() {
		final Credential credential = this.getAuthorizedCredential();
		// TODO - Replace this bya call to the PilotPreference repository to get this preference or empty
		final Optional<PilotPreferencesEntity> preferredMarketHub = Optional.empty();
		if (preferredMarketHub.isEmpty())
			return this.esiDataService.getRegionMarketHub( this.accessCorporationHomeRegionId( credential ) );
		else {
			final SpaceLocation location = this.locationCatalogService.searchLocation4Id(
					preferredMarketHub.get().getNumberValue().longValue()
			);
			if (location instanceof Station) return (Station) location;
			else {
				LogWrapper.info( "The Pilot preferred market hub does not point to a valid station." );
				return (Station) this.locationCatalogService.searchLocation4Id( PREDEFINED_MARKET_HUB_STATION_ID );
			}
		}
	}
}