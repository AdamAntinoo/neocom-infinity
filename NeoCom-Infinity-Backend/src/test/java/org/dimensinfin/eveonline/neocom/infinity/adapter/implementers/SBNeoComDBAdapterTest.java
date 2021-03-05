package org.dimensinfin.eveonline.neocom.infinity.adapter.implementers;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class SBNeoComDBAdapterTest {
	@Test
	public void constructorContract() throws Exception {
		// Given
		final String NEOCOM_DATABASE_URL = "-NEOCOM_DATABASE_URL-";
		// Test
		final SBNeoComDBAdapter dbAdapter = new SBNeoComDBAdapter( NEOCOM_DATABASE_URL );
		// Assertions
		Assertions.assertNotNull( dbAdapter );
	}
}
