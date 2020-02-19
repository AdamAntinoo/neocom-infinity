// - CUCUMBER-PREPROCESSOR
import { Given } from "cypress-cucumber-preprocessor/steps";
import { When } from "cypress-cucumber-preprocessor/steps";
import { Then } from "cypress-cucumber-preprocessor/steps";
// - SERVICES
import { IsolationService } from '../../support/IsolationService.support';

const PILOT_FITTINGS_ACTIVATION = 'PILOT-FITTINGS';
When('the PilotFittingsPage is activated with the request id {string}', function (activationCode) {
    console.log('[WHEN] the PilotFittingsPage is activated with the request id {string}');
    switch (activationCode) {
        case PILOT_FITTINGS_ACTIVATION:
            let urlRequest = '/fittings/pilot';
            new IsolationService().doLoginPage(); // Authenticate the acceptance environment.
            console.log('-[[WHEN] the PilotFittingsPage is activated with the request id {string}]> urlRequest=' + urlRequest);
            cy.visit(urlRequest);
            break;
    }
});
Then('there is a viewer-panel with {string} instances of {string}', function (instanceCount, instanceTag) {
    cy.get('viewer-panel').find(instanceTag).should('have.length', instanceCount);
});
