package org.dimensinfin.eveonline.neocom.infinity.infrastructure.adapters.inbound.industryApi;

import javax.validation.constraints.NotNull;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import org.dimensinfin.eveonline.neocom.infinity.config.security.NeoComAuthenticationProvider;
import org.dimensinfin.eveonline.neocom.infinity.core.rest.NeoComAuthenticatedController;

import static org.dimensinfin.eveonline.neocom.infinity.NeoComInfinityBackendApplication.APPLICATION_ERROR_CODE_PREFIX;

@RestController
@RequestMapping("/api/v2/neospring/industry")
public abstract class IndustryControllerV2 extends NeoComAuthenticatedController {
	public static final String INDUSTRY_ERROR_CODE_PREFIX = APPLICATION_ERROR_CODE_PREFIX + ".industry";

	// - C O N S T R U C T O R S
	public IndustryControllerV2( @NotNull final NeoComAuthenticationProvider neoComAuthenticationProvider ) {
		super( neoComAuthenticationProvider );
	}
}
