// - CORE
import { environment } from '../../../src/environments/environment';

export class SupportService {
    private translationTable: any = {}
    private routeTranslationTable: any = {}

    constructor() {
        // - PAGES
        this.translationTable['Start'] = 'v1-start-page'
        this.translationTable['Pilot Dashboard'] = 'dashboard-home-page'
        this.translationTable['Industry Dashboard'] = 'industry-dashboard-page'
        this.translationTable['Planetary Dashboard Page'] = 'dashboard-page'
        this.translationTable['Loyalty Recommendations'] = 'v1-loyalty-recommendations-page'
        this.translationTable['Blueprint Manufacture CostIndex'] = 'v1-blueprint-list-page'
        this.translationTable['Mining Operations'] = 'v1-mining-operations-page'

        this.translationTable['Fitting Build Configuration'] = 'v1-industry-fitting-build-configuration-page'
        this.translationTable['Planetary Enter Data Page'] = 'v1-enter-planet-data-page'
        this.translationTable['Resource Research Page'] = 'v1-resource-research-page'
        // - PANELS
        this.translationTable['app-component'] = 'app-root'
        this.translationTable['app-info'] = 'app-info-panel'
        this.translationTable['server-info'] = 'server-info-panel'
        this.translationTable['pilot-list'] = 'v1-pilot-panel'
        this.translationTable['available-blueprints'] = 'v1-available-blueprints-panel'
        this.translationTable['mining-operations'] = 'v1-miningoperations-panel'

        this.translationTable['feature'] = 'v2-feature'

        this.translationTable['fitting-summary'] = 'v1-fitting-summary-panel'
        this.translationTable['fitting-saved-configuration'] = 'v1-fitting-configuration-panel'
        this.translationTable['fitting-target-configuration'] = 'v1-fitting-configuration-panel'
        this.translationTable['fitting-contents'] = 'v1-fitting-contents-panel'
        this.translationTable['system-selection'] = 'v1-system-selection-panel'
        // this.translationTable['fitting-module'] = 'v1-fitting-item'
        this.translationTable['fitting-group'] = 'v1-fitting-group'
        this.translationTable['fitting-build-content'] = 'v1-fitting-build-content'
        this.translationTable['fitting-item'] = 'v1-fitting-item'
        this.translationTable['market-data'] = 'v1-market-data'
        this.translationTable['known-systems'] = 'v1-known-systems-panel'
        this.translationTable['system-planets'] = 'v1-known-planets-panel'
        this.translationTable['selected-planets'] = 'v1-selected-planets-panel'
        this.translationTable['output-resources'] = 'v1-output-resources-panel'
        this.translationTable['selected-resources'] = 'v1-selected-resources-panel'
        // - RENDERS
        this.translationTable['exception'] = 'div'
        this.translationTable['pilot'] = 'v2-pilot'
        this.translationTable['processed-blueprint'] = 'v1-blueprint'
        this.translationTable['feature-button'] = 'v1-feature-button'
        this.translationTable['planetary-system'] = 'v1-planetary-system'
        this.translationTable['mining-operation'] = 'v1-mining-operation'
        // this.translationTable['request'] = 'v1-request'
        // this.translationTable['coil'] = 'v1-coil'
        // this.translationTable['build-countdown-timer'] = 'v1-build-countdown-timer'
        // this.translationTable['part4-request'] = 'v1-part4-request'
        // this.translationTable['part-container'] = 'v1-part-container'
        // this.translationTable['part-stack'] = 'v1-part-stack'
        // this.translationTable['model'] = 'v1-model'
        // this.translationTable['request-item'] = 'v1-request-item'
        // this.translationTable['request-content'] = 'v1-request-item'
        // this.translationTable['job-timer'] = 'v1-build-countdown-timer'
        // - TAGS
        this.translationTable['buttons'] = 'button'
        // - BACKEND REQUESTS
        this.translationTable['Authentication Validate'] = 'apiv1_validateAuthenticationState'
        // this.translationTable['Start Build Job'] = 'apiMachinesStartBuild_v2'
        // this.translationTable['Save New Part'] = 'apiNewPart_v1'

        // -  R O U T E S
        this.routeTranslationTable['Start'] = '/'
        this.routeTranslationTable['Pilot Dashboard'] = '/dashboard'
        this.routeTranslationTable['Planetary Dashboard Page'] = '/planetary/dashboard'
        this.routeTranslationTable['Resource Research Page'] = '/planetary/resource-research'
        this.routeTranslationTable['Industry Dashboard'] = '/industry/dashboard'
        this.routeTranslationTable['Blueprint Manufacture CostIndex'] = '/industry/manufacture/blueprints'
        this.routeTranslationTable['Mining Operations'] = '/industry/mining/miningoperations'
    }
    /**
     * Replaces symbolic names by the application names so if there are version changes the acceptance scritps should not change.
     * For example the tags for machine can change from version 1 v1-machine to version 3 v3-machine without changes on the test code.
     * @param tag the HTML tag name to serch for the applciation tag replacement.
     */
    public translateTag(name: string): string {
        return this.translationTable[name]
    }
    public translateRoute(name: string): string {
        return this.routeTranslationTable[name]
    }
    // - R A N D O M
    public generateRandomNum(min: number, max: number) {
        return Math.floor(Math.random() * (max - min + 1)) + min;
    };
    public generateRandomString(length: number): string {
        var string = '';
        var letters = 'ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz' //Include numbers if you want
        for (let i = 0; i < length; i++) {
            string += letters.charAt(Math.floor(Math.random() * letters.length));
        }
        return string;
    }
    // - T E M P L A T E   R E P L A C E M E N T
    /**
    * This function replaces values found on Gherkin files by configuration values if they fit the <name> syntax.
    * There are 3 sets of templated values. Environmental that will replace the value by an 'environtment' value,
    * configuration that will do the same with an application configured constant and system that will replace the
    * value by the result of a system function.
    * @param value the value to check for templated.
    */
    public replaceValueTemplated(value: string): string {
        let regex = /^<(.+)\.(.+)>$/g
        if (regex.test(value)) {
            const domain = RegExp.$1;
            const name = RegExp.$2;
            console.log('-[replaceValueTemplated]>domain=' + domain);
            console.log('-[replaceValueTemplated]>name=' + name);
            if (null != domain) {
                switch (domain) {
                    case 'environment':
                        return this.replaceEnvironmentTemplate(name);
                        break;
                }
            }
        }
        return value;
    }
    public replaceEnvironmentTemplate(templateName: string): string {
        switch (templateName) {
            case 'app-name':
                return environment.appName;
            case 'app-title':
                return environment.appTitle;
            case 'app-version':
                return environment.appVersion;
            // case 'backend-version':
            //     return environment.backendVersion;
            case 'copyright':
                return environment.copyright;
        }
        return '-undefined-';
    }

    // - S T O R A G E   A C C E S S
    public setToSession(key: string, content: any): any {
        cy.log('>[setToSession]> ' + key + ': ' + content);
        cy.window().then(win => {
            win.sessionStorage.setItem(key, JSON.stringify(content))
        });
    }
    public getFromSession(key: string): any {
        cy.log('>[getFromSession]> ' + key);
        cy.window().then(win => {
            return win.sessionStorage.getItem(key)
        });
    }
    public removeFromSession(key: string): any {
        cy.log('>[removeFromSession]> ' + key);
        cy.window().then(win => {
            return win.sessionStorage.removeItem(key)
        });
    }
}
