package org.dimensinfin.eveonline.neocom.infinity.backend.character.fitting.rest.v2;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import org.dimensinfin.eveonline.neocom.database.entities.Credential;
import org.dimensinfin.eveonline.neocom.domain.EsiType;
import org.dimensinfin.eveonline.neocom.esiswagger.model.GetCharactersCharacterIdFittings200Ok;
import org.dimensinfin.eveonline.neocom.infinity.backend.character.fitting.domain.FittingModel;
import org.dimensinfin.eveonline.neocom.infinity.config.security.CredentialDetails;
import org.dimensinfin.eveonline.neocom.infinity.config.security.CredentialDetailsService;
import org.dimensinfin.eveonline.neocom.infinity.config.security.NeoComAuthenticationProvider;
import org.dimensinfin.eveonline.neocom.service.ESIDataService;
import org.dimensinfin.eveonline.neocom.service.ResourceFactory;

import static org.dimensinfin.eveonline.neocom.infinity.backend.support.TestDataConstants.FittingConstants.TEST_FITTING_DESCRIPTION;
import static org.dimensinfin.eveonline.neocom.infinity.backend.support.TestDataConstants.FittingConstants.TEST_FITTING_ID;
import static org.dimensinfin.eveonline.neocom.infinity.backend.support.TestDataConstants.FittingConstants.TEST_FITTING_NAME;
import static org.dimensinfin.eveonline.neocom.infinity.backend.support.TestDataConstants.FittingConstants.TEST_FITTING_SHIP_TYPE_ID;

public class PilotFittingsServiceV2Test {
	private NeoComAuthenticationProvider neoComAuthenticationProvider;
	private CredentialDetailsService credentialDetailsService;
	private ESIDataService esiDataService;
	private ResourceFactory resourceFactory;

	@BeforeEach
	public void beforeEach() {
		this.esiDataService = Mockito.mock( ESIDataService.class );
		this.neoComAuthenticationProvider = Mockito.mock( NeoComAuthenticationProvider.class );
		this.credentialDetailsService = Mockito.mock( CredentialDetailsService.class );
		this.resourceFactory = Mockito.mock( ResourceFactory.class );
	}

	@Test
	public void constructorContract() {
		// Test
		final PilotFittingsServiceV2 pilotFittingsServiceV2 = new PilotFittingsServiceV2(
				this.neoComAuthenticationProvider, this.credentialDetailsService, this.esiDataService, this.resourceFactory
		);
		// Assertions
		Assertions.assertNotNull( pilotFittingsServiceV2 );
	}

	@Test
	public void getPilotFittings() {
		// Given
		//		final ESIDataProvider esiDataProvider = Mockito.mock( ESIDataProvider.class );
		final List<GetCharactersCharacterIdFittings200Ok> esiFittingList = new ArrayList();
		final GetCharactersCharacterIdFittings200Ok esiFitting = new GetCharactersCharacterIdFittings200Ok();
		esiFitting.setFittingId( TEST_FITTING_ID );
		esiFitting.setName( TEST_FITTING_NAME );
		esiFitting.setDescription( TEST_FITTING_DESCRIPTION );
		esiFitting.setShipTypeId( TEST_FITTING_SHIP_TYPE_ID );
		esiFitting.setItems( new ArrayList<>() );
		esiFittingList.add( esiFitting );
		final CredentialDetails credentialService = Mockito.mock( CredentialDetails.class );
		final Credential credential = Mockito.mock( Credential.class );
		final EsiType shipHull = Mockito.mock( EsiType.class );
		// When
		//		Mockito.when( this.esiDataService.getSingleton() ).thenReturn( esiDataProvider );
		Mockito.when( this.esiDataService.getCharactersCharacterIdFittings( Mockito.any( Credential.class ) ) ).thenReturn( esiFittingList );
		Mockito.when( this.neoComAuthenticationProvider.getAuthenticatedUniqueId() ).thenReturn( "-UNIQUE-ID-" );
		Mockito.when( this.credentialDetailsService.loadUserByUsername( Mockito.anyString() ) ).thenReturn( credentialService );
		Mockito.when( credentialService.getCredential() ).thenReturn( credential );
		Mockito.when( this.resourceFactory.generateType4Id( Mockito.anyInt() ) ).thenReturn( shipHull );
		// Test
		final PilotFittingsServiceV2 pilotFittingsServiceV2 = new PilotFittingsServiceV2(
				this.neoComAuthenticationProvider, this.credentialDetailsService, this.esiDataService, this.resourceFactory
		);
		final List<FittingModel> obtained = pilotFittingsServiceV2.getPilotFittings();
		// Assertions
		Assertions.assertNotNull( obtained );
		Assertions.assertEquals( 1, obtained.size() );
		Assertions.assertEquals( TEST_FITTING_NAME, obtained.get( 0 ).getName() );
	}
}
