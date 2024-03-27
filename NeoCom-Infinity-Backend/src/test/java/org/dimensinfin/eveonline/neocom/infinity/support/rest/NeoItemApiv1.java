package org.dimensinfin.eveonline.neocom.infinity.support.rest;

import org.dimensinfin.eveonline.neocom.infinity.support.neoitem.rest.v1.NeoItemTransport;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Path;

public interface NeoItemApiv1 {
	@GET("/api/v1/neoitem/item/basic/{itemId}")
	Call<NeoItemTransport> getItemBasic( @Header("Content-Type") final String contentType,
	                                     @Path("itemId") final Integer itemId );
}