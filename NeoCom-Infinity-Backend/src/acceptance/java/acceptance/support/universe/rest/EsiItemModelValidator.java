package acceptance.support.universe.rest;

import java.util.Map;

import org.junit.jupiter.api.Assertions;

import acceptance.support.Validator;
import org.dimensinfin.eveonline.neocom.infinity.backend.universe.domain.EsiItemModel;

import static acceptance.support.AcceptanceFieldMapConstants.ESIITEM_CATEGORYNAME;
import static acceptance.support.AcceptanceFieldMapConstants.ESIITEM_GROUPNAME;
import static acceptance.support.AcceptanceFieldMapConstants.ESIITEM_HULL_GROUP;
import static acceptance.support.AcceptanceFieldMapConstants.ESIITEM_INDUSTRY_GROUP;
import static acceptance.support.AcceptanceFieldMapConstants.ESIITEM_NAME;
import static acceptance.support.AcceptanceFieldMapConstants.ESIITEM_TYPE_ID;
import static acceptance.support.AcceptanceFieldMapConstants.ESIITEM_URL_FORITEM;

public class EsiItemModelValidator implements Validator<EsiItemModel> {

	@Override
	public boolean validate( final Map<String, String> rowData, final EsiItemModel record ) {
		if (null != rowData.get( ESIITEM_TYPE_ID )) Assertions.assertEquals(
				Integer.parseInt( rowData.get( ESIITEM_TYPE_ID ) ),
				record.getTypeId()
		);
		if (null != rowData.get( ESIITEM_NAME )) Assertions.assertEquals( ESIITEM_NAME, record.getName() );
		if (null != rowData.get( ESIITEM_GROUPNAME )) Assertions.assertEquals( ESIITEM_GROUPNAME, record.getGroupName() );
		if (null != rowData.get( ESIITEM_CATEGORYNAME )) Assertions.assertEquals( ESIITEM_CATEGORYNAME, record.getCategoryName() );
		if (null != rowData.get( ESIITEM_INDUSTRY_GROUP )) Assertions.assertEquals( ESIITEM_INDUSTRY_GROUP, record.getIndustryGroup() );
		if (null != rowData.get( ESIITEM_HULL_GROUP )) Assertions.assertEquals( ESIITEM_HULL_GROUP, record.getHullGroup() );
		if (null != rowData.get( ESIITEM_URL_FORITEM )) Assertions.assertEquals( ESIITEM_URL_FORITEM, record.getURLForItem() );
		return true;
	}
}
