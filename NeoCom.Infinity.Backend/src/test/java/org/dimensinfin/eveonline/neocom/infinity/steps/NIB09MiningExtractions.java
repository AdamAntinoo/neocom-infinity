package org.dimensinfin.eveonline.neocom.infinity.steps;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.junit.Assert;
import org.springframework.beans.factory.annotation.Autowired;

import org.dimensinfin.eveonline.neocom.database.entities.MiningExtractionEntity;
import org.dimensinfin.eveonline.neocom.infinity.support.ConverterContainer;
import org.dimensinfin.eveonline.neocom.infinity.support.NeoComWorld;
import org.dimensinfin.eveonline.neocom.infinity.support.miningextraction.converter.CucumberTableToMiningExtractionEntityConverter;
import org.dimensinfin.eveonline.neocom.infinity.support.miningextraction.rest.support.MiningExtractionsFeignClientSupport;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;

public class NIB09MiningExtractions extends SupportSteps {
	private final MiningExtractionsFeignClientSupport miningExtractionsFeignClientSupport;

	@Autowired
	public NIB09MiningExtractions( final ConverterContainer cucumberTableToRequestConverters,
	                               final NeoComWorld neocomWorld,
	                               final MiningExtractionsFeignClientSupport miningExtractionsFeignClientSupport ) {
		super( cucumberTableToRequestConverters, neocomWorld );
		this.miningExtractionsFeignClientSupport = miningExtractionsFeignClientSupport;
	}

	@Given("the list of MiningExtractions for pilot {string} at the repository")
	public void the_list_of_MiningExtractions_for_pilot_at_the_repository( final String pilotId, final List<Map<String, String>> dataTable ) throws IOException {
		for (int i = 0; i < dataTable.size(); i++) {
			final Map<String, String> row = dataTable.get( i );
			final MiningExtractionEntity miningExtractionEntity = new CucumberTableToMiningExtractionEntityConverter().convert( row );
			Assert.assertTrue( this.miningExtractionsFeignClientSupport.storeMiningExtractionEntity( miningExtractionEntity ) > 0 );
		}
	}

	@Then("the list of mining extractions reported has the next contents")
	public void the_list_of_mining_extractions_reported_has_the_next_contents( final List<Map<String, String>> dataTable ) {
		final String ID = "id";
		final String OWNER_ID = "ownerId";
		final String TYPE_ID = "typeId";
		final String EXTRACTIONS_DATE = "extractionDate";
		final String EXTRACTIONS_HOUR = "extractionHour";
		final String QUANTITY = "quantity";
		final String SYSTEMID = "systemId";
		final List<MiningExtractionEntity> miningExtractionsReceived = this.neocomWorld.getMiningExtractionsResponseEntity().getBody();
		for (int i = 0; i < dataTable.size(); i++) {
			final Map<String, String> row = dataTable.get( i );
			final MiningExtractionEntity miningExtractionEntity = miningExtractionsReceived.get( i );
			Assert.assertEquals( row.get( ID ), miningExtractionEntity.getId() );
		}
	}
}
