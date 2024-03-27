package org.dimensinfin.eveonline.neocom.infinity.acceptance.support.industry;

import java.util.Map;

import org.junit.jupiter.api.Assertions;

import org.dimensinfin.eveonline.neocom.infinity.acceptance.support.Validator;
import org.dimensinfin.eveonline.neocom.infinity.backend.industry.fitting.domain.FittingInfo;

import static org.dimensinfin.eveonline.neocom.infinity.acceptance.support.AcceptanceFieldMapConstants.FITTING_INFO_FITTING;
import static org.dimensinfin.eveonline.neocom.infinity.acceptance.support.AcceptanceFieldMapConstants.FITTING_INFO_HULL;
import static org.dimensinfin.eveonline.neocom.infinity.acceptance.support.AcceptanceFieldMapConstants.FITTING_INFO_HULL_ACTION;

public class FittingInfoValidator implements Validator<FittingInfo> {
	@Override
	public boolean validate( final Map<String, String> rowData, final FittingInfo record ) {
		if (null != rowData.get( FITTING_INFO_FITTING ))
			Assertions.assertNotNull( record.getFitting() );
		if (null != rowData.get( FITTING_INFO_HULL ))
			Assertions.assertNotNull( record.getHull() );
		if (null != rowData.get( FITTING_INFO_HULL_ACTION ))
			Assertions.assertNotNull( record.getHullAction() );
		return true;
	}
}
