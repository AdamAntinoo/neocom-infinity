package org.dimensinfin.eveonline.neocom.infinity.acceptance.steps;

import javax.validation.constraints.NotNull;

import org.junit.jupiter.api.Assertions;

import org.dimensinfin.eveonline.neocom.infinity.support.NeoComWorld;

import io.cucumber.java.en.Then;

public class ThenThereIsAExceptionResponseWithReturnCode extends StepSupport {
	// - C O N S T R U C T O R S
	public ThenThereIsAExceptionResponseWithReturnCode( final @NotNull NeoComWorld neoComWorld ) {
		super( neoComWorld );
	}

	@Then("there is a exception response with return code of {string}")
	public void there_is_a_exception_response_with_return_code_of( final String httpReturnCode ) {
		Assertions.assertEquals( httpReturnCode, this.neocomWorld.getHttpStatus().toString() );
	}
}
