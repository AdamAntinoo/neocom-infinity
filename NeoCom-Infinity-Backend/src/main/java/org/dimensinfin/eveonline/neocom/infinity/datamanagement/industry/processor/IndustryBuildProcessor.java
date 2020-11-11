package org.dimensinfin.eveonline.neocom.infinity.datamanagement.industry.processor;

import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.dimensinfin.eveonline.neocom.adapter.LocationCatalogService;
import org.dimensinfin.eveonline.neocom.database.entities.Credential;
import org.dimensinfin.eveonline.neocom.domain.space.SpaceRegion;
import org.dimensinfin.eveonline.neocom.domain.space.Station;
import org.dimensinfin.eveonline.neocom.esiswagger.model.GetCorporationsCorporationIdOk;
import org.dimensinfin.eveonline.neocom.esiswagger.model.GetMarketsRegionIdOrders200Ok;
import org.dimensinfin.eveonline.neocom.infinity.backend.industry.fitting.domain.BuildAction;
import org.dimensinfin.eveonline.neocom.infinity.backend.industry.fitting.domain.BuyBuildAction;
import org.dimensinfin.eveonline.neocom.infinity.datamanagement.converter.GetMarketsRegionIdOrdersToMarketOrderConverter;
import org.dimensinfin.eveonline.neocom.infinity.datamanagement.industry.domain.IAction;
import org.dimensinfin.eveonline.neocom.infinity.datamanagement.services.MarketProvider;
import org.dimensinfin.eveonline.neocom.provider.ESIDataProvider;

public class IndustryBuildProcessor {
	public static final String ORDER_TYPE = "ORDER_TYPE";
	public static final String ANY_ORDER = "all";
	public static final String BUY_ORDER = "buy";
	public static final String SELL_ORDER = "sell";
	private Credential credential;
	private ESIDataProvider esiDataProvider;
	private LocationCatalogService locationCatalogService;

	// - C O N S T R U C T O R S
	private IndustryBuildProcessor() {}

	// - G E T T E R S   &   S E T T E R S
	/**
	 * Generate a new industry BUY action for the specified item type id and the required quantity.
	 *
	 * @param typeId the type identifier for the item to be bought.
	 * @return a BUY action with the requested information and corresponding market data.
	 */
	public BuildAction generateBuyAction( final int typeId, final int quantity ) {
		// Search and filter the SELL market orders at the specified market hub.
		final MarketProvider marketService = new MarketProvider.Builder()
				.withEsiDataProvider( this.esiDataProvider )
				.withRegion( (SpaceRegion) this.locationCatalogService.searchLocation4Id( (long) this.accessCorporationHomeRegion().getRegionId() ) )
				.build();
		final List<GetMarketsRegionIdOrders200Ok> orders = marketService.getOrders( typeId );
		final GetMarketsRegionIdOrders200Ok markerOrderOk = marketService
				.getOrders( typeId ).stream()
				.filter( order -> !order.getIsBuyOrder() )
				.sorted( Comparator.comparingDouble( GetMarketsRegionIdOrders200Ok::getPrice ) )
				.collect( this.toSingleton() );
		return new BuyBuildAction.Builder()
				.withItem( this.esiDataProvider.searchEsiItem4Id( typeId ) )
				.withCorporationHome( this.accessCorporationHomeStation() )
				.withMarketOrder( new GetMarketsRegionIdOrdersToMarketOrderConverter( this.locationCatalogService ).convert( markerOrderOk ) )
				.withQuantity( quantity )
				.build();
	}

	/**
	 * Calculates the region space location that corresponds to the home office for the corporation identifier selected. This is a default value
	 * that can be used as a general reference when calculating prices when related to Corporation activities.
	 */
	private SpaceRegion accessCorporationHomeRegion() {
		final GetCorporationsCorporationIdOk corporationData = this.esiDataProvider.getCorporationsCorporationId(
				this.credential.getCorporationId()
		);
		final SpaceRegion homeRegion = (SpaceRegion) this.locationCatalogService.searchLocation4Id( (long) corporationData.getHomeStationId() );
		return homeRegion;
	}

	private Station accessCorporationHomeStation() {
		final GetCorporationsCorporationIdOk corporationData = this.esiDataProvider.getCorporationsCorporationId(
				this.credential.getCorporationId()
		);
		final Station homeStation = (Station) this.locationCatalogService.searchLocation4Id( (long) corporationData.getHomeStationId() );
		return homeStation;
	}

	private <T> Collector<T, ?, T> toSingleton() {
		return Collectors.collectingAndThen(
				Collectors.toList(),
				list -> {
					if (list.isEmpty()) return null;
					else return list.get( 0 );
				}
		);
	}

	// - B U I L D E R
	public static class Builder {
		private final IndustryBuildProcessor onConstruction;

		// - C O N S T R U C T O R S
		public Builder() {
			this.onConstruction = new IndustryBuildProcessor();
		}

		public IndustryBuildProcessor build() {
			Objects.requireNonNull( this.onConstruction.credential );
			Objects.requireNonNull( this.onConstruction.esiDataProvider );
			Objects.requireNonNull( this.onConstruction.locationCatalogService );
			return this.onConstruction;
		}

		public IndustryBuildProcessor.Builder withCredential( final Credential credential ) {
			this.onConstruction.credential = Objects.requireNonNull( credential );
			return this;
		}

		public IndustryBuildProcessor.Builder withEsiDataProvider( final ESIDataProvider esiDataProvider ) {
			this.onConstruction.esiDataProvider = Objects.requireNonNull( esiDataProvider );
			return this;
		}

		public IndustryBuildProcessor.Builder withLocationCatalogService( final LocationCatalogService locationCatalogService ) {
			this.onConstruction.locationCatalogService = Objects.requireNonNull( locationCatalogService );
			return this;
		}
	}
}
