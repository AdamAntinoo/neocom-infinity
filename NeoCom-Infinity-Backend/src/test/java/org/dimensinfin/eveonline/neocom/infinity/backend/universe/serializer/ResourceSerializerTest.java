package org.dimensinfin.eveonline.neocom.infinity.backend.universe.serializer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import org.dimensinfin.eveonline.neocom.domain.Resource;
import org.dimensinfin.eveonline.neocom.esiswagger.model.GetUniverseCategoriesCategoryIdOk;
import org.dimensinfin.eveonline.neocom.esiswagger.model.GetUniverseGroupsGroupIdOk;
import org.dimensinfin.eveonline.neocom.esiswagger.model.GetUniverseTypesTypeIdOk;

public class ResourceSerializerTest {
	public static final Integer TEST_RESOURCE_TYPE_ID = 16636;
	public static final Integer TEST_RESOURCE_QUANTITY = 543;
	public static final String TEST_RESOURCE_GROUP = "Moon Materials";
	public static final String TEST_RESOURCE_CATEGORY = "Material";
	public static final String TEST_RESOURCE_NAME = "Silicates";
	public static final Float TEST_RESOURCE_VOLUME = 0.5F;
	private ObjectMapper objectMapper;

	@BeforeEach
	public void beforeEach() {
		this.objectMapper = new ObjectMapper();
		final SimpleModule module = new SimpleModule();
		module.addSerializer( Resource.class, new ResourceSerializer() );
		this.objectMapper.registerModule( module );
	}

	@Test
	public void serialize() throws JsonProcessingException {
		// Given
		final GetUniverseTypesTypeIdOk type = Mockito.mock( GetUniverseTypesTypeIdOk.class );
		final GetUniverseGroupsGroupIdOk group = Mockito.mock( GetUniverseGroupsGroupIdOk.class );
		final GetUniverseCategoriesCategoryIdOk category = Mockito.mock( GetUniverseCategoriesCategoryIdOk.class );
		// When
		Mockito.when( type.getName() ).thenReturn( TEST_RESOURCE_NAME );
		Mockito.when( type.getVolume() ).thenReturn( TEST_RESOURCE_VOLUME );
		Mockito.when( group.getName() ).thenReturn( TEST_RESOURCE_GROUP );
		Mockito.when( category.getName() ).thenReturn( TEST_RESOURCE_CATEGORY );
		// Test
		final Resource resource = new Resource.Builder()
				.withTypeId( TEST_RESOURCE_TYPE_ID )
				.withItemType( type )
				.withGroup( group )
				.withCategory( category )
				.build();
		resource.setQuantity( TEST_RESOURCE_QUANTITY );
		final String json = this.objectMapper.writeValueAsString( resource );
		final String expected = "{\"typeId\":16636,\"name\":\"Silicates\",\"quantity\":543,\"group\":{\"categoryId\":0,\"groupId\":0,\"name\":\"Moon Materials\",\"published\":false,\"types\":[]},\"category\":{\"categoryId\":0,\"groups\":[],\"name\":\"Material\",\"published\":false},\"type\":{\"capacity\":0.0,\"description\":null,\"dogmaAttributes\":[],\"dogmaEffects\":[],\"graphicId\":0,\"groupId\":0,\"iconId\":0,\"marketGroupId\":0,\"mass\":0.0,\"name\":\"Silicates\",\"packagedVolume\":0.0,\"portionSize\":0,\"published\":false,\"radius\":0.0,\"typeId\":0,\"volume\":0.5},\"tech\":\"Tech I\",\"volume\":0.5,\"isBlueprint\":false,\"typeIconURL\":\"https://image.eveonline.com/Type/16636_64.png\",\"marketData\":{\"rel\":\"marketData\",\"href\":\"/api/v1/universe/market/consolidated/byregion/10000043/16636\"}}";
		Assertions.assertEquals( expected, json );
	}
}