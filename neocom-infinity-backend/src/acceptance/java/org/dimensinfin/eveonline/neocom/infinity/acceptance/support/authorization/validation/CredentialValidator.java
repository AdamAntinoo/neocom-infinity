package org.dimensinfin.eveonline.neocom.infinity.acceptance.support.authorization.validation;

import java.util.Map;

import org.junit.jupiter.api.Assertions;

import org.dimensinfin.eveonline.neocom.database.entities.Credential;
import org.dimensinfin.eveonline.neocom.infinity.acceptance.support.Validator;

import static org.dimensinfin.eveonline.neocom.infinity.acceptance.support.AcceptanceFieldMapConstants.ACCOUNT_ID;
import static org.dimensinfin.eveonline.neocom.infinity.acceptance.support.AcceptanceFieldMapConstants.ACCOUNT_NAME;
import static org.dimensinfin.eveonline.neocom.infinity.acceptance.support.AcceptanceFieldMapConstants.UNIQUE_CREDENTIAL;

public class CredentialValidator implements Validator<Credential> {
	@Override
	public boolean validate( final Map<String, String> rowData, final Credential record ) {
		if (null != rowData.get( UNIQUE_CREDENTIAL )) Assertions.assertEquals( rowData.get( UNIQUE_CREDENTIAL ), record.getUniqueCredential() );
		if (null != rowData.get( ACCOUNT_ID )) Assertions.assertEquals( Integer.parseInt( rowData.get( ACCOUNT_ID ) ), record.getAccountId() );
		if (null != rowData.get( ACCOUNT_NAME )) Assertions.assertEquals( Integer.parseInt( rowData.get( ACCOUNT_NAME ) ), record.getAccountName() );
		return true;
	}
}
