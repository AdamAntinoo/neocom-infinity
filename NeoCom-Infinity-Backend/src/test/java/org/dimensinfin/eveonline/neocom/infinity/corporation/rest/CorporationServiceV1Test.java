package org.dimensinfin.eveonline.neocom.infinity.corporation.rest;

import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.Mockito;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.dimensinfin.eveonline.neocom.asset.service.AssetDownloadProcessorJob;
import org.dimensinfin.eveonline.neocom.database.entities.Credential;
import org.dimensinfin.eveonline.neocom.database.repositories.AssetRepository;
import org.dimensinfin.eveonline.neocom.database.repositories.CredentialRepository;
import org.dimensinfin.eveonline.neocom.domain.Corporation;
import org.dimensinfin.eveonline.neocom.domain.Pilot;
import org.dimensinfin.eveonline.neocom.esiswagger.model.GetCorporationsCorporationIdOk;
import org.dimensinfin.eveonline.neocom.infinity.backend.character.pilot.rest.v1.PilotServiceV1;
import org.dimensinfin.eveonline.neocom.infinity.backend.character.pilot.rest.v2.PilotServiceV2;
import org.dimensinfin.eveonline.neocom.infinity.config.security.CredentialDetails;
import org.dimensinfin.eveonline.neocom.infinity.config.security.CredentialDetailsService;
import org.dimensinfin.eveonline.neocom.infinity.config.security.NeoComAuthenticationProvider;
import org.dimensinfin.eveonline.neocom.infinity.corporation.domain.ShippingYardLocation;
import org.dimensinfin.eveonline.neocom.service.ESIDataService;
import org.dimensinfin.eveonline.neocom.service.LocationCatalogService;

public class CorporationServiceV1Test {
	private static final int TEST_CORPORATION_IDENTIFIER = 98384726;

	// - COMPONENTS
	//	private ESIDataProviderWrapper esiDataProviderWrapper;
	private ESIDataService esiDataService;
	private PilotServiceV1 pilotServiceV1;
	//	private AssetRepositoryWrapper assetRepositoryWrapper;
	private AssetRepository assetRepository;
	//	private CredentialRepository credentialRepositoryWrapper;
	private CredentialRepository credentialRepository;
	private LocationCatalogService locationCatalogServiceWrapper;
	private LocationCatalogService locationCatalogService;
	private CredentialDetailsService credentialDetailsService;
	private NeoComAuthenticationProvider neoComAuthenticationProvider;
	private PilotServiceV2 pilotServiceV2;

	@BeforeEach
	public void beforeEach() {
		//		this.esiDataProviderWrapper = Mockito.mock( ESIDataProviderWrapper.class );
		this.esiDataService = Mockito.mock( ESIDataService.class );
		this.pilotServiceV1 = Mockito.mock( PilotServiceV1.class );
		//		this.assetRepositoryWrapper = Mockito.mock( AssetRepositoryWrapper.class );
		this.assetRepository = Mockito.mock( AssetRepository.class );
		//		this.credentialRepositoryWrapper = Mockito.mock( CredentialRepositoryWrapper.class );
		this.credentialRepository = Mockito.mock( CredentialRepository.class );
		this.locationCatalogServiceWrapper = Mockito.mock( LocationCatalogService.class );
		this.locationCatalogService = Mockito.mock( LocationCatalogService.class );
		this.credentialDetailsService = Mockito.mock( CredentialDetailsService.class );
		this.neoComAuthenticationProvider = Mockito.mock( NeoComAuthenticationProvider.class );

		//		Mockito.when( esiDataProviderWrapper.getSingleton() ).thenReturn( esiDataService );
		//		Mockito.when( this.assetRepositoryWrapper.getSingleton() ).thenReturn( this.assetRepository );
		//		Mockito.when( credentialRepositoryWrapper.getSingleton() ).thenReturn( credentialRepository );
		//		Mockito.when( locationCatalogServiceWrapper ).thenReturn( locationCatalogService );
		this.pilotServiceV2 = Mockito.mock( PilotServiceV2.class );
	}

	//	@Test
	public void getCorporationData() {
		final GetCorporationsCorporationIdOk corporationData = Mockito.mock( GetCorporationsCorporationIdOk.class );
		final Pilot pilot = Mockito.mock( Pilot.class );
		final Integer corporationId = TEST_CORPORATION_IDENTIFIER;

		// When
		Mockito.when( this.esiDataService.getCorporationsCorporationId( Mockito.anyInt() ) ).thenReturn( corporationData );
		Mockito.when( this.pilotServiceV1.buildPilotData( Mockito.anyInt() ) ).thenReturn( pilot );

		// Test
		final CorporationServiceV1 corporationServiceV1 = new CorporationServiceV1(
				this.esiDataService,
				this.pilotServiceV2,
				this.assetRepository,
				this.credentialRepository,
				this.locationCatalogServiceWrapper,
				this.credentialDetailsService,
				this.neoComAuthenticationProvider );
		Assertions.assertNotNull( corporationServiceV1 );
		final ResponseEntity<Corporation> obtained = corporationServiceV1.getCorporationData( corporationId );

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
		Mockito.when( this.neoComAuthenticationProvider.getAuthenticatedUniqueId() ).thenReturn( "-TESTING-UNIQUE-ID-" );
		Mockito.when( this.credentialDetailsService.loadUserByUsername( Mockito.anyString() ) ).thenReturn( credentialDetails );
		Mockito.when( credentialDetails.getCredential() ).thenReturn( credential );
		// TODO - The api has been moved to the AsserProvider
		//		Mockito.when( downloadProcessorJob.downloadCorporationAssets( Mockito.anyInt() ) ).thenReturn( new ArrayList<>() );

		// Test
		final CorporationServiceV1 corporationServiceV1 = new CorporationServiceV1(
				this.esiDataService,
				this.pilotServiceV2,
				this.assetRepository,
				this.credentialRepository,
				this.locationCatalogServiceWrapper,
				this.credentialDetailsService,
				this.neoComAuthenticationProvider );
		Assertions.assertNotNull( corporationServiceV1 );
		final ResponseEntity<List<ShippingYardLocation>> obtained = corporationServiceV1.getCorporationShippingYards( corporationId );

		// Asserts
		Assertions.assertNotNull( obtained );
		Assertions.assertEquals( HttpStatus.OK, obtained.getStatusCode() );
	}

	//	@Test
	public void getCorporationShippingYardsWithUserlabels() {
		// Given
	}
}
