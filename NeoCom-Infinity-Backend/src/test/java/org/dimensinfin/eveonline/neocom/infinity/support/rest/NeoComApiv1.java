package org.dimensinfin.eveonline.neocom.infinity.support.rest;

import java.util.List;

import org.dimensinfin.eveonline.neocom.database.entities.Credential;
import org.dimensinfin.eveonline.neocom.database.entities.MiningExtractionEntity;
import org.dimensinfin.eveonline.neocom.infinity.authorization.client.v1.StoreCredentialResponse;
import org.dimensinfin.eveonline.neocom.infinity.authorization.client.v1.ValidateAuthorizationTokenResponse;
import org.dimensinfin.eveonline.neocom.infinity.backend.industry.fitting.domain.FittingBuildConfiguration;
import org.dimensinfin.eveonline.neocom.infinity.backend.industry.fitting.domain.FittingConfigurations;
import org.dimensinfin.eveonline.neocom.infinity.support.corporation.rest.v1.CorporationResponse;
import org.dimensinfin.eveonline.neocom.infinity.support.corporation.rest.v1.LocationAssetContainer;
import org.dimensinfin.eveonline.neocom.infinity.support.fitting.rest.v1.FittingResponse;
import org.dimensinfin.eveonline.neocom.infinity.support.pilot.rest.v1.PilotResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface NeoComApiv1 {
	String NEOCOM_BACKEND_APP_HOST = "http://localhost:5240";
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

	@GET("/api/v1/neocom/pilots/{pilotId}")
	Call<PilotResponse> getPilotData( @Header("Content-Type") final String contentType,
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

	@PUT("/api/v1/neocom/credentials/{credentialId}")
	Call<StoreCredentialResponse> storeCredential( @Header("Content-Type") final String contentType,
	                                               @Path("credentialId") final Integer credentialId,
	                                               @Body final Credential credential );

	@GET("/api/v1/neocom/validateAuthorizationToken?")
	Call<ValidateAuthorizationTokenResponse> validateAuthorizationToken( @Header("Content-Type") final String contentType,
	                                                                     @Query("code") final String code,
	                                                                     @Query("state") final String state,
	                                                                     @Query("datasource") final String datasource );
}
