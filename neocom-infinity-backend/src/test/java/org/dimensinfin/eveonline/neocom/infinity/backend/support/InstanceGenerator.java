package org.dimensinfin.eveonline.neocom.infinity.backend.support;

import org.dimensinfin.eveonline.neocom.database.entities.Credential;
import org.dimensinfin.eveonline.neocom.infinity.backend.authorization.domain.AuthorizationTokenResponse;
import org.mockito.Mockito;
import org.springframework.boot.autoconfigure.security.saml2.Saml2RelyingPartyProperties;

import javax.servlet.http.Cookie;

import static org.dimensinfin.eveonline.neocom.infinity.backend.support.TestDataConstants.CredentialConstants.TEST_ACCOUNT_ID;

public class InstanceGenerator {
    public AuthorizationTokenResponse  generateAuthorizationTokenResponse(){
        return  new AuthorizationTokenResponse.Builder()
                .withCredential( this.generateCredential() )
                .withJwtToken( "FFJJWWTT-TTKKEENNFF" )
                .withEsiToken("-ESI-TOKEN-")
                .withCookie( Mockito.mock( Cookie.class ))
                .build();
    }
    public   Credential  generateCredential(){
        return new Credential.Builder(TestDataConstants.CredentialConstants.TEST_ACCOUNT_ID)
                .withDataSource(TestDataConstants.CredentialConstants.TEST_DATASOURCE)
                .withAccessToken(TestDataConstants.CredentialConstants.TEST_ACCESSTOKEN)
                .withAccountName(TestDataConstants.CredentialConstants.TEST_ACCOUNT_NAME)
                .withCorporationId(34)
                .withRefreshToken("ref")
                .withScope("scope")
                .withTokenType("-TYPE-")
                .build();
    }
}
