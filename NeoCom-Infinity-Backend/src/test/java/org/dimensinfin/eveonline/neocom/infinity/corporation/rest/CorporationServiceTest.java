package org.dimensinfin.eveonline.neocom.infinity.corporation.rest;

import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.dimensinfin.eveonline.neocom.adapter.LocationCatalogService;
import org.dimensinfin.eveonline.neocom.asset.service.AssetDownloadProcessorJob;
import org.dimensinfin.eveonline.neocom.database.entities.Credential;
import org.dimensinfin.eveonline.neocom.database.repositories.AssetRepository;
import org.dimensinfin.eveonline.neocom.database.repositories.CredentialRepository;
import org.dimensinfin.eveonline.neocom.domain.Corporation;
import org.dimensinfin.eveonline.neocom.domain.Pilot;
import org.dimensinfin.eveonline.neocom.esiswagger.model.GetCorporationsCorporationIdOk;
import org.dimensinfin.eveonline.neocom.infinity.adapter.AssetRepositoryWrapper;
import org.dimensinfin.eveonline.neocom.infinity.adapter.CredentialRepositoryWrapper;
import org.dimensinfin.eveonline.neocom.infinity.adapter.ESIDataProviderWrapper;
import org.dimensinfin.eveonline.neocom.infinity.adapter.LocationCatalogServiceWrapper;
import org.dimensinfin.eveonline.neocom.infinity.core.security.CredentialDetails;
import org.dimensinfin.eveonline.neocom.infinity.core.security.CredentialDetailsService;
import org.dimensinfin.eveonline.neocom.infinity.core.security.NeoComAuthenticationProvider;
import org.dimensinfin.eveonline.neocom.infinity.corporation.domain.ShippingYardLocation;
import org.dimensinfin.eveonline.neocom.infinity.backend.character.pilot.rest.v1.PilotServiceV1;
import org.dimensinfin.eveonline.neocom.provider.ESIDataProvider;

public class CorporationServiceTest {
	private static final int TEST_CORPORATION_IDENTIFIER = 98384726;

	// - COMPONENTS
	private ESIDataProviderWrapper esiDataProviderWrapper;
	private ESIDataProvider esiDataProvider;
	private PilotServiceV1 pilotServiceV1;
	private AssetRepositoryWrapper assetRepositoryWrapper;
	private AssetRepository assetRepository;
	private CredentialRepositoryWrapper credentialRepositoryWrapper;
	private CredentialRepository credentialRepository;
	private LocationCatalogServiceWrapper locationCatalogServiceWrapper;
	private LocationCatalogService locationCatalogService;
	private CredentialDetailsService credentialDetailsService;
	private NeoComAuthenticationProvider neoComAuthenticationProvider;

	@BeforeEach
	public void beforeEach() {
		this.esiDataProviderWrapper = Mockito.mock( ESIDataProviderWrapper.class );
		this.esiDataProvider = Mockito.mock( ESIDataProvider.class );
		this.pilotServiceV1 = Mockito.mock( PilotServiceV1.class );
		this.assetRepositoryWrapper = Mockito.mock( AssetRepositoryWrapper.class );
		this.assetRepository = Mockito.mock( AssetRepository.class );
		this.credentialRepositoryWrapper = Mockito.mock( CredentialRepositoryWrapper.class );
		this.credentialRepository = Mockito.mock( CredentialRepository.class );
		this.locationCatalogServiceWrapper = Mockito.mock( LocationCatalogServiceWrapper.class );
		this.locationCatalogService = Mockito.mock( LocationCatalogService.class );
		this.credentialDetailsService = Mockito.mock( CredentialDetailsService.class );
		this.neoComAuthenticationProvider = Mockito.mock( NeoComAuthenticationProvider.class );

		Mockito.when( esiDataProviderWrapper.getSingleton() ).thenReturn( esiDataProvider );
		Mockito.when( assetRepositoryWrapper.getSingleton() ).thenReturn( assetRepository );
		Mockito.when( credentialRepositoryWrapper.getSingleton() ).thenReturn( credentialRepository );
		Mockito.when( locationCatalogServiceWrapper.getSingleton() ).thenReturn( locationCatalogService );
	}

	@Test
	public void getCorporationData() {
		final GetCorporationsCorporationIdOk corporationData = Mockito.mock( GetCorporationsCorporationIdOk.class );
		final Pilot pilot = Mockito.mock( Pilot.class );
		final Integer corporationId = TEST_CORPORATION_IDENTIFIER;

		// When
		Mockito.when( this.esiDataProvider.getCorporationsCorporationId( Mockito.anyInt() ) ).thenReturn( corporationData );
		Mockito.when( this.pilotServiceV1.buildPilotData( Mockito.anyInt() ) ).thenReturn( pilot );

		// Test
		final CorporationService corporationService = new CorporationService(
				esiDataProviderWrapper,
				pilotServiceV1,
				assetRepositoryWrapper,
				credentialRepositoryWrapper,
				locationCatalogServiceWrapper,
				credentialDetailsService,
				neoComAuthenticationProvider );
		Assertions.assertNotNull( corporationService );
		final ResponseEntity<Corporation> obtained = corporationService.getCorporationData( corporationId );

		// Asserts
		Assertions.assertNotNull( obtained );
		Assertions.assertEquals( HttpStatus.OK, obtained.getStatusCode() );
	}

//	@Test
	public void getCorporationShippingYards() {
		// Given
		final Integer corporationId = TEST_CORPORATION_IDENTIFIER;
		final CredentialDetails credentialDetails = Mockito.mock( CredentialDetails.class );
		final AssetDownloadProcessorJob downloadProcessorJob = Mockito.mock( AssetDownloadProcessorJob.class );
		final Credential credential = Mockito.mock( Credential.class );

		// When
		Mockito.when( neoComAuthenticationProvider.getAuthenticatedUniqueId() ).thenReturn( "-TESTING-UNIQUE-ID-" );
		Mockito.when( credentialDetailsService.loadUserByUsername( Mockito.anyString() ) ).thenReturn( credentialDetails );
		Mockito.when( credentialDetails.getCredential() ).thenReturn( credential );
		// TODO - The api has been moved to the AsserProvider
//		Mockito.when( downloadProcessorJob.downloadCorporationAssets( Mockito.anyInt() ) ).thenReturn( new ArrayList<>() );

		// Test
		final CorporationService corporationService = new CorporationService(
				esiDataProviderWrapper,
				pilotServiceV1,
				assetRepositoryWrapper,
				credentialRepositoryWrapper,
				locationCatalogServiceWrapper,
				credentialDetailsService,
				neoComAuthenticationProvider );
		Assertions.assertNotNull( corporationService );
		final ResponseEntity<List<ShippingYardLocation>> obtained = corporationService.getCorporationShippingYards( corporationId );

		// Asserts
		Assertions.assertNotNull( obtained );
		Assertions.assertEquals( HttpStatus.OK, obtained.getStatusCode() );
	}

	@Test
	public void getCorporationShippingYardsWithUserlabels() {
		// Given
	}
}
