package org.dimensinfin.eveonline.neocom.infinity.acceptance.steps;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;

import org.dimensinfin.eveonline.neocom.infinity.CucumberLauncherAcc;
import org.dimensinfin.eveonline.neocom.infinity.NeoComInfinityBackendApplication;

import io.cucumber.spring.CucumberContextConfiguration;

@CucumberContextConfiguration
@ContextConfiguration(name="steps", classes={ CucumberLauncherAcc.class})
@SpringBootTest(classes={ NeoComInfinityBackendApplication.class }, webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@ActiveProfiles("acceptance")
public class CucumberBootstrap {
}
