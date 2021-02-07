package org.dimensinfin.eveonline.neocom.infinity.support.authorization.converter;

import java.util.Map;

import org.springframework.stereotype.Component;

import org.dimensinfin.eveonline.neocom.infinity.backend.authorization.domain.ValidateAuthorizationTokenRequest;
import org.dimensinfin.eveonline.neocom.infinity.support.CucumberTableToRequestConverter;
import org.dimensinfin.eveonline.neocom.infinity.support.RequestType;

@Component
public class CucumberTableToValidateAuthorizationTokenRequest extends CucumberTableToRequestConverter<ValidateAuthorizationTokenRequest> {
	private static final String CODE = "code";
	private static final String STATE = "state";
	private static final String DATA_SOURCE = "dataSource";

	// - G E T T E R S   &   S E T T E R S
	@Override
	public RequestType getType() {
		return RequestType.VALIDATE_AUTHORIZATION_TOKEN;
	}

	@Override
	public ValidateAuthorizationTokenRequest convert( final Map<String, String> cucumberRow ) {
		return new ValidateAuthorizationTokenRequest.Builder()
				.withCode( cucumberRow.get( CODE ) )
				.withState( cucumberRow.get( STATE ) )
				.withDataSource( cucumberRow.get( DATA_SOURCE ) )
				.build();
	}
}
