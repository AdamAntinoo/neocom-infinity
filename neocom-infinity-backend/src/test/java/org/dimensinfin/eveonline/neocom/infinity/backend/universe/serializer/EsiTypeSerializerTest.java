package org.dimensinfin.eveonline.neocom.infinity.backend.universe.serializer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import org.dimensinfin.eveonline.neocom.domain.EsiType;
import org.dimensinfin.eveonline.neocom.domain.IndustryGroup;
import org.dimensinfin.eveonline.neocom.esiswagger.model.GetUniverseCategoriesCategoryIdOk;
import org.dimensinfin.eveonline.neocom.esiswagger.model.GetUniverseGroupsGroupIdOk;
import org.dimensinfin.eveonline.neocom.esiswagger.model.GetUniverseTypesTypeIdOk;

public class EsiTypeSerializerTest {
	public static final Integer TEST_ESITYPE_ID = 11535;
	public static final String TEST_ESITYPE_NAME = "Magnetometric Sensor Cluster";
	public static final String TEST_ESITYPE_DESCRIPTION = "Magnetometric Sensor Cluster";
	public static final String TEST_ESITYPE_GROUP_NAME = "Construction Components";
	public static final String TEST_ESITYPE_CATEGORY_NAME = "Commodity";
	public static final String TEST_ESITYPE_TYPEICON_URL = "https://image.eveonline.com/Type/11535_64.png";
	public static final IndustryGroup TEST_ESITYPE_INDUSTRYGROUP_NAME = IndustryGroup.COMMODITY;
	public static final String TEST_ESITYPE_HULLGROUP_NAME = "not-applies";
	public static final String TEST_ESITYPE_TECH = "Tech I";
	public static final Float TEST_ESITYPE_VOLUME = 0.1F;
	private ObjectMapper objectMapper;
	private GetUniverseTypesTypeIdOk type;
	private GetUniverseGroupsGroupIdOk group;
	private GetUniverseCategoriesCategoryIdOk category;

	@BeforeEach
	public void beforeEach() {
		this.objectMapper = new ObjectMapper();
		final SimpleModule module = new SimpleModule();
		module.addSerializer( EsiType.class, new EsiTypeSerializer() );
		this.objectMapper.registerModule( module );

		this.type = Mockito.mock( GetUniverseTypesTypeIdOk.class );
		this.group = Mockito.mock( GetUniverseGroupsGroupIdOk.class );
		this.category = Mockito.mock( GetUniverseCategoriesCategoryIdOk.class );
	}

	@Test
	public void serialize() throws JsonProcessingException {
		// When
		Mockito.when( this.type.getName() ).thenReturn( TEST_ESITYPE_NAME );
		Mockito.when( this.type.getDescription() ).thenReturn( TEST_ESITYPE_DESCRIPTION );
		Mockito.when( this.type.getVolume() ).thenReturn( TEST_ESITYPE_VOLUME );
		Mockito.when( this.category.getName() ).thenReturn( TEST_ESITYPE_CATEGORY_NAME );
		// Test
		final EsiType esiType = new EsiType.Builder()
				.withTypeId( TEST_ESITYPE_ID )
				.withItemType( this.type )
				.withGroup( this.group )
				.withCategory( this.category )
				.build();
		final String json = this.objectMapper.writeValueAsString( esiType );
		final String expected = "{\"jsonClass\":\"EsiType\",\"typeId\":11535,\"name\":\"Magnetometric Sensor Cluster\",\"marketGroupId\":0,\"groupId\":0,\"groupName\":null,\"categoryId\":0,\"categoryName\":\"Commodity\",\"capacity\":0.0,\"packagedVolume\":0.0,\"volume\":0.1,\"tech\":\"Tech I\",\"volume\":0.1,\"isBlueprint\":false}";
		Assertions.assertEquals( expected, json );
	}
}