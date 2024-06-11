package org.dimensinfin.eveonline.neocom.infinity.domain.exceptions;

import org.springframework.http.HttpStatus;

import org.dimensinfin.eveonline.neocom.exception.NeoComRuntimeException;

public class NeoComRuntimeHttpException extends NeoComRuntimeException {
	private static final long serialVersionUID = -5119600231618946789L;
	private final NeoComExceptionCatalog cataloguedException;
	private final transient Object[] messageArguments;

	// - C O N S T R U C T O R S
	public NeoComRuntimeHttpException( final NeoComExceptionCatalog cataloguedException, final Object... messageArguments ) {
		super( cataloguedException.getResolvedMessage( messageArguments ) );
		this.cataloguedException = cataloguedException;
		this.messageArguments = messageArguments;
	}

	// - G E T T E R S   &   S E T T E R S
	public String getErrorCode() {
		return this.cataloguedException.getCode();
	}

	public HttpStatus getHttpStatus() {
		return this.cataloguedException.getStatus();
	}

	public int getHttpStatusCode() {
		return this.getHttpStatus().value();
	}

	public String getHttpStatusName() {
		return this.getHttpStatus().name();
	}

	public String getMessage() {
		return this.cataloguedException.getResolvedMessage( this.messageArguments );
	}
}
