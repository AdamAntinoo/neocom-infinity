package org.dimensinfin.eveonline.neocom.infinity.backend.universe.loyalty.rest.support;

import javax.validation.constraints.NotNull;

import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import org.dimensinfin.eveonline.neocom.loyalty.persistence.LoyaltyOffersRepository;

/**
 * @author Adam Antinoo (adamantinoo.git@gmail.com)
 * @since 0.20.0
 */
@Profile("acceptance")
@RestController
@Validated
@RequestMapping("/api/v1/public")
public class LayoutControllerSupport {
	private final LoyaltyOffersRepository loyaltyOffersRepository;

	// - C O N S T R U C T O R S
	public LayoutControllerSupport( @NotNull final LoyaltyOffersRepository loyaltyOffersRepository ) {
		this.loyaltyOffersRepository = loyaltyOffersRepository;
	}

	@GetMapping(path = "/support/loyalty/deleteall",
			consumes = "application/json",
			produces = "application/json")
	public ResponseEntity<Integer> deleteAllLoyaltyOffers() {
		return new ResponseEntity<>( this.loyaltyOffersRepository.deleteAll(), HttpStatus.OK );
	}
}