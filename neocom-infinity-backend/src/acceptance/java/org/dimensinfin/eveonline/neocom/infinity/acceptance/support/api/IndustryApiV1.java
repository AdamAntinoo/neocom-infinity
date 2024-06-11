package org.dimensinfin.eveonline.neocom.infinity.acceptance.support.api;

import java.util.List;

import org.dimensinfin.eveonline.neocom.infinity.acceptance.support.industry.deserializer.ProcessedBlueprintResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;

/**
 * @author Adam Antinoo (adamantinoo.git@gmail.com)
 * @since 0.20.0
 */
public interface IndustryApiV1 {
	@Headers({ "Content-Type: application/json", "xApp-Signature: S0000.0020.0000" })
	@GET("/api/v2/neospring/industry/pilots/manufacture/blueprints/session/-no-cache-")
	Call<List<ProcessedBlueprintResponse>> getProcessedBlueprints4Pilot( @Header("Cookie") final String userCookie,
	                                                                     @Header("Authorization") final String authorization );
}