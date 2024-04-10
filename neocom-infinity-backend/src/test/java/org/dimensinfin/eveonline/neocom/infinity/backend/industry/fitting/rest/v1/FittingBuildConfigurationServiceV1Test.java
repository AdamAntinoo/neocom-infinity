package org.dimensinfin.eveonline.neocom.infinity.backend.industry.fitting.rest.v1;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;

import org.dimensinfin.eveonline.neocom.database.entities.Credential;
import org.dimensinfin.eveonline.neocom.domain.EsiType;
import org.dimensinfin.eveonline.neocom.domain.space.SpaceRegion;
import org.dimensinfin.eveonline.neocom.esiswagger.model.GetCharactersCharacterIdFittings200Ok;
import org.dimensinfin.eveonline.neocom.esiswagger.model.GetCorporationsCorporationIdOk;
import org.dimensinfin.eveonline.neocom.infinity.backend.industry.fitting.domain.FittingBuildConfiguration;
import org.dimensinfin.eveonline.neocom.infinity.backend.industry.fitting.domain.FittingConfigurations;
import org.dimensinfin.eveonline.neocom.infinity.backend.industry.fitting.persistence.BuildActionPreferencesRepository;
import org.dimensinfin.eveonline.neocom.infinity.config.security.CredentialDetails;
import org.dimensinfin.eveonline.neocom.infinity.config.security.CredentialDetailsService;
import org.dimensinfin.eveonline.neocom.infinity.config.security.NeoComAuthenticationProvider;
import org.dimensinfin.eveonline.neocom.service.ESIDataService;
import org.dimensinfin.eveonline.neocom.service.LocationCatalogService;
import org.dimensinfin.eveonline.neocom.service.ResourceFactory;

import static org.dimensinfin.eveonline.neocom.infinity.backend.support.TestDataConstants.FittingConstants.TEST_FITTING_ID;
import static org.dimensinfin.eveonline.neocom.infinity.backend.support.TestDataConstants.PilotConstants.TEST_PILOT_ID;
import static org.dimensinfin.eveonline.neocom.infinity.backend.support.TestDataConstants.PilotConstants.TEST_PILOT_NAME;

public class FittingBuildConfigurationServiceV1Test {
	private static final Integer TEST_CREDENTIAL_ID = 546789;
	private static final String TEST_CREDENTIAL_UNIQUE_ID = "-CREDENTIAL-UNIQUE-ID-";
	private Integer fittingId;
	private NeoComAuthenticationProvider neoComAuthenticationProvider;
	private CredentialDetailsService credentialDetailsService;
	private ESIDataService esiDataService;
	private LocationCatalogService locationCatalogService;
	private BuildActionPreferencesRepository buildActionPreferencesRepository;
	private ResourceFactory resourceFactory;

	@BeforeEach
	public void beforeEach() {
		this.fittingId = TEST_FITTING_ID;
		this.credentialDetailsService = Mockito.mock( CredentialDetailsService.class );
		this.esiDataService = Mockito.mock( ESIDataService.class );
		this.locationCatalogService = Mockito.mock( LocationCatalogService.class );
		this.buildActionPreferencesRepository = Mockito.mock( BuildActionPreferencesRepository.class );
		this.resourceFactory = Mockito.mock( ResourceFactory.class );
	}

	@BeforeEach
	public void beforeEachCredentialAuthentication() {
		this.neoComAuthenticationProvider = Mockito.mock( NeoComAuthenticationProvider.class );
		final CredentialDetails credentialService = Mockito.mock( CredentialDetails.class );
		final Credential credential = Mockito.mock( Credential.class );
		Mockito.when( this.neoComAuthenticationProvider.getAuthenticatedUniqueId() ).thenReturn( "-UNIQUE-ID-" );
		Mockito.when( this.credentialDetailsService.loadUserByUsername( Mockito.anyString() ) ).thenReturn( credentialService );
		Mockito.when( credentialService.getCredential() ).thenReturn( credential );
		Mockito.when( credential.getAccountId() ).thenReturn( TEST_PILOT_ID );
		Mockito.when( credential.getName() ).thenReturn( TEST_PILOT_NAME );
	}

	@Test
	public void constructorContract() {
		final FittingBuildConfigurationServiceV1 serviceV1 = new FittingBuildConfigurationServiceV1(
				this.neoComAuthenticationProvider,
				this.credentialDetailsService,
				this.esiDataService,
				this.locationCatalogService,
				this.buildActionPreferencesRepository,
				this.resourceFactory
		);
		Assertions.assertNotNull( serviceV1 );
	}

	//	@Test
	public void getFittingBuildConfigurationSavedConfiguration() {
		// Given
		//		final ESIDataService esiDataServiceLocal = Mockito.mock( ESIDataService.class );
		final GetCharactersCharacterIdFittings200Ok fitting = Mockito.mock( GetCharactersCharacterIdFittings200Ok.class );
		final List<GetCharactersCharacterIdFittings200Ok> fittingList = new ArrayList<>();
		fittingList.add( fitting );
		final EsiType esiType = Mockito.mock( EsiType.class );
		final SpaceRegion location = Mockito.mock( SpaceRegion.class );
		final GetCorporationsCorporationIdOk corporationData = Mockito.mock( GetCorporationsCorporationIdOk.class );
		final CredentialDetails credentialDetails = Mockito.mock( CredentialDetails.class );
		final Credential credential = Mockito.mock( Credential.class );
		// When
		Mockito.when( this.esiDataService.getCharactersCharacterIdFittings( Mockito.any( Credential.class ) ) ).thenReturn( fittingList );
		Mockito.when( fitting.getFittingId() ).thenReturn( this.fittingId );
		Mockito.when( this.resourceFactory.generateType4Id( Mockito.anyInt() ) ).thenReturn( esiType );
		Mockito.when( this.locationCatalogService.searchLocation4Id( Mockito.anyLong() ) ).thenReturn( location );
		Mockito.when( this.neoComAuthenticationProvider.getAuthenticatedUniqueId() ).thenReturn( TEST_CREDENTIAL_UNIQUE_ID );
		Mockito.when( credentialDetails.getCredential() ).thenReturn( credential );
		Mockito.when( this.credentialDetailsService.loadUserByUsername( Mockito.anyString() ) ).thenReturn( credentialDetails );
		Mockito.when( credential.getCorporationId() ).thenReturn( TEST_CREDENTIAL_ID );
		Mockito.when( this.esiDataService.getCorporationsCorporationId( Mockito.anyInt() ) ).thenReturn( corporationData );
		// Test
		final FittingBuildConfigurationServiceV1 serviceV1 = new FittingBuildConfigurationServiceV1(
				this.neoComAuthenticationProvider,
				this.credentialDetailsService,
				this.esiDataService,
				this.locationCatalogService,
				this.buildActionPreferencesRepository,
				this.resourceFactory
		);
		final FittingBuildConfiguration obtained = serviceV1.getFittingBuildConfigurationSavedConfiguration( this.fittingId );
		// Assertions
		Assertions.assertNotNull( obtained );
	}

	@Test
	public void getFittingConfigurations() {
		// Test
		final FittingBuildConfigurationServiceV1 serviceV1 = new FittingBuildConfigurationServiceV1(
				this.neoComAuthenticationProvider,
				this.credentialDetailsService,
				this.esiDataService,
				this.locationCatalogService,
				this.buildActionPreferencesRepository,
				this.resourceFactory
		);
		final FittingConfigurations obtained = serviceV1.getFittingConfigurations( this.fittingId );
		final String savedConfiguration = WebMvcLinkBuilder.linkTo(
				WebMvcLinkBuilder.methodOn( FittingBuildConfigurationControllerV1.class )
						.getFittingBuildConfigurationSavedConfiguration( this.fittingId )
		).withRel( "saved" ).getHref();
		final String targetConfiguration = WebMvcLinkBuilder.linkTo(
				WebMvcLinkBuilder.methodOn( FittingBuildConfigurationControllerV1.class )
						.getFittingBuildConfigurationTargetConfiguration( this.fittingId )
		).withRel( "target" ).getHref();
		// Assertions
		Assertions.assertNotNull( obtained );
		Assertions.assertEquals( savedConfiguration, obtained.getSavedBuildData().getHref() );
		Assertions.assertEquals( targetConfiguration, obtained.getTargetBuildData().getHref() );
	}
}
