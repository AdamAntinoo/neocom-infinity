package org.dimensinfin.eveonline.neocom.infinity.backend.universe.item.rest.v2;

import javax.validation.constraints.NotNull;

import org.springframework.stereotype.Service;

import org.dimensinfin.eveonline.neocom.esiswagger.model.GetUniverseGroupsGroupIdOk;
import org.dimensinfin.eveonline.neocom.esiswagger.model.GetUniverseTypesTypeIdOk;
import org.dimensinfin.eveonline.neocom.infinity.backend.universe.domain.EsiItemModel;
import org.dimensinfin.eveonline.neocom.service.ESIDataService;

@Deprecated
@Service
public class EsiItemServiceV2 {
	private final ESIDataService esiDataService;

	// - C O N S T R U C T O R S
	public EsiItemServiceV2( @NotNull final ESIDataService esiDataService ) {
		this.esiDataService = esiDataService;
	}

	public EsiItemModel getItem( final @NotNull Integer itemId ) {
		final GetUniverseTypesTypeIdOk item = this.esiDataService.searchEsiUniverseType4Id( itemId );
		final GetUniverseGroupsGroupIdOk group = this.esiDataService.searchItemGroup4Id( item.getGroupId() );
		return new EsiItemModel.Builder()
				.withTypeId( itemId )
				.withItemType( item )
				.withGroup( group )
				.withCategory( this.esiDataService.searchItemCategory4Id( group.getCategoryId() ) )
				.build();
	}
}
