package acceptance.support;

import java.util.UUID;

import org.springframework.http.HttpStatus;

import org.dimensinfin.eveonline.neocom.infinity.core.exception.NeoComRuntimeBackendException;

public class CommonWorld {
	private HttpStatus httpStatus;
	private String jwtAuthorizationToken;
	private NeoComRuntimeBackendException applicationException;
	@Deprecated
	private UUID id;

	// - G E T T E R S   &   S E T T E R S
	public NeoComRuntimeBackendException getApplicationException() {
		return this.applicationException;
	}

	public CommonWorld setApplicationException( final NeoComRuntimeBackendException applicationException ) {
		this.applicationException = applicationException;
		return this;
	}

	public HttpStatus getHttpStatus() {
		return this.httpStatus;
	}

	public CommonWorld setHttpStatus( final HttpStatus httpStatus ) {
		this.httpStatus = httpStatus;
		return this;
	}

	public UUID getId() {
		return this.id;
	}

	public CommonWorld setId( final UUID id ) {
		this.id = id;
		return this;
	}

	public String getJwtAuthorizationToken() {
		return this.jwtAuthorizationToken;
	}

	public CommonWorld setJwtAuthorizationToken( final String jwtAuthorizationToken ) {
		this.jwtAuthorizationToken = jwtAuthorizationToken;
		return this;
	}
}
