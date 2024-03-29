package org.dimensinfin.eveonline.neocom.infinity.backend.industry.rest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.dimensinfin.eveonline.neocom.infinity.NeoComInfinityBackendApplication.APPLICATION_ERROR_CODE_PREFIX;

@RestController
@RequestMapping("/api/v1/neocom/industry")
public class IndustryControllerV1 {
	public static final String INDUSTRY_ERROR_CODE_PREFIX = APPLICATION_ERROR_CODE_PREFIX + ".industry";
}
