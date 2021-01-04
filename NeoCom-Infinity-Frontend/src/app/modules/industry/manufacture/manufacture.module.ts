// - CORE MODULES
import { NgModule } from '@angular/core'
import { CommonModule } from '@angular/common'
import { FormsModule } from '@angular/forms'
// - ROUTING
import { Routes } from '@angular/router'
import { RouterModule } from '@angular/router';
// - APPLICATION MODULES
import { HeaderModule } from '@app/modules/header/header.module';
import { RendersModule } from '@app/modules/renders/renders.module';
// - COMPONENTS
import { V1BlueprintListPageComponent } from './pages/v1-blueprint-list-page/v1-blueprint-list-page.component'
import { V1AvailableBlueprintsPanelComponent } from './panels/v1-available-blueprints-panel/v1-available-blueprints-panel.component';
import { V1ManufactureResearchPageComponent } from '../pages/v1-manufacture-research-page/v1-manufacture-research-page.component';
import { V1TopBOMPanelComponent } from '../panel/v1-top-bompanel/v1-top-bompanel.component';
import { SharedModule } from '@shared/shared.module';

const routes: Routes = [
  { path: 'blueprints', component: V1BlueprintListPageComponent },
  { path: 'research/:blueprintId', component: V1ManufactureResearchPageComponent },
];

@NgModule({
    imports: [
        CommonModule,
        FormsModule,
        RouterModule.forChild(routes),
        SharedModule,
        HeaderModule,
        RendersModule
    ],
    declarations: [
    V1BlueprintListPageComponent,
    V1ManufactureResearchPageComponent,
    V1TopBOMPanelComponent,
    V1AvailableBlueprintsPanelComponent
],
    exports: [RouterModule]
})
export class ManufactureModule { }
