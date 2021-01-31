package org.dimensinfin.eveonline.neocom.infinity.acceptance.steps;

import java.io.IOException;
import javax.validation.constraints.NotNull;

import org.junit.Assert;

import org.dimensinfin.eveonline.neocom.infinity.acceptance.support.api.NeoComSupportFeignClient;
import org.dimensinfin.eveonline.neocom.infinity.support.NeoComWorld;

import io.cucumber.java.en.Given;

/**
 * @author Adam Antinoo (adamantinoo.git@gmail.com)
 * @since 0.20.0
 */
public class GivenACleanLoyaltyOffersRepository extends StepSupport {
	private final NeoComSupportFeignClient neoComSupportFeignClient;

	// - C O N S T R U C T O R S
	public GivenACleanLoyaltyOffersRepository( @NotNull final NeoComWorld neoComWorld,
	                                           @NotNull final NeoComSupportFeignClient neoComSupportFeignClient ) {
		super( neoComWorld );
		this.neoComSupportFeignClient = neoComSupportFeignClient;
	}

	@Given("a clean LoyaltyOffers repository")
	public void a_clean_LoyaltyOffers_repository() throws IOException {
		// Send the support message to clean the list of credentials at the repository.
		final Integer obtained = this.neoComSupportFeignClient.deleteAllLoyaltyOffers();
		Assert.assertNotNull( obtained );
	}

}