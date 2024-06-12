package org.dimensinfin.eveonline.neocom.infinity.acceptance.support;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ActiveProfiles;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Configuration
@ConfigurationProperties("acceptance.server")
//@Validated
@ActiveProfiles("acceptance")
public class AcceptanceTargetConfigurationProperties {
	private Integer serverPort;
	private String serverHost;
	private String serverContentType;
}
