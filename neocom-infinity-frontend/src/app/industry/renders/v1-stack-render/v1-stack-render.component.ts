import { Component } from '@angular/core'
import { V2NodeContainerRenderComponent } from '@app/modules/renders/v2-node-container-render/v2-node-container-render.component'
import { V1Stack } from '@domain/esi/V1.Stack.domain'
i
@Component({
    selector: 'v1-stack',
    templateUrl: './v1-stack-render.component.html',
    styleUrls: ['./v1-stack-render.component.scss']
})
export class V1StackRenderComponent extends V2NodeContainerRenderComponent {

    public getNode(): V1Stack {
        return this.node as V1Stack
    }
    public getURLIcon(): string {
        return 'url'
    }
    public getName(): string {
        return 'name'
    }
    public getQuantity(): string {
        return 'name'
    }
}
