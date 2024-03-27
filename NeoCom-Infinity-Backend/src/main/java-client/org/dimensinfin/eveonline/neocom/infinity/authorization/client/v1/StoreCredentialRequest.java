package org.dimensinfin.eveonline.neocom.infinity.authorization.client.v1;

import java.util.Objects;

import org.springframework.stereotype.Component;

import org.dimensinfin.eveonline.neocom.database.entities.Credential;

@Component
public class StoreCredentialRequest {
	private Credential credential;

	private StoreCredentialRequest() {}

	public Credential getCredential() {
		return this.credential;
	}

	// - B U I L D E R
	public static class Builder {
		private StoreCredentialRequest onConstruction;

		public Builder() {
			this.onConstruction = new StoreCredentialRequest();
		}

		public StoreCredentialRequest build() {
			Objects.requireNonNull( this.onConstruction.credential );
			return this.onConstruction;
		}

		public StoreCredentialRequest.Builder withCredential( final Credential credential ) {
			Objects.requireNonNull( credential );
			this.onConstruction.credential = credential;
			return this;
		}
	}
}
