// - CUCUMBER-PREPROCESSOR
import { Given } from "cypress-cucumber-preprocessor/steps";
import { When } from "cypress-cucumber-preprocessor/steps";
import { Then } from "cypress-cucumber-preprocessor/steps";
// - SERVICES
import { SupportService } from '../../support/SupportService.support';
// - CORE
import { platformConstants } from '../../../src/environments/platform-constants';
// - DOMAIN
import { NeoComCredential } from '../../../src/app/domain/NeoComCredential.domain';

const supportService = new SupportService();
const TITLE_VALIDATION = 'NeoCom.Infinity';

// - P A G E
Given('the page {string} is activated with parameters {int}', function (symbolicName: string, identifier: number) {
    const pageName = supportService.translateTag(symbolicName)
    switch (symbolicName) {
        case "Fitting Build Configuration":
            cy.visit('/industry/fittings/buildConfiguration/60320161')
            cy.get('app-root').find(pageName).as('target-page').as('target')
                .should('exist')
            break;
        default:
            break;
    }
})
Then('the target has the identity {int}', function (identity: number) {
    cy.get('@target').find('[id="' + identity + '"]').should('exist')
})
