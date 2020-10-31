package org.dimensinfin.eveonline.neocom.infinity.neoitem;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import org.dimensinfin.eveonline.neocom.domain.NeoItem;
import org.dimensinfin.eveonline.neocom.esiswagger.model.GetUniverseCategoriesCategoryIdOk;
import org.dimensinfin.eveonline.neocom.esiswagger.model.GetUniverseGroupsGroupIdOk;
import org.dimensinfin.eveonline.neocom.esiswagger.model.GetUniverseTypesTypeIdOk;
import org.dimensinfin.eveonline.neocom.infinity.backend.universe.item.rest.v1.NeoItemServiceV1;
import org.dimensinfin.eveonline.neocom.provider.ESIUniverseDataProvider;

public class NeoItemServiceV1Test {
	@Test
	public void getItemBasic() {
		final GetUniverseTypesTypeIdOk esiItem = Mockito.mock( GetUniverseTypesTypeIdOk.class );
		final GetUniverseGroupsGroupIdOk group = Mockito.mock( GetUniverseGroupsGroupIdOk.class );
		Mockito.when( group.getGroupId() ).thenReturn( 18 );
		final GetUniverseCategoriesCategoryIdOk category = Mockito.mock( GetUniverseCategoriesCategoryIdOk.class );
		Mockito.when( category.getCategoryId() ).thenReturn( 4 );
		final ESIUniverseDataProvider esiUniverseDataProvider = Mockito.mock( ESIUniverseDataProvider.class );
		Mockito.when( esiUniverseDataProvider.searchEsiItem4Id( Mockito.anyInt() ) )
				.thenReturn( esiItem );
		Mockito.when( esiUniverseDataProvider.searchItemGroup4Id( Mockito.anyInt() ) )
				.thenReturn( group );
		Mockito.when( esiUniverseDataProvider.searchItemCategory4Id( Mockito.anyInt() ) )
				.thenReturn( category );
		NeoItem.injectEsiUniverseDataAdapter( esiUniverseDataProvider );
		final NeoItemServiceV1 neoItemServiceV1 = new NeoItemServiceV1();
		final NeoItem expected = new NeoItem( 123 );
		final NeoItem obtained = neoItemServiceV1.getItem( 123 );
		Assertions.assertEquals( expected, obtained );
	}
}
