// - SUPPORT
import { IsolationService } from "../IsolationService.support";
// - FIELDS
const TAB_COUNT: string = 'tab-count';
const ACTIVE_TAB: string = 'active-tab';
const ACTIVE_TAB_NAME: string = 'active-tab-name';

export class ViewerPanel extends IsolationService {
    public validatePanel(row: any) {
        let tabCount = this.decodeDataTableRow(row, TAB_COUNT);
        console.log('[AppInfoPanel.validateAppInfoPanel]> TAB_COUNT=' + tabCount);
        let activeTab = this.decodeDataTableRow(row, ACTIVE_TAB_NAME);
        console.log('[AppInfoPanel.validateAppInfoPanel]> ACTIVE_TAB_NAME=' + activeTab);

        cy.get('tab-container-panel').find('li').should('have.length', tabCount)
        cy.get('tab-container-panel').find('.active').contains(activeTab)
    }
}
