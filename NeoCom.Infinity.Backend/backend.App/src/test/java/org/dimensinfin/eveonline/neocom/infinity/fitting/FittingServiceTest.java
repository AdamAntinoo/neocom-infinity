package org.dimensinfin.eveonline.neocom.infinity.fitting;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.http.ResponseEntity;

import org.dimensinfin.eveonline.neocom.database.entities.Credential;
import org.dimensinfin.eveonline.neocom.domain.Fitting;
import org.dimensinfin.eveonline.neocom.domain.NeoItem;
import org.dimensinfin.eveonline.neocom.esiswagger.model.GetCharactersCharacterIdFittings200Ok;
import org.dimensinfin.eveonline.neocom.esiswagger.model.GetUniverseCategoriesCategoryIdOk;
import org.dimensinfin.eveonline.neocom.esiswagger.model.GetUniverseGroupsGroupIdOk;
import org.dimensinfin.eveonline.neocom.esiswagger.model.GetUniverseTypesTypeIdOk;
import org.dimensinfin.eveonline.neocom.infinity.adapter.ESIDataProviderWrapper;
import org.dimensinfin.eveonline.neocom.infinity.core.exceptions.NeoComNotFoundException;
import org.dimensinfin.eveonline.neocom.infinity.core.security.CredentialDetails;
import org.dimensinfin.eveonline.neocom.infinity.core.security.CredentialDetailsService;
import org.dimensinfin.eveonline.neocom.infinity.core.security.NeoComAuthenticationProvider;
import org.dimensinfin.eveonline.neocom.provider.ESIDataProvider;
import org.dimensinfin.eveonline.neocom.provider.ESIUniverseDataProvider;

//@RunWith( JUnit )
public class FittingServiceTest {
	private ESIUniverseDataProvider esiUniverseDataProvider;

	@BeforeEach
	public void setUp() {
		final GetUniverseTypesTypeIdOk esiItem = Mockito.mock( GetUniverseTypesTypeIdOk.class );
		final GetUniverseGroupsGroupIdOk group = Mockito.mock( GetUniverseGroupsGroupIdOk.class );
		Mockito.when( group.getGroupId() ).thenReturn( 25 );
		Mockito.when( group.getName() ).thenReturn( "Frigate" );
		final GetUniverseCategoriesCategoryIdOk category = Mockito.mock( GetUniverseCategoriesCategoryIdOk.class );
		Mockito.when( category.getCategoryId() ).thenReturn( 6 );
		Mockito.when( category.getName() ).thenReturn( "Ship" );
		this.esiUniverseDataProvider = Mockito.mock( ESIUniverseDataProvider.class );
		Mockito.when( this.esiUniverseDataProvider.searchEsiItem4Id( Mockito.anyInt() ) )
				.thenReturn( esiItem );
		Mockito.when( this.esiUniverseDataProvider.searchItemGroup4Id( Mockito.anyInt() ) )
				.thenReturn( group );
		Mockito.when( this.esiUniverseDataProvider.searchItemCategory4Id( Mockito.anyInt() ) )
				.thenReturn( category );
		NeoItem.injectEsiUniverseDataAdapter( this.esiUniverseDataProvider );
	}

	@Test
	void getPilotFittingsSuccess() {
		// Given
		final ESIDataProviderWrapper esiDataProviderWrapper = Mockito.mock( ESIDataProviderWrapper.class );
		final ESIDataProvider esiDataProvider = Mockito.mock( ESIDataProvider.class );
		final CredentialDetailsService credentialDetailsService = Mockito.mock( CredentialDetailsService.class );
		final NeoComAuthenticationProvider neoComAuthenticationProvider = Mockito.mock( NeoComAuthenticationProvider.class );
		final Integer authorizedPilotId = 93813310;
		final CredentialDetails credentialDetails = Mockito.mock( CredentialDetails.class );
		final Credential credential = Mockito.mock( Credential.class );
		final GetCharactersCharacterIdFittings200Ok characterFitting = Mockito.mock( GetCharactersCharacterIdFittings200Ok.class );

		// When
		Mockito.when( neoComAuthenticationProvider.getAuthenticatedUniqueId() ).thenReturn( "-TESTING-UNIQUE-ID-" );
		Mockito.when( credentialDetailsService.loadUserByUsername( Mockito.anyString() ) ).thenReturn( credentialDetails );
		Mockito.when( credentialDetails.getCredential() ).thenReturn( credential );
		Mockito.when( esiDataProviderWrapper.getSingleton() ).thenReturn( esiDataProvider );
		final List<GetCharactersCharacterIdFittings200Ok> fittingList = new ArrayList<GetCharactersCharacterIdFittings200Ok>();
		fittingList.add( characterFitting );
		Mockito.when( esiDataProvider.getCharactersCharacterIdFittings( credential ) )
				.thenReturn( fittingList );
		Mockito.when( characterFitting.getShipTypeId() ).thenReturn( 32880 );
		Mockito.when( characterFitting.getItems() ).thenReturn( new ArrayList<>() );

		// Test
		final FittingService fittingService = new FittingService( esiDataProviderWrapper, credentialDetailsService, neoComAuthenticationProvider );
		final ResponseEntity<List<Fitting>> obtained = fittingService.getPilotFittings();

		// Asserts
		Assertions.assertNotNull( obtained );
	}

	@Test
	void getPilotFittingsNeoComNotFoundException() {
		// Given
		final ESIDataProviderWrapper esiDataProviderWrapper = Mockito.mock( ESIDataProviderWrapper.class );
		final ESIDataProvider esiDataProvider = Mockito.mock( ESIDataProvider.class );
		final CredentialDetailsService credentialDetailsService = Mockito.mock( CredentialDetailsService.class );
		final NeoComAuthenticationProvider neoComAuthenticationProvider = Mockito.mock( NeoComAuthenticationProvider.class );
//		final Integer authorizedPilotId = 93813310;
		final CredentialDetails credentialDetails = Mockito.mock( CredentialDetails.class );
		final Credential credential = Mockito.mock( Credential.class );
		final GetCharactersCharacterIdFittings200Ok characterFitting = Mockito.mock( GetCharactersCharacterIdFittings200Ok.class );

		// When
		Mockito.when( neoComAuthenticationProvider.getAuthenticatedUniqueId() ).thenReturn( "-TESTING-UNIQUE-ID-" );
		Mockito.when( credentialDetailsService.loadUserByUsername( Mockito.anyString() ) ).thenReturn( credentialDetails );
		Mockito.when( credentialDetails.getCredential() ).thenReturn( credential );
		Mockito.when( esiDataProviderWrapper.getSingleton() ).thenReturn( esiDataProvider );
		Mockito.when( esiDataProvider.getCharactersCharacterIdFittings( credential ) )
				.thenReturn( null );
		Mockito.when( characterFitting.getShipTypeId() ).thenReturn( 32880 );
		Mockito.when( characterFitting.getItems() ).thenReturn( new ArrayList<>() );

		// Test
		final FittingService fittingService = new FittingService( esiDataProviderWrapper, credentialDetailsService, neoComAuthenticationProvider );
		NeoComNotFoundException thrown = Assertions.assertThrows( NeoComNotFoundException.class,
				() -> fittingService.getPilotFittings(),
				"Expected fittingService.getPilotFittings() to throw null verification, but it didn't." );
	}
}
