// - CORE MODULES
import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
// - COMPONENTS
// import { PilotRenderComponent } from './pilot-render/pilot-render.component';
import { V2PilotRenderComponent } from './v2-pilot-render/v2-pilot-render.component';
import { V1FittingConfigurationRenderComponent } from './v1-fitting-configuration-render/v1-fitting-configuration-render.component';

@NgModule({
    imports: [
        CommonModule
    ],
    declarations: [
        // PilotRenderComponent,
        V2PilotRenderComponent,
        V1FittingConfigurationRenderComponent
    ],
    exports: [
        V2PilotRenderComponent,
        V1FittingConfigurationRenderComponent
    ]
})
export class RendersModule { }
