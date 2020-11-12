package org.dimensinfin.eveonline.neocom.infinity.backend.industry.fitting.rest.v1;

import javax.validation.constraints.NotNull;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import org.dimensinfin.eveonline.neocom.infinity.backend.industry.rest.IndustryControllerV1;
import org.dimensinfin.eveonline.neocom.infinity.backend.industry.fitting.domain.FittingBuildConfiguration;
import org.dimensinfin.eveonline.neocom.infinity.backend.industry.fitting.domain.FittingConfigurations;

@RestController
@Validated
public class FittingBuildConfigurationControllerV1 extends IndustryControllerV1 {
	private final FittingBuildConfigurationServiceV1 fittingBuildConfigurationServiceV1;

	// - C O N S T R U C T O R S
	public FittingBuildConfigurationControllerV1( final @NotNull FittingBuildConfigurationServiceV1 fittingBuildConfigurationServiceV1 ) {
		this.fittingBuildConfigurationServiceV1 = fittingBuildConfigurationServiceV1;
	}

	@GetMapping(path = "/fittings/buildConfiguration/{fittingId}",
			consumes = "application/json",
			produces = "application/json")
	public ResponseEntity<FittingConfigurations> getFittingConfigurations( final @PathVariable @NotNull Integer fittingId ) {
		return new ResponseEntity<>( this.fittingBuildConfigurationServiceV1.getFittingConfigurations( fittingId ), HttpStatus.OK );
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
}
