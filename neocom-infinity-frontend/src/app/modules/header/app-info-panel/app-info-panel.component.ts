// - CORE
import { Component } from '@angular/core';
import { environment } from '@env/environment';
// - SERVICES

@Component({
    selector: 'app-info-panel',
    templateUrl: './app-info-panel.component.html',
    styleUrls: ['./app-info-panel.component.scss']
})
export class AppInfoPanelComponent {
    constructor() { }

    public getName(): string {
        return environment.appName.toUpperCase();
    }
    public getVersion(): string {
        return environment.appVersion;
    }
    public getCopyright(): string {
        return environment.copyright;
    }
}
