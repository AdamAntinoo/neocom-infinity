package org.dimensinfin.eveonline.neocom.infinity.acceptance.steps;

import java.util.List;
import java.util.Map;
import javax.validation.constraints.NotNull;

import org.junit.jupiter.api.Assertions;

import org.dimensinfin.eveonline.neocom.infinity.acceptance.support.character.rest.FittingModelValidator;
import org.dimensinfin.eveonline.neocom.infinity.acceptance.support.character.rest.PilotV1Validator;
import org.dimensinfin.eveonline.neocom.infinity.support.NeoComWorld;

import io.cucumber.java.en.Then;

public class NIB03Character extends StepSupport {
	// - C O N S T R U C T O R S
	public NIB03Character( final @NotNull NeoComWorld neoComWorld ) {
		super( neoComWorld );
	}

	@Then("the fitting with id {string} has the next data")
	public void the_fitting_with_id_has_the_next_data( final String fittingIdentifier, final List<Map<String, String>> dataTable ) {
		Assertions.assertNotNull( this.neocomWorld.getPilotFittingsResponseEntity() );
		Assertions.assertNotNull( this.neocomWorld.getPilotFittingsResponseEntity().getBody() );
		// Search for fitting
		this.neocomWorld.getPilotFittingsResponseEntity().getBody().stream()
				.filter( fitting -> fitting.getFittingId() == Integer.parseInt( fittingIdentifier ) )
				.forEach( fitting -> Assertions.assertTrue(
						new FittingModelValidator().validate( dataTable.get( 0 ), fitting )
				) );
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
		//		Assertions.assertTrue( new PilotModelValidator().validate( dataTable.get( 0 ), this.neocomWorld.getPilotDataResponseEntity().getBody() ) );
	}

	@Then("the resulting PilotV1 has the next contents")
	public void the_resulting_pilot_v1_has_the_next_contents( final List<Map<String, String>> dataTable ) {
		Assertions.assertNotNull( this.neocomWorld.getPilotDataResponseEntity() );
		Assertions.assertNotNull( this.neocomWorld.getPilotDataResponseEntity().getBody() );
		Assertions.assertTrue( new PilotV1Validator().validate( dataTable.get( 0 ), this.neocomWorld.getPilotDataResponseEntity().getBody() ) );
	}
}
