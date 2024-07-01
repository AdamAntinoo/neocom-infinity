package org.dimensinfin.eveonline.neocom.infinity.backend.scheduler.jobs;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import org.dimensinfin.eveonline.neocom.database.entities.Credential;
import org.dimensinfin.eveonline.neocom.database.repositories.SDERepository;
import org.dimensinfin.eveonline.neocom.domain.EsiType;
import org.dimensinfin.eveonline.neocom.domain.space.SpaceLocation;
import org.dimensinfin.eveonline.neocom.esiswagger.model.GetCharactersCharacterIdBlueprints200Ok;
import org.dimensinfin.eveonline.neocom.exception.NeoComRuntimeException;
import org.dimensinfin.eveonline.neocom.industry.domain.ProcessedBlueprint;
import org.dimensinfin.eveonline.neocom.industry.domain.Resource;
import org.dimensinfin.eveonline.neocom.infinity.backend.scheduler.JobServicePackager;
import org.dimensinfin.eveonline.neocom.infinity.backend.support.InstanceGenerator;
import org.dimensinfin.eveonline.neocom.market.MarketData;
import org.dimensinfin.eveonline.neocom.market.service.MarketService;
import org.dimensinfin.eveonline.neocom.ports.IDataStorePort;
import org.dimensinfin.eveonline.neocom.service.ESIDataService;
import org.dimensinfin.eveonline.neocom.service.LocationCatalogService;

public class BlueprintProcessorJobTest {

	private JobServicePackager jobServicePackager;
	private ESIDataService esiDataService;
	private IDataStorePort dataStore;
	private final SDERepository sdeRepository = Mockito.mock( SDERepository.class );
	private final MarketService marketService = Mockito.mock( MarketService.class );
	private final Credential credential = Mockito.mock( Credential.class );
	//	private RetrofitService retrofitService;
	private LocationCatalogService locationCatalogService;

	@BeforeEach
	public void beforeEach() {
		this.jobServicePackager = Mockito.mock( JobServicePackager.class );
		this.esiDataService = Mockito.mock( ESIDataService.class );
		this.dataStore = Mockito.mock( IDataStorePort.class );
		//		this.retrofitService = Mockito.mock( RetrofitService.class );
		this.locationCatalogService = Mockito.mock( LocationCatalogService.class );
		Mockito.when( this.jobServicePackager.getEsiDataService() ).thenReturn( this.esiDataService );
		Mockito.when( this.jobServicePackager.getLocationCatalogService() ).thenReturn( this.locationCatalogService );
		Mockito.when( this.jobServicePackager.getDataStoreService() ).thenReturn( this.dataStore );
	}

	@Test
	public void buildContract() {
		final BlueprintProcessorJob blueprintProcessorJob = new BlueprintProcessorJob.Builder()
				.withCredential( this.credential )
				.withJobServicePackager( this.jobServicePackager )
				.build();
		Assertions.assertNotNull( blueprintProcessorJob );
	}

	@Test
	void build_missing_field() {
		Assertions.assertThrows( NullPointerException.class, () -> {
			new BlueprintProcessorJob.Builder()
					.withCredential( null )
					.withJobServicePackager( this.jobServicePackager )
					.build();
		} );
		Assertions.assertThrows( NeoComRuntimeException.class, () -> {
			new BlueprintProcessorJob.Builder()
					.withJobServicePackager( this.jobServicePackager )
					.build();
		} );
	}

	@Test
	void getUniqueIdentifier_job_identifier() {
		// Given
		final Credential credentialA = new InstanceGenerator().generateCredential();
		credentialA.setUniqueCredential( "-IUNIQUE-IDENTIFIER-CREENTIAL-A-" );
		final Credential credentialB = new InstanceGenerator().generateCredential();
		credentialB.setUniqueCredential( "-IUNIQUE-IDENTIFIER-CREENTIAL-B-" );
		final BlueprintProcessorJob jobA = new BlueprintProcessorJob.Builder()
				.withCredential( credentialA )
				.withJobServicePackager( this.jobServicePackager )
				.build();
		final BlueprintProcessorJob jobB = new BlueprintProcessorJob.Builder()
				.withCredential( credentialB )
				.withJobServicePackager( this.jobServicePackager )
				.build();
		// When
		final int sutCredentialA = jobA.getUniqueIdentifier();
		final int sutCredentialB = jobB.getUniqueIdentifier();
		// Then
		Assertions.assertNotEquals( sutCredentialA, sutCredentialB );
	}

	@Test
	void call_when_no_blueprints() throws Exception {
		// Given
		final BlueprintProcessorJob job = this.getBlueprintProcessorJob();
		// When
		Mockito.when( this.jobServicePackager.getEsiDataService() ).thenReturn( this.esiDataService );
		Mockito.when( this.esiDataService.getCharactersCharacterIdBlueprints( Mockito.any( Credential.class ) ) )
				.thenReturn( new ArrayList<>() );
		// Then
		Assertions.assertTrue( job.call() );
	}

	@Test
	void call_packed_blueprints() throws Exception {
		// Given
		final BlueprintProcessorJob job = this.getBlueprintProcessorJob();
		final List<GetCharactersCharacterIdBlueprints200Ok> identicalBlueprints = new ArrayList<>();
		final GetCharactersCharacterIdBlueprints200Ok blueprintEsi = new InstanceGenerator().getGetCharactersCharacterIdBlueprints200Ok();
		identicalBlueprints.add( blueprintEsi );
		identicalBlueprints.add( blueprintEsi );
		final SpaceLocation spaceLocation = new InstanceGenerator().getSpaceLocation();
		final EsiType blueprint = new InstanceGenerator().getEsiType();
		final Integer outputTypeId = 4432;
		final List<Resource> bom = new ArrayList<>();
		final MarketData marketData = Mockito.mock( MarketData.class );
		final Double price = 3342.9;
		// When
		Mockito.when( this.jobServicePackager.getEsiDataService() ).thenReturn( this.esiDataService );
		Mockito.when( this.esiDataService.getCharactersCharacterIdBlueprints( Mockito.any( Credential.class ) ) )
				.thenReturn( identicalBlueprints );
		Mockito.when( this.dataStore.accessLocation4Id( Mockito.anyLong(), Mockito.any( Credential.class ), Mockito.any( Function.class ) ) )
				.thenReturn( Optional.of( spaceLocation ) );
		Mockito.when( this.dataStore.accessType4Id( Mockito.anyInt(), Mockito.any( Function.class ) ) )
				.thenReturn( Optional.of( blueprint ) )
				.thenReturn( Optional.of( blueprint ) );
		Mockito.when( this.jobServicePackager.getSDERepository() ).thenReturn( this.sdeRepository );
		Mockito.when( this.sdeRepository.accessModule4Blueprint( Mockito.anyInt() ) )
				.thenReturn( outputTypeId );
		Mockito.when( this.sdeRepository.accessBillOfMaterials( Mockito.anyInt() ) )
				.thenReturn( bom );
		Mockito.when( this.jobServicePackager.getMarketService() ).thenReturn( this.marketService );
		Mockito.when( this.marketService.getMarketConsolidatedByRegion4ItemId( Mockito.anyInt(), Mockito.anyInt() ) )
				.thenReturn( marketData );
		Mockito.when( marketData.getBestSellPrice() ).thenReturn( price );
		// Then
		Assertions.assertTrue( job.call() );
		Mockito.verify( this.dataStore, Mockito.times( 1 ) )
				.updateProcessedBlueprint( Mockito.anyInt(), Mockito.any( ProcessedBlueprint.class ) );
	}

	@Test
	void call_multiple_blueprints() throws Exception {
		// Given
		final BlueprintProcessorJob job = this.getBlueprintProcessorJob();
		final List<GetCharactersCharacterIdBlueprints200Ok> differentBlueprints = new ArrayList<>();
		final GetCharactersCharacterIdBlueprints200Ok blueprintEsi1 = new InstanceGenerator().getGetCharactersCharacterIdBlueprints200Ok();
		final GetCharactersCharacterIdBlueprints200Ok blueprintEsi2 = new InstanceGenerator().getGetCharactersCharacterIdBlueprints200Ok();
		differentBlueprints.add( blueprintEsi1 );
		differentBlueprints.add( blueprintEsi1 );
		differentBlueprints.add( blueprintEsi2.typeId( 38 ) );
		final SpaceLocation spaceLocation = new InstanceGenerator().getSpaceLocation();
		final EsiType blueprint = new InstanceGenerator().getEsiType();
		final Integer outputTypeId = 4432;
		final List<Resource> bom = new ArrayList<>();
		final MarketData marketData = Mockito.mock( MarketData.class );
		final Double price = 3342.9;
		// When
		Mockito.when( this.jobServicePackager.getEsiDataService() ).thenReturn( this.esiDataService );
		Mockito.when( this.esiDataService.getCharactersCharacterIdBlueprints( Mockito.any( Credential.class ) ) )
				.thenReturn( differentBlueprints );
		Mockito.when( this.dataStore.accessLocation4Id( Mockito.anyLong(), Mockito.any( Credential.class ), Mockito.any( Function.class ) ) )
				.thenReturn( Optional.of( spaceLocation ) );
		Mockito.when( this.dataStore.accessType4Id( Mockito.anyInt(), Mockito.any( Function.class ) ) )
				.thenReturn( Optional.of( blueprint ) )
				.thenReturn( Optional.of( blueprint ) );
		Mockito.when( this.jobServicePackager.getSDERepository() ).thenReturn( this.sdeRepository );
		Mockito.when( this.sdeRepository.accessModule4Blueprint( Mockito.anyInt() ) )
				.thenReturn( outputTypeId );
		Mockito.when( this.sdeRepository.accessBillOfMaterials( Mockito.anyInt() ) )
				.thenReturn( bom );
		Mockito.when( this.jobServicePackager.getMarketService() ).thenReturn( this.marketService );
		Mockito.when( this.marketService.getMarketConsolidatedByRegion4ItemId( Mockito.anyInt(), Mockito.anyInt() ) )
				.thenReturn( marketData );
		Mockito.when( marketData.getBestSellPrice() ).thenReturn( price );
		// Then
		Assertions.assertTrue( job.call() );
		Mockito.verify( this.dataStore, Mockito.times( 2 ) )
				.updateProcessedBlueprint( Mockito.anyInt(), Mockito.any( ProcessedBlueprint.class ) );
	}

	@Test
	void call_outout_item_not_found() throws Exception {
		// Given
		final BlueprintProcessorJob job = this.getBlueprintProcessorJob();
		final List<GetCharactersCharacterIdBlueprints200Ok> differentBlueprints = new ArrayList<>();
		final GetCharactersCharacterIdBlueprints200Ok blueprintEsi1 = new InstanceGenerator().getGetCharactersCharacterIdBlueprints200Ok();
		final GetCharactersCharacterIdBlueprints200Ok blueprintEsi2 = new InstanceGenerator().getGetCharactersCharacterIdBlueprints200Ok();
		differentBlueprints.add( blueprintEsi1 );
		differentBlueprints.add( blueprintEsi1 );
		final SpaceLocation spaceLocation = new InstanceGenerator().getSpaceLocation();
		final EsiType blueprint = new InstanceGenerator().getEsiType();
		final Integer outputTypeId = 4432;
		final List<Resource> bom = new ArrayList<>();
		final MarketData marketData = Mockito.mock( MarketData.class );
		final Double price = 3342.9;
		// When
		Mockito.when( this.jobServicePackager.getEsiDataService() ).thenReturn( this.esiDataService );
		Mockito.when( this.esiDataService.getCharactersCharacterIdBlueprints( Mockito.any( Credential.class ) ) )
				.thenReturn( differentBlueprints );
		Mockito.when( this.dataStore.accessLocation4Id( Mockito.anyLong(), Mockito.any( Credential.class ), Mockito.any( Function.class ) ) )
				.thenReturn( Optional.of( spaceLocation ) );
		Mockito.when( this.dataStore.accessType4Id( Mockito.anyInt(), Mockito.any( Function.class ) ) )
				.thenReturn( Optional.of( blueprint ) )
				.thenReturn( Optional.empty() );
		Mockito.when( this.jobServicePackager.getSDERepository() ).thenReturn( this.sdeRepository );
		Mockito.when( this.sdeRepository.accessModule4Blueprint( Mockito.anyInt() ) )
				.thenReturn( outputTypeId );
		Mockito.when( this.sdeRepository.accessBillOfMaterials( Mockito.anyInt() ) )
				.thenReturn( bom );
		Mockito.when( this.jobServicePackager.getMarketService() ).thenReturn( this.marketService );
		Mockito.when( this.marketService.getMarketConsolidatedByRegion4ItemId( Mockito.anyInt(), Mockito.anyInt() ) )
				.thenReturn( marketData );
		Mockito.when( marketData.getBestSellPrice() ).thenReturn( price );
		// Then
		Assertions.assertTrue( job.call() );
		Mockito.verify( this.dataStore, Mockito.times( 0 ) )
				.updateProcessedBlueprint( Mockito.anyInt(), Mockito.any( ProcessedBlueprint.class ) );
	}

	@Test
	void call_blueprint_item_not_found() throws Exception {
		// Given
		final BlueprintProcessorJob job = this.getBlueprintProcessorJob();
		final List<GetCharactersCharacterIdBlueprints200Ok> differentBlueprints = new ArrayList<>();
		final GetCharactersCharacterIdBlueprints200Ok blueprintEsi1 = new InstanceGenerator().getGetCharactersCharacterIdBlueprints200Ok();
		final GetCharactersCharacterIdBlueprints200Ok blueprintEsi2 = new InstanceGenerator().getGetCharactersCharacterIdBlueprints200Ok();
		differentBlueprints.add( blueprintEsi1 );
		differentBlueprints.add( blueprintEsi1 );
		final SpaceLocation spaceLocation = new InstanceGenerator().getSpaceLocation();
		final EsiType blueprint = new InstanceGenerator().getEsiType();
		final Integer outputTypeId = 4432;
		final List<Resource> bom = new ArrayList<>();
		final MarketData marketData = Mockito.mock( MarketData.class );
		final Double price = 3342.9;
		// When
		Mockito.when( this.jobServicePackager.getEsiDataService() ).thenReturn( this.esiDataService );
		Mockito.when( this.esiDataService.getCharactersCharacterIdBlueprints( Mockito.any( Credential.class ) ) )
				.thenReturn( differentBlueprints );
		Mockito.when( this.dataStore.accessLocation4Id( Mockito.anyLong(), Mockito.any( Credential.class ), Mockito.any( Function.class ) ) )
				.thenReturn( Optional.of( spaceLocation ) );
		Mockito.when( this.dataStore.accessType4Id( Mockito.anyInt(), Mockito.any( Function.class ) ) )
				.thenReturn( Optional.empty() )
				.thenReturn( Optional.of( blueprint ) );
		Mockito.when( this.jobServicePackager.getSDERepository() ).thenReturn( this.sdeRepository );
		Mockito.when( this.sdeRepository.accessModule4Blueprint( Mockito.anyInt() ) )
				.thenReturn( outputTypeId );
		Mockito.when( this.sdeRepository.accessBillOfMaterials( Mockito.anyInt() ) )
				.thenReturn( bom );
		Mockito.when( this.jobServicePackager.getMarketService() ).thenReturn( this.marketService );
		Mockito.when( this.marketService.getMarketConsolidatedByRegion4ItemId( Mockito.anyInt(), Mockito.anyInt() ) )
				.thenReturn( marketData );
		Mockito.when( marketData.getBestSellPrice() ).thenReturn( price );
		// Then
		Assertions.assertTrue( job.call() );
		Mockito.verify( this.dataStore, Mockito.times( 0 ) )
				.updateProcessedBlueprint( Mockito.anyInt(), Mockito.any( ProcessedBlueprint.class ) );
	}

	@Test
	void call_multiple_packs_on_same_region() throws Exception {
		// Given
		final BlueprintProcessorJob job = this.getBlueprintProcessorJob();
		final List<GetCharactersCharacterIdBlueprints200Ok> differentBlueprints = new ArrayList<>();
		final GetCharactersCharacterIdBlueprints200Ok blueprintEsi1 = new InstanceGenerator().getGetCharactersCharacterIdBlueprints200Ok();
		final GetCharactersCharacterIdBlueprints200Ok blueprintEsi2 = new InstanceGenerator().getGetCharactersCharacterIdBlueprints200Ok();
		differentBlueprints.add( blueprintEsi1 );
		differentBlueprints.add( blueprintEsi1 );
		differentBlueprints.add( blueprintEsi2.materialEfficiency( 99 ) );
		final SpaceLocation spaceLocation = new InstanceGenerator().getSpaceLocation();
		final EsiType blueprint = new InstanceGenerator().getEsiType();
		final Integer outputTypeId = 4432;
		final List<Resource> bom = new ArrayList<>();
		final MarketData marketData = Mockito.mock( MarketData.class );
		final Double price = 3342.9;
		// When
		Mockito.when( this.jobServicePackager.getEsiDataService() ).thenReturn( this.esiDataService );
		Mockito.when( this.esiDataService.getCharactersCharacterIdBlueprints( Mockito.any( Credential.class ) ) )
				.thenReturn( differentBlueprints );
		Mockito.when( this.dataStore.accessLocation4Id( Mockito.anyLong(), Mockito.any( Credential.class ), Mockito.any( Function.class ) ) )
				.thenReturn( Optional.of( spaceLocation ) );
		Mockito.when( this.dataStore.accessType4Id( Mockito.anyInt(), Mockito.any( Function.class ) ) )
				.thenReturn( Optional.of( blueprint ) )
				.thenReturn( Optional.of( blueprint ) );
		Mockito.when( this.jobServicePackager.getSDERepository() ).thenReturn( this.sdeRepository );
		Mockito.when( this.sdeRepository.accessModule4Blueprint( Mockito.anyInt() ) )
				.thenReturn( outputTypeId );
		Mockito.when( this.sdeRepository.accessBillOfMaterials( Mockito.anyInt() ) )
				.thenReturn( bom );
		Mockito.when( this.jobServicePackager.getMarketService() ).thenReturn( this.marketService );
		Mockito.when( this.marketService.getMarketConsolidatedByRegion4ItemId( Mockito.anyInt(), Mockito.anyInt() ) )
				.thenReturn( marketData );
		Mockito.when( marketData.getBestSellPrice() ).thenReturn( price );
		// Then
		Assertions.assertTrue( job.call() );
		Mockito.verify( this.dataStore, Mockito.times( 2 ) )
				.updateProcessedBlueprint( Mockito.anyInt(), Mockito.any( ProcessedBlueprint.class ) );
	}

	private BlueprintProcessorJob getBlueprintProcessorJob() {
		return new BlueprintProcessorJob.Builder()
				.withCredential( this.credential )
				.withJobServicePackager( this.jobServicePackager )
				.build();
	}
}