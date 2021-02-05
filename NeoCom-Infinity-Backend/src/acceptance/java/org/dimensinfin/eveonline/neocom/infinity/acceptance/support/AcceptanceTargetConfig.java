package org.dimensinfin.eveonline.neocom.infinity.acceptance.support;

import org.springframework.stereotype.Component;

import org.dimensinfin.logging.LogWrapper;

@Component
public class AcceptanceTargetConfig implements ITargetConfiguration {
	private static final String DEFAULT_BACKEND_PORT = System.getenv( "DEFAULT_BACKEND_PORT" );
	private static final String DEFAULT_BACKEND_ACCEPTED_CONTENT_TYPE = "application/json";
	private static String DEFAULT_BACKEND_SERVER = System.getenv( "DEFAULT_BACKEND_SERVER" );

	// - G E T T E R S   &   S E T T E R S
	@Override
	public String getBackendServer() {
		if (null == DEFAULT_BACKEND_PORT) DEFAULT_BACKEND_SERVER = "http://localhost";
		try {
			final int port = Integer.parseInt( DEFAULT_BACKEND_PORT );
			LogWrapper.info( DEFAULT_BACKEND_SERVER + ":" + port );
			return DEFAULT_BACKEND_SERVER + ":" + port;
		} catch (final RuntimeException rte) {
			LogWrapper.info( DEFAULT_BACKEND_SERVER + ":" + 5240 );
			return DEFAULT_BACKEND_SERVER + ":" + 5240;
		}
	}

	@Override
	public String getContentType() {
		return DEFAULT_BACKEND_ACCEPTED_CONTENT_TYPE;
	}
}
