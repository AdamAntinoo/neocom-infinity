package org.dimensinfin.eveonline.neocom.infinity.support.rest;

import java.util.List;

import org.dimensinfin.eveonline.neocom.database.entities.Credential;
import org.dimensinfin.eveonline.neocom.database.entities.MiningExtractionEntity;
import org.dimensinfin.eveonline.neocom.infinity.acceptance.support.dto.PilotDto;
import org.dimensinfin.eveonline.neocom.infinity.authorization.client.v1.StoreCredentialResponse;
import org.dimensinfin.eveonline.neocom.infinity.backend.authorization.domain.AuthenticationStateResponse;
import org.dimensinfin.eveonline.neocom.infinity.backend.authorization.domain.AuthorizationTokenResponse;
import org.dimensinfin.eveonline.neocom.infinity.backend.industry.fitting.domain.FittingBuildConfiguration;
import org.dimensinfin.eveonline.neocom.infinity.backend.industry.fitting.domain.FittingConfigurations;
import org.dimensinfin.eveonline.neocom.infinity.backend.market.domain.MarketData;
import org.dimensinfin.eveonline.neocom.infinity.support.corporation.rest.v1.CorporationResponse;
import org.dimensinfin.eveonline.neocom.infinity.support.corporation.rest.v1.LocationAssetContainer;
import org.dimensinfin.eveonline.neocom.infinity.support.fitting.rest.v1.FittingResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface NeoComApiv1 {
	String NEOCOM_BACKEND_ACCEPTED_CONTENT_TYPE = "application/json";

	@GET("/api/v1/neocom/corporations/{corporationId}/assetsbylocation")
	Call<List<LocationAssetContainer>> getCorporationAssetsByLocation( @Header("Content-Type") final String contentType,
	                                                                   @Header("Authorization") final String authorization,
	                                                                   @Path("corporationId") final Integer corporationId );

	@GET("/api/v1/neocom/corporations/{corporationId}")
	Call<CorporationResponse> getCorporationData( @Header("Content-Type") final String contentType,
	                                              @Header("Authorization") final String authorization,
	                                              @Path("corporationId") final Integer corporationId );

	@Headers({ "Content-Type: application/json" })
	@GET("/api/v1/neocom/industry/fittings/buildConfiguration/{fittingId}")
	Call<FittingConfigurations> getFittingBuildConfigurationById( @Header("Authorization") final String authorization,
	                                                              @Path("fittingId") Integer fittingId );

	@Headers({ "Content-Type: application/json" })
	@GET("/api/v1/neocom/industry/fittings/buildConfiguration/{fittingId}/savedConfiguration")
	Call<FittingBuildConfiguration> getFittingBuildConfigurationSavedConfiguration( @Header("Authorization") final String authorization,
	                                                                                @Path("fittingId") Integer fittingId );

	@Headers({ "Content-Type: application/json" })
	@GET("/api/v1/neocom/industry/fittings/buildConfiguration/{fittingId}/targetConfiguration")
	Call<FittingBuildConfiguration> getFittingBuildConfigurationTargetConfiguration( @Header("Authorization") final String authorization,
	                                                                                 @Path("fittingId") Integer fittingId );

	@Headers({ "Content-Type: application/json" })
	@GET("/api/v1/neocom/market/consolidated/{itemId}")
	Call<MarketData> getMarketConsolidated4ItemId( @Header("Authorization") final String authorization,
	                                               @Path("itemId") final Integer itemId );

	@Headers({ "Content-Type: application/json", "xApp-Signature: S0000.0020.0000" })
	@GET("/api/v1/neocom/pilots/{pilotId}")
	Call<PilotDto> getPilotData( @Header("Cookie") final String userCookie,
	                             @Header("Authorization") final String authorization,
	                             @Path("pilotId") final Integer pilotId );

	@GET("/api/v1/neocom/fittings/pilot/{pilotId}")
	Call<List<FittingResponse>> getPilotFittings( @Header("Content-Type") final String contentType,
	                                              @Header("Authorization") final String authorization,
	                                              @Path("pilotId") final Integer pilotId );

	@GET("/api/v1/neocom/miningextractions/pilot/{pilotIdentifier}/today")
	Call<List<MiningExtractionEntity>> getTodayMiningExtractions4Pilot( @Header("Content-Type") final String contentType,
	                                                                    @Header("Authorization") final String authorization,
	                                                                    @Path("pilotIdentifier") final Integer pilotIdentifier );

	@Headers({ "Content-Type: application/json" })
	@PUT("/api/v1/neocom/credentials/{credentialId}")
	Call<StoreCredentialResponse> storeCredential( @Path("credentialId") final Integer credentialId,
	                                               @Body final Credential credential );

	@Headers({ "Content-Type: application/json" })
	@GET("/api/v1/neocom/validateAuthenticationState")
	Call<AuthenticationStateResponse> validateAuthenticationState( @Header("Cookie") final String userCookie );

	@GET("/api/v1/neocom/validateAuthorizationToken")
	Call<AuthorizationTokenResponse> validateAuthorizationToken( @Header("Content-Type") final String contentType,
	                                                             @Query("code") final String code,
	                                                             @Query("state") final String state,
	                                                             @Query("datasource") final String datasource );
}
