package org.dimensinfin.eveonline.neocom.infinity.acceptance.support.character.rest;

import java.util.Map;

import org.junit.jupiter.api.Assertions;

import org.dimensinfin.eveonline.neocom.infinity.acceptance.support.Validator;
import org.dimensinfin.eveonline.neocom.infinity.backend.character.fitting.domain.FittingModel;

import static org.dimensinfin.eveonline.neocom.infinity.acceptance.support.AcceptanceFieldMapConstants.FITTING_HULL_CLASS;
import static org.dimensinfin.eveonline.neocom.infinity.acceptance.support.AcceptanceFieldMapConstants.FITTING_ID;
import static org.dimensinfin.eveonline.neocom.infinity.acceptance.support.AcceptanceFieldMapConstants.FITTING_ITEMS_COUNT;
import static org.dimensinfin.eveonline.neocom.infinity.acceptance.support.AcceptanceFieldMapConstants.FITTING_NAME;
import static org.dimensinfin.eveonline.neocom.infinity.acceptance.support.AcceptanceFieldMapConstants.FITTING_SHIP_HULL_CLASS_NAME;

public class FittingModelValidator implements Validator<FittingModel> {

	@Override
	public boolean validate( final Map<String, String> rowData, final FittingModel record ) {
		if (null != rowData.get( FITTING_ID )) Assertions.assertEquals( Integer.parseInt( rowData.get( FITTING_ID ) ), record.getFittingId() );
		if (null != rowData.get( FITTING_ITEMS_COUNT )) Assertions.assertEquals(
				Integer.parseInt( rowData.get( FITTING_ITEMS_COUNT ) ),
				record.getFittingItems().size()
		);
		if (null != rowData.get( FITTING_NAME )) Assertions.assertEquals( rowData.get( FITTING_NAME ), record.getName() );
		if (null != rowData.get( FITTING_HULL_CLASS )) Assertions.assertEquals(
				rowData.get( FITTING_HULL_CLASS ),
				record.getShipHull().getHullGroup()
		);
//		if (null != rowData.get( FITTING_SHIP_TYPE_ID )) Assertions.assertEquals(
//				Integer.parseInt( rowData.get( FITTING_SHIP_TYPE_ID ) ),
//				record.getShipHull().getTypeId()
//		);
		if (null != rowData.get( FITTING_SHIP_HULL_CLASS_NAME )) Assertions.assertEquals(
				rowData.get( FITTING_SHIP_HULL_CLASS_NAME ),
				record.getShipHull().getName()
		);
		return true;
	}
}
