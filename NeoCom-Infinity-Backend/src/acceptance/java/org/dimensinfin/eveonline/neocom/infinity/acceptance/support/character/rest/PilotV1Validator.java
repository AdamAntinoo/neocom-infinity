package org.dimensinfin.eveonline.neocom.infinity.acceptance.support.character.rest;

import java.util.Map;

import org.junit.jupiter.api.Assertions;

import org.dimensinfin.eveonline.neocom.infinity.acceptance.support.Validator;
import org.dimensinfin.eveonline.neocom.infinity.acceptance.support.dto.PilotDto;

import static org.dimensinfin.eveonline.neocom.infinity.acceptance.support.AcceptanceFieldMapConstants.CORPORATION_ID;
import static org.dimensinfin.eveonline.neocom.infinity.acceptance.support.AcceptanceFieldMapConstants.PILOT_BIRTHDAY;
import static org.dimensinfin.eveonline.neocom.infinity.acceptance.support.AcceptanceFieldMapConstants.PILOT_CURRENT_SHIP_NAME;
import static org.dimensinfin.eveonline.neocom.infinity.acceptance.support.AcceptanceFieldMapConstants.PILOT_CURRENT_SHIP_TYPE_NAME;
import static org.dimensinfin.eveonline.neocom.infinity.acceptance.support.AcceptanceFieldMapConstants.PILOT_GENDER;
import static org.dimensinfin.eveonline.neocom.infinity.acceptance.support.AcceptanceFieldMapConstants.PILOT_ID;
import static org.dimensinfin.eveonline.neocom.infinity.acceptance.support.AcceptanceFieldMapConstants.PILOT_NAME;
import static org.dimensinfin.eveonline.neocom.infinity.acceptance.support.AcceptanceFieldMapConstants.PILOT_SECURITY_STATUS;
import static org.dimensinfin.eveonline.neocom.infinity.acceptance.support.AcceptanceFieldMapConstants.PILOT_TOTAL_SKILL_POINTS;
import static org.dimensinfin.eveonline.neocom.infinity.acceptance.support.AcceptanceFieldMapConstants.PILOT_WALLET_BALANCE;

/**
 * @author Adam Antinoo (adamantinoo.git@gmail.com)
 * @since 0.20.0
 */
public class PilotV1Validator implements Validator<PilotDto> {
	@Override
	public boolean validate( final Map<String, String> rowData, final PilotDto record ) {
		if (null != rowData.get( PILOT_ID ))
			Assertions.assertEquals( Integer.parseInt( rowData.get( PILOT_ID ) ), record.getPilotId() );
		if (null != rowData.get( CORPORATION_ID ))
			Assertions.assertEquals( Integer.parseInt( rowData.get( CORPORATION_ID ) ), record.getCorporationId() );
		if (null != rowData.get( PILOT_NAME )) Assertions.assertEquals( rowData.get( PILOT_NAME ),
				record.getName() );
		if (null != rowData.get( PILOT_BIRTHDAY ))
			Assertions.assertEquals( rowData.get( PILOT_BIRTHDAY ), record.getBirthday().toString() );
		if (null != rowData.get( PILOT_GENDER ))
			Assertions.assertEquals( rowData.get( PILOT_GENDER ), record.getGender() );
		if (null != rowData.get( PILOT_SECURITY_STATUS ))
			Assertions.assertEquals( Float.parseFloat( rowData.get( PILOT_SECURITY_STATUS ) ), record.getSecurityStatus(), 0.01 );
		if (null != rowData.get( PILOT_TOTAL_SKILL_POINTS ))
			Assertions.assertEquals( Integer.parseInt( rowData.get( PILOT_TOTAL_SKILL_POINTS ) ), record.getTotalSkillpoints() );
		if (null != rowData.get( PILOT_WALLET_BALANCE ))
			Assertions.assertEquals( Double.parseDouble( rowData.get( PILOT_WALLET_BALANCE ) ), record.getWalletBalance(), 0.01 );
		if (null != rowData.get( PILOT_CURRENT_SHIP_NAME )) Assertions.assertEquals( rowData.get( PILOT_CURRENT_SHIP_NAME ),
				record.getCurrentShipName() );
		if (null != rowData.get( PILOT_CURRENT_SHIP_TYPE_NAME )) Assertions.assertEquals( rowData.get( PILOT_CURRENT_SHIP_TYPE_NAME ),
				record.getCurrentShipTypeName() );
		return true;
	}
}
