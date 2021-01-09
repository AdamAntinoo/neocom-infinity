package acceptance.support.character.rest;

import java.util.Map;

import org.junit.jupiter.api.Assertions;

import acceptance.support.Validator;
import org.dimensinfin.eveonline.neocom.infinity.pilot.rest.representation.PilotModel;

import static acceptance.support.AcceptanceFieldMapConstants.CORPORATION_ID;
import static acceptance.support.AcceptanceFieldMapConstants.CORPORATION_LINK;
import static acceptance.support.AcceptanceFieldMapConstants.PILOT_ANCESTRY_DATA_OBJECT;
import static acceptance.support.AcceptanceFieldMapConstants.PILOT_BLOODLINE_DATA_OBJECT;
import static acceptance.support.AcceptanceFieldMapConstants.PILOT_ID;
import static acceptance.support.AcceptanceFieldMapConstants.PILOT_PUBLIC_DATA_OBJECT;
import static acceptance.support.AcceptanceFieldMapConstants.PILOT_RACE_DATA_OBJECT;

public class PilotModelValidator implements Validator<PilotModel> {

	@Override
	public boolean validate( final Map<String, String> rowData, final PilotModel record ) {
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
