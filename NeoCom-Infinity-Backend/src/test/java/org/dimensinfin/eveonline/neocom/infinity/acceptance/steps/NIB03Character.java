package org.dimensinfin.eveonline.neocom.infinity.acceptance.steps;

import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Assertions;

import org.dimensinfin.eveonline.neocom.infinity.acceptance.support.character.rest.FittingModelValidator;
import org.dimensinfin.eveonline.neocom.infinity.acceptance.support.character.rest.PilotModelValidator;
import org.dimensinfin.eveonline.neocom.infinity.support.ConverterContainer;
import org.dimensinfin.eveonline.neocom.infinity.support.NeoComWorld;

import io.cucumber.java.en.Then;

public class NIB03Character extends SupportSteps {
	// - C O N S T R U C T O R S
	public NIB03Character( final ConverterContainer cucumberTableToRequestConverters, final NeoComWorld neocomWorld ) {
		super( cucumberTableToRequestConverters, neocomWorld );
	}

	@Then("the response has a list of {int} fittings")
	public void the_response_has_a_list_of_fittings( final Integer fittingCount ) {
		Assertions.assertNotNull( this.neocomWorld.getPilotFittingsResponseEntity() );
		Assertions.assertNotNull( this.neocomWorld.getPilotFittingsResponseEntity().getBody() );
		Assertions.assertEquals( fittingCount, this.neocomWorld.getPilotFittingsResponseEntity().getBody().size() );
	}

	@Then("the resulting Pilot data has the next contents")
	public void the_resulting_Pilot_data_has_the_next_contents( final List<Map<String, String>> dataTable ) {
		Assertions.assertNotNull( this.neocomWorld.getPilotDataResponseEntity() );
		Assertions.assertNotNull( this.neocomWorld.getPilotDataResponseEntity().getBody() );
		Assertions.assertTrue( new PilotModelValidator().validate( dataTable.get( 0 ), this.neocomWorld.getPilotDataResponseEntity().getBody() ) );
	}
	@Then("the fitting with id {string} has the next data")
	public void the_fitting_with_id_has_the_next_data(final String fittingIdentifier, final List<Map<String, String>> dataTable) {
		Assertions.assertNotNull( this.neocomWorld.getPilotFittingsResponseEntity() );
		Assertions.assertNotNull( this.neocomWorld.getPilotFittingsResponseEntity().getBody() );
		// Search for fitting
		this.neocomWorld.getPilotFittingsResponseEntity().getBody().stream()
				.filter( fitting -> fitting.getFittingId()==Integer.parseInt( fittingIdentifier ) )
				.forEach( fitting -> Assertions.assertTrue(
						new FittingModelValidator().validate( dataTable.get( 0 ), fitting )
				));
	}
}
