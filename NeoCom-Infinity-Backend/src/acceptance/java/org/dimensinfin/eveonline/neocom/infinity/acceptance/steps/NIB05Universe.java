package org.dimensinfin.eveonline.neocom.infinity.acceptance.steps;

import java.util.List;
import java.util.Map;
import javax.validation.constraints.NotNull;

import org.junit.jupiter.api.Assertions;

import org.dimensinfin.eveonline.neocom.infinity.acceptance.support.universe.rest.EsiItemModelValidator;
import org.dimensinfin.eveonline.neocom.infinity.support.NeoComWorld;

import io.cucumber.java.en.Then;

public class NIB05Universe extends StepSupport {

	// - C O N S T R U C T O R S
	public NIB05Universe( final @NotNull NeoComWorld neoComWorld ) {
		super( neoComWorld );
	}

	@Then("the resulting EsiItem data has the next contents")
	public void the_resulting_EsiItem_data_has_the_next_contents( final List<Map<String, String>> dataTable ) {
		Assertions.assertNotNull( this.neocomWorld.getItemResponseEntity() );
		Assertions.assertNotNull( this.neocomWorld.getItemResponseEntity().getBody() );
		Assertions.assertTrue( new EsiItemModelValidator().validate( dataTable.get( 0 ), this.neocomWorld.getItemResponseEntity().getBody() ) );
	}
}
