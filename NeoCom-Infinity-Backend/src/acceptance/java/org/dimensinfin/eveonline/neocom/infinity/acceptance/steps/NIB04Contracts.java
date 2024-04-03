package org.dimensinfin.eveonline.neocom.infinity.acceptance.steps;

import java.util.List;
import java.util.Map;

import org.junit.Assert;
import org.springframework.beans.factory.annotation.Autowired;

import org.dimensinfin.eveonline.neocom.infinity.backend.authorization.domain.AuthorizationTokenResponse;
import org.dimensinfin.eveonline.neocom.infinity.support.ConverterContainer;
import org.dimensinfin.eveonline.neocom.infinity.support.NeoComWorld;
import org.dimensinfin.eveonline.neocom.infinity.support.ResponseType;
import org.dimensinfin.eveonline.neocom.infinity.support.corporation.rest.v1.CorporationResponse;
import org.dimensinfin.eveonline.neocom.infinity.support.pilot.rest.v1.PilotResponse;

import io.cucumber.java.en.Then;

public class NIB04Contracts extends SupportSteps {
	private static final String RESPONSE_TYPE = "responseType";
	private static final String JWT_TOKEN = "jwtToken";
	private static final String CREDENTIAL = "credential";
	private static final String ACCOUNT_ID = "accountId";
	private static final String ACCOUNT_NAME = "accountName";
	private static final String DATA_SOURCE = "dataSource";
	private static final String CORPORATION_ID = "corporationId";
	private static final String ASSETS_COUNT = "assetsCount";
	private static final String WALLET_BALANCE = "walletBalance";
	private static final String MINING_RESOURCES_ESTIMATED_VALUE = "miningResourcesEstimatedValue";
	private static final String RACE_NAME = "race name";
	private static final String JSON_CLASS = "jsonClass";
	private static final String PILOT_ID = "pilotId";
	private static final String URL4ICON = "url4Icon";
	private static final String ANCESTRY_NAME = "ancestry name";
	private static final String BLOODLINE_NAME = "bloodline name";
	private static final String GENDER = "gender";
	private static final String NAME = "name";
	private static final String SECURITY_STATUS = "securityStatus";
	private static final String ALLIANCE_ID = "alliance id";
	private static final String CEO_ID = "ceo id";
	private static final String HOME_STATION_ID = "home station id";
	private static final String MEMBER_COUNT = "member count";

	// - C O N S T R U C T O R S
	@Autowired
	public NIB04Contracts(
			final ConverterContainer cucumberTableToRequestConverters,
			final NeoComWorld neocomWorld ) {
		super( cucumberTableToRequestConverters, neocomWorld );
	}

	@Then("the {string} has the next contents")
	public void the_has_the_next_contents( final String responseTypeName, final List<Map<String, String>> dataTable ) {
		final ResponseType responseType = ResponseType.from( responseTypeName );
		final Map<String, String> row = dataTable.get( 0 );
		switch (responseType) {
			case VALIDATE_AUTHORIZATION_TOKEN_RESPONSE:
				final AuthorizationTokenResponse responseValidation = this.neocomWorld
						.getValidateAuthorizationTokenResponse();
				Assert.assertEquals( row.get( RESPONSE_TYPE ), responseValidation.getResponseType() );
				Assert.assertEquals( row.get( JWT_TOKEN ), responseValidation.getJwtToken() );
				Assert.assertEquals( row.get( CREDENTIAL ), responseValidation.getCredential().getJsonClass() );
				Assert.assertEquals( Integer.valueOf( row.get( ACCOUNT_ID ) ).intValue(),
						responseValidation.getCredential().getAccountId().intValue() );
				Assert.assertEquals( Integer.valueOf( row.get( CORPORATION_ID ) ).intValue(),
						responseValidation.getCredential().getCorporationId() );
				Assert.assertEquals( row.get( ACCOUNT_NAME ), responseValidation.getCredential().getAccountName() );
				Assert.assertEquals( row.get( DATA_SOURCE ), responseValidation.getCredential().getDataSource() );
				Assert.assertEquals( Integer.valueOf( row.get( ASSETS_COUNT ) ).intValue(),
						responseValidation.getCredential().getAssetsCount() );
				Assert.assertEquals( Double.valueOf( row.get( WALLET_BALANCE ) ).doubleValue(),
						responseValidation.getCredential().getWalletBalance(), 1.0 );
				Assert.assertEquals( Double.valueOf( row.get( MINING_RESOURCES_ESTIMATED_VALUE ) ).doubleValue(),
						responseValidation.getCredential().getMiningResourcesEstimatedValue(), 1.0 );
				Assert.assertNull( row.get( RACE_NAME ) );
				break;
			case PILOT_PUBLIC_DATA_RESPONSE:
				final PilotResponse pilot = this.neocomWorld.getPilotResponse();
				Assert.assertEquals( row.get( JSON_CLASS ), pilot.getJsonClass() );
				Assert.assertEquals( Integer.valueOf( row.get( PILOT_ID ) ).intValue(),
						pilot.getPilotId().intValue() );
				Assert.assertEquals( row.get( URL4ICON ), pilot.getUrl4Icon() );
				Assert.assertEquals( row.get( ANCESTRY_NAME ), pilot.getAncestry().getName() );
				Assert.assertEquals( row.get( BLOODLINE_NAME ), pilot.getBloodline().getName() );
				Assert.assertEquals( row.get( GENDER ), pilot.getGender() );
				Assert.assertEquals( row.get( NAME ), pilot.getName() );
				Assert.assertEquals( Double.valueOf( row.get( SECURITY_STATUS ) ).doubleValue(),
						pilot.getSecurityStatus(), 0.01 );
				break;
			case CORPORATION_DATA_RESPONSE:
				final CorporationResponse corporation = this.neocomWorld.getCorporationResponse();
				Assert.assertEquals( row.get( JSON_CLASS ), corporation.getJsonClass() );
				Assert.assertEquals( Integer.valueOf( row.get( CORPORATION_ID ) ).intValue(),
						corporation.getCorporationId().intValue() );
				Assert.assertEquals( row.get( URL4ICON ), corporation.getUrl4Icon() );
				Assert.assertEquals( Integer.valueOf( row.get( ALLIANCE_ID ) ).intValue(),
						corporation.getAllianceId().intValue() );
				Assert.assertEquals( Integer.valueOf( row.get( CEO_ID ) ).intValue(),
						corporation.getCorporationPublicData().getCeoId().intValue() );
				break;
		}
	}
}
