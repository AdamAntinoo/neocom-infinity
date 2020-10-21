package org.dimensinfin.eveonline.neocom.infinity.acceptance.steps;

import java.util.List;
import java.util.Map;

import org.junit.Assert;
import org.springframework.beans.factory.annotation.Autowired;

import org.dimensinfin.eveonline.neocom.infinity.support.ConverterContainer;
import org.dimensinfin.eveonline.neocom.infinity.support.NeoComWorld;

import io.cucumber.java.en.Then;

public class NIB07GetItem extends SupportSteps {
	private static final String JSON_CLASS = "jsonClass";
	private static final String TYPE_ID = "typeId";
	private static final String NAME = "name";
	private static final String GROUP_ID = "groupId";
	private static final String GROUP_NAME = "groupName";
	private static final String CATEGORY_ID = "categoryId";
	private static final String CATEGORY_NAME = "categoryName";
	private static final String TECH = "tech";
	private static final String VOLUME = "volume";
	private static final String PRICE = "price";
	private static final String IS_BLUEPRINT = "isBlueprint";
	private static final String URL_FOR_ITEM = "urlforItem";

	@Autowired
	public NIB07GetItem( final ConverterContainer cucumberTableToRequestConverters,
	                     final NeoComWorld neocomWorld ) {
		super( cucumberTableToRequestConverters, neocomWorld );
	}

	@Then("the resulting item data has the next contents")
	public void the_resulting_item_data_has_the_next_contents( final List<Map<String, String>> dataTable ) {
		final Map<String, String> row = dataTable.get( 0 );
		Assert.assertEquals( row.get( JSON_CLASS ), this.neocomWorld.getNeoItemBasicResponseEntity().getBody().getJsonClass() );
		Assert.assertEquals( Integer.parseInt( row.get( TYPE_ID ) ),
				this.neocomWorld.getNeoItemBasicResponseEntity().getBody().getTypeId().intValue() );
		Assert.assertEquals( row.get( NAME ), this.neocomWorld.getNeoItemBasicResponseEntity().getBody().getName() );
		Assert.assertEquals( Integer.parseInt( row.get( GROUP_ID ) ),
				this.neocomWorld.getNeoItemBasicResponseEntity().getBody().getGroupId().intValue() );
		Assert.assertEquals( row.get( GROUP_NAME ), this.neocomWorld.getNeoItemBasicResponseEntity().getBody().getGroupName() );
		Assert.assertEquals( Integer.parseInt( row.get( CATEGORY_ID ) ),
				this.neocomWorld.getNeoItemBasicResponseEntity().getBody().getCategoryId().intValue() );
		Assert.assertEquals( row.get( CATEGORY_NAME ), this.neocomWorld.getNeoItemBasicResponseEntity().getBody().getCategoryName() );
		Assert.assertEquals( row.get( TECH ), this.neocomWorld.getNeoItemBasicResponseEntity().getBody().getTech() );
		Assert.assertEquals( Double.parseDouble( row.get( VOLUME ) ),
				this.neocomWorld.getNeoItemBasicResponseEntity().getBody().getVolume(), 0.01 );
		Assert.assertEquals( Double.parseDouble( row.get( PRICE ) ),
				this.neocomWorld.getNeoItemBasicResponseEntity().getBody().getPrice(), 0.01 );
		Assert.assertEquals( Boolean.parseBoolean( row.get( IS_BLUEPRINT ) ),
				this.neocomWorld.getNeoItemBasicResponseEntity().getBody().isBlueprint() );
		Assert.assertEquals( row.get( URL_FOR_ITEM ), this.neocomWorld.getNeoItemBasicResponseEntity().getBody().getUrlforItem() );
	}
}
