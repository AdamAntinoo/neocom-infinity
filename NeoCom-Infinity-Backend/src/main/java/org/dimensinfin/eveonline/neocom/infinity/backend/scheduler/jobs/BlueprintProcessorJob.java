package org.dimensinfin.eveonline.neocom.infinity.backend.scheduler.jobs;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.apache.commons.lang3.builder.HashCodeBuilder;

import org.dimensinfin.eveonline.neocom.database.entities.Credential;
import org.dimensinfin.eveonline.neocom.database.repositories.SDERepository;
import org.dimensinfin.eveonline.neocom.esiswagger.model.GetCharactersCharacterIdBlueprints200Ok;
import org.dimensinfin.eveonline.neocom.infinity.backend.industry.domain.ProcessedBlueprint;
import org.dimensinfin.eveonline.neocom.infinity.service.DataStoreService;
import org.dimensinfin.eveonline.neocom.service.ESIDataService;
import org.dimensinfin.eveonline.neocom.service.ResourceFactory;
import org.dimensinfin.eveonline.neocom.service.scheduler.domain.Job;

public class BlueprintProcessorJob extends Job {
	private Credential credential;
	private ESIDataService esiDataService;
	private SDERepository sdeRepository;
	private DataStoreService dataStoreService;
	private ResourceFactory resourceFactory;

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
		final List<GetCharactersCharacterIdBlueprints200Ok> blueprints = this.esiDataService.getCharactersCharacterIdBlueprints( credential );
		final List<ProcessedBlueprint> processedBlueprints = blueprints.stream()
				.map( blueprint -> blueprint.getTypeId() )
				.distinct()
				.map( blueprintType -> new ProcessedBlueprint.Builder()
						.withType( blueprintType.intValue() )
						.withBOM( this.sdeRepository.accessBillOfMaterials( blueprintType ) )
						.withOutput( this.resourceFactory.generateType4Id(
								this.sdeRepository.accessModule4Blueprint( blueprintType.intValue() )
						) )
						.build() )
				.collect( Collectors.toList() );
		this.dataStoreService.updateProcessedBlueprint( credential, processedBlueprints );
		return true;
	}

	// - B U I L D E R
	public static class Builder extends Job.Builder<BlueprintProcessorJob, BlueprintProcessorJob.Builder> {
		private BlueprintProcessorJob onConstruction;

		// - C O N S T R U C T O R S
		public Builder() {
			this.onConstruction = new BlueprintProcessorJob();
		}

		public BlueprintProcessorJob.Builder withCredential( final Credential credential ) {
			this.onConstruction.credential = Objects.requireNonNull( credential );
			return this;
		}

		public BlueprintProcessorJob.Builder withDataStore( final DataStoreService dataStoreService ) {
			this.onConstruction.dataStoreService = Objects.requireNonNull( dataStoreService );
			return this;
		}

		public BlueprintProcessorJob.Builder withEsiDataService( final ESIDataService esiDataService ) {
			this.onConstruction.esiDataService = Objects.requireNonNull( esiDataService );
			return this;
		}

		public BlueprintProcessorJob.Builder withResourceFactory( final ResourceFactory resourceFactory ) {
			this.onConstruction.resourceFactory = Objects.requireNonNull( resourceFactory );
			return this;
		}

		public BlueprintProcessorJob.Builder withSDERepository( final SDERepository sdeRepository ) {
			this.onConstruction.sdeRepository = Objects.requireNonNull( sdeRepository );
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

		public BlueprintProcessorJob build() {
			return this.onConstruction;
		}
	}
}