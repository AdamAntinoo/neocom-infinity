package org.dimensinfin.eveonline.neocom.infinity.acceptance.support.authorization.rest.v1;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import org.dimensinfin.eveonline.neocom.infinity.authorization.client.v1.ValidateAuthorizationTokenResponse;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ValidateAuthorizationTokenResponseClient {
	public Object headers;
	public ValidateAuthorizationTokenResponse body;
	public String statusCode;
	public Integer statusCodeValue;

// - G E T T E R S   &   S E T T E R S
	public ValidateAuthorizationTokenResponse getBody() {
		return this.body;
	}

	public ValidateAuthorizationTokenResponseClient setBody( final ValidateAuthorizationTokenResponse body ) {
		this.body = body;
		return this;
	}

	public Object getHeaders() {
		return this.headers;
	}

	public ValidateAuthorizationTokenResponseClient setHeaders( final Object headers ) {
		this.headers = headers;
		return this;
	}

	public String getStatusCode() {
		return this.statusCode;
	}

	public ValidateAuthorizationTokenResponseClient setStatusCode( final String statusCode ) {
		this.statusCode = statusCode;
		return this;
	}

	public Integer getStatusCodeValue() {
		return this.statusCodeValue;
	}

	public ValidateAuthorizationTokenResponseClient setStatusCodeValue( final Integer statusCodeValue ) {
		this.statusCodeValue = statusCodeValue;
		return this;
	}
}