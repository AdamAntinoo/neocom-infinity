// - CORE MODULES
import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
// - PIPES
import { CapitalizeLetterPipe } from './pipes/capitalize-letter.pipe';
import { ISKNoDecimalsPipe } from './pipes/iskno-decimals.pipe';
import { IskScaledPipe } from './pipes/iskscaled.pipe';
// - COMPONENTS
import { V2PilotPublicDataPanelComponent } from './panels/v2-pilot-public-data-panel/v2-pilot-public-data-panel.component';
import { SharedModule } from '../shared/shared.module';
import { InnovativeCoreModule } from '@bit/innovative.innovative.innovative-core';
import { RendersModule } from '../renders/renders.module';

@NgModule({
    imports: [
        CommonModule,
        // RendersModule,
        InnovativeCoreModule
    ],
    declarations: [
        // -PIPES
        CapitalizeLetterPipe,
        ISKNoDecimalsPipe,
        IskScaledPipe,
        V2PilotPublicDataPanelComponent
    ],
    exports: [
        CapitalizeLetterPipe,
        ISKNoDecimalsPipe,
        IskScaledPipe,
        V2PilotPublicDataPanelComponent
    ]
})
export class AppCommonModule { }
