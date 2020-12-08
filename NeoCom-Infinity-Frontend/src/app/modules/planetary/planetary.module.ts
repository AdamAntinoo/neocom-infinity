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
import { DashboardPageComponent } from './page/dashboard-page/dashboard-page.component';
import { HeaderModule } from '../header/header.module'
import { AppCommonModule } from '@common/common.module';
import { V1PlanetSearchPageComponent } from './page/v1-planet-search-page/v1-planet-search-page.component'
import { V1KnownPlanetsPanelComponent } from './panel/v1-known-planets-panel/v1-known-planets-panel.component'
import { V1SelectedPlanetsPanelComponent } from './panel/v1-selected-planets-panel/v1-selected-planets-panel.component'
import { PlanetaryDataService } from './service/PlanetaryData.service';
import { V1EnterPlanetDataPageComponent } from './page/v1-enter-planet-data-page/v1-enter-planet-data-page.component'

const routes: Routes = [
    { path: 'dashboard', component: DashboardPageComponent },
    { path: 'planet-data', component: V1EnterPlanetDataPageComponent },
    { path: 'resource-search', component: V1PlanetSearchPageComponent },
]

@NgModule({
    imports: [
        CommonModule,
        FormsModule,
        RouterModule.forChild(routes),
        AppCommonModule,
        HeaderModule
    ],
    declarations: [
        DashboardPageComponent, 
        V1PlanetSearchPageComponent,
        V1KnownPlanetsPanelComponent,
        V1SelectedPlanetsPanelComponent,
        V1EnterPlanetDataPageComponent
    ],
    exports: [RouterModule],
    providers: [
        // - SERVICES
        { provide: PlanetaryDataService, useClass: PlanetaryDataService },
    ],
})
export class PlanetaryModule { }
