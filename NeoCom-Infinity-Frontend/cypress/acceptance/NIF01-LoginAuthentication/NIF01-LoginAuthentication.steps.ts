// - CUCUMBER-PREPROCESSOR
import { Given } from "cypress-cucumber-preprocessor/steps";
import { When } from "cypress-cucumber-preprocessor/steps";
import { Then } from "cypress-cucumber-preprocessor/steps";
// - SERVICES
import { SupportService } from '../../support/SupportService.support';

// - N E W   I M P L E M E N T A T I O N
Given('a clean cookie repository', function () {
    // - Clear all the application cookies
    cy.clearCookies()
})
