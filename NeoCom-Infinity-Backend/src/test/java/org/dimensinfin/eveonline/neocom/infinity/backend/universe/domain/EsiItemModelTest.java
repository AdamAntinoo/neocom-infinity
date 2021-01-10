package org.dimensinfin.eveonline.neocom.infinity.backend.universe.domain;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import org.dimensinfin.eveonline.neocom.esiswagger.model.GetUniverseCategoriesCategoryIdOk;
import org.dimensinfin.eveonline.neocom.esiswagger.model.GetUniverseGroupsGroupIdOk;
import org.dimensinfin.eveonline.neocom.esiswagger.model.GetUniverseTypesTypeIdOk;

import static org.dimensinfin.eveonline.neocom.infinity.backend.support.TestDataConstants.EsiItemModelConstants.TEST_ESI_ITEM_CATEGORY_NAME;
import static org.dimensinfin.eveonline.neocom.infinity.backend.support.TestDataConstants.EsiItemModelConstants.TEST_ESI_ITEM_GROUP_NAME;
import static org.dimensinfin.eveonline.neocom.infinity.backend.support.TestDataConstants.EsiItemModelConstants.TEST_ESI_ITEM_HULLGROUP_NAME;
import static org.dimensinfin.eveonline.neocom.infinity.backend.support.TestDataConstants.EsiItemModelConstants.TEST_ESI_ITEM_MODEL_TYPE_ID;

public class EsiItemModelTest {

	private GetUniverseTypesTypeIdOk item;
	private GetUniverseGroupsGroupIdOk group;
	private GetUniverseCategoriesCategoryIdOk category;

	@BeforeEach
	public void beforeEach() {
		this.item = Mockito.mock( GetUniverseTypesTypeIdOk.class );
		this.group = Mockito.mock( GetUniverseGroupsGroupIdOk.class );
		Mockito.when( this.group.getName() ).thenReturn( TEST_ESI_ITEM_GROUP_NAME );
		this.category = Mockito.mock( GetUniverseCategoriesCategoryIdOk.class );
		Mockito.when( this.category.getName() ).thenReturn( TEST_ESI_ITEM_CATEGORY_NAME );
	}

	@Test
	public void buildContract() {
		// Test
		final EsiItemModel esiItemModel = new EsiItemModel.Builder()
				.withTypeId( TEST_ESI_ITEM_MODEL_TYPE_ID )
				.withItemType( this.item )
				.withGroup( this.group )
				.withCategory( this.category )
				.build();
		// Assertions
		Assertions.assertNotNull( esiItemModel );
	}

	@Test
	public void buildContractEmpty() {
		Assertions.assertThrows( NullPointerException.class, () -> {
			new EsiItemModel.Builder()
					.withItemType( this.item )
					.withGroup( this.group )
					.withCategory( this.category )
					.build();
			new EsiItemModel.Builder()
					.withTypeId( TEST_ESI_ITEM_MODEL_TYPE_ID )
					.withGroup( this.group )
					.withCategory( this.category )
					.build();
			new EsiItemModel.Builder()
					.withTypeId( TEST_ESI_ITEM_MODEL_TYPE_ID )
					.withItemType( this.item )
					.withCategory( this.category )
					.build();
			new EsiItemModel.Builder()
					.withTypeId( TEST_ESI_ITEM_MODEL_TYPE_ID )
					.withItemType( this.item )
					.withGroup( this.group )
					.build();
		} );
	}

	@Test
	public void buildContractNull() {
		Assertions.assertThrows( NullPointerException.class, () -> {
			new EsiItemModel.Builder()
					.withTypeId( null )
					.withItemType( this.item )
					.withGroup( this.group )
					.withCategory( this.category )
					.build();
			new EsiItemModel.Builder()
					.withTypeId( TEST_ESI_ITEM_MODEL_TYPE_ID )
					.withItemType( null )
					.withGroup( this.group )
					.withCategory( this.category )
					.build();
			new EsiItemModel.Builder()
					.withTypeId( TEST_ESI_ITEM_MODEL_TYPE_ID )
					.withItemType( this.item )
					.withGroup( null )
					.withCategory( this.category )
					.build();
			new EsiItemModel.Builder()
					.withTypeId( TEST_ESI_ITEM_MODEL_TYPE_ID )
					.withItemType( this.item )
					.withGroup( this.group )
					.withCategory( null )
					.build();
		} );
	}

	@Test
	public void getterContract() {
		final EsiItemModel esiItemModel = new EsiItemModel.Builder()
				.withTypeId( TEST_ESI_ITEM_MODEL_TYPE_ID )
				.withItemType( this.item )
				.withGroup( this.group )
				.withCategory( this.category )
				.build();
		// Assertions
		Assertions.assertEquals( TEST_ESI_ITEM_MODEL_TYPE_ID, esiItemModel.getTypeId() );
		Assertions.assertEquals( this.group, esiItemModel.getGroup() );
		Assertions.assertEquals( this.category, esiItemModel.getCategory() );
		Assertions.assertEquals( TEST_ESI_ITEM_GROUP_NAME, esiItemModel.getGroupName() );
		Assertions.assertEquals( TEST_ESI_ITEM_CATEGORY_NAME, esiItemModel.getCategoryName() );
		Assertions.assertEquals( this.item, esiItemModel.getItem() );
		Assertions.assertEquals( TEST_ESI_ITEM_HULLGROUP_NAME, esiItemModel.getHullGroup() );
	}
}
