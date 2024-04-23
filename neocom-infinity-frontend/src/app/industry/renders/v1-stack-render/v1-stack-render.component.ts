import { Component } from '@angular/core'
import { V2NodeContainerRenderComponent } from '@app/modules/renders/v2-node-container-render/v2-node-container-render.component'
import { V1Stack } from '@domain/esi/V1.Stack.domain'

@Component({
    selector: 'v1-stack',
    templateUrl: './v1-stack-render.component.html',
    styleUrls: ['./v1-stack-render.component.scss']
})
export class V1StackRenderComponent extends V2NodeContainerRenderComponent {

    public getNode(): V1Stack {
        console.log('Stack->' + this.node.jsonClass)
        return this.node as V1Stack
    }
    public getUniqueId(): string {
        if (this.node) return this.getNode().getIdentifier()
        return '-'
    }
    public getURLIcon(): string {
        return 'url'
    }
    public getName(): string {
        return this.getNode().type.name
    }
    public getQuantity(): number {
        return this.getNode().quantity
    }
    public getPrice(): number {
        return this.getNode().getSellPrice()
    }
}
