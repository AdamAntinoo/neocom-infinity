package org.dimensinfin.eveonline.neocom.infinity.support.rest;

import java.util.List;

import org.dimensinfin.eveonline.neocom.character.domain.PilotV1;
import org.dimensinfin.eveonline.neocom.infinity.backend.character.fitting.domain.FittingModel;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.Path;

public interface NeoComApiv2 {
	@Headers({ "Content-Type: application/json" })
	@GET("/api/v2/neocom/pilots/{pilotId}")
	Call<PilotV1> getPilotData( @Header("Authorization") final String authorization,
	                            @Path("pilotId") final Integer pilotId );

	@Headers({ "Content-Type: application/json" })
	@GET("/api/v2/neocom/pilots/{pilotId}/fittings")
	Call<List<FittingModel>> getPilotFittings( @Header("Authorization") final String authorization,
	                                           @Path("pilotId") final Integer pilotId );
}
