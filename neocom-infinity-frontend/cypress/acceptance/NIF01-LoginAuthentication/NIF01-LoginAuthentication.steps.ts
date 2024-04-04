// - CUCUMBER-PREPROCESSOR
import { Given } from "cypress-cucumber-preprocessor/steps";
import { When } from "cypress-cucumber-preprocessor/steps";
import { Then } from "cypress-cucumber-preprocessor/steps";
// - SERVICES
import { SupportService } from '../../support/step_definitions/SupportService.support';
import { NeoComCredential } from "../../../src/app/domain/NeoComCredential.domain";
import { platformConstants } from "../../../src/environments/platform-constants";

const supportService = new SupportService();

// - N E W   I M P L E M E N T A T I O N
Given('a clean cookie repository', function () {
    // - Clear all the application cookies
    cy.clearCookies()
})
Given('a valid Credential on the session storage', function () {
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
    supportService.setToSession(platformConstants.CREDENTIAL_KEY, JSON.stringify(credential))
})
