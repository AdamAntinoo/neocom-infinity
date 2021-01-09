package org.dimensinfin.eveonline.neocom.infinity.support.rest;

import java.io.IOException;
import java.text.MessageFormat;
import java.util.List;

import com.google.gson.GsonBuilder;
import org.joda.time.DateTime;
import org.joda.time.LocalDate;
import org.springframework.stereotype.Component;

import org.dimensinfin.eveonline.neocom.core.support.GSONDateTimeDeserializer;
import org.dimensinfin.eveonline.neocom.core.support.GSONLocalDateDeserializer;
import org.dimensinfin.eveonline.neocom.database.entities.Credential;
import org.dimensinfin.eveonline.neocom.infinity.authorization.client.v1.StoreCredentialRequest;
import org.dimensinfin.eveonline.neocom.infinity.authorization.client.v1.StoreCredentialResponse;
import org.dimensinfin.eveonline.neocom.infinity.support.client.CredentialCountResponse;
import org.dimensinfin.eveonline.neocom.infinity.support.client.ScheduleJobCountResponse;
import org.dimensinfin.eveonline.neocom.service.logger.NeoComLogger;
import org.dimensinfin.eveonline.neocom.service.scheduler.domain.JobRecord;

import retrofit2.Converter;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Component
public class NeoComSupportFeignClient {
	public static final Converter.Factory GSON_CONVERTER_FACTORY =
			GsonConverterFactory.create(
					new GsonBuilder()
							.registerTypeAdapter( DateTime.class, new GSONDateTimeDeserializer() )
							.registerTypeAdapter( LocalDate.class, new GSONLocalDateDeserializer() )
							.create() );

// - G E T T E R S   &   S E T T E R S
	public List<JobRecord> getRegisteredJobs() throws IOException {
		final Response<List<JobRecord>> response = new Retrofit.Builder()
				.baseUrl( NeoComApiv1.NEOCOM_BACKEND_APP_HOST )
				.addConverterFactory( GSON_CONVERTER_FACTORY )
				.build()
				.create( NeoComSupportApi.class )
				.getRegisteredJobs( NeoComApiv1.NEOCOM_BACKEND_ACCEPTED_CONTENT_TYPE )
				.execute();
		if (response.isSuccessful()) {
			NeoComLogger.info( "Request to get scheduler job records." );
			return response.body();
		} else throw new IOException( "Request to get scheduler job records failed." );
	}

	public Integer countCredentials() throws IOException {
		final Response<CredentialCountResponse> response = new Retrofit.Builder()
				.baseUrl( NeoComApiv1.NEOCOM_BACKEND_APP_HOST )
				.addConverterFactory( GSON_CONVERTER_FACTORY )
				.build()
				.create( NeoComSupportApi.class )
				.credentialsCount( NeoComApiv1.NEOCOM_BACKEND_ACCEPTED_CONTENT_TYPE )
				.execute();
		if (response.isSuccessful()) {
			final CredentialCountResponse credentialCountResponse = response.body();
			NeoComLogger.info( MessageFormat.format( "Request to count credentials {0}. Response is 200 OK.",
					credentialCountResponse.getCredentialCount() ) );
			return credentialCountResponse.getCredentialCount();
		} else throw new IOException( "Request to count credentials failed." );
	}

	public Integer countScheduleJobs() throws IOException {
		final Response<ScheduleJobCountResponse> response = new Retrofit.Builder()
				.baseUrl( NeoComApiv1.NEOCOM_BACKEND_APP_HOST )
				.addConverterFactory( GSON_CONVERTER_FACTORY )
				.build()
				.create( NeoComSupportApi.class )
				.scheduledJobsCount( NeoComApiv1.NEOCOM_BACKEND_ACCEPTED_CONTENT_TYPE )
				.execute();
		if (response.isSuccessful()) {
			final ScheduleJobCountResponse scheduleJobCountResponse = response.body();
			NeoComLogger.info( MessageFormat.format( "Request to count scheduled jobs {0}. Response is 200 OK.",
					scheduleJobCountResponse.getSchedulerJobCount() ) );
			return scheduleJobCountResponse.getSchedulerJobCount();
		} else throw new IOException( "Request to count jobs failed." );
	}

	public Integer deleteAllCredentials() throws IOException {
		final Response<CredentialCountResponse> response = new Retrofit.Builder()
				.baseUrl( NeoComApiv1.NEOCOM_BACKEND_APP_HOST )
				.addConverterFactory( GSON_CONVERTER_FACTORY )
				.build()
				.create( NeoComSupportApi.class )
				.deleteAllCredentials( NeoComApiv1.NEOCOM_BACKEND_ACCEPTED_CONTENT_TYPE )
				.execute();
		if (response.isSuccessful()) {
			final CredentialCountResponse credentialCountResponse = response.body();
			NeoComLogger.info( MessageFormat.format( "Request to count credentials {0}. Response is 200 OK.",
					credentialCountResponse.getCredentialCount() ) );
			return credentialCountResponse.getCredentialCount();
		} else throw new IOException( "Request to delete all credentials failed." );
	}

	public Credential findCredentialById( final String credentialId ) throws IOException {
		final Response<Credential> response = new Retrofit.Builder()
				.baseUrl( NeoComApiv1.NEOCOM_BACKEND_APP_HOST )
				.addConverterFactory( GSON_CONVERTER_FACTORY )
				.build()
				.create( NeoComSupportApi.class )
				.findCredentialById( NeoComApiv1.NEOCOM_BACKEND_ACCEPTED_CONTENT_TYPE, credentialId )
				.execute();
		if (response.isSuccessful()) {
			NeoComLogger.info( MessageFormat.format( "Request to get credential by id {0}. Response is 200 OK.", credentialId ) );
			return response.body();
		} else throw new IOException( "Request to get credential by id failed." );
	}

	public Integer restartScheduler() throws IOException {
		final Response<ScheduleJobCountResponse> response = new Retrofit.Builder()
				.baseUrl( NeoComApiv1.NEOCOM_BACKEND_APP_HOST )
				.addConverterFactory( GSON_CONVERTER_FACTORY )
				.build()
				.create( NeoComSupportApi.class )
				.restartScheduler( NeoComApiv1.NEOCOM_BACKEND_ACCEPTED_CONTENT_TYPE )
				.execute();
		if (response.isSuccessful()) {
			final ScheduleJobCountResponse scheduleJobCountResponse = response.body();
			NeoComLogger.info( MessageFormat.format( "Request to count scheduled jobs {0}. Response is 200 OK.",
					scheduleJobCountResponse.getSchedulerJobCount() ) );
			return scheduleJobCountResponse.getSchedulerJobCount();
		} else throw new IOException( "Request to reinitialize the scheduler failed." );
	}

	public StoreCredentialResponse storeCredential( final StoreCredentialRequest storeCredentialRequest ) throws IOException {
		final Response<StoreCredentialResponse> response = new Retrofit.Builder()
				.baseUrl( NeoComApiv1.NEOCOM_BACKEND_APP_HOST )
				.addConverterFactory( GSON_CONVERTER_FACTORY )
				.build()
				.create( NeoComSupportApi.class )
				.storeCredential( NeoComApiv1.NEOCOM_BACKEND_ACCEPTED_CONTENT_TYPE,
						storeCredentialRequest.getCredential().getAccountId(),
						storeCredentialRequest.getCredential() )
				.execute();
		if (response.isSuccessful()) {
			NeoComLogger.info( MessageFormat.format( "Request to store the credential {0}. Response is 200 OK.",
					storeCredentialRequest.getCredential().getAccountId() ) );
			return response.body();
		} else throw new IOException( "Request to store credential failed." );
	}
}
