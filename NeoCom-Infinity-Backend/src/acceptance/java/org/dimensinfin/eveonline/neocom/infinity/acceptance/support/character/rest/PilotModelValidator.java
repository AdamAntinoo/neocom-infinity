package org.dimensinfin.eveonline.neocom.infinity.acceptance.support.character.rest;

import java.util.Map;

import org.junit.jupiter.api.Assertions;

import org.dimensinfin.eveonline.neocom.infinity.acceptance.support.Validator;
import org.dimensinfin.eveonline.neocom.infinity.backend.universe.domain.PilotV1;

import static org.dimensinfin.eveonline.neocom.infinity.acceptance.support.AcceptanceFieldMapConstants.CORPORATION_ID;
import static org.dimensinfin.eveonline.neocom.infinity.acceptance.support.AcceptanceFieldMapConstants.CORPORATION_LINK;
import static org.dimensinfin.eveonline.neocom.infinity.acceptance.support.AcceptanceFieldMapConstants.PILOT_ANCESTRY_DATA_OBJECT;
import static org.dimensinfin.eveonline.neocom.infinity.acceptance.support.AcceptanceFieldMapConstants.PILOT_BLOODLINE_DATA_OBJECT;
import static org.dimensinfin.eveonline.neocom.infinity.acceptance.support.AcceptanceFieldMapConstants.PILOT_ID;
import static org.dimensinfin.eveonline.neocom.infinity.acceptance.support.AcceptanceFieldMapConstants.PILOT_PUBLIC_DATA_OBJECT;
import static org.dimensinfin.eveonline.neocom.infinity.acceptance.support.AcceptanceFieldMapConstants.PILOT_RACE_DATA_OBJECT;

public class PilotModelValidator implements Validator<PilotV1> {

	@Override
	public boolean validate( final Map<String, String> rowData, final PilotV1 record ) {
		if (null != rowData.get( PILOT_ID )) Assertions.assertEquals( Integer.parseInt( rowData.get( PILOT_ID ) ), record.getPilotId() );
		if (null != rowData.get( CORPORATION_ID )) Assertions.assertEquals( Integer.parseInt(
				rowData.get( CORPORATION_ID ) ), record.getCorporationId()
		);
		if (null != rowData.get( CORPORATION_LINK )) Assertions.assertEquals( rowData.get( CORPORATION_LINK ), record.getCorporation().getHref() );
		if (null != rowData.get( PILOT_PUBLIC_DATA_OBJECT )) Assertions.assertNotNull( record.getPilotPublicData() );
		if (null != rowData.get( PILOT_RACE_DATA_OBJECT )) Assertions.assertNotNull( record.getRaceData() );
		if (null != rowData.get( PILOT_ANCESTRY_DATA_OBJECT )) Assertions.assertNotNull( record.getAncestryData() );
		if (null != rowData.get( PILOT_BLOODLINE_DATA_OBJECT )) Assertions.assertNotNull( record.getBloodlineData() );
		return true;
	}
}
