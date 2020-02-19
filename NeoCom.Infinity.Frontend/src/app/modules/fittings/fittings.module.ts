// - CORE MODULES
import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
// - ROUTING
import { RouterModule } from '@angular/router';
import { Routes } from '@angular/router';
// - SECURITY
import { TokenAuthorizationGuard } from '@app/security/token-authorization.guard';
// - COMPONENTS
import { PilotFittingsPageComponent } from './pages/pilot-fittings-page/pilot-fittings-page.component';
import { SharedModule } from '@app/modules/shared/shared.module';
import { HeaderModule } from '@app/modules/header/header.module';

// - MODULE ROUTES
const routes: Routes = [
    { path: 'pilot', component: PilotFittingsPageComponent, canActivate: [TokenAuthorizationGuard] }
    // { path: 'home', component: DashboardHomePage, canActivate: [TokenAuthorizationGuard] },
    // { path: 'servicios', component: AdminServiciosPageComponent, canActivate: [AuthAdminGuard] }
];

@NgModule({
    imports: [
        CommonModule,
        SharedModule,
        HeaderModule,
        RouterModule.forChild(routes),
    ],
    declarations: [
        PilotFittingsPageComponent
    ],
    exports: [
        RouterModule,
        PilotFittingsPageComponent,
    ]
})
export class FittingsModule { }
