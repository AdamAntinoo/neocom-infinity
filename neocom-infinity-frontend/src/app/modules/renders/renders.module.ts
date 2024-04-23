// - CORE MODULES
import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
// - COMPONENTS
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
import { V1PublicPilotRenderComponent } from './v1-public-pilot-render/v1-public-pilot-render.component';
import { V1RegionRenderComponent } from './v1-region-render/v1-region-render.component';
import { V1SolarSystemRenderComponent } from './v1-solar-system-render/v1-solar-system-render.component';
import { V1LoyaltyCorporationRenderComponent } from './v1-loyalty-corporation-render/v1-loyalty-corporation-render.component';
import { V1LoyaltyOfferRenderComponent } from './v1-loyalty-offer-render/v1-loyalty-offer-render.component';
import { IskScaledPipe } from './pipes/iskscaled.pipe';
import { NgxChartsModule } from '@swimlane/ngx-charts';
import { V1MiningOperationRenderComponent } from '@app/industry/renders/v1-mining-operation-render/v1-mining-operation-render.component';
import { V1StackRenderComponent } from '@app/industry/renders/v1-stack-render/v1-stack-render.component';
import { M3VolumePipe } from './pipes/m3volume.pipe';


@NgModule({
    imports: [
        CommonModule,
        // AppCommonModule,
        NgDragDropModule.forRoot(),
        NgxChartsModule
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
        V1BOMGroupRenderComponent,
        V1PublicPilotRenderComponent,
        V1RegionRenderComponent,
        V1SolarSystemRenderComponent,
        V1LoyaltyCorporationRenderComponent,
        V1LoyaltyOfferRenderComponent,
        V1MiningOperationRenderComponent,
        V1StackRenderComponent,
        // - PIPES
        IskScaledPipe,
        M3VolumePipe

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
        V1BOMGroupRenderComponent,
        V1PublicPilotRenderComponent,
        V1RegionRenderComponent,
        V1SolarSystemRenderComponent,
        V1LoyaltyCorporationRenderComponent,
        V1LoyaltyOfferRenderComponent,
        V1MiningOperationRenderComponent,
        V1StackRenderComponent,
        // - PIPES
        IskScaledPipe,
        M3VolumePipe
    ]
})
export class RendersModule { }
