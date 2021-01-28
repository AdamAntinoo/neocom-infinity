// - CORE
import { Component } from '@angular/core';
import { Input } from '@angular/core';
// - DOMAIN
import { IsolationService } from '@innovative/services/isolation.service'
import { BackgroundEnabledComponent } from '@innovative/components/background-enabled/background-enabled.component'
import { IRefreshable } from '@innovative/domain/interfaces/IRefreshable.interface'
import { HALNode } from '@domain/hal/HALNode.hal';
import { IViewer } from '@innovative/domain/interfaces/IViewer.interface';
import { NeoCom } from '@domain/NeoCom.domain';
import { ICollaboration } from '@innovative/domain/interfaces/ICollaboration.interface';
import { NeoComDelayed } from '@domain/core/NeoComDelayed.domain';

@Component({
    selector: 'v2-node-container',
    templateUrl: './v2-node-container-render.component.html',
    styleUrls: ['./v2-node-container-render.component.scss']
})
export class V2NodeContainerRenderComponent extends BackgroundEnabledComponent {
    @Input() container: IViewer;
    @Input() node: NeoCom;
    @Input() variant: string = '-DEFAULT-';
    @Input() index: number = 1;
    @Input() selectOnHover: boolean = false

    // - G E T T E R S
    public getNode(): NeoCom {
        return this.node;
    }
    public getVariant(): string {
        return this.variant;
    }
    public isReady(): boolean {
        if (this.node instanceof NeoComDelayed) {
            const delayed = <NeoComDelayed>this.node
            return delayed.isReady()
        } else return true
    }
    public toggleExpanded(): void {
        if (null != this.node) {
            if (this.node.isExpandable()) {
                this.node.toggleExpanded()
                this.container.notifyDataChanged();
            }
        }
    }
    public isExpanded(): boolean {
        if (null != this.node)
            return this.node.isExpanded();
    }
    public isActive(): boolean {
        if (null != this.node) return this.node.isActive();
        else return true;
    }
    public isEmpty(target?: any): boolean {
        if (null == target) return true;
        if (Object.keys(target).length == 0) return true;
        return false;
    }

    // - I N T E R A C T I O N S
    /**
     * Controls selection click on any node. The click can progress from the node so this is the last click interceptor.
     * If the user clicks the node then it should toggle the selection state and update the container selection manager.
     */
    public onClick(): void {
        console.log('><[V2NodeContainerRenderComponent.onClick]')
        this.node.toggleSelected()
        this.container.updateSelection(this.node)
    }
    /**
     * Pass the container panel the node that is being entered so if there is additional data it can be exported to another panel.
     * @param target target node that is being entered by the cursor.
     */
    public mouseEnter(target: ICollaboration) {
        console.log('>Selecting target')
        if (this.selectOnHover) this.container.enterSelected(target)
    }
}
