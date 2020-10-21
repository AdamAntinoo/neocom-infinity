// - CORE MODULES
import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
// - COMPONENTS
import { V2PilotPublicDataPanelComponent } from './panels/v2-pilot-public-data-panel/v2-pilot-public-data-panel.component';
import { SharedModule } from '../shared/shared.module';
import { InnovativeCoreModule } from '@bit/innovative.innovative.innovative-core';
import { RendersModule } from '../renders/renders.module';

@NgModule({
    imports: [
        CommonModule,
        // CommonRoutingModule,
        RendersModule,
        InnovativeCoreModule
    ],
    declarations: [
        V2PilotPublicDataPanelComponent
    ],
    exports: [
        V2PilotPublicDataPanelComponent
    ]
})
export class AppCommonModule { }
