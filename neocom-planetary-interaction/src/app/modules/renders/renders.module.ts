import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { V1PlanetT2ResourcesRenderComponent } from './v1-planet-t2-resources-render/v1-planet-t2-resources-render.component';
import { V1ResourceRenderComponent } from './v1-resource-render/v1-resource-render.component';
import { V1PlanetDataRenderComponent } from './v1-planet-data-render/v1-planet-data-render.component';
import { V1T2GeneratedResourceRenderComponent } from './v1-t2-generated-resource-render/v1-t2-generated-resource-render.component';

@NgModule({
  declarations: [
    V1PlanetT2ResourcesRenderComponent,
    V1ResourceRenderComponent,
    V1PlanetDataRenderComponent,
    V1T2GeneratedResourceRenderComponent
  ],
  imports: [
    CommonModule
  ],
  exports: [
    V1PlanetT2ResourcesRenderComponent,
    V1ResourceRenderComponent,
    V1PlanetDataRenderComponent,
    V1T2GeneratedResourceRenderComponent
  ]
})
export class RendersModule { }
