package org.dimensinfin.eveonline.neocom.infinity.authorization.client.v1;

import java.util.Objects;

public class StoreCredentialResponse {
	private String jwtToken;

	private StoreCredentialResponse() {}

	public String getJwtToken() {
		return jwtToken;
	}

	// - B U I L D E R
	public static class Builder {
		private StoreCredentialResponse onConstruction;

		public Builder() {
			this.onConstruction = new StoreCredentialResponse();
		}

		public StoreCredentialResponse build() {
			Objects.requireNonNull( this.onConstruction.jwtToken );
			return this.onConstruction;
		}

		public StoreCredentialResponse.Builder withJwtToken( final String jwtToken ) {
			Objects.requireNonNull( jwtToken );
			this.onConstruction.jwtToken = jwtToken;
			return this;
		}
	}
}