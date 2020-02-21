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
    cy.log('instanceCount=' + instanceCount);
    cy.log('instanceTag=' + instanceTag);
    cy.get('viewer-panel').find(instanceTag).should('have.length', instanceCount);
});
Then('the node-container has a expandable arrow indicator pointing right', function () {
    console.log('[THEN] the node-container has a expandable arrow indicator pointing right');
    let iconPath = '/assets/res-ui/drawable/arrowright.png';
    cy.get('node-container').find('.neocom-arrow-box').find('img').invoke('attr', 'src').should('equal', iconPath);
});
Then('the v1-group-container title has the glow attribute', function (int) {
    console.log('[THEN] the v1-group-container title has the glow attribute');
    cy.get('node-container').find('v1-group-container').find('span').invoke('attr', 'class').should('exist', 'glow');
});
 Then('the border color of the {string} is {string}', function (string, panelColor) {
     console.log('[THEN] the border color of the {string} is {string}');
     cy.get('node-container').find('div').invoke('attr', 'class').should('exist', panelColor);
});
       
//    ?And the opacity interaction for the "v1-group-container" is "disabled"
// Undefined.Implement with the following snippet:

// Then('the opacity interaction for the {string} is {string}', function (string, string2) {
//     // Write code here that turns the phrase above into concrete actions
//     return 'pending';
// });


       

When('there is a click on the first node-container', function (int) {
    console.log('[WHEN] there is a click on the first node-container');
    cy.get('node-container').contains('Frigate').click();

});
Then('the node-container has a expandable arrow indicator pointing down', function () {
    console.log('[THEN] the node-container has a expandable arrow indicator pointing right');
    let iconPath = '/assets/res-ui/drawable/arrowright.png';
    cy.get('node-container').find('.neocom-arrow-box').find('img')
        .invoke('attr', 'src').should('equal', iconPath);
    cy.get('node-container').find('.neocom-arrow-box').find('img')
        .invoke('attr', 'class').should('exist', 'neocom-arrow-expanded');
});
   
//    ?Then the v1 - group - container expands to render its contents
// Undefined.Implement with the following snippet:

// Then('the v{int}-group-container expands to render its contents', function (int) {
//     // Write code here that turns the phrase above into concrete actions
//     return 'pending';
// });
       
       
//    ?And the background color has changed to a shade of the border color
// Undefined.Implement with the following snippet:

// Then('the background color has changed to a shade of the border color', function () {
//     // Write code here that turns the phrase above into concrete actions
//     return 'pending';
// });
       
//    ?And there is a viewer - panel with "5" instances of "v1-group-container"
// Undefined.Implement with the following snippet:

// Then('there is a viewer-panel with {string} instances of {string}', function (string, string2) {
//     // Write code here that turns the phrase above into concrete actions
//     return 'pending';
// });
