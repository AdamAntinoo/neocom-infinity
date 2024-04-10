package org.dimensinfin.eveonline.neocom.infinity.acceptance.steps;

import java.util.ArrayList;
import javax.validation.constraints.NotNull;

import org.dimensinfin.eveonline.neocom.infinity.support.NeoComWorld;

import io.cucumber.java.en.Given;

/**
 * @author Adam Antinoo (adamantinoo.git@gmail.com)
 * @since 0.20.0
 */
public class GivenACleanListOfCookies extends StepSupport {
	// - C O N S T R U C T O R S
	public GivenACleanListOfCookies( final @NotNull NeoComWorld neoComWorld ) {
		super( neoComWorld );
	}

	@Given("a clear list of cookies")
	public void a_clear_list_of_cookies() {
		this.neocomWorld.setCookies( new ArrayList<>() );
	}
}