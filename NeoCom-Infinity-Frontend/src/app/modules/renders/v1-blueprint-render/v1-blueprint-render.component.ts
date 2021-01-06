// - CORE
import { Component } from '@angular/core';
import { ProcessedBlueprint } from '@app/modules/industry/domain/V1ProcessedBlueprint.domain';
import { ProcessedBlueprintDto } from '@app/modules/industry/dto/ProcessedBlueprintDto.dto';
// - DOMAIN
import { V2NodeContainerRenderComponent } from '@app/modules/renders/v2-node-container-render/v2-node-container-render.component';
import { platformConstants } from '@env/platform-constants';

@Component({
    selector: 'v1-blueprint',
    templateUrl: './v1-blueprint-render.component.html',
    styleUrls: ['./v1-blueprint-render.component.scss']
})
export class V1BlueprintRenderComponent extends V2NodeContainerRenderComponent {
    public getNode(): ProcessedBlueprint {
        return this.node as ProcessedBlueprint
    }
    public getUniqueId(): string {
        if (this.node)
            if (this.getNode().type) return 'typeid-' + this.getNode().getUniqueId()
        return '-'
    }
    public getTypeIconUrl(): string {
        if (this.node) {
            if (this.getNode().type) return this.getNode().getTypeIconURL()
        } else return platformConstants.DEFAULT_ICON_PLACEHOLDER
    }
    public getName(): string {
        if (this.node)
            if (this.getNode().type) return this.getNode().getName()
        return '-'
    }
    public getModuleName(): string {
        if (this.node)
            if (this.getNode().type) return this.getNode().getModuleName()
        return '-'
    }
}
