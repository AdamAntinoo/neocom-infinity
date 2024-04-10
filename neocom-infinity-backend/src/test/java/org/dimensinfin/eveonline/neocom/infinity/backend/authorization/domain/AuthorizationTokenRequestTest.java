package org.dimensinfin.eveonline.neocom.infinity.backend.authorization.domain;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import org.dimensinfin.eveonline.neocom.auth.NeoComOAuth2Flow;
import org.dimensinfin.eveonline.neocom.provider.ESIDataProvider;

public class AuthorizationTokenRequestTest {
	@Test
	public void buildContract() {
		final AuthorizationTokenRequest request = new AuthorizationTokenRequest.Builder()
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
			final AuthorizationTokenRequest request = new AuthorizationTokenRequest.Builder()
					.withCode( null )
					.withState( "-TEST-STATE-" )
					.withDataSource( "-OPTIONAL-DATA-SOURCE-" )
					.build();
		} );
		Assertions.assertThrows( NullPointerException.class, () -> {
			final AuthorizationTokenRequest request = new AuthorizationTokenRequest.Builder()
					.withCode( "-TEST-CODE-" )
					.withState( null )
					.withDataSource( "-OPTIONAL-DATA-SOURCE-" )
					.build();
		} );
		Assertions.assertThrows( NullPointerException.class, () -> {
			final AuthorizationTokenRequest request = new AuthorizationTokenRequest.Builder()
					.withState( "-TEST-STATE-" )
					.withDataSource( "-OPTIONAL-DATA-SOURCE-" )
					.build();
		} );
		Assertions.assertThrows( NullPointerException.class, () -> {
			final AuthorizationTokenRequest request = new AuthorizationTokenRequest.Builder()
					.withCode( "-TEST-CODE-" )
					.withDataSource( "-OPTIONAL-DATA-SOURCE-" )
					.build();
		} );
		final AuthorizationTokenRequest request1 = new AuthorizationTokenRequest.Builder()
				.withCode( "-TEST-CODE-" )
				.withState( "-TEST-STATE-" )
				.withDataSource( null )
				.build();
		Assertions.assertNotNull( request1 );
		final AuthorizationTokenRequest request2 = new AuthorizationTokenRequest.Builder()
				.withCode( "-TEST-CODE-" )
				.withState( "-TEST-STATE-" )
				.build();
		Assertions.assertNotNull( request2 );
	}

	@Test
	public void buildIncomplete() {
		final AuthorizationTokenRequest request = new AuthorizationTokenRequest.Builder()
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
		final AuthorizationTokenRequest request = new AuthorizationTokenRequest.Builder()
				.withCode( "-TEST-CODE-" )
				.withState( "-TEST-STATE-" )
				.withDataSource( null )
				.build();
		Assertions.assertNotNull( request );
		Assertions.assertEquals( ESIDataProvider.DEFAULT_ESI_SERVER, request.getDataSourceName() );
	}

	@Test
	public void getterContract() {
		final NeoComOAuth2Flow flow = Mockito.mock( NeoComOAuth2Flow.class );
		final AuthorizationTokenRequest request = new AuthorizationTokenRequest.Builder()
				.withCode( "-TEST-CODE-" )
				.withState( "-TEST-STATE-" )
				.withDataSource( "-OPTIONAL-DATA-SOURCE-" )
				.build();
		Assertions.assertNotNull( request );
		Assertions.assertEquals( "-TEST-CODE-", request.getCode() );
		Assertions.assertEquals( "-TEST-STATE-", request.getState() );
		Assertions.assertEquals( "-OPTIONAL-DATA-SOURCE-", request.getDataSourceName() );
		request.setRunningFlow( flow );
		Assertions.assertNotNull( request.getOauthFlow() );
	}
}
