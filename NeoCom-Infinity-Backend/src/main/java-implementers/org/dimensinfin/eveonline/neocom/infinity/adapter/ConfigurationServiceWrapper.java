package org.dimensinfin.eveonline.neocom.infinity.adapter;

import java.io.IOException;
import java.text.MessageFormat;
import java.util.Objects;
import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import org.dimensinfin.eveonline.neocom.infinity.adapter.implementers.SBConfigurationService;
import org.dimensinfin.eveonline.neocom.provider.IConfigurationService;
import org.dimensinfin.logging.LogWrapper;

@Component
public class ConfigurationServiceWrapper {
	private static final String DEFAULT_PROPERTIES_DIRECTORY = "properties";
	private String propertiesDirectory;
	private SBConfigurationService singleton;

	@Autowired
	public ConfigurationServiceWrapper( @Value("${P.runtime.configuration.properties.path}") final String configuredLocation ) throws IOException {
		// Set the final configuration path from the properties or from default.
		if (null == configuredLocation) this.propertiesDirectory = DEFAULT_PROPERTIES_DIRECTORY;
		else this.propertiesDirectory = configuredLocation;
	}

	public IConfigurationService getSingleton() {
		return Objects.requireNonNull( this.singleton );
	}

	@PostConstruct
	void build() throws IOException {
		LogWrapper.info( MessageFormat.format( "Configured Properties location: {0}",this.propertiesDirectory ) );
		this.singleton = new SBConfigurationService.Builder()
				.optionalPropertiesDirectory( this.propertiesDirectory )
				.build();
		this.singleton.readAllProperties();
	}
}
