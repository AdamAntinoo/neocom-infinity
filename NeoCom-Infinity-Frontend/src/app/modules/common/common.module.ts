// - CORE MODULES
import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
// - PIPES
import { CapitalizeLetterPipe } from './pipes/capitalize-letter.pipe';
import { ISKNoDecimalsPipe } from './pipes/iskno-decimals.pipe';
// - COMPONENTS
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
        SystemFilterPipe,
        V1FeatureButtonComponent
    ],
    exports: [
        CapitalizeLetterPipe,
        ISKNoDecimalsPipe,
        SystemFilterPipe,
        V1FeatureButtonComponent
    ]
})
export class AppCommonModule { }
