package org.dimensinfin.eveonline.neocom.infinity.acceptance;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(
		features = { "src/test/resources/features" },
		glue = { "org.dimensinfin.eveonline.neocom.infinity.acceptance.steps" },
		plugin = { "pretty", "json:target/cucumber_report.json" },
		tags = { "not @skip_scenario", "not @front", "not @duplication", "@NIB10.01" })
public class RunAcceptanceTests {}
