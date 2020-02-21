// - CYPRESS
import { Given } from "cypress-cucumber-preprocessor/steps";
import { When } from "cypress-cucumber-preprocessor/steps";
import { Then } from "cypress-cucumber-preprocessor/steps";
// - ASSERTION
// import { expect } from 'chai';
// - PAGE OBJECTS
import { AppInfoPanel } from "../../support/page-objects/AppInfoPanel.panel";
import { ServerInfoPanel } from "../../support/page-objects/ServerInfoPanel.panel";
import { CorporationDataPanel } from "../../support/page-objects/CorporationDataPanel.panel";
import { PilotPublicDataPanel } from "../../support/page-objects/PilotPublicDataPanel.panel";
import { PilotRenderPanel } from '../../support/page-objects/PilotRender.panel';
import { ActionBarPanel } from '../../support/page-objects/ActionBarPanel.panel';
import { TabContainerPanel } from '../../support/page-objects/TabContainerPanel.panel';
import { ViewerPanel } from '../../support/page-objects/ViewerPanel.panel';
import { V1GroupContainer } from '../../support/page-objects/V1GroupContainer.panel';

// let appInfoPanel: AppInfoPanel;
// let serverInfoPanel: ServerInfoPanel;
let corporationDataPanel: CorporationDataPanel;
let pilotPublicDataPanel: PilotPublicDataPanel;
let pilotRender: PilotRenderPanel;
// let actionBarPanel: ActionBarPanel;
// let tabContainerPanel: TabContainerPanel;
// let viewerPanel: ViewerPanel;
// let v1GroupContainer: V1GroupContainer;

Given('one instance of AppInfoPanel', function () {
    console.log('[GIVEN] one instance of AppInfoPanel');
    const appInfoPanel: AppInfoPanel = new AppInfoPanel();
    expect(appInfoPanel).to.not.be.null;
    cy.get('app-root').find('app-info-panel').should('have.length', 1)
});
Given('one instance of ServerInfoPanel', function () {
    console.log('[GIVEN] one instance of ServerInfoPanel');
    const serverInfoPanel: ServerInfoPanel = new ServerInfoPanel();
    expect(serverInfoPanel).to.not.be.null;
    cy.get('app-root').find('server-info-panel').should('have.length', 1)
});
Given('one instance of CorporationDataPanel', function () {
    corporationDataPanel = new CorporationDataPanel();
});
Given('one instance of PilotPublicDataPanel', function () {
    pilotPublicDataPanel = new PilotPublicDataPanel();
});
Given('one instance of PilotRenderPanel', function () {
    pilotRender = new PilotRenderPanel();
});
Given('one instance of ActionBarPanel', function () {
    console.log('[GIVEN] one instance of ActionBarPanel');
    const actionBarPanel: ActionBarPanel = new ActionBarPanel();
    expect(actionBarPanel).to.not.be.null;
    cy.get('app-root').find('action-bar').should('have.length', 1)
});
Given('one instance of TabContainerPanel', function () {
    console.log('[GIVEN] one instance of TabContainerPanel');
    const tabContainerPanel: TabContainerPanel = new TabContainerPanel();
    expect(tabContainerPanel).to.not.be.null;
    cy.get('app-root').find('tab-container-panel').should('have.length', 1)
});
Given('one instance of ViewerPanel', function () {
    console.log('[GIVEN] one instance of ViewerPanel');
    const viewerPanel: ViewerPanel = new ViewerPanel();
    expect(viewerPanel).to.not.be.null;
    cy.get('app-root').find('viewer-panel').should('have.length', 1)
});
Given('one instance of V1GroupContainer', function () {
    console.log('[GIVEN] one instance of V1GroupContainer');
    const v1GroupContainer: V1GroupContainer = new V1GroupContainer();
    expect(v1GroupContainer).to.not.be.null;
    cy.get('app-root').find('v1-group-container').should('have.length', 1)
});
Given('at least one instance of V1GroupContainer', function () {
    console.log('[GIVEN] at least one instance of V1GroupContainer');
    const v1GroupContainer: V1GroupContainer = new V1GroupContainer();
    expect(v1GroupContainer).to.not.be.null;
    cy.get('app-root').find('v1-group-container').should('have.length.gt', 0)
});

Then('there is a {string} with the next fields', (panelType, dataTable) => {
    cy.log('[THEN] there is a {string} with the next fields');
    cy.log('[THEN] panelType=' + panelType);
    // cy.log('[THEN] panelType=' + panelType);
    const row = dataTable.hashes()[0];
    switch (panelType) {
        case 'appinfo-panel':
            const appInfoPanel: AppInfoPanel = new AppInfoPanel();
            expect(appInfoPanel).to.not.be.null;
            appInfoPanel.validatePanel(row);
            break;
        case 'serverinfo-panel':
            const serverInfoPanel: ServerInfoPanel = new ServerInfoPanel();
            expect(serverInfoPanel).to.not.be.null;
            serverInfoPanel.validatePanel(row);
            break;
        case 'corporation-public-data-panel':
            corporationDataPanel.validatePanel(row);
            break;
        case 'pilot-public-data-panel':
            pilotPublicDataPanel.validatePanel(row);
            break;
        case 'pilot-render':
            pilotRender.validatePanel(row);
            break;
        case 'action-bar':
            const actionBarPanel: ActionBarPanel = new ActionBarPanel();
            expect(actionBarPanel).to.not.be.null;
            actionBarPanel.validatePanel(row);
            break;
        case 'tab-container-panel':
            const tabContainerPanel: TabContainerPanel = new TabContainerPanel();
            expect(tabContainerPanel).to.not.be.null;
            tabContainerPanel.validatePanel(row);
            break;
//         case 'viewer-panel':
//             const viewerPanel: ViewerPanel = new ViewerPanel();
//             expect(viewerPanel).to.not.be.null;
//   viewerPanel.validatePanel(row);
//             break;
        case 'v1-group-container':
            const v1GroupContainer: V1GroupContainer = new V1GroupContainer();
            expect(v1GroupContainer).to.not.be.null;
            v1GroupContainer.validatePanel(row);
            break;
    }
});
