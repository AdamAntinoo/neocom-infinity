package org.dimensinfin.eveonline.neocom.infinity.acceptance.support.authorization.validation;

import java.util.Map;

import org.junit.jupiter.api.Assertions;

import org.dimensinfin.eveonline.neocom.infinity.acceptance.support.Validator;
import org.dimensinfin.eveonline.neocom.infinity.config.security.JwtPayload;

import static org.dimensinfin.eveonline.neocom.infinity.acceptance.support.AcceptanceFieldMapConstants.JWTTOKEN_ACCOUNT_NAME;
import static org.dimensinfin.eveonline.neocom.infinity.acceptance.support.AcceptanceFieldMapConstants.JWTTOKEN_CORPORATION_ID;
import static org.dimensinfin.eveonline.neocom.infinity.acceptance.support.AcceptanceFieldMapConstants.JWTTOKEN_PILOT_ID;
import static org.dimensinfin.eveonline.neocom.infinity.acceptance.support.AcceptanceFieldMapConstants.JWTTOKEN_SUBSCRIBER;
import static org.dimensinfin.eveonline.neocom.infinity.acceptance.support.AcceptanceFieldMapConstants.JWTTOKEN_UNIQUE_ID;

/**
 * @author Adam Antinoo (adamantinoo.git@gmail.com)
 * @since 0.20.0
 */
public class JWTTokenValidator implements Validator<JwtPayload> {

	@Override
	public boolean validate( final Map<String, String> rowData, final JwtPayload record ) {
		if (null != rowData.get( JWTTOKEN_SUBSCRIBER ))
			Assertions.assertEquals( rowData.get( JWTTOKEN_SUBSCRIBER ), record.getSub() );
		if (null != rowData.get( JWTTOKEN_CORPORATION_ID ))
			Assertions.assertEquals( Integer.parseInt( rowData.get( JWTTOKEN_CORPORATION_ID ) ), record.getCorporationId() );
		if (null != rowData.get( JWTTOKEN_ACCOUNT_NAME ))
			Assertions.assertEquals( rowData.get( JWTTOKEN_ACCOUNT_NAME ), record.getAccountName() );
		if (null != rowData.get( JWTTOKEN_UNIQUE_ID ))
			Assertions.assertEquals( rowData.get( JWTTOKEN_UNIQUE_ID ), record.getUniqueId() );
		if (null != rowData.get( JWTTOKEN_PILOT_ID ))
			Assertions.assertEquals( Integer.parseInt( rowData.get( JWTTOKEN_PILOT_ID ) ), record.getCorporationId() );
		return true;
	}
}