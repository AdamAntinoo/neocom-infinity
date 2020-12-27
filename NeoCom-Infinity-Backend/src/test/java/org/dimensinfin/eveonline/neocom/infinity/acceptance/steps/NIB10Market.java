package org.dimensinfin.eveonline.neocom.infinity.acceptance.steps;

import java.util.List;
import java.util.Map;
import java.util.Objects;

import org.junit.Assert;
import org.junit.jupiter.api.Assertions;

import org.dimensinfin.eveonline.neocom.infinity.acceptance.support.core.StationValidator;
import org.dimensinfin.eveonline.neocom.infinity.acceptance.support.market.MarketDataValidator;
import org.dimensinfin.eveonline.neocom.infinity.acceptance.support.market.MarketOrderValidator;
import org.dimensinfin.eveonline.neocom.infinity.backend.market.domain.MarketData;
import org.dimensinfin.eveonline.neocom.infinity.support.ConverterContainer;
import org.dimensinfin.eveonline.neocom.infinity.support.NeoComWorld;

import io.cucumber.java.en.Then;

public class NIB10Market extends SupportSteps {
	// - C O N S T R U C T O R S
	public NIB10Market( final ConverterContainer cucumberTableToRequestConverters,
	                    final NeoComWorld neocomWorld ) {
		super( cucumberTableToRequestConverters, neocomWorld );
	}

	@Then("the resulting Market Data Best Sell Order Station matches a Station with data")
	public void the_resulting_Market_Data_Best_Sell_Order_Station_matches_a_Station_with_data( final List<Map<String, String>> dataTable ) {
		Objects.requireNonNull( this.neocomWorld.getMarketDataResponseEntity() );
		Objects.requireNonNull( this.neocomWorld.getMarketDataResponseEntity().getBody() );
		final MarketData marketData = this.neocomWorld.getMarketDataResponseEntity().getBody();
		Assert.assertTrue( new StationValidator().validate( dataTable.get( 0 ), marketData.getBestSellOrder().getStation() ) );
	}

	@Then("the resulting Market Data Best Sell Order contains")
	public void the_resulting_Market_Data_Best_Sell_Order_contains( final List<Map<String, String>> dataTable ) {
		Objects.requireNonNull( this.neocomWorld.getMarketDataResponseEntity() );
		Objects.requireNonNull( this.neocomWorld.getMarketDataResponseEntity().getBody() );
		final MarketData marketData = this.neocomWorld.getMarketDataResponseEntity().getBody();
		Assert.assertTrue( new MarketOrderValidator().validate( dataTable.get( 0 ), marketData.getBestSellOrder() ) );
	}

	@Then("the resulting Market Data contents")
	public void the_resulting_Market_Data_contents( final List<Map<String, String>> dataTable ) {
		Objects.requireNonNull( this.neocomWorld.getMarketDataResponseEntity() );
		Objects.requireNonNull( this.neocomWorld.getMarketDataResponseEntity().getBody() );
		final MarketData marketData = this.neocomWorld.getMarketDataResponseEntity().getBody();
		Assert.assertTrue( new MarketDataValidator().validate( dataTable.get( 0 ), marketData ) );
	}

	@Then("the resulting Market Data has a Best Buy Order")
	public void the_resulting_Market_Data_has_a_Best_Buy_Order() {
		Objects.requireNonNull( this.neocomWorld.getMarketDataResponseEntity() );
		Objects.requireNonNull( this.neocomWorld.getMarketDataResponseEntity().getBody() );
		final MarketData marketData = this.neocomWorld.getMarketDataResponseEntity().getBody();
		Assertions.assertNotNull( marketData.getBestBuyOrder() );
	}

	@Then("the resulting Market Data has a Best Sell Order")
	public void the_resulting_Market_Data_has_a_Best_Sell_Order() {
		Objects.requireNonNull( this.neocomWorld.getMarketDataResponseEntity() );
		Objects.requireNonNull( this.neocomWorld.getMarketDataResponseEntity().getBody() );
		final MarketData marketData = this.neocomWorld.getMarketDataResponseEntity().getBody();
		Assertions.assertNotNull( marketData.getBestSellOrder() );
	}

	@Then("the resulting Market Data has {int} entries on the Sell Orders list")
	public void the_resulting_Market_Data_has_entries_on_the_Sell_Orders_list( final Integer entries ) {
		Objects.requireNonNull( this.neocomWorld.getMarketDataResponseEntity() );
		Objects.requireNonNull( this.neocomWorld.getMarketDataResponseEntity().getBody() );
		final MarketData marketData = this.neocomWorld.getMarketDataResponseEntity().getBody();
		Assertions.assertEquals( entries, marketData.getSellOrders().size() );
	}

	@Then("the resulting Market Data has not a Best Buy Order")
	public void the_resulting_Market_Data_has_not_a_Best_Buy_Order() {
		Objects.requireNonNull( this.neocomWorld.getMarketDataResponseEntity() );
		Objects.requireNonNull( this.neocomWorld.getMarketDataResponseEntity().getBody() );
		final MarketData marketData = this.neocomWorld.getMarketDataResponseEntity().getBody();
		Assertions.assertNull( marketData.getBestSellOrder() );
	}
}