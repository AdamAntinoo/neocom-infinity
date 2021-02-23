package org.dimensinfin.eveonline.neocom.infinity.backend.universe.server.rest.v1;

import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.info.BuildProperties;
import org.springframework.stereotype.Service;

import org.dimensinfin.eveonline.neocom.database.repositories.SDERepository;
import org.dimensinfin.eveonline.neocom.infinity.backend.universe.domain.ServerStatus;
import org.dimensinfin.eveonline.neocom.provider.ESIDataProvider;
import org.dimensinfin.eveonline.neocom.service.ESIDataService;

@Service
public class ServerDataServiceV1 {
	private final BuildProperties buildProperties;
	private final ESIDataService esiDataService;
	private final SDERepository sdeRepository;

	// - C O N S T R U C T O R S
	@Autowired
	public ServerDataServiceV1( @NotNull final BuildProperties buildProperties,
	                            @NotNull final ESIDataService esiDataService,
	                            @NotNull final SDERepository sdeRepository ) {
		this.buildProperties = buildProperties;
		this.esiDataService = esiDataService;
		this.sdeRepository = sdeRepository;
	}

	// - G E T T E R S   &   S E T T E R S
	public ServerStatus getServerStatus() {
		return new ServerStatus.Builder()
				.withServer( ESIDataProvider.DEFAULT_ESI_SERVER )
				.withBackendVersion( this.getApplicationVersion() )
				.withSDEVersion( this.sdeRepository.accessSDEVersion() )
				.withStatus( this.esiDataService.getUniverseStatus( ESIDataProvider.DEFAULT_ESI_SERVER ) )
				.build();
	}

	private String getApplicationVersion() {
		return this.buildProperties.getVersion();
	}
}
