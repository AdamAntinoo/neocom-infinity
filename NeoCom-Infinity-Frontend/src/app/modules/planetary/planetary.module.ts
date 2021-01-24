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
import { V1KnownPlanetsPanelComponent } from './panel/v1-known-planets-panel/v1-known-planets-panel.component'
import { V1SelectedPlanetsPanelComponent } from './panel/v1-selected-planets-panel/v1-selected-planets-panel.component'
import { PlanetaryDataService } from './service/PlanetaryData.service';
import { V1EnterPlanetDataPageComponent } from './page/v1-enter-planet-data-page/v1-enter-planet-data-page.component'
import { NgDragDropModule } from 'ng-drag-drop';
import { V1ResourceResearchPageComponent } from './page/v1-resource-research-page/v1-resource-research-page.component';
import { V1KnownSystemsPanelComponent } from './panel/v1-known-systems-panel/v1-known-systems-panel.component';
import { V1OutputResourcesPanelComponent } from './panel/v1-output-resources-panel/v1-output-resources-panel.component';
import { V1SelectedResourcesPanelComponent } from './panel/v1-selected-resources-panel/v1-selected-resources-panel.component'

const routes: Routes = [
    { path: 'dashboard', component: DashboardPageComponent },
    { path: 'planet-data', component: V1EnterPlanetDataPageComponent },
    { path: 'planetary-research', component: V1ResourceResearchPageComponent },
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
        DashboardPageComponent,
        V1KnownPlanetsPanelComponent,
        V1SelectedPlanetsPanelComponent,
        V1EnterPlanetDataPageComponent,
        V1ResourceResearchPageComponent,
        V1KnownSystemsPanelComponent,
        V1OutputResourcesPanelComponent,
        V1SelectedResourcesPanelComponent
    ],
    exports: [RouterModule],
    providers: [
        // - SERVICES
        { provide: PlanetaryDataService, useClass: PlanetaryDataService }
    ],
})
export class PlanetaryModule { }
