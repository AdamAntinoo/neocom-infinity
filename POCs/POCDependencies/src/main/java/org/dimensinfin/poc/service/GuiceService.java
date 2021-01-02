package org.dimensinfin.poc.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.dimensinfin.eveonline.neocom.service.ESIDataService;
import org.dimensinfin.logging.LogWrapper;

@Service
public class GuiceService {
	private final ESIDataService esiDataService;

	// - C O N S T R U C T O R S
	@Autowired
	public GuiceService( final ESIDataService esiDataService ) {
		LogWrapper.enter();
		LogWrapper.info( esiDataService.toString() );
		this.esiDataService = esiDataService;
		LogWrapper.exit();
	}
}