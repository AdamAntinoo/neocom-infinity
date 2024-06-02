package org.dimensinfin.eveonline.neocom.infinity.backend.industry.converter;

import java.util.Objects;
import javax.validation.constraints.NotNull;

import org.dimensinfin.core.interfaces.Converter;
import org.dimensinfin.eveonline.neocom.industry.domain.ProcessedBlueprint;
import org.dimensinfin.eveonline.neocom.industry.domain.ProcessedBlueprintSummary;

/**
 * @author Adam Antinoo (adamantinoo.git@gmail.com)
 * @since 0.20.0
 */
public class ProcessedBlueprintToSummaryConverter implements Converter<ProcessedBlueprint, ProcessedBlueprintSummary> {
	private final String uniqueId;

	// - C O N S T R U C T O R S
	public ProcessedBlueprintToSummaryConverter( @NotNull final String uniqueId ) {
		this.uniqueId = uniqueId;
	}

	@Override
	public ProcessedBlueprintSummary convert( final ProcessedBlueprint input ) {
		final double manufactureCost = input.getBom().stream()
				.filter( element -> element.getCategory().getCategoryId() != 9 )
				.filter( element -> element.getCategory().getCategoryId() != 16 )
				.mapToDouble( element -> element.getMarketPrice() * element.getQuantity() )
				.sum();
		// TODO - Add the job build time to the cost index calculation.
		final double costIndex = (input.getOutput().getMarketPrice() * input.getOutput().getQuantity()) * 0.9 / manufactureCost;
		return new ProcessedBlueprintSummary.Builder()
				.withUID( this.uniqueId )
				.withBlueprint( input.getBlueprint() )
				.withOutput( input.getOutput() )
				.withTradeStation( Objects.isNull( input.getOutputMarketData().getBestSellOrder() ) ?
						input.getOutputMarketData().getRegionHub() :
						input.getOutputMarketData().getBestSellOrder().getStation() )
				.withManufactureCost( manufactureCost )
				.withCostIndex( costIndex )
				.build();
	}
}