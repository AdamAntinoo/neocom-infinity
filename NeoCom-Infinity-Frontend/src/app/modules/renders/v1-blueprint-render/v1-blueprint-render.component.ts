// - CORE
import { Component } from '@angular/core';
import { ProcessedBlueprint } from '@app/modules/industry/domain/V1ProcessedBlueprint.domain';
import { V1ProcessedBlueprintSummary } from '@app/modules/industry/domain/V1ProcessedBlueprintSummary.domain';
import { ProcessedBlueprintDto } from '@app/modules/industry/dto/ProcessedBlueprintDto.dto';
// - DOMAIN
import { V2NodeContainerRenderComponent } from '@app/modules/renders/v2-node-container-render/v2-node-container-render.component';
import { NeoComConstants } from '@app/platform/NeocomConstants.platform';
import { PlatformConstants } from '@env/PlatformConstants';

@Component({
    selector: 'v1-blueprint',
    templateUrl: './v1-blueprint-render.component.html',
    styleUrls: ['./v1-blueprint-render.component.scss']
})
export class V1BlueprintRenderComponent extends V2NodeContainerRenderComponent {
    public getNode(): V1ProcessedBlueprintSummary {
        return this.node as V1ProcessedBlueprintSummary
    }
    public getUniqueId(): string {
        if (this.node) return this.getNode().getUniqueId()
        return '-'
    }
    public getBlueprintIconUrl(): string {
        if (this.node) return this.getNode().blueprintTypeIconURL
        else return NeoComConstants.DEFAULT_ICON_PLACEHOLDER
    }
    public getBlueprintName(): string {
        if (this.node) return this.getNode().getBlueprintName()
        return '-'
    }
    public getOutputName(): string {
        if (this.node) return this.getNode().getOutputName()
        return '-'
    }
    public getManufactureCost(): number {
        if (this.node) return this.getNode().manufactureMaterialCost
        return 0.0
    }
    public getOutputPrice(): number {
        if (this.node) return this.getNode().outputPrice
        return 0.0
    }
    public getCostIndex(): number {
        if (this.node) return this.getNode().costIndex * 100.0
        return 0.0
    }
    public getOutputIconUrl(): string {
        if (this.node) return this.getNode().outputTypeIconURL
        else return NeoComConstants.DEFAULT_ICON_PLACEHOLDER
    }
}
