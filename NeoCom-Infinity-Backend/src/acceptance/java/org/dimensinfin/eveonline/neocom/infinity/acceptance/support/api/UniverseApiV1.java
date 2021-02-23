package org.dimensinfin.eveonline.neocom.infinity.acceptance.support.api;

import java.util.List;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.dimensinfin.eveonline.neocom.infinity.acceptance.support.dto.ServerStatusDto;
import org.dimensinfin.eveonline.neocom.infinity.backend.market.domain.MarketData;
import org.dimensinfin.eveonline.neocom.loyalty.domain.LoyaltyServiceConfiguration;
import org.dimensinfin.eveonline.neocom.loyalty.persistence.LoyaltyOfferEntity;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.PATCH;
import retrofit2.http.Path;

public interface UniverseApiV1 {
	// - G E T T E R S   &   S E T T E R S
	@Headers({ "Content-Type: application/json" })
	@GET("/api/v1/public/server/status")
	Call<ServerStatusDto> getServerStatus();

	@Headers({ "Content-Type: application/json" })
	@GET("/api/v1/public/loyalty/corporations/{corporationId}")
	Call<List<LoyaltyOfferEntity>> getLoyaltyRecommendedOfferForCorporation( @Path("corporationId") final Integer corporationId );

	@Headers({ "Content-Type: application/json" })
	@GET("/api/v1/universe/market/consolidated/byregion/{regionId}/{typeId}")
	Call<MarketData> getMarketConsolidatedByRegion4ItemId( @Path("regionId") final Integer regionId, @Path("typeId") final Integer typeId );

	@Headers({ "Content-Type: application/json" })
	@PATCH("/api/v1/public/loyalty/configuration")
	Call<LoyaltyServiceConfiguration> setLoyaltyServiceConfiguration( @Body @Valid @NotNull final LoyaltyServiceConfiguration serviceConfiguration );
}
