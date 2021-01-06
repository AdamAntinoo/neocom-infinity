package org.dimensinfin.eveonline.neocom.infinity.backend.universe.item.rest.v2;

import java.util.Objects;
import javax.validation.constraints.NotNull;

import org.springframework.stereotype.Service;

import org.dimensinfin.eveonline.neocom.esiswagger.model.GetUniverseGroupsGroupIdOk;
import org.dimensinfin.eveonline.neocom.esiswagger.model.GetUniverseTypesTypeIdOk;
import org.dimensinfin.eveonline.neocom.infinity.adapter.ESIDataProviderWrapper;
import org.dimensinfin.eveonline.neocom.infinity.backend.universe.domain.EsiItemModel;
import org.dimensinfin.eveonline.neocom.provider.ESIDataProvider;

@Deprecated
@Service
public class EsiItemServiceV2 {
	private final ESIDataProvider esiDataProvider;

	// - C O N S T R U C T O R S
	public EsiItemServiceV2( final @NotNull ESIDataProviderWrapper esiDataProviderWrapper ) {
		this.esiDataProvider = Objects.requireNonNull( esiDataProviderWrapper.getSingleton() );
	}

	public EsiItemModel getItem( final @NotNull Integer itemId ) {
		final GetUniverseTypesTypeIdOk item = this.esiDataProvider.searchEsiItem4Id( itemId );
		final GetUniverseGroupsGroupIdOk group = this.esiDataProvider.searchItemGroup4Id( item.getGroupId() );
		return new EsiItemModel.Builder()
				.withTypeId( itemId )
				.withItemType( item )
				.withGroup( group )
				.withCategory( this.esiDataProvider.searchItemCategory4Id( group.getCategoryId() ) )
				.build();
	}
}
