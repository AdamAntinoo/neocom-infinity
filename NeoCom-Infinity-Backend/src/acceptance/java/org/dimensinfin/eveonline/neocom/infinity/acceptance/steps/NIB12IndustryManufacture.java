package org.dimensinfin.eveonline.neocom.infinity.acceptance.steps;

import java.util.List;
import java.util.Map;
import javax.validation.constraints.NotNull;

import org.junit.jupiter.api.Assertions;

import org.dimensinfin.eveonline.neocom.infinity.acceptance.support.industry.ProcessedBlueprintResponseValidator;
import org.dimensinfin.eveonline.neocom.infinity.support.NeoComWorld;

import io.cucumber.java.en.Then;

/**
 * @author Adam Antinoo (adamantinoo.git@gmail.com)
 * @since 0.20.0
 */
public class NIB12IndustryManufacture extends StepSupport {
	// - C O N S T R U C T O R S
	public NIB12IndustryManufacture( final @NotNull NeoComWorld neoComWorld ) {
		super( neoComWorld );
	}

	@Then("the ProcessedBlueprintSummary with identifier {string} has the next contents")
	public void the_processed_blueprint_summary_wit_identifier_has_the_next_contents( final String blueprintUniqueIdentifier,
	                                                                                  final List<Map<String, String>> dataTable ) {
		Assertions.assertNotNull( this.neocomWorld.getPilotBlueprintsResponseEntity() );
		Assertions.assertNotNull( this.neocomWorld.getPilotBlueprintsResponseEntity().getBody() );
		this.neocomWorld.getPilotBlueprintsResponseEntity().getBody()
				.stream()
				.filter( blueprint -> blueprint.getUid().equalsIgnoreCase( blueprintUniqueIdentifier ) )
				.forEach( blueprint ->
						Assertions.assertTrue( new ProcessedBlueprintResponseValidator().validate( dataTable.get( 0 ), blueprint ) )
				);
	}

	@Then("the resulting Pilot Industry Blueprints Cost Index request list has {int} records")
	public void the_resulting_pilot_industry_blueprints_cost_index_request_list_has_records( final Integer recordCount ) {
		Assertions.assertNotNull( this.neocomWorld.getPilotBlueprintsResponseEntity() );
		Assertions.assertNotNull( this.neocomWorld.getPilotBlueprintsResponseEntity().getBody() );
		Assertions.assertEquals( recordCount, this.neocomWorld.getPilotBlueprintsResponseEntity().getBody().size() );
	}
}