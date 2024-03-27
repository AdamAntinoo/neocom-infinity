// - SUPPORT
import { IsolationService } from "../IsolationService.support";
// - FIELDS
const TAB_COUNT: string = 'tab-count';
const ACTIVE_TAB_NAME: string = 'active-tab-nametab';

export class TabContainerPanel extends IsolationService {
    public validatePanel(row: any) {
        let tabCount = this.decodeDataTableRow(row, TAB_COUNT);
        console.log('[AppInfoPanel.validateAppInfoPanel]> TAB_COUNT=' + tabCount);
        let activeTab = this.decodeDataTableRow(row, ACTIVE_TAB_NAME);
        console.log('[AppInfoPanel.validateAppInfoPanel]> ACTIVE_TAB_NAME=' + activeTab);

        cy.get('tab-container-panel').find('li').should('have.length', tabCount)
        // TODO Check how to validate the tab selected since the style does not match
        // cy.get('tab-container-panel').find('li').within(($panel) => {
        //     cy.get('.active').contains(activeTab)
        // });
    }
}
