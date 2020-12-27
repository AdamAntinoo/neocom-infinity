package org.dimensinfin.eveonline.neocom.infinity.support.rest;

import org.dimensinfin.eveonline.neocom.infinity.backend.market.domain.MarketData;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Path;

public interface UniverseApiV1 {
	@Headers({ "Content-Type: application/json" })
	@GET("/api/v1/universe/market/consolidated/byregion/{regionId}/{typeId}")
	Call<MarketData> getMarketConsolidatedByRegion4ItemId( final @Path("regionId") Integer regionId, final @Path("typeId") Integer typeId );
}
