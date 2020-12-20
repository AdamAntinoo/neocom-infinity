// - CORE
import { Component } from '@angular/core';
import { IndustryResource } from '@app/modules/industry/domain/V1IndustryResource.domain';
import { platformConstants } from '@env/platform-constants';
// - DOMAIN
import { V2NodeContainerRenderComponent } from '../v2-node-container-render/v2-node-container-render.component';

@Component({
    selector: 'v1-bomresource',
    templateUrl: './v1-bomresource-render.component.html',
    styleUrls: ['./v1-bomresource-render.component.scss']
})
export class V1BOMResourceRenderComponent extends V2NodeContainerRenderComponent {
    public getNode(): IndustryResource {
        return this.node as IndustryResource
    }
    public getURLIcon(): string {
        if (this.node) return 'https://image.eveonline.com/Type/' + this.getNode().getTypeId() + '_64.png'
        else return platformConstants.DEFAULT_ICON_PLACEHOLDER
    }
    public getName(): string {
        if (this.node) return this.getNode().getName()
        else return '-'
    }
    public getQuantity(): string {
        if (this.node) return this.getNode().quantity + ''
        else return '-'
    }
    public getPrice(): string {
        if (this.node) return this.getNode().price + ' ISK'
        else return '- ISK'
    }
}
