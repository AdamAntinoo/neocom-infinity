package org.dimensinfin.eveonline.neocom.infinity.backend.scheduler.jobs;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.apache.commons.lang3.builder.HashCodeBuilder;

import org.dimensinfin.eveonline.neocom.database.entities.Credential;
import org.dimensinfin.eveonline.neocom.domain.EsiType;
import org.dimensinfin.eveonline.neocom.esiswagger.model.GetCharactersCharacterIdBlueprints200Ok;
import org.dimensinfin.eveonline.neocom.esiswagger.model.GetMarketsRegionIdOrders200Ok;
import org.dimensinfin.eveonline.neocom.industry.domain.PricedResource;
import org.dimensinfin.eveonline.neocom.industry.domain.Resource;
import org.dimensinfin.eveonline.neocom.infinity.backend.industry.domain.ProcessedBlueprint;
import org.dimensinfin.eveonline.neocom.market.MarketData;

public class BlueprintProcessorJob extends NeoComBackendJob {
	private Credential credential;

	// - C O N S T R U C T O R S
	private BlueprintProcessorJob() {}

	// - G E T T E R S   &   S E T T E R S
	@Override
	public int getUniqueIdentifier() {
		return new HashCodeBuilder( 19, 137 )
				.appendSuper( super.hashCode() )
				.append( this.getClass().getSimpleName() )
				.toHashCode();
	}

	// - J O B

	/**
	 * The blueprint processor will get all the blueprints for a credential, then filter them to keep only distinct blueprint types and finally
	 * apply the bill of materials to decompose the product requirements.
	 * With the BOM and the output product produced by the blueprint make a comparison and create a <code>ProcessedBlueprint</code> instance that
	 * will report the profit index expected when manufacturing items with this blueprint type.
	 */
	@Override
	public Boolean call() throws Exception {
		final int defaultRegionId = this.jobServicePackager.getPreferencesRepository()
				.accessMarketRegionId( this.credential.getAccountId() )
				.getNumberValue().intValue();
		final List<GetCharactersCharacterIdBlueprints200Ok> blueprints = this.jobServicePackager.getEsiDataService()
				.getCharactersCharacterIdBlueprints( this.credential );
		final List<ProcessedBlueprint> processedBlueprints = blueprints.stream()
				.map( blueprint -> blueprint.getTypeId() )
				.distinct()
				.map( blueprintType -> {
					final int outputModuleId = this.jobServicePackager.getSDERepository().accessModule4Blueprint( blueprintType );
					final EsiType outputModule = this.jobServicePackager.getResourceFactory().generateType4Id( outputModuleId );
					final MarketData outputMarketData = this.jobServicePackager.getMarketService()
							.getMarketConsolidatedByRegion4ItemId( defaultRegionId, outputModuleId );
					final List<Resource> bom = this.jobServicePackager.getSDERepository().accessBillOfMaterials( blueprintType );
					final List<PricedResource> bomPriced = new ArrayList<>();
					for (final Resource resource : bom) {
						final MarketData marketData = this.jobServicePackager.getMarketService()
								.getMarketConsolidatedByRegion4ItemId( defaultRegionId, resource.getTypeId() );
						bomPriced.add( new PricedResource.Builder()
								.withMarketData( marketData )
								.withPrice( this.getBestSellPriceFromMarketData( marketData ) )
								.withItemType( resource.getType() )
								.withGroup( resource.getGroup() )
								.withCategory( resource.getCategory() )
								.build()
						);
					}
					return new ProcessedBlueprint.Builder()
							.withBlueprint( this.jobServicePackager.getResourceFactory().generateType4Id( blueprintType ) )
							.withOutput( outputModule )
							.withOutputMarketData( outputMarketData )
							.withBOM( bomPriced )
							.build();
				} )
				.collect( Collectors.toList() );
		this.jobServicePackager.getDataStoreService().updateProcessedBlueprint( this.credential, processedBlueprints );
		return true;
	}

	private Double getBestSellPriceFromMarketData( final MarketData data ) {
		final List<GetMarketsRegionIdOrders200Ok> orders = data.getSellOrders();
		if (orders.isEmpty()) return 0.0;
		else
			return orders.stream()
					.mapToDouble( order -> order.getPrice() )
					.max().orElse( 0 );
	}

	// - B U I L D E R
	public static class Builder extends NeoComBackendJob.Builder<BlueprintProcessorJob, BlueprintProcessorJob.Builder> {
		private BlueprintProcessorJob onConstruction;

		// - C O N S T R U C T O R S
		public Builder() {
			this.onConstruction = new BlueprintProcessorJob();
		}

		@Override
		public BlueprintProcessorJob build() {
			return this.onConstruction;
		}

		public BlueprintProcessorJob.Builder withCredential( final Credential credential ) {
			this.getActual().credential = Objects.requireNonNull( credential );
			return this;
		}

		@Override
		protected BlueprintProcessorJob getActual() {
			if (null == this.onConstruction) this.onConstruction = new BlueprintProcessorJob();
			return this.onConstruction;
		}

		@Override
		protected BlueprintProcessorJob.Builder getActualBuilder() {
			return this;
		}
	}
}