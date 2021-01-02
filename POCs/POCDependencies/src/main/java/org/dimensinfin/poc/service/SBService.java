package org.dimensinfin.poc.service;

import org.springframework.stereotype.Service;

@Service
public class SBService {
	private final CoreService coreService;

	public SBService( final CoreService coreService ) {this.coreService = coreService;}
}