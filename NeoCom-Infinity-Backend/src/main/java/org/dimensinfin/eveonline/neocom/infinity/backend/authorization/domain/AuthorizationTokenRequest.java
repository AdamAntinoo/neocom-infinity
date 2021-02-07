package org.dimensinfin.eveonline.neocom.infinity.backend.authorization.domain;

import java.util.Objects;

import org.springframework.stereotype.Component;

import org.dimensinfin.eveonline.neocom.auth.NeoComOAuth2Flow;

import static org.dimensinfin.eveonline.neocom.provider.ESIDataProvider.DEFAULT_ESI_SERVER;

@Component
public class AuthorizationTokenRequest {
	private String code;
	private String state;
	private String dataSource = DEFAULT_ESI_SERVER;
	private NeoComOAuth2Flow oauthFlow; // This is the flow instance to be used during request processing

	// - C O N S T R U C T O R S
	private AuthorizationTokenRequest() {}

	// - G E T T E R S   &   S E T T E R S
	public String getCode() {
		return this.code;
	}

	public String getDataSourceName() {
		return this.dataSource;
	}

	public NeoComOAuth2Flow getOauthFlow() {
		return this.oauthFlow;
	}

	public String getState() {
		return this.state;
	}

	public AuthorizationTokenRequest setRunningFlow( final NeoComOAuth2Flow oauthFlow ) {
		this.oauthFlow = oauthFlow;
		return this;
	}

	// - B U I L D E R
	public static class Builder {
		private final AuthorizationTokenRequest onConstruction;

		// - C O N S T R U C T O R S
		public Builder() {
			this.onConstruction = new AuthorizationTokenRequest();
		}

		public AuthorizationTokenRequest build() {
			Objects.requireNonNull( this.onConstruction.code );
			Objects.requireNonNull( this.onConstruction.state );
			Objects.requireNonNull( this.onConstruction.dataSource );
			return this.onConstruction;
		}

		public AuthorizationTokenRequest.Builder withCode( final String code ) {
			Objects.requireNonNull( code );
			this.onConstruction.code = code;
			return this;
		}

		public AuthorizationTokenRequest.Builder withDataSource( final String dataSource ) {
			if (null != dataSource) this.onConstruction.dataSource = dataSource;
			return this;
		}

		public AuthorizationTokenRequest.Builder withState( final String state ) {
			Objects.requireNonNull( state );
			this.onConstruction.state = state;
			return this;
		}
	}
}
