package org.dimensinfin.eveonline.neocom.infinity.core.exception;

import java.util.Objects;

import org.springframework.http.HttpStatus;

@Deprecated
public class NeoComSBException extends RuntimeException {
	protected final String sourceClass;
	protected final String sourceMethod;
	protected ErrorInfo errorInfo;
	protected Exception rootException;

	protected NeoComSBException() {
		final StackTraceElement[] stacktrace = Thread.currentThread().getStackTrace();
		final StackTraceElement stackElement = stacktrace[3]; // This is to check if we are using Dalvik
		this.sourceMethod = stackElement.getMethodName();
		this.sourceClass = stackElement.getClassName();
	}

	public NeoComSBException( final String message ) {
		super( message );
		this.errorInfo = ErrorInfo.NEOCOM_EXCEPTION;
		final StackTraceElement[] stacktrace = Thread.currentThread().getStackTrace();
		final StackTraceElement stackElement = stacktrace[3]; // This is to check if we are using Dalvik
		this.sourceMethod = stackElement.getMethodName();
		this.sourceClass = stackElement.getClassName();
	}

	public NeoComSBException( final ErrorInfo errorInfo ) {
		this();
		this.errorInfo = errorInfo;
	}

	public NeoComSBException( final Exception rootException ) {
		this();
		this.errorInfo = ErrorInfo.NOT_INTERCEPTED_EXCEPTION;
		this.rootException = rootException;
	}

	public NeoComSBException( final ErrorInfo errorInfo, final Exception rootException ) {
		this( errorInfo );
		this.rootException = rootException;
	}

	public String getMessage() {
		try {
			String message = Objects.requireNonNull( this.errorInfo ).errorMessage;
			if ((null != super.getMessage()) && (!super.getMessage().isEmpty()))
				message = message.concat( ":" ).concat( super.getMessage() );
			if (null != this.rootException)
				message = message.concat( ":" ).concat( this.rootException.getMessage() );
			return message;
		} catch (final NullPointerException npe) {
			String message = "Malformed NeoCom exception. 'errorInfo' is null.";
			if ((null != super.getMessage()) && (!super.getMessage().isEmpty()))
				message = message.concat( ":" ).concat( super.getMessage() );
			if (null != this.rootException)
				message = message.concat( ":" ).concat( this.rootException.getMessage() );
			return message;
		}
	}

	public String getSourceClass() {
		return this.sourceClass;
	}

	public String getSourceMethod() {
		return this.sourceMethod;
	}

	public Exception getRootException() {
		return this.rootException;
	}

	public HttpStatus getHttpStatus() {
		return this.errorInfo.status;
	}
//
//	public String getExceptionType() {
//		if (null != this.rootException) return this.rootException.getClass().getSimpleName();
//		else return "NeoComSBException";
//	}
}
