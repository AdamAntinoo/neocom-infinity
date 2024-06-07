package org.dimensinfin.eveonline.neocom.infinity.backend.scheduler.jobs;

import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;

import org.apache.commons.lang3.builder.HashCodeBuilder;

import org.dimensinfin.eveonline.neocom.database.entities.Credential;
import org.dimensinfin.eveonline.neocom.domain.EsiType;
import org.dimensinfin.eveonline.neocom.domain.space.SpaceLocation;
import org.dimensinfin.eveonline.neocom.esiswagger.model.GetCharactersCharacterIdBlueprints200Ok;
import org.dimensinfin.eveonline.neocom.esiswagger.model.GetMarketsRegionIdOrders200Ok;
import org.dimensinfin.eveonline.neocom.industry.domain.ProcessedBlueprint;
import org.dimensinfin.eveonline.neocom.industry.domain.Resource;
import org.dimensinfin.eveonline.neocom.infinity.app.functional.BlueprintPack;
import org.dimensinfin.eveonline.neocom.infinity.app.functional.LocationLookup;
import org.dimensinfin.eveonline.neocom.market.MarketData;
import org.dimensinfin.logging.LogWrapper;


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
	 * The blueprint processor will get all the blueprints for a credential, then filter them to keep only distinct blueprint types and finally apply
	 * the bill of materials to decompose the product requirements. With the BOM and the output product produced by the blueprint make a comparison
	 * and create a <code>ProcessedBlueprint</code> instance that will report the profit index expected when manufacturing items with this blueprint
	 * type.
	 */
	@Override
	public Boolean call() throws Exception {
		LogWrapper.enter();
		try {
			// Get the list of blueprints and then pack them for identical characteristics.
			final List<GetCharactersCharacterIdBlueprints200Ok> blueprints = this.jobServicePackager.getEsiDataService()
					.getCharactersCharacterIdBlueprints( this.credential );
			final List<GetCharactersCharacterIdBlueprints200Ok> packedBlueprints = new BlueprintPack().apply( blueprints );
			final Set<String> addedBlueprintLocators = new HashSet<>();
			packedBlueprints.stream()
					.map( ( GetCharactersCharacterIdBlueprints200Ok bp ) -> {
						final Optional<SpaceLocation> location = this.jobServicePackager
								.getLocationCatalogService()
								.lookupLocation4Id( bp.getLocationId(), credential ); // Get the location to extract the Region
						return BlueprintAndLocation.builder()
								.blueprint( bp )
								.location( location )
								.build();
					} )
					.filter( bpLoc -> bpLoc.getLocation().isPresent() )
					.filter( bpLoc -> addedBlueprintLocators.add(
							bpLoc.getBlueprint().getItemId() + ":" + bpLoc.getLocation().get().getLocationId() ) )
					.map( ( BlueprintAndLocation bpLoc ) -> {
						// Start to build the ProcessedBlueprintDto
						final int blueprintTypeId = bpLoc.getBlueprint().getTypeId();
						final EsiType blueprint = this.jobServicePackager.getResourceFactory().generateType4Id( blueprintTypeId );
						final int outputModuleId = this.jobServicePackager.getSDERepository().accessModule4Blueprint( blueprintTypeId );
						final EsiType outputModule = this.jobServicePackager.getResourceFactory().generateType4Id( outputModuleId );
						final List<Resource> bom = this.jobServicePackager.getSDERepository().accessBillOfMaterials( blueprintTypeId );
						final Double bomAtRegionCost = this.bomBuyCostAtRegionHub( bom, 10000002 );
						final Double outputModuleAtRegionCost = this.jobServicePackager.getMarketService()
								.getMarketConsolidatedByRegion4ItemId( 10000002, outputModuleId )
								.getBestSellPrice();
						Double index = 0.0;
						if ( bomAtRegionCost > 0.0 )
							index = outputModuleAtRegionCost / bomAtRegionCost;
						return ProcessedBlueprint.builder()
								.withTypeId( blueprintTypeId )
								.withBlueprintItem( blueprint )
								.withLocation( bpLoc.getLocation().get() )
								.withMaterialEfficiency( bpLoc.getBlueprint().getMaterialEfficiency() )
								.withTimeEfficiency( bpLoc.getBlueprint().getTimeEfficiency() )
								.withOutputTypeId( outputModuleId )
								.withOutputItem( outputModule )
								.withManufactureCost( bomAtRegionCost )
								.withOutputCost( outputModuleAtRegionCost )
								.withBom( bom )
								.withIndex( index )
								.build();
					} )
					.forEach( blueprint -> {
						LogWrapper.info( "Processed Blueprint->" + blueprint.toString() );
						this.jobServicePackager.getDataStoreService()
								.updateProcessedBlueprint( this.credential.getAccountId(), blueprint );
					} );
		} catch (
				final Exception rte) {
			LogWrapper.error( rte );
		} finally {
			LogWrapper.exit();
		}
		return true;
	}

	private Double bomBuyCostAtRegionHub( final List<Resource> bom, final int regionHubId ) {
		return bom.stream()
				.mapToDouble( resource -> {
					final MarketData marketData = this.jobServicePackager.getMarketService()
							.getMarketConsolidatedByRegion4ItemId( regionHubId, resource.getTypeId() );
					return marketData.getBestSellPrice();
				} )
				.sum();
	}

	private Double getBestSellPriceFromMarketData( final MarketData data ) {
		final List<GetMarketsRegionIdOrders200Ok> orders = data.getSellOrders();
		if ( orders.isEmpty() ) return 0.0;
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
			if ( null == this.onConstruction ) this.onConstruction = new BlueprintProcessorJob();
			return this.onConstruction;
		}

		@Override
		protected BlueprintProcessorJob.Builder getActualBuilder() {
			return this;
		}
	}
}