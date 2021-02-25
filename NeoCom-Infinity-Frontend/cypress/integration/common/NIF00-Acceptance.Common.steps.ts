// - CUCUMBER-PREPROCESSOR
import { Given } from "cypress-cucumber-preprocessor/steps"
import { When } from "cypress-cucumber-preprocessor/steps"
import { Then } from "cypress-cucumber-preprocessor/steps"
// - SERVICE
import { SupportService } from '../../support/SupportService.support'
import { PlatformConstants } from "../../../src/environments/PlatformConstants"
import { NeoComCredential } from "../../../src/app/domain/NeoComCredential.domain"

const TITLE_VALIDATION = 'NeoCom.Infinity'
const supportService = new SupportService()

// - A U T H E N T I C A T I O N
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
    supportService.setToSession(PlatformConstants.CREDENTIAL_KEY, JSON.stringify(credential))
})
Given('a valid JWT Token on the session storage', function () {
    // - Set a valid Credential on the session storage.
    const jwtToken: string = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJFU0kgT0F1dGgyIEF1dGhlbnRpY2F0aW9uIiwiY29ycG9yYXRpb25JZCI6MTQyNzY2MTU3MywiYWNjb3VudE5hbWUiOiJBZGFtIEFudGlub28iLCJpc3MiOiJOZW9Db20uSW5maW5pdHkuQmFja2VuZCIsInVuaXF1ZUlkIjoidHJhbnF1aWxpdHkvOTIwMDIwNjciLCJwaWxvdElkIjo5MjAwMjA2N30.6JgBvtHyhvD8aY8-I4075tb433mYMpn9sNeYCkIO28LbhqVR4CZ-x1t_sk4IOLLtzSN07bF4c7ZceWw_ta4Brw"
    supportService.setToSession(PlatformConstants.JWTTOKEN_KEY, jwtToken)
})
Given('a valid NEOCOM-INFINITY cookie', function () {
    cy.setCookie('NEOCOM-INFINITY', "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJFU0kgT0F1dGgyIEF1dGhlbnRpY2F0aW9uIiwiY29ycG9yYXRpb25JZCI6MTQyNzY2MTU3MywiYWNjb3VudE5hbWUiOiJBZGFtIEFudGlub28iLCJpc3MiOiJOZW9Db20uSW5maW5pdHkuQmFja2VuZCIsInVuaXF1ZUlkIjoidHJhbnF1aWxpdHkvOTIwMDIwNjciLCJwaWxvdElkIjo5MjAwMjA2N30.6JgBvtHyhvD8aY8-I4075tb433mYMpn9sNeYCkIO28LbhqVR4CZ-x1t_sk4IOLLtzSN07bF4c7ZceWw_ta4Brw")
})

// - A P P L I C A T I O N
Given('the application NeoCom-Infinity', function () {
    cy.viewport(1400, 900)
    cy.visit('/')
    cy.title().should('eq', TITLE_VALIDATION)
    cy.get('app-root').as('target-page').as('target')
})

Given('the application NeoCom-Infinity-Frontend', function () {
    cy.viewport(1400, 900)
    cy.visit('/')
    cy.title().should('eq', TITLE_VALIDATION)
    cy.get('app-root').as('target-page').as('target')
})

// - P A G E   A C T I V A T I O N
Then('the page {string} is activated', function (symbolicName: string) {
    const tag = supportService.translateTag(symbolicName) // Do name replacement
    const route = supportService.translateRoute(symbolicName) // Get the route for this page name
    cy.log('>[Translation]> ' + symbolicName + ': ' + tag)
    cy.log('>[Route]> ' + route)
    cy.visit(route)
    cy.get('app-root').find(tag).as('target-page').as('target-panel').as('target')
        .should('exist')
})
Then('the page has the title {string}', function (title: string) {
    cy.get('@target-page').find('.page-title').contains(title, { matchCase: false })
})
Then('the page is page {string}', function (symbolicName: string) {
    const page = supportService.translateTag(symbolicName) // Do name replacement
    cy.get('app-root').find(page).should('exist')
})

/**
 * On the NeoCom application panels may be on a hierarchical setup and not only as la row list.
 */
Then('the page {string} has {int} panels', function (symbolicName: string, panelCount: number) {
    const tag = supportService.translateTag(symbolicName) // Do name replacement
    cy.get('app-root').find(tag).find('.panel')
        .should('have.length', panelCount)
})
Then('the page {string} has {int} sections', function (symbolicName: string, sectionCount: number) {
    const tag = supportService.translateTag(symbolicName) // Do name replacement
    cy.get('app-root').find(tag).find('.section')
        .should('have.length', sectionCount)
})

// - S E C T I O N S
Then('the section {string} has {int} panels', function (sectionName: string, panelCount: number) {
    // const tag = supportService.translateTag(sectionName) // Do name replacement
    cy.get('app-root').find('[cy-section-name="' + sectionName + '"]').find('.panel')
        .should('have.length', panelCount)
})

// - S P I N N E R
Then('the loading panel shows {string}', function (loadingMessage: string) {
    cy.get('@target-page').find('.loading-message')
        .contains(loadingMessage, { matchCase: false })
})
When('the loading panel completes', function () {
    cy.wait(3000)
})

// - T A R G E T   S E L E C T I O N
Given('the target is the panel of type {string}', function (symbolicName: string) {
    const tag = supportService.translateTag(symbolicName) // Do name replacement
    cy.log('>[translation]> ' + symbolicName + ' -> ' + tag)
    cy.get('@target-page').find(tag)
        .as('target-panel').as('target')
})
Given('the target the {string} with id {string}', function (symbolicName: string, recordId: string) {
    const tag = supportService.translateTag(symbolicName) // Do name replacement
    cy.log('>[translation]> ' + symbolicName + ' -> ' + tag)
    cy.get('@target-panel').find(tag).find('[id="' + recordId + '"]').as('target')
        .should('exist')
})

// - T A R G E T   I N T E R A C T I O N
When('the target is clicked', function () {
    cy.get('@target').scrollIntoView().click()
})

// - T A R G E T   C O N T E N T S
Then('the target has the title {string}', function (title: string) {
    cy.get('@target').find('.panel-title').contains(title, { matchCase: false })
})
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
                .find('[cy-field-value="' + fieldName + '"]')
                .contains(fieldValue, { matchCase: false })
            // .contains(new RegExp(fieldValue, "g"))
            // .should("contain.text", fieldValue) // This is an exact match of the field contents.
        })
    })
Then('field named {string} has contents {string}',
    function (fieldName: string, fieldValue: string) {
        cy.get('@target').within(($item) => {
            cy.get('.field').find('[cy-field-value="' + fieldName + '"]')
                .contains(fieldValue, { matchCase: false })
        })
    })
Then('field named {string} should exist', function (fieldName: string) {
    cy.get('@target').within(($item) => {
        cy.get('.field').find('[cy-field-value="' + fieldName + '"]')
            .should('exist')
    })
})
Then('field named {string} should not exist', function (fieldName: string) {
    cy.get('@target').within(($item) => {
        cy.get('.field').find('[cy-field-value="' + fieldName + '"]')
            .should('not.exist')
    })
})

// - F E A T U R E   S E L E C T I O N
When('the Feature with label {string} is clicked the destination is the Page {string}',
    function (label: string, destinationTag: string) {
        const destination = supportService.translateTag(destinationTag)
        cy.get('@target-page')
            .find(supportService.translateTag('feature-button'))
            .contains(label, { matchCase: false }).parent()
            .scrollIntoView().click()
        cy.get('app-root').find(destination).as('target-page').as('target-panel').as('target').should('exist')
    })
Then('the Feature has the label {string}', function (title: string) {
    cy.get('@target').find('.feature-label').contains(title, { matchCase: false })
})

// - L I N K S
Then('target has link pointing {string}', function (linkDestination: string) {
    cy.get('@target')
    .find('a').should("have.attr", "href", linkDestination);
})

// - I M A G E   B U T T O N S
Then('target has an actionable image named {string}', function (buttonName: string) {
    cy.get('@target').find('[cy-name="' + buttonName + '"]').should('exist')
})

// - A L T E R N A T E   B A C K E N D   R E S P O N S E S
Given('response {string} for {string}', function (responseCode: string, endpoint: string) {
    const tag = supportService.translateTag(endpoint) // Do name replacement
    cy.setCookie(tag, responseCode)
})
