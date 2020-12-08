// - CORE MODULES
import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
// - COMPONENTS
import { AppCommonModule } from '../common/common.module';
import { V2PilotRenderComponent } from './v2-pilot-render/v2-pilot-render.component';
import { V1FittingItemRenderComponent } from './v1-fitting-item-render/v1-fitting-item-render.component';
// import { V1FittingContentRenderComponent } from './v1-fitting-content-render/v1-fitting-content-render.component';
import { V1FittingInfoRenderComponent } from './v1-fitting-info-render/v1-fitting-info-render.component';
import { SharedModule } from '@shared/shared.module';
import { V1FittingGroupRenderComponent } from './v1-fitting-group-render/v1-fitting-group-render.component';
import { V2NodeContainerRenderComponent } from './v2-node-container-render/v2-node-container-render.component';
import { V1BuildActionRenderComponent } from './v1-build-action-render/v1-build-action-render.component';
import { V1FittingBuildContentRenderComponent } from './v1-fitting-build-content-render/v1-fitting-build-content-render.component';
import { V1MarketDataRenderComponent } from './v1-market-data-render/v1-market-data-render.component';
import { V2FeatureRenderComponent } from './v2-feature-render/v2-feature-render.component';
import { V1PlanetarySystemRenderComponent } from './v1-planetary-system-render/v1-planetary-system-render.component';

@NgModule({
    imports: [
        CommonModule,
        AppCommonModule,
        // SharedModule
    ],
    declarations: [
        // NodeContainerRenderComponent,
        V2PilotRenderComponent,
        V1FittingItemRenderComponent,
        // V1FittingContentRenderComponent,
        V1FittingInfoRenderComponent,
        V1FittingGroupRenderComponent,
        V2NodeContainerRenderComponent,
        V1BuildActionRenderComponent,
        V1FittingBuildContentRenderComponent,
        V1MarketDataRenderComponent,
        V2FeatureRenderComponent,
        V1PlanetarySystemRenderComponent,
    ],
    exports: [
        // NodeContainerRenderComponent,
        V2PilotRenderComponent,
        V1FittingItemRenderComponent,
        // V1FittingContentRenderComponent,
        V1FittingInfoRenderComponent,
        V1FittingGroupRenderComponent,
        V2NodeContainerRenderComponent,
        V1BuildActionRenderComponent,
        V1FittingBuildContentRenderComponent,
        V1MarketDataRenderComponent,
        V2FeatureRenderComponent,
        V1PlanetarySystemRenderComponent,
    ]
})
export class RendersModule { }
