package org.dimensinfin.eveonline.neocom.infinity.core.exception;

import java.time.Instant;
import java.util.Objects;

public class ApiError {
	private final NeoComRuntimeBackendExceptionObsolete neoComRuntimeBackendException;

	// - C O N S T R U C T O R S
	public ApiError( final NeoComRuntimeBackendExceptionObsolete neoComRuntimeBackendException ) {
		this.neoComRuntimeBackendException = Objects.requireNonNull( neoComRuntimeBackendException );
	}

	// - G E T T E R S   &   S E T T E R S
	public String getCause() {
		return this.neoComRuntimeBackendException.getCauseMessage();
	}

	public String getErrorCode() {
		return this.neoComRuntimeBackendException.getErrorCode();
	}

	public String getErrorName() {
		return this.neoComRuntimeBackendException.getErrorName();
	}

	public String getHttpStatus() {
		return this.neoComRuntimeBackendException.getHttpStatus().toString();
	}

	public Integer getHttpStatusCode() {
		return this.neoComRuntimeBackendException.getHttpStatus().value();
	}

	public String getHttpStatusName() {
		return this.neoComRuntimeBackendException.getHttpStatus().name();
	}

	public String getMessage() {
		return this.neoComRuntimeBackendException.getMessage();
	}

	public Instant getTimestamp() {
		return Instant.now();
	}
}
