package org.dimensinfin.eveonline.neocom.infinity.core.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import org.dimensinfin.eveonline.neocom.infinity.authorization.rest.v1.AuthorizationService;

@RestController
public abstract class NeoComAuthenticatedController extends NeoComController {
	private final AuthorizationService authorizationService;

	@Autowired
	public NeoComAuthenticatedController( final AuthorizationService authorizationService ) {
		this.authorizationService = authorizationService;
	}
}
