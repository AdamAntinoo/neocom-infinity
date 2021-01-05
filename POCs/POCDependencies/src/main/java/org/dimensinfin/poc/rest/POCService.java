package org.dimensinfin.poc.rest;

import com.google.inject.name.Named;
import org.springframework.stereotype.Service;

import org.dimensinfin.eveonline.neocom.service.ResourceFactory;
import org.dimensinfin.logging.LogWrapper;
import org.dimensinfin.poc.service.GuiceService;
import org.dimensinfin.poc.service.SBService;

@Service
public class POCService {
	private final SBService sbService;
	private final GuiceService guiceService;
	private final ResourceFactory resourceFactory;

	// - C O N S T R U C T O R S
	public POCService( final SBService sbService, final GuiceService guiceService,
	                   final @Named("ResourceFactory") ResourceFactory resourceFactory ) {
		LogWrapper.enter();
		this.resourceFactory = resourceFactory;
		this.guiceService = guiceService;
		this.sbService = sbService;
		LogWrapper.exit();
	}

	public boolean test01() {
		LogWrapper.enter();
		return true;
	}
}