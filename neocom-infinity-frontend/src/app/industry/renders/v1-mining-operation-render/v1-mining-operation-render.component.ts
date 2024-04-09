import { Component, OnInit } from '@angular/core'
import { V2NodeContainerRenderComponent } from '@app/modules/renders/v2-node-container-render/v2-node-container-render.component'
import { V1MiningOperation } from '@domain/industry/V1.MiningOperation.domain'

@Component({
    selector: 'v1-mining-operation',
    templateUrl: './v1-mining-operation-render.component.html',
    styleUrls: ['./v1-mining-operation-render.component.scss']
})
export class V1MiningOperationRenderComponent extends V2NodeContainerRenderComponent {

    public getNode(): V1MiningOperation {
        return this.node as V1MiningOperation
    }
    public getUniqueId(): string {
        // TODO - Use the common method to get the unique identifier
        if (this.node) return this.getNode().getIdentifier()
        return '-'
    }
    public getQuantity() {
        return 5
    }
}
