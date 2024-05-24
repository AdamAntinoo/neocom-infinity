package org.dimensinfin.eveonline.neocom.infinity.backend.authorization.domain;

import java.util.Objects;

import org.dimensinfin.eveonline.neocom.database.entities.Credential;

/**
 * @author Adam Antinoo (adamantinoo.git@gmail.com)
 * @since 0.20.0
 */
public class AuthenticationStateResponse {
	public enum AuthenticationStateType {
		NOT_FOUND, NOT_VALID, VALID;
	}

	private AuthenticationStateType state = AuthenticationStateType.NOT_VALID;
	private String jwtToken;
	private String esiToken;
	private Credential credential;

	// - C O N S T R U C T O R S
	private AuthenticationStateResponse() {}

	// - G E T T E R S   &   S E T T E R S
	public Credential getCredential() {
		return this.credential;
	}

	public String getJwtToken() {
		return this.jwtToken;
	}

	public String getEsiToken() {
		return this.esiToken;
	}

	public AuthenticationStateType getState() {
		return this.state;
	}

	// - B U I L D E R
	public static class Builder {
		private final AuthenticationStateResponse onConstruction;

		// - C O N S T R U C T O R S
		public Builder() {
			this.onConstruction = new AuthenticationStateResponse();
		}

		public AuthenticationStateResponse build() {
			// TODO - Add mandatory fields validation
			return this.onConstruction;
		}

		public AuthenticationStateResponse.Builder withCredential( final Credential credential ) {
			this.onConstruction.credential = Objects.requireNonNull( credential );
			return this;
		}

		public AuthenticationStateResponse.Builder withJwtToken( final String jwtToken ) {
			this.onConstruction.jwtToken = Objects.requireNonNull( jwtToken );
			return this;
		}

		public AuthenticationStateResponse.Builder withEsiToken( final String token ) {
			this.onConstruction.esiToken = Objects.requireNonNull( token );
			return this;
		}

		public AuthenticationStateResponse.Builder withState( final AuthenticationStateType state ) {
			this.onConstruction.state = state;
			return this;
		}
	}
}
