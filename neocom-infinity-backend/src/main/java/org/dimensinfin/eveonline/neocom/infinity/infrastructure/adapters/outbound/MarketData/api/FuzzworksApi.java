package org.dimensinfin.eveonline.neocom.infinity.infrastructure.adapters.outbound.MarketData.api;

import org.dimensinfin.eveonline.neocom.infinity.infrastructure.domain.FuzzWorkMarketData;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface FuzzworksApi {
	@GET("/aggregates")
	Call<FuzzWorkMarketData> getMarketData( @Query("region") int region, @Query("types") int typeId );
}
