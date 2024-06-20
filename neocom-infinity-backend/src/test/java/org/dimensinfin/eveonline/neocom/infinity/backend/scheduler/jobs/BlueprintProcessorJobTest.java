package org.dimensinfin.eveonline.neocom.infinity.backend.scheduler.jobs;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import org.dimensinfin.eveonline.neocom.database.entities.Credential;
import org.dimensinfin.eveonline.neocom.esiswagger.model.GetCharactersCharacterIdBlueprints200Ok;
import org.dimensinfin.eveonline.neocom.infinity.backend.scheduler.JobServicePackager;
import org.dimensinfin.eveonline.neocom.service.ESIDataService;
import org.dimensinfin.eveonline.neocom.service.LocationCatalogService;
import org.dimensinfin.eveonline.neocom.service.RetrofitService;

public class BlueprintProcessorJobTest {

	private JobServicePackager jobServicePackager;
	private ESIDataService esiDataService;
	private RetrofitService retrofitService;
	private LocationCatalogService locationCatalogService;

	@BeforeEach
	public void beforeEach() {
		this.esiDataService = Mockito.mock( ESIDataService.class );
		this.jobServicePackager = Mockito.mock( JobServicePackager.class );
		this.retrofitService = Mockito.mock( RetrofitService.class);
		this.locationCatalogService=Mockito.mock( LocationCatalogService.class );
		Mockito.when( this.jobServicePackager.getEsiDataService() ).thenReturn( this.esiDataService );
		Mockito.when( this.jobServicePackager.getLocationCatalogService() ).thenReturn( this.locationCatalogService );
	}

	@Test
	public void buildContract() {
		final Credential credential = Mockito.mock( Credential.class );
		final BlueprintProcessorJob blueprintProcessorJob = new BlueprintProcessorJob.Builder()
				.withCredential( credential )
				.withJobServicePackager( this.jobServicePackager )
				.build();
		Assertions.assertNotNull( blueprintProcessorJob );
	}

	@Test
	public void callSuccess() throws Exception {
		// Given
		final Credential credential = Mockito.mock( Credential.class );
		final ESIDataService esiDataService = Mockito.mock( ESIDataService.class );
		final List<GetCharactersCharacterIdBlueprints200Ok> blueprintList = new ArrayList<>();
		final GetCharactersCharacterIdBlueprints200Ok blueprint1 = new GetCharactersCharacterIdBlueprints200Ok();
		blueprint1.setItemId( 1005458604009L );
		// When
		Mockito.when( this.jobServicePackager.getEsiDataService() ).thenReturn( esiDataService );
		Mockito.when( esiDataService.getCharactersCharacterIdBlueprints( credential ) ).thenReturn( blueprintList );
		// Test
		final BlueprintProcessorJob blueprintProcessorJob = new BlueprintProcessorJob.Builder()
				.withCredential( credential )
				.withJobServicePackager( this.jobServicePackager )
				.build();
		final Boolean obtained = blueprintProcessorJob.call();
		// Assertions
		Assertions.assertNotNull( obtained );
		Assertions.assertTrue( obtained );
	}

	@Test
	void when_process_blueprint_on_safety() throws Exception {
		// Given
		final Credential credential = Mockito.mock( Credential.class );
		final List<GetCharactersCharacterIdBlueprints200Ok> blueprints = new ArrayList<>();

		final BlueprintProcessorJob job = new BlueprintProcessorJob.Builder()
				.withCredential( credential )
				.withJobServicePackager( this.jobServicePackager )
				.build();
		// When
		Mockito.when( this.esiDataService.getCharactersCharacterIdBlueprints( Mockito.any( Credential.class ) ) ).thenReturn( blueprints );
//		Mockito.when( this.locationCatalogService )

		final Boolean sut = job.call();
		// Then
		Assertions.assertTrue( sut );
	}
}