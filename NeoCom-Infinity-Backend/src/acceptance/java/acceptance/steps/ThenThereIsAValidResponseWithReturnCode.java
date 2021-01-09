package acceptance.steps;

import org.junit.jupiter.api.Assertions;

import org.dimensinfin.eveonline.neocom.infinity.support.NeoComWorld;

import io.cucumber.java.en.Then;

public class ThenThereIsAValidResponseWithReturnCode extends StepSupport {
	// - C O N S T R U C T O R S
	public ThenThereIsAValidResponseWithReturnCode( final NeoComWorld neoComWorld ) {
		super( neoComWorld );
	}

	@Then("there is a valid response with return code of {string}")
	public void there_is_a_valid_response_with_return_code_of( final String httpReturnCode ) {
		Assertions.assertEquals( httpReturnCode, this.neocomWorld.getHttpStatus().toString() );
	}
}
