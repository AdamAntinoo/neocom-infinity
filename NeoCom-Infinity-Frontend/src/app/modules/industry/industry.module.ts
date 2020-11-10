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
import { V1FittingConfigurationPanelComponent } from './panels/v1-fitting-configuration-panel/v1-fitting-configuration-panel.component'

const routes: Routes = [
    { path: 'fittings/buildConfiguration/:fittingId', component: V1IndustryFittingBuildConfigurationPageComponent },
]

@NgModule({
    imports: [
        CommonModule,
        FormsModule,
        RouterModule.forChild(routes),
        RendersModule,
        SharedModule
    ],
    declarations: [
        V1IndustryFittingBuildConfigurationPageComponent,
        V1FittingConfigurationPanelComponent
    ],
    exports: [RouterModule]
})
export class IndustryModule { }
