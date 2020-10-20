package org.dimensinfin.eveonline.neocom.infinity.backend.universe.spacelocations.rest.v1;

import java.util.Objects;
import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.dimensinfin.eveonline.neocom.adapter.LocationCatalogService;
import org.dimensinfin.eveonline.neocom.domain.space.SpaceLocation;
import org.dimensinfin.eveonline.neocom.infinity.adapter.LocationCatalogServiceWrapper;

@Service
public class SpaceLocationServiceV1 {
	private LocationCatalogService locationCatalogService;

	// - C O N S T R U C T O R S
	@Autowired
	private SpaceLocationServiceV1( @NotNull final LocationCatalogServiceWrapper locationCatalogServiceWrapper ) {
		this.locationCatalogService = Objects.requireNonNull( locationCatalogServiceWrapper ).getSingleton();
	}

	public SpaceLocation getLocationById( final Long locationId ) {
		return this.locationCatalogService.searchLocation4Id( locationId );
	}
}
