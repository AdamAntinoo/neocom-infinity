// - CORE MODULES
import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { SharedModule } from '@app/modules/shared/shared.module';
import { HeaderModule } from '@app/modules/header/header.module';
// - ROUTING
import { RouterModule } from '@angular/router';
import { Routes } from '@angular/router';
// - SECURITY
import { TokenAuthorizationGuard } from '@app/security/token-authorization.guard';
// - COMPONENTS
import { PilotFittingsPageComponent } from './pages/pilot-fittings-page/pilot-fittings-page.component';
// import { V1FittingRenderComponent } from '../shared/renders/v1-fitting-render/v1-fitting-render.component';

// - MODULE ROUTES
const routes: Routes = [
    { path: 'pilot', component: PilotFittingsPageComponent, canActivate: [TokenAuthorizationGuard] },
    { path: 'corporation', component: PilotFittingsPageComponent, canActivate: [TokenAuthorizationGuard] }
];

@NgModule({
    imports: [
        CommonModule,
        SharedModule,
        HeaderModule,
        RouterModule.forChild(routes),
    ],
    declarations: [
        PilotFittingsPageComponent,
        // V1FittingRenderComponent
    ],
    exports: [
        RouterModule,
        PilotFittingsPageComponent,
        // V1FittingRenderComponent
    ]
})
export class FittingsModule { }
