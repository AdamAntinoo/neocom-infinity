// - CORE MODULES
import { NgModule } from '@angular/core'
import { CommonModule } from '@angular/common'
import { FormsModule } from '@angular/forms'
// - ROUTING
import { Routes } from '@angular/router'
import { RouterModule } from '@angular/router'
// - APPLICATION MODULES
import { SharedModule } from '@shared/shared.module'
import { RendersModule } from '../renders/renders.module'
// - COMPONENTS
import { V1IndustryFittingBuildConfigurationPageComponent } from './pages/v1-industry-fitting-build-configuration-page/v1-industry-fitting-build-configuration-page.component'
import { V1FittingConfigurationPanelComponent } from './panels/v1-fitting-configuration-panel/v1-fitting-configuration-panel.component';
import { V1FittingContentsPanelComponent } from './panels/v1-fitting-contents-panel/v1-fitting-contents-panel.component';
import { NgDragDropModule } from 'ng-drag-drop'
import { AppCommonModule } from '@common/common.module'
import { HeaderModule } from '../header/header.module';
import { IndustryDashboardPageComponent } from './pages/industry-dashboard-page/industry-dashboard-page.component';
import { TokenAuthorizationGuard } from '@app/security/token-authorization.guard'

const routes: Routes = [
    { path: 'dashboard', component: IndustryDashboardPageComponent , canActivate: [TokenAuthorizationGuard] },
    { path: 'fittings/buildConfiguration/:fittingId', component: V1IndustryFittingBuildConfigurationPageComponent },
    { path: 'manufacture', loadChildren: () => import('./manufacture/manufacture.module').then(m => m.ManufactureModule) },
]

@NgModule({
    imports: [
        CommonModule,
        FormsModule,
        RouterModule.forChild(routes),
        NgDragDropModule.forRoot(),
        AppCommonModule,
        HeaderModule,
        RendersModule,
        SharedModule
    ],
    declarations: [
        V1IndustryFittingBuildConfigurationPageComponent,
        V1FittingConfigurationPanelComponent,
        V1FittingContentsPanelComponent,
        IndustryDashboardPageComponent,
    ],
    exports: [RouterModule]
})
export class IndustryModule { }
