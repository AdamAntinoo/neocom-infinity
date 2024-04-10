// - CORE MODULES
import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
// - GUARDS
import { TokenAuthorizationGuard } from '@app/security/token-authorization.guard';
// - PAGES
// - PANELS
import { AppInfoPanelComponent } from './app-info-panel/app-info-panel.component';
import { CorporationPublicDataPanelComponent } from './corporation-public-data-panel/corporation-public-data-panel.component';
import { ServerInfoPanelComponent } from './server-info-panel/server-info-panel.component';
import { V1PilotPanelComponent } from './v1-pilot-panel/v1-pilot-panel.component';
import { SharedModule } from '@shared/shared.module';
import { RendersModule } from '../renders/renders.module';

// - MODULE ROUTES
@NgModule({
    imports: [
        CommonModule,
        SharedModule,
        RendersModule
    ],
    declarations: [
        // - PANELS
        AppInfoPanelComponent,
        CorporationPublicDataPanelComponent,
        ServerInfoPanelComponent,
        V1PilotPanelComponent,
    ],
    exports: [
        // - PANELS
        AppInfoPanelComponent,
        CorporationPublicDataPanelComponent,
        ServerInfoPanelComponent,
        V1PilotPanelComponent,
    ]
})
export class HeaderModule { }
