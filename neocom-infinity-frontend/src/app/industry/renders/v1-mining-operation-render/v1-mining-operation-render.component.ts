import { Component, OnInit } from '@angular/core'
import { V2NodeContainerRenderComponent } from '@app/modules/renders/v2-node-container-render/v2-node-container-render.component'
import { V1Stack } from '@domain/esi/V1.Stack.domain'
import { V3MiningOperation } from '@domain/industry/V3.MiningOperation.domain'

@Component({
    selector: 'v1-mining-operation',
    templateUrl: './v1-mining-operation-render.component.html',
    styleUrls: ['./v1-mining-operation-render.component.scss']
})
export class V1MiningOperationRenderComponent extends V2NodeContainerRenderComponent {

    public getNode(): V3MiningOperation {
        return this.node as V3MiningOperation
    }
    public getUniqueId(): string {
        if (this.node) return this.getNode().getIdentifier()
        return '-'
    }
    public getResources(): V1Stack[] {
        return this.getNode().getResources()
    }
    public getDate(): string {
        return '2024-01-01'
    }
    public getSystemName(): string {
        return 'SYSTEM-NAME'
    }
    public getSystemId(): number {
        return 34
    }
}
