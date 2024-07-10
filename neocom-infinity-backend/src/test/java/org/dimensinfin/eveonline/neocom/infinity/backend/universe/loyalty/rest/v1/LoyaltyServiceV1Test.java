package org.dimensinfin.eveonline.neocom.infinity.backend.universe.loyalty.rest.v1;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import org.dimensinfin.eveonline.neocom.infinity.core.exception.NeoComRuntimeBackendExceptionObsolete;
import org.dimensinfin.eveonline.neocom.loyalty.persistence.LoyaltyOfferEntity;
import org.dimensinfin.eveonline.neocom.loyalty.persistence.LoyaltyOffersRepository;
import org.dimensinfin.eveonline.neocom.loyalty.service.LoyaltyService;

import static org.dimensinfin.eveonline.neocom.infinity.backend.universe.loyalty.rest.v1.LoyaltyControllerV1Test.TEST_LOYALTY_CORPORATION_ID;

public class LoyaltyServiceV1Test {

	private LoyaltyOffersRepository loyaltyOffersRepository;
	private LoyaltyService loyaltyService;

	@BeforeEach
	public void beforeEach() {
		this.loyaltyOffersRepository = Mockito.mock( LoyaltyOffersRepository.class );
		this.loyaltyService = Mockito.mock( LoyaltyService.class );
	}

	@Test
	public void constructorContract() {
		final LoyaltyServiceV1 serviceV1 = new LoyaltyServiceV1( this.loyaltyOffersRepository, this.loyaltyService );
		Assertions.assertNotNull( serviceV1 );
	}

	@Test
	public void getLoyaltyRecommendedOfferForCorporationException() throws SQLException {
		// Given
		final LoyaltyOfferEntity offer = Mockito.mock( LoyaltyOfferEntity.class );
		final List<LoyaltyOfferEntity> offerList = new ArrayList<>();
		offerList.add( offer );
		// When
		Mockito.when( offer.getLpValue() ).thenReturn( 1100 );
		Mockito.when( this.loyaltyOffersRepository.searchOffers4Corporation( Mockito.anyInt() ) ).thenThrow( new SQLException( "-EXCEPTION-" ) );
		Mockito.when( this.loyaltyOffersRepository.searchOffers4CorporationAndHub( Mockito.anyInt(), Mockito.anyInt() ) )
				.thenThrow( new SQLException( "-EXCEPTION-" ) );
		// Test
		final LoyaltyServiceV1 serviceV1 = new LoyaltyServiceV1( this.loyaltyOffersRepository, this.loyaltyService );
		Assertions.assertThrows( NeoComRuntimeBackendExceptionObsolete.class, () -> {
			serviceV1.getLoyaltyRecommendedOfferForCorporation( TEST_LOYALTY_CORPORATION_ID );
		} );
	}

	@Test
	public void getLoyaltyRecommendedOfferForCorporationOrdered() throws SQLException {
		// Given
		final LoyaltyOfferEntity offerA = Mockito.mock( LoyaltyOfferEntity.class );
		final LoyaltyOfferEntity offerB = Mockito.mock( LoyaltyOfferEntity.class );
		final List<LoyaltyOfferEntity> offerList = new ArrayList<>();
		offerList.add( offerA );
		offerList.add( offerB );
		// When
		Mockito.when( offerA.getOfferId() ).thenReturn( 100 );
		Mockito.when( offerB.getOfferId() ).thenReturn( 200 );
		Mockito.when( offerA.getLpValue() ).thenReturn( 1100 );
		Mockito.when( offerB.getLpValue() ).thenReturn( 2200 );
		Mockito.when( this.loyaltyOffersRepository.searchOffers4Corporation( Mockito.anyInt() ) ).thenReturn( new ArrayList<>() );
		Mockito.when( this.loyaltyService.processOffers( Mockito.anyInt() ) ).thenReturn( offerList );
		// Test
		final LoyaltyServiceV1 serviceV1 = new LoyaltyServiceV1( this.loyaltyOffersRepository, this.loyaltyService );
		final List<LoyaltyOfferEntity> obtained = serviceV1.getLoyaltyRecommendedOfferForCorporation( TEST_LOYALTY_CORPORATION_ID );
		// Assertions
		Assertions.assertNotNull( obtained );
		Assertions.assertFalse( obtained.isEmpty() );
		Assertions.assertEquals( 2, obtained.size() );
		Assertions.assertEquals( 200, obtained.get( 0 ).getOfferId() );
		Assertions.assertEquals( 100, obtained.get( 1 ).getOfferId() );
	}

	@Test
	public void getLoyaltyRecommendedOfferForCorporationReprocess() throws SQLException {
		// Given
		final LoyaltyOfferEntity offer = Mockito.mock( LoyaltyOfferEntity.class );
		final List<LoyaltyOfferEntity> offerList = new ArrayList<>();
		offerList.add( offer );
		// When
		Mockito.when( offer.getLpValue() ).thenReturn( 1100 );
		Mockito.when( this.loyaltyOffersRepository.searchOffers4Corporation( Mockito.anyInt() ) ).thenReturn( new ArrayList<>() );
		Mockito.when( this.loyaltyService.processOffers( Mockito.anyInt() ) ).thenReturn( offerList );
		// Test
		final LoyaltyServiceV1 serviceV1 = new LoyaltyServiceV1( this.loyaltyOffersRepository, this.loyaltyService );
		final List<LoyaltyOfferEntity> obtained = serviceV1.getLoyaltyRecommendedOfferForCorporation( TEST_LOYALTY_CORPORATION_ID );
		// Assertions
		Assertions.assertNotNull( obtained );
		Assertions.assertFalse( obtained.isEmpty() );
		Assertions.assertEquals( 1, obtained.size() );
	}

	@Test
	public void getLoyaltyRecommendedOfferForCorporationSuccess() throws SQLException {
		// Given
		final LoyaltyOfferEntity offer = Mockito.mock( LoyaltyOfferEntity.class );
		final List<LoyaltyOfferEntity> offerList = new ArrayList<>();
		offerList.add( offer );
		// When
		Mockito.when( offer.getLpValue() ).thenReturn( 1100 );
		Mockito.when( this.loyaltyOffersRepository.searchOffers4Corporation( Mockito.anyInt() ) ).thenReturn( offerList );
		Mockito.when( this.loyaltyOffersRepository.searchOffers4CorporationAndHub( Mockito.anyInt(), Mockito.anyInt() ) )
				.thenReturn( offerList );
		// Test
		final LoyaltyServiceV1 serviceV1 = new LoyaltyServiceV1( this.loyaltyOffersRepository, this.loyaltyService );
		final List<LoyaltyOfferEntity> obtained = serviceV1.getLoyaltyRecommendedOfferForCorporation( TEST_LOYALTY_CORPORATION_ID );
		// Assertions
		Assertions.assertNotNull( obtained );
		Assertions.assertFalse( obtained.isEmpty() );
		Assertions.assertEquals( 1, obtained.size() );
	}
}