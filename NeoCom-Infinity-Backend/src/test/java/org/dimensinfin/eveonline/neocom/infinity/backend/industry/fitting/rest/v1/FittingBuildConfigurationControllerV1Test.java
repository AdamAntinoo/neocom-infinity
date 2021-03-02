package org.dimensinfin.eveonline.neocom.infinity.backend.industry.fitting.rest.v1;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;

import org.dimensinfin.eveonline.neocom.infinity.backend.industry.fitting.domain.FittingConfigurations;
import org.dimensinfin.eveonline.neocom.infinity.config.security.NeoComAuthenticationProvider;

import static org.dimensinfin.eveonline.neocom.infinity.backend.support.TestDataConstants.FittingConstants.TEST_FITTING_ID;

public class FittingBuildConfigurationControllerV1Test {

	private FittingBuildConfigurationServiceV1 fittingBuildConfigurationServiceV1;

	@BeforeEach
	public void beforeEach() {
		this.fittingBuildConfigurationServiceV1 = Mockito.mock( FittingBuildConfigurationServiceV1.class );
	}

	@Test
	public void getFittingBuildConfigurationSavedConfiguration() {
	}

	@Test
	public void getFittingBuildConfigurationTargetConfiguration() {
	}

	@Test
	public void getFittingConfigurations() {
		// Given
		final Integer fittingId = TEST_FITTING_ID;
		final FittingConfigurations fittingConfigurations = new FittingConfigurations.Builder()
				.withSavedLink(
						WebMvcLinkBuilder.linkTo(
								WebMvcLinkBuilder.methodOn( FittingBuildConfigurationControllerV1.class )
										.getFittingBuildConfigurationSavedConfiguration( fittingId )
						).withRel( "saved" )
				)
				.withTargetLink(
						WebMvcLinkBuilder.linkTo(
								WebMvcLinkBuilder.methodOn( FittingBuildConfigurationControllerV1.class )
										.getFittingBuildConfigurationTargetConfiguration( fittingId )
						).withRel( "target" )
				)
				.build();
		// When
		Mockito.when( this.fittingBuildConfigurationServiceV1.getFittingConfigurations( fittingId ) ).thenReturn( fittingConfigurations );
		final NeoComAuthenticationProvider neoComAuthenticationProvider = Mockito.mock( NeoComAuthenticationProvider.class );
		// Test
		final FittingBuildConfigurationControllerV1 fittingBuildConfigurationControllerV1 = new FittingBuildConfigurationControllerV1(
				neoComAuthenticationProvider,
				this.fittingBuildConfigurationServiceV1
		);
		final ResponseEntity<FittingConfigurations> obtained = fittingBuildConfigurationControllerV1.getFittingConfigurations( fittingId );
		final String expected = "/api/v1/neocom/industry/fittings/buildConfiguration/60320161/";
		// Assertions
		Assertions.assertNotNull( obtained );
		Assertions.assertNotNull( obtained.getBody() );
		Assertions.assertEquals( expected + "savedConfiguration", obtained.getBody().getSavedBuildData().getHref() );
		Assertions.assertEquals( expected + "targetConfiguration", obtained.getBody().getTargetBuildData().getHref() );
	}
}
