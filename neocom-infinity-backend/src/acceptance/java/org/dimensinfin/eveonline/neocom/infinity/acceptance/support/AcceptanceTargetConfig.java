package org.dimensinfin.eveonline.neocom.infinity.acceptance.support;

import java.util.Objects;

import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AcceptanceTargetConfig implements ITargetConfiguration {
	private static final Integer DEFAULT_BACKEND_PORT = 5275;
	private static final String DEFAULT_BACKEND_SERVER = "http://localhost";
	private static final String DEFAULT_BACKEND_ACCEPTED_CONTENT_TYPE = "application/json";
	private final AcceptanceTargetConfigurationProperties properties;

	@Autowired
	public AcceptanceTargetConfig( final @NotNull AcceptanceTargetConfigurationProperties properties ) {this.properties = properties;}

	public AcceptanceTargetConfig() {this.properties = new AcceptanceTargetConfigurationProperties();}

	// - G E T T E R S   &   S E T T E R S
	@Override
	public String getBackendServer() {
		return (Objects.isNull( this.properties.getServerHost() ) ? DEFAULT_BACKEND_SERVER : this.properties.getServerHost()) +
				":" +
				(Objects.isNull( this.properties.getServerPort() ) ? DEFAULT_BACKEND_PORT : this.properties.getServerPort());
	}

	@Override
	public String getContentType() {
		return (Objects.isNull(
				this.properties.getServerContentType() ) ? DEFAULT_BACKEND_ACCEPTED_CONTENT_TYPE : this.properties.getServerContentType()
		);
	}
}
