package org.dimensinfin.eveonline.neocom.infinity.acceptance.support.industry;

import java.util.Map;

import org.junit.jupiter.api.Assertions;

import org.dimensinfin.eveonline.neocom.infinity.acceptance.support.Validator;
import org.dimensinfin.eveonline.neocom.infinity.backend.industry.fitting.domain.FittingBuildConfiguration;

import static org.dimensinfin.eveonline.neocom.infinity.acceptance.support.AcceptanceFieldMapConstants.FITTING_BUILD_CONFIGURATION_FITTING_BUILD_ID;
import static org.dimensinfin.eveonline.neocom.infinity.acceptance.support.AcceptanceFieldMapConstants.FITTING_BUILD_CONFIGURATION_FITTING_CONTENTS;
import static org.dimensinfin.eveonline.neocom.infinity.acceptance.support.AcceptanceFieldMapConstants.FITTING_BUILD_CONFIGURATION_FITTING_CONTENTS_SIZE;
import static org.dimensinfin.eveonline.neocom.infinity.acceptance.support.AcceptanceFieldMapConstants.FITTING_BUILD_CONFIGURATION_FITTING_INFO;

public class FittingBuildConfigurationValidator implements Validator<FittingBuildConfiguration> {
	@Override
	public boolean validate( final Map<String, String> rowData, final FittingBuildConfiguration record ) {
		if (null != rowData.get( FITTING_BUILD_CONFIGURATION_FITTING_BUILD_ID ))
			Assertions.assertEquals( rowData.get( FITTING_BUILD_CONFIGURATION_FITTING_BUILD_ID ), record.getFittingBuildId()
			);
		if (null != rowData.get( FITTING_BUILD_CONFIGURATION_FITTING_INFO ))
			Assertions.assertNotNull( record.getFittingInfo() );
		if (null != rowData.get( FITTING_BUILD_CONFIGURATION_FITTING_CONTENTS ))
			Assertions.assertNotNull( record.getContents() );
		if (null != rowData.get( FITTING_BUILD_CONFIGURATION_FITTING_CONTENTS_SIZE ))
			Assertions.assertEquals( Integer.parseInt( rowData.get( FITTING_BUILD_CONFIGURATION_FITTING_CONTENTS_SIZE ) ), record.getContents().size() );
		return true;
	}
}
