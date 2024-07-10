package org.dimensinfin.eveonline.neocom.infinity.domain.exceptions;

import java.text.MessageFormat;
import javax.print.attribute.standard.Severity;

import org.springframework.http.HttpStatus;

import lombok.Getter;

@Getter
public enum NeoComExceptionCatalog {
	// - G E N E R I C
	UNEXPECTED_EXCEPTION(
			Severity.ERROR,
			HttpStatus.INTERNAL_SERVER_ERROR,
			ExceptionErrorCodes.UNEXPECTED_ERROR.name(),
			NeoComExceptionCatalog.UNEXPECTED_EXCEPTION_MESSAGE
	),
	INVALID_REQUEST_PARAMETER(
			Severity.ERROR,
			HttpStatus.BAD_REQUEST,
			ExceptionErrorCodes.BAD_REQUEST.name(),
			NeoComExceptionCatalog.INVALID_REQUEST_PARAMETER_MESSAGE
	),
	// - N O T   F O U N D
	BLUEPRINT_NOT_FOUND(
			Severity.ERROR,
			HttpStatus.NOT_FOUND,
			ExceptionErrorCodes.NOT_FOUND.name(),
			NeoComExceptionCatalog.BLUEPRINT_NOT_FOUND_MESSAGE
	),
	PILOT_ACCESS_NOT_AUTHORIZED(
			Severity.ERROR,
			HttpStatus.FORBIDDEN,
			ExceptionErrorCodes.PILOT_ACCESS_NOT_AUTHORIZED.name(),
			NeoComExceptionCatalog.PILOT_ACCESS_NOT_AUTHORIZED_MESSAGE
	);

	private final Severity severity;
	private final HttpStatus status;
	private final String code;
	private final String messageTemplate;

	NeoComExceptionCatalog( final Severity severity, final HttpStatus status, final String code, final String messageTemplate ) {
		this.severity = severity;
		this.status = status;
		this.code = code;
		this.messageTemplate = messageTemplate;
	}

	public static NeoComExceptionCatalog get( final String code ) {
		for (NeoComExceptionCatalog e : values()) {
			if ( e.code.equals( code ) ) return e;
		}
		return UNEXPECTED_EXCEPTION;
	}

	public String getResolvedMessage( final Object... arguments ) {
		return MessageFormat.format( this.getMessageTemplate(), arguments );
	}

	private String getMessageTemplate() {
		return this.messageTemplate;
	}

	// - C O D E S   D E F I N I T I O N S
	public enum ExceptionErrorCodes {
		UNEXPECTED_ERROR,
		BAD_REQUEST,
		NOT_FOUND,
		PILOT_ACCESS_NOT_AUTHORIZED
	}

	// - E X C E P T I O N   M E S S A G E S
	private static final String UNEXPECTED_EXCEPTION_MESSAGE = "";
	private static final String INVALID_REQUEST_PARAMETER_MESSAGE = "";
	private static final String BLUEPRINT_NOT_FOUND_MESSAGE = "";
	private static final String PILOT_ACCESS_NOT_AUTHORIZED_MESSAGE = "The access to the pilot data is not authorized to the requester credential.";
}
