package org.dimensinfin.eveonline.neocom.infinity.backend.authorization.domain;

import java.util.Objects;
import javax.servlet.http.Cookie;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.http.HttpStatus;

import org.dimensinfin.eveonline.neocom.database.entities.Credential;
import org.dimensinfin.eveonline.neocom.infinity.core.client.v0.NeoComResponse;
import org.dimensinfin.eveonline.neocom.infinity.core.exception.NeoComRestError;
import org.dimensinfin.eveonline.neocom.infinity.core.exception.NeoComRuntimeBackendExceptionObsolete;

import lombok.Getter;
import static org.dimensinfin.eveonline.neocom.infinity.infrastructure.adapters.inbound.industryApi.IndustryControllerV2.INDUSTRY_ERROR_CODE_PREFIX;

@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
public class AuthorizationTokenResponse extends NeoComResponse {
	public static NeoComRestError Error_MISSINGMANDATORYBUILDPARAMETER( final String message ) {
		return new NeoComRestError.Builder()
			.withErrorName( "MISSING_BUILD_MANDATORY_PARAMETER" )
			.withHttpStatus( HttpStatus.INTERNAL_SERVER_ERROR )
			.withErrorCode( INDUSTRY_ERROR_CODE_PREFIX + ".build.missing.parameter" )
			.withMessage( message )
			.build();
	}

	private String jwtToken;
	private String esiToken;
	private Credential credential;
	@Deprecated
	private Cookie cookie;

	// - C O N S T R U C T O R S
	private AuthorizationTokenResponse() {
	}


	// - G E T T E R S   &   S E T T E R S
	@Deprecated
	public Cookie getCookie() {
		return this.cookie;
	}

	public Credential getCredential() {
		return this.credential;
	}

	public String getJwtToken() {
		return this.jwtToken;
	}

	public String getEsiToken() {
		return this.esiToken;
	}

	// - B U I L D E R
	public static class Builder {
		private final AuthorizationTokenResponse onConstruction;

		// - C O N S T R U C T O R S
		public Builder() {
			this.onConstruction = new AuthorizationTokenResponse();
		}

		public AuthorizationTokenResponse build() {
			if (Objects.isNull( this.onConstruction.jwtToken ))
				throw new NeoComRuntimeBackendExceptionObsolete( "Missing jwtToken" );
			if (Objects.isNull( this.onConstruction.esiToken ))
				throw new NeoComRuntimeBackendExceptionObsolete( "Missing esiToken" );
			return this.onConstruction;
		}

		@Deprecated
		public AuthorizationTokenResponse.Builder withCookie( final Cookie cookie ) {
			this.onConstruction.cookie = Objects.requireNonNull( cookie );
			return this;
		}

		public AuthorizationTokenResponse.Builder withCredential( final Credential credential ) {
			this.onConstruction.credential = Objects.requireNonNull( credential );
			return this;
		}

		public AuthorizationTokenResponse.Builder withJwtToken( final String jwtToken ) {
			this.onConstruction.jwtToken = Objects.requireNonNull( jwtToken );
			return this;
		}

		public AuthorizationTokenResponse.Builder withEsiToken( final String jwtToken ) {
			this.onConstruction.esiToken = Objects.requireNonNull( jwtToken );
			return this;
		}
	}
}
