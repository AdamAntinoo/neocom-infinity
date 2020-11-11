package org.dimensinfin.eveonline.neocom.infinity.acceptance.steps;

import java.util.List;
import java.util.Map;

import javax.validation.constraints.NotNull;

import org.junit.jupiter.api.Assertions;

import org.dimensinfin.eveonline.neocom.infinity.acceptance.support.industry.FittingConfigurationsValidator;
import org.dimensinfin.eveonline.neocom.infinity.support.NeoComWorld;

import io.cucumber.java.en.Then;

public class NIB04IndustryFitting extends StepSupport{
	public NIB04IndustryFitting( final @NotNull NeoComWorld neoComWorld ) {
		super( neoComWorld );
	}

	@Then("the resulting Fitting Build Configuration data has the next contents")
	public void the_resulting_Fitting_Build_Configuration_data_has_the_next_contents(final List<Map<String, String>> dataTable) {
		Assertions.assertNotNull( this.neocomWorld.getFittingConfigurationsResponseEntity() );
		Assertions.assertNotNull( this.neocomWorld.getFittingConfigurationsResponseEntity().getBody() );
		Assertions.assertTrue( new FittingConfigurationsValidator().validate( dataTable.get( 0 ), this.neocomWorld.getFittingConfigurationsResponseEntity().getBody() ) );
	}
}
