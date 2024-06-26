package org.dimensinfin.eveonline.neocom.infinity.acceptance;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import static io.cucumber.junit.CucumberOptions.SnippetType.CAMELCASE;

@RunWith(Cucumber.class)
@CucumberOptions(
		features = { "src/acceptance/resources/features" },
		snippets = CAMELCASE,
		glue = { "org.dimensinfin.eveonline.neocom.infinity.acceptance.steps" },
		plugin = { "pretty", "json:target/cucumber_report.json" },
		tags = "@NIB12.01")
public class RunAcceptanceTests {
}
