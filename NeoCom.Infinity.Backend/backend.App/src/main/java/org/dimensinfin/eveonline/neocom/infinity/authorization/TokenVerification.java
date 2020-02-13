package org.dimensinfin.eveonline.neocom.infinity.authorization;

import org.dimensinfin.eveonline.neocom.auth.TokenTranslationResponse;
import org.dimensinfin.eveonline.neocom.auth.VerifyCharacterResponse;
import org.dimensinfin.eveonline.neocom.infinity.core.exceptions.ErrorInfo;
import org.dimensinfin.eveonline.neocom.infinity.core.exceptions.NeoComSBException;
import org.dimensinfin.eveonline.neocom.provider.ESIDataProvider;

public class TokenVerification {
	private String authCode;
	private String dataSource;
	private TokenTranslationResponse tokenTranslationResponse;
	private String peck;
	private VerifyCharacterResponse verifyCharacterResponse;

	public String getAuthCode() {
		return authCode;
	}

	public String getDataSource() {
		if ( null == this.dataSource) return ESIDataProvider.DEFAULT_ESI_SERVER;
		return this.dataSource;
	}

	public TokenTranslationResponse getTokenTranslationResponse() {
		return tokenTranslationResponse;
	}

	public String getPeck() {
		return this.peck;
	}

	public VerifyCharacterResponse getVerifyCharacterResponse() {
		return verifyCharacterResponse;
	}

	public int getAccountIdentifier() {
		if ( null != this.verifyCharacterResponse)
			return Long.valueOf(this.verifyCharacterResponse.getCharacterID()).intValue();
		else throw new NeoComSBException( ErrorInfo.INVALID_CREDENTIAL_IDENTIFIER );
	}

	public TokenVerification setAuthCode(final String authCode) {
		this.authCode = authCode;
		return this;
	}

	public TokenVerification setDataSource(final String dataSource) {
		this.dataSource = dataSource;
		return this;
	}

	public TokenVerification setTokenTranslationResponse(TokenTranslationResponse tokenTranslationResponse) {
		this.tokenTranslationResponse = tokenTranslationResponse;
		return this;
	}

	public TokenVerification setPeck(String peck) {
		this.peck = peck;
		return this;
	}

	public TokenVerification setVerifyCharacterResponse(VerifyCharacterResponse verifyCharacterResponse) {
		this.verifyCharacterResponse = verifyCharacterResponse;
		return this;
	}
}
