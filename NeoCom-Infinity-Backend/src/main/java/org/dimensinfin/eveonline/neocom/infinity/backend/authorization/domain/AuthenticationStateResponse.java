package org.dimensinfin.eveonline.neocom.infinity.backend.authorization.domain;

/**
 * @author Adam Antinoo (adamantinoo.git@gmail.com)
 * @since 0.20.0
 */
public class AuthenticationStateResponse {
	public enum AuthenticationStateType {
		NOT_FOUND( "not found" ),
		NOT_VALID( "not valid" ),
		VALID( "valid" );
		private final String message;

		// - C O N S T R U C T O R S
		AuthenticationStateType( final String message ) {
			this.message = message;
		}

		// - G E T T E R S   &   S E T T E R S
		public String getMessage() {
			return this.message;
		}
	}

	private AuthenticationStateType state = AuthenticationStateType.NOT_VALID;

	// - C O N S T R U C T O R S
	private AuthenticationStateResponse() {}

	// - G E T T E R S   &   S E T T E R S
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
			return this.onConstruction;
		}

		public AuthenticationStateResponse.Builder withState( final AuthenticationStateType state ) {
			this.onConstruction.state = state;
			return this;
		}
	}
}