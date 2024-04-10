// - CORE
import { Component } from '@angular/core';
import { BOMGroup } from '@app/modules/industry/domain/V1BOMGroup.domain';
// - DOMAIN
import { V2FeatureRenderComponent } from '../v2-feature-render/v2-feature-render.component';
import { V2NodeContainerRenderComponent } from '../v2-node-container-render/v2-node-container-render.component';

@Component({
    selector: 'v1-bomgroup',
    templateUrl: './v1-bomgroup-render.component.html',
    styleUrls: ['./v1-bomgroup-render.component.scss']
})
export class V1BOMGroupRenderComponent extends V2NodeContainerRenderComponent {
    public getNode(): BOMGroup {
        return this.node as BOMGroup
    }
    public getLabel(): string {
        if (this.node) return this.getNode().getLabel()
        else return '-'
    }
    public getTotalCost () : string  {
        if (this.node) return this.getNode().getTotalCost()+' ISK'
        else return '- ISK'
    }
}
