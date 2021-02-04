package org.dimensinfin.eveonline.neocom.infinity.backend.authorization.domain;

/**
 * @author Adam Antinoo (adamantinoo.git@gmail.com)
 * @since 0.20.0
 */
public class CookieStateResponse {
	public enum SessionStateType {
		NOT_FOUND( "not found" ),
		NOT_VALID( "not valid" ),
		VALID( "valid" );
		private final String message;

		// - C O N S T R U C T O R S
		SessionStateType( final String message ) {
			this.message = message;
		}

		// - G E T T E R S   &   S E T T E R S
		public String getMessage() {
			return this.message;
		}
	}

	private SessionStateType state = SessionStateType.NOT_VALID;

	// - C O N S T R U C T O R S
	private CookieStateResponse() {}

	// - G E T T E R S   &   S E T T E R S
	public SessionStateType getState() {
		return this.state;
	}

	// - B U I L D E R
	public static class Builder {
		private final CookieStateResponse onConstruction;

		// - C O N S T R U C T O R S
		public Builder() {
			this.onConstruction = new CookieStateResponse();
		}

		public CookieStateResponse build() {
			return this.onConstruction;
		}

		public CookieStateResponse.Builder withState( final SessionStateType state ) {
			this.onConstruction.state = state;
			return this;
		}
	}
}