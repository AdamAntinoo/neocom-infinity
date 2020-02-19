// - SUPPORT
import { IsolationService } from "../IsolationService.support";
// - FIELDS
const ICON_PATH: string = 'neocom-icon';
const HULL_CLASS: string = 'hullClass';
// const ACTIVE_TAB_NAME: string = 'active-tab-name';

export class V1GroupContainer extends IsolationService {
    public validatePanel(row: any) {
        cy.log('[V1GroupContainer.validateAppInfoPanel]> row:' + JSON.stringify(row));
        let iconPath = this.decodeDataTableRow(row, ICON_PATH);
        cy.log('[V1GroupContainer.validateAppInfoPanel]> ICON_PATH=' + iconPath);
        let hullClass = this.decodeDataTableRow(row, HULL_CLASS);
        cy.log('[V1GroupContainer.validateAppInfoPanel]> HULL_CLASS=' + hullClass);

        cy.get('v1-group-container').within(($panel) => {
            cy.get('.neocom-icon').invoke('attr', 'src').should('equal', iconPath);
        });
        cy.get('v1-group-container').within(($panel) => {
            cy.get('#neocom-ts20').contains(hullClass);
        });
    }
}
