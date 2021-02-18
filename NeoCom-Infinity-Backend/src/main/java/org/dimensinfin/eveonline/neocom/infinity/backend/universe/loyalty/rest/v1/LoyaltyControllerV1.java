package org.dimensinfin.eveonline.neocom.infinity.backend.universe.loyalty.rest.v1;

import java.util.ArrayList;
import java.util.List;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import org.dimensinfin.eveonline.neocom.loyalty.domain.LoyaltyServiceConfiguration;
import org.dimensinfin.eveonline.neocom.loyalty.persistence.LoyaltyOfferEntity;

/**
 * @author Adam Antinoo (adamantinoo.git@gmail.com)
 * @since 0.20.0
 */
@RestController
@Validated
@RequestMapping("/api/v1/public")
public class LoyaltyControllerV1 {
	private final LoyaltyServiceV1 loyaltyServiceV1;

	// - C O N S T R U C T O R S
	@Autowired
	public LoyaltyControllerV1( @NotNull final LoyaltyServiceV1 loyaltyServiceV1 ) {this.loyaltyServiceV1 = loyaltyServiceV1;}

	@GetMapping(path = "/loyalty/corporations/{corporationId}",
			//			consumes = "application/json",
			produces = "application/json")
	public ResponseEntity<List<LoyaltyOfferEntity>> getLoyaltyRecommendedOfferForCorporation(
			@PathVariable @NotNull final Integer corporationId ) {
		List<LoyaltyOfferEntity> offerList = new ArrayList<>();
		offerList = this.loyaltyServiceV1.getLoyaltyRecommendedOfferForCorporation( corporationId );
		return new ResponseEntity<>( offerList, HttpStatus.OK );
	}

	@PatchMapping(path = "/loyalty/configuration",
			consumes = "application/json",
			produces = "application/json")
	public ResponseEntity<LoyaltyServiceConfiguration> setLoyaltyServiceConfiguration(
			@RequestBody @Valid @NotNull final LoyaltyServiceConfiguration serviceConfiguration ) {
		return new ResponseEntity<>( this.loyaltyServiceV1.setLoyaltyServiceConfiguration( serviceConfiguration ), HttpStatus.OK );
	}
}