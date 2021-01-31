package org.dimensinfin.eveonline.neocom.infinity.acceptance.support.api;

import java.util.List;

import org.dimensinfin.eveonline.neocom.infinity.backend.market.domain.MarketData;
import org.dimensinfin.eveonline.neocom.loyalty.persistence.LoyaltyOfferEntity;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Path;

public interface UniverseApiV1 {
	@Headers({ "Content-Type: application/json" })
	@GET("/api/v1/public/loyalty/corporations/{corporationId}")
	Call<List<LoyaltyOfferEntity>> getLoyaltyRecommendedOfferForCorporation( @Path("corporationId") final Integer corporationId );

	@Headers({ "Content-Type: application/json" })
	@GET("/api/v1/universe/market/consolidated/byregion/{regionId}/{typeId}")
	Call<MarketData> getMarketConsolidatedByRegion4ItemId( @Path("regionId") final Integer regionId, @Path("typeId") final Integer typeId );
}
