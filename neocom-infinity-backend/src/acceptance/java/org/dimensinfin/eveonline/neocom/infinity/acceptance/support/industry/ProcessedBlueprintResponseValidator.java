package org.dimensinfin.eveonline.neocom.infinity.acceptance.support.industry;

import java.util.Map;

import org.junit.jupiter.api.Assertions;

import org.dimensinfin.eveonline.neocom.infinity.acceptance.support.Validator;
import org.dimensinfin.eveonline.neocom.infinity.acceptance.support.industry.deserializer.ProcessedBlueprintResponse;

import static org.dimensinfin.eveonline.neocom.infinity.acceptance.support.AcceptanceFieldMapConstants.BLUEPRINT_UID;

/**
 * @author Adam Antinoo (adamantinoo.git@gmail.com)
 * @since 0.20.0
 */
public class ProcessedBlueprintResponseValidator implements Validator<ProcessedBlueprintResponse> {
	@Override
	public boolean validate( final Map<String, String> rowData, final ProcessedBlueprintResponse record ) {
		if (null != rowData.get( BLUEPRINT_UID ))
			Assertions.assertEquals( rowData.get( BLUEPRINT_UID ), record.getUid() );
		return true;
	}
}