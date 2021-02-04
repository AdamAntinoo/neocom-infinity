package org.dimensinfin.eveonline.neocom.infinity.backend.authorization.domain;

import java.util.Objects;

/**
 * @author Adam Antinoo (adamantinoo.git@gmail.com)
 * @since 0.20.0
 */
public class NeoComCookie {
	private String jwtToken;
	private Boolean valid = false;

	// - C O N S T R U C T O R S
	public NeoComCookie() {}

	// - G E T T E R S   &   S E T T E R S
	public String getJwtToken() {
		return this.jwtToken;
	}

	public boolean isValid() {
		return this.valid;
	}

	// - B U I L D E R
	public static class Builder {
		private final NeoComCookie onConstruction;

		// - C O N S T R U C T O R S
		public Builder() {
			this.onConstruction = new NeoComCookie();
		}

		public NeoComCookie build() {
			return this.onConstruction;
		}

		public NeoComCookie.Builder withJWTToken( final String jwtToken ) {
			this.onConstruction.jwtToken = Objects.requireNonNull( jwtToken );
			this.onConstruction.valid = true;
			return this;
		}
	}
}