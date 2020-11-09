// - CORE MODULES
import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
// - COMPONENTS
import { AppCommonModule } from '../common/common.module';
import { V2PilotRenderComponent } from './v2-pilot-render/v2-pilot-render.component';
import { V1FittingConfigurationRenderComponent } from './v1-fitting-configuration-render/v1-fitting-configuration-render.component';
import { V1FittingItemRenderComponent } from './v1-fitting-item-render/v1-fitting-item-render.component';

@NgModule({
    imports: [
        CommonModule,
        AppCommonModule
    ],
    declarations: [
        V2PilotRenderComponent,
        V1FittingConfigurationRenderComponent,
        V1FittingItemRenderComponent
    ],
    exports: [
        V2PilotRenderComponent,
        V1FittingConfigurationRenderComponent,
        V1FittingItemRenderComponent
    ]
})
export class RendersModule { }
