package org.dimensinfin.eveonline.neocom.infinity;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.platform.suite.api.ConfigurationParameter;
import org.junit.platform.suite.api.IncludeEngines;
import org.junit.platform.suite.api.SelectClasspathResource;
import org.junit.platform.suite.api.Suite;

import org.dimensinfin.logging.LogWrapper;

import io.cucumber.junit.platform.engine.Constants;

@Suite
@IncludeEngines("cucumber")
@SelectClasspathResource("features")
@ConfigurationParameter(key = Constants.PLUGIN_PROPERTY_NAME, value = "pretty")
@ConfigurationParameter(key = Constants.GLUE_PROPERTY_NAME, value = "org.dimensinfin.eveonline.neocom.infinity.acceptance.steps")
public class CucumberLauncherAcc {
	@Test
	@DisplayName("Gherkin Acceptance Tests")
	public void acceptancetestCheck() {
		LogWrapper.info( "...Running Acceptance tests" );
	}
}
