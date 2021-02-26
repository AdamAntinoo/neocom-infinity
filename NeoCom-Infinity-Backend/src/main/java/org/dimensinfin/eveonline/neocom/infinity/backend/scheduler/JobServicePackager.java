package org.dimensinfin.eveonline.neocom.infinity.backend.scheduler;

import javax.validation.constraints.NotNull;

import org.springframework.stereotype.Component;

import org.dimensinfin.eveonline.neocom.database.repositories.SDERepository;
import org.dimensinfin.eveonline.neocom.infinity.backend.scheduler.config.SchedulerConfiguration;
import org.dimensinfin.eveonline.neocom.infinity.service.DataStoreService;
import org.dimensinfin.eveonline.neocom.provider.IConfigurationService;
import org.dimensinfin.eveonline.neocom.service.ESIDataService;
import org.dimensinfin.eveonline.neocom.service.ResourceFactory;

/**
 * @author Adam Antinoo (adamantinoo.git@gmail.com)
 * @since 0.20.0
 */
@Component
public class JobServicePackager {
	private final IConfigurationService configurationService;
	private final SchedulerConfiguration schedulerConfiguration;
	private final ESIDataService esiDataService;
	private final SDERepository sdeRepository;
	private final DataStoreService dataStoreService;
	private final ResourceFactory resourceFactory;

	// - C O N S T R U C T O R S
	private JobServicePackager( @NotNull final IConfigurationService configurationService,
	                            @NotNull final SchedulerConfiguration schedulerConfiguration,
	                            @NotNull final ESIDataService esiDataService,
	                            @NotNull final SDERepository sdeRepository,
	                            @NotNull final DataStoreService dataStoreService,
	                            @NotNull final ResourceFactory resourceFactory ) {
		this.configurationService = configurationService;
		this.schedulerConfiguration = schedulerConfiguration;
		this.esiDataService = esiDataService;
		this.sdeRepository = sdeRepository;
		this.dataStoreService = dataStoreService;
		this.resourceFactory = resourceFactory;
	}

	// - G E T T E R S   &   S E T T E R S
	public IConfigurationService getConfigurationService() {
		return this.configurationService;
	}

	public DataStoreService getDataStoreService() {
		return this.dataStoreService;
	}

	public ESIDataService getEsiDataService() {
		return this.esiDataService;
	}

	public ResourceFactory getResourceFactory() {
		return this.resourceFactory;
	}

	public SchedulerConfiguration getSchedulerConfiguration() {
		return this.schedulerConfiguration;
	}

	public SDERepository getSdeRepository() {
		return this.sdeRepository;
	}
}