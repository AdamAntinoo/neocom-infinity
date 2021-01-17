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
import { RendersModule } from '../renders/renders.module';
import { InnovativeCoreModule } from '@innovative/innovative-core.module';
import { V1FeatureButtonComponent } from './components/v1-feature-button/v1-feature-button.component';

@NgModule({
    imports: [
        CommonModule,
        RendersModule,
        InnovativeCoreModule
    ],
    declarations: [
        // -PIPES
        CapitalizeLetterPipe,
        ISKNoDecimalsPipe,
        IskScaledPipe,
        V2PilotPublicDataPanelComponent,
        V1FeatureButtonComponent
    ],
    exports: [
        CapitalizeLetterPipe,
        ISKNoDecimalsPipe,
        IskScaledPipe,
        V2PilotPublicDataPanelComponent,
        V1FeatureButtonComponent
    ]
})
export class AppCommonModule { }
