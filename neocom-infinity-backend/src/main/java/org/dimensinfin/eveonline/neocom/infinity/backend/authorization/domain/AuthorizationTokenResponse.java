package org.dimensinfin.eveonline.neocom.infinity.backend.authorization.domain;

import java.util.Objects;
import javax.servlet.http.Cookie;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import org.dimensinfin.eveonline.neocom.database.entities.Credential;
import org.dimensinfin.eveonline.neocom.infinity.core.client.v0.NeoComResponse;

@JsonIgnoreProperties(ignoreUnknown = true)
public class AuthorizationTokenResponse extends NeoComResponse {
	private String jwtToken;
	private Credential credential;
	private Cookie cookie;

	// - C O N S T R U C T O R S
	private AuthorizationTokenResponse() {}

	// - G E T T E R S   &   S E T T E R S
	public Cookie getCookie() {
		return this.cookie;
	}

	public Credential getCredential() {
		return this.credential;
	}

	public String getJwtToken() {
		return this.jwtToken;
	}

	// - B U I L D E R
	public static class Builder {
		private final AuthorizationTokenResponse onConstruction;

		// - C O N S T R U C T O R S
		public Builder() {
			this.onConstruction = new AuthorizationTokenResponse();
		}

		public AuthorizationTokenResponse build() {
			return this.onConstruction;
		}

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
	}
}
