// - CORE MODULES
import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
// - COMPONENTS
import { AppCommonModule } from '../common/common.module';
import { NeocomViewerPanelComponent } from './neocom-viewer-panel/neocom-viewer-panel.component';

import { V2PilotRenderComponent } from './v2-pilot-render/v2-pilot-render.component';
import { V1FittingItemRenderComponent } from './v1-fitting-item-render/v1-fitting-item-render.component';
import { V1FittingInfoRenderComponent } from './v1-fitting-info-render/v1-fitting-info-render.component';
import { V1FittingGroupRenderComponent } from './v1-fitting-group-render/v1-fitting-group-render.component';
import { V2NodeContainerRenderComponent } from './v2-node-container-render/v2-node-container-render.component';
import { V1BuildActionRenderComponent } from './v1-build-action-render/v1-build-action-render.component';
import { V1FittingBuildContentRenderComponent } from './v1-fitting-build-content-render/v1-fitting-build-content-render.component';
import { V1MarketDataRenderComponent } from './v1-market-data-render/v1-market-data-render.component';
import { V2FeatureRenderComponent } from './v2-feature-render/v2-feature-render.component';
import { V1PlanetarySystemRenderComponent } from './v1-planetary-system-render/v1-planetary-system-render.component';
import { V1PlanetDataRenderComponent } from './v1-planet-data-render/v1-planet-data-render.component';
import { V1PlanetaryResourceRenderComponent } from './v1-planetary-resource-render/v1-planetary-resource-render.component';
import { NgDragDropModule } from 'ng-drag-drop';
import { V1OutputResourceRenderComponent } from './v1-output-resource-render/v1-output-resource-render.component';
import { V1BlueprintRenderComponent } from './v1-blueprint-render/v1-blueprint-render.component';
import { V1BOMResourceRenderComponent } from './v1-bomresource-render/v1-bomresource-render.component';
import { V1BOMGroupRenderComponent } from './v1-bomgroup-render/v1-bomgroup-render.component';

@NgModule({
    imports: [
        CommonModule,
        AppCommonModule,
        NgDragDropModule.forRoot()
    ],
    declarations: [
        NeocomViewerPanelComponent,
        V2PilotRenderComponent,
        V1FittingItemRenderComponent,
        V1FittingInfoRenderComponent,
        V1FittingGroupRenderComponent,
        V2NodeContainerRenderComponent,
        V1BuildActionRenderComponent,
        V1FittingBuildContentRenderComponent,
        V1MarketDataRenderComponent,
        V2FeatureRenderComponent,
        V1PlanetarySystemRenderComponent,
        V1PlanetDataRenderComponent,
        V1PlanetaryResourceRenderComponent,
        V1OutputResourceRenderComponent,
        // - INDUSTRY
        V1BlueprintRenderComponent,
        V1BOMResourceRenderComponent,
        V1BOMGroupRenderComponent

    ],
    exports: [
        NeocomViewerPanelComponent,
        V2PilotRenderComponent,
        V1FittingItemRenderComponent,
        V1FittingInfoRenderComponent,
        V1FittingGroupRenderComponent,
        V2NodeContainerRenderComponent,
        V1BuildActionRenderComponent,
        V1FittingBuildContentRenderComponent,
        V1MarketDataRenderComponent,
        V2FeatureRenderComponent,
        V1PlanetarySystemRenderComponent,
        V1PlanetDataRenderComponent,
        V1PlanetaryResourceRenderComponent,
        V1OutputResourceRenderComponent,
        // - INDUSTRY
        V1BlueprintRenderComponent,
        V1BOMResourceRenderComponent,
        V1BOMGroupRenderComponent
    ]
})
export class RendersModule { }
