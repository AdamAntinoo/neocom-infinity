package org.dimensinfin.eveonline.neocom.infinity.core.security;

public class SecurityConstants {
	public static final String ISSUER = "NeoCom.Infinity.Backend";
	public static final String SUBJECT = "ESI OAuth2 Authentication";
	public static final String TOKEN_UNIQUE_IDENTIFIER_FIELD_NAME = "uniqueId";
	public static final String TOKEN_ACCOUNT_NAME_FIELD_NAME = "accountName";
	public static final String TOKEN_CORPORATION_ID_FIELD_NAME = "corporationId";
	public static final String TOKEN_PILOT_ID_FIELD_NAME = "pilotId";
	public static final String SECRET = "The secret phrase to be used for JWT generation.";
	public static final String TOKEN_PREFIX = "Bearer ";
	public static final String AUTHORIZATION_HEADER_STRING = "Authorization";
	public static final String LOGIN_VERIFICATION_URL = "/api/v1/neocom/validateAuthorizationToken";
	public static final String CREDENTIAL_SUPPORT_URL = "/api/v1/neocom/support/**";
	public static final String GET_ITEM = "/api/v1/neoitem/item/**";
	public static final String STORE_CREDENTIAL_URL = "/api/v1/neocom/credentials/**";
	public static final String SERVER_STATUS_URL = "/api/v1/neocom/server**";
	public static final String ITEMSV1_URL = "/api/v1/universe/items/**";
	public static final String ITEMSV2_URL = "/api/v2/universe/items/**";
	public static final String SPACELOCATIONS_URL = "/api/v1/universe/spacelocations/**";
}
