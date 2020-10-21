// - CORE MODULES
import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
// - COMPONENTS
// import { PilotRenderComponent } from './pilot-render/pilot-render.component';
import { V2PilotRenderComponent } from './v2-pilot-render/v2-pilot-render.component';

@NgModule({
    imports: [
        CommonModule
    ],
    declarations: [
        // PilotRenderComponent,
        V2PilotRenderComponent
    ],
    exports: [
        V2PilotRenderComponent
    ]
})
export class RendersModule { }
