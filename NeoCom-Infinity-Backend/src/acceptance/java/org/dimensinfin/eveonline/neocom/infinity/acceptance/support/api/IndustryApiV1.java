package org.dimensinfin.eveonline.neocom.infinity.acceptance.support.api;

import java.util.List;

import org.dimensinfin.eveonline.neocom.infinity.acceptance.support.industry.deserializer.ProcessedBlueprintResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.Path;

/**
 * @author Adam Antinoo (adamantinoo.git@gmail.com)
 * @since 0.20.0
 */
public interface IndustryApiV1 {
	@Headers({ "Content-Type: application/json", "xApp-Signature: S0000.0020.0000" })
	@GET("/api/v1/neocom/industry/pilots/{pilotId}/manufacture/blueprints")
	Call<List<ProcessedBlueprintResponse>> getBlueprints4PilotWithCostIndex( @Header("Cookie") final String userCookie,
	                                                                         @Header("Authorization") final String authorization,
	                                                                         @Path("pilotId") final Integer pilotId );
}