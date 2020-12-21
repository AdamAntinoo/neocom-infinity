package org.dimensinfin.eveonline.neocom.infinity.backend.market.rest.v1;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import javax.validation.constraints.NotNull;

import com.google.inject.name.Named;
import org.springframework.stereotype.Service;

import org.dimensinfin.eveonline.neocom.adapter.LocationCatalogService;
import org.dimensinfin.eveonline.neocom.database.entities.Credential;
import org.dimensinfin.eveonline.neocom.database.entities.PilotPreferencesEntity;
import org.dimensinfin.eveonline.neocom.database.repositories.PilotPreferencesRepository;
import org.dimensinfin.eveonline.neocom.domain.space.SpaceLocation;
import org.dimensinfin.eveonline.neocom.domain.space.Station;
import org.dimensinfin.eveonline.neocom.esiswagger.model.GetCorporationsCorporationIdOk;
import org.dimensinfin.eveonline.neocom.esiswagger.model.GetMarketsRegionIdOrders200Ok;
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
	private final PilotPreferencesRepository pilotPreferencesRepository;

	// - C O N S T R U C T O R S
	public MarketServiceV1( final @NotNull NeoComAuthenticationProvider neoComAuthenticationProvider,
	                        final @NotNull CredentialDetailsService credentialDetailsService,
	                        final @NotNull @Named("ESIDataService") ESIDataService esiDataService,
	                        final @NotNull @Named("LocationCatalogService") LocationCatalogService locationCatalogService,
	                        final @NotNull @Named("PilotPreferencesRepository") PilotPreferencesRepository pilotPreferencesRepository ) {
		super( neoComAuthenticationProvider, credentialDetailsService );
		this.esiDataService = esiDataService;
		this.locationCatalogService = locationCatalogService;
		this.pilotPreferencesRepository = pilotPreferencesRepository;
	}

	/**
	 * Get the current selected preferred Pilot market hub or obtain the default hub depending on the Pilot's corporation home region. There is a
	 * table for the market hub that corresponds to each game region or that if not found defaults to the Jita special market hub.
	 * With the region and the item download the market data from esi servers and then process it into a consolidated <code>MarketData</code>
	 * instance.
	 *
	 * This is an authenticated endpoint that relies on the authentication data to identify the current Pilot.
	 *
	 * @param itemId the type id for the item to search on the market for market information.
	 * @return an instance of the consolidates market data on a <code>MarketData</code>.
	 */
	public MarketData getMarketConsolidated4ItemId( final Integer itemId ) {
		final Station hub = this.getPreferredMarketHub();
		final List<GetMarketsRegionIdOrders200Ok> sellOrders = this.getMarketHubSellOrders4Id( hub, itemId );
		final List<GetMarketsRegionIdOrders200Ok> buyOrders = this.getMarketHubBuyOrders4Id( hub, itemId );
		return new MarketData.Builder()
				.withBestSellOrder( sellOrders.isEmpty() ? null : new GetMarketsRegionIdOrdersToMarketOrderConverter( this.locationCatalogService )
						.convert( sellOrders.get( 0 ) ) )
				.withSellOrders( sellOrders )
				.withBestBuyOrder( buyOrders.isEmpty() ? null : new GetMarketsRegionIdOrdersToMarketOrderConverter( this.locationCatalogService )
						.convert( buyOrders.get( 0 ) ) )
				.build();
	}

	/**
	 * Calculates the region space location that corresponds to the home office for the corporation identifier selected. This is a default value
	 * that can be used as a general reference when calculating prices when related to Corporation activities.
	 */
	private Integer accessCorporationHomeRegionId( final Credential credential ) {
		final GetCorporationsCorporationIdOk corporationData = this.esiDataService.getCorporationsCorporationId(
				credential.getCorporationId()
		);
		return corporationData.getHomeStationId();
	}

	private List<PilotPreferencesEntity> accessPreferredMarketHubPreference( final Credential credential ) {
		try {
			final List<PilotPreferencesEntity> preferences = this.pilotPreferencesRepository.findPilotPreferenceByPilot( credential.getAccountId() );
			return preferences;
		} catch (final SQLException sqle) {
			LogWrapper.error( sqle );
			return new ArrayList<>();
		}
	}

	private List<GetMarketsRegionIdOrders200Ok> getMarketHubBuyOrders4Id( final Station hub, final Integer itemId ) {
		return this.esiDataService.getUniverseMarketOrdersForId( hub.getRegionId(), itemId )
				.stream()
				.filter( GetMarketsRegionIdOrders200Ok::getIsBuyOrder )
				.filter( order -> order.getSystemId() == hub.getSolarSystemId() ) // Filter only orders for the hub system
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
		final Optional<Double> priceLimitOptional = orders.stream()
				.map( GetMarketsRegionIdOrders200Ok::getPrice )
				.min( Double::compare );
		if (priceLimitOptional.isPresent()) {
			final double priceLimit = priceLimitOptional.get() * MARKET_DEEP_RANGE;
			return this.esiDataService.getUniverseMarketOrdersForId( hub.getRegionId(), itemId )
					.stream()
					.filter( order -> !order.getIsBuyOrder() ) // Fuilter only SELL orders
					.filter( order -> order.getSystemId() == hub.getSolarSystemId() ) // Filter only orders for the hub system
					.filter( order -> order.getPrice() <= priceLimit )
					.sorted( Comparator.comparingDouble( GetMarketsRegionIdOrders200Ok::getPrice ) )
					.collect( Collectors.toList() );
		} else return new ArrayList<>();
	}

	/**
	 * Returns the <code>Station</code> record for the pilot selects or corporation home region selected market hub.
	 *
	 * @return the market hub Station
	 */
	private Station getPreferredMarketHub() {
		final Credential credential = this.getAuthorizedCredential();
		final List<PilotPreferencesEntity> preferredMarketHubList = this.accessPreferredMarketHubPreference( credential );
		if (preferredMarketHubList.isEmpty())
			return this.esiDataService.getRegionMarketHub( this.accessCorporationHomeRegionId( credential ) );
		else {
			final SpaceLocation location = this.locationCatalogService
					.searchLocation4Id( preferredMarketHubList.get( 0 ).getNumberValue().longValue() );
			if (location instanceof Station) return (Station) location;
			else {
				LogWrapper.info( "The Pilot preferred market hub does not point to a valid station." );
				return (Station) this.locationCatalogService.searchLocation4Id( PREDEFINED_MARKET_HUB_STATION_ID );
			}
		}
	}
}