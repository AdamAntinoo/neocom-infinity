package org.dimensinfin.eveonline.neocom.infinity.backend.industry.fitting.rest.v1;

import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import org.dimensinfin.eveonline.neocom.infinity.backend.industry.fitting.domain.FittingBuildConfiguration;
import org.dimensinfin.eveonline.neocom.infinity.backend.industry.fitting.domain.FittingConfigurations;
import org.dimensinfin.eveonline.neocom.infinity.config.security.NeoComAuthenticationProvider;
import org.dimensinfin.eveonline.neocom.infinity.core.rest.NeoComAuthenticatedController;

@RestController
@Validated
public class FittingBuildConfigurationControllerV1 extends NeoComAuthenticatedController {
	private final FittingBuildConfigurationServiceV1 fittingBuildConfigurationServiceV1;

	// - C O N S T R U C T O R S
	@Autowired
	public FittingBuildConfigurationControllerV1( @NotNull final NeoComAuthenticationProvider neoComAuthenticationProvider,
	                                              @NotNull final FittingBuildConfigurationServiceV1 fittingBuildConfigurationServiceV1 ) {
		super( neoComAuthenticationProvider );
		this.fittingBuildConfigurationServiceV1 = fittingBuildConfigurationServiceV1;
	}

	@GetMapping(path = "/fittings/buildConfiguration/{fittingId}/savedConfiguration",
			consumes = "application/json",
			produces = "application/json")
	public ResponseEntity<FittingBuildConfiguration> getFittingBuildConfigurationSavedConfiguration( final @PathVariable @NotNull Integer fittingId ) {
		return new ResponseEntity<>( this.fittingBuildConfigurationServiceV1.getFittingBuildConfigurationSavedConfiguration( fittingId ),
				HttpStatus.OK );
	}

	@GetMapping(path = "/fittings/buildConfiguration/{fittingId}/targetConfiguration",
			consumes = "application/json",
			produces = "application/json")
	public ResponseEntity<FittingBuildConfiguration> getFittingBuildConfigurationTargetConfiguration( final @PathVariable @NotNull Integer fittingId ) {
		return new ResponseEntity<>( this.fittingBuildConfigurationServiceV1.getFittingBuildConfigurationTargetConfiguration( fittingId ),
				HttpStatus.OK );
	}

	@GetMapping(path = "/fittings/buildConfiguration/{fittingId}",
			consumes = "application/json",
			produces = "application/json")
	public ResponseEntity<FittingConfigurations> getFittingConfigurations( final @PathVariable @NotNull Integer fittingId ) {
		return new ResponseEntity<>( this.fittingBuildConfigurationServiceV1.getFittingConfigurations( fittingId ), HttpStatus.OK );
	}
}
