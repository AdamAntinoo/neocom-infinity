// - CUCUMBER-PREPROCESSOR
import { Given } from "cypress-cucumber-preprocessor/steps";
import { When } from "cypress-cucumber-preprocessor/steps";
import { Then } from "cypress-cucumber-preprocessor/steps";
// - SERVICES
import { SupportService } from '../../support/SupportService.support';
import { NeoComCredential } from "../../../src/app/domain/NeoComCredential.domain";
import { PlatformConstants } from "../../../src/environments/PlatformConstants";

const supportService = new SupportService();

// - N E W   I M P L E M E N T A T I O N
Given('a clean cookie repository', function () {
    // - Clear all the application cookies
    cy.clearCookies()
})
Given('a valid JWT Token on the session storage', function () {
    // - Set a valid Credential on the session storage.
    const credential = new NeoComCredential({
        "uniqueId": "tranquility/92223647",
        "accountId": 92223647,
        "accountName": "Beth Ripley",
        "corporationId": 98384726,
        "assetsCount": 1476,
        "walletBalance": 6.309543632E8,
        "raceName": "Amarr",
        "dataSource": "tranquility"
    })
    supportService.setToSession(PlatformConstants.CREDENTIAL_KEY, JSON.stringify(credential))
})
Given('a valid Credential on the session storage', function () {
    // - Set a valid Credential on the session storage.
    const jwtToken : string ="eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJFU0kgT0F1dGgyIEF1dGhlbnRpY2F0aW9uIiwiY29ycG9yYXRpb25JZCI6MTQyNzY2MTU3MywiYWNjb3VudE5hbWUiOiJBZGFtIEFudGlub28iLCJpc3MiOiJOZW9Db20uSW5maW5pdHkuQmFja2VuZCIsInVuaXF1ZUlkIjoidHJhbnF1aWxpdHkvOTIwMDIwNjciLCJwaWxvdElkIjo5MjAwMjA2N30.6JgBvtHyhvD8aY8-I4075tb433mYMpn9sNeYCkIO28LbhqVR4CZ-x1t_sk4IOLLtzSN07bF4c7ZceWw_ta4Brw"
    supportService.setToSession(PlatformConstants.CREDENTIAL_KEY, jwtToken)
})
Given('a valid NEOCOM-INFINITY cookie', function () {
    cy.setCookie('NEOCOM-INFINITY', "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJFU0kgT0F1dGgyIEF1dGhlbnRpY2F0aW9uIiwiY29ycG9yYXRpb25JZCI6MTQyNzY2MTU3MywiYWNjb3VudE5hbWUiOiJBZGFtIEFudGlub28iLCJpc3MiOiJOZW9Db20uSW5maW5pdHkuQmFja2VuZCIsInVuaXF1ZUlkIjoidHJhbnF1aWxpdHkvOTIwMDIwNjciLCJwaWxvdElkIjo5MjAwMjA2N30.6JgBvtHyhvD8aY8-I4075tb433mYMpn9sNeYCkIO28LbhqVR4CZ-x1t_sk4IOLLtzSN07bF4c7ZceWw_ta4Brw")
})
