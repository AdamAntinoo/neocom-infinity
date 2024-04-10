package org.dimensinfin.eveonline.neocom.infinity.acceptance.steps;

import java.util.List;
import java.util.Map;
import javax.validation.constraints.NotNull;

import org.junit.jupiter.api.Assertions;

import org.dimensinfin.eveonline.neocom.infinity.acceptance.support.industry.FittingBuildConfigurationValidator;
import org.dimensinfin.eveonline.neocom.infinity.acceptance.support.industry.FittingConfigurationsValidator;
import org.dimensinfin.eveonline.neocom.infinity.acceptance.support.industry.FittingInfoValidator;
import org.dimensinfin.eveonline.neocom.infinity.support.NeoComWorld;

import io.cucumber.java.en.Then;

public class NIB04IndustryFitting extends StepSupport {
	// - C O N S T R U C T O R S
	public NIB04IndustryFitting( final @NotNull NeoComWorld neoComWorld ) {
		super( neoComWorld );
	}

	@Then("the resulting Fitting Configurations contents")
	public void the_resulting_Fitting_Configurations_contents( final List<Map<String, String>> dataTable ) {
		Assertions.assertNotNull( this.neocomWorld.getFittingConfigurationsResponseEntity() );
		Assertions.assertNotNull( this.neocomWorld.getFittingConfigurationsResponseEntity().getBody() );
		Assertions.assertTrue( new FittingConfigurationsValidator()
				.validate( dataTable.get( 0 ), this.neocomWorld.getFittingConfigurationsResponseEntity().getBody() )
		);
	}
	@Then("the resulting Fitting Build Configuration data has the next contents")
	public void the_resulting_Fitting_Build_Configuration_data_has_the_next_contents(final List<Map<String, String>> dataTable) {
		Assertions.assertNotNull( this.neocomWorld.getFittingBuildConfigurationResponseEntity() );
		Assertions.assertNotNull( this.neocomWorld.getFittingBuildConfigurationResponseEntity().getBody() );
		Assertions.assertTrue( new FittingBuildConfigurationValidator()
				.validate( dataTable.get( 0 ), this.neocomWorld.getFittingBuildConfigurationResponseEntity().getBody() )
		);
	}
	@Then("the resulting Fitting Info data has the next contents")
	public void the_resulting_Fitting_Info_data_has_the_next_contents(final List<Map<String, String>> dataTable) {
		Assertions.assertNotNull( this.neocomWorld.getFittingBuildConfigurationResponseEntity() );
		Assertions.assertNotNull( this.neocomWorld.getFittingBuildConfigurationResponseEntity().getBody() );
		Assertions.assertNotNull( this.neocomWorld.getFittingBuildConfigurationResponseEntity().getBody().getFittingInfo() );
		Assertions.assertTrue( new FittingInfoValidator()
				.validate( dataTable.get( 0 ),this.neocomWorld.getFittingBuildConfigurationResponseEntity().getBody().getFittingInfo() )
		);
	}
}
