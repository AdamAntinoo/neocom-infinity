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
import org.dimensinfin.eveonline.neocom.domain.space.SpaceRegion;
import org.dimensinfin.eveonline.neocom.esiswagger.model.GetCharactersCharacterIdBlueprints200Ok;
import org.dimensinfin.eveonline.neocom.exception.ErrorInfoCatalog;
import org.dimensinfin.eveonline.neocom.exception.NeoComRuntimeException;
import org.dimensinfin.eveonline.neocom.industry.domain.ProcessedBlueprint;
import org.dimensinfin.eveonline.neocom.industry.domain.Resource;
import org.dimensinfin.eveonline.neocom.infinity.app.functional.BlueprintPacker;
import org.dimensinfin.eveonline.neocom.infinity.infrastructure.config.LogMessageCatalog;
import org.dimensinfin.eveonline.neocom.market.MarketData;
import org.dimensinfin.logging.LogWrapper;

import static org.dimensinfin.eveonline.neocom.utility.GlobalWideConstants.REDIS_SEPARATOR;


public class BlueprintProcessorJob extends NeoComBackendJob {
	private Credential credential;
	private Integer uniqueIdentifier;

	// - C O N S T R U C T O R S
	private BlueprintProcessorJob() {}

	// - G E T T E R S   &   S E T T E R S
	@Override
	public int getUniqueIdentifier() {
		return this.uniqueIdentifier;
	}

	// - J O B

	/**
	 * The blueprint processor will get all the blueprints for a credential, then pack identical blueprints located exactly on the same location to
	 * reduce the most the number of records to process and finally apply the bill of materials to decompose the manufacture requirements. With the
	 * BOM and the output product produced by the blueprint we are able to make a comparison and create a <code>V2ExtendedBlueprint</code> instance
	 * that will report the profit index expected when manufacturing items with this blueprint instance.
	 */
	@Override
	public Boolean call() throws Exception {
		LogWrapper.enter();
		try {
			// Get the list of blueprints and then pack them with identical characteristics.
			final List<GetCharactersCharacterIdBlueprints200Ok> blueprints = this.jobServicePackager.getEsiDataService()
					.getCharactersCharacterIdBlueprints( this.credential );
			final List<GetCharactersCharacterIdBlueprints200Ok> packedBlueprints = new BlueprintPacker().apply( blueprints );
			final Set<String> addedBlueprintLocators = new HashSet<>();
			packedBlueprints.stream()
					.map( ( GetCharactersCharacterIdBlueprints200Ok bp ) -> {
						final Optional<SpaceLocation> location = this.jobServicePackager.getDataStoreService()
								.accessLocation4Id(
										bp.getLocationId(),
										credential,
										( Long locationId ) -> this.jobServicePackager.getLocationCatalogService()
												.lookupLocation4Id( locationId, credential ).orElseThrow( () ->
														new NeoComRuntimeException(
																ErrorInfoCatalog.LOCATION_NOT_PRESENT.getErrorMessage( locationId )
														)
												)
								); // Get the location to extract the Region
						return BlueprintAndLocation.builder()
								.blueprint( bp )
								.location( location )
								.build();
					} )
					.filter( bpLoc -> { // Remove any blueprint that cannot be reached because the location is not present. This should be notified.
						if ( bpLoc.getLocation().isPresent() ) return true;
						else {
							LogWrapper.info( LogMessageCatalog.BLUEPRINT_LOCATION_NOT_PRESENT.getResolvedMessage(
									bpLoc.getBlueprint().getTypeId(),
									bpLoc.getBlueprint().getLocationId()
							) );
							return false;
						}
					} )
					// Remove any duplication when the same blueprint stack is on different locations of the same System location.
					.filter( ( BlueprintAndLocation bpLoc ) -> this.filterEquivalentExtendedBlueprint( bpLoc, addedBlueprintLocators ) )
					.map( ( BlueprintAndLocation bpLoc ) -> {
						// Start to build the V2ExtendedBlueprint
						final int blueprintTypeId = bpLoc.getBlueprint().getTypeId();
						// Use new cache systems with decoupled generators from data service caches.
						final Optional<EsiType> blueprint = this.jobServicePackager.getDataStoreService().accessType4Id(
								blueprintTypeId,
								( Integer typeId ) -> this.jobServicePackager.getResourceFactory()
										.generateType4Id( typeId )
						);
						if ( blueprint.isEmpty() )
							throw new NeoComRuntimeException( ErrorInfoCatalog.ESITYPE_NOT_FOUND.getErrorMessage( blueprintTypeId ) );
						final int outputModuleId = this.jobServicePackager.getSDERepository().accessModule4Blueprint( blueprintTypeId );
						final Optional<EsiType> outputModule = this.jobServicePackager.getDataStoreService().accessType4Id(
								outputModuleId,
								( Integer typeId ) -> this.jobServicePackager.getResourceFactory()
										.generateType4Id( typeId )
						);
						if ( outputModule.isEmpty() )
							throw new NeoComRuntimeException( ErrorInfoCatalog.ESITYPE_NOT_FOUND.getErrorMessage( outputModuleId ) );
						final List<Resource> bom = this.jobServicePackager.getSDERepository().accessBillOfMaterials( blueprintTypeId );
						final Integer locationRegionId = ((SpaceRegion) bpLoc.getLocation().get()).getRegionId();
						final Double bomAtRegionCost = this.bomBuyCostAtRegionHub( bom, locationRegionId );
						final Double outputModuleAtRegionCost = this.jobServicePackager.getMarketService()
								.getMarketConsolidatedByRegion4ItemId( locationRegionId, outputModuleId )
								.getBestSellPrice();
						double index = 0.0;
						if ( bomAtRegionCost > 0.0 )
							index = outputModuleAtRegionCost / bomAtRegionCost;
						return ProcessedBlueprint.builder()
								.withTypeId( blueprintTypeId )
								.withBlueprintItem( blueprint.get() )
								.withLocation( bpLoc.getLocation().get() )
								.withMaterialEfficiency( bpLoc.getBlueprint().getMaterialEfficiency() )
								.withTimeEfficiency( bpLoc.getBlueprint().getTimeEfficiency() )
								.withOutputTypeId( outputModuleId )
								.withOutputItem( outputModule.get() )
								.withManufactureCost( bomAtRegionCost )
								.withOutputCost( outputModuleAtRegionCost )
								.withBom( bom )
								.withIndex( index )
								.build();
					} )
					.forEach( blueprint -> {
						LogWrapper.info( "Extended Blueprint->" + blueprint.toString() );
						this.jobServicePackager.getDataStoreService()
								.updateProcessedBlueprint( this.credential.getAccountId(), blueprint );
					} );
		} catch (final Exception rte) {
			LogWrapper.error( rte );
		} finally {
			LogWrapper.exit();
		}
		return true;
	}

	/**
	 * Filters blueprints packs that being on the same location and independent on the efficiency will generate equivalent
	 * <code>ExtendedBlueprinnt</code> values.
	 *
	 * @param bpLoc                  the blueprint and location pair
	 * @param addedBlueprintLocators the list of unique blueprint type and region location identifiers.
	 * @return true if the expected generated ExtendedBlueprint is not equal to any already generated instance.
	 */
	private boolean filterEquivalentExtendedBlueprint( final BlueprintAndLocation bpLoc, final Set<String> addedBlueprintLocators ) {
		return addedBlueprintLocators.add(
				bpLoc.getBlueprint().getTypeId() +
						REDIS_SEPARATOR +
						((SpaceRegion) bpLoc.getLocation().get()).getRegionId() +
						REDIS_SEPARATOR +
						bpLoc.getBlueprint().getMaterialEfficiency() +
						REDIS_SEPARATOR +
						bpLoc.getBlueprint().getTimeEfficiency()
		);
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

	// - B U I L D E R
	public static class Builder extends NeoComBackendJob.Builder<BlueprintProcessorJob, BlueprintProcessorJob.Builder> {
		private BlueprintProcessorJob onConstruction;

		// - C O N S T R U C T O R S
		public Builder() {
			this.onConstruction = new BlueprintProcessorJob();
		}

		@Override
		public BlueprintProcessorJob build() {
			if ( Objects.isNull( this.onConstruction.jobServicePackager ))throw new NeoComRuntimeException( "Service Packager is mandatory but this time is null." );
			if ( Objects.isNull( this.onConstruction.credential ))throw new NeoComRuntimeException( "Credential is mandatory but this time is null." );
			return this.onConstruction;
		}

		public BlueprintProcessorJob.Builder withCredential( final Credential credential ) {
			this.getActual().credential = Objects.requireNonNull( credential );
			this.getActual().uniqueIdentifier = new HashCodeBuilder( 19, 137 )
					.appendSuper( super.hashCode() )
					.append( this.getClass().getSimpleName() )
					.toHashCode();
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
