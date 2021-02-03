// - CORE
import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';
import { Routes } from '@angular/router';

// - GUARDS
import { TokenAuthorizationGuard } from './security/token-authorization.guard';

// - PAGES
import { LoginValidationPageComponent } from './pages/login-validation-page/login-validation-page.component';
import { DashboardHomePageComponent } from './pages/dashboard-home-page/dashboard-home-page.component';
import { ExceptionInformationPageComponent } from './pages/exception-information-page/exception-information-page.component';
import { V1StartPageComponent } from './pages/v1-start-page/v1-start-page.component';

const routes: Routes = [
    {
        path: '',
        redirectTo: '/start',
        pathMatch: 'full'
    },
    // {
    //     path: '',
    //     redirectTo: '/manufacture/research/123',
    //     pathMatch: 'full'
    // },
    // - LOGIN PAGES
    { path: 'start', component: V1StartPageComponent },
    { path: 'loginValidation', component: LoginValidationPageComponent },
    { path: 'exceptionInfo', component: ExceptionInformationPageComponent },
    { path: 'dashboard', component: DashboardHomePageComponent },
    // - D A S H B O A R D
    // {
    //     path: 'dashboard',
    //     loadChildren: './modules/shared/shared.module#SharedModule',
    //     canActivate: [TokenAuthorizationGuard]
    // },
    // - I N D U S T R Y
    { path: 'industry', loadChildren: () => import('./modules/industry/industry.module').then(m => m.IndustryModule) },
    // - P L A N E T A R Y
    { path: 'planetary', loadChildren: () => import('./modules/planetary/planetary.module').then(m => m.PlanetaryModule) },
    // - L O Y A L T Y
    { path: 'loyalty',loadChildren: () => import('./modules/loyalty/loyalty.module').then(m => m.LoyaltyModule) }
    // - REDIRECT NOT FOUND PAGES
    // { path: '**', component: NotFoundPage }
    // -  F I T T I N G S
    // {
    //     path: 'fittings',
    //     loadChildren: './modules/fittings/fittings.module#FittingsModule',
    //     canActivate: [TokenAuthorizationGuard]
    // },
];

@NgModule({
    imports: [RouterModule.forRoot(routes, { enableTracing: false })],
    exports: [RouterModule]
})
export class AppRoutingModule { }
