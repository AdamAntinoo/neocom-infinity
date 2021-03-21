package org.dimensinfin.eveonline.neocom.infinity.adapter.implementers;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import org.dimensinfin.eveonline.neocom.infinity.service.SBNeoComDBService;

public class SBNeoComDBServiceTest {
	@Test
	public void constructorContract() throws Exception {
		// Given
		final String NEOCOM_DATABASE_URL = "-NEOCOM_DATABASE_URL-";
		// Test
		final SBNeoComDBService dbAdapter = new SBNeoComDBService( NEOCOM_DATABASE_URL );
		// Assertions
		Assertions.assertNotNull( dbAdapter );
	}
}
