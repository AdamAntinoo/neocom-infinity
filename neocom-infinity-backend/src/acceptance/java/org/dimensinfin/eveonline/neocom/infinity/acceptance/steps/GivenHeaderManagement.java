package org.dimensinfin.eveonline.neocom.infinity.acceptance.steps;

import org.dimensinfin.eveonline.neocom.infinity.support.NeoComWorld;

import io.cucumber.java.en.Given;

public class GivenHeaderManagement extends StepSupport{
	public GivenHeaderManagement( final NeoComWorld neoComWorld ) {
		super( neoComWorld );
	}

	@Given("the authorization header with token {string}")
	public void the_authorization_header_with_token(final String token) {
		this.getWorld().setAuthorizationHeader(token);
	}

}
