package org.dimensinfin.poc.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.dimensinfin.logging.LogWrapper;

@Service
public class CoreService {
	// - C O N S T R U C T O R S
	@Autowired
	public CoreService() {
		LogWrapper.enter();
		LogWrapper.exit();
	}
}