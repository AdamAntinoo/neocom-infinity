package org.dimensinfin.eveonline.neocom.infinity.core.exceptions;

import java.text.MessageFormat;

import org.springframework.http.HttpStatus;

public enum ErrorInfo {
	NEOCOM_EXCEPTION( HttpStatus.BAD_REQUEST,
			"neocom.error.neocomexception",
			"NeoCom.Exception" ),
	NOT_INTERCEPTED_EXCEPTION( HttpStatus.BAD_REQUEST,
			"neocom.error.unexpected",
			"Not intercepted exception. Reporting java native exception." ),
	AUTHORIZATION_TRANSLATION( HttpStatus.BAD_REQUEST,
			"neocom.error.authorization.translation",
			"TokenTranslationResponse response is not valid." ),
	//	NOT_MATCHING_AUTHORIZATION_REQUEST( HttpStatus.UNAUTHORIZED,
	//			"neocom.error.authorization.validation",
	//			"Unauthorized request because a field does not match the validation procedures." ),
	VERIFICATION_RESPONSE( HttpStatus.BAD_REQUEST,
			"neocom.error.authorization.verification",
			"VerifyCharacterResponse response is not valid." ),
	INVALID_CREDENTIAL_IDENTIFIER( HttpStatus.BAD_REQUEST,
			"neocom.error.authorization.verification",
			"The validation character response is not valid and then the unique character identifier is not found." ),
	CORPORATION_ID_NOT_AUTHORIZED( HttpStatus.FORBIDDEN,
			"neocom.error.authorization.translation",
			"The corporation requested is not authorized to the requester." ),
	PILOT_ID_NOT_AUTHORIZED( HttpStatus.FORBIDDEN,
			"neocom.error.authorization.translation",
			"The access to the pilot data is not authorized to the requester credential." ),
	TARGET_NOT_FOUND( HttpStatus.NOT_FOUND,
			"neocom.error.authorization.translation",
			"The entity of class {0} with identifier {1} is not found." ),
	ESI_DATA_NOT_FOUND( HttpStatus.NOT_FOUND,
			"neocom.error.data.nonexistent",
			"The requested data of class {0} was not found on the ESI service." ),
	CREDENTIAL_DATA_NOT_MATCHING( HttpStatus.UNAUTHORIZED,
			"neocom.error.authorization",
			"The credential request parameters do not match. Invalid credential." ),
	CREDENTIAL_STORE_REPOSITORY_FAILURE( HttpStatus.INTERNAL_SERVER_ERROR,
			"neocom.error.credential.repository.sql.error",
			"The credential was not saved at the repository - {0}" );

	public final HttpStatus status;
	public final String errorCode;
	public final String errorMessage;

	// - C O N S T R U C T O R S
	ErrorInfo( final HttpStatus status, final String errorCode, final String errorMessage ) {
		this.status = status;
		this.errorCode = errorCode;
		this.errorMessage = errorMessage;
	}

	@Deprecated
	public String getErrorMessage( final String arguments ) {
		return MessageFormat.format( this.errorMessage, arguments );
	}
}
