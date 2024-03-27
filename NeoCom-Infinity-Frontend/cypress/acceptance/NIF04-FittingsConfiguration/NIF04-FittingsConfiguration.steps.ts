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

// - T A R G E T
Then('the target has the identity {int}', function (identity: number) {
    cy.get('@target').find('[id="' + identity + '"]').should('exist')
})
Then('the target is tagged {string}', function (tagColor: string) {
    cy.get('@target').find('.neocom-panel').first().find('.panel-' + tagColor).should('exist')
})
Given('the target section {string}', function (sectionTitle: string) {
    cy.get('@target')
        .find('.section')
        .find('[cy-section-name="' + sectionTitle + '"]').parent().as('target')
    cy.get('@target')
        .find('[cy-section-name="' + sectionTitle + '"]')
        .contains(sectionTitle, { matchCase: false })
})
Then('the target has {int} groups', function (count: number) {
    cy.get('@target').find('.fitting-group').should('have.length', count)
})
Given('the target the group identified {string}', function (groupIdentifier: string) {
    cy.get('@target').find('[id="' + groupIdentifier + '"]').should('exist')
})
Then('the target has the group title {string}', function (title: string) {
    cy.get('@target').find('.group-title').contains(title, { matchCase: false })
})

// - F I E L D S
Then('field named {string} has contents {string}',
    function (fieldName: string, fieldValue: string) {
        cy.get('@target').within(($item) => {
            cy.get('[cy-field-name="' + fieldName + '"]')
                .find('[cy-field-value="' + fieldName + '"]').contains(fieldValue, { matchCase: false })
        })
    })
Then('image named {string} is visible', function (imageName: string) {
    cy.get('@target').find('[alt="' + imageName + '"]').should('be.visible')
})

// - D E P R E C A T E D
Then('image named {string} has link {string}', function (imageName: string, imageURL: string) {
    cy.get('@target').find('.neocom-icon').find('img')
        .should('be.visible')
    // .should('have.src', imageURL)
})
