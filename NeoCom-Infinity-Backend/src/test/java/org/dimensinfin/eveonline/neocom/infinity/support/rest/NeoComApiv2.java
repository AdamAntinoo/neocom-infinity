package org.dimensinfin.eveonline.neocom.infinity.support.rest;

import java.util.List;

import org.dimensinfin.eveonline.neocom.database.entities.Credential;
import org.dimensinfin.eveonline.neocom.database.entities.MiningExtractionEntity;
import org.dimensinfin.eveonline.neocom.infinity.authorization.client.v1.StoreCredentialResponse;
import org.dimensinfin.eveonline.neocom.infinity.authorization.client.v1.ValidateAuthorizationTokenResponse;
import org.dimensinfin.eveonline.neocom.infinity.pilot.rest.representation.PilotModel;
import org.dimensinfin.eveonline.neocom.infinity.support.corporation.rest.v1.CorporationResponse;
import org.dimensinfin.eveonline.neocom.infinity.support.corporation.rest.v1.LocationAssetContainer;
import org.dimensinfin.eveonline.neocom.infinity.support.fitting.rest.v1.FittingResponse;
import org.dimensinfin.eveonline.neocom.infinity.support.pilot.rest.v1.PilotResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface NeoComApiv2 {
	@GET("/api/v1/neocom/pilots/{pilotId}")
	Call<PilotModel> getPilotData( @Header("Content-Type") final String contentType,
	                               @Header("Authorization") final String authorization,
	                               @Path("pilotId") final Integer pilotId );
}
