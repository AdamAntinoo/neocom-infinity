package org.dimensinfin.eveonline.neocom.infinity.core;

import java.io.IOException;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import org.dimensinfin.eveonline.neocom.infinity.core.exceptions.ErrorInfo;
import org.dimensinfin.eveonline.neocom.infinity.core.exceptions.NeoComSBException;

public class NeoComSBExceptionTest {
	@Test
	public void constructorErrorInfo() {
		final NeoComSBException exception = new NeoComSBException( ErrorInfo.AUTHORIZATION_TRANSLATION );
		Assertions.assertNotNull( exception );
	}

//	@Test
//	public void getMessageEmptyConstructor() {
//		// Given
//		final NeoComSBException exception = new NeoComSBException();
//		// Assertions
//		String expected = "Malformed NeoCom exception. 'errorInfo' is null.";
//		Assertions.assertEquals( expected, exception.getMessage() );
//	}

	@Test
	public void getMessageErrorInfoAndExceptionConstructor() {
		// Given
		final NeoComSBException exception = new NeoComSBException( ErrorInfo.ESI_DATA_NOT_FOUND, new IOException( "-IOEXCEPTION-MESSAGE-" ) );
		// Assertions
		String expected = "The requested data of class {0} was not found on the ESI service.:-IOEXCEPTION-MESSAGE-";
		Assertions.assertEquals( expected, exception.getMessage() );
	}

	@Test
	public void getMessageErrorInfoConstructor() {
		// Given
		final NeoComSBException exception = new NeoComSBException( ErrorInfo.AUTHORIZATION_TRANSLATION );
		// Assertions
		String expected = "TokenTranslationResponse response is not valid.";
		Assertions.assertEquals( expected, exception.getMessage() );
	}

	@Test
	public void getMessageErrorInfoConstructorWithParameter() {
		// Given
		final NeoComSBException exception = new NeoComSBException( ErrorInfo.ESI_DATA_NOT_FOUND.getErrorMessage( "-PARAMETER-" ) );
		// Assertions
		String expected = "NeoCom.Exception:The requested data of class -PARAMETER- was not found on the ESI service.";
		Assertions.assertEquals( expected, exception.getMessage() );
	}

	@Test
	public void getMessageExceptionConstructor() {
		// Given
		final NeoComSBException exception = new NeoComSBException( new IOException( "-IOEXCEPTION-MESSAGE-" ) );
		// Assertions
		String expected = "Not intercepted exception. Reporting java native exception.:-IOEXCEPTION-MESSAGE-";
		Assertions.assertEquals( expected, exception.getMessage() );
	}

	@Test
	public void getMessageMessageConstructor() {
		// Given
		final NeoComSBException exception = new NeoComSBException( "Exception local manual message." );
		// Assertions
		String expected = "NeoCom.Exception:Exception local manual message.";
		Assertions.assertEquals( expected, exception.getMessage() );
	}
}
