package org.dimensinfin.eveonline.neocom.infinity.authorization.client.v1;

import java.util.Objects;
import java.util.Optional;

import org.springframework.stereotype.Component;

import org.dimensinfin.eveonline.neocom.auth.NeoComOAuth2Flow;
import org.dimensinfin.eveonline.neocom.provider.ESIDataProvider;

@Component
public class ValidateAuthorizationTokenRequest {
	private String code;
	private String state;
	private Optional<String> dataSource = Optional.empty();
	private NeoComOAuth2Flow oauthFlow; // This is the flow instance to be used during request processing

	// - C O N S T R U C T O R S
	private ValidateAuthorizationTokenRequest() {}

	// - G E T T E R S   &   S E T T E R S
	public String getCode() {
		return this.code;
	}

	public String getDataSourceName() {
		return this.dataSource.orElse( ESIDataProvider.DEFAULT_ESI_SERVER );
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
		private ValidateAuthorizationTokenRequest onConstruction;

		// - C O N S T R U C T O R S
		public Builder() {
			this.onConstruction = new ValidateAuthorizationTokenRequest();
		}

		public ValidateAuthorizationTokenRequest build() {
			Objects.requireNonNull( this.onConstruction.code );
			Objects.requireNonNull( this.onConstruction.state );
			return this.onConstruction;
		}

		public ValidateAuthorizationTokenRequest.Builder optionalDataSource( final String dataSource ) {
			if (null != dataSource)
				if (!dataSource.isEmpty())
					this.onConstruction.dataSource = Optional.of( dataSource );
			return this;
		}

		public ValidateAuthorizationTokenRequest.Builder withCode( final String code ) {
			Objects.requireNonNull( code );
			this.onConstruction.code = code;
			return this;
		}

		public ValidateAuthorizationTokenRequest.Builder withState( final String state ) {
			Objects.requireNonNull( state );
			this.onConstruction.state = state;
			return this;
		}
	}
}
