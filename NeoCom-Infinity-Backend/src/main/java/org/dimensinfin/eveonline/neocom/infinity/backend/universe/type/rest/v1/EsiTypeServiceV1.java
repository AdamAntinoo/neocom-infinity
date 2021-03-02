package org.dimensinfin.eveonline.neocom.infinity.backend.universe.type.rest.v1;

import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.dimensinfin.eveonline.neocom.domain.EsiType;
import org.dimensinfin.eveonline.neocom.esiswagger.model.GetUniverseGroupsGroupIdOk;
import org.dimensinfin.eveonline.neocom.esiswagger.model.GetUniverseTypesTypeIdOk;
import org.dimensinfin.eveonline.neocom.service.ESIDataService;

@Service
public class EsiTypeServiceV1 {
	private final ESIDataService esiDataService;

	// - C O N S T R U C T O R S
	@Autowired
	public EsiTypeServiceV1( @NotNull final ESIDataService esiDataService ) {
		this.esiDataService = esiDataService;
	}

	public EsiType getEsiType( final @NotNull Integer typeId ) {
		final GetUniverseTypesTypeIdOk item = this.esiDataService.searchEsiItem4Id( typeId );
		final GetUniverseGroupsGroupIdOk group = this.esiDataService.searchItemGroup4Id( item.getGroupId() );
		return new EsiType.Builder()
				.withTypeId( typeId )
				.withItemType( item )
				.withGroup( group )
				.withCategory( this.esiDataService.searchItemCategory4Id( group.getCategoryId() ) )
				.build();
	}
}
