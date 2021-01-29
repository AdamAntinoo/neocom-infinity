package org.dimensinfin.eveonline.neocom.infinity.backend.universe.loyalty.rest.v1;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.http.ResponseEntity;

import org.dimensinfin.eveonline.neocom.loyalty.persistence.LoyaltyOfferEntity;

public class LoyaltyControllerV1Test {
	public static final int TEST_LOYALTY_CORPORATION_ID = 1000179;
	private LoyaltyServiceV1 loyaltyServiceV1;

	@BeforeEach
	public void beforeEach() {
		this.loyaltyServiceV1 = Mockito.mock( LoyaltyServiceV1.class );
	}

	@Test
	public void constructorContract() {
		final LoyaltyControllerV1 controllerV1 = new LoyaltyControllerV1( this.loyaltyServiceV1 );
		Assertions.assertNotNull( controllerV1 );
	}

	@Test
	public void getLoyaltyRecommendedOfferForCorporationSuccess() {
		// Given
		final LoyaltyOfferEntity offer = Mockito.mock( LoyaltyOfferEntity.class );
		final List<LoyaltyOfferEntity> offerList = new ArrayList<>();
		offerList.add( offer );
		// When
		Mockito.when( this.loyaltyServiceV1.getLoyaltyRecommendedOfferForCorporation( Mockito.anyInt() ) ).thenReturn( offerList );
		// Test
		final LoyaltyControllerV1 controllerV1 = new LoyaltyControllerV1( this.loyaltyServiceV1 );
		final ResponseEntity<List<LoyaltyOfferEntity>> obtained = controllerV1
				.getLoyaltyRecommendedOfferForCorporation( TEST_LOYALTY_CORPORATION_ID );
		// Assertions
		Assertions.assertNotNull( obtained );
		Assertions.assertNotNull( obtained.getBody() );
		Assertions.assertFalse( obtained.getBody().isEmpty() );
	}
}