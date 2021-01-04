// - CUCUMBER-PREPROCESSOR
import { Given } from "cypress-cucumber-preprocessor/steps";
import { When } from "cypress-cucumber-preprocessor/steps";
import { Then } from "cypress-cucumber-preprocessor/steps";
// - SERVICES
import { SupportService } from '../../support/SupportService.support';

const supportService = new SupportService();

// - S T E P   M A C R O S
When('activate the page Industry Manufacture Blueprints Page', function () {
    // Given the page "Resource Research Page" is activated
    const symbolicName = 'Blueprint Manufacture CostIndex'
    const tag = supportService.translateTag(symbolicName) // Do name replacement
    const route = supportService.translateRoute(symbolicName) // Get the route for this page name
    cy.log('>[Translation]> ' + symbolicName + ': ' + tag)
    cy.log('>[Route]> ' + route)
    cy.visit(route)
    cy.get('app-root').find(tag).as('target-page').as('target')
        .should('exist')
    // Then the page has the title "PLANETARY RESOURCE RESEARCH ENGINE"
    const title = 'BLUEPRINT MANUFACTURE COSTINDEX'
    cy.get('@target-page').find('.page-title').contains(title, { matchCase: false })
    // And the loading panel shows "Downloading known planetary data..."
    const loadingMessage = 'Downloading Blueprints...'
    cy.get('@target-page').find('.loading-message')
        .contains(loadingMessage, { matchCase: false })
    // When the loading panel completes
    cy.wait(3000)
})
