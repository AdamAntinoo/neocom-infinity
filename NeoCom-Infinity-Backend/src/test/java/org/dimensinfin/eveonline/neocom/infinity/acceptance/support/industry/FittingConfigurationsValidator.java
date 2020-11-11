package org.dimensinfin.eveonline.neocom.infinity.acceptance.support.industry;

import java.util.Map;

import org.junit.jupiter.api.Assertions;

import org.dimensinfin.eveonline.neocom.infinity.acceptance.support.Validator;
import org.dimensinfin.eveonline.neocom.infinity.backend.industry.fitting.domain.FittingConfigurations;

import static org.dimensinfin.eveonline.neocom.infinity.acceptance.support.AcceptanceFieldMapConstants.FITTING_BUILD_CONFIGURATIONS_SAVED;
import static org.dimensinfin.eveonline.neocom.infinity.acceptance.support.AcceptanceFieldMapConstants.FITTING_BUILD_CONFIGURATIONS_TARGET;

public class FittingConfigurationsValidator implements Validator<FittingConfigurations> {
	@Override
	public boolean validate( final Map<String, String> rowData, final FittingConfigurations record ) {
		if (null != rowData.get( FITTING_BUILD_CONFIGURATIONS_SAVED ))
			Assertions.assertEquals( rowData.get( FITTING_BUILD_CONFIGURATIONS_SAVED ), record.getSavedBuildData().getHref().toString()
			);
		if (null != rowData.get( FITTING_BUILD_CONFIGURATIONS_TARGET ))
			Assertions.assertEquals( rowData.get( FITTING_BUILD_CONFIGURATIONS_TARGET ), record.getTargetBuildData().getHref().toString()
			);
		return true;
	}
}
