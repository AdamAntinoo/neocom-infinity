package org.dimensinfin.eveonline.neocom.infinity.backend.industry.domain;

import java.util.ArrayList;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import org.dimensinfin.eveonline.neocom.domain.EsiType;

import static org.dimensinfin.eveonline.neocom.infinity.backend.support.TestDataConstants.ProcessedBlueprintConstants.TEST_PROCESSED_BLUEPRINT_ID;

public class ProcessedBlueprintTest {
	private static final String TEST_OUTPUT_NAME = "-TEST_OUTPUT_NAME-";

	@Test
	public void buildContract() {
		final EsiType output = Mockito.mock( EsiType.class );
		final ProcessedBlueprint processedBlueprint = new ProcessedBlueprint.Builder()
				.withType( TEST_PROCESSED_BLUEPRINT_ID )
				.withBOM( new ArrayList<>() )
				.withOutput( output )
				.build();
		Assertions.assertNotNull( processedBlueprint );
	}

	@Test
	public void buildFailure() {
		final EsiType output = Mockito.mock( EsiType.class );
		Assertions.assertThrows( NullPointerException.class, () -> {
			new ProcessedBlueprint.Builder()
					.withType( TEST_PROCESSED_BLUEPRINT_ID )
					.withBOM( new ArrayList<>() )
					.withOutput( null )
					.build();
		} );
		Assertions.assertThrows( NullPointerException.class, () -> {
			new ProcessedBlueprint.Builder()
					.withType( TEST_PROCESSED_BLUEPRINT_ID )
					.withBOM( null )
					.withOutput( output )
					.build();
		} );
		Assertions.assertThrows( NullPointerException.class, () -> {
			new ProcessedBlueprint.Builder()
					.withType( TEST_PROCESSED_BLUEPRINT_ID )
					.withBOM( new ArrayList<>() )
					.build();
		} );
	}

	@Test
	public void getterContract() {
		// Given
		final EsiType output = Mockito.mock( EsiType.class );
		// When
		Mockito.when( output.getName() ).thenReturn( TEST_OUTPUT_NAME );
		// Test
		final ProcessedBlueprint processedBlueprint = new ProcessedBlueprint.Builder()
				.withType( TEST_PROCESSED_BLUEPRINT_ID )
				.withBOM( new ArrayList<>() )
				.withOutput( output )
				.build();
		// Assertions
		Assertions.assertEquals( TEST_PROCESSED_BLUEPRINT_ID, processedBlueprint.getBlueprintType() );
		Assertions.assertNotNull( processedBlueprint.getBom() );
		Assertions.assertEquals( TEST_OUTPUT_NAME, processedBlueprint.getOutput().getName() );
	}
}