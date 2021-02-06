package org.dimensinfin.eveonline.neocom.infinity.support.authorization.converter;

import java.util.Map;

import org.springframework.stereotype.Component;

import org.dimensinfin.eveonline.neocom.database.entities.Credential;
import org.dimensinfin.eveonline.neocom.infinity.support.CucumberTableToRequestConverter;
import org.dimensinfin.eveonline.neocom.infinity.support.RequestType;

@Component
public class CucumberTableToCredential extends CucumberTableToRequestConverter<Credential> {
	private static final String UNIQUE_CREDENTIAL = "uniqueCredential";
	private static final String ACCOUNT_ID = "accountId";
	private static final String ACCOUNT_NAME = "accountName";
	private static final String CORPORATION_ID = "corporationId";
	private static final String DATA_SOURCE = "dataSource";
	private static final String ACCESS_TOKEN = "accessToken";
	private static final String REFRESH_TOKEN = "refreshToken";
	private static final String SCOPE = "scope";
	private static final String WALLET_BALANCE = "walletBalance";
	private static final String ASSETS_COUNT = "assetsCount";
	private static final String MINING_RESOURCES_VALUE = "miningResourcesEstimatedValue";

// - G E T T E R S   &   S E T T E R S
	@Override
	public RequestType getType() {
		return RequestType.VALIDATE_AUTHORIZATION_TOKEN;
	}

	@Override
	public Credential convert( final Map<String, String> cucumberRow ) {
		return new Credential.Builder( Integer.parseInt( cucumberRow.get( ACCOUNT_ID ) ) )
				//				.withAccountId( Long.parseLong( cucumberRow.get( ACCOUNT_ID ) ) )
				.withAccountName( cucumberRow.get( ACCOUNT_NAME ) )
				.withCorporationId( Integer.parseInt( cucumberRow.get( CORPORATION_ID ) ) )
				.withDataSource( cucumberRow.get( DATA_SOURCE ) )
				.withAccessToken( cucumberRow.get( ACCESS_TOKEN ) )
				.withRefreshToken( cucumberRow.get( REFRESH_TOKEN ) )
				.withScope( cucumberRow.get( SCOPE ) )
				.build()
				.setWalletBalance( Double.parseDouble( cucumberRow.get( WALLET_BALANCE ) ) )
				.setAssetsCount( Integer.parseInt( cucumberRow.get( ASSETS_COUNT ) ) )
				.setMiningResourcesEstimatedValue( Double.parseDouble( cucumberRow.get( MINING_RESOURCES_VALUE ) ) );
	}
}
