package org.dimensinfin.eveonline.neocom.infinity.support.rest;

import org.dimensinfin.eveonline.neocom.infinity.backend.universe.domain.ServerStatus;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Path;

public interface UniverseApiV1 {
	// - G E T T E R S   &   S E T T E R S
	@Deprecated
	@Headers({ "Content-Type: application/json" })
	@GET("/api/v1/neocom/server/datasource")
	Call<ServerStatus> getServerStatus();

	@Deprecated
	@Headers({ "Content-Type: application/json" })
	@GET("/api/v1/neocom/server")
	Call<ServerStatus> getServerStatusDefault();

	@Headers({ "Content-Type: application/json" })
	@GET("/api/v1/server/status")
	Call<ServerStatus> getServerStatusV2();

	@Deprecated
	@Headers({ "Content-Type: application/json" })
	@GET("/api/v1/neocom/server/datasource/{dataSource}")
	Call<ServerStatus> getServerStatus( final @Path("datasource") String datasource );
}
