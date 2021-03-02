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

public class BlueprintProcessorJobTest {

	private JobServicePackager jobServicePackager;

	@BeforeEach
	public void beforeEach() {
		this.jobServicePackager = Mockito.mock( JobServicePackager.class );
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
}