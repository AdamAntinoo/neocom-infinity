import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { PlanetaryRoutingModule } from './planetary-routing.module';
import { V1T2ClassificationPanelComponent } from './panel/v1-t2-classification-panel/v1-t2-classification-panel.component';
import { V1PlanetaryPageComponent } from './pages/v1-planetary-page/v1-planetary-page.component';
import { RendersModule } from '../renders/renders.module';
import { V1KnownPlanetsPanelComponent } from './panel/v1-known-planets-panel/v1-known-planets-panel.component';


@NgModule({
  imports: [
    CommonModule,
    PlanetaryRoutingModule,
    RendersModule
  ],
  declarations: [V1T2ClassificationPanelComponent, V1PlanetaryPageComponent, V1KnownPlanetsPanelComponent],
})
export class PlanetaryModule { }
