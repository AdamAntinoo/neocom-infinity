// - CORE MODULES
import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
// - ROUTING
import { RouterModule } from '@angular/router';
import { Routes } from '@angular/router';

// - PAGES
// - PANELS
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
        // - RENDERS
        RenderComponent,
        AllianceRenderComponent,
        CorporationRenderComponent,
        PilotRenderComponent,
        AppPanelComponent,
    ],
    exports: [
        RouterModule,
        // - RENDERS
        AllianceRenderComponent,
        CorporationRenderComponent,
        PilotRenderComponent
    ]
})
export class SharedModule { }
