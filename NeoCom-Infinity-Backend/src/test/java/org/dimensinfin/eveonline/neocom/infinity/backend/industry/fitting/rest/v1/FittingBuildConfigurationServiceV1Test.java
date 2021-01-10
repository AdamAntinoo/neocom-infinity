package org.dimensinfin.eveonline.neocom.infinity.backend.industry.fitting.rest.v1;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;

import org.dimensinfin.eveonline.neocom.adapter.LocationCatalogService;
import org.dimensinfin.eveonline.neocom.database.entities.Credential;
import org.dimensinfin.eveonline.neocom.esiswagger.model.GetCharactersCharacterIdFittings200Ok;
import org.dimensinfin.eveonline.neocom.infinity.adapter.ESIDataProviderWrapper;
import org.dimensinfin.eveonline.neocom.infinity.adapter.LocationCatalogServiceWrapper;
import org.dimensinfin.eveonline.neocom.infinity.backend.industry.domain.ItemFactory;
import org.dimensinfin.eveonline.neocom.infinity.backend.industry.fitting.domain.FittingBuildConfiguration;
import org.dimensinfin.eveonline.neocom.infinity.backend.industry.fitting.domain.FittingConfigurations;
import org.dimensinfin.eveonline.neocom.infinity.backend.industry.fitting.persistence.BuildActionPreferencesRepository;
import org.dimensinfin.eveonline.neocom.infinity.backend.universe.item.rest.v2.EsiItemServiceV2;
import org.dimensinfin.eveonline.neocom.infinity.core.security.CredentialDetails;
import org.dimensinfin.eveonline.neocom.infinity.core.security.CredentialDetailsService;
import org.dimensinfin.eveonline.neocom.infinity.core.security.NeoComAuthenticationProvider;
import org.dimensinfin.eveonline.neocom.provider.ESIDataProvider;

import static org.dimensinfin.eveonline.neocom.infinity.backend.support.TestDataConstants.FittingConstants.TEST_FITTING_ID;
import static org.dimensinfin.eveonline.neocom.infinity.backend.support.TestDataConstants.PilotConstants.TEST_PILOT_ID;
import static org.dimensinfin.eveonline.neocom.infinity.backend.support.TestDataConstants.PilotConstants.TEST_PILOT_NAME;

public class FittingBuildConfigurationServiceV1Test {

	private Integer fittingId;
	private NeoComAuthenticationProvider neoComAuthenticationProvider;
	private CredentialDetailsService credentialDetailsService;
	private ESIDataProviderWrapper esiDataProviderWrapper;
	private LocationCatalogServiceWrapper locationCatalogServiceWrapper;
	private BuildActionPreferencesRepository buildActionPreferencesRepository;
	private EsiItemServiceV2 esiItemServiceV2;
	private ItemFactory itemFactory;
	private ESIDataProvider esiDataProvider;
	private LocationCatalogService locationCatalogService;

	@BeforeEach
	public void beforeEach() {
		this.fittingId = TEST_FITTING_ID;
		this.credentialDetailsService = Mockito.mock( CredentialDetailsService.class );
		this.esiDataProviderWrapper = Mockito.mock( ESIDataProviderWrapper.class );
		this.locationCatalogServiceWrapper = Mockito.mock( LocationCatalogServiceWrapper.class );
		this.buildActionPreferencesRepository = Mockito.mock( BuildActionPreferencesRepository.class );
		this.esiItemServiceV2 = Mockito.mock( EsiItemServiceV2.class );
		this.itemFactory = Mockito.mock( ItemFactory.class );
		this.esiDataProvider = Mockito.mock( ESIDataProvider.class );
		this.locationCatalogService = Mockito.mock( LocationCatalogService.class );
		Mockito.when( this.esiDataProviderWrapper.getSingleton() ).thenReturn( this.esiDataProvider );
		Mockito.when( this.locationCatalogServiceWrapper.getSingleton() ).thenReturn( this.locationCatalogService );
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
		Mockito.when( credential.getName() ).thenReturn( TEST_PILOT_NAME);
	}
	@Test
	public void constructorContract() {
		final FittingBuildConfigurationServiceV1 serviceV1 = new FittingBuildConfigurationServiceV1(
				this.neoComAuthenticationProvider,
				this.credentialDetailsService,
				this.esiDataProviderWrapper,
				this.locationCatalogServiceWrapper,
				this.buildActionPreferencesRepository,
				this.esiItemServiceV2,
				this.itemFactory
		);
		Assertions.assertNotNull( serviceV1 );
	}

	@Test
	public void getFittingBuildConfigurationSavedConfiguration() {
		// Given
		final List<GetCharactersCharacterIdFittings200Ok> fittingList= new ArrayList<>();
		// When
		Mockito.when( this.esiDataProvider.getCharactersCharacterIdFittings(Mockito.any(Credential.class)) ).thenReturn( fittingList );
		// Test
		final FittingBuildConfigurationServiceV1 serviceV1 = new FittingBuildConfigurationServiceV1(
				this.neoComAuthenticationProvider,
				this.credentialDetailsService,
				this.esiDataProviderWrapper,
				this.locationCatalogServiceWrapper,
				this.buildActionPreferencesRepository,
				this.esiItemServiceV2,
				this.itemFactory
		);
		final FittingBuildConfiguration obtained = serviceV1.getFittingBuildConfigurationSavedConfiguration( fittingId );
		// Assertions
		Assertions.assertNotNull( obtained );
	}

	@Test
	public void getFittingConfigurations() {
		// Test
		final FittingBuildConfigurationServiceV1 serviceV1 = new FittingBuildConfigurationServiceV1(
				this.neoComAuthenticationProvider,
				this.credentialDetailsService,
				this.esiDataProviderWrapper,
				this.locationCatalogServiceWrapper,
				this.buildActionPreferencesRepository,
				this.esiItemServiceV2,
				this.itemFactory
		);
		final FittingConfigurations obtained = serviceV1.getFittingConfigurations( fittingId );
		final String savedConfiguration = WebMvcLinkBuilder.linkTo(
				WebMvcLinkBuilder.methodOn( FittingBuildConfigurationControllerV1.class )
						.getFittingBuildConfigurationSavedConfiguration( fittingId )
		).withRel( "saved" ).getHref();
		final String targetConfiguration = WebMvcLinkBuilder.linkTo(
				WebMvcLinkBuilder.methodOn( FittingBuildConfigurationControllerV1.class )
						.getFittingBuildConfigurationTargetConfiguration( fittingId )
		).withRel( "target" ).getHref();
		// Assertions
		Assertions.assertNotNull( obtained );
		Assertions.assertEquals( savedConfiguration, obtained.getSavedBuildData().getHref() );
		Assertions.assertEquals( targetConfiguration, obtained.getTargetBuildData().getHref() );
	}
}
