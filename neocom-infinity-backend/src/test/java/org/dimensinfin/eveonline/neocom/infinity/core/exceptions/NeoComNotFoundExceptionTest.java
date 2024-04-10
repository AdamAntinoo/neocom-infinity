package org.dimensinfin.eveonline.neocom.infinity.core.exceptions;

import org.junit.jupiter.api.Assertions;

import org.dimensinfin.eveonline.neocom.infinity.core.exception.ErrorInfo;
import org.dimensinfin.eveonline.neocom.infinity.core.exception.NeoComNotFoundException;

public class NeoComNotFoundExceptionTest {
	//	@Test
	public void constructorsContract() {
		final NeoComNotFoundException exception1 = new NeoComNotFoundException( ErrorInfo.TARGET_NOT_FOUND );
		Assertions.assertNotNull( exception1 );
		Assertions.assertEquals( "The entity of class <undefined> 0 with identifier {1} is not found.",
				exception1.getMessage() );

		final NeoComNotFoundException exception2 = new NeoComNotFoundException( ErrorInfo.TARGET_NOT_FOUND, "EntityType" );
		Assertions.assertNotNull( exception2 );
		Assertions.assertEquals( "The entity of class EntityType with identifier 0 is not found.",
				exception2.getMessage() );

		final NeoComNotFoundException exception3 = new NeoComNotFoundException( ErrorInfo.TARGET_NOT_FOUND, "EntityType", "123" );
		Assertions.assertNotNull( exception3 );
		Assertions.assertEquals( "The entity of class EntityType with identifier 123 is not found.",
				exception3.getMessage() );
	}
}