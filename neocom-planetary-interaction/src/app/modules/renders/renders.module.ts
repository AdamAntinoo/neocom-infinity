import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { V1PlanetT2ResourcesRenderComponent } from './v1-planet-t2-resources-render/v1-planet-t2-resources-render.component';

@NgModule({
  declarations: [V1PlanetT2ResourcesRenderComponent],
  imports: [
    CommonModule
  ],
  exports: [V1PlanetT2ResourcesRenderComponent]
})
export class RendersModule { }
