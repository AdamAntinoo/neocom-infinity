package org.dimensinfin.eveonline.neocom.infinity.authorization.client.v1;

import java.util.Objects;

import org.springframework.stereotype.Component;

import org.dimensinfin.eveonline.neocom.auth.NeoComOAuth2Flow;

import static org.dimensinfin.eveonline.neocom.provider.ESIDataProvider.DEFAULT_ESI_SERVER;

@Component
public class ValidateAuthorizationTokenRequest {
	private String code;
	private String state;
	private String dataSource = DEFAULT_ESI_SERVER;
	private NeoComOAuth2Flow oauthFlow; // This is the flow instance to be used during request processing

	// - C O N S T R U C T O R S
	private ValidateAuthorizationTokenRequest() {}

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

	public void setRunningFlow( final NeoComOAuth2Flow oauthFlow ) {
		this.oauthFlow = oauthFlow;
	}

	// - B U I L D E R
	public static class Builder {
		private final ValidateAuthorizationTokenRequest onConstruction;

		// - C O N S T R U C T O R S
		public Builder() {
			this.onConstruction = new ValidateAuthorizationTokenRequest();
		}

		public ValidateAuthorizationTokenRequest build() {
			Objects.requireNonNull( this.onConstruction.code );
			Objects.requireNonNull( this.onConstruction.state );
			Objects.requireNonNull( this.onConstruction.dataSource );
			return this.onConstruction;
		}

		public ValidateAuthorizationTokenRequest.Builder withCode( final String code ) {
			Objects.requireNonNull( code );
			this.onConstruction.code = code;
			return this;
		}

		public ValidateAuthorizationTokenRequest.Builder withDataSource( final String dataSource ) {
			if (null != dataSource) this.onConstruction.dataSource = dataSource;
			return this;
		}

		public ValidateAuthorizationTokenRequest.Builder withState( final String state ) {
			Objects.requireNonNull( state );
			this.onConstruction.state = state;
			return this;
		}
	}
}
