import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { V1PlanetT2ResourcesRenderComponent } from './v1-planet-t2-resources-render/v1-planet-t2-resources-render.component';
import { V1ResourceRenderComponent } from './v1-resource-render/v1-resource-render.component';

@NgModule({
  declarations: [
    V1PlanetT2ResourcesRenderComponent,
    V1ResourceRenderComponent
  ],
  imports: [
    CommonModule
  ],
  exports: [
    V1PlanetT2ResourcesRenderComponent,
    V1ResourceRenderComponent
  ]
})
export class RendersModule { }
