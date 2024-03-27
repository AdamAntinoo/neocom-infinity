// - CUCUMBER-PREPROCESSOR
import { Given } from "cypress-cucumber-preprocessor/steps";
import { When } from "cypress-cucumber-preprocessor/steps";
import { Then } from "cypress-cucumber-preprocessor/steps";
// - SERVICES
import { IsolationService } from '../../support/IsolationService.support';

const PILOT_FITTINGS_ACTIVATION = 'PILOT-FITTINGS';

let selector: string;

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

Then('there is a panel of type v1-group-container with name {string} and stack counter of {string}', function (shipClass, stackCount) {
    cy.log('[THEN] there is a panel of type v1-group-container with name {string} and stack counter of {string}: ' + shipClass);
    cy.log('[THEN] there is a panel of type v1-group-container with name {string} and stack counter of {string}: ' + stackCount);
    cy.get('node-container').get('v1-group-container').within(($panel) => {
        cy.get('.neocom-name').contains(shipClass)
        cy.get('.stack-counter').contains(stackCount);
    });
});
When('we click on the v1-group-container with name {string}', function (selector) {
    console.log('[THEN] when we click on the v1-group-container with name {string}: ' + selector);
    // selector = fieldContent
    cy.get('node-container').get('v1-group-container').within(($panel) => {
        cy.get('.neocom-name').contains(selector).click();
    });
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
Then('the border color of the {string} is {string}', function (panelType, panelColor) {
    console.log('[THEN] the border color of the {string} is {string}');
    // cy.get('node-container').get(panelType).first().find('div').invoke('attr', 'class').should('exist', panelColor);
    cy.get('node-container').find('div').invoke('attr', 'class').contains('neocom-panel' + ' ' + panelColor).then(() => {
        cy.get(panelType)
    });
// });
    // cy.get('node-container').get('div').invoke('attr', 'class').should('equal', 'neocom-panel' + ' ' + panelColor).and.get(panelType)
    // });
});
When('there is a click on the first node-container', function (int) {
    console.log('[WHEN] there is a click on the first node-container');
    cy.get('node-container').first().contains('Frigate').click();

});
Then('the node-container has a expandable arrow indicator pointing down', function () {
    console.log('[THEN] the node-container has a expandable arrow indicator pointing right');
    let iconPath = '/assets/res-ui/drawable/arrowright.png';
    cy.get('node-container').find('.neocom-arrow-box').find('img')
        .invoke('attr', 'src').should('equal', iconPath);
    cy.get('node-container').find('.neocom-arrow-box').find('img')
        .invoke('attr', 'class').should('exist', 'neocom-arrow-expanded');
});
When('the background color has changed to a shade of the border color {string}', function (panelColor) {
    console.log('[WHEN] the background color has changed to a shade of the border color');
    const color = '.' + panelColor + '-expanded';
    cy.log('[WHEN the background color has changed]> color: ' + color)
    // cy.get('node-container').eq(1).find('div').invoke('attr', 'class').should('equal', color);
    cy.get('node-container').eq(1).find('div').invoke('attr', 'class').get(color);
});
When('there is a click on the v1-group-container with name {string}', function (fieldContent) {
    console.log('[WHEN] there is a click on the v1-group-container with name {string}');
    selector = fieldContent
    cy.get('node-container').get('v1-group-container').within(($panel) => {
        cy.get('.neocom-name').contains(fieldContent).click();
        // cy.log('[WHEN] there is a click on the v1-group-container with name {string}: '+JSON.stringify(selectedPanel));
    });
});
Then('the selected panel has a stack counter of {int}', function (stackCounter) {
    cy.log('[THEN] the selected panel has a stack counter of {int}: ' + selector);
    cy.log('[THEN] the selected panel has a stack counter of {int}: ' + stackCounter);
    cy.get('node-container').get('v1-group-container').within(($panel) => {
        cy.get('.neocom-name').contains(selector)/*.within(($panel) => {*/
        cy.get('.stack-counter').contains(stackCounter);
        // });
    });
});

//    ?And there is a click on the v1 - group - container with text "Epithal"
// Undefined.Implement with the following snippet:

// Then('there is a click on the v{int}-group-container with text {string}', function (int, string) {
//     // Write code here that turns the phrase above into concrete actions
//     return 'pending';
// });

//    ?Then the selected panel has a stock counter of 3
// Undefined.Implement with the following snippet:

// Then('the selected panel has a stock counter of {int}', function (int) {
//     // Write code here that turns the phrase above into concrete actions
//     return 'pending';
// });

//    ?And there is a viewer - panel with "3" instances of "v1-fitting"
// Undefined.Implement with the following snippet:

// Then('there is a viewer-panel with {string} instances of {string}', function (string, string2) {
//     // Write code here that turns the phrase above into concrete actions
//     return 'pending';
// });

//    ?And teh selected panel is set to the v1 - fitting with text "EI Oruga N2"
// Undefined.Implement with the following snippet:

// Then('teh selected panel is set to the v{int}-fitting with text {string}', function (int, string) {
//     // Write code here that turns the phrase above into concrete actions
//     return 'pending';
// });

//    ?And the selected panel has the styles "panel-orange"
// Undefined.Implement with the following snippet:

// Then('the selected panel has the styles {string}', function (string) {
//     // Write code here that turns the phrase above into concrete actions
//     return 'pending';
// });

//    ?And the selected panel is a "v1-fitting" with the next fields
//     | neocom - icon | neocom - selectable - name |
//        | https://image.eveonline.com/Type/655_64.png | EI Oruga N2            |
// Undefined.Implement with the following snippet:

// Then('the selected panel is a {string} with the next fields', function (string, dataTable) {
//     // Write code here that turns the phrase above into concrete actions
//     return 'pending';
// });
