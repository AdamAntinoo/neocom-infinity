// - CUCUMBER-PREPROCESSOR
import { Given } from "cypress-cucumber-preprocessor/steps";
import { When } from "cypress-cucumber-preprocessor/steps";
import { Then } from "cypress-cucumber-preprocessor/steps";
// - SERVICE
import { SupportService } from '../../support/SupportService.support';

const TITLE_VALIDATION = 'NeoCom.Infinity';
const supportService = new SupportService();

// - A P P L I C A T I O N
Given('the application NeoCom-Infinity', function () {
    cy.viewport(1400, 900)
    cy.visit('/')
    cy.title().should('eq', TITLE_VALIDATION);
    cy.get('app-root').as('target-page').as('target')
})

Given('the application NeoCom-Infinity-frontend', function () {
    cy.viewport(1400, 900)
    cy.visit('/')
    cy.title().should('eq', TITLE_VALIDATION);
    cy.get('app-root').as('target-page').as('target')
})

// - P A G E   A C T I V A T I O N
Then('the page {string} is activated', function (symbolicName: string) {
    const tag = supportService.translateTag(symbolicName) // Do name replacement
    const route = supportService.translateRoute(symbolicName) // Get the route for this page name
    cy.log('>[Translation]> ' + symbolicName + ': ' + tag)
    cy.log('>[Route]> ' + route)
    cy.visit(route)
    cy.get('app-root').find(tag).as('target-page').as('target')
        .should('exist')
});
Then('the page has the title {string}', function (title: string) {
    cy.get('@target-page').find('.page-title').contains(title, { matchCase: false })
});

/**
 * On the NeoCom application panels may be on a hierarchical setup and not only as la row list.
 */
Then('the page {string} has {int} panels', function (symbolicName: string, panelCount: number) {
    const tag = supportService.translateTag(symbolicName) // Do name replacement
    cy.get('app-root').find(tag).find('.panel')
        .should('have.length', panelCount)
});

// - S P I N N E R
Then('the loading panel shows {string}', function (loadingMessage: string) {
    cy.get('@target-page').find('.loading-message')
        .contains(loadingMessage, { matchCase: false })
});
When('the loading panel completes', function () {
    cy.wait(2000)
});

// - T A R G E T   S E L E C T I O N
Given('the target is the panel of type {string}', function (symbolicName: string) {
    const tag = supportService.translateTag(symbolicName) // Do name replacement
    cy.log('>[translation]> ' + symbolicName + ' -> ' + tag)
    cy.get('@target-page').find(tag)
        .as('target-panel').as('target')
});
Given('the target the {string} with id {string}', function (symbolicName: string, recordId: string) {
    const tag = supportService.translateTag(symbolicName) // Do name replacement
    cy.log('>[translation]> ' + symbolicName + ' -> ' + tag)
    cy.get('@target-panel').find(tag).find('[id="' + recordId + '"]').as('target')
        .should('exist')
});

// - T A R G E T   I N T E R A C T I O N
When('the target is clicked', function () {
    cy.get('@target').scrollIntoView().click()
});

// - T A R G E T   C O N T E N T S
Then('the target has the title {string}', function (title: string) {
    cy.get('@target').find('.panel-title').contains(title, { matchCase: false })
});
Then('the target has {int} {string}', function (count: number, symbolicName: string) {
    const tag = supportService.translateTag(symbolicName) // Do name replacement
    cy.log('>[translation]> ' + symbolicName + ' -> ' + tag)
    cy.get('@target').within(($item) => {
        cy.get(tag).should('have.length', count)
    })
})

// - F I E L D S
Then('field named {string} with label {string} has contents {string}',
    function (fieldName: string, fieldLabel: string, fieldValue: string) {
        cy.get('@target').within(($item) => {
            cy.get('[cy-field-label="' + fieldName + '"]').contains(fieldLabel, { matchCase: false })
        })
        cy.get('@target').within(($item) => {
            cy.get('.label').contains(fieldLabel, { matchCase: false }).parent()
                .find('[cy-field-value="' + fieldName + '"]').contains(fieldValue, { matchCase: false })
        })
    })

// - F E A T U R E   S E L E C T I O N
When('the Feature with label {string} is clicked the destination is the Page {string}',
    function (label: string, destinationTag: string) {
        const destination = supportService.translateTag(destinationTag)
        cy.get('@target-page')
            .find(supportService.translateTag('feature'))
            .contains(label, { matchCase: false }).parent()
            .scrollIntoView().click();
        cy.get('app-root').find(destination).as('target-page').as('target').should('exist')
    })
