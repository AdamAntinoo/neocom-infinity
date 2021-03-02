package org.dimensinfin.eveonline.neocom.infinity.backend.market.rest.v1;

import java.util.Objects;
import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.dimensinfin.eveonline.neocom.database.entities.Credential;
import org.dimensinfin.eveonline.neocom.database.repositories.PilotPreferencesRepository;
import org.dimensinfin.eveonline.neocom.domain.space.SpaceLocation;
import org.dimensinfin.eveonline.neocom.domain.space.Station;
import org.dimensinfin.eveonline.neocom.esiswagger.model.GetCorporationsCorporationIdOk;
import org.dimensinfin.eveonline.neocom.infinity.config.security.CredentialDetailsService;
import org.dimensinfin.eveonline.neocom.infinity.config.security.NeoComAuthenticationProvider;
import org.dimensinfin.eveonline.neocom.infinity.core.rest.NeoComAuthenticatedService;
import org.dimensinfin.eveonline.neocom.market.MarketData;
import org.dimensinfin.eveonline.neocom.market.service.MarketService;
import org.dimensinfin.eveonline.neocom.service.ESIDataService;
import org.dimensinfin.eveonline.neocom.service.LocationCatalogService;
import org.dimensinfin.eveonline.neocom.utility.NeoObjects;

import static org.dimensinfin.eveonline.neocom.market.service.MarketService.PREDEFINED_MARKET_HUB_STATION_ID;

@Service
public class MarketServiceV1 extends NeoComAuthenticatedService {
	private final ESIDataService esiDataService;
	private final LocationCatalogService locationCatalogService;
	private final PilotPreferencesRepository preferencesRepository;
	private final MarketService marketService;

	// - C O N S T R U C T O R S
	@Autowired
	public MarketServiceV1( @NotNull final NeoComAuthenticationProvider neoComAuthenticationProvider,
	                        @NotNull final CredentialDetailsService credentialDetailsService,
	                        @NotNull final ESIDataService esiDataService,
	                        @NotNull final LocationCatalogService locationCatalogService,
	                        @NotNull final PilotPreferencesRepository preferencesRepository,
	                        @NotNull final MarketService marketService ) {
		super( neoComAuthenticationProvider, credentialDetailsService );
		this.esiDataService = esiDataService;
		this.locationCatalogService = Objects.requireNonNull( locationCatalogService );
		this.preferencesRepository = preferencesRepository;
		this.marketService = marketService;
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
		return this.marketService.getMarketConsolidatedByRegion4ItemId(
				this.preferencesRepository.accessMarketRegionId( this.getAuthorizedCredential().getAccountId() ).getNumberValue().intValue(),
				typeId
		);
	}

	/**
	 * Calculates the region space location that corresponds to the home office for the corporation identifier selected. This is a default value
	 * that can be used as a general reference when calculating prices when related to Corporation activities.
	 */
	private Integer accessCorporationHomeRegionId( final Credential credential ) {
		final GetCorporationsCorporationIdOk corporationData = NeoObjects.requireNonNull( this.esiDataService.getCorporationsCorporationId(
				credential.getCorporationId()
		) );
		final SpaceLocation station = this.locationCatalogService.searchLocation4Id( (long) corporationData.getHomeStationId() );
		if (station instanceof Station) return ((Station) station).getRegionId();
		else return PREDEFINED_MARKET_HUB_STATION_ID.intValue();
	}
}