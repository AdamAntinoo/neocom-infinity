package org.dimensinfin.eveonline.neocom.infinity.core.exception;

import java.sql.SQLException;
import java.text.MessageFormat;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;

import org.dimensinfin.eveonline.neocom.exception.NeoComRuntimeException;
import org.dimensinfin.eveonline.neocom.infinity.client.core.dto.RestExceptionResponse;

public class NeoComRuntimeBackendException extends NeoComRuntimeException {
	private static final long serialVersionUID = -5119600231618946788L;

	public static NeoComRestError errorRUNTIMEINTERNALERROR( final String message ) {
		return new NeoComRestError.Builder()
				.withErrorName( "RUNTIME_INTERNAL_ERROR" )
				.withHttpStatus( HttpStatus.INTERNAL_SERVER_ERROR )
				.withErrorCode( "dimensinfin.uncatalogued.runtime" )
				.withMessage( MessageFormat.format( "Runtime uncatalogued exception: {0}", message ) )
				.build();
	}

	public static NeoComRestError errorINITIALIZATIONEXCEPTION( final Exception exception ) {
		return new NeoComRestError.Builder()
				.withErrorName( "INITIALIZATION_INTERNAL_ERROR" )
				.withHttpStatus( HttpStatus.INTERNAL_SERVER_ERROR )
				.withErrorCode( "dimensinfin.initialization.runtime" )
				.withMessage( MessageFormat.format( "Initialization exception: {0}", exception.getMessage() ) )
				.build();
	}

	public static NeoComRestError errorINVALIDREQUESTSTRUCTURE( final MethodArgumentNotValidException restException ) {
		return new NeoComRestError.Builder()
				.withErrorName( "INVALID_REQUEST_STRUCTURE" )
				.withHttpStatus( HttpStatus.BAD_REQUEST )
				.withErrorCode( "dimensinfin.request.validation" )
				.withMessage( MessageFormat.format( "The request is not valid. {0}", restException.getMessage() ) )
				.build();
	}

	public static NeoComRestError errorUNEXPECTEDSQLEXCEPTION( final SQLException sqlException ) {
		return new NeoComRestError.Builder()
				.withErrorName( "UNEXPECTED_SQL_EXCEPTION" )
				.withErrorCode( "org.dimensinfin.eveonline.neocom.infinity.sql.exception" )
				.withHttpStatus( HttpStatus.INTERNAL_SERVER_ERROR )
				.withMessage( MessageFormat.format( "There is an unexpected SQL exception: Message [{0}].",
						sqlException.getMessage() ) )
				.build();
	}


	private final String errorName;
	private final String errorCode;
	private final String causeMessage;
	private final String message;
	private final HttpStatus httpStatus;

	// - C O N S T R U C T O R S
	public NeoComRuntimeBackendException( final String errorMessage ) {
		this( errorRUNTIMEINTERNALERROR( errorMessage ) );
	}

	public NeoComRuntimeBackendException( final NeoComRestError error ) {
		this.errorName = error.getErrorName();
		this.errorCode = error.getErrorCode();
		this.causeMessage = error.getCause();
		this.message = error.getMessage();
		this.httpStatus = error.getStatus();
	}

	public NeoComRuntimeBackendException( final RestExceptionResponse exceptionResponse ) {
		this.errorName = exceptionResponse.getErrorName();
		this.errorCode = exceptionResponse.getErrorCode();
		this.causeMessage = exceptionResponse.getCause();
		this.message = exceptionResponse.getMessage();
		this.httpStatus = HttpStatus.valueOf( exceptionResponse.getHttpStatusCode() );
	}

	public NeoComRuntimeBackendException( final NeoComRestError error, final String cause ) {
		this.errorName = error.getErrorName();
		this.errorCode = error.getErrorCode();
		this.causeMessage = cause;
		this.message = error.getMessage();
		this.httpStatus = error.getStatus();
	}

	// - G E T T E R S   &   S E T T E R S
	public String getCauseMessage() {
		return this.causeMessage;
	}

	public String getErrorCode() {
		return this.errorCode;
	}

	public String getErrorName() {
		return this.errorName;
	}

	public HttpStatus getHttpStatus() {
		return this.httpStatus;
	}

	public int getHttpStatusCode() {
		return this.httpStatus.value();
	}

	public String getHttpStatusName() {
		return this.httpStatus.name();
	}

	@Override
	public String getMessage() {
		return this.message;
	}
}
