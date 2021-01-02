package org.dimensinfin.eveonline.neocom.infinity.mining.rest;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import org.dimensinfin.eveonline.neocom.database.entities.Credential;
import org.dimensinfin.eveonline.neocom.database.entities.MiningExtractionEntity;
import org.dimensinfin.eveonline.neocom.database.repositories.MiningRepository;
import org.dimensinfin.eveonline.neocom.domain.NeoItem;
import org.dimensinfin.eveonline.neocom.domain.space.SpaceSystem;
import org.dimensinfin.eveonline.neocom.infinity.adapter.MiningRepositoryWrapper;
import org.dimensinfin.eveonline.neocom.infinity.core.security.CredentialDetails;
import org.dimensinfin.eveonline.neocom.infinity.core.security.CredentialDetailsService;
import org.dimensinfin.eveonline.neocom.infinity.core.security.NeoComAuthenticationProvider;
import org.dimensinfin.eveonline.neocom.infinity.mining.rest.v1.MiningExtractionsService;
import org.dimensinfin.eveonline.neocom.miningextraction.domain.MiningExtraction;
import org.dimensinfin.eveonline.neocom.service.ResourceFactory;

public class MiningExtractionsServiceTest {

	private NeoComAuthenticationProvider authenticationProvider;
	private CredentialDetailsService credentialDetailsService;
	private MiningRepositoryWrapper miningRepositoryWrapper;
	private MiningRepository miningRepository;
	private ResourceFactory resourceFactory;

	@BeforeEach
	public void beforeEach() {
		this.authenticationProvider = Mockito.mock( NeoComAuthenticationProvider.class );
		this.credentialDetailsService = Mockito.mock( CredentialDetailsService.class );
		this.miningRepositoryWrapper = Mockito.mock( MiningRepositoryWrapper.class );
		this.miningRepository = Mockito.mock( MiningRepository.class );
		this.resourceFactory = Mockito.mock(ResourceFactory.class);
	}

	@Test
	public void constructionContract() {
		final MiningExtractionsService miningExtractionsService = new MiningExtractionsService(
				this.authenticationProvider,
				this.credentialDetailsService,
				this.miningRepositoryWrapper,
				this.resourceFactory );
		Assertions.assertNotNull( miningExtractionsService );
	}

	@Test
	public void getTodayMiningExtractions4Pilot() {
		// Given
		final CredentialDetails credentialDetails = Mockito.mock( CredentialDetails.class );
		final Credential credential = Mockito.mock( Credential.class );
		final MiningRepository miningRepositoryLocal = Mockito.mock( MiningRepository.class );
		final NeoItem neoItem = Mockito.mock( NeoItem.class );
		final SpaceSystem spaceSystem = Mockito.mock( SpaceSystem.class );
		final List<MiningExtraction> miningExtractions = new ArrayList<>();
		miningExtractions.add( new MiningExtraction.Builder()
				.withOwnerId( 34565432 )
				.withQuantity( 1000L )
				.withExtractionDate( "2020-03-16" )
				.withExtractionHour( 15 )
				.withNeoItem( neoItem )
				.withSpaceSystem( spaceSystem )
				.build() );
		miningExtractions.add( new MiningExtraction.Builder()
				.withOwnerId( 34565432 )
				.withQuantity( 1000L )
				.withExtractionDate( "2020-03-16" )
				.withExtractionHour( 16 )
				.withNeoItem( neoItem )
				.withSpaceSystem( spaceSystem )
				.build() );
		miningExtractions.add( new MiningExtraction.Builder()
				.withOwnerId( 34565432 )
				.withQuantity( 1500L )
				.withExtractionDate( "2020-03-16" )
				.withExtractionHour( 17 )
				.withNeoItem( neoItem )
				.withSpaceSystem( spaceSystem )
				.build() );
		miningExtractions.add( new MiningExtraction.Builder()
				.withOwnerId( 34565432 )
				.withQuantity( 2000L )
				.withExtractionDate( "2020-03-16" )
				.withExtractionHour( 18 )
				.withNeoItem( neoItem )
				.withSpaceSystem( spaceSystem )
				.build() );
		miningExtractions.add( new MiningExtraction.Builder()
				.withOwnerId( 34565432 )
				.withQuantity( 2000L )
				.withExtractionDate( "2020-03-16" )
				.withExtractionHour( 19 )
				.withNeoItem( neoItem )
				.withSpaceSystem( spaceSystem )
				.build() );
		// When
		Mockito.when( this.authenticationProvider.getAuthenticatedUniqueId() ).thenReturn( "-CREDENTIAL-UNIQUEID-" );
		Mockito.when( this.credentialDetailsService.loadUserByUsername( "-CREDENTIAL-UNIQUEID-" ) ).thenReturn( credentialDetails );
		Mockito.when( credentialDetails.getCredential() ).thenReturn( credential );
		Mockito.when( this.miningRepositoryWrapper.getSingleton() ).thenReturn( miningRepositoryLocal );
		Mockito.when( miningRepositoryLocal.accessTodayMiningExtractions4Pilot( credential ) ).thenReturn( miningExtractions );
		Mockito.when( neoItem.getTypeId() ).thenReturn( 345 );
		// Test
		final MiningExtractionsService miningExtractionsService = new MiningExtractionsService(
				this.authenticationProvider,
				this.credentialDetailsService,
				this.miningRepositoryWrapper,
				this.resourceFactory );
		final List<MiningExtractionEntity> obtained = miningExtractionsService.getTodayMiningExtractions4Pilot();
		// Assertions
		Assertions.assertNotNull( obtained );
		Assertions.assertEquals( 3, obtained.size() );
		Assertions.assertEquals( 15, obtained.get( 0 ).getExtractionHour() );
		Assertions.assertEquals( 1000, obtained.get( 0 ).getQuantity() );
		Assertions.assertEquals( 17, obtained.get( 1 ).getExtractionHour() );
		Assertions.assertEquals( 500, obtained.get( 1 ).getQuantity() );
		Assertions.assertEquals( 18, obtained.get( 2 ).getExtractionHour() );
		Assertions.assertEquals( 500, obtained.get( 2 ).getQuantity() );
	}
}
