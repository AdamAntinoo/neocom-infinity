// - CORE MODULES
import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
// - ROUTING
import { RouterModule } from '@angular/router';
import { Routes } from '@angular/router';
// - GUARDS
import { TokenAuthorizationGuard } from '@app/security/token-authorization.guard';

// - PAGES
// - PANELS
import { AppInfoPanelComponent } from './panels/app-info-panel/app-info-panel.component';
import { AppPanelComponent } from './panels/app-panel/app-panel.component';
// - RENDERS
import { RenderComponent } from './renders/render/render.component';
import { AllianceRenderComponent } from './renders/alliance-render/alliance-render.component';
import { CorporationRenderComponent } from './renders/corporation-render/corporation-render.component';
import { PilotRenderComponent } from './renders/pilot-render/pilot-render.component';

// - MODULE ROUTES
const routes: Routes = [
    // { path: '', component: DashboardHomePage, canActivate: [TokenAuthorizationGuard] },
    // { path: 'home', component: DashboardHomePage, canActivate: [TokenAuthorizationGuard] },
    // { path: 'servicios', component: AdminServiciosPageComponent, canActivate: [AuthAdminGuard] }
];

@NgModule({
    imports: [
        CommonModule,
        RouterModule.forChild(routes),
    ],
    declarations: [
        // - PANELS
        AppInfoPanelComponent,
        // - RENDERS
        RenderComponent,
        AllianceRenderComponent,
        CorporationRenderComponent,
        PilotRenderComponent,
        AppPanelComponent,
    ],
    exports: [
        RouterModule,
        AppInfoPanelComponent,
        // - RENDERS
        AllianceRenderComponent,
        CorporationRenderComponent,
        PilotRenderComponent
    ]
})
export class SharedModule { }
