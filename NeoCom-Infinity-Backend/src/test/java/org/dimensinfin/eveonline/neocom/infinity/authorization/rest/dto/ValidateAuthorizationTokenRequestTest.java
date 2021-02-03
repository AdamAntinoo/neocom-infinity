package org.dimensinfin.eveonline.neocom.infinity.authorization.rest.dto;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import org.dimensinfin.eveonline.neocom.infinity.authorization.client.v1.ValidateAuthorizationTokenRequest;
import org.dimensinfin.eveonline.neocom.provider.ESIDataProvider;

public class ValidateAuthorizationTokenRequestTest {
	@Test
	public void buildComplete() {
		final ValidateAuthorizationTokenRequest request = new ValidateAuthorizationTokenRequest.Builder()
				.withCode( "-TEST-CODE-" )
				.withState( "-TEST-STATE-" )
				.withDataSource( "-OPTIONAL-DATA-SOURCE-" )
				.build();
		Assertions.assertNotNull( request );
		Assertions.assertEquals( "-TEST-CODE-", request.getCode() );
		Assertions.assertEquals( "-TEST-STATE-", request.getState() );
		Assertions.assertEquals( "-OPTIONAL-DATA-SOURCE-", request.getDataSourceName() );
	}

	public void buildFailure() {
		Assertions.assertThrows( NullPointerException.class, () -> {
			final ValidateAuthorizationTokenRequest request = new ValidateAuthorizationTokenRequest.Builder()
					.withCode( null )
					.withState( "-TEST-STATE-" )
					.withDataSource( "-OPTIONAL-DATA-SOURCE-" )
					.build();
		} );
		Assertions.assertThrows( NullPointerException.class, () -> {
			final ValidateAuthorizationTokenRequest request = new ValidateAuthorizationTokenRequest.Builder()
					.withCode( "-TEST-CODE-" )
					.withState( null )
					.withDataSource( "-OPTIONAL-DATA-SOURCE-" )
					.build();
		} );
		Assertions.assertThrows( NullPointerException.class, () -> {
			final ValidateAuthorizationTokenRequest request = new ValidateAuthorizationTokenRequest.Builder()
					.withState( "-TEST-STATE-" )
					.withDataSource( "-OPTIONAL-DATA-SOURCE-" )
					.build();
		} );
		Assertions.assertThrows( NullPointerException.class, () -> {
			final ValidateAuthorizationTokenRequest request = new ValidateAuthorizationTokenRequest.Builder()
					.withCode( "-TEST-CODE-" )
					.withDataSource( "-OPTIONAL-DATA-SOURCE-" )
					.build();
		} );
		final ValidateAuthorizationTokenRequest request1 = new ValidateAuthorizationTokenRequest.Builder()
				.withCode( "-TEST-CODE-" )
				.withState( "-TEST-STATE-" )
				.withDataSource( null )
				.build();
		Assertions.assertNotNull( request1 );
		final ValidateAuthorizationTokenRequest request2 = new ValidateAuthorizationTokenRequest.Builder()
				.withCode( "-TEST-CODE-" )
				.withState( "-TEST-STATE-" )
				.build();
		Assertions.assertNotNull( request2 );
	}

	@Test
	public void buildIncomplete() {
		final ValidateAuthorizationTokenRequest request = new ValidateAuthorizationTokenRequest.Builder()
				.withCode( "-TEST-CODE-" )
				.withState( "-TEST-STATE-" )
				.build();
		Assertions.assertNotNull( request );
		Assertions.assertEquals( "-TEST-CODE-", request.getCode() );
		Assertions.assertEquals( "-TEST-STATE-", request.getState() );
		Assertions.assertEquals( ESIDataProvider.DEFAULT_ESI_SERVER, request.getDataSourceName() );
	}

	@Test
	public void buildNullOptional() {
		final ValidateAuthorizationTokenRequest request = new ValidateAuthorizationTokenRequest.Builder()
				.withCode( "-TEST-CODE-" )
				.withState( "-TEST-STATE-" )
				.withDataSource( null )
				.build();
		Assertions.assertNotNull( request );
		Assertions.assertEquals( ESIDataProvider.DEFAULT_ESI_SERVER, request.getDataSourceName() );
	}

	@Test
	public void getterContract() {
		final ValidateAuthorizationTokenRequest request = new ValidateAuthorizationTokenRequest.Builder()
				.withCode( "-TEST-CODE-" )
				.withState( "-TEST-STATE-" )
				.withDataSource( "-OPTIONAL-DATA-SOURCE-" )
				.build();
		Assertions.assertNotNull( request );
		Assertions.assertEquals( "-TEST-CODE-", request.getCode() );
		Assertions.assertEquals( "-TEST-STATE-", request.getState() );
		Assertions.assertEquals( "-OPTIONAL-DATA-SOURCE-", request.getDataSourceName() );
	}
}
