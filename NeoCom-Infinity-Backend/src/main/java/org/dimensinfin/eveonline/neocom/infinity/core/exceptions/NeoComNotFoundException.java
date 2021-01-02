package org.dimensinfin.eveonline.neocom.infinity.core.exceptions;

import org.springframework.http.HttpStatus;

@Deprecated
public class NeoComNotFoundException extends RuntimeException {
	protected String sourceClass;
	protected String sourceMethod;
	private ErrorInfo errorInfo;
	private String message;

	// - C O N S T R U C T O R S
	private NeoComNotFoundException() {
		final StackTraceElement[] stacktrace = Thread.currentThread().getStackTrace();
		final StackTraceElement stackElement = stacktrace[3]; // This is to check if we are using Dalvik
		this.sourceMethod = stackElement.getMethodName();
		this.sourceClass = stackElement.getClassName();
	}

	public NeoComNotFoundException( final ErrorInfo errorInfo, final String... arguments ) {
		this();
		this.errorInfo = errorInfo;
		this.message = this.prepareMessage( arguments );
	}

	// - G E T T E R S   &   S E T T E R S
	public HttpStatus getHttpStatus() {
		return this.errorInfo.status;
	}

	public String getMessage() {
		return this.message;
	}

	public String getSourceClass() {
		return this.sourceClass;
	}

	public String getSourceMethod() {
		return this.sourceMethod;
	}

	private String prepareMessage( final String... arguments ) {
		if (arguments.length < 1) return this.errorInfo.getErrorMessage( "<undefined>" + " " + "0" );
		if (arguments.length < 2) return this.errorInfo.getErrorMessage( arguments[0] + " " + "0" );
		return this.errorInfo.getErrorMessage( arguments[0] + " " + arguments[1] );
	}
}
