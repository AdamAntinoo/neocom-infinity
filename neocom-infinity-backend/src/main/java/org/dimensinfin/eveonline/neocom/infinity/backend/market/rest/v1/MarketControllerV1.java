package org.dimensinfin.eveonline.neocom.infinity.backend.market.rest.v1;

import javax.validation.constraints.NotNull;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import org.dimensinfin.eveonline.neocom.market.MarketData;

@RestController
@Validated
@RequestMapping("/api/v1/neocom/market")
public class MarketControllerV1 {
	private final MarketServiceV1 marketServiceV1;

	// - C O N S T R U C T O R S
	public MarketControllerV1( final @NotNull MarketServiceV1 marketServiceV1 ) {this.marketServiceV1 = marketServiceV1;}

	/**
	 * This endpoint reads the Pilot configuration to identify the target market station where to read the market information. Then using that
	 * market hub reads and consolidates the market data into an instance that contains:
	 * <ul>
	 *     <li>the market sell best order</li>
	 *     <li>the market sell deep (the aggregated volume on sell)</li>
	 *     <li>sell orders on the lowest and 5% margin with their order volume</li>
	 *     <li>the sell average price</li>
	 *     <li>the best buy price and its volume</li>
	 * </ul>
	 *
	 * @param itemId the type identifier for the item to search on the market for order and price data.
	 * @return an instance of <code>MarketData</code> with all the required market consolidated information.
	 */
	@GetMapping(path = "/consolidated/{itemId}",
			consumes = "application/json",
			produces = "application/json")
	public ResponseEntity<MarketData> getMarketConsolidated4ItemId( final @PathVariable @NotNull Integer itemId ) {
		return new ResponseEntity<>( this.marketServiceV1.getMarketConsolidated4ItemId( itemId ), HttpStatus.OK );
	}
}