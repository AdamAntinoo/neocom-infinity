// - CUCUMBER-PREPROCESSOR
import { Given } from "cypress-cucumber-preprocessor/steps";
import { When } from "cypress-cucumber-preprocessor/steps";
import { Then } from "cypress-cucumber-preprocessor/steps";
// - SERVICES
import { SupportService } from '../../support/SupportService.support';
// - CORE
import { PlatformConstants } from '../../../src/environments/PlatformConstants';
// - DOMAIN
import { NeoComCredential } from '../../../src/app/domain/NeoComCredential.domain';

const supportService = new SupportService();
const TITLE_VALIDATION = 'NeoCom.Infinity';

// - N E W   I M P L E M E N T A T I O N
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
    supportService.setToSession(PlatformConstants.CREDENTIAL_KEY, JSON.stringify(credential))
});
Given('there is no Credential on session storage', function () {
    supportService.removeFromSession(PlatformConstants.CREDENTIAL_KEY)
});

// - S T E P   M A C R O S
Given('the Dashboard page activation', function (recordId: string) {
    // Given the application NeoCom-Infinity-Frontend
    cy.viewport(1400, 900)
    cy.visit('/')
    cy.title().should('eq', TITLE_VALIDATION);
    cy.get('app-root').as('target-page').as('target')
    // Given a valid Credential on the session storage
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
    // Then the page "Dashboard" is activated
    const symbolicName = 'Dashboard'
    const tag = supportService.translateTag(symbolicName) // Do name replacement
    const route = supportService.translateRoute(symbolicName) // Gdt the route for this page name
    cy.log('>[Translation]> ' + symbolicName + ': ' + tag)
    cy.log('>[Route]> ' + route)
    cy.visit(route)
    cy.get('app-root').find(tag).as('target-page').as('target-panel').as('target')
        .should('exist')
});
