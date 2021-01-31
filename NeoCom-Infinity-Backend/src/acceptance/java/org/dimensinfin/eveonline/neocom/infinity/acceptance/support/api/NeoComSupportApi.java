package org.dimensinfin.eveonline.neocom.infinity.acceptance.support.api;

import java.util.List;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.dimensinfin.eveonline.neocom.database.entities.Credential;
import org.dimensinfin.eveonline.neocom.infinity.authorization.client.v1.StoreCredentialResponse;
import org.dimensinfin.eveonline.neocom.infinity.support.client.CredentialCountResponse;
import org.dimensinfin.eveonline.neocom.infinity.support.client.ScheduleJobCountResponse;
import org.dimensinfin.eveonline.neocom.service.scheduler.domain.JobRecord;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface NeoComSupportApi {
	@GET("/api/v1/neocom/support/credentials/count")
	Call<CredentialCountResponse> credentialsCount( @Header("Content-Type") final String contentType );

	@GET("/api/v1/neocom/support/credentials/deleteall")
	Call<CredentialCountResponse> deleteAllCredentials( @Header("Content-Type") final String contentType );

	@Headers({ "Content-Type: application/json" })
	@GET("/api/v1/public/support/loyalty/deleteall")
	Call<Integer> deleteAllLoyaltyOffers();

	@GET("/api/v1/neocom/support/credentials/{credentialId}")
	Call<Credential> findCredentialById( @Header("Content-Type") final String contentType,
	                                     @Path("credentialId") final String credentialId );

	@GET("/api/v1/neocom/support/scheduler/jobs")
	Call<List<JobRecord>> getRegisteredJobs( @Header("Content-Type") final String contentType );

	@GET("/api/v1/neocom/support/scheduler/restart")
	Call<ScheduleJobCountResponse> restartScheduler( @Header("Content-Type") final String contentType );

	@GET("/api/v1/neocom/support/scheduler/jobs/count")
	Call<ScheduleJobCountResponse> scheduledJobsCount( @Header("Content-Type") final String contentType );

	@PUT("/api/v1/neocom/credentials/{credentialId}")
	Call<StoreCredentialResponse> storeCredential( @Header("Content-Type") final String contentType,
	                                               @Path("credentialId") @NotNull final Integer credentialId,
	                                               @Body @Valid final Credential credential );
}
