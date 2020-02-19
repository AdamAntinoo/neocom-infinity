// - CORE MODULES
import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
// - ROUTING
import { RouterModule } from '@angular/router';
import { Routes } from '@angular/router';

// - PAGES
// - PANELS
// import { AppPanelComponent } from './panels/app-panel/app-panel.component';
import { ActionBarComponent } from './panels/action-bar/action-bar.component';
import { ViewerPanelComponent } from './panels/viewer-panel/viewer-panel.component';
import { SpinnerPanelComponent } from './panels/spinner-panel/spinner-panel.component';
// - RENDERS
import { NodeContainerRenderComponent } from './renders/node-container-render/node-container-render.component';
import { RenderComponent } from './renders/render/render.component';
import { AllianceRenderComponent } from './renders/alliance-render/alliance-render.component';
import { CorporationRenderComponent } from './renders/corporation-render/corporation-render.component';
import { PilotRenderComponent } from './renders/pilot-render/pilot-render.component';
import { V1GroupContainerRenderComponent } from './renders/v1-group-container-render/v1-group-container-render.component';
import { V1FittingRenderComponent } from './renders/v1-fitting-render/v1-fitting-render.component';
import { V1FittingItemRenderComponent } from './renders/v1-fitting-item-render/v1-fitting-item-render.component';

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
        // AppPanelComponent,
        ActionBarComponent,
        ViewerPanelComponent,
        SpinnerPanelComponent,
        // - RENDERS
        NodeContainerRenderComponent,
        RenderComponent,
        AllianceRenderComponent,
        CorporationRenderComponent,
        PilotRenderComponent,
        V1GroupContainerRenderComponent,
        V1FittingRenderComponent,
        V1FittingItemRenderComponent,
    ],
    exports: [
        RouterModule,
        // - PANELS
        // AppPanelComponent,
        ActionBarComponent,
        ViewerPanelComponent,
        SpinnerPanelComponent,
        // - RENDERS
        NodeContainerRenderComponent,
        AllianceRenderComponent,
        CorporationRenderComponent,
        PilotRenderComponent
    ]
})
export class SharedModule { }
