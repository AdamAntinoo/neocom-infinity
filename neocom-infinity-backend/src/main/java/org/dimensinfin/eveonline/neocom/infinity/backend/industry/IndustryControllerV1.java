package org.dimensinfin.eveonline.neocom.infinity.backend.industry;

import javax.validation.constraints.NotNull;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import org.dimensinfin.eveonline.neocom.infinity.config.security.NeoComAuthenticationProvider;
import org.dimensinfin.eveonline.neocom.infinity.core.rest.NeoComAuthenticatedController;

import static org.dimensinfin.eveonline.neocom.infinity.NeoComInfinityBackendApplication.APPLICATION_ERROR_CODE_PREFIX;

@RestController
@RequestMapping("/api/v1/neocom/industry")
public abstract class IndustryControllerV1 extends NeoComAuthenticatedController {
	public static final String INDUSTRY_ERROR_CODE_PREFIX = APPLICATION_ERROR_CODE_PREFIX + ".industry";

	// - C O N S T R U C T O R S
	public IndustryControllerV1( @NotNull final NeoComAuthenticationProvider neoComAuthenticationProvider ) {
		super( neoComAuthenticationProvider );
	}
}
