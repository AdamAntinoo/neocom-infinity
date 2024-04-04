package org.dimensinfin.eveonline.neocom.infinity.support.rest;

import org.dimensinfin.eveonline.neocom.infinity.backend.universe.domain.EsiItemModel;
import org.dimensinfin.eveonline.neocom.infinity.support.neoitem.rest.v1.NeoItemTransport;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Path;

public interface UniverseApiV2 {
	@Headers({ "Content-Type: application/json" })
	@GET("/api/v2/universe/items/{itemId}")
	Call<EsiItemModel> getItem( final @Path("itemId") Integer itemId );
}
