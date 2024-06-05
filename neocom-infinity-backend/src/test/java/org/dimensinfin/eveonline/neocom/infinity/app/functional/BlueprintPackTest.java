package org.dimensinfin.eveonline.neocom.infinity.app.functional;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import org.dimensinfin.eveonline.neocom.esiswagger.model.GetCharactersCharacterIdBlueprints200Ok;

class BlueprintPackTest {

	@Test
	void apply() {
	}

	@Test
	void apply_toList() {
		// Given
		final List<GetCharactersCharacterIdBlueprints200Ok> sourceList = new ArrayList<>();
		final GetCharactersCharacterIdBlueprints200Ok blueprint1 = new GetCharactersCharacterIdBlueprints200Ok();
		blueprint1.setTypeId( 1001 );
		blueprint1.setLocationId( 2001L );
		blueprint1.setMaterialEfficiency( 10 );
		blueprint1.setTimeEfficiency( 10 );
		blueprint1.setRuns( 100 );
		final GetCharactersCharacterIdBlueprints200Ok blueprint2 = new GetCharactersCharacterIdBlueprints200Ok();
		blueprint2.setTypeId( 1002 );
		blueprint2.setLocationId( 2001L );
		blueprint2.setMaterialEfficiency( 10 );
		blueprint2.setTimeEfficiency( 10 );
		blueprint2.setRuns( 100 );
		final BlueprintPack fun = new BlueprintPack();
		sourceList.add( blueprint1 );
		sourceList.add( blueprint1 );
		sourceList.add( blueprint1 );
		sourceList.add( blueprint1 );
		sourceList.add( blueprint2 );
		sourceList.add( blueprint2 );
		// When
		final List<GetCharactersCharacterIdBlueprints200Ok> sut = fun.apply( sourceList );
		// Then
		Assertions.assertNotNull( sut );
		Assertions.assertEquals( 2, sut.size() );
	}
}