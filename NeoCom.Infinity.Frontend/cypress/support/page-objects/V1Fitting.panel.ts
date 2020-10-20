// - SUPPORT
import { IsolationService } from "../IsolationService.support";
// - FIELDS
const FITTING_NAME: string = 'neocom-name';
const NODE_CLASS: string = 'neocom-classname';

export class V1Fitting extends IsolationService {
    public validatePanel(row: any) {
        cy.log('[V1GroupContainer.validateAppInfoPanel]> row:' + JSON.stringify(row));
        let fittingName = this.decodeDataTableRow(row, FITTING_NAME);
        cy.log('[V1GroupContainer.validateAppInfoPanel]> FITTING_NAME=' + fittingName);
        let nodeClass = this.decodeDataTableRow(row, NODE_CLASS);
        cy.log('[V1GroupContainer.validateAppInfoPanel]> NODE_CLASS=' + nodeClass);

        cy.get('v1-fitting').within(($panel) => {
            cy.get('.neocom-name').contains( fittingName);
        // });
        // cy.get('v1-fitting').within(($panel) => {
            cy.get('.neocom-classname').contains(nodeClass);
        });
    }
}
