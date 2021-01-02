package org.dimensinfin.poc.rest;

import org.springframework.stereotype.Service;

import org.dimensinfin.logging.LogWrapper;
import org.dimensinfin.poc.service.GuiceService;
import org.dimensinfin.poc.service.SBService;

@Service
public class POCService {
	private final SBService sbService;
	private final GuiceService guiceService;

	// - C O N S T R U C T O R S
	public POCService( final SBService sbService, final GuiceService guiceService ) {
		LogWrapper.enter();
		this.guiceService = guiceService;
		this.sbService = sbService;
		LogWrapper.exit();
	}

	public boolean test01() {
		LogWrapper.enter();
		return true;
	}
}