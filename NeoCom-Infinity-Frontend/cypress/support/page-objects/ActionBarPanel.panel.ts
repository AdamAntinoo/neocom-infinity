// - SUPPORT
import { IsolationService } from "../IsolationService.support";
// - FIELDS
const TITLE: string = 'title';
const SUBTITLE: string = 'subtitle';

export class ActionBarPanel extends IsolationService {
    public validatePanel(row: any) {
        let title = this.decodeDataTableRow(row, TITLE).toUpperCase();
        console.log('[AppInfoPanel.validateAppInfoPanel]> TITLE=' + title);
        let subtitle = this.decodeDataTableRow(row, SUBTITLE);
        console.log('[AppInfoPanel.validateAppInfoPanel]> SUBTITLE=' + subtitle);

        cy.get('action-bar').within(($panel) => {
            cy.get('.title').contains(title);
            cy.get('.subtitle').contains(subtitle);
        });
    }
}
