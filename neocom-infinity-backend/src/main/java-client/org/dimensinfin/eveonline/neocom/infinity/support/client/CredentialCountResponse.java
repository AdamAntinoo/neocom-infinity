package org.dimensinfin.eveonline.neocom.infinity.support.client;

import java.util.Objects;

public class CredentialCountResponse {
	private Integer credentialCount;

	private CredentialCountResponse() {}

	public Integer getCredentialCount() {
		return this.credentialCount;
	}

	// - B U I L D E R
	public static class Builder {
		private CredentialCountResponse onConstruction;

		public Builder() {
			this.onConstruction = new CredentialCountResponse();
		}

		public CredentialCountResponse build() {
			return this.onConstruction;
		}

		public CredentialCountResponse.Builder withCredentialCount( final Integer credentialCount ) {
			Objects.requireNonNull( credentialCount );
			this.onConstruction.credentialCount = credentialCount;
			return this;
		}
	}
}