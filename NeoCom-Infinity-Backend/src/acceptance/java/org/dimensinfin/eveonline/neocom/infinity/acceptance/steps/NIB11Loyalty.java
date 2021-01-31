package org.dimensinfin.eveonline.neocom.infinity.acceptance.steps;

import java.util.List;
import java.util.Map;

import org.junit.Assert;
import org.junit.jupiter.api.Assertions;

import org.dimensinfin.eveonline.neocom.infinity.acceptance.support.universe.rest.LoyaltyOfferValidator;
import org.dimensinfin.eveonline.neocom.infinity.support.ConverterContainer;
import org.dimensinfin.eveonline.neocom.infinity.support.NeoComWorld;
import org.dimensinfin.eveonline.neocom.loyalty.persistence.LoyaltyOfferEntity;

import io.cucumber.java.en.Then;

/**
 * @author Adam Antinoo (adamantinoo.git@gmail.com)
 * @since 0.20.0
 */
public class NIB11Loyalty extends SupportSteps {
	// - C O N S T R U C T O R S
	public NIB11Loyalty( final ConverterContainer cucumberTableToRequestConverters, final NeoComWorld neocomWorld ) {
		super( cucumberTableToRequestConverters, neocomWorld );
	}

	@Then("the LoyaltyOffers repository has {int} records")
	public void the_LoyaltyOffers_repository_has_records( final Integer recordCount ) {
		Assertions.assertNotNull( this.neocomWorld.getLoyaltyOffersResponseEntity() );
		Assertions.assertNotNull( this.neocomWorld.getLoyaltyOffersResponseEntity().getBody() );
		Assertions.assertEquals( recordCount, this.neocomWorld.getLoyaltyOffersResponseEntity().getBody().size() );
	}

	@Then("the resulting Loyalty Offer has the next contents")
	public void the_resulting_Loyalty_Offer_has_the_next_contents( final List<Map<String, String>> dataTable ) {
		Assertions.assertNotNull( this.neocomWorld.getLoyaltyOffersResponseEntity() );
		Assertions.assertNotNull( this.neocomWorld.getLoyaltyOffersResponseEntity().getBody() );
		final List<LoyaltyOfferEntity> loyaltyOffers = this.neocomWorld.getLoyaltyOffersResponseEntity().getBody();
		for (int i = 0; i < dataTable.size(); i++) {
			final Map<String, String> row = dataTable.get( i );
			final LoyaltyOfferEntity loyaltyOffer = loyaltyOffers.get( i );
			Assert.assertTrue( new LoyaltyOfferValidator().validate( row, loyaltyOffer ) );
		}
	}
}