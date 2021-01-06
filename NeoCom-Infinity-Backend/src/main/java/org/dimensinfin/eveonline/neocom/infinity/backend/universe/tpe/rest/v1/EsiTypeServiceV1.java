package org.dimensinfin.eveonline.neocom.infinity.backend.universe.tpe.rest.v1;

import java.util.Objects;
import javax.validation.constraints.NotNull;

import org.springframework.stereotype.Service;

import org.dimensinfin.eveonline.neocom.domain.EsiType;
import org.dimensinfin.eveonline.neocom.esiswagger.model.GetUniverseGroupsGroupIdOk;
import org.dimensinfin.eveonline.neocom.esiswagger.model.GetUniverseTypesTypeIdOk;
import org.dimensinfin.eveonline.neocom.infinity.adapter.ESIDataProviderWrapper;
import org.dimensinfin.eveonline.neocom.provider.ESIDataProvider;

@Service
public class EsiTypeServiceV1 {
	private final ESIDataProvider esiDataProvider;

	// - C O N S T R U C T O R S
	public EsiTypeServiceV1( final @NotNull ESIDataProviderWrapper esiDataProviderWrapper ) {
		this.esiDataProvider = Objects.requireNonNull( esiDataProviderWrapper.getSingleton() );
	}

	public EsiType getEsiType( final @NotNull Integer typeId ) {
		final GetUniverseTypesTypeIdOk item = this.esiDataProvider.searchEsiItem4Id( typeId );
		final GetUniverseGroupsGroupIdOk group = this.esiDataProvider.searchItemGroup4Id( item.getGroupId() );
		return new EsiType.Builder()
				.withTypeId( typeId )
				.withItemType( item )
				.withGroup( group )
				.withCategory( this.esiDataProvider.searchItemCategory4Id( group.getCategoryId() ) )
				.build();
	}
}
