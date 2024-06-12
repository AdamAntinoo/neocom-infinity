package org.dimensinfin.eveonline.neocom.infinity.core.exception;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import org.dimensinfin.logging.LogWrapper;

@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
public class GlobalRestExceptionHandler extends ResponseEntityExceptionHandler {
	@ExceptionHandler(NeoComRuntimeBackendExceptionObsolete.class)
	protected ResponseEntity<ApiError> handleNeoComRuntimeException( final NeoComRuntimeBackendExceptionObsolete neoComRuntimeBackendException ) {
		return new ResponseEntity<>( new ApiError( neoComRuntimeBackendException ), neoComRuntimeBackendException.getHttpStatus() );
	}

	@ExceptionHandler(Exception.class)
	protected ResponseEntity<String> handleNeoComRuntimeException( final Exception exception ) {
		return new ResponseEntity<>( exception.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR );
	}

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid( final MethodArgumentNotValidException ex, final HttpHeaders headers, final HttpStatus status, final WebRequest request ) {
		LogWrapper.info( "Exception: " + ex.toString() );
		LogWrapper.info( "message: " + ex.getMessage() );
		return new ResponseEntity<>(
				new ApiError( new NeoComRuntimeBackendExceptionObsolete( NeoComRuntimeBackendExceptionObsolete.errorINVALIDREQUESTSTRUCTURE( ex ) ) ),
				HttpStatus.BAD_REQUEST );
	}
}
