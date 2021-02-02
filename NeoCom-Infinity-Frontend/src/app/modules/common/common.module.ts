// - CORE MODULES
import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
// - PIPES
import { CapitalizeLetterPipe } from './pipes/capitalize-letter.pipe';
import { ISKNoDecimalsPipe } from './pipes/iskno-decimals.pipe';
// import { IskScaledPipe } from './pipes/iskscaled.pipe';
// - COMPONENTS
import { V2PilotPublicDataPanelComponent } from './panels/v2-pilot-public-data-panel/v2-pilot-public-data-panel.component';
import { RendersModule } from '../renders/renders.module';
import { InnovativeCoreModule } from '@innovative/innovative-core.module';
import { V1FeatureButtonComponent } from './components/v1-feature-button/v1-feature-button.component';
import { SystemFilterPipe } from './pipes/systemFilter.pipe';

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
        // IskScaledPipe,
        SystemFilterPipe,
        V2PilotPublicDataPanelComponent,
        V1FeatureButtonComponent
    ],
    exports: [
        CapitalizeLetterPipe,
        ISKNoDecimalsPipe,
        // IskScaledPipe,
        SystemFilterPipe,
        V2PilotPublicDataPanelComponent,
        V1FeatureButtonComponent
    ]
})
export class AppCommonModule { }
