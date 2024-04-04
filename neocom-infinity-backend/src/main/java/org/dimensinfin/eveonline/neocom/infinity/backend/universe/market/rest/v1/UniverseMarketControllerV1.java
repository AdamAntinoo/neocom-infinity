package org.dimensinfin.eveonline.neocom.infinity.backend.universe.market.rest.v1;

import javax.validation.constraints.NotNull;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import org.dimensinfin.eveonline.neocom.market.MarketData;
import org.dimensinfin.eveonline.neocom.market.service.MarketService;

@RestController
@Validated
@RequestMapping("/api/v1/universe")
public class UniverseMarketControllerV1 {
	private final MarketService marketService;

	// - C O N S T R U C T O R S
	public UniverseMarketControllerV1( final @NotNull MarketService marketService ) {this.marketService = marketService;}

	/**
	 * @param regionId The region unique identifier for the region where to search for the market data.
	 * @param typeId   The type id for the item data to search for market data. For some types there would not be any data so there is not null
	 *                 validations.
	 */
	@GetMapping(path = "/market/consolidated/byregion/{regionId}/{typeId}",
			produces = "application/json")
	public ResponseEntity<MarketData> getMarketConsolidatedByRegion4ItemId(
			final @PathVariable @NotNull Integer regionId,
			final @PathVariable @NotNull Integer typeId ) {
		return new ResponseEntity<>( this.marketService.getMarketConsolidatedByRegion4ItemId( regionId, typeId ), HttpStatus.OK );
	}
}