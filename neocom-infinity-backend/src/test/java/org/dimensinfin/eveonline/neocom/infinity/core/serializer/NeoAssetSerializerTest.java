package org.dimensinfin.eveonline.neocom.infinity.core.serializer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import org.dimensinfin.eveonline.neocom.asset.converter.GetCharactersCharacterIdAsset2EsiAssets200OkConverter;
import org.dimensinfin.eveonline.neocom.asset.domain.EsiAssets200Ok;
import org.dimensinfin.eveonline.neocom.database.entities.NeoAsset;
import org.dimensinfin.eveonline.neocom.domain.LocationIdentifier;
import org.dimensinfin.eveonline.neocom.domain.NeoItem;
import org.dimensinfin.eveonline.neocom.esiswagger.model.GetCharactersCharacterIdAssets200Ok;
import org.dimensinfin.eveonline.neocom.esiswagger.model.GetUniverseCategoriesCategoryIdOk;
import org.dimensinfin.eveonline.neocom.esiswagger.model.GetUniverseGroupsGroupIdOk;
import org.dimensinfin.eveonline.neocom.esiswagger.model.GetUniverseTypesTypeIdOk;
import org.dimensinfin.eveonline.neocom.service.ESIDataService;

class NeoAssetSerializerTest {
	private ObjectMapper objectMapper;

	@BeforeEach
	public void beforeEach() {
		this.objectMapper = new ObjectMapper();
		final SimpleModule module = new SimpleModule();
		module.addSerializer( NeoAsset.class, new NeoAssetSerializer() );
		this.objectMapper.registerModule( module );
	}

	@Test
	public void serialize() throws JsonProcessingException {
		final GetUniverseTypesTypeIdOk esiItem = Mockito.mock( GetUniverseTypesTypeIdOk.class );
		Mockito.when( esiItem.getGroupId() ).thenReturn( 18 );
		Mockito.when( esiItem.getName() ).thenReturn( "Tritanium" );
		final GetUniverseGroupsGroupIdOk esiGroup = Mockito.mock( GetUniverseGroupsGroupIdOk.class );
		Mockito.when( esiGroup.getGroupId() ).thenReturn( 18 );
		Mockito.when( esiGroup.getName() ).thenReturn( "Mineral" );
		Mockito.when( esiGroup.getCategoryId() ).thenReturn( 4 );
		final GetUniverseCategoriesCategoryIdOk esiCategory = Mockito.mock( GetUniverseCategoriesCategoryIdOk.class );
		Mockito.when( esiCategory.getCategoryId() ).thenReturn( 4 );
		Mockito.when( esiCategory.getName() ).thenReturn( "Material" );
		final ESIDataService esiDataService = Mockito.mock( ESIDataService.class );
		Mockito.when( esiDataService.searchEsiItem4Id( Mockito.anyInt() ) ).thenReturn( esiItem );
		Mockito.when( esiDataService.searchItemGroup4Id( Mockito.anyInt() ) ).thenReturn( esiGroup );
		Mockito.when( esiDataService.searchItemCategory4Id( Mockito.anyInt() ) ).thenReturn( esiCategory );
		NeoItem.injectEsiUniverseDataAdapter( esiDataService );
		final GetCharactersCharacterIdAssets200Ok esiAssetOk = new GetCharactersCharacterIdAssets200Ok();
		esiAssetOk.setItemId( 123456L );
		esiAssetOk.setTypeId( 34 );
		esiAssetOk.setLocationId( 54321L );
		esiAssetOk.setQuantity( 10 );
		esiAssetOk.setLocationId( 123L );
		esiAssetOk.setIsBlueprintCopy( false );
		esiAssetOk.setIsSingleton( false );
		esiAssetOk.setLocationFlag( GetCharactersCharacterIdAssets200Ok.LocationFlagEnum.ASSETSAFETY );
		esiAssetOk.setLocationType( GetCharactersCharacterIdAssets200Ok.LocationTypeEnum.SOLAR_SYSTEM );
		final EsiAssets200Ok esiAsset = new GetCharactersCharacterIdAsset2EsiAssets200OkConverter().convert( esiAssetOk );

		final NeoAsset asset = new NeoAsset()
				.setAssetId( 123456L )
				.setAssetDelegate( esiAsset )
				.setItemDelegate( new NeoItem( esiAssetOk.getTypeId() ) )
				.setBlueprintFlag( false )
				.setContainerFlag( false )
				.setShipFlag( false )
				.setLocationId( new LocationIdentifier.Builder()
						.withSpaceIdentifier( 12345L )
						.withLocationFlag( EsiAssets200Ok.LocationFlagEnum.ASSETSAFETY )
						.withLocationType( EsiAssets200Ok.LocationTypeEnum.SOLAR_SYSTEM )
						.build() );
		final String expected = "{\"jsonClass\":\"NeoAsset\",\"UUID\":null,\"assetId\":123456,\"type\":34,\"locationId\":12345,\"name\":\"Tritanium\",\"groupId\":18,\"groupName\":\"Mineral\",\"categoryId\":4,\"categoryName\":\"Material\",\"tech\":\"Tech I\",\"volume\":0.0,\"price\":0.0,\"quantity\":10}";

		final String obtained = this.objectMapper.writeValueAsString( asset );
		Assertions.assertEquals( expected, obtained );
	}
}
