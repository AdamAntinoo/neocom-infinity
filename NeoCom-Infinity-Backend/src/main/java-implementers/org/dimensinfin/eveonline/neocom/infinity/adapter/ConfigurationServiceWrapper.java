package org.dimensinfin.eveonline.neocom.infinity.adapter;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import org.dimensinfin.eveonline.neocom.provider.IConfigurationService;

@Component
public class ConfigurationServiceWrapper {
	private static final String DEFAULT_PROPERTIES_DIRECTORY = "properties";
	private String propertiesDirectory;
	private IConfigurationService singleton;

	// - C O N S T R U C T O R S
	//	public ConfigurationServiceWrapper( @Value("${P.runtime.configuration.properties.path}") final String configuredLocation ) throws IOException {
	//		// Set the final configuration path from the properties or from default.
	//		if (null == configuredLocation) this.propertiesDirectory = DEFAULT_PROPERTIES_DIRECTORY;
	//		else this.propertiesDirectory = configuredLocation;
	//	}
	@Autowired
	public ConfigurationServiceWrapper( final IConfigurationService configurationService ) {
		this.singleton = configurationService;
	}

	// - G E T T E R S   &   S E T T E R S
	public IConfigurationService getSingleton() {
		return Objects.requireNonNull( this.singleton );
	}

	//	@PostConstruct
	//	void build() throws IOException {
	//		LogWrapper.info( MessageFormat.format( "Configured Properties location: {0}",this.propertiesDirectory ) );
	//		this.singleton = new SBConfigurationService.Builder()
	//				.optionalPropertiesDirectory( this.propertiesDirectory )
	//				.build();
	////		this.singleton.readAllProperties();
	//	}
}
