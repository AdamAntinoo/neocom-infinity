package org.dimensinfin.eveonline.neocom.industry.domain;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import org.dimensinfin.eveonline.neocom.domain.EsiType;
import org.dimensinfin.eveonline.neocom.domain.space.Station;

import static org.dimensinfin.eveonline.neocom.infinity.backend.support.TestDataConstants.ProcessedBlueprintSummaryConstants.TEST_PROCESSED_BLUEPRINT_BLUEPRINT_TYPE_ICON_URL;
import static org.dimensinfin.eveonline.neocom.infinity.backend.support.TestDataConstants.ProcessedBlueprintSummaryConstants.TEST_PROCESSED_BLUEPRINT_BLUEPRINT_TYPE_ID;
import static org.dimensinfin.eveonline.neocom.infinity.backend.support.TestDataConstants.ProcessedBlueprintSummaryConstants.TEST_PROCESSED_BLUEPRINT_BLUEPRINT_TYPE_NAME;
import static org.dimensinfin.eveonline.neocom.infinity.backend.support.TestDataConstants.ProcessedBlueprintSummaryConstants.TEST_PROCESSED_BLUEPRINT_COST_INDEX;
import static org.dimensinfin.eveonline.neocom.infinity.backend.support.TestDataConstants.ProcessedBlueprintSummaryConstants.TEST_PROCESSED_BLUEPRINT_MANUFACTURE_COST;
import static org.dimensinfin.eveonline.neocom.infinity.backend.support.TestDataConstants.ProcessedBlueprintSummaryConstants.TEST_PROCESSED_BLUEPRINT_OUTPUT_PRICE;
import static org.dimensinfin.eveonline.neocom.infinity.backend.support.TestDataConstants.ProcessedBlueprintSummaryConstants.TEST_PROCESSED_BLUEPRINT_OUTPUT_TYPE_ICON_URL;
import static org.dimensinfin.eveonline.neocom.infinity.backend.support.TestDataConstants.ProcessedBlueprintSummaryConstants.TEST_PROCESSED_BLUEPRINT_OUTPUT_TYPE_ID;
import static org.dimensinfin.eveonline.neocom.infinity.backend.support.TestDataConstants.ProcessedBlueprintSummaryConstants.TEST_PROCESSED_BLUEPRINT_OUTPUT_TYPE_NAME;
import static org.dimensinfin.eveonline.neocom.infinity.backend.support.TestDataConstants.ProcessedBlueprintSummaryConstants.TEST_PROCESSED_BLUEPRINT_UID;

public class ProcessedBlueprintSummaryTest {

	private EsiType blueprint;
	private PricedResource output;
	private Station station;

	@BeforeEach
	public void beforeEach() {
		this.blueprint = Mockito.mock( EsiType.class );
		this.output = Mockito.mock( PricedResource.class );
		this.station = Mockito.mock( Station.class );
	}

	@Test
	public void buildContract() {
		final ProcessedBlueprintSummary blueprintSummary = new ProcessedBlueprintSummary.Builder()
				.withUID( TEST_PROCESSED_BLUEPRINT_UID )
				.withBlueprint( this.blueprint )
				.withOutput( this.output )
				.withTradeStation( this.station )
				.withManufactureCost( TEST_PROCESSED_BLUEPRINT_MANUFACTURE_COST )
				.withCostIndex( TEST_PROCESSED_BLUEPRINT_COST_INDEX )
				.build();
		Assertions.assertNotNull( blueprintSummary );
	}

	@Test
	public void buildFailureMissingWith() {
		Assertions.assertThrows( NullPointerException.class, () -> {
			new ProcessedBlueprintSummary.Builder()
					.withBlueprint( this.blueprint )
					.withOutput( this.output )
					.withTradeStation( this.station )
					.withManufactureCost( TEST_PROCESSED_BLUEPRINT_MANUFACTURE_COST )
					.withCostIndex( TEST_PROCESSED_BLUEPRINT_COST_INDEX )
					.build();
		} );
		Assertions.assertThrows( NullPointerException.class, () -> {
			new ProcessedBlueprintSummary.Builder()
					.withUID( TEST_PROCESSED_BLUEPRINT_UID )
					.withBlueprint( this.blueprint )
					.withOutput( this.output )
					.withManufactureCost( TEST_PROCESSED_BLUEPRINT_MANUFACTURE_COST )
					.withCostIndex( TEST_PROCESSED_BLUEPRINT_COST_INDEX )
					.build();
		} );
		Assertions.assertThrows( NullPointerException.class, () -> {
			new ProcessedBlueprintSummary.Builder()
					.withUID( TEST_PROCESSED_BLUEPRINT_UID )
					.withBlueprint( this.blueprint )
					.withOutput( this.output )
					.withTradeStation( this.station )
					.withCostIndex( TEST_PROCESSED_BLUEPRINT_COST_INDEX )
					.build();
		} );
		Assertions.assertThrows( NullPointerException.class, () -> {
			new ProcessedBlueprintSummary.Builder()
					.withUID( TEST_PROCESSED_BLUEPRINT_UID )
					.withBlueprint( this.blueprint )
					.withOutput( this.output )
					.withTradeStation( this.station )
					.withManufactureCost( TEST_PROCESSED_BLUEPRINT_MANUFACTURE_COST )
					.build();
		} );
	}

	@Test
	public void buildFailureNull() {
		Assertions.assertThrows( NullPointerException.class, () -> {
			new ProcessedBlueprintSummary.Builder()
					.withUID( null )
					.withBlueprint( this.blueprint )
					.withOutput( this.output )
					.withTradeStation( this.station )
					.withManufactureCost( TEST_PROCESSED_BLUEPRINT_MANUFACTURE_COST )
					.withCostIndex( TEST_PROCESSED_BLUEPRINT_COST_INDEX )
					.build();
		} );
		Assertions.assertThrows( NullPointerException.class, () -> {
			new ProcessedBlueprintSummary.Builder()
					.withUID( TEST_PROCESSED_BLUEPRINT_UID )
					.withBlueprint( this.blueprint )
					.withOutput( this.output )
					.withTradeStation( null )
					.withManufactureCost( TEST_PROCESSED_BLUEPRINT_MANUFACTURE_COST )
					.withCostIndex( TEST_PROCESSED_BLUEPRINT_COST_INDEX )
					.build();
		} );
		Assertions.assertThrows( NullPointerException.class, () -> {
			new ProcessedBlueprintSummary.Builder()
					.withUID( TEST_PROCESSED_BLUEPRINT_UID )
					.withBlueprint( this.blueprint )
					.withOutput( this.output )
					.withTradeStation( this.station )
					.withManufactureCost( null )
					.withCostIndex( TEST_PROCESSED_BLUEPRINT_COST_INDEX )
					.build();
		} );
		Assertions.assertThrows( NullPointerException.class, () -> {
			new ProcessedBlueprintSummary.Builder()
					.withUID( TEST_PROCESSED_BLUEPRINT_UID )
					.withBlueprint( this.blueprint )
					.withOutput( this.output )
					.withTradeStation( this.station )
					.withManufactureCost( TEST_PROCESSED_BLUEPRINT_MANUFACTURE_COST )
					.withCostIndex( null )
					.build();
		} );
	}

	@Disabled
	@Test
	public void gettersContract() {
		// When
		Mockito.when( this.blueprint.getTypeIconURL() ).thenReturn( TEST_PROCESSED_BLUEPRINT_BLUEPRINT_TYPE_ICON_URL );
		Mockito.when( this.blueprint.getTypeId() ).thenReturn( TEST_PROCESSED_BLUEPRINT_BLUEPRINT_TYPE_ID );
		Mockito.when( this.blueprint.getName() ).thenReturn( TEST_PROCESSED_BLUEPRINT_BLUEPRINT_TYPE_NAME );
		Mockito.when( this.output.getMarketPrice() ).thenReturn( TEST_PROCESSED_BLUEPRINT_OUTPUT_PRICE );
		Mockito.when( this.output.getTypeIconURL() ).thenReturn( TEST_PROCESSED_BLUEPRINT_OUTPUT_TYPE_ICON_URL );
		Mockito.when( this.output.getTypeId() ).thenReturn( TEST_PROCESSED_BLUEPRINT_OUTPUT_TYPE_ID );
		Mockito.when( this.output.getName() ).thenReturn( TEST_PROCESSED_BLUEPRINT_OUTPUT_TYPE_NAME );
		// Test
		final ProcessedBlueprintSummary blueprintSummary = new ProcessedBlueprintSummary.Builder()
				.withUID( TEST_PROCESSED_BLUEPRINT_UID )
				.withBlueprint( this.blueprint )
				.withOutput( this.output )
				.withTradeStation( this.station )
				.withManufactureCost( TEST_PROCESSED_BLUEPRINT_MANUFACTURE_COST )
				.withCostIndex( TEST_PROCESSED_BLUEPRINT_COST_INDEX )
				.build();
		// Assertions
		Assertions.assertEquals( TEST_PROCESSED_BLUEPRINT_BLUEPRINT_TYPE_ICON_URL, blueprintSummary.getBlueprintTypeIconURL() );
		Assertions.assertEquals( TEST_PROCESSED_BLUEPRINT_BLUEPRINT_TYPE_ID, blueprintSummary.getBlueprintTypeId() );
		Assertions.assertEquals( TEST_PROCESSED_BLUEPRINT_BLUEPRINT_TYPE_NAME, blueprintSummary.getBlueprintTypeName() );
		Assertions.assertEquals( TEST_PROCESSED_BLUEPRINT_COST_INDEX, blueprintSummary.getCostIndex(), 0.01 );
		Assertions.assertEquals( TEST_PROCESSED_BLUEPRINT_MANUFACTURE_COST, blueprintSummary.getManufactureMaterialCost(), 0.01 );
		Assertions.assertEquals( TEST_PROCESSED_BLUEPRINT_OUTPUT_PRICE, blueprintSummary.getOutputPrice(), 0.01 );
		Assertions.assertEquals( TEST_PROCESSED_BLUEPRINT_OUTPUT_TYPE_ICON_URL, blueprintSummary.getOutputTypeIconURL() );
		Assertions.assertEquals( TEST_PROCESSED_BLUEPRINT_OUTPUT_TYPE_ID, blueprintSummary.getOutputTypeId() );
		Assertions.assertEquals( TEST_PROCESSED_BLUEPRINT_OUTPUT_TYPE_NAME, blueprintSummary.getOutputTypeName() );
		Assertions.assertNotNull( blueprintSummary.getTradeStation() );
		Assertions.assertEquals( TEST_PROCESSED_BLUEPRINT_UID, blueprintSummary.getUid() );
	}
}