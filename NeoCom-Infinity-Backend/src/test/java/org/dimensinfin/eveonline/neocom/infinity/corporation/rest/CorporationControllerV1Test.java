package org.dimensinfin.eveonline.neocom.infinity.corporation.rest;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.dimensinfin.eveonline.neocom.asset.domain.LocationAssetContainer;
import org.dimensinfin.eveonline.neocom.domain.Corporation;
import org.dimensinfin.eveonline.neocom.infinity.backend.corporation.domain.ShippingYardLocation;
import org.dimensinfin.eveonline.neocom.infinity.backend.corporation.rest.v1.CorporationControllerV1;
import org.dimensinfin.eveonline.neocom.infinity.backend.corporation.rest.v1.CorporationServiceV1;
import org.dimensinfin.eveonline.neocom.infinity.config.security.NeoComAuthenticationProvider;
import org.dimensinfin.eveonline.neocom.infinity.core.exception.NeoComAuthorizationException;

public class CorporationControllerV1Test {
	private static final int TEST_CORPORATION_IDENTIFIER = 98384726;
	private static final int INVALID_CORPORATION_IDENTIFIER = -34;

	// - COMPONENTS
	private CorporationServiceV1 corporationServiceV1;
	private NeoComAuthenticationProvider neoComAuthenticationProvider;

	@BeforeEach
	public void beforeEach() {
		this.corporationServiceV1 = Mockito.mock( CorporationServiceV1.class );
		this.neoComAuthenticationProvider = Mockito.mock( NeoComAuthenticationProvider.class );
	}

	@Test
	public void getCorporationAssetsByLocation() throws IOException {
		// Given
		final List<LocationAssetContainer> locations = new ArrayList<>();
		final ResponseEntity<List<LocationAssetContainer>> response = new ResponseEntity<>( locations, HttpStatus.OK );
		// When
		Mockito.when( this.neoComAuthenticationProvider.getAuthenticatedCorporation() ).thenReturn( TEST_CORPORATION_IDENTIFIER );
		Mockito.when( this.corporationServiceV1.getCorporationAssetsByLocation( Mockito.anyInt() ) ).thenReturn( response );
		// Test
		final CorporationControllerV1 corporationController = new CorporationControllerV1( this.corporationServiceV1,
				this.neoComAuthenticationProvider );
		final ResponseEntity<List<LocationAssetContainer>> obtained = corporationController
				.getCorporationAssetsByLocation( TEST_CORPORATION_IDENTIFIER );
		// Assertions
		Assertions.assertNotNull( obtained );
		Assertions.assertNotNull( obtained.getBody() );
		Assertions.assertEquals( 0, obtained.getBody().size() );
	}

	@Test
	public void getCorporationAssetsByLocationFailure() throws IOException {
		Mockito.when( this.neoComAuthenticationProvider.getAuthenticatedCorporation() ).thenReturn( 3221456 );
		// Exceptions
		Assertions.assertThrows( NeoComAuthorizationException.class, () -> {
			final CorporationControllerV1 corporationController = new CorporationControllerV1( this.corporationServiceV1,
					this.neoComAuthenticationProvider );
			final ResponseEntity<List<LocationAssetContainer>> obtained = corporationController
					.getCorporationAssetsByLocation( TEST_CORPORATION_IDENTIFIER );
		} );
	}

	@Test
	public void getCorporationData() throws IOException {
		final Integer corporationId = TEST_CORPORATION_IDENTIFIER;
		final Corporation corporation = Mockito.mock( Corporation.class );

		// When
		Mockito.when( this.neoComAuthenticationProvider.getAuthenticatedCorporation() ).thenReturn( TEST_CORPORATION_IDENTIFIER );
		Mockito.when( this.corporationServiceV1.getCorporationData( Mockito.anyInt() ) )
				.thenReturn( new ResponseEntity<>( corporation, HttpStatus.OK ) );

		// Test
		final CorporationControllerV1 corporationController = new CorporationControllerV1( this.corporationServiceV1,
				this.neoComAuthenticationProvider );
		Assertions.assertNotNull( corporationController );
		final ResponseEntity<Corporation> obtained = corporationController.getCorporationData( corporationId );

		// Asserts
		Assertions.assertNotNull( obtained );
		Assertions.assertEquals( HttpStatus.OK, obtained.getStatusCode() );
	}

	@Test
	public void getCorporationDataAuthenticationFailure() throws IOException {
		// Given
		final Integer corporationId = TEST_CORPORATION_IDENTIFIER;

		// When
		Mockito.when( this.neoComAuthenticationProvider.getAuthenticatedCorporation() ).thenReturn( INVALID_CORPORATION_IDENTIFIER );

		// Test
		final CorporationControllerV1 corporationController = new CorporationControllerV1( this.corporationServiceV1,
				this.neoComAuthenticationProvider );
		Assertions.assertNotNull( corporationController );
		Assertions.assertThrows( NeoComAuthorizationException.class, () -> {
					final ResponseEntity<Corporation> obtained = corporationController.getCorporationData( corporationId );
				},
				"Expected corporationController.getCorporationData() to throw null verification, but it didn't." );
	}

	@Test
	public void getCorporationShippingYards() throws IOException {
		final Integer corporationId = TEST_CORPORATION_IDENTIFIER;
		final Corporation corporation = Mockito.mock( Corporation.class );

		// When
		Mockito.when( this.neoComAuthenticationProvider.getAuthenticatedCorporation() ).thenReturn( TEST_CORPORATION_IDENTIFIER );
		Mockito.when( this.corporationServiceV1.getCorporationShippingYards( Mockito.anyInt() ) )
				.thenReturn( new ResponseEntity<>( new ArrayList<>(), HttpStatus.OK ) );

		// Test
		final CorporationControllerV1 corporationController = new CorporationControllerV1( this.corporationServiceV1,
				this.neoComAuthenticationProvider );
		Assertions.assertNotNull( corporationController );
		final ResponseEntity<List<ShippingYardLocation>> obtained = corporationController.getCorporationShippingYards( corporationId );

		// Asserts
		Assertions.assertNotNull( obtained );
		Assertions.assertEquals( HttpStatus.OK, obtained.getStatusCode() );
	}

	@Test
	public void getCorporationShippingYardsAuthenticationFailure() throws IOException {
		final Integer corporationId = TEST_CORPORATION_IDENTIFIER;

		// When
		Mockito.when( this.neoComAuthenticationProvider.getAuthenticatedCorporation() ).thenReturn( INVALID_CORPORATION_IDENTIFIER );

		// Test
		final CorporationControllerV1 corporationController = new CorporationControllerV1( this.corporationServiceV1,
				this.neoComAuthenticationProvider );
		Assertions.assertNotNull( corporationController );
		Assertions.assertThrows( NeoComAuthorizationException.class, () -> {
					final ResponseEntity<List<ShippingYardLocation>> obtained = corporationController.getCorporationShippingYards( corporationId );
				},
				"Expected corporationController.getCorporationShippingYards() to throw null verification, but it didn't." );
	}
}
